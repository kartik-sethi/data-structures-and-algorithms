# BitSet

By storing DP states as bits inside a `long[]`, **64 states are processed in a
single operation**, leading to major performance improvements.

---

## When to Use

Use this BitSet when:
- DP state is boolean (reachable / not reachable)
- Transitions involve shifting states by a fixed value
- Performance and memory efficiency matter

Typical applications:
- Subset Sum
- 0/1 Knapsack
- Partition Equal Subset Sum
- Similar DP problems with additive transitions

---

## Example Usage

```java[]
BitSet dp = new BitSet(maxSum);
dp.set(0); // base case

for (int weight : weights) {
    dp.shiftOr(weight);
}

boolean possible = dp.get(targetSum);
```

---

## Example LeetCode Problem & Solution Links

- **Problem:** Partition Equal Subset Sum  
  https://leetcode.com/problems/partition-equal-subset-sum/

- **LeetCode BitSet Solution by kartik__sethi:**  
  https://leetcode.com/problems/partition-equal-subset-sum/solutions/7443900/bitset-solution-by-kartik__sethi-wlct/


# Complexity
- Time complexity:  
  `O(N × SUM / 64)`

- Space complexity:  
  `O(SUM / 64)`

---