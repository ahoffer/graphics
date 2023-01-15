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
        for (int z = 0; z < getYgridSize(); z++)
            for (int x = 1; x < getXgridSize() - 1; x++) setElevation(x, z, random.nextInt(20));
    }

    @Override
    public Point3D pointAt(int x, int z) {
        // The flat plane (the grid) is X-Z, the Y axis represents height.
        return new Point3D(x, surfaceElevations[z][x], z);
    }

    public void setElevation(int x, int z, int elevation) {
        surfaceElevations[z][x] = elevation;
    }
}
