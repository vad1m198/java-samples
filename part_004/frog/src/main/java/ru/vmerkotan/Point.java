package ru.vmerkotan;

/**
 * Simple Point on Field class.
 *
 * Created by vmerkotan on 1/11/2017.
 */
public class Point {
    /**
     * Holds sector number.
     */
    private int sector;
    /**
     * Holds circle number.
     */
    private int circle;

    /**
     * Creates new Point instance.
     *
     * @param sector    int sector position.
     * @param circle    int circle position.
     */
    Point(int sector, int circle) {
        this.sector = sector;
        this.circle = circle;
    }

    /**
     * Returns sector number.
     *
     * @return point sector number.
     */
    int getSector() {
        return sector;
    }

    /**
     * Returns circle number.
     *
     * @return  point cirlce number.
     */
    int getCircle() {
        return circle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        return sector == point.sector && circle == point.circle;
    }

    @Override
    public int hashCode() {
        int result = sector;
        result = 31 * result + circle;
        return result;
    }
}
