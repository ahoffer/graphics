package org.example;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel{
    Surface3D surface=new Surface3D(50,50,150,-300,300);
    public DrawingPanel(){
    }
    public int originX=0;
    public int originY=0;
    public float ySkew=1.0f;
    public Iso3D iso3D= new Iso3D();
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,1000,1000);
        g.setColor(Color.blue);
        Point2D point1= null;
        Point2D point2= null;
        int x=2;
        int y=1;
        int xs=50;
        int ys=50;
        xs=surface.xSquareSize;
        ys=surface.ySquareSize;
        g.setColor(Color.green);
        for(y=0;y<surface.yGridSize;y++)
            for(x=0;x<surface.xGridSize;x++){
                try{
                    if(x<surface.xGridSize-1){
                        point1= new Point3D(x*xs,(int)(surface.getElevationAvg(x,y)*ySkew),y*ys).transform3D(iso3D);
                        point2= new Point3D((x+1)*xs,(int)(surface.getElevationAvg(x+1,y)*ySkew),y*ys).transform3D(iso3D);
                        g.drawLine(point1.x+originX,point1.y+originY,point2.x+originX,point2.y+originY);
                    }
                    if(y<surface.yGridSize-1){
                        point1= new Point3D(x*xs,(int)(surface.getElevationAvg(x,y)*ySkew),y*ys).transform3D(iso3D);
                        point2= new Point3D(x*xs,(int)(surface.getElevationAvg(x,y+1)*ySkew),(y+1)*ys).transform3D(iso3D);
                        g.drawLine(point1.x+originX,point1.y+originY,point2.x+originX,point2.y+originY);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    }
}