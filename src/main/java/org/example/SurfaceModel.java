package org.example;

public abstract class SurfaceModel {
    private int squareSize;
    private int xGridSize;
    private int yGridSize;


    public void decSquareSize(int value) {
        setSquareSize(squareSize - value);
    }

    public abstract int getElevation(int x, int y);


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


    public void setSquareSize(int size) {
        if (size <= 0) {
            squareSize = 1;
        } else {
            squareSize = size;
        }
    }

    public void setXgridSize(int value) {
        xGridSize = value;
    }

    public void setYgridSize(int value) {
        yGridSize = value;
    }

}
