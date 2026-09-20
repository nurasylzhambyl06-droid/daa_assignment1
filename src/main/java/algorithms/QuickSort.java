package algorithms;

import java.util.Random;

public class QuickSort {

    private static final Random RANDOM = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length<= 1) {
            return;
        }
        sort(a, 0, a.length - 1, metrics);
    }
    private static void sort(int[] a, int low, int high, Metrics metrics) {
        metrics.enterRecursion();
        try {
            while (low < high) {
                int[] bounds = Partition.partition3Way(a, low, high, metrics, RANDOM);
                int lt = bounds[0];
                int gt = bounds[1];

                int leftSize = lt-low;
                int rightSize = high - gt;

                if (leftSize < rightSize) {
                    sort(a, low, lt -1, metrics);
                    low = gt + 1;
                } else {
                    sort(a, gt+ 1, high, metrics);
                    high = lt - 1;
                }
            }
        } finally {
            metrics.exitRecursion();
        }
    }
}
