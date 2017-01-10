package ru.vmerkotan;

/**
 * {@code Point} class represents a point
 * on Game Field to make the move.
 *
 * Created by vmerkotan on 1/10/2017.
 */
public class Point {
    /**
     * X coordinate.
     */
    private int x;
    /**
     * Y coordinate.
     */
    private int y;

    /**
     * Creates new Point.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns X coordinate.
     *
     * @return x coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns Y coordinate.
     *
     * @return y coordinate.
     */
    public int getY() {
        return this.y;
    }
}
