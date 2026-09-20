package algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MergeSortTest {

    @Test
    void sortsEmptyArray() {
        int[] a = {};
        MergeSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    void sortsSingleElement() {
        int[] a = {42};
        MergeSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    void sortsAllEqualElements() {
        int[] a = {7, 7, 7, 7, 7};
        int[] expected = a.clone();
        MergeSort.sort(a, new Metrics());
        assertArrayEquals(expected, a);
    }

    @Test
    void sortsAlreadySortedArray() {
        int[] a = {1, 2, 3, 4, 5};
        int[] expected = a.clone();
        MergeSort.sort(a, new Metrics());
        assertArrayEquals(expected, a);
    }

    @Test
    void sortsReverseSortedArray() {
        int[] a = {5, 4, 3, 2, 1};
        MergeSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, a);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 15, 16, 50, 500, 5000})
    void matchesJavaSortOnRandomArrays(int size) {
        Random rnd =new Random(42);
        for (int trial = 0; trial < 100; trial++) {
            int[] a = rnd.ints(size, -10000, 10000).toArray();
            int[] expected = a.clone();
            Arrays.sort(expected);

            MergeSort.sort(a, new Metrics());

            assertArrayEquals(expected, a, "Mismatch for size=" +size + ", trial=" + trial);
        }
    }

    @Test
    void metricsRecordComparisonsAndDepth() {
        int[] a = {5, 3, 8, 1, 9, 2, 7, 4, 6, 0};
        Metrics metrics = new Metrics();
        MergeSort.sort(a, metrics);

        assertEquals(0, compareToSorted(a));
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
    }

    private int compareToSorted(int[] a) {
        int[] expected = a.clone();
        Arrays.sort(expected);
        return Arrays.equals(expected, a) ? 0 : 1;
    }
}