package three;

import java.util.Arrays;

public class Polygon {
    private Point[] points;

    public Polygon(int n) {
        points = new Point[n];
    }

    public Polygon(float[] corners) {
        points = new Point[corners.length / 2];

        for (int i = 0; i < corners.length; i += 2) {
            points[i / 2] = new Point(corners[i], corners[i + 1]);
        }
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "points=" + Arrays.toString(points) +
                '}';
    }
}
