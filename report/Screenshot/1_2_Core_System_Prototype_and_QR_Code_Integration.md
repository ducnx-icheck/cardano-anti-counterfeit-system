# **BUSINESS PROCESS DOCUMENTATION: BATCH ACTIVATION & ON-CHAIN TRACEABILITY (FULL FLOW)**

## **1. Objective**

To demonstrate the end-to-end business logic: from the initial batch
activation on the iCheck Enterprise Portal to the automated metadata
packaging and transaction execution on the Cardano Blockchain network.

##  

## **PART I: BATCH INITIALIZATION (INIT ON-CHAIN)**

### **Step 1: Configuration and Blockchain Logging Toggle**

On the **iCheck Enterprise Portal**, the Administrator selects a
specific batch from the \"Pending Activation\" list and configures the
authentication parameters.

- **Key Action:** Toggle the **\'Record Batch Log to Blockchain\'**
  switch to **ON**.

- **Note:** This constraint is strictly enforced; the option is only
  available during this pre-activation stage.

![](./media/image3.png)

### **Step 2: Activation Confirmation & API Call**

Once the **\'Confirm Activation\'** button is clicked, the Backend
system immediately packages the metadata (Product name, Batch ID,
manufacturing date, etc.) and executes an **API call to the Cardano
Gateway**.

![](./media/image5.png)

![](./media/image1.png)

### **Step 3: Database Verification -- \'Pending\' Status**

To verify the process, we query the iCheck Database at the moment the
transaction is broadcast.

- **Status:** Init_Status = \'Pending\'

- **Meaning:** The transaction has left the internal system and is
  currently in the Cardano Mempool, awaiting validation by Stake Pools.

![](./media/image4.png)

### **Step 4: Finalizing Init On-chain -- \'Completed\' Status**

Once the Cardano network confirms the block, the system receives a
callback signal and updates the record.

- **Status:** Init_Status = \'Completed\'

- **Data:** A unique, immutable **Transaction Hash** is generated and
  linked to the batch.

![](./media/image6.png)

## **PART II: REAL-WORLD INTERACTION (SCAN FLOW)**

### **Step 5: First Scan (Product Activation)**

When the product reaches the market, the consumer scans the QR code
using the iCheck App or a mobile camera.

- **System Action:** The Backend identifies this as the first
  interaction. It records the activation and triggers a **First Scan API
  call** to Cardano to timestamp the exact moment the product entered
  consumption.

![](./media/image2.png)

### **Step 6: Second Scan (Counterfeit Alert)**

If the QR code is duplicated or copied and scanned by a subsequent user:

- **System Action:** The Backend compares the scan count. Detecting n \>
  1, it triggers a security alert.

![](./media/image7.png)

## **SUMMARY OF DATA FLOW**

1.  **Init:** Enterprise commits product data to the Public Ledger.

2.  **First Scan:** Consumer claims ownership and activates the warranty
    on-chain.

3.  **Second Scan:** System leverages Blockchain immutability to prove
    the code is no longer unique, effectively flagging counterfeits.



