package org.example;

import javax.swing.*;

public class Window3D extends JFrame {

    public static final int WIN_HEIGHT = 1200;
    public static final int WIN_WIDTH = 1600;
    public static boolean DEBUG = true;
    final int squareSizeStep;
    final int translationStep;

    Window3D() {
        translationStep = 50;
        squareSizeStep = 1;
        JFrame aFrame = new JFrame("Isometric 3D Test");
        SurfaceModel surface = new RandomSurfaceModel(50, 50, 13);
        View view = new View(surface);
        int amountTranslateOriginToCenterObjectHorizontally = surface.getSquareSize() * surface.getXgridSize();
        view.setTranslateX(WIN_WIDTH / 2 - amountTranslateOriginToCenterObjectHorizontally);
        view.setTranslateY(WIN_HEIGHT / 2);

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(ae -> {
                    view.decOriginX(translationStep);
                    debugPrintln("Origin X=" + view.getTranslateX());
                    view.repaint();
                }
        );

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(ae -> {
                    view.incOriginX(translationStep);
                    debugPrintln("Origin X=" + view.getTranslateX());
                    view.repaint();
                }
        );

        JButton upButton = new JButton("Up");
        upButton.addActionListener(ae -> {
                    view.decOriginY(translationStep);
                    debugPrintln("Origin Y=" + view.getTranslateX());
                    view.repaint();
                }
        );

        JButton downButton = new JButton("Down");
        downButton.addActionListener(ae -> {
                    view.incOriginY(translationStep);
                    debugPrintln("Origin Y=" + view.getTranslateY());
                    view.repaint();
                }
        );

        JButton yDecButton = new JButton("Y-");
        yDecButton.addActionListener(ae -> {
                    view.scaleElevation -= 0.1f;
                    if (view.scaleElevation == 0) view.scaleElevation = -0.1f;
                    debugPrintln("Drawing Panel Y Skew=" + view.scaleElevation);

                    view.repaint();
                }
        );

        JButton yIncButton = new JButton("Y+");
        yIncButton.addActionListener(ae -> {
                    view.scaleElevation += 0.1f;
                    if (view.scaleElevation == 0) view.scaleElevation = 0.1f;
                    debugPrintln("Drawing Panel Y Skew=" + view.scaleElevation);
                    view.repaint();
                }
        );

        JButton yInc2DButton = new JButton("Y Skew+");
        yInc2DButton.addActionListener(ae -> {
                    GlobalVars.ySkew += .1f;
                    if (view.scaleElevation == 0.0f) GlobalVars.ySkew = -0.1f;
                    debugPrintln("Global Panel Y Skew=" + GlobalVars.ySkew);
                    view.repaint();
                }
        );

        JButton yDec2DButton = new JButton("Y Skew-");
        yDec2DButton.addActionListener(ae -> {
                    GlobalVars.ySkew -= .1f;
                    if (view.scaleElevation == 0.0f) GlobalVars.ySkew = 0.1f;
                    debugPrintln("Global Skew Y Skew=" + GlobalVars.ySkew);
                    view.repaint();
                }
        );
        JButton zoomInButton = new JButton("Zoom+");
        zoomInButton.addActionListener(ae -> {
                    surface.incSquareSize(squareSizeStep);
                    debugPrintln("Square Size=" + surface.getSquareSize());
                    view.repaint();
                }
        );

        JButton zoomOutButton = new JButton("Zoom-");
        zoomOutButton.addActionListener(ae -> {
                    surface.decSquareSize(squareSizeStep);
                    debugPrintln("Square Size=" + surface.getSquareSize());
                    view.repaint();
                }
        );

        view.add(leftButton);
        view.add(rightButton);
        view.add(upButton);
        view.add(downButton);
        view.add(yDecButton);
        view.add(yIncButton);
        view.add(yDec2DButton);
        view.add(yInc2DButton);
        view.add(zoomOutButton);
        view.add(zoomInButton);

        aFrame.add(view);
        aFrame.setSize(WIN_WIDTH, WIN_HEIGHT);
        aFrame.setDefaultCloseOperation(aFrame.EXIT_ON_CLOSE);
        aFrame.show();
    }

    public static void main(String[] args) {
        new Window3D();
    }

    void debugPrintln(Object msg) {
        if (DEBUG) System.err.println(msg);
    }
}