package org.example;

public class NoiseSurface3D {
    PerlinNoise perlinNoise = new PerlinNoise(3);
    int[][] surfaceElevations = null;
    int xGridSize;
    int yGridSize;

    NoiseSurface3D(int xGridSize, int yGridSize, int nRandomHeights, int minHeight, int maxHeight) {
        this.xGridSize = xGridSize;
        this.yGridSize = yGridSize;
        surfaceElevations = new int[xGridSize][yGridSize];
        int randomX;
        int randomY;
        for (int i = 0; i < nRandomHeights; i++) {
            randomX =
                    (int)
                            (Math.random() * (xGridSize - 2)
                                    + 1); // -2 leaves 0's in outsize edge points
            randomY = (int) (Math.random() * (yGridSize - 2) + 1);
            setElevation(randomX, randomY, randomElevation(minHeight, maxHeight));
        }
        for (int i = 0; i < 2; i++) {
            for (int y = 1; y < yGridSize - 1; y++)
                for (int x = 1; x < xGridSize - 1; x++) setElevation(x, y, average(x, y));
        }
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
        return total / 9;
    }

    public int getElevation(int x, int y) {
        return surfaceElevations[y][x];
    }

    public int randomElevation(int minHeight, int maxHeight) {
        return (int) (Math.random() * (maxHeight - minHeight) + minHeight);
    }

    public void setElevation(int x, int y, int elevation) {
        surfaceElevations[y][x] = elevation;
    }
}
