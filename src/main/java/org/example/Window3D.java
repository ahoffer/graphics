package org.example;

import javax.swing.*;

public class Window3D extends JFrame {

    public static final int WIN_HEIGHT = 1200;
    public static final int WIN_WIDTH = 1600;
    public static boolean DEBUG = true;
    int squareSizeStep;
    int translationStep;
    private SurfaceModel surface;
    private View view;

    Window3D() {
        translationStep = 50;
        squareSizeStep = 1;
        JFrame aFrame = new JFrame("Isometric 3D Test");
        surface = new RandomSurfaceModel(50, 50, 13);
        view = new View(surface);
        int amountTranslateOriginToCenterObjectHorizontally = surface.getSquareSize() * surface.getXgridSize();
        view.setTranslateX(WIN_WIDTH / 2 - amountTranslateOriginToCenterObjectHorizontally);
        view.setTranslateY(WIN_HEIGHT / 2);

        JButton leftButton = createButton("Left", this::translateLeft);
        JButton rightButton = createButton("Right", this::translateRight);
        JButton upButton = createButton("Up", this::translateUp);
        JButton downButton = createButton("Down", this::translateDown);
        JButton yDecButton = createButton("Scale Down", this::scaleDown);
        JButton yIncButton = createButton("Scale Up", this::scaleUp);
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

    private void rotateMinus() {
        GlobalVars.ySkew -= .1f;
        if (view.getScaleElevation() == 0.0f) GlobalVars.ySkew = 0.1f;
        debugPrintln("Global Skew Y Skew=" + GlobalVars.ySkew);
    }

    private void rotatePlus() {
        GlobalVars.ySkew += .1f;
        if (view.getScaleElevation() == 0.0f) GlobalVars.ySkew = -0.1f;
        debugPrintln("Global Panel Y Skew=" + GlobalVars.ySkew);
        view.repaint();
    }

    private void scaleDown() {
        view.setScaleElevation(view.getScaleElevation() - 0.1f);
        if (view.getScaleElevation() == 0) view.setScaleElevation(-0.1f);
        debugPrintln("Drawing Panel Y Skew=" + view.getScaleElevation());
    }

    private void scaleUp() {
        view.setScaleElevation(view.getScaleElevation() + 0.1f);
        if (view.getScaleElevation() == 0) view.setScaleElevation(0.1f);
        debugPrintln("Drawing Panel Y Skew=" + view.getScaleElevation());
    }

    private void translateDown() {
        view.incOriginY(translationStep);
        debugPrintln("Origin Y=" + view.getTranslateY());
    }

    private void translateLeft() {
        view.translateLeft(translationStep);
        debugPrintln("Origin X=" + view.getTranslateX());
    }

    private void translateRight() {
        view.translateRight(translationStep);
        debugPrintln("Origin X=" + view.getTranslateX());
    }

    private void translateUp() {
        view.translateUp(translationStep);
        debugPrintln("Origin Y=" + view.getTranslateX());
    }

    private void zoomIn() {
        surface.incSquareSize(squareSizeStep);
        debugPrintln("Square Size=" + surface.getSquareSize());
    }

    private void zoomOut() {
        surface.decSquareSize(squareSizeStep);
        debugPrintln("Square Size=" + surface.getSquareSize());
        view.repaint();
    }
}