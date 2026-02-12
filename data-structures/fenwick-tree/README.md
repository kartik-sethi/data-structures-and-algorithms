# Fenwick Tree (Binary Indexed Tree)

The **Fenwick Tree** (or Binary Indexed Tree - BIT) is a data structure that provides efficient methods for calculation and manipulation of the **prefix sums** of a table of values.

It is famous for being much shorter and easier to implement than a Segment Tree, while maintaining **O(log N)** performance for both updates and queries.

---

## How It Works: `index & -index`

The core logic relies on **Low Bit extraction**.

In Two's Complement representation:

```
x & -x
```

isolates the **last set bit** (rightmost `1`) of `x`.

This allows the Fenwick Tree to jump between responsible ranges efficiently.

### How it's used:

- **Update Step:** Add the Least Significant Bit (LSB) to the index to move to the covering range.
- **Query Step:** Subtract the LSB to move to the previous range.

This gives logarithmic time complexity for both operations.

---

## Complexity

| Operation | Time Complexity | Space Complexity |
|---|---|---|
| **Build** | O(N log N) or O(N) | O(N) |
| **Update** | O(log N) | - |
| **Prefix Query** | O(log N) | - |

---

## Code Usage (Java)

This implementation supports **Point Updates** and **Range Queries**.

### Standard Use: Point Update / Range Query
Used for problems like:

> "Change value at index `i`, get sum from `L` to `R`."

```java
Bit bit = new Bit();
bit.clear(10);

bit.update(2, 5);  // Add 5 to index 2
bit.update(3, 2);  // Add 2 to index 3

// Get sum of range [1, 3]
System.out.println(bit.query_range(1, 3));
```

---

## Core Operations

### 1. Update - O(log N)

To update a value at index `i`:

```
index += index & -index
```

We update all responsible parent ranges.

---

### 2. Prefix Query - O(log N)

To compute prefix sum up to index `i`:

```
index -= index & -index
```

We accumulate values while moving to smaller ranges.

---

## Why Use Fenwick Tree Over Segment Tree?

- Extremely short implementation
- Faster constant factor in practice
- Uses only O(N) memory (Segment Tree uses ~4N)
- Cleaner and easier for interviews
- Great for frequency counting and prefix problems

---

## Practice Problems

Try these standard problems to master Fenwick Tree:

---

### Range Sum Query - Mutable (LeetCode 307)
https://leetcode.com/problems/range-sum-query-mutable/

**Concepts Used:**
- Point updates
- Range sum queries
- Basic BIT implementation

---

### Count of Smaller Numbers After Self (LeetCode 315)
https://leetcode.com/problems/count-of-smaller-numbers-after-self/

**Concepts Used:**
- Coordinate compression
- Frequency counting
- Advanced BIT usage

---

### Reverse Pairs (LeetCode 493)
https://leetcode.com/problems/reverse-pairs/

**Concepts Used:**
- Order statistics
- Prefix counting
- BIT + sorting

---

## When to Use Fenwick Tree

Use Fenwick Tree when:

- You need fast prefix sums
- Array updates are frequent
- Memory efficiency matters
- You want simpler alternative to Segment Tree
- Problems involve frequency counting or inversion counting

---

## Limitations

- Less flexible than Segment Tree
- Harder to extend for complex range operations
- Works best for additive operations (sum, frequency)

---

## Advanced Extensions

Fenwick Tree can also support:

- Range Updates + Point Queries (using difference array idea)
- Range Updates + Range Queries (using two BITs)
- Inversion counting
- Order statistics