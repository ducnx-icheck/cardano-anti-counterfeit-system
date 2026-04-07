package com.icheck.core.config;


import com.bloxbean.cardano.client.account.Account;
import com.bloxbean.cardano.client.backend.api.BackendService;
import com.bloxbean.cardano.client.backend.blockfrost.service.BFBackendService;
import com.bloxbean.cardano.client.common.model.Networks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardanoConfig {

  @Bean
  public BackendService backend(@Value("${blockfrost.projectId}") String pid, @Value("${cardano.network}") String net) {
    String url = switch (net) {
      case "mainnet" -> "https://cardano-mainnet.blockfrost.io/api/v0/";
      case "preprod" -> "https://cardano-preprod.blockfrost.io/api/v0/";
      default        -> "https://cardano-preview.blockfrost.io/api/v0/";
    };
    // BFBackendService tự gắn header "project_id: <pid>"
    return new BFBackendService(url, pid);
  }

  @Bean
  public Account account(@Value("${cardano.wallet.mnemonic}") String mnemonic, @Value("${cardano.network}") String net) {
    return switch (net) {
      case "mainnet" -> new Account(Networks.mainnet(), mnemonic);
      case "preprod" -> new Account(Networks.preprod(), mnemonic);
      default        -> new Account(Networks.preview(), mnemonic);
    };
  }

  /** Địa chỉ dùng làm sender/change – ưu tiên cấu hình */
  @Bean
  public String walletAddress(Account account, @Value("${cardano.wallet.address:}") String address) {
    if (address != null && !address.isBlank()) return address.trim();
    return account.baseAddress(); // fallback nếu chưa set CARDANO_ADDRESS
  }
}
