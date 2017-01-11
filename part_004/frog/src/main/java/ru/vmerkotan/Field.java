package ru.vmerkotan;

/**
 * {@code Field} class represents
 * a circle for frog.
 *
 * Created by vmerkotan on 1/11/2017.
 */
public class Field {

    private final int sectorsNumber = 16;
    private final int circlesNumber = 10;
    private Point[] occupiedPoints;

    public Field(Point[] occupiedPoints) {
        this.occupiedPoints = occupiedPoints;
    }

    public boolean isOccupied(Point point) {
        if(this.occupiedPoints != null) {
            for(Point p: occupiedPoints) {
                if(p.equals(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getSectorsNumber() {
        return sectorsNumber;
    }

    public int getCirclesNumber() {
        return circlesNumber;
    }
}
