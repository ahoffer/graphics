package org.example;

import static org.example.Iso3D.CENTER_ISO;
import static org.example.Iso3D.LEFT_ISO;
import static org.example.Iso3D.RIGHT_ISO;

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

    public Point2D transform3D(Iso3D iso3D) {
        switch (iso3D.mode) {
            case LEFT_ISO:
                return new Point2D(x + z, (int) (((-y) + z) * Iso3D.ySkew));
            case CENTER_ISO:
                return new Point2D(x + z, (int) (((-y) + z - x) * Iso3D.ySkew));
            case RIGHT_ISO:
                return new Point2D(x - z, (int) (((-y) + z) * Iso3D.ySkew));
            default:
                throw new RuntimeException("Invalid Transformation Mode! [" + iso3D.mode + "]?");
        }
    }
}
