# Evidence 5: Final Closeout Report

**Project Title:** Anti-Counterfeiting System Integrating with Cardano Blockchain
**Project ID / Number:** 1300021
**Milestone:** Final Deployment & Project Summary
**Environment:** Cardano Network (`mainnet`)

---

## Name of project and Project URL on IdeaScale/Fund
- **Name of project:** Anti-Counterfeiting System Integrating with Cardano Blockchain (Integration of Cardano Blockchain into iCheck's Anti-Counterfeiting Solution)
- **Project URL:** [Catalyst Fund 13 Proposal](https://projectcatalyst.io/funds/13/cardano-use-cases-product/anti-counterfeiting-system-integrating-with-cardano-blockchain)

## Your Project Number
**#1300021**

## Name of project manager
**Mr. Ngo Xuan Duc** (Co-founder & Product Director)

## Date project started
**January 15, 2025** (Milestone 1 Signoff)

## Date project completed
**December 2025** (Final Milestone Delivery)

## List of challenge KPIs and how the project addressed them
*(Challenge: Cardano Use Cases: Product)*
- **Enhancement & Extension:** We significantly enhanced iCheck's existing centralized Web2 anti-counterfeiting system by integrating Cardano. This empowers independent verification using the CIP-20 metadata standard without relying solely on centralized servers.
- **Ecosystem Benefit:** The project brought a large-scale real-world FMCG supply chain use case to the Cardano ecosystem, exposing traditional enterprises to Web3 infrastructure.
- **Adoption:** By anchoring standard commercial records into the Cardano ecosystem, the project drives high-volume, real-world utility transactions on the Cardano blockchain.

## List of project KPIs and how the project addressed them
- **Product Integration (>20,000 products):** Successfully integrated the Cardano blockchain logic into iCheck's backend. The architecture relies on Spring Boot 3.x Reactive to handle massive localized spikes in product scanning asynchronously.
- **Counterfeit Reduction:** Implemented an unforgeable "Proof of Existence" on the blockchain. Consumers and distributors can now independently verify batch origins directly on the distributed public ledger.
- **Adoption Rate & Scalability (Cost Reduction):** We developed a dynamic **Batching Algorithm** that "hoards" multiple product lifecycle queries within a short window. This optimized the on-chain cost from 0.170669 ADA per single record to just 0.030438 ADA per record (an **82.19% reduction**), making enterprise adoption economically viable.
- **User Engagement:** Conducted rigorous UX pilot testing. Users reacted overwhelmingly positively to blockchain transparency. We identified an acceptable latency tolerance of ~3 seconds for the batching algorithm and plan to mask this with UI improvements.

## Key achievements (in particular around collaboration and engagement)
- **Technical Integration:** Bridged enterprise Web2 architecture with Cardano Web3 ledger using Java 17+, BloxBean Cardano Client, and Blockfrost API.
- **Pilot Execution:** Ran a successful controlled Pilot program with internal administrators and external strategic partners acting as mock consumers.
- **Economic Scalability:** The Batching Algorithm solved the fundamental barrier of prohibitive costs for high-frequency micro-transactions.

## Key learnings
- **Overcoming UTxO Concurrency Clashes:** When simulating high-load scenarios where multiple transactions were constructed simultaneously, the system aggressively attempted to query and spend the identical Unspent Transaction Output (UTxO). We resolved this by introducing strategic thread-isolation and back-off delays (a 5-second delay timer with `Schedulers.boundedElastic()`). This guaranteed a 100% successful UTxO locking rate without bottlenecking the application.
- **UX & Blockchain Latency:** We learned that enterprise users tolerate a 3-second processing delay when they understand it guarantees unforgeable data security. However, clear UI indicators are strictly required to manage these expectations.

## Next steps for the product or service developed
1. **Batching Calibration:** Aggressively tightening the batching aggregation window from a 5-second interval strictly to a 2-second threshold.
2. **Application UX Overhaul:** Deploying immediate front-end revisions to introduce highly visible UI animations and status benchmarks (e.g., “Confirmed on Ledger”) to mask the blockchain latency.
3. **Enterprise Load Scaling:** Expanding testing from the pilot group directly into a 100+ concurrent user stress environment.
4. **Mainnet Monitoring & Optimization:** Continuously monitoring the performance, transaction costs, and wallet balances on the Cardano Mainnet to ensure stable operation and seamless enterprise adoption.

## Final thoughts/comments
The integration of Cardano into iCheck's supply chain network marks a significant step toward hybrid Web2/Web3 enterprise solutions. We are grateful for the Catalyst community's support, which enabled us to prove that blockchain infrastructure can handle real-world, high-volume tracking securely and economically.

## Links to other relevant project sources or documents.
- **GitHub Repository:** [cardano-anti-counterfeit-system](https://github.com/ducnx-icheck/cardano-anti-counterfeit-system)
- **Milestone Proof of Achievements:** [https://milestones.projectcatalyst.io/projects/1300021](https://milestones.projectcatalyst.io/projects/1300021)

## Link to Close-out video - must be either YouTube or Vimeo link only
- **Close-out Video:** [https://www.youtube.com/watch?v=KQEhFExEHWY](https://www.youtube.com/watch?v=KQEhFExEHWY)
