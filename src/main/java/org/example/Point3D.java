package org.example;

import static org.example.GlobalVars.CENTER_ISO;

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

    public Point2D transformToIso() {
        //            case LEFT_ISO:
        //                return new Point2D(x + z, (int) (((-y) + z) * GlobalVars.ySkew));
        if (GlobalVars.mode == CENTER_ISO) {
            return new Point2D(x + z, (int) (((-y) + z - x) * GlobalVars.ySkew));
//            case RIGHT_ISO:
//                return new Point2D(x - z, (int) (((-y) + z) * GlobalVars.ySkew));
        }
        throw new RuntimeException("Invalid Transformation Mode!");
    }
}
