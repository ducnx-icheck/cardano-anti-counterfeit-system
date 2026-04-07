# Cardano Metadata Integration Service (Java Spring Boot)

This repository contains a specialized Java implementation for integrating business data (QR Code/Batch information) into the Cardano blockchain as on-chain metadata. The system is designed using a Reactive programming model and follows the CIP-20 (Message Metadata) and CIP-25 standards.

## 1. Overview

The core objective of this project is to provide a "Proof of Existence" for system-generated data by anchoring it onto the Cardano ledger. This ensures that records such as product batches, QR code registrations, and user actions are immutable and verifiable.

**Key Features:**
* **Reactive Stack:** Built with Spring Boot and Project Reactor (Mono/Flux) for high-concurrency handling.
* **Deterministic Fee Calculation:** Implements Cardano's native fee logic ($a \times \text{size} + b$) to ensure predictable transaction costs.
* **Batching Support:** Capability to include multiple data records within a single on-chain transaction to optimize ADA costs.
* **Multi-Network Support:** Seamless switching between mainnet, preprod, and preview environments via configuration.

## 2. Technical Stack

* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Blockchain Library:** BloxBean Cardano Client Lib
* **API Gateway:** Blockfrost.io

## 3. Project Structure & Components

### ⚙️ Configuration (CardanoConfig.java)
Manages the lifecycle of blockchain connection beans.
* **BackendService:** Connects to Blockfrost nodes based on the configured environment (mainnet, preprod, or preview).
* **Account:** Initializes the signing authority using a 24-word BIP39 mnemonic.
* **WalletAddress:** Manages the source and change addresses for transaction UTxOs.

### 🛠️ Core Service (CardanoService.java)
The business logic layer that handles blockchain interactions using non-blocking I/O.
* **putCustomerCip20:** The primary method for submitting metadata. It serializes `CardanoQrcodeRequest` data into JSON and attaches it to a transaction.
* **waitForAvailableUtxo:** A safety mechanism that polls the backend to ensure the wallet has valid UTxOs before attempting a submission.
* **getTxWithMetadata:** Retrieves and zips transaction details with its corresponding on-chain metadata for easy verification.

### 📦 Data Models (CardanoQrcodeRequest.java)
A generic wrapper (`<T>`) using Jackson and Lombok annotations to support any JSON-serializable batch or product information.

## 4. How It Works (The Flow)

1. **Request:** The application receives a data payload via `CardanoQrcodeRequest`.
2. **Serialization:** Objects are converted to JSON strings using a pre-configured `ObjectMapper`.
3. **Transaction Building:** The `QuickTxBuilder` composes a transaction, attaches the metadata, and selects UTxOs from the configured `walletAddress`.
4. **Signing:** The transaction is signed locally using the private key derived from the mnemonic.
5. **Submission:** The signed transaction is pushed to the Cardano network via Blockfrost. Upon success, a `txHash` is returned as a permanent receipt.

## 5. Configuration & Setup

To run this service, update your `application.properties` with the following credentials:

```properties
# Cardano Network Settings: mainnet | preprod | preview
cardano.network=preview
cardano.wallet.mnemonic=your twenty four word mnemonic phrase here...
cardano.wallet.address=addr_test...
blockfrost.projectId=your_blockfrost_project_id
```

## 6. Verification

Every transaction submitted by this service can be verified on a public explorer (e.g., Cardanoscan). The metadata is stored under a specific label and can be retrieved using the `getTxWithMetadata` method included in this service.
