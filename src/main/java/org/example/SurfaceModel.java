package org.example;

public abstract class SurfaceModel {
    private int squareSize;
    private int xGridSize;
    private int yGridSize;

    public void decSquareSize(int value) {
        setSquareSize(squareSize - value);
    }

    public int getSquareSize() {
        return squareSize;
    }

    public int getXgridSize() {
        return xGridSize;
    }

    public int getYgridSize() {
        return yGridSize;
    }

    public void incSquareSize(int value) {
        setSquareSize(getSquareSize() + value);
    }

    public abstract Point3D pointAt(int x, int y);

    public void setSquareSize(int value) {
        squareSize = value > 0 ? value : 1;

    }

    public void setXgridSize(int value) {
        xGridSize = value;
    }

    public void setYgridSize(int value) {
        yGridSize = value;
    }
}
