# Evidence 3: User Feedback Summary

**Acceptance Criteria:** Basic feedback collected from pilot users, highlighting key insights for improvement.

## 3.1. Pilot User Group Details
*Detailed information about the pilot user test group.*
- **Total Users:** 8
- **User Profile:** 
  - Internal Users: 6
  - External Clients: 2

## 3.2. Feedback Collection Method
*Methods used to collect user feedback.*
- Direct interview with pilot users.
- Observation during pilot usage.
- Manual issue logging from the support/product team.
- Basic feedback form after pilot scenario completion.

## 3.3. Key Insights and Feedback
*Summary of feedback and detailed analysis of collected insights.*

### Positive Feedback:
- Data display is relatively clear, and the information is visual and good.

### Areas for Improvement:
- **UX Flow:** The user experience flow is unclear after the scanning step.
- **Transaction Status:** Need more transparency and clarity regarding the transaction status after the user performs a scan.
- **Delay Experience:** There is a delay of about 3 seconds due to the batching mechanism, but it is at an acceptable level (Low Impact).

### Raw Feedback:
| No. | Detailed Feedback |
|-----|-------------------|
| 1   | Quick scan but the next steps are unclear. |
| 2   | Delay of about 3 seconds. |
| 3   | Information display is good. |
| 4   | Need clearer transaction status after scanning. |

## 3.4. Next Steps / Action Plan
*Plan to address feedback and improve the system in the next phase.*

**Optimization Plan for Discovered Issues:**
- **Add instruction UI after scan (Priority: High):** Add instructional UI immediately after scanning to resolve the unclear UX issue.
- **Add submitted/confirmed status indicator (Priority: High):** Add transaction status display (submitted/confirmed) to make the status transparent.
- **Reduce batch window from 5s to 2s (Priority: Medium):** Reduce the Batch waiting time from 5 seconds down to 2 seconds to overcome the delay situation.

**Next Phase Plan:**
- Optimize real-time batching logic to minimize delay.
- Upgrade the ability to track progression and transaction statuses on the UI.
- Scale pilot user base from 8 users to 100+ users.
- Prepare production-readiness checklist.
