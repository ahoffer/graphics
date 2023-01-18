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
            for (int x = 1; x < getXgridSize() - 1; x++)
                setPoint(new Point3D(x, random.nextInt(20), z));
    }

    void setPoint(Point3D point) {
        surfaceElevations[(int) point.z][(int) point.x] = (int) point.y;
    }

    @Override
    public Point3D pointAt(int x, int z) {
        // The flat plane (the grid) is X-Z, the Y axis represents height.
        return new Point3D(x, surfaceElevations[z][x], z);
    }
}
