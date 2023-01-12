package org.example;

import javax.swing.*;
import java.awt.*;

import static org.example.Window3D.WIN_HEIGHT;
import static org.example.Window3D.WIN_WIDTH;

public class DrawingPanel extends JPanel {

    public float ySkew = 1.0f;
    SurfaceModel surface;
    private Graphics grxContext;


    public DrawingPanel(SurfaceModel surface) {
        this.surface = surface;
    }


    private void drawNoise(int x, int y) {
        Point2D point1;
        Point2D point2;
        int xs = surface.getSquareSize();
        int ys = surface.getSquareSize();
        point1 = new Point3D(x * xs, (int) (surface.getElevation(x, y) * ySkew), y * ys).transformToIso();
        point2 = new Point3D((x + 1) * xs, (int) (surface.getElevation(x + 1, y) * ySkew), y * ys).transformToIso();
        grxContext.drawLine(point1.x + surface.getOriginX(), point1.y + surface.getOriginY(), point2.x + surface.getOriginX(), point2.y + surface.getOriginY());
//        System.err.printf("noise line from %s -> %s%n", point1, point2);
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
}