package org.example;

import javax.swing.*;

public class Window3D extends JFrame {

    public static final int WIN_HEIGHT = 1200;
    public static final int WIN_WIDTH = 1600;
    final double elevationScaleStep = 0.3;
    boolean DEBUG = true;
    int squareSizeStep;
    SurfaceModel surface;
    int translationStep;
    View view;

    Window3D() {
        translationStep = 50;
        squareSizeStep = 1;
        JFrame aFrame = new JFrame("Isometric 3D Test");
        surface = new RandomSurfaceModel(50, 50, 13);
        view = new View(surface, WIN_WIDTH, WIN_HEIGHT);
        int amountTranslateOriginToCenterObjectHorizontally = surface.getSquareSize() * surface.getXgridSize();
        view.setTranslateX(WIN_WIDTH / 2 - amountTranslateOriginToCenterObjectHorizontally);
        view.setTranslateY(WIN_HEIGHT / 2);

        JButton leftButton = createButton("Left", this::translateLeft);
        JButton rightButton = createButton("Right", this::translateRight);
        JButton upButton = createButton("Up", this::translateUp);
        JButton downButton = createButton("Down", this::translateDown);
        JButton yDecButton = createButton("Scale Down", this::adjustElevationDown);
        JButton yIncButton = createButton("Scale Up", this::adjustElevationUp);
        JButton yInc2DButton = createButton("Rotate +", this::rotatePlus);
        JButton yDec2DButton = createButton("Rotate -", this::rotateMinus);
        JButton zoomInButton = createButton("Zoom+", this::zoomIn);
        JButton zoomOutButton = createButton("Zoom-", this::zoomOut);

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
        aFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Window3D();
    }

    void adjustElevationDown() {
        view.adjustElevationScale(-elevationScaleStep);
        debugPrintln("Drawing Panel Y Skew=" + view.elevationScalar());
    }

    void adjustElevationUp() {
        view.adjustElevationScale(elevationScaleStep);
        debugPrintln("Drawing Panel Y Skew=" + view.elevationScalar());
    }

    JButton createButton(String name, Runnable callback) {
        JButton button = new JButton(name);
        button.addActionListener((ae) -> {
            callback.run();
            view.repaint();
        });
        return button;
    }

    void debugPrintln(Object msg) {
        if (DEBUG) System.err.println(msg);
    }

    void rotateMinus() {
        GlobalVars.ySkew -= .1f;
        if (view.elevationScalar() == 0.0f) GlobalVars.ySkew = 0.1f;
        debugPrintln("Global Skew Y Skew=" + GlobalVars.ySkew);
    }

    void rotatePlus() {
        GlobalVars.ySkew += .1f;
        if (view.elevationScalar() == 0.0f) GlobalVars.ySkew = -0.1f;
        debugPrintln("Global Panel Y Skew=" + GlobalVars.ySkew);
        view.repaint();
    }

    void translateDown() {
        view.translateDown(translationStep);
        debugPrintln("Origin Y=" + view.getTranslateY());
    }

    void translateLeft() {
        view.translateLeft(translationStep);
        debugPrintln("Origin X=" + view.getTranslateX());
    }

    void translateRight() {
        view.translateRight(translationStep);
        debugPrintln("Origin X=" + view.getTranslateX());
    }

    void translateUp() {
        view.translateUp(translationStep);
        debugPrintln("Origin Y=" + view.getTranslateX());
    }

    void zoomIn() {
        surface.incSquareSize(squareSizeStep);
        debugPrintln("Square Size=" + surface.getSquareSize());
    }

    void zoomOut() {
        surface.decSquareSize(squareSizeStep);
        debugPrintln("Square Size=" + surface.getSquareSize());
        view.repaint();
    }
}