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
        debugPrintln("Elevation Scalar=" + view.elevationScalar());
    }

    void adjustElevationUp() {
        view.adjustElevationScale(elevationScaleStep);
        debugPrintln("Elevation Scalar=" + view.elevationScalar());
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

    void decreaseYskew() {
        view.adjustYskew(-0.1);
        debugPrintln("Y Skew=" + view.getySkew());
    }

    void increaseYskew() {
        view.adjustYskew(0.1);
        debugPrintln("Y Skew=" + view.getySkew());
    }

    void openWindow() {
        frame.add(view);
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void setupButtons() {
        view.add(createButton("Left", this::translateLeft));
        view.add(createButton("Right", this::translateRight));
        view.add(createButton("Up", this::translateUp));
        view.add(createButton("Down", this::translateDown));
        view.add(createButton("Scale DownY", this::adjustElevationDown));
        view.add(createButton("Scale Up", this::adjustElevationUp));
        view.add(createButton("Y Skew -", this::decreaseYskew));
        view.add(createButton("Y Skew +", this::increaseYskew));
        view.add(createButton("Zoom-", this::zoomOut));
        view.add(createButton("Zoom+", this::zoomIn));
    }

    void translateDown() {
        view.translateDown(translationStep);
        debugPrintln("Origin Y=" + view.yOffset());
    }

    void translateLeft() {
        view.translateLeft(translationStep);
        debugPrintln("Origin X=" + view.xOffset());
    }

    void translateRight() {
        view.translateRight(translationStep);
        debugPrintln("Origin X=" + view.xOffset());
    }

    void translateUp() {
        view.translateUp(translationStep);
        debugPrintln("Origin Y=" + view.xOffset());
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