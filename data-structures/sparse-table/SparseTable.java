/**
 * Sparse Table Implementation for Range Minimum Query (RMQ).
 * * <p>This data structure allows us to answer range minimum queries in O(1) time
 * after O(N log N) preprocessing. It relies on the idempotency of the min() function,
 * meaning min(a, a) = a, allowing us to use overlapping intervals.</p>
 *
 * Time Complexity:
 * - Preprocessing: O(N log N)
 * - Query: O(1)
 * * Space Complexity: O(N log N)
 */
public class SparseTable {
    // sparse[i][j] stores the minimum of the range starting at 'j' with length 2^i
    private int[][] sparse;
    private int n;

    public SparseTable(int[] arr) {
        this.n = arr.length;
        // Calculate the maximum power of 2 needed (log2(n))
        int k = (int) (Math.log(n) / Math.log(2));

        // Initialize table: [power][starting_index]
        sparse = new int[k + 1][n];

        // BASE CASE: Intervals of length 1 (2^0) are just the elements themselves
        for (int i = 0; i < n; i++) {
            sparse[0][i] = arr[i];
        }

        // BUILD STEP: Compute minimums for intervals of length 2, 4, 8, etc.
        // Rule: min(range of len 2^i) = min(first half of len 2^(i-1), second half of len 2^(i-1))
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) {
                // 1 << (i-1) is 2^(i-1)
                int leftInterval = sparse[i - 1][j];
                int rightInterval = sparse[i - 1][j + (1 << (i - 1))];

                sparse[i][j] = Math.min(leftInterval, rightInterval);
            }
        }
    }

    /**
     * Finds the minimum value in the range [L, R] in O(1) time.
     * * Logic: We verify two overlapping ranges of length 2^k that cover [L, R].
     * 1. Range covering the start: [L ... L + 2^k - 1]
     * 2. Range covering the end:   [R - 2^k + 1 ... R]
     */
    public int query(int l, int r) {
        // Calculate the largest power of 2 (k) that fits in the range length
        int k = (int) (Math.log(r - l + 1) / Math.log(2));

        // Lookup the two precomputed intervals
        int leftMin = sparse[k][l];
        int rightMin = sparse[k][r - (1 << k) + 1];

        // Return the minimum of both (overlap doesn't matter for min/max/gcd)
        return Math.min(leftMin, rightMin);
    }
}