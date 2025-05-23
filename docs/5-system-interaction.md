# System Interactions

## Introduction

This document details the communication methods and protocols between the main components of the anti-counterfeit QR code management system integrated with the Cardano blockchain. The goal is to clarify how data and commands are transmitted between components to ensure smooth, secure, and efficient system operation. This document specifically focuses on API interfaces and other interaction mechanisms.

## Main System Interactions

The system involves the following main interactions:
1. Communication between the Mobile Application and the Backend API.
2. Communication between the Manufacturer Web Portal and the Backend API.
3. Communication between the Backend API and the Cardano Blockchain.
4. Communication between the Backend API and the Off-chain Database.

## Detailed Component Interactions

### Communication between the Mobile Application and the Backend API

* **Communication Method**: RESTful API over HTTPS.
* **Data Format**: JSON.
* **API Endpoints**:
    * `/api/verify` (POST): Receives the QR code value from the application and returns the verification result (`{"status": "genuine" | "counterfeit" | "error", "message": "..."}`).
    * `/api/history` (GET, optional `qr_code` parameter): (Optional) Retrieves the scan history for a QR code.
* **Data Flow Details**:
    * The application sends the scanned QR code in the request body (JSON).
    * The Backend API processes it and returns the verification result in the response body (JSON).

### Communication between the Manufacturer Portal and the Backend API

* **Communication Method**: RESTful API over HTTPS.
* **Data Format**: JSON.
* **API Endpoints**:
    * `/api/auth/login` (POST): Authenticates the manufacturer and returns a JWT token.
    * `/api/auth/register` (POST): Registers a new manufacturer account.
    * `/api/products` (GET): Retrieves the manufacturer's product list (requires JWT token).
    * `/api/products` (POST): Creates a new product (requires JWT token).
    * `/api/products/{product_id}` (PUT): Updates product information (requires JWT token).
    * `/api/qrcodes/generate` (POST): Requests the generation of QR codes for products (requires JWT token).
    * `/api/reports/product-scans` (GET): Retrieves reports on product scan activity (requires JWT token).
* **Data Flow Details**:
    * The web portal sends requests to the respective API endpoints based on user actions.
    * Data (e.g., product information, QR code generation requests) is typically sent in the request body (JSON).
    * The Backend API processes it and returns a JSON response containing the success/failure status or the requested data.
* **Authentication**:
    * Uses JWT (JSON Web Tokens) to authenticate requests from the Manufacturer Web Portal after successful login.
    * The token is sent in the `Authorization` header with the `Bearer <token>` schema.

### Communication between the Backend API and the Cardano Blockchain

* **Communication Method**: API of a Cardano API node service (e.g., Blockfrost, Koios) over HTTPS.
* **Data Format**: JSON as specified by the API node service.
* **Libraries/SDKs**: Utilizes Python libraries (e.g., `blockfrost-python`), Node.js (`blockfrost-js`), or direct HTTP clients to interact with the API node.
* **Building and Signing Transactions**:
    * Employs the Cardano Serialization Library (CSL) or its wrappers to construct transaction structures and metadata.
    * Private key management and transaction signing are performed using secure methods.
* **Main Operations**:
    * **Querying QR Code Initialization Transactions**: Uses API node search endpoints to find transactions with metadata key `event` as `product_init` and containing the `qr_code_value`.
    * **Querying First Scan Transactions**: Similarly, searches for transactions with metadata key `event` as `first_scan_recorded` and containing the `qr_code_value`.
    * **Submitting Transactions**: Uses the API node's transaction submission endpoint (`/api/txs/submit`).
        * The signed raw transaction (as a hex string) is placed in the request body (POST).
        * The API node returns the transaction hash upon acceptance. The Backend API handles error responses and implements appropriate retry mechanisms.
    * **Tracking Transaction Status**: Uses API node endpoints (e.g., `/api/txs/{tx_hash}/status`) to monitor the confirmation status of submitted transactions. This is crucial to ensure the first scan is successfully recorded on the blockchain.
* **Data Flow Details**:
    * The Backend API constructs HTTP requests (GET or POST) to the API node's endpoints with necessary parameters (e.g., transaction hash, metadata filters).
    * The API node returns a JSON response containing blockchain data.
    * For transaction submission, the Backend API sends the signed raw transaction in the request body (POST).

### Communication between the Backend API and the Off-chain Database

* **Communication Method**: Depends on the database type (e.g., JDBC/ODBC for SQL, client libraries for NoSQL).
* **Data Format**: Data is exchanged in the database's native format (e.g., rows/columns for SQL, documents for NoSQL).
* **ORM/ODM**: Utilizes libraries like SQLAlchemy (Python) or Mongoose (Node.js) to interact with the database, mapping code objects to database records.
* **Database Schema/Collections**: (Details will be in the Data Workflow document) Includes tables/collections for `Products`, `QRMapping` (potentially including `txid` and `first_scan_txid`), `(Optional) ScanHistory`, and `Users`.
* **Data Flow Details**:
    * The Backend API uses database connection libraries to send queries and data manipulation commands to the database server and receive results.
    * Common queries include searching for products by `qr_code_value` (joining `QRMapping` and `Products`), checking for existing `first_scan_txid`, and storing `first_scan_txid`.
* **Connection Management and Pooling**: Employs connection pooling to reuse database connections, reducing overhead and improving performance.

## Technical Considerations

* **Security**: Implement comprehensive security measures for all communication channels, including encryption, strong authentication and authorization, and protection against common vulnerabilities. Special attention to securing blockchain private keys.
* **Performance**: Optimize API calls and database queries to ensure fast response times. Implement strategic caching.
* **Reliability**: Implement error handling, logging, and monitoring to ensure system stability. Use retry mechanisms for unreliable communications.
* **Scalability**: Design APIs and the system to handle a large volume of requests. Consider a microservices architecture for the Backend API.
* **Data Consistency**: Ensure consistency between on-chain (verification status) and off-chain (product information, scan history) data.

## Conclusion

This document has provided a detailed overview of the communication methods and protocols between the main components of the system. The careful design and implementation of these interactions are crucial for the efficient, secure, and scalable operation of the anti-counterfeit QR code management system.