/**
 * A lightweight and efficient BitSet implementation backed by a {@code long[]}.
 *
 * <p>
 * Each {@code long} stores 64 bits, allowing fast bitwise operations and
 * compact memory usage. This implementation is particularly useful for
 * optimizing dynamic programming problems such as:
 * </p>
 *
 * <ul>
 *   <li>Subset Sum</li>
 *   <li>0/1 Knapsack</li>
 *   <li>Partition problems</li>
 * </ul>
 *
 * <p>
 * The key optimization supported is the shift-or operation:
 * </p>
 *
 * <pre>
 *     dp = dp | (dp << shift)
 * </pre>
 *
 * <p>
 * This reduces DP transitions from {@code O(n)} to {@code O(n / 64)} by
 * processing 64 states in a single operation.
 * </p>
 */
public class BitSet {

    /** Maximum number of bits represented */
    private final int size;

    /** Internal storage where each long stores 64 bits */
    private final long[] bits;

    /**
     * Constructs a BitSet capable of representing bits in the range [0, size].
     *
     * @param size maximum bit index (inclusive)
     */
    public BitSet(int size) {
        this.size = size;
        this.bits = new long[size / 64 + 2];
    }

    /**
     * Sets the bit at the specified index.
     *
     * @param index bit position to set
     */
    public void set(int index) {
        bits[index >> 6] |= 1L << (index & 63);
    }

    /**
     * Checks whether the bit at the specified index is set.
     *
     * @param index bit position to check
     * @return true if the bit is set, false otherwise
     */
    public boolean get(int index) {
        return (bits[index >> 6] & (1L << (index & 63))) != 0;
    }

    /**
     * Performs an in-place shift-or operation.
     *
     * <p>
     * After this operation:
     * </p>
     *
     * <pre>
     *     this = this | (this << shift)
     * </pre>
     *
     * <p>
     * This is commonly used in optimized knapsack-style DP transitions where
     * shifting represents adding a new weight.
     * </p>
     *
     * @param shift number of positions to shift
     */
    public void shiftOr(int shift) {
        int whole = shift >> 6;   // shift / 64
        int rem = shift & 63;     // shift % 64

        // Case 1: shift is a multiple of 64
        if (rem == 0) {
            for (int i = bits.length - 1; i >= whole; i--) {
                bits[i] |= bits[i - whole];
            }
        }
        // Case 2: general shift
        else {
            for (int i = bits.length - 1; i >= whole + 1; i--) {
                bits[i] |= (bits[i - whole] << rem)
                        | (bits[i - whole - 1] >>> (64 - rem));
            }
            bits[whole] |= bits[0] << rem;
        }
    }

    /**
     * Returns a binary string representation of this BitSet.
     * Intended mainly for debugging and visualization.
     *
     * @return bitset as a string of '0' and '1'
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(get(i) ? '1' : '0');
        }
        return sb.toString();
    }
}