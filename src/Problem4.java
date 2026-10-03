import java.util.Arrays;

public class Problem4 {
    private static final class Point {
        private final double x;
        private final double y;
        private int rank;

        private Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double minDistBrute(double[][] P) {
        if (P.length < 2) {
            return Double.POSITIVE_INFINITY;
        }
        double best = Double.POSITIVE_INFINITY;
        for (int first = 0; first < P.length; first++) {
            for (int second = first + 1; second < P.length; second++) {
                best = Math.min(best, distance(P[first][0], P[first][1], P[second][0], P[second][1]));
            }
        }
        return best;
    }

    public static double minDistSmart(double[][] P) {
        if (P.length < 2) {
            return Double.POSITIVE_INFINITY;
        }
        Point[] byX = new Point[P.length];
        for (int index = 0; index < P.length; index++) {
            byX[index] = new Point(P[index][0], P[index][1]);
        }
        Arrays.sort(byX, (first, second) -> {
            int byCoordinate = Double.compare(first.x, second.x);
            return byCoordinate != 0 ? byCoordinate : Double.compare(first.y, second.y);
        });
        for (int index = 0; index < byX.length; index++) {
            byX[index].rank = index;
        }
        Point[] byY = byX.clone();
        Arrays.sort(byY, (first, second) -> {
            int byCoordinate = Double.compare(first.y, second.y);
            return byCoordinate != 0 ? byCoordinate : Double.compare(first.x, second.x);
        });
        return closest(byX, 0, byX.length, byY);
    }

    private static double closest(Point[] byX, int left, int right, Point[] byY) {
        int size = right - left;
        if (size <= 3) {
            double best = Double.POSITIVE_INFINITY;
            for (int first = 0; first < byY.length; first++) {
                for (int second = first + 1; second < byY.length; second++) {
                    best = Math.min(best, distance(byY[first].x, byY[first].y,
                            byY[second].x, byY[second].y));
                }
            }
            return best;
        }

        int middle = left + size / 2;
        int leftCount = middle - left;
        Point[] leftByY = new Point[leftCount];
        Point[] rightByY = new Point[size - leftCount];
        int leftIndex = 0;
        int rightIndex = 0;
        for (Point point : byY) {
            if (point.rank < middle) {
                leftByY[leftIndex++] = point;
            } else {
                rightByY[rightIndex++] = point;
            }
        }

        double best = Math.min(closest(byX, left, middle, leftByY),
                closest(byX, middle, right, rightByY));
        double dividingX = byX[middle].x;
        Point[] strip = new Point[size];
        int stripSize = 0;
        for (Point point : byY) {
            if (Math.abs(point.x - dividingX) < best) {
                strip[stripSize++] = point;
            }
        }
        for (int first = 0; first < stripSize; first++) {
            for (int second = first + 1; second < stripSize
                    && strip[second].y - strip[first].y < best; second++) {
                best = Math.min(best, distance(strip[first].x, strip[first].y,
                        strip[second].x, strip[second].y));
            }
        }
        return best;
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.hypot(x1 - x2, y1 - y2);
    }
}