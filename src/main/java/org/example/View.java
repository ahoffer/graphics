package org.example;

import javax.swing.*;
import java.awt.*;

import static org.example.Window3D.WIN_HEIGHT;
import static org.example.Window3D.WIN_WIDTH;

public class View extends JPanel {
    public float scaleElevation = 1.0f;
    SurfaceModel surface;
    private Graphics grxContext;
    private int translateX = 0;
    private int translateY = 0;


    public View(SurfaceModel surface) {
        this.surface = surface;
    }

    public void decOriginX(int value) {
        setTranslateX(getTranslateX() - value);
    }

    public void decOriginY(int value) {
        setTranslateY(getTranslateY() - value);
    }

    private void drawNoise(int x, int y) {
        Point2D point1;
        Point2D point2;
        int xs = surface.getSquareSize();
        int ys = surface.getSquareSize();
        point1 = new Point3D(x * xs, (int) (surface.getElevation(x, y) * scaleElevation), y * ys).transformToIso();
        point2 = new Point3D((x + 1) * xs, (int) (surface.getElevation(x + 1, y) * scaleElevation), y * ys).transformToIso();
        grxContext.drawLine(point1.x + getTranslateX(), point1.y + getTranslateY(), point2.x + getTranslateX(), point2.y + getTranslateY());
//        System.err.printf("noise line from %s -> %s%n", point1, point2);
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public void incOriginX(int value) {
        setTranslateX(getTranslateX() + value);

    }

    public void incOriginY(int value) {
        setTranslateY(getTranslateY() + value);
    }

    public void paintComponent(Graphics g) {
        this.grxContext = g;
        g.setColor(Color.black);
        grxContext.fillRect(0, 0, WIN_WIDTH, WIN_HEIGHT);
        grxContext.setColor(Color.green);
        for (int y = 0; y < surface.getYgridSize(); y++)
            for (int x = 0; x < surface.getXgridSize(); x++) {
                if (x < surface.getXgridSize() - 1) {
                    drawNoise(x, y);
                }
            }
    }

    public void setTranslateX(int translateX) {
        this.translateX = translateX;
    }

    public void setTranslateY(int translateY) {
        this.translateY = translateY;
    }
}