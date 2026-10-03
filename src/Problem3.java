public class Problem3 {
    public static int maxSumBrute(int[] A) {
        if (A.length == 0) {
            throw new IllegalArgumentException("Array must be non-empty");
        }
        int best = Integer.MIN_VALUE;
        for (int start = 0; start < A.length; start++) {
            int sum = 0;
            for (int end = start; end < A.length; end++) {
                sum += A[end];
                best = Math.max(best, sum);
            }
        }
        return best;
    }

    public static int maxSumSmart(int[] A) {
        if (A.length == 0) {
            throw new IllegalArgumentException("Array must be non-empty");
        }
        return maxSum(A, 0, A.length - 1);
    }

    private static int maxSum(int[] A, int left, int right) {
        if (left == right) {
            return A[left];
        }
        int middle = left + (right - left) / 2;
        int leftBest = maxSum(A, left, middle);
        int rightBest = maxSum(A, middle + 1, right);

        int sum = 0;
        int bestSuffix = Integer.MIN_VALUE;
        for (int index = middle; index >= left; index--) {
            sum += A[index];
            bestSuffix = Math.max(bestSuffix, sum);
        }
        sum = 0;
        int bestPrefix = Integer.MIN_VALUE;
        for (int index = middle + 1; index <= right; index++) {
            sum += A[index];
            bestPrefix = Math.max(bestPrefix, sum);
        }
        return Math.max(Math.max(leftBest, rightBest), bestSuffix + bestPrefix);
    }
}