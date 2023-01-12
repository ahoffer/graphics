package org.example;

import static org.example.GlobalVars.CENTER_ISO;
import static org.example.GlobalVars.LEFT_ISO;
import static org.example.GlobalVars.RIGHT_ISO;

public class Point3D {
    int x = 0;
    int y = 0;
    int z = 0;
    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d", x, y, z);
    }

    public Point2D transformToIso(GlobalVars globalVars) {
        switch (globalVars.mode) {
            case LEFT_ISO:
                return new Point2D(x + z, (int) (((-y) + z) * GlobalVars.ySkew));
            case CENTER_ISO:
                return new Point2D(x + z, (int) (((-y) + z - x) * GlobalVars.ySkew));
            case RIGHT_ISO:
                return new Point2D(x - z, (int) (((-y) + z) * GlobalVars.ySkew));
            default:
                throw new RuntimeException("Invalid Transformation Mode! [" + globalVars.mode + "]?");
        }
    }
}
