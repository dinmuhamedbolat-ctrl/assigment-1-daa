### Problem 1 - Number of Occurrences (20 points)

+ Count the number of times a given key $k$ occurs in a given sorted array $A$.
+ **5pts.** Implement a brute-force solution as `countFreqBrute()` method in [src/Problem1.java](src/Problem1.java). 
+ **5pts.** Implement a divide-and-conquer efficient solution as `countFreqSmart()` method in [src/Problem1.java](src/Problem1.java).
+ **5pts.** Describe in written form your solution ideas in the **Report** section below.
+ **5pts.** Defend your solutions to your instructor in live during practice sessions.
+ Your code will be tested against various inputs using `JUnit6` testing framework.
+ **Don't change API** of the solutions in [src/Problem1.java](src/Problem1.java).
#### Example 1
+ Input: `A = {1,1,1,2,2,2,2,2,2,4,4,4,5,5,5,5}, key = 4`
+ Output: `3`

#### Example 2
+ Input:  `A = {1,1,1,2,2,2,2,2,2,4,4,4,5,5,5,5}, key = 3`
+ Output: `0`

#### Constraints
+ $0 \leq A.length \leq 10^5$
+ $-10^9 \leq A[i] \leq 10^9$
+ $-10^9 \leq key \leq 10^9$
+ $A$ is sorted in non-descending order

#### References
1. Chapter 5.1.1 Counting Occurrences, The Algorithm Design Manual, 3rd Edition, 2020 - Steven H. Skiena
2. [find-first-and-last-position-of-element-in-sorted-array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/)

### Report - Number of Occurrences
#### Solution ideas
`countFreqBrute` scans every array element and increments a counter when it equals the key. `countFreqSmart` uses two binary searches: a lower bound finds the first index whose value is at least the key, and an upper bound finds the first index whose value is greater than the key. Their difference is the frequency; when the key is absent, both searches return the same insertion position.

#### Running time
For an array of length $n$, brute force takes $\Theta(n)$ time and $\Theta(1)$ extra space. The smart method takes $\Theta(\log n)$ time and $\Theta(1)$ extra space. It performs two binary searches, each with recurrence-like halving behavior and at most $\lceil\log_2(n+1)\rceil$ iterations.

#### Empirical comparison
Run `src/ProblemBenchmark.java` to measure both methods with `System.nanoTime()`. The benchmark uses a sorted array of 100,000 values, performs warm-up calls, and reports average milliseconds per call. The measured values depend on the machine and JVM; the expected trend is linear growth for brute force and logarithmic growth for the smart method. Do not treat one timing run as a universal constant.

### Defense Interview 
+ Be ready to justify running times, for example using **Master method** for divide-and-conquer algorithms.
+ Be ready to answer questions on algorithms and your code.
+ Be ready to run your code and test it on various inputs. 

Good Luck!