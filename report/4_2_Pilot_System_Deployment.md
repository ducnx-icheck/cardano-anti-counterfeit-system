# Evidence 2: Pilot System Deployment

**Acceptance Criteria:** System successfully deployed in a test environment.

## 2.1. Deployment Environment Details
*Information regarding the test environment.*
- **Environment:** Development & Test Environment
- **System URL/Host:** `api-qrcode-blockchain.dev.icheck.vn`
- **Deployment Date:** Operation recorded on `02/04/2026`

## 2.2. Deployment Logs & Screenshots
Below is a screenshot from the server demonstrating the successful startup of the system, including successful connections to the Database (MongoDB), Message Queue (RabbitMQ), and the initialization of the gRPC Service.

![Server Startup Logs](./Screenshot_1_Startup.png)

## 2.3. System Operation Screenshots (Execution)
The actual system log screenshot shows that the Worker (CardanoSyncService) operates stably, successfully processing the Queue and submitting Batching data to the Cardano network (returning the TxHash result `ad0a...` and an HTTP 200 OK request structure).

![Batch Execution Success Logs](./Screenshot_2_Execution.png)
