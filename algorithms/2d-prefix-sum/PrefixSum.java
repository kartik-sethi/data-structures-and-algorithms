/**
 * 2D Prefix Sum Implementation.
 * <p>
 * This class provides O(1) query time for calculating the sum of any rectangular sub-matrix.
 * It uses the Inclusion-Exclusion principle for both building the prefix array and querying it.
 * </p>
 * * Time Complexity: O(N^2) build, O(1) query.
 * Space Complexity: O(N^2)
 */
public class PrefixSum {
    long[][] a;
    int n;

    public PrefixSum(int n) {
        this.n = n;
        // Using n+1 size often simplifies boundary checks (1-based indexing),
        // but keeping 0-based to match standard array inputs.
        a = new long[n][n];
    }

    /**
     * Updates the value at a specific cell.
     * NOTE: Must call form() after all updates are done and before querying.
     */
    void update(int i, int j, long val) {
        a[i][j] = val;
    }

    /**
     * Builds the 2D Prefix Sum array in-place.
     * Uses Inclusion-Exclusion: Current + Top + Left - TopLeft
     */
    void form() {
        // 1. Initialize the first row and first column separately to avoid boundary checks in the nested loop
        for (int i = 1; i < n; i++) {
            a[i][0] += a[i - 1][0]; // Accumulate first column
            a[0][i] += a[0][i - 1]; // Accumulate first row
        }

        // 2. Fill the rest of the matrix
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                // a[i][j] currently holds the raw value.
                // We add the prefix sum from top, left, and subtract the double-counted diagonal.
                a[i][j] += a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1];
            }
        }
    }

    /**
     * Queries the sum of the sub-matrix defined by top-left (x1, y1) and bottom-right (x2, y2).
     * Formula: BottomRight - TopStrip - LeftStrip + TopLeftIntersection
     */
    long query(int x1, int x2, int y1, int y2) {
        // Validating bounds could be added here
        long res = a[x2][y2];

        if (x1 > 0) res -= a[x1 - 1][y2]; // Subtract Top Strip
        if (y1 > 0) res -= a[x2][y1 - 1]; // Subtract Left Strip
        if (x1 > 0 && y1 > 0) res += a[x1 - 1][y1 - 1]; // Add back Intersection

        return res;
    }
}