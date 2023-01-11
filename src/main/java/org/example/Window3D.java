package org.example;
import javax.swing.*;
import java.awt.event.*;

public class Window3D extends JFrame {
    Window3D(){
        JFrame aFrame=new JFrame("Isometric 3D Test");
        int step=50;
        DrawingPanel drawingPanel =new DrawingPanel();
        JButton mode0Button = new JButton("Mode Left");
        mode0Button.addActionListener(ae -> {
            drawingPanel.iso3D.mode=0;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(mode0Button);
        JButton mode1Button = new JButton("Mode Center");
        mode1Button.addActionListener(ae -> {
            drawingPanel.iso3D.mode=1;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(mode1Button);
        JButton mode2Button = new JButton("Mode Right");
        mode2Button.addActionListener(ae -> {
            drawingPanel.iso3D.mode=2;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(mode2Button);

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(ae -> {
            drawingPanel.originX-=step;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(leftButton);
        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(ae -> {
            drawingPanel.originX+=step;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(rightButton);
        JButton upButton = new JButton("Up");
        upButton.addActionListener(ae -> {
            drawingPanel.originY-=step;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(upButton);
        JButton downButton = new JButton("Down");
        downButton.addActionListener(ae -> {
            drawingPanel.originY+=step;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(downButton);
        JButton yIncButton = new JButton("Y+");
        yIncButton.addActionListener(ae -> {
            drawingPanel.ySkew-=0.1f;
            if(drawingPanel.ySkew==0) drawingPanel.ySkew=-0.1f;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(yIncButton);
        JButton yDecButton = new JButton("Y-");
        yDecButton.addActionListener(ae -> {
            drawingPanel.ySkew+=0.1f;
            if(drawingPanel.ySkew==0) drawingPanel.ySkew=0.1f;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(yDecButton);

        JButton yInc2DButton = new JButton("2D Y+");
        yInc2DButton.addActionListener(ae -> {
            drawingPanel.iso3D.ySkew-=.1f;
            if(drawingPanel.ySkew==0.0f) drawingPanel.iso3D.ySkew=-0.1f;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(yInc2DButton);
        JButton yDec2DButton = new JButton("2D Y-");
        yDec2DButton.addActionListener(ae -> {
            drawingPanel.iso3D.ySkew+=.1f;
            if(drawingPanel.ySkew==0.0f) drawingPanel.iso3D.ySkew=0.1f;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(yDec2DButton);
        JButton zoomInButton = new JButton("Zoom+");
        zoomInButton.addActionListener(ae -> {
            drawingPanel.surface.xSquareSize-=1;
            drawingPanel.surface.ySquareSize-=1;
            if(drawingPanel.surface.xSquareSize<=0) drawingPanel.surface.xSquareSize=1;
            if(drawingPanel.surface.ySquareSize<=0) drawingPanel.surface.ySquareSize=1;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(zoomInButton);
        JButton zoomOutButton = new JButton("Zoom-");
        zoomOutButton.addActionListener(ae -> {
            drawingPanel.surface.xSquareSize+=1;
            drawingPanel.surface.ySquareSize+=1;
            drawingPanel.repaint();
        }
        );
        drawingPanel.add(zoomOutButton);
        aFrame.add(drawingPanel);
        aFrame.setSize(1000,800);
        aFrame.setDefaultCloseOperation(aFrame.EXIT_ON_CLOSE);
        aFrame.show();
    }
    public static void main(String args[]){
        new Window3D();
    }
}