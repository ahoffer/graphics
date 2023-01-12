package org.example;

public abstract class SurfaceModel {

    private int originX = 0;
    private int originY = 0;
    private int squareSize;
    private int xGridSize;
    private int yGridSize;

    public void decOriginX(int value) {
        setOriginX(getOriginX() - value);
    }

    public void decOriginY(int value) {
        setOriginY(getOriginY() - value);
    }

    public void decSquareSize(int value) {
        setSquareSize(squareSize - value);
    }

    public abstract int getElevation(int x, int y);

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
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

    public void incOriginX(int value) {
        setOriginX(getOriginX() + value);

    }

    public void incOriginY(int value) {
        setOriginY(getOriginY() + value);
    }

    public void incSquareSize(int value) {
        setSquareSize(getSquareSize() + value);
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
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
