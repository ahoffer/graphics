package org.example;

public class Surface3D {
    int xSquareSize = 5, ySquareSize = 5;
    int xGridSize, yGridSize;
    int[][] surfaceElevations = null;
    int[][] surfaceElevationsAverage = null;

    public int getElevation(int x, int y) {
        return surfaceElevations[y][x];
    }

    public void setElevation(int x, int y, int elevation) {
        surfaceElevations[y][x] = elevation;
    }

    public int randomElevation(int minHeight, int maxHeight) {
        return (int) (Math.random() * (maxHeight - minHeight) + minHeight);
    }

    public int getElevationAvg(int x, int y) {
        return surfaceElevations[y][x];
    }

    public void setElevationAvg(int x, int y, int elevation) {
        surfaceElevationsAverage[y][x] = elevation;
    }

    public int average(int x, int y) {
        int total = getElevation(x - 1, y - 1);
        total += getElevation(x, y - 1);
        total += getElevation(x + 1, y - 1);
        total += getElevation(x - 1, y);
        total += getElevation(x, y);
        total += getElevation(x + 1, y);
        total += getElevation(x - 1, y + 1);
        total += getElevation(x, y + 1);
        total += getElevation(x + 1, y + 1);
        return (int) total / 9;
    }

    Surface3D(int xGridSize, int yGridSize, int nRandomHeights, int minHeight, int maxHeight) {
        this.xGridSize = xGridSize;
        this.yGridSize = yGridSize;
        surfaceElevations = new int[xGridSize][yGridSize];
        int randomX, randomY = 0;
        for (int i = 0; i < nRandomHeights; i++) {
            randomX = (int) (Math.random() * (xGridSize - 2) + 1);//-2 leaves 0's in outsize edge points
            randomY = (int) (Math.random() * (yGridSize - 2) + 1);
            setElevation(randomX, randomY, randomElevation(minHeight, maxHeight));
        }
        surfaceElevationsAverage = new int[xGridSize][yGridSize];
        for (int i = 0; i < 2; i++) {
            for (int y = 1; y < yGridSize - 1; y++)
                for (int x = 1; x < xGridSize - 1; x++)
                    setElevationAvg(x, y, average(x, y));
            surfaceElevations = surfaceElevationsAverage;
        }
    }
}
