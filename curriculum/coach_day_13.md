# Day 13 MiniMax coach

# Day 13 — Stack Pattern

**When to reach for a stack:**
- Matching/canceling pairs (parens, brackets)
- Evaluating expressions (postfix, infix with precedence)
- "Previous greater/smaller" or monotonic behavior
- Decoding nested structures

**Mental model:** Stack = "what's still pending / unresolved." Whatever you pop has been waiting for whatever you just pushed.

**Today's flow:**

1. **LC 150 RPN** — Number → push. Operator → pop b (top), pop a, push a ⊕ b. Don't swap! `a - b` ≠ `b - a`. Use `Integer.parseInt` and a `Deque<String>` so `push`/`pop` read cleanly.
2. **LC 22 Generate Parens** — Backtracking with two counters, not a stack check. Pruning is the win: only add `)` when `close < open`. Total strings = Catalan(n) — don't worry about duplicates, the constraints make them impossible.

**LC 224 stretch:** Two stacks — operands + operators. On `+`/`-`/`(` push; on `)` flush until `(`. Track sign separately for the unary-minus edge case.

**Edge cases to keep handy:**
- Single element input (LC 150: just return it)
- `n = 0` (LC 22: return `[""]`, not `[]`)
- Negative results / division by single operand

**Complexity target:** LC 150 → O(n) time, O(n) space. LC 22 → O(Cₙ · n) time, O(n) recursion depth.

Which one are you starting with — 150 or 22?
