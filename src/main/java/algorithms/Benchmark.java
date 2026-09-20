package algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final String[] INPUT_TYPES = {"random", "sorted", "duplicates"};
    private static final int REPEATS = 5;

    public static void main(String[] args) throws IOException {
        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.write("algorithm,input,n,time_ms,comparisons,max_depth\n");

            for (int n : SIZES) {
                for (String inputType : INPUT_TYPES) {
                    runCase("MergeSort", inputType, n, writer);
                    runCase("QuickSort", inputType, n, writer);
                    runCase("QuickSelect", inputType, n, writer);
                }
            }
        }
        System.out.println("Done. Results written to results.csv");
    }

    private static void runCase(String algorithm, String inputType, int n, FileWriter writer) throws IOException {
        double[] times = new double[REPEATS];
        long[] comparisons = new long[REPEATS];
        int[] depths = new int[REPEATS];

        for (int run = 0; run < REPEATS; run++) {
            int[] input = generateInput(inputType, n, run);
            Metrics metrics = new Metrics();

            metrics.startTimer();
            switch (algorithm) {
                case "MergeSort" -> MergeSort.sort(input, metrics);
                case "QuickSort" -> QuickSort.sort(input, metrics);
                case "QuickSelect" -> QuickSelect.select(input, n / 2, metrics); // median element
                default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
            }
            metrics.stopTimer();

            times[run] = metrics.getElapsedMillis();
            comparisons[run] = metrics.getComparisons();
            depths[run] = metrics.getMaxDepth();
        }

        double medianTime = median(times);
        long medianComparisons = medianLong(comparisons);
        int medianDepth = medianInt(depths);

        writer.write(String.format("%s,%s,%d,%.3f,%d,%d%n",
                algorithm, inputType, n, medianTime, medianComparisons, medianDepth));

        System.out.printf("%s | %s | n=%d | median time=%.3fms, comparisons=%d, depth=%d%n",
                algorithm, inputType, n, medianTime, medianComparisons, medianDepth);
    }

    private static int[] generateInput(String type, int n, int seedOffset) {
        Random rnd = new Random(1000 + seedOffset);
        int[] a = new int[n];

        switch (type) {
            case "random" -> {
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt();
            }
            case "sorted" -> {
                for (int i = 0; i < n; i++) a[i] = i;
            }
            case "duplicates" -> {
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt(10);
            }
            default -> throw new IllegalArgumentException("Unknown input type: " + type);
        }
        return a;
    }

    private static double median(double[] values) {
        double[] sorted = values.clone();
        Arrays.sort(sorted);
        int mid = sorted.length / 2;
        return sorted.length % 2 == 0
                ? (sorted[mid - 1] + sorted[mid]) / 2.0
                : sorted[mid];
    }

    private static long medianLong(long[] values) {
        long[] sorted = values.clone();
        Arrays.sort(sorted);
        return sorted[sorted.length / 2];
    }

    private static int medianInt(int[] values) {
        int[] sorted = values.clone();
        Arrays.sort(sorted);
        return sorted[sorted.length / 2];
    }
}
