package algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickSortTest {
    @Test
    void sortsEmptyArray() {
        int[] a ={};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    void sortsSingleElement() {
        int[] a = {42};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    void sortsAllEqualElements() {
        int[] a = {7, 7, 7, 7, 7};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, a);
    }

    @Test
    void sortsAlreadySortedArray() {
        int[] a = {1, 2, 3, 4, 5};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, a);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 50, 500, 5000})
    void matchesJavaSortOnRandomArrays(int size) {
        Random rnd = new Random(123);
        for (int trial =0; trial < 100; trial++) {
            int[] a = rnd.ints(size, -10000, 10000).toArray();
            int[] expected = a.clone();
            Arrays.sort(expected);

            QuickSort.sort(a, new Metrics());

            assertArrayEquals(expected, a, "Mismatch for size=" +size + ", trial=" + trial);
        }
    }

    @Test
    void recursionDepthIsBoundedOnSortedInput() {
        int n = 100_000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i]= i;
        }

        Metrics metrics = new Metrics();
        QuickSort.sort(a, metrics);

        double log2n = Math.log(n) / Math.log(2);
        int limit = (int) Math.ceil(2 * log2n);

        assertTrue(
            metrics.getMaxDepth() <= limit,
            "maxDepth=" +metrics.getMaxDepth() + " exceeded limit=" +limit
        );
    }

    @Test
    void doesNotCrashOnSortedInputOfModerateSize() {
        int n = 50_000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;

        QuickSort.sort(a, new Metrics());

        for (int i = 0; i < n; i++) {
            assertTrue(a[i] ==i);
        }
    }
}