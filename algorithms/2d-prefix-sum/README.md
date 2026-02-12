# 2D Prefix Sum (Inclusion-Exclusion Principle)

A technique to perform **Range Sum Queries on a 2D Matrix** in **O(1)** constant time, after an **O(N × M)** preprocessing step.

This relies heavily on the **Inclusion-Exclusion Principle** to avoid double counting overlapping regions.

---

## 🧠 How It Works (Visual Intuition)

### 1. The Build Step (Pre-computation)
We create a 2D array `pref[i][j]` where each cell contains the sum of the rectangle starting at `(0,0)` and ending at `(i,j)`.

**Formula:**

```
pref[i][j] = matrix[i][j] + top_neighbor + left_neighbor - top_left_diagonal
```

```
P[i][j] = A[i][j] + P[i-1][j] + P[i][j-1] - P[i-1][j-1]
```

**Reason:** Adding the Top and Left neighbors adds the Top-Left diagonal area twice. We subtract it once to correct this.

---

### 2. The Query Step
To find the sum of a sub-matrix from `(r1, c1)` to `(r2, c2)`:

**Formula:**

```
Sum = P[r2][c2] - P[r1-1][c2] - P[r2][c1-1] + P[r1-1][c1-1]
```

**Reason:** We take the total area `(0,0) to (r2,c2)` and subtract the unused Top strip and Left strip. However, the intersection of those strips (Top-Left) was subtracted twice, so we add it back.

---

## 🚀 Complexity

| Operation | Time Complexity |
|---|---|
| **Preprocessing** | O(N × M) |
| **Query** | O(1) |
| **Space** | O(N × M) |

---

## 💻 Example Usage (Java)

```java
int size = 3;
PrefixSum ps = new PrefixSum(size);

// Fill matrix with values
ps.update(0, 0, 1); ps.update(0, 1, 2);
ps.update(1, 0, 3); ps.update(1, 1, 4);

// Build Prefix Sum Matrix
ps.form(); 

// Query sum of rectangle from (0,0) to (1,1) -> 1+2+3+4 = 10
long sum = ps.query(0, 1, 0, 1); 
System.out.println(sum);
```

---

## 📚 Example Problems & Solutions

Here are standard problems to practice and master the **2D Prefix Sum / Inclusion-Exclusion** pattern.

---

### ⭐ **Range Sum Query 2D — Immutable (LeetCode 304)**
🔗 https://leetcode.com/problems/range-sum-query-2d-immutable/

**Context:**  
The classic application of 2D prefix sums. You are given a matrix and must answer multiple range sum queries efficiently.

**Why it's important:**
- Direct use of prefix matrix construction
- O(1) query after preprocessing
- Foundation problem for this technique

---

### 🌲 **Forest Queries (CSES)**
🔗 https://cses.fi/problemset/task/1652

**Context:**  
You are given a grid representing a forest and must determine how many trees exist inside a rectangular area.

**Why it's important:**
- Competitive programming standard problem
- Tests inclusion-exclusion understanding
- Works with character grids (`*` vs `.`)

---

### 🧮 **Matrix Block Sum (LeetCode 1314)**
🔗 https://leetcode.com/problems/matrix-block-sum/

**Context:**  
Construct a new matrix where each cell contains the sum of all elements inside a `k`-radius square around it.

**Why it's important:**
- Uses prefix sums to compute neighborhood sums
- Demonstrates real-world transformation usage
- Shows boundary handling

---

### 📈 **Maximum Sum Rectangular Submatrix (Kadane's 2D Variant)**
🔗 https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/

**Context:**  
Find the maximum sum sub-rectangle in a matrix.

**Why it's important:**
- Combines prefix sums + Kadane’s algorithm
- Used to optimize brute force from **O(N⁴) → O(N³)**
- Shows advanced usage of 2D prefix sums

---

## 💡 When to Think About 2D Prefix Sum

Use this technique when problems involve:

- Multiple rectangle sum queries
- Static matrix (few or no updates)
- Subgrid counting problems
- Area-based computations
- Optimization of brute-force rectangle sums  
