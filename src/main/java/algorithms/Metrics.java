package algorithms;
public class Metrics {

    private long comparisons = 0;
    private int currentDepth =0;
    private int maxDepth = 0;
    private long startTimeNanos;
    private long elapsedNanos;

    public void startTimer() {
        startTimeNanos = System.nanoTime();
    }

    public void stopTimer() {
        elapsedNanos = System.nanoTime() -startTimeNanos;
    }

    public long getElapsedNanos() {
        return elapsedNanos;
    }

    public double getElapsedMillis() {
        return elapsedNanos/ 1_000_000.0;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth =currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }
    public int getMaxDepth() {
        return maxDepth;
    }
    public void reset() {
        comparisons = 0;
        currentDepth =0;
        maxDepth =0;
        elapsedNanos = 0;
    }

    @Override
    public String toString() {
        return String.format("time=%.3fms, comparisons=%d, maxDepth=%d",
                getElapsedMillis(), comparisons, maxDepth);
    }
}
