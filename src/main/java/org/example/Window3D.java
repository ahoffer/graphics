package org.example;

import javax.swing.*;

public class Window3D extends JFrame {

    public static final int WIN_HEIGHT = 1200;
    public static final int WIN_WIDTH = 1600;
    final int squareSizeStep;
    final int translationStep;

    Window3D() {
        translationStep = 50;
        squareSizeStep = 1;
        JFrame aFrame = new JFrame("Isometric 3D Test");
        SurfaceModel surface = new RandomSurfaceModel(50, 50, 5);
        DrawingPanel drawingPanel = new DrawingPanel(surface);

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(ae -> {
                    surface.decOriginX(translationStep);
                    drawingPanel.repaint();
                }
        );

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(ae -> {
                    surface.incOriginY(translationStep);
                    drawingPanel.repaint();
                }
        );

        JButton upButton = new JButton("Up");
        upButton.addActionListener(ae -> {
                    surface.decOriginY(translationStep);
                    drawingPanel.repaint();
                }
        );
        drawingPanel.add(upButton);

        JButton downButton = new JButton("Down");


        downButton.addActionListener(ae -> {
                   surface.incOriginY(translationStep);
                    drawingPanel.repaint();
                }
        );
        drawingPanel.add(downButton);
        JButton yDecButton = new JButton("Y-");
        yDecButton.addActionListener(ae -> {
                    drawingPanel.ySkew -= 0.1f;
                    if (drawingPanel.ySkew == 0) drawingPanel.ySkew = -0.1f;
                    drawingPanel.repaint();
                }
        );
        JButton yIncButton = new JButton("Y+");
        yIncButton.addActionListener(ae -> {
                    drawingPanel.ySkew += 0.1f;
                    if (drawingPanel.ySkew == 0) drawingPanel.ySkew = 0.1f;
                    drawingPanel.repaint();
                }
        );

        JButton yInc2DButton = new JButton("Y Skew+");
        yInc2DButton.addActionListener(ae -> {
                    GlobalVars.ySkew += .1f;
                    if (drawingPanel.ySkew == 0.0f) GlobalVars.ySkew = -0.1f;
//                    System.err.println("Y skew=" + GlobalVars.ySkew);
                    drawingPanel.repaint();
                }
        );
        drawingPanel.add(yInc2DButton);
        JButton yDec2DButton = new JButton("Y Skew-");
        yDec2DButton.addActionListener(ae -> {
                    GlobalVars.ySkew -= .1f;
                    if (drawingPanel.ySkew == 0.0f) GlobalVars.ySkew = 0.1f;
                    drawingPanel.repaint();
                }
        );
        JButton zoomInButton = new JButton("Zoom+");
        zoomInButton.addActionListener(ae -> {
                    surface.incSquareSize(squareSizeStep);
                    drawingPanel.repaint();
                }
        );
        JButton zoomOutButton = new JButton("Zoom-");
        zoomOutButton.addActionListener(ae -> {
                    surface.decSquareSize(squareSizeStep);
                    drawingPanel.repaint();
                }
        );

        drawingPanel.add(leftButton);
        drawingPanel.add(rightButton);
        drawingPanel.add(upButton);
        drawingPanel.add(downButton);
        drawingPanel.add(yDecButton);
        drawingPanel.add(yIncButton);
        drawingPanel.add(yDec2DButton);
        drawingPanel.add(yInc2DButton);
        drawingPanel.add(zoomOutButton);
        drawingPanel.add(zoomInButton);

        aFrame.add(drawingPanel);
        aFrame.setSize(WIN_WIDTH, WIN_HEIGHT);
        aFrame.setDefaultCloseOperation(aFrame.EXIT_ON_CLOSE);
        aFrame.show();
    }

    public static void main(String[] args) {
        new Window3D();
    }
}