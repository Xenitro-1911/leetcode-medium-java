# 002 - Add Two Numbers

## Problem Summary
Given two non-empty linked lists representing non-negative integers stored in **reverse order**, return their sum as a linked list in the same format.

## Approach: Digit-by-digit with carry

Since digits are already stored ones-first, we can walk both lists simultaneously and add digit by digit — exactly like grade-school addition — tracking the carry as we go.

### Key variables
- `sum` — doubles as both the current column sum and the carry between iterations. After extracting the digit (`sum % 10`), dividing by 10 leaves only the carry for the next round.
- `head` — a dummy sentinel node (`new ListNode(0)`) that simplifies list construction; we never have to special-case the first node.
- `x` — holds the original position of `head` so we can return `x.next` at the end, skipping the sentinel.

### Algorithm
1. Walk both lists together while both have nodes, adding `l1.val + l2.val + carry`.
2. Walk the remaining `l1` nodes (if any), adding `l1.val + carry`.
3. Walk the remaining `l2` nodes (if any), adding `l2.val + carry`.
4. After all loops, if `sum > 0` a final carry node must be appended.

## Why the dummy head pattern?

Without it, the first node requires special-cased logic (`if result == null`). The dummy node lets every iteration follow the same `head.next = new ListNode(...)` / `head = head.next` pattern uniformly.

## Edge Cases
| Input | Expected | Reason |
|---|---|---|
| `[0]` + `[0]` | `[0]` | Zero + zero; loop runs once, no carry |
| `[5]` + `[5]` | `[0,1]` | Carry survives after all loops |
| `[9,9,9]` + `[1]` | `[0,0,0,1]` | Carry propagates through all digits |
| `[1,9,9,9,9,9,9,9,9,9]` + `[9]` | `[0,0,0,0,0,0,0,0,0,2]` | Long list; carry resolved at last digit |
| Lists of unequal length | correct | Handled by separate remainder loops |

## Complexity
- **Time:** O(max(m, n)) — each list is traversed once
- **Space:** O(max(m, n)) — output list length is at most max(m, n) + 1

## Key Learnings
- `sum % 10` extracts the current digit; `sum / 10` propagates the carry — no need to track them as separate variables.
- The dummy sentinel node is a standard linked list construction pattern — use it whenever building a new list from scratch.
- Don't reconstruct the full integer from the list — it overflows for long inputs and wastes the reversed-order gift the problem gives you.
- Always check for a leftover carry **after** the main loops exit.