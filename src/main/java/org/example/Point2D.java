package org.example;

public class Point2D {
    double x = 0;
    double y = 0;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

//    public Point2D translate(int deltaX, int deltaY) {
//        x += deltaX;
//        y += deltaY;
//        return this;
//    }

    public Point2D scaleY( double yScalar ) {
        this.y *= yScalar;
        return this;
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)", x, y);
    }

    public Point2D translateX(int value) {
        x += value;
        return this;
    }

    public Point2D translateY(int value) {
        y += value;
        return this;
    }
}
