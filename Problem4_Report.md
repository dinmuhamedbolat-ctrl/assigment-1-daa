# Problem 4 Report: Closest Pair

## Objective

Find the minimum Euclidean distance between any two points in a two-dimensional array of coordinates.

## Algorithms

`minDistBrute` checks every unordered pair of points and keeps the smallest distance, computed with `Math.hypot`.

`minDistSmart` sorts points by x-coordinate and by y-coordinate, retaining each point's rank in the x-sorted order. It recursively finds the closest pair in each half. It then builds a y-ordered strip of points close enough to the dividing line to improve the current best distance and compares nearby points in that strip. Geometric packing bounds the number of following strip points that need to be checked for each point, keeping the combine step linear.

## Correctness

The closest pair is either contained entirely in the left half, contained entirely in the right half, or has one point in each half. The recursive calls find the closest pairs in the first two cases. If a cross-boundary pair is shorter than the best distance found so far, both points must be within that distance of the dividing line, so both are included in the strip. The strip's y-order and the geometric packing bound make it sufficient to check nearby successors. The smallest distance found among these cases is therefore the global minimum.

For fewer than two points, no pair exists, so both methods return positive infinity.

## Complexity

For $n$ points:

| Method | Time | Auxiliary space |
| --- | --- | --- |
| Brute force | $\Theta(n^2)$ | $\Theta(1)$ |
| Divide and conquer | $\Theta(n\log n)$ | $O(n\log n)$ in this implementation |

The smart method initially sorts the points in $\Theta(n\log n)$ time. Its recursive work follows $T(n)=2T(n/2)+\Theta(n)$, giving $\Theta(n\log n)$ total time. The auxiliary-space bound accounts for the y-sorted arrays allocated at each recursion level.

## Empirical Evaluation

Run `src/ProblemBenchmark.java` to compare both methods on the same deterministic set of 1,500 generated points. The harness checks that the returned distances agree within a small tolerance, warms up each method, and reports average milliseconds per call over seven measured runs. Timings depend on the JVM and machine; the asymptotic gap becomes more apparent as the input grows.