package org.example;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private final int viewHeight;
    private final int viewWidth;
    private double elevationScalar = 1.0;
    private Graphics graphics;
    private final SurfaceModel surface;
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
        point1 = new Point3D(x, surface.getElevation(x, y), y)
                .scale(courseness, elevationScalar(), courseness)
                .transformToIso(ySkew);
        point2 = new Point3D(
                (x + 1), surface.getElevation(x + 1, y), y)
                .scale(courseness, elevationScalar(), courseness)
                .transformToIso(ySkew);
//System.err.println(String.format("%s %s", point1,point2));
        point1.translate(xOffset(), yOffset());
        point2.translate(xOffset(), yOffset());
        graphics.drawLine(
                (int) point1.x,
                (int) point1.y,
                (int) point2.x,
                (int) point2.y);
    }

    public double elevationScalar() {
        return elevationScalar;
    }

    public void elevationScalar(double elevationScale) {
        this.elevationScalar = elevationScale > 0 ? elevationScale : 0.1;
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
        ySkew = value > 0 ? value : 0.1;
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

    public int xOffset() {
        return translateX;
    }

    public int yOffset() {
        return translateY;
    }
}