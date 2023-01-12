package org.example;

public class Point2D {
    int x = 0;
    int y = 0;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
