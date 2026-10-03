public class Problem2 {
    public static double getMedianBrute(int[] A, int[] B) {
        int total = A.length + B.length;
        if (total == 0) {
            throw new IllegalArgumentException("At least one array must be non-empty");
        }

        int target = total / 2;
        int indexA = 0;
        int indexB = 0;
        int previous = 0;
        int current = 0;
        for (int index = 0; index <= target; index++) {
            previous = current;
            if (indexA < A.length && (indexB >= B.length || A[indexA] <= B[indexB])) {
                current = A[indexA++];
            } else {
                current = B[indexB++];
            }
        }
        return total % 2 == 1 ? current : ((double) previous + current) / 2.0;
    }

    public static double getMedianSmart(int[] A, int[] B) {
        if (A.length > B.length) {
            return getMedianSmart(B, A);
        }
        int total = A.length + B.length;
        if (total == 0) {
            throw new IllegalArgumentException("At least one array must be non-empty");
        }

        int leftSize = (total + 1) / 2;
        int low = 0;
        int high = A.length;
        while (low <= high) {
            int cutA = low + (high - low) / 2;
            int cutB = leftSize - cutA;
            long leftA = cutA == 0 ? Long.MIN_VALUE : A[cutA - 1];
            long rightA = cutA == A.length ? Long.MAX_VALUE : A[cutA];
            long leftB = cutB == 0 ? Long.MIN_VALUE : B[cutB - 1];
            long rightB = cutB == B.length ? Long.MAX_VALUE : B[cutB];

            if (leftA <= rightB && leftB <= rightA) {
                long largestLeft = Math.max(leftA, leftB);
                if (total % 2 == 1) {
                    return largestLeft;
                }
                long smallestRight = Math.min(rightA, rightB);
                return ((double) largestLeft + smallestRight) / 2.0;
            }
            if (leftA > rightB) {
                high = cutA - 1;
            } else {
                low = cutA + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays must be sorted");
    }
}