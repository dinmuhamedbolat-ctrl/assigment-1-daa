# Problem 1 Report: Number of Occurrences

## Objective

Count how many times a key occurs in a non-descending sorted integer array.

## Algorithms

`countFreqBrute` scans the array once and increments a counter for every element equal to the key. It works regardless of ordering, although this problem guarantees a sorted input.

`countFreqSmart` performs two binary searches. The first finds the lower bound, the first index whose value is greater than or equal to the key. The second finds the upper bound, the first index whose value is greater than the key. Their difference is the number of occurrences. If the key is absent, both searches return the same insertion position.

## Correctness

In a sorted array, all occurrences of a key form one contiguous interval. The lower-bound search finds the start of that interval, and the upper-bound search finds the position immediately after it. Subtracting the two indices therefore gives the interval's length, including zero when the interval is empty. The brute-force method counts each matching element exactly once.

## Complexity

For an array of length $n$:

| Method | Time | Extra space |
| --- | --- | --- |
| Brute force | $\Theta(n)$ | $\Theta(1)$ |
| Two binary searches | $\Theta(\log(n+1))$ | $\Theta(1)$ |

Each binary search halves its remaining search interval on every iteration.

## Empirical Evaluation

Run `src/ProblemBenchmark.java` to compare the methods on a sorted array of 100,000 integers. The harness warms up each method and reports average milliseconds per call based on seven measured runs. Timings depend on the JVM and machine; the expected difference is linear scanning versus logarithmic searching. No fixed timing is a universal performance guarantee.