# Problem 2 Report: Median of Two Sorted Arrays

## Objective

Find the median of two sorted integer arrays. For an even number of combined elements, the median is the average of the two middle values.

## Algorithms

`getMedianBrute` merges the arrays conceptually by repeatedly taking the smaller next value. It stops after reaching the middle position and retains only the last one or two values needed for the answer; it does not allocate a merged array.

`getMedianSmart` binary-searches a partition of the shorter array. The corresponding partition in the other array is chosen so that the left side contains half of the combined elements. A valid partition has every left-side value less than or equal to every right-side value. Boundary sentinels allow the same comparisons to work when a partition is at either end of an array.

## Correctness

The brute-force merge visits the combined values in sorted order, so the value at the middle position, or the average of the two middle values, is the median. For the smart method, the partition conditions ensure the left side contains the smaller half and the right side contains the larger half. Thus, the largest left-side value is the median for an odd total length; for an even total length, averaging it with the smallest right-side value gives the median.

The implementation searches the shorter array to keep the partition within the other array's bounds. It also supports one empty input, but rejects two empty arrays because their median is undefined.

## Complexity

Let the array lengths be $n$ and $m$:

| Method | Time | Extra space |
| --- | --- | --- |
| Partial merge | $\Theta(n+m)$ | $\Theta(1)$ |
| Partition search | $\Theta(\log(\min(n,m)+1))$ | $\Theta(1)$ |

## Empirical Evaluation

Run `src/ProblemBenchmark.java` to compare the methods on two sorted arrays of 1,000 elements each. The harness warms up each method and reports average milliseconds per call over seven measured runs. The partition method performs fewer steps as input sizes grow, while small-input measurements can be affected by JVM and timing overhead.