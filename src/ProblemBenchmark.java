import java.math.BigInteger;
import java.util.Random;

public class ProblemBenchmark {
    private static final int WARMUP_RUNS = 3;
    private static final int MEASURED_RUNS = 7;

    public static void main(String[] args) {
        verifyExamples();
        benchmarkProblem1();
        benchmarkProblem2();
        benchmarkProblem3();
        benchmarkProblem4();
        benchmarkProblem5();
    }

    private static void verifyExamples() {
        int[] repeated = {1, 1, 1, 2, 2, 2, 2, 2, 2, 4, 4, 4, 5, 5, 5, 5};
        require(Problem1.countFreqSmart(repeated, 4) == 3, "Problem 1 example");
        require(Problem2.getMedianSmart(new int[]{2, 4}, new int[]{3, 5}) == 3.5, "Problem 2 example");
        int[] subrange = {-17, 5, 3, -10, 6, 1, 4, -3, 8, 1, -13, 4};
        require(Problem3.maxSumSmart(subrange) == 17, "Problem 3 example");
        require(Problem4.minDistSmart(new double[][]{{0, 0}, {3, 4}, {-5, -3}}) == 5.0,
                "Problem 4 example");
        String product = "1219326320073159566072245112635269";
        require(Problem5.multSmart("12345678987654321", "98765432123456789").equals(product),
                "Problem 5 example");
        require(Problem5.multSmart("-00012", "+03").equals("-36"), "Signed multiplication");
        require(Problem5.multSmart("0", "-999").equals("0"), "Zero multiplication");
        System.out.println("Example checks passed.");
    }

    private static void benchmarkProblem1() {
        int[] values = new int[100_000];
        for (int index = 0; index < values.length; index++) {
            values[index] = index / 10;
        }
        report("P1 brute", () -> Problem1.countFreqBrute(values, 9_999));
        report("P1 smart", () -> Problem1.countFreqSmart(values, 9_999));
    }

    private static void benchmarkProblem2() {
        int[] first = new int[1_000];
        int[] second = new int[1_000];
        for (int index = 0; index < 1_000; index++) {
            first[index] = index * 2;
            second[index] = index * 2 + 1;
        }
        report("P2 brute", () -> Problem2.getMedianBrute(first, second));
        report("P2 smart", () -> Problem2.getMedianSmart(first, second));
    }

    private static void benchmarkProblem3() {
        int[] values = new int[3_000];
        Random random = new Random(31);
        for (int index = 0; index < values.length; index++) {
            values[index] = random.nextInt(2_001) - 1_000;
        }
        require(Problem3.maxSumBrute(values) == Problem3.maxSumSmart(values), "Problem 3 cross-check");
        report("P3 brute", () -> Problem3.maxSumBrute(values));
        report("P3 smart", () -> Problem3.maxSumSmart(values));
    }

    private static void benchmarkProblem4() {
        double[][] points = new double[1_500][2];
        Random random = new Random(47);
        for (int index = 0; index < points.length; index++) {
            points[index][0] = random.nextDouble() * 10_000;
            points[index][1] = random.nextDouble() * 10_000;
        }
        double brute = Problem4.minDistBrute(points);
        double smart = Problem4.minDistSmart(points);
        require(Math.abs(brute - smart) < 1e-9, "Problem 4 cross-check");
        report("P4 brute", () -> Problem4.minDistBrute(points));
        report("P4 smart", () -> Problem4.minDistSmart(points));
    }

    private static void benchmarkProblem5() {
        String first = randomDigits(1_200, 59);
        String second = randomDigits(1_200, 61);
        String expected = new BigInteger(first).multiply(new BigInteger(second)).toString();
        require(Problem5.multBrute(first, second).equals(expected), "Problem 5 brute cross-check");
        require(Problem5.multSmart(first, second).equals(expected), "Problem 5 smart cross-check");
        report("P5 brute", () -> Problem5.multBrute(first, second));
        report("P5 smart", () -> Problem5.multSmart(first, second));
    }

    private static String randomDigits(int length, long seed) {
        Random random = new Random(seed);
        StringBuilder value = new StringBuilder(length);
        value.append((char) ('1' + random.nextInt(9)));
        while (value.length() < length) {
            value.append((char) ('0' + random.nextInt(10)));
        }
        return value.toString();
    }

    private static void report(String label, Runnable operation) {
        for (int run = 0; run < WARMUP_RUNS; run++) {
            operation.run();
        }
        long start = System.nanoTime();
        for (int run = 0; run < MEASURED_RUNS; run++) {
            operation.run();
        }
        double averageMilliseconds = (System.nanoTime() - start) / (double) MEASURED_RUNS / 1_000_000.0;
        System.out.printf("%-10s %.3f ms/op%n", label, averageMilliseconds);
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}