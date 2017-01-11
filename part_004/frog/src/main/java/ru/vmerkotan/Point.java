package ru.vmerkotan;

/**
 * Simple Point on Field class.
 *
 * Created by vmerkotan on 1/11/2017.
 */
public class Point {

    private int sector;
    private int circle;

    public Point(int sector, int circle) {
        this.sector = sector;
        this.circle = circle;
    }

    public int getSector() {
        return sector;
    }

    public int getCircle() {
        return circle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (sector != point.sector) return false;
        return circle == point.circle;
    }

    @Override
    public int hashCode() {
        int result = sector;
        result = 31 * result + circle;
        return result;
    }
}
