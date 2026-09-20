package algorithms;

public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }
        int[] buffer = new int[a.length];
        sort(a, buffer, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int[] buffer, int low, int high, Metrics metrics) {
        metrics.enterRecursion();
        try {

            if (high- low + 1 <= CUTOFF) {
                InsertionSort.sort(a, low, high, metrics);
                return;
            }

            if (low >= high) {
                return;
            }

            int mid = low+ (high - low) / 2;

            sort(a, buffer, low, mid, metrics);
            sort(a, buffer, mid+ 1, high, metrics);
            merge(a, buffer, low, mid, high, metrics);
        } finally {
            metrics.exitRecursion();
        }
    }

    private static void merge(int[] a, int[] buffer, int low, int mid, int high, Metrics metrics) {

        System.arraycopy(a, low, buffer, low, high -low + 1);

        int i = low;
        int j = mid+ 1;
        int k = low;

        while (i <= mid && j <=high) {
            metrics.incrementComparisons();
            if (buffer[i] <= buffer[j]) {
                a[k++] = buffer[i++];
            } else {
                a[k++] = buffer[j++];
            }
        }

        while (i<= mid) {
            a[k++] = buffer[i++];
        }
        while (j <=high) {
            a[k++] = buffer[j++];
        }
    }
}