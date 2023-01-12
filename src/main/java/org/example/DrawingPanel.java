package org.example;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    public GlobalVars globalVars = new GlobalVars();
    public int originX = 0;
    public int originY = 500;
    public float ySkew = 1.0f;
    Surface3D surface;
    private Graphics grxContext;
    private int x;
    private int xs;
    private int y;
    private int ys;

    public DrawingPanel() {
        x = 2;
        y = 1;
        xs = 50;
        ys = 50;
        surface = new Surface3D(50, 50, 150, -300, 300);
    }

    private void drawGrid() {
        Point2D point2;
        Point2D point1;
        point1 = new Point3D(x * xs, (int) (surface.getElevationAvg(x, y) * ySkew), y * ys).transform3D(globalVars);
        point2 = new Point3D(x * xs, (int) (surface.getElevationAvg(x, y + 1) * ySkew), (y + 1) * ys).transform3D(globalVars);
        grxContext.drawLine(point1.x + originX, point1.y + originY, point2.x + originX, point2.y + originY);
//        System.err.printf("y-line from %s -> %s%n", point1, point2);
    }

    private void drawNoise() {
        Point2D point1;
        Point2D point2;
        point1 = new Point3D(x * xs, (int) (surface.getElevationAvg(x, y) * ySkew), y * ys).transform3D(globalVars);
        point2 = new Point3D((x + 1) * xs, (int) (surface.getElevationAvg(x + 1, y) * ySkew), y * ys).transform3D(globalVars);
        grxContext.drawLine(point1.x + originX, point1.y + originY, point2.x + originX, point2.y + originY);
//        System.err.printf("noise line from %s -> %s%n", point1, point2);
    }

    public void paintComponent(Graphics g) {
        this.grxContext = g;
        g.setColor(Color.black);
        grxContext.fillRect(0, 0, 1000, 1000);
        grxContext.setColor(Color.blue);
        Point2D point1 = null;
        Point2D point2 = null;
        xs = surface.xSquareSize;
        ys = surface.ySquareSize;
        grxContext.setColor(Color.green);
        for (y = 0; y < surface.yGridSize; y++)
            for (x = 0; x < surface.xGridSize; x++) {
                if (x < surface.xGridSize - 1) {
                    drawNoise();
                }
//                if (y < surface.yGridSize - 1) {
//                    drawGrid();
//                }
            }
    }
}