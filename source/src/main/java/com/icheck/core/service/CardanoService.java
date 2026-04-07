package com.icheck.core.service;

import com.bloxbean.cardano.client.account.Account;
import com.bloxbean.cardano.client.api.common.OrderEnum;
import com.bloxbean.cardano.client.api.model.Result;
import com.bloxbean.cardano.client.api.model.Utxo;
import com.bloxbean.cardano.client.backend.api.BackendService;
import com.bloxbean.cardano.client.backend.model.TransactionContent;
import com.bloxbean.cardano.client.backend.model.metadata.MetadataJSONContent;
import com.bloxbean.cardano.client.function.helper.SignerProviders;
import com.bloxbean.cardano.client.quicktx.QuickTxBuilder;
import com.bloxbean.cardano.client.quicktx.Tx;
import com.icheck.core.config.Appconfig;
import com.icheck.core.model.request.CardanoQrcodeRequest;
import com.icheck.core.utils.AppUtils;
import com.icheck.core.utils.MessageMetadataCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class CardanoService extends BaseComponent {

	@Autowired
	private BackendService backend;
	
	@Autowired
	private Account account;


//    public Mono<String> putCustomerCip20(CardanoQrcodeRequest<?> request) {
//        return waitForAvailableUtxo() // đảm bảo có UTxO trước khi gửi
//                .then(Mono.defer(() -> Mono.fromCallable(() -> {
//                            MessageMetadataCustom md = MessageMetadataCustom.create();
//
//                            Object data = request.getData();
//                            if (data != null) {
//                                if (data instanceof Collection<?>) {
//                                    for (Object item : (Collection<?>) data) {
//                                        String json = Appconfig.MAPPER.writeValueAsString(item);
//                                        md.add(json);
//                                    }
//                                } else {
//                                    String json = Appconfig.MAPPER.writeValueAsString(data);
//                                    md.add(json);
//                                }
//                            }
//
//                            Tx tx = new Tx().attachMetadata(md).from(walletAddress);
//                            QuickTxBuilder quick = new QuickTxBuilder(backend);
//                            Result<String> res = quick.compose(tx)
//                                    .withSigner(SignerProviders.signerFrom(account))
//                                    .complete();
//
//                            if (res.isSuccessful()) {
//                                LOGGER.info("[Cardano] TX submitted successfully: {}", res.getValue());
//                                return res.getValue(); // txHash
//                            }
//
//                            throw new IllegalStateException("Submit failed: " + res.getResponse());
//                        })
//                        .subscribeOn(Schedulers.boundedElastic())))
//                // Retry logic: chỉ retry khi lỗi không phải do logic sai
//                .retryWhen(
//                        Retry.backoff(3, Duration.ofSeconds(10))
//                                .filter(ex -> ex instanceof IllegalStateException)
//                                .onRetryExhaustedThrow((spec, signal) -> signal.failure())
//                )
//                .timeout(Duration.ofMinutes(2)) // bảo vệ không bị treo quá lâu
//                .doOnError(e -> LOGGER.error("[Cardano] TX failed: {}", e.getMessage()));
//    }


	private Mono<Void> waitForAvailableUtxo() {
		return Mono.defer(() -> Mono.fromCallable(() -> backend.getUtxoService().getUtxos(walletAddress, 0, 10))
						.subscribeOn(Schedulers.boundedElastic())
						.flatMap(result -> {
							List<Utxo> utxos = result.getValue();
							if (utxos == null || utxos.isEmpty()) {
								return Mono.empty();
							}
							return Flux.fromIterable(utxos)
									.filter(utxo -> utxo != null && utxo.getAmount() != null && !utxo.getAmount().isEmpty())
									.next()
									.switchIfEmpty(Mono.empty());
						})
				)
				.repeatWhenEmpty(repeat -> repeat.delayElements(Duration.ofSeconds(5)))
				.then();
	}

    public Mono<String> putCustomerCip20(CardanoQrcodeRequest request) {
		return Mono.fromCallable(() -> {
			MessageMetadataCustom md = MessageMetadataCustom.create();
			Object data = request.getData();
			if (data != null) {
				if (data instanceof Collection<?>) {
					for (Object item : (Collection<?>) data) {
						String json = Appconfig.MAPPER.writeValueAsString(item);
						md.add(json);
					}
				} else {
					String json = Appconfig.MAPPER.writeValueAsString(data);
					md.add(json);
				}
			}
			Tx tx = new Tx().attachMetadata(md).from(walletAddress); // chọn UTxO nguồn & change về cùng địa chỉ
			QuickTxBuilder quick = new QuickTxBuilder(backend);
			Result<String> res = quick.compose(tx).withSigner(SignerProviders.signerFrom(account)).complete(); // build + sign + submit
			if (res.isSuccessful()) return res.getValue(); // txHash
			throw new IllegalStateException("Submit failed: " + res.getResponse());
		}).subscribeOn(Schedulers.boundedElastic());
	}

	  public Flux<String> listTxHashes(String address, int page, int count, OrderEnum order) {
	    return Mono
				.fromCallable(() -> {
					return backend.getAddressService().getTransactions(address, count, page, order);
				}).subscribeOn(Schedulers.boundedElastic())
				.flatMapMany(res -> {
					if (!res.isSuccessful()) {
						return Flux.error(new IllegalStateException(res.getResponse()));
					}
					return Flux.fromIterable(res.getValue()).map(item -> item.getTxHash());
				});
	  }

	  public Mono<Map<String, Object>> getTxWithMetadata(String txHash) {
			Mono<Result<TransactionContent>> txMono = Mono.fromCallable(() -> {
				return backend.getTransactionService().getTransaction(txHash);
			}).subscribeOn(Schedulers.boundedElastic());

			// tùy phiên bản lib, tên hàm có thể là getTransactionMetadata / getMetadata
			Mono<Result<List<MetadataJSONContent>>> mdMono = Mono.fromCallable(() -> {
				return backend.getMetadataService().getJSONMetadataByTxnHash(txHash);
			}).subscribeOn(Schedulers.boundedElastic());

			return Mono.zip(txMono, mdMono).flatMap(tuple -> {
				Result<TransactionContent> txRes = tuple.getT1();
				Result<List<MetadataJSONContent>> mdRes = tuple.getT2();
				if (!txRes.isSuccessful()) {
					return Mono.error(new IllegalStateException(txRes.getResponse()));
				}
				if (!mdRes.isSuccessful()) {
					return Mono.error(new IllegalStateException(mdRes.getResponse()));
				}
				TransactionContent tx = txRes.getValue(); // blockHeight, blockTime, fees, size...
				List<MetadataJSONContent> md = mdRes.getValue(); // danh sách metadata theo label
				return Mono.just(Map.of("hash", txHash, "blockHeight", tx.getBlockHeight(), "blockTime", tx.getBlockTime(), "fees", tx.getFees(), "metadata", md));
			});
		}
}
