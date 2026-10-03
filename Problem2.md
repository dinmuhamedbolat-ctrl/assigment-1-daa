### Problem 2 - Median of Two Sorted Arrays (20 points)

+ Given two sorted arrays $A$ and $B$ of sizes $n$ and $m$ respectively, return the median of the two sorted arrays. If there are two medians return the average of them. 
+ **5pts.** Implement a brute-force solution as `getMedianBrute()` method in [src/Problem2.java](src/Problem2.java). 
+ **5pts.** Implement a divide-and-conquer efficient solution as `getMedianSmart()` method in [src/Problem2.java](src/Problem2.java).
+ **5pts.** Describe in written form your solution ideas in the **Report** section below.
+ **5pts.** Defend your solutions to your instructor in live during practice sessions.
+ Your code will be tested against various inputs using `JUnit6` testing framework.
+ **Don't change API** of the solutions in [src/Problem2.java](src/Problem2.java).

#### Example 1
+ Input: `A={2,4}, B={3}`
+ Output: `3.0`

#### Example 2
+ Input: `A={2,4}, B={3,5}`
+ Output: `3.5`

#### Constraints
+ $0 \leq n,m \leq 1000$
+ $1 \leq (n+m) \leq 2000$
+ $-10^6 \leq A[i],B[i] \leq 10^6$
+ $A$ and $B$ are sorted in non-descending order

#### References
1. Exercise 5-8, The Algorithm Design Manual, 3rd Edition, 2020 - Steven H. Skiena
2. [median-of-two-sorted-arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/description/)

### Report - Median of Two Sorted Arrays
#### Solution ideas
`getMedianBrute` merges the two sorted arrays only until it reaches the middle position, keeping the last two values needed for the answer. It does not need to allocate a third merged array. `getMedianSmart` binary-searches a partition in the shorter input. A valid partition places every value on the left of both arrays at or below every value on the right; the median is then the largest left value, or the average of the largest left and smallest right values for an even total length.

#### Running time
Let $n$ and $m$ be the input lengths. The partial merge takes $\Theta(n+m)$ time in the worst case and $\Theta(1)$ extra space. The partition search takes $\Theta(\log(\min(n,m)+1))$ time and $\Theta(1)$ extra space. The shorter array is searched so the partition remains valid even when one input is empty.

#### Empirical comparison
Run `src/ProblemBenchmark.java`. Its `System.nanoTime()` benchmark compares both algorithms on two sorted arrays of 1,000 elements each, with warm-up calls and seven measured runs. The smart method should require substantially fewer comparisons as input sizes grow. Small inputs can be dominated by timing noise and method-call overhead.

### Defense Interview 
+ Be ready to justify running times, for example using **Master method** for divide-and-conquer algorithms.
+ Be ready to answer questions on algorithms and your code.
+ Be ready to run your code and test it on various inputs.

Good Luck!