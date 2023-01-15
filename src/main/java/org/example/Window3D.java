package org.example;

import javax.swing.*;

public class Window3D extends JFrame {

    static final JFrame frame = new JFrame(Window3D.class.getName());
    boolean DEBUG;
    double elevationScaleStep;
    int isoMode;
    int squareSizeStep;
    transient SurfaceModel surface;
    int translationStep;
    View view;
    int windowHeight;
    int windowWidth;
    double ySkewStep;

    Window3D() {
        isoMode = 1;
        DEBUG = false;
        windowWidth = 1024;
        windowHeight = 768;
        translationStep = 50;
        squareSizeStep = 1;
        elevationScaleStep = 0.3;
        ySkewStep = 0.05;
        surface = new RandomSurfaceModel(50, 50, 13);
        view = new View(surface, windowWidth, windowHeight);
        setupButtons();
        centerObject();
        openWindow();
    }

    public static void main(String[] args) {
        new Window3D();
    }

    void adjustElevationDown() {
        view.adjustElevationScale(-elevationScaleStep);
    }

    void adjustElevationUp() {
        view.adjustElevationScale(elevationScaleStep);
    }

    void centerObject() {
        int amountTranslateOriginToCenterObjectHorizontally =
                surface.getSquareSize() * surface.getXgridSize();
        view.setTranslateX(windowWidth / 2 - amountTranslateOriginToCenterObjectHorizontally);
        view.setTranslateY(windowHeight / 2);
    }

    JButton createButton(String name, Runnable callback, Object debugMsg) {
        JButton button = new JButton(name);
        button.addActionListener(
                ae -> {
                    callback.run();
                    view.repaint();
                    debugPrintln(debugMsg);
                });
        return button;
    }

    void debugPrintln(Object msg) {
        // Ignore nulls and empty Strings
        if (msg == null || msg instanceof String && !((String) msg).isBlank()) {
            return;
        }
        System.err.println(msg);
    }

    void decreaseYskew() {
        view.adjustYskew(-ySkewStep);
    }

    void increaseYskew() {
        view.adjustYskew(ySkewStep);
    }

    void openWindow() {
        frame.add(view);
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void setupButtons() {
        view.add(createButton("Left", this::translateLeft, "Origin X=" + view.xOffset()));
        view.add(createButton("Right", this::translateRight, "Origin X=" + view.xOffset()));
        view.add(createButton("Up", this::translateUp, "Origin Y=" + view.xOffset()));
        view.add(createButton("Down", this::translateDown, "Origin Y=" + view.yOffset()));
        view.add(
                createButton(
                        "Elevation -",
                        this::adjustElevationDown,
                        "Elevation Scalar=" + view.elevationScalar()));
        view.add(
                createButton(
                        "Elevation +",
                        this::adjustElevationUp,
                        "Elevation Scalar=" + view.elevationScalar()));
        view.add(createButton("Y Skew -", this::decreaseYskew, "Y Skew=" + view.getYskew()));
        view.add(createButton("Y Skew +", this::increaseYskew, "Y Skew=" + view.getYskew()));
        view.add(createButton("Zoom-", this::zoomOut, "Square Size=" + surface.getSquareSize()));
        view.add(createButton("Zoom+", this::zoomIn, "Square Size=" + surface.getSquareSize()));
    }

    void translateDown() {
        view.translateDown(translationStep);
    }

    void translateLeft() {
        view.translateLeft(translationStep);
    }

    void translateRight() {
        view.translateRight(translationStep);
    }

    void translateUp() {
        view.translateUp(translationStep);
    }

    void zoomIn() {
        surface.incSquareSize(squareSizeStep);
    }

    void zoomOut() {
        surface.decSquareSize(squareSizeStep);
    }
}
