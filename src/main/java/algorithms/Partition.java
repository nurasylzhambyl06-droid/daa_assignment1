package algorithms;

import java.util.Random;
public class Partition {

    static int[] partition3Way(int[] a, int low, int high, Metrics metrics, Random rnd) {

        int pivotIndex = low+ rnd.nextInt(high - low + 1);
        int pivotValue = a[pivotIndex];

        int lt = low;
        int i =low;
        int gt = high;

        while (i<= gt) {
            metrics.incrementComparisons();
            if (a[i] <pivotValue) {
                swap(a, lt, i);
                lt++;
                i++;
            } else if (a[i] > pivotValue) {
                swap(a, i, gt);
                gt--;

            } else {
                i++;
            }
        }

        return new int[]{lt, gt};
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] =tmp;
    }
}
