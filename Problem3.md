### Problem 3 - Largest Subrange (20 points)

+ Given an integer array $A$ of size $n$, find the index pair $i$ and $j$ that maximizes $S = \sum_{k=i}^{j}A[k]$, return $S$ as the answer.
+ **5pts.** Implement a brute-force solution as `maxSumBrute()` method in [src/Problem3.java](src/Problem3.java).
+ **5pts.** Implement a divide-and-conquer efficient solution as `maxSumSmart()` method in [src/Problem3.java](src/Problem3.java).
+ **5pts.** Describe in written form your solution ideas in the **Report** section below.
+ **5pts.** Defend your solutions to your instructor in live during practice sessions.
+ Your code will be tested against various inputs using `JUnit6` testing framework.
+ **Don't change API** of the solutions in [src/Problem3.java](src/Problem3.java).

#### Example
+ Input: `A={-17, 5, 3, -10, 6, 1, 4, -3, 8, 1, -13, 4}`
+ Output: `17`

#### Constraints
+ $1 \leq n \leq 10^5$
+ $-10^4 \leq A[i] \leq 10^4$

#### References
1. Chapter 5.6, The Algorithm Design Manual, 3rd Edition, 2020 - Steven H. Skiena
2. [maximum-subarray](https://leetcode.com/problems/maximum-subarray/description/)

### Report - Largest Subrange
#### Solution ideas
`maxSumBrute` tries every starting index and extends the subarray one element at a time, updating its running sum. `maxSumSmart` splits the array in half. The best answer is either wholly in the left half, wholly in the right half, or crosses the midpoint. For the crossing case, it finds the largest suffix sum of the left half and the largest prefix sum of the right half, then adds them. Initializing the best sums to negative infinity correctly handles arrays containing only negative values.

#### Running time
For length $n$, brute force takes $\Theta(n^2)$ time and $\Theta(1)$ extra space. The divide-and-conquer algorithm satisfies $T(n)=2T(n/2)+\Theta(n)$, because it solves two halves and scans both halves once for a crossing solution. By the Master Theorem, it takes $\Theta(n\log n)$ time and $\Theta(\log n)$ recursion-stack space.

#### Empirical comparison
Run `src/ProblemBenchmark.java`. It compares both methods on the same deterministic array of 3,000 integers, checks that their answers agree, and reports warmed-up average timings from `System.nanoTime()`. Brute force is intentionally measured at a moderate size because its quadratic work grows quickly.

### Defense Interview
+ Be ready to justify running times, for example using **Master method** for divide-and-conquer algorithms.
+ Be ready to answer questions on algorithms and your code.
+ Be ready to run your code and test it on various inputs.

Good Luck!