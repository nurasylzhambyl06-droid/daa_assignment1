package algorithms;

import java.util.Random;

public class QuickSelect {

    private static final Random RANDOM = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException(
                "Invalid input: array must be non-empty and 0 <= k < a.length (got k=" + k
                + ", length=" + (a == null ? "null" : a.length) + ")"
            );
        }
        return select(a, 0, a.length - 1, k, metrics);
    }

    private static int select(int[] a, int low, int high, int k, Metrics metrics) {
        metrics.enterRecursion();
        try {
            if (low == high) {
                return a[low];
            }

            int[] bounds = Partition.partition3Way(a, low, high, metrics, RANDOM);
            int lt =bounds[0];
            int gt =bounds[1];

            if (k < lt) {
                return select(a, low, lt- 1, k, metrics);
            } else if (k > gt) {
                return select(a, gt +1, high, k, metrics);
            } else {
                return a[k];
            }
        } finally {
            metrics.exitRecursion();
        }
    }
}
