package org.example;

import javax.swing.*;

public class Window3D extends JFrame {

    final JFrame frame;
    boolean DEBUG;
    double elevationScaleStep;
    int isoMode;
    int squareSizeStep;
    SurfaceModel surface;
    int translationStep;
    View view;
    int windowHeight;
    int windowWidth;

    Window3D() {
        isoMode = 1;
        DEBUG = true;
        windowWidth = 1024;
        windowHeight = 768;
        translationStep = 50;
        squareSizeStep = 1;
        elevationScaleStep = 0.3;
        surface = new RandomSurfaceModel(50, 50, 13);
        view = new View(surface, windowWidth, windowHeight);
        frame = new JFrame("Isometric 3D Test");
        centerObject();
        setupButtons();
        openWindow();
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

    void centerObject() {
        int amountTranslateOriginToCenterObjectHorizontally = surface.getSquareSize() * surface.getXgridSize();
        view.setTranslateX(windowWidth / 2 - amountTranslateOriginToCenterObjectHorizontally);
        view.setTranslateY(windowHeight / 2);
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

    void openWindow() {
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void rotateMinus() {
        view.adjustYskew(0.1);
        debugPrintln("Global Skew Y Skew=" + view.getySkew());
    }

    void rotatePlus() {
        view.adjustYskew(0.1);
        debugPrintln("Global Panel Y Skew=" + view.getySkew());
    }

    void setupButtons() {
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
        frame.add(view);
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