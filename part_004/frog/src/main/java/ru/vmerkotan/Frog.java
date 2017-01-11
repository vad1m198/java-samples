package ru.vmerkotan;

import java.util.Arrays;

/**
 * {@code Frog} class represents a Frod from the task.
 *
 * Created by vmerkotan on 1/11/2017.
 */
public class Frog {

    private Point currentPoint;
    private Point destinationPoint;
    private Field field;

    public Frog(Point startPoint, Point destinationPoint, Field field) {
        if(startPoint.getCircle() < 0 || startPoint.getCircle() > field.getCirclesNumber()
                || startPoint.getSector() < 0 || startPoint.getSector() > field.getSectorsNumber()
                || destinationPoint.getCircle() < 0 || destinationPoint.getCircle() > field.getCirclesNumber()
                || destinationPoint.getSector() < 0 || destinationPoint.getSector() > field.getSectorsNumber()) {
            throw new RuntimeException("Start or destination points are out of field.");
        }
        this.currentPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.field = field;
    }

    public Point[] getRoute() {
        if(currentPoint.equals(destinationPoint)) {
            return new Point[0];
        }

        if(!isPossibleMove()) {
            throw new RuntimeException("Move is impossible");
        }



        return new Point[]{new Point(1,1)};
    }

    private boolean isPossibleMove() {
        int sectorsToPass;
        int circlesToPass;
        if(destinationPoint.getSector() >= currentPoint.getSector()) {
            sectorsToPass = destinationPoint.getSector() - currentPoint.getSector();
        } else {
            sectorsToPass = this.field.getSectorsNumber() - this.currentPoint.getSector() + this.destinationPoint.getSector();
        }

        circlesToPass = Math.abs(this.currentPoint.getCircle() - this.destinationPoint.getCircle());

        if(sectorsToPass == 0 || sectorsToPass + circlesToPass < 3) {
            return false;
        }
        return true;
    }

    private Point[] getPossibleMoves() {
        Point[] result = new Point[5];
        int counter = 0;
        Point p1 = new Point(this.currentPoint.getSector() + 3 > this.field.getSectorsNumber()
                ? this.currentPoint.getSector() - this.field.getSectorsNumber() + 3
                : this.currentPoint.getSector() + 3, currentPoint.getCircle());
        result[counter++] = p1;
        if(this.currentPoint.getCircle() + 1 <= this.field.getCirclesNumber()) {
            Point p2 = new Point(this.currentPoint.getSector() + 2 > this.field.getSectorsNumber()
                    ? this.currentPoint.getSector() - this.field.getSectorsNumber() + 2
                    : this.currentPoint.getSector() + 2, currentPoint.getCircle() + 1);
            if(!this.field.isOccupied(p2)) {
                result[counter++] = p2;
            }
        }

        if(this.currentPoint.getCircle() - 1 > 0) {
            Point p3 = new Point(this.currentPoint.getSector() + 2 > this.field.getSectorsNumber()
                    ? this.currentPoint.getSector() - this.field.getSectorsNumber() + 2
                    : this.currentPoint.getSector() + 2, currentPoint.getCircle() - 1);
            if(!this.field.isOccupied(p3)) {
                result[counter++] = p3;
            }
        }

        if(this.currentPoint.getCircle() + 2 <= this.field.getCirclesNumber()) {
            Point p4 = new Point(this.currentPoint.getSector() + 1 > this.field.getSectorsNumber()
                    ? this.currentPoint.getSector() - this.field.getSectorsNumber() + 1
                    : this.currentPoint.getSector() + 1, currentPoint.getCircle() + 2);
            if(!this.field.isOccupied(p4)) {
                result[counter++] = p4;
            }
        }

        if(this.currentPoint.getCircle() - 2 > 0) {
            Point p5 = new Point(this.currentPoint.getSector() + 1 > this.field.getSectorsNumber()
                    ? this.currentPoint.getSector() - this.field.getSectorsNumber() + 1
                    : this.currentPoint.getSector() + 1, currentPoint.getCircle() - 2);
            counter++;
            if(!this.field.isOccupied(p5)) {
                result[counter++] = p5;
            }
        }
        return Arrays.copyOf(result, counter);
    }



}
