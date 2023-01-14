package org.example;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private final int viewHeight;
    private final int viewWidth;
    private double elevationScalar = 1.0;
    private Graphics graphics;
    private SurfaceModel surface;
    private int translateX = 0;
    private int translateY = 0;
    private double ySkew = 0.5;

    public View(SurfaceModel surface, int viewWidth, int viewHeight) {
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        this.surface = surface;
    }

    public void adjustElevationScale(double value) {
        elevationScalar(elevationScalar() + value);
    }

    public void adjustYskew(double value) {
        setySkew(ySkew + value);
    }

    private void draw(int x, int y, double ySkew) {
        Point2D point1;
        Point2D point2;
        int courseness = surface.getSquareSize();
        point1 = new Point3D(
                x * courseness,
                surface.getElevation(x, y) * elevationScalar(),
                y * courseness).transformToIso().scaleY(ySkew).translateX(xOffset());
        point2 = new Point3D(
                (x + 1) * courseness,
                surface.getElevation(x + 1, y) * elevationScalar(),
                y * courseness).transformToIso().scaleY(y);
System.err.println(String.format("%s %s", point1,point2));
        graphics.drawLine(
                (int) point1.translateX(xOffset()).x,
                (int) point1.translateY(yOffset()).y,
                (int) point2.translateX(xOffset()).x,
                (int) point2.translateY(yOffset()).y);
    }

    public double elevationScalar() {
        return elevationScalar;
    }

    public void elevationScalar(double elevationScale) {
        this.elevationScalar = elevationScale > 0 ? elevationScale : 0.1;
    }

    public int xOffset() {
        return translateX;
    }

    public int yOffset() {
        return translateY;
    }

    public double getySkew() {
        return ySkew;
    }

    public void paintComponent(Graphics g) {
        this.graphics = g;
        g.setColor(Color.black);
        graphics.fillRect(0, 0, viewWidth, viewHeight);
        graphics.setColor(Color.green);
        for (int y = 0; y < surface.getYgridSize(); y++)
            for (int x = 0; x < surface.getXgridSize() - 1; x++) {
                draw(x, y, getySkew());

            }
    }

    public void setTranslateX(int translateX) {
        this.translateX = translateX;
    }

    public void setTranslateY(int translateY) {
        this.translateY = translateY;
    }

    public void setySkew(double value) {
        ySkew = value > 0 ? 0.1 : value;
    }

    public void translateDown(int value) {
        //Screen coords. Y-axis points down
        setTranslateY(yOffset() + value);
    }

    public void translateLeft(int value) {
        setTranslateX(xOffset() - value);
    }

    public void translateRight(int value) {
        setTranslateX(xOffset() + value);
    }

    public void translateUp(int value) {
        //Screen coords. Y-axis points down
        setTranslateY(yOffset() - value);
    }
}