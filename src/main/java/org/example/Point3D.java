package org.example;

public class Point3D {
    double x;
    double y;
    double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D scale(double xScalar, double yScalar, double zScalar) {
        this.x *= xScalar;
        this.y *= yScalar;
        this.z *= zScalar;
        return this;
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f,%.2f)", x, y, z);
    }

    public Point2D transformToIso(double ySkew) {
        //            case LEFT_ISO:
        //                return new Point2D(x + z, (int) (((-y) + z) * GlobalVars.ySkew));
        // if (GlobalVars.mode == CENTER_ISO) {
        return new Point2D(x + z, (int) (((-y) + z - x) * ySkew));
//            case RIGHT_ISO:
//                return new Point2D(x - z, (int) (((-y) + z) * GlobalVars.ySkew));
        //   }
        //  throw new RuntimeException("Invalid Transformation Mode!");
    }
}
