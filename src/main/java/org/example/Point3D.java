package org.example;

import static org.example.Iso3D.CENTER_ISO;
import static org.example.Iso3D.LEFT_ISO;
import static org.example.Iso3D.RIGHT_ISO;

public class Point3D {
    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    int x = 0;
    int y = 0;
    int z = 0;

    public Point2D transform3D(Iso3D iso3D) throws Exception{
        switch(iso3D.mode) {
            case LEFT_ISO:
                return new Point2D(x+ z,(int)(((-y)+ z)* iso3D.ySkew));
            case CENTER_ISO:
                return new Point2D(x+ z,(int)(((-y)+ z- x)* iso3D.ySkew));
            case RIGHT_ISO:
                return new Point2D(x- z,(int)(((-y)+ z)* iso3D.ySkew));
            default:throw new Exception("Invalid Transformation Mode! ["+ iso3D.mode+"]?");
        }
    }
}
