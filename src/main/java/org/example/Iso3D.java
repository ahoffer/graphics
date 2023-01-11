package org.example;

public class Iso3D{
    public Iso3D(){
    }
    int mode=1;
    public static final int LEFT_ISO=0;
    public static final int CENTER_ISO=1;
    public static final int RIGHT_ISO=2;
    public static float ySkew=1.0f;
    public void setTransformMode(int mode){
        this.mode=mode;
    }
}
