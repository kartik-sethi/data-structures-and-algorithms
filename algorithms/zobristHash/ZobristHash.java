import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ZobristHash {

    // The "Universe" map: Maps every number to a random 64-bit Hash
    private final Map<Integer, Long> hashMap;
    private final Random random;

    public ZobristHash() {
        this.hashMap = new HashMap<>();
        this.random = new Random();
    }

    /**
     * Gets the unique random Hash for a number.
     * Lazy Initialization: If we see a number for the first time, generate a new random hash.
     */
    private long getElementHash(int num) {
        if (!hashMap.containsKey(num)) {
            hashMap.put(num, random.nextLong());
        }
        return hashMap.get(num);
    }

    /**
     * Computes the Total Hash of an array.
     * Uses ADDITION (+) to handle duplicates (Multisets) correctly.
     * Order of elements does not affect the result.
     */
    public long computeTotalHash(int[] array) {
        long totalHash = 0L;
        for (int num : array) {
            totalHash += getElementHash(num);
        }
        return totalHash;
    }
}