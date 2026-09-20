package algorithms;

public class InsertionSort {

    public static void sort(int[] a, int low, int high, Metrics metrics) {
        for (int i =low + 1; i <=high; i++) {
            int key = a[i];
            int j = i- 1;

            while (j >= low) {
                metrics.incrementComparisons();
                if (a[j] <= key) {
                    break;
                }
                a[j+ 1] = a[j];
                j--;
            }
            a[j +1] = key;
        }
    }

    public static void sort(int[] a, Metrics metrics) {
        sort(a, 0, a.length- 1, metrics);
    }
}
