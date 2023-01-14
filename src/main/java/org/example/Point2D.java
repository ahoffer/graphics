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

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)", x, y);
    }

    public Point2D translate(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }
}
