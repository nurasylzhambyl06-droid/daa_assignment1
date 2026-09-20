package algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuickSelectTest {

    @Test
    void throwsOnEmptyArray() {
        assertThrows(IllegalArgumentException.class,
            () -> QuickSelect.select(new int[]{}, 0, new Metrics()));
    }

    @Test
    void throwsOnNullArray() {
        assertThrows(IllegalArgumentException.class,
            () -> QuickSelect.select(null, 0, new Metrics()));
    }

    @Test
    void throwsOnNegativeK() {
        assertThrows(IllegalArgumentException.class,
            () -> QuickSelect.select(new int[]{1, 2, 3}, -1, new Metrics()));
    }

    @Test
    void throwsOnKTooLarge() {
        assertThrows(IllegalArgumentException.class,
            () -> QuickSelect.select(new int[]{1, 2, 3}, 3, new Metrics()));
    }

    @Test
    void singleElementArray() {
        assertEquals(9, QuickSelect.select(new int[]{9}, 0, new Metrics()));
    }

    @Test
    void findsMinAndMax() {
        int[] a = {5, 3, 8, 1, 9, 2, 7, 4, 6, 0};
        assertEquals(0, QuickSelect.select(a.clone(), 0, new Metrics()));
        assertEquals(9, QuickSelect.select(a.clone(), 9, new Metrics()));
    }

    @Test
    void matchesSortedArrayOnRandomInputs() {
        Random rnd = new Random(7);
        for (int trial = 0; trial <100; trial++) {
            int size = 1 + rnd.nextInt(500);
            int[] a = rnd.ints(size, -1000, 1000).toArray();
            int[] sorted = a.clone();
            Arrays.sort(sorted);

            int k = rnd.nextInt(size);
            int result = QuickSelect.select(a.clone(), k, new Metrics());

            assertEquals(sorted[k], result, "Mismatch at trial=" + trial + ", size=" + size + ", k=" + k);
        }
    }

    @Test
    void handlesArrayWithDuplicates() {
        int[] a = {4, 4, 4, 2, 2, 8, 8, 1};
        int[] sorted =a.clone();
        Arrays.sort(sorted);

        for (int k =0; k< a.length; k++) {
            assertEquals(sorted[k], QuickSelect.select(a.clone(), k, new Metrics()));
        }
    }
}
