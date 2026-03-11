# CARDANO BLOCKCHAIN INTEGRATION

## 1. Testing Overview

Testing activities are conducted to ensure that the Portal system integrating the Cardano Blockchain operates correctly according to the requirements described in the SRS document. The system allows enterprises to manage operations related to Cardano Blockchain integration, including: displaying the enterprise's blockchain status, managing the activation of stamp batches on the blockchain, recording Init On-chain when a stamp batch is activated, and recording First Scan On-chain when a QR code is scanned for the first time.

Testing Objectives:

- Verify that the system executes the business functions correctly according to the SRS document.

- Ensure that data among Portal -- CMS -- Cardano Blockchain is processed and synchronized accurately.

- Check the blockchain processing statuses: Pending, Completed.

- Verify the accuracy of the transaction hash and timestamp.

- Detect potential errors that could affect the writing of data to the blockchain or cause data discrepancies.

## 2. Testing Scope

The following functions will be tested:

### 1. Display Enterprise's Blockchain Status

- Display **Blockchain ON/OFF** status.

- Synchronize status data from CMS to Portal.

### 2. Configure ON/OFF for Logging Stamp Batches to Blockchain

- Display the ON/OFF toggle and corresponding labels when ON/OFF.

- Save user-selected configurations.

- Check the default status.

### 3. Init On-chain for Stamp Batches

- Activate stamp batches.

- Write activation data to the blockchain.

- Update transaction status:

  - Pending

  - Completed

- Display transaction information:

  - Transaction hash

  - Timestamp

- Test the following flows:

  - Blockchain ON

  - Blockchain OFF

  - Toggle ON

  - Toggle OFF

### 4. First Scan On-chain for QR

- Record the first QR scan.

- Send data to the Blockchain.

- Update transaction status.

- Display transaction hash information.

- Check the scenario of a second scan.

## 3. Test Environment

3.1 System Components:

- Portal: QR Check Portal management system.

- CMS: Enterprise management system.

- Blockchain: Cardano.

- QR Code Scanner.

3.2 Environment Architecture Flow:

- Admin configures the enterprise's Blockchain usage status in the CMS.

- Portal synchronizes the Blockchain status from the CMS.

- Portal sends the Init On-chain transaction to Cardano.

- Portal receives the transaction hash from the Blockchain.

- When a user scans the QR code → Portal sends the First Scan transaction.
