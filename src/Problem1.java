public class Problem1 {
    public static int countFreqBrute(int[] A, int key) {
        int count = 0;
        for (int value : A) {
            if (value == key) {
                count++;
            }
        }
        return count;
    }

    public static int countFreqSmart(int[] A, int key) {
        int first = lowerBound(A, key);
        int afterLast = upperBound(A, key);
        return afterLast - first;
    }

    private static int lowerBound(int[] A, int key) {
        int left = 0;
        int right = A.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (A[middle] < key) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    private static int upperBound(int[] A, int key) {
        int left = 0;
        int right = A.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (A[middle] <= key) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }
}