package org.example;

import java.util.Random;

public class RandomSurfaceModel extends SurfaceModel {
    int[][] surfaceElevations;

    RandomSurfaceModel(int xGridSize, int yGridSize, int squareSize) {
        setXgridSize(xGridSize);
        setYgridSize(yGridSize);
        setSquareSize(squareSize);
        computeElevations();
    }

    void computeElevations() {
        surfaceElevations = new int[getXgridSize()][getYgridSize()];
        Random random = new Random(3);
        for (int y = 0; y < getYgridSize(); y++)
            for (int x = 1; x < getXgridSize() - 1; x++)
                setElevation(x, y, random.nextInt(20));
    }

    public int getElevation(int x, int y) {
        return surfaceElevations[y][x];
    }

    public void setElevation(int x, int y, int elevation) {
        surfaceElevations[y][x] = elevation;
    }

}
