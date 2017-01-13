package ru.vmerkotan;

import java.util.Arrays;

/**
 * {@code Frog} class represents a Frod from the task.
 *
 * Created by vmerkotan on 1/11/2017.
 */
class Frog {
    /**
     * Point to start from.
     */
    private Point startPoint;
    /**
     * Point to get to.
     */
    private Point destinationPoint;
    /**
     * Field instance to perform on.
     */
    private Field field;
    /**
     * Holds max steps that can be performed.
     */
    private int maxCount = 50;
    /**
     * Min steps to get to destination point.
     */
    private int minSteps = 0;
    /**
     * was valid route found.
     */
    private boolean routeFound = false;
    /**
     *
     */
    private String path = "";
    /**
     * Creates new Frog instance.
     *
     * @param startPoint        Point to start from.
     * @param destinationPoint  Point to get to.
     * @param field             Field instance to perform on.
     */
    Frog(Point startPoint, Point destinationPoint, Field field) {
        if (startPoint.getCircle() < 1 || startPoint.getCircle() > field.getCirclesNumber()
                || startPoint.getSector() < 1 || startPoint.getSector() > field.getSectorsNumber()
                || destinationPoint.getCircle() < 1 || destinationPoint.getCircle() > field.getCirclesNumber()
                || destinationPoint.getSector() < 1 || destinationPoint.getSector() > field.getSectorsNumber()) {
            throw new RuntimeException("Start or destination points are out of field.");
        }
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.field = field;
    }

    /**
     * Gets minimal route to get to destination point.
     *
     * @return  String representation of minimal path.
     */
    String getRoute() {
        if (startPoint.equals(destinationPoint)) {
            this.routeFound = true;
        } else {
            recursiveJump(this.startPoint, 0, this.path);
        }
        if (!this.routeFound) {
            throw new ImpossibleMoveException("Route can not be created");
        }
        return this.path;
    }

    /**
     * Performs moves and write result to class variables.
     *
     * @param point         Point to make jump from.
     * @param count         How many jumps were performed previously.
     * @param previousPath  String representation of previous jumps.
     */
    private void recursiveJump(Point point, int count, String previousPath) {
        if (point.equals(this.destinationPoint)) {

            if (this.minSteps == 0 || this.minSteps > count) {

                this.minSteps = count;
                this.path = previousPath;
                routeFound = true;
            }
        } else if (count < maxCount && (this.minSteps == 0 || this.minSteps > count)) {

            Point[] points = getPossibleMoves(point);
            for (Point p: points) {
                recursiveJump(p, count + 1, previousPath + "[" + p.getSector() + ":" + p.getCircle()  + "] ");
            }
        }
    }

    /**
     * Returns possible moves that can be performed on field.
     * Also consider field boundaries and is Point is occupied or not.
     *
     * @param point Point to start moves from.
     * @return      Point[] of possible moves.
     */
    private Point[] getPossibleMoves(Point point) {
        Point[] result = new Point[5];
        int counter = 0;
        Point p1 = new Point(point.getSector() + 3 > this.field.getSectorsNumber()
                ? point.getSector() - this.field.getSectorsNumber() + 3
                : point.getSector() + 3, point.getCircle());
        if (!this.field.isOccupied(p1)) {
            result[counter++] = p1;
        }
        if (point.getCircle() + 1 <= this.field.getCirclesNumber()) {
            Point p2 = new Point(point.getSector() + 2 > this.field.getSectorsNumber()
                    ? point.getSector() - this.field.getSectorsNumber() + 2
                    : point.getSector() + 2, point.getCircle() + 1);
            if (!this.field.isOccupied(p2)) {
                result[counter++] = p2;
            }
        }
        if (point.getCircle() - 1 > 0) {
            Point p3 = new Point(point.getSector() + 2 > this.field.getSectorsNumber()
                    ? point.getSector() - this.field.getSectorsNumber() + 2
                    : point.getSector() + 2, point.getCircle() - 1);
            if (!this.field.isOccupied(p3)) {
                result[counter++] = p3;
            }
        }
        if (point.getCircle() + 2 <= this.field.getCirclesNumber()) {
            Point p4 = new Point(point.getSector() + 1 > this.field.getSectorsNumber()
                    ? point.getSector() - this.field.getSectorsNumber() + 1
                    : point.getSector() + 1, point.getCircle() + 2);
            if (!this.field.isOccupied(p4)) {
                result[counter++] = p4;
            }
        }
        if (point.getCircle() - 2 > 0) {
            Point p5 = new Point(point.getSector() + 1 > this.field.getSectorsNumber()
                    ? point.getSector() - this.field.getSectorsNumber() + 1
                    : point.getSector() + 1, point.getCircle() - 2);
            if (!this.field.isOccupied(p5)) {
                result[counter++] = p5;
            }
        }
        return Arrays.copyOf(result, counter);
    }

    /**
     * Returns minimal steps count.
     *
     * @return int minimal steps count.
     */
    int getMinimalStepsNumber() {
        return this.minSteps;
    }



}
