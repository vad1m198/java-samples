package ru.vmerkotan;

/**
 * {@code Field} class represents
 * a circle for frog.
 *
 * Created by vmerkotan on 1/11/2017.
 */
class Field {
    /**
     * Holds field sectors amount.
     */
    private final int sectorsNumber = 16;
    /**
     * Holds field circles amount.
     */
    private final int circlesNumber = 10;
    /**
     * Points occupied by some type of barriers.
     */
    private Point[] occupiedPoints;

    /**
     * Creates new Field object.
     *
     * @param occupiedPoints Point[] occupied by some type of barriers.
     */
    Field(Point[] occupiedPoints) {
        this.occupiedPoints = occupiedPoints;
    }

    /**
     * Verifies that passed Point is occupied or not.
     *
     * @param point Point to verify.
     * @return  true if Point is occupied with barrier.
     */
    boolean isOccupied(Point point) {
        if (this.occupiedPoints != null) {
            for (Point p: occupiedPoints) {
                if (p.equals(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns field sectors number.
     *
     * @return sectorsNumber value.
     */
    int getSectorsNumber() {
        return sectorsNumber;
    }

    /**
     * Returns field circles number.
     *
     * @return circlesNumber value
     */
    int getCirclesNumber() {
        return circlesNumber;
    }
}
