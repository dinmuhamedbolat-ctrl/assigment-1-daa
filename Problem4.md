### Problem 4 - Closest Pair (20 points)

+ Given an array of point coordinates $P$, find the minimum distance between two points in the array, return the distance.
+ **5pts.** Implement a brute-force solution as `minDistBrute()` method in [src/Problem4.java](src/Problem4.java).
+ **5pts.** Implement a divide-and-conquer efficient solution as `minDistSmart()` method in [src/Problem4.java](src/Problem4.java).
+ **5pts.** Describe in written form your solution ideas in the **Report** section below.
+ **5pts.** Defend your solutions to your instructor in live during practice sessions.
+ Your code will be tested against various inputs using `JUnit6` testing framework.
+ **Don't change API** of the solutions in [src/Problem4.java](src/Problem4.java).

#### Example
+ Input: `P = {{0,0},{3,4},{-5,-3}}`
+ Output: `5.0`

#### Constraints
+ $1 \leq P.length \leq 10^5$
+ $P[i][j]$ is of type `double`

#### References
1. Chapter 5.6, The Algorithm Design Manual, 3rd Edition, 2020 - Steven H. Skiena
2. Chapter 3.4, Algorithms Illuminated Part1, 2017 - Tim Roughgarden

### Report - Closest Pair
#### Solution ideas
`minDistBrute` checks every pair of points and keeps the smallest Euclidean distance. `minDistSmart` sorts points by both coordinates, divides the points by their x-order, and recursively finds the closest pair in each half. It then checks only points in the vertical strip around the dividing line, in y-order. Geometric packing limits how many following strip points need to be compared for each point, so the combine step is linear.

#### Running time
For $n$ points, brute force takes $\Theta(n^2)$ time and $\Theta(1)$ auxiliary space. The smart method sorts in $\Theta(n\log n)$ time and uses the recurrence $T(n)=2T(n/2)+\Theta(n)$, giving $\Theta(n\log n)$ total time. Its auxiliary storage is $O(n\log n)$ in this implementation because each recursion level creates y-sorted partitions. For fewer than two points, both methods return positive infinity because no pair exists.

#### Empirical comparison
Run `src/ProblemBenchmark.java`. It compares both methods on the same deterministic set of 1,500 random points, verifies that their distances agree, and reports warmed-up average timings using `System.nanoTime()`. The timing is machine-dependent; the asymptotic gap becomes clearer on larger sets.

### Defense Interview
+ Be ready to justify running times, for example using **Master method** for divide-and-conquer algorithms.
+ Be ready to answer questions on algorithms and your code.
+ Be ready to run your code and test it on various inputs.

Good Luck!