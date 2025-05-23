# Resource Allocation

## Introduction

This document outlines the specific allocation of resources required for each main component of the anti-counterfeit QR code management system integrated with the Cardano blockchain. The resources include hardware, software, personnel, and external services. This detailed resource allocation is crucial for ensuring efficient, stable, and scalable system operation, while optimizing costs and serving as the basis for detailed budget planning.

## Required Resource Types

This section outlines the core resources required for the practical development and deployment of the anti-counterfeit QR code system integrated with the Cardano blockchain. The focus is on the essential elements needed to support a lean but effective technical team.

* **Hardware**:
    * Developer workstations (laptops or desktops with >=16GB RAM, SSD, and modern CPUs)
    * Mobile devices for testing (3 Android, 2 iOS)
    * Server instances for hosting backend infrastructure.
* **Software**:
    * Development IDEs (e.g., VS Code, Android Studio, Xcode)
    * Libraries, SDKs, frameworks (Kotlin, Swift, React, Node.js/Python, PostgreSQL client libraries)
    * DevOps tools (e.g., Docker, GitHub Actions, CI/CD pipeline tools)
    * Project management & collaboration (e.g., Jira, Slack)
* **Personnel**:
    * 2 Mobile Developers (iOS + Android)
    * 2 Frontend Developers (React)
    * 2 Backend Developers (Node.js/Python)
    * 1 DevOps / Infrastructure Engineer (part-time)
    * 1 Blockchain Integration Specialist (part-time)
    * 1 UI/UX Designer (part-time)
    * 1 QA/Tester
    * 1 Project Manager / Product Owner
* **External Services**:
    * Cardano API service (e.g., Blockfrost, Enterprise Plan)
    * Cloud infrastructure (e.g., AWS/GCP/Azure for database, API hosting)
    * Email/SMS delivery (optional – for product notifications)

## Resource Allocation by Component

### Mobile Application (iOS & Android)

* **Human Resources**:
    * 2 Mobile Developers (1 Android, 1 iOS)
    * 1 UX/UI Designer (shared role)
    * 1 QA Tester (shared role)
* **Infrastructure & Tools**:
    * 5 test devices (3 Android, 2 iOS)
    * Android Studio, Xcode, mobile SDKs
* **Purpose**:
    * Build user-friendly apps for product verification via QR scanning
    * Integrate secure API communication and scan result display

### Manufacturer Web Portal

* **Human Resources**:
    * 2 Front-end Developers (React)
    * 1 UX/UI Designer (shared role)
    * 1 QA Tester (shared role)
* **Infrastructure & Tools**:
    * Developer laptops
    * React 18.x, TypeScript, Material UI
* **Purpose**:
    * Enable product creation, QR code generation, and scan report access
    * Provide secure manufacturer authentication and dashboard UX

### Backend API & Blockchain Integration

* **Human Resources**:
    * 2 Back-end Developers (Node.js or Python)
    * 1 Blockchain Specialist (part-time)
* **Infrastructure & Tools**:
    * Node.js or Python stack with PostgreSQL
    * Integration with Cardano via Blockfrost API
    * Cardano Serialization Library (CSL) for transaction handling
* **Purpose**:
    * Process QR code verification requests
    * Submit first-scan transactions to Cardano blockchain
    * Query on-chain metadata and off-chain product data

### Off-chain Database & Infrastructure

* **Human Resources**:
    * 1 Infrastructure/DevOps Engineer (part-time)
    * Backend developers assist with DB optimization
* **Infrastructure & Tools**:
    * GCP PostgreSQL instance
    * Cloud monitoring tools
    * CI/CD and container orchestration (Docker, GitHub Actions)
* **Purpose**:
    * Store and retrieve product metadata and scan records
    * Ensure scalability, uptime, and resource tracking

### Product Management & QA

* **Human Resources**:
    * 1 Product Owner
    * 1 QA Tester (shared across components)
* **Infrastructure & Tools**:
    * Project management via Jira or Notion
    * Manual and automated testing setup
* **Purpose**:
    * Drive functional delivery, define priorities, and validate features
    * Coordinate between blockchain, database, and UX teams

## Projected Budget Allocation

| Role / Item | Estimated Budget |
|---|---|
| Developer – Front-end | ₳ 30,000 |
| Developer – Back-end | ₳ 80,000 |
| Product Owner | ₳ 30,000 |
| UX/UI Designer | ₳ 10,000 |
| QA Tester | ₳ 20,000 |
| Infrastructure & Cloud Services | ₳ 30,000 |

**Subtotal (Tech Stack)**: ₳ 200,000

**Note**: Budget is denominated in ADA (₳), aligned with Catalyst funding format. Infrastructure cost includes Cardano API services, cloud database (e.g., AWS RDS), monitoring tools, and CI/CD pipeline resources.

## Conclusion

The strategic and planned allocation of resources, combined with diligent monitoring and flexible adjustments based on real-world metrics, is paramount for the successful technical and cost-effective development and maintenance of the anti-counterfeit QR code management system integrated with Cardano. This document provides a detailed framework for resource management throughout the project lifecycle.