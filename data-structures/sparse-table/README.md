# Sparse Table (Range Minimum Query)

A high-performance data structure that answers range queries on static arrays in **O(1)** (constant time) after **O(N log N)** preprocessing.

This implementation uses the **Idempotency** property of operations like `min`, `max`, `gcd`, `and`, `or` to allow for overlapping query intervals.

---

## 🧠 How It Works (Visual Intuition)

### 1. The Core Idea: Powers of 2
Instead of calculating the answer for *every* possible subarray, we only pre-calculate answers for subarrays with lengths that are **Powers of 2** ($1, 2, 4, 8, \dots$).

We store these in a 2D table `sparse[k][i]`:
- `k`: The power (length $= 2^k$)
- `i`: The starting index

**Example:** `sparse[2][3]` stores the minimum of the range starting at index `3` with length $2^2 = 4$.

### 2. The "Magic" of O(1) Queries ⚡
To find the answer for *any* arbitrary range $[L, R]$, we don't need to cut it into perfect unique pieces (like a Segment Tree).

We just need **two overlapping blocks** of length $2^k$ that cover the entire range:
1.  **Block 1:** Starts at $L$ (covers the left side).
2.  **Block 2:** Ends at $R$ (covers the right side).

Because operations like `min()` are **idempotent** (meaning `min(5, 5) = 5`), the overlap doesn't affect the result.

$$\text{Answer} = \min(\text{Block}_1, \text{Block}_2)$$

---

## 🚀 Complexity

| Operation | Time Complexity |
| :--- | :--- |
| **Preprocessing** | $O(N \log N)$ |
| **Query** | $O(1)$ |
| **Space** | $O(N \log N)$ |

---

## 💻 Example Usage (Java)

```java
int[] nums = {1, 3, -1, 5, 2, 6};
SparseTable st = new SparseTable(nums);

// Query range [2, 5] (indices are 0-based)
// Subarray: {-1, 5, 2, 6} -> Min is -1
int minVal = st.query(2, 5); 

System.out.println("Min in range: " + minVal); // Output: -1****