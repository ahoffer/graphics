package org.example;

public class Point3D {
    double x = 0;
    double y = 0;
    double z = 0;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D scaleX(double xScalar) {
        this.x *= xScalar;
        return this;
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f,%.2f)", x, y, z);
    }

    public Point2D transformToIso() {
        //            case LEFT_ISO:
        //                return new Point2D(x + z, (int) (((-y) + z) * GlobalVars.ySkew));
        // if (GlobalVars.mode == CENTER_ISO) {
        return new Point2D(x + z, ((-y) + z - x) );
//            case RIGHT_ISO:
//                return new Point2D(x - z, (int) (((-y) + z) * GlobalVars.ySkew));
        //   }
        //  throw new RuntimeException("Invalid Transformation Mode!");
    }
}
