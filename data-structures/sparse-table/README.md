# Sparse Table (Range Minimum Query)

A high-performance data structure that answers range queries on static arrays in **O(1)** (constant time) after **O(N log N)** preprocessing.

This implementation uses the **Idempotency** property of operations like `min`, `max`, `gcd`, `and`, `or` to allow for overlapping query intervals.

---

## 🧠 How It Works (Visual Intuition)

### 1. The Core Idea: Powers of 2
Instead of calculating the answer for *every* possible subarray, we only pre-calculate answers for subarrays with lengths that are **Powers of 2** (`1, 2, 4, 8, ...`).

We store these in a 2D table `sparse[k][i]`:

- `k`: The power (length = `2^k`)
- `i`: The starting index

**Example:**  
`sparse[2][3]` stores the minimum of the range starting at index `3` with length `2^2 = 4`.

---

### 2. The "Magic" of O(1) Queries ⚡
To find the answer for *any* arbitrary range `[L, R]`, we don't need to split it into multiple disjoint segments (like a Segment Tree).

We just use **two overlapping blocks** of length `2^k` that cover the entire range:

1. **Block 1:** Starts at `L`
2. **Block 2:** Ends at `R`

Because operations like `min()` are **idempotent** (meaning `min(x, x) = x`), overlapping elements do not affect the result.

```
k = floor(log2(R - L + 1))

Answer = min(sparse[k][L], sparse[k][R - 2^k + 1])
```

---

## 🚀 Complexity

| Operation | Time Complexity |
|---|---|
| **Preprocessing** | O(N log N) |
| **Query** | O(1) |
| **Space** | O(N log N) |

---

## 💻 Example Usage (Java)

```java
int[] nums = {1, 3, -1, 5, 2, 6};
SparseTable st = new SparseTable(nums);

// Query range [2, 5] (indices are 0-based)
// Subarray: {-1, 5, 2, 6} -> Min is -1
int minVal = st.query(2, 5); 

System.out.println("Min in range: " + minVal); // Output: -1
```

---

## 📚 Practice Problems

Want to test your Sparse Table implementation? Try these:

---

### ⭐ **Maximum of Minimums (Codeforces 872B)**
🔗 https://codeforces.com/problemset/problem/872/B

**Context:**  
Find the maximum possible minimum value after performing operations on array partitions.

**Why it's useful:**
- Classic RMQ usage
- Helps understand range-based decision making
- Tests min/max reasoning

---

### 🔗 **Longest Regular Bracket Sequence (Codeforces 5C)**
🔗 https://codeforces.com/problemset/problem/5/C

**Context:**  
Find the longest valid bracket subsequence.

**Why it's useful:**
- Combines range queries + preprocessing ideas
- Advanced competitive programming usage
- Good for interval reasoning

---

## 💡 When to Use Sparse Table

Use Sparse Table when:

- Array is **static** (no updates)
- Many range queries exist
- Operation is **idempotent** (`min`, `max`, `gcd`, `and`, `or`)
- Need extremely fast query time

---

## ⚠️ Limitations

- Does **not support updates efficiently**
- Requires O(N log N) space
- Only works with idempotent operations for O(1) queries

For dynamic updates, consider using a **Segment Tree** instead.
