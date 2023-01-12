package org.example;

import java.util.Arrays;
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
        for (int y = 1; y < getYgridSize() - 2; y++)
            for (int x = 1; x < getXgridSize() - 2; x++)
                setElevation(x, y, random.nextInt(20));

//        for (int y = 1; y < getYgridSize() - 2; y++)
//            for (int x = 1; x < getXgridSize() - 2; x++) {
//                System.err.println(String.format("(%d,%d)=%d",x,y,surfaceElevations[y][x]));
//            }
        }



    public int getElevation(int x, int y) {
        return surfaceElevations[y][x];
    }

    void setElevation(int x, int y, int elevation) {
        surfaceElevations[y][x] = elevation;
    }

}
