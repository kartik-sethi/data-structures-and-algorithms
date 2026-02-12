/**
 * Fenwick Tree (Binary Indexed Tree) Implementation.
 * <p>
 * Supports O(log N) Point Updates and O(log N) Prefix Sum Queries.
 * This implementation handles 1-based indexing internally.
 * </p>
 * * Time Complexity: O(log N) for update and query.
 * Space Complexity: O(N)
 */
public class Bit {
    long[] bit;
    int len;
    int mod = (int) 1e9 + 7; // Example Modulo

    // Default Constructor: Size ~200,000
    Bit() {
        len = (int) 2e5 + 10;
        bit = new long[len];
    }

    // Dynamic Sizing Constructor
    public Bit(int n) {
        this.len = n + 5;
        bit = new long[len];
    }

    /**
     * Resets the BIT array to 0.
     */
    void clear(int n) {
        len = n + 5;
        for (int i = 0; i <= len; i++) {
            bit[i] = 0;
        }
    }

    /**
     * Point Update: Adds 'value' to the element at 'index'.
     * Logic: Traverses UP the tree by ADDING the LSB (index & -index).
     */
    void update(int index, int value) {
        while (index <= len) {
            bit[index] += value; // + mod if needed
            // bit[index] %= mod;
            index += index & -index; // Move to parent (up)
        }
    }

    /**
     * Prefix Query: Returns the sum from 1 to 'index'.
     * Logic: Traverses DOWN the tree by SUBTRACTING the LSB (index & -index).
     */
    int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += bit[index];
            // sum %= mod;
            index -= index & -index; // Move to child (down)
        }
        return sum;
    }

    /**
     * Range Query: Returns sum of range [x, y].
     * Formula: PrefixSum(y) - PrefixSum(x - 1)
     */
    int query_range(int x, int y) {
        long res = query(y) - query(x - 1);
        // Handle potential negative result due to modulo
        return (int)((res % mod + mod) % mod);
    }

    /**
     * SPECIAL: Range Update / Point Query.
     * WARNING: Only use this if you are using the BIT as a 'Difference Array'.
     * If you use this, query(i) will return the VALUE at i, not the sum.
     */
    void range_update(int x, int y, int value) {
        update(x, value);
        update(y + 1, -value);
    }
}