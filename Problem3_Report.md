# Problem 3 Report: Largest Subrange

## Objective

Find the maximum sum over all non-empty contiguous subarrays of an integer array.

## Algorithms

`maxSumBrute` considers every starting index, extends the subarray one element at a time, and keeps a running sum. It records the largest sum seen over all such subarrays.

`maxSumSmart` divides the array into two halves. The maximum-sum subarray is either entirely in the left half, entirely in the right half, or crosses the midpoint. For a crossing subarray, the algorithm adds the best suffix sum of the left half to the best prefix sum of the right half, then returns the largest of these three candidates.

## Correctness

Every non-empty contiguous subarray belongs to exactly one of the three cases: it lies wholly on the left, lies wholly on the right, or crosses the midpoint. The recursive calls find the best answer in the first two cases. A crossing subarray must consist of a suffix ending at the midpoint and a prefix beginning immediately after it; independently maximizing those sums gives the best crossing candidate. Taking the maximum of all three therefore returns the global optimum. Initializing the suffix and prefix best values to negative infinity ensures that all-negative arrays still produce a non-empty answer.

## Complexity

For an array of length $n$:

| Method | Time | Extra space |
| --- | --- | --- |
| Brute force | $\Theta(n^2)$ | $\Theta(1)$ |
| Divide and conquer | $\Theta(n\log n)$ | $\Theta(\log n)$ recursion stack |

The divide-and-conquer recurrence is $T(n)=2T(n/2)+\Theta(n)$. By the Master Theorem, its running time is $\Theta(n\log n)$.

## Empirical Evaluation

Run `src/ProblemBenchmark.java` to compare both methods on the same deterministic array of 3,000 integers. The harness first checks that the answers agree, then warms up each method and reports average milliseconds per call over seven measured runs. The brute-force input size is moderate because its quadratic running time grows quickly. Results depend on the JVM and machine.