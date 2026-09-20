# DAA Assignment 1 — Fast Sorting & Selection Engine

MergeSort, QuickSort and QuickSelect implementations in Java, with performance
metrics (comparisons, recursion depth, time) and a benchmark that exports
results to CSV.

## Requirements

- Java 17+
- Maven 3.6+

## Build

```bash
mvn compile
```

## Run tests

```bash
mvn test
```

## Run the benchmark

```bash
mvn compile exec:java -Dexec.mainClass="algorithms.Benchmark"
```

This runs MergeSort, QuickSort and QuickSelect across n = 1 000, 10 000,
100 000, 1 000 000, on `random`, `sorted` and `duplicates` inputs, 5 repeats
per case, and writes the median of each case to `results.csv` in the project
root.

**Note:** n = 1 000 000 can take a while, especially for QuickSort/MergeSort
combined across 3 input types — expect the full run to take a few minutes.

## Project structure

```
src/main/java/algorithms/
├── Metrics.java       — comparison/depth/time counters, passed into every algorithm
├── InsertionSort.java — used as MergeSort's cutoff for small subarrays
├── MergeSort.java      — reusable buffer, cutoff to InsertionSort, linear merge
├── Partition.java      — shared 3-way partition used by QuickSort and QuickSelect
├── QuickSort.java       — random pivot, smaller-side-first recursion (bounded depth)
├── QuickSelect.java     — reuses Partition, recurses into one side only
└── Benchmark.java       — runs all cases, writes results.csv

src/test/java/algorithms/
├── MergeSortTest.java
├── QuickSortTest.java
└── QuickSelectTest.java
```

## Git workflow

Branches used during development:
- `feature/mergesort`
- `feature/quicksort`
- `feature/select`
- `feature/metrics`

merged into `main`, tagged `v1.0` for the final submission.

Example commands to set this up from scratch:

```bash
git init
git checkout -b feature/mergesort
# ... add Metrics.java, InsertionSort.java, MergeSort.java, MergeSortTest.java ...
git add .
git commit -m "feat(mergesort): add reusable buffer, cutoff and linear merge"

git checkout main 2>/dev/null || git checkout -b main
git merge feature/mergesort

git checkout -b feature/quicksort
# ... add Partition.java, QuickSort.java, QuickSortTest.java ...
git add .
git commit -m "feat(quicksort): add random pivot, 3-way partition, bounded recursion depth"
git commit -m "test(quicksort): check recursion depth on sorted input"

git checkout main
git merge feature/quicksort

git checkout -b feature/select
# ... add QuickSelect.java, QuickSelectTest.java ...
git add .
git commit -m "feat(select): implement QuickSelect reusing shared partition"

git checkout main
git merge feature/select

git checkout -b feature/metrics
# ... add Benchmark.java ...
git add .
git commit -m "feat(metrics): add benchmark runner exporting results.csv"

git checkout main
git merge feature/metrics

git add REPORT.md README.md
git commit -m "docs(report): add asymptotic analysis, recurrences and discussion"

git tag v1.0
git remote add origin <your-repo-url>
git push -u origin main --tags
```

## Submission checklist

- [ ] Source code (Maven project) with all algorithms + Metrics + JUnit 5 tests
- [ ] `results.csv` from a full benchmark run
- [ ] Plots (PNG): Time vs n, Depth vs n, Ratio vs n — see REPORT.md
- [ ] `REPORT.md` filled in completely
- [ ] Pushed to GitHub, `main` branch, tagged `v1.0`
- [ ] ZIP named `DAA_Assignment1_name_surname_group.zip` uploaded to Moodle
  with a link to the GitHub repository
