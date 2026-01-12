import java.util.Arrays;

/**
 * A Data-Oriented Trie (Prefix Tree) implementation.
 * * OPTIMIZATION STRATEGY:
 * Unlike standard Tries that use a `Node` class with a HashMap or Array of children,
 * this implementation uses a "Flat Array" approach (Data-Oriented Design).
 * * ARCHITECTURE:
 * - 2D Integer Array (`trie`): Rows represent nodes, Columns represent characters (0-25).
 * - The value `trie[row][col]` stores the index of the next row (node).
 * - Parallel arrays (`count`, `endCount`) store metadata for each node index.
 * * BENEFITS:
 * 1. CPU Cache Locality: Traversing the trie is just jumping indices in a contiguous array.
 * 2. Zero Object Overhead: Removes the 12-16 byte header overhead per node found in OOP Tries.
 * 3. GC Stability: Eliminates millions of tiny Node objects, reducing Garbage Collection pressure.
 */
public class Trie {

    // The "Transition Matrix".
    // trie[currentNodeIndex][charIndex] = nextNodeIndex
    int[][] trie = new int[1][26];

    // Parallel arrays for node metadata
    // count[i] = Number of prefixes passing through node i
    // endCount[i] = Number of exact words ending at node i
    int[] count = new int[2];
    int[] endCount = new int[2];

    int root = 0;
    int index = 0; // Points to the next available row index

    public Trie() {
        root = newNode();
    }

    /**
     * Allocates a new row in the matrix.
     * If the array is full, it resizes (doubles) similar to an ArrayList.
     */
    int newNode() {
        if (index >= trie.length) {
            resize();
        }
        trie[index] = new int[26];
        Arrays.fill(trie[index], -1); // -1 indicates no path exists
        count[index] = endCount[index] = 0;
        index++;
        return index - 1;
    }

    private void resize() {
        // Amortized O(1) resizing
        trie = Arrays.copyOf(trie, trie.length * 2);
        count = Arrays.copyOf(count, count.length * 2);
        endCount = Arrays.copyOf(endCount, endCount.length * 2);
    }

    /**
     * Inserts a word into the Trie.
     * Performance: O(L) where L is word length.
     * Allocation: Zero object allocation during traversal.
     */
    void add(char[] s) {
        int curr = root;
        for (char c : s) {
            int in = c - 'a';

            // If path doesn't exist, allocate a new row index
            if (trie[curr][in] == -1) {
                trie[curr][in] = newNode();
            }

            // Move to next node (integer jump, no pointer chasing)
            curr = trie[curr][in];
            count[curr]++;
        }
        endCount[curr]++;
    }

    /**
     * Returns the count of exact word matches.
     */
    int count(char[] s) {
        int curr = root;
        for (char c : s) {
            int in = c - 'a';
            if (trie[curr][in] == -1) {
                return 0;
            }
            curr = trie[curr][in];
        }
        return endCount[curr];
    }

    /**
     * Returns the count of words starting with the given prefix.
     */
    int startCount(char[] s) {
        int curr = root;
        for (char c : s) {
            int in = c - 'a';
            if (trie[curr][in] == -1) {
                return 0;
            }
            curr = trie[curr][in];
        }
        return count[curr];
    }
}