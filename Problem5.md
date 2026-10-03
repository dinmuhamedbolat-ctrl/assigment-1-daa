### Problem 5 - Integer Multiplication (20 points)

+ Given two long integer $A$ and $B$ represented as `String` objects, output the product of the two numbers.
+ Do not use `java.math.BigInteger`. But you can use it to test that your solutions are correct.
+ **5pts.** Implement a brute-force solution as `multBrute` method in [src/Problem5.java](src/Problem5.java).
+ **5pts.** Implement a divide-and-conquer efficient solution as `multSmart` method in [src/Problem5.java](src/Problem5.java).
+ **5pts.** Describe in written form your solution ideas in the **Report** section below.
+ **5pts.** Defend your solutions to your instructor in live during practice sessions.
+ Your code will be tested against various inputs using `JUnit6` testing framework.
+ **Don't change API** of the solutions in [src/Problem5.java](src/Problem5.java).

#### Example
+ Input: `A = "12345678987654321", B="98765432123456789"`
+ Output: `"1219326320073159566072245112635269"`

#### Constraints
+ $1 \leq A.length(), B.length() \leq 10^4$

#### References
1. Chapter 5.5, The Algorithm Design Manual, 3rd Edition, 2020 - Steven H. Skiena
2. Chapter 1.3, Algorithms Illuminated Part1, 2017 - Tim Roughgarden

### Report - Integer Multiplication
#### Solution ideas
Both methods first separate each optional sign from the decimal digits and remove leading zeroes. `multBrute` uses the grade-school multiplication algorithm: each digit of one number is multiplied by each digit of the other, and carries are propagated into the result. `multSmart` uses Karatsuba multiplication. It splits each number into high and low parts, computes three recursive products, and combines them using $xy=z_2 10^{2s}+(z_1-z_2-z_0)10^s+z_0$. The implementation uses grade-school multiplication for small subproblems and never uses `BigInteger` in either solution.

#### Running time
For digit lengths $n$ and $m$, brute force takes $\Theta(nm)$ time and $O(n+m)$ result space. For similarly sized inputs of length $n$, Karatsuba satisfies $T(n)=3T(n/2)+\Theta(n)$, so the Master Theorem gives $\Theta(n^{\log_2 3})$, approximately $\Theta(n^{1.585})$. The algorithms also correctly normalize zero and support optional `+` and `-` signs.

#### Empirical comparison
Run `src/ProblemBenchmark.java`. It compares both methods on the same 1,200-digit operands, verifies the result against `BigInteger` as a test oracle only, then reports warmed-up average timings measured with `System.nanoTime()`. Results depend on the JVM and hardware; Karatsuba's recursive overhead means it may not win on short operands.

### Defense Interview
+ Be ready to justify running times, for example using **Master method** for divide-and-conquer algorithms.
+ Be ready to answer questions on algorithms and your code.
+ Be ready to run your code and test it on various inputs.

Good Luck!