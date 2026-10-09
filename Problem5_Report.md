# Problem 5 Report: Integer Multiplication

## Objective

Multiply two signed decimal integers provided as strings, without using `java.math.BigInteger` in either multiplication algorithm.

## Algorithms

Both methods parse an optional sign, validate the decimal digits, and remove leading zeroes. The sign of a nonzero result is negative exactly when one input is negative and the other is positive.

`multBrute` uses grade-school multiplication. It multiplies each digit of the first operand by each digit of the second, stores partial results by place value, and propagates carries.

`multSmart` uses Karatsuba multiplication for sufficiently large operands and switches to grade-school multiplication for small subproblems. For operands split at $s$ decimal digits, write $x=x_1 10^s+x_0$ and $y=y_1 10^s+y_0$. It recursively computes three products and combines them as

$$
xy=z_2 10^{2s}+(z_1-z_2-z_0)10^s+z_0,
$$

where $z_2=x_1y_1$, $z_0=x_0y_0$, and $z_1=(x_1+x_0)(y_1+y_0)$.

## Correctness

Grade-school multiplication adds the contribution of every pair of decimal digits into its correct place-value position, with carries preserving the represented value. Karatsuba's identity is the expansion of $(x_1 10^s+x_0)(y_1 10^s+y_0)$; its middle coefficient equals $x_1y_0+x_0y_1$. Recursive calls therefore produce the same magnitude as ordinary multiplication. Sign handling then supplies the correct sign, while zero is always formatted without a negative sign.

## Complexity

For operand lengths $n$ and $m$ digits, grade-school multiplication takes $\Theta(nm)$ time and $O(n+m)$ result space. For similarly sized operands of $n$ digits, Karatsuba satisfies $T(n)=3T(n/2)+\Theta(n)$, giving $\Theta(n^{\log_2 3})$, approximately $\Theta(n^{1.585})$, by the Master Theorem. Its additions and subtractions operate on decimal strings; small recursive cases use grade-school multiplication.

## Empirical Evaluation

Run `src/ProblemBenchmark.java` to compare both methods on the same pair of 1,200-digit operands. The harness uses `BigInteger` only as an independent test oracle, checks both results, warms up each method, and reports average milliseconds per call over seven measured runs. Timings depend on the JVM and machine. Karatsuba's recursive overhead can make it slower for shorter operands.