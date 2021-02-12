/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import utils.Vertex;

/**
 *
 * A Class for handling the images of the maps.
 * 
 * @author matibrax
 */

public class ImageHandler {
    Color colorDijkstra;
    Color colorAstar;
    public ImageHandler() {  
        colorDijkstra = new Color(255, 178, 102);
        colorAstar = new Color(255, 51, 51);
    }
   
    /**
    * Method resizes the BufferedImage. 
    *
    * @param img given BufferedImage.
    * @param width target width.
    * @param heigth target height.
    * 
    * @return resized BufferedImage.
    */
    
    public BufferedImage resizeImage(BufferedImage img, int width, int heigth) throws Exception {
        BufferedImage resizedImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
        Graphics2D writer = resizedImage.createGraphics();
        writer.drawImage(img, 0, 0, width, heigth, null);
        writer.dispose();
        return resizedImage;
    }

    /**
    * Method draws a shortest path. 
    *
    * @param img given BufferedImage.
    * @param shortestPathAStar list of vertex from the A* algorithm.
    * @param shortestPathDijkstra  list of vertex from the Dijkstra algorithm.
    * 
    * @return BufferedImage, where are shortest paths drawn.
    */
    
    public BufferedImage drawShortestPath(BufferedImage img, ArrayList<Vertex> shortestPathAStar, ArrayList<Vertex> shortestPathDijkstra) {   
        int d = 0;
        int a = 1;
        for (int i = 1; i < shortestPathDijkstra.size() - 1; i++) {
            Vertex v = shortestPathDijkstra.get(i);
            //img = draw(img, v);
            img = drawGrid(img, v, d);
        }
        
        
        for (int i = 1; i < shortestPathAStar.size() - 1; i++) {
            Vertex v = shortestPathAStar.get(i);
            //img = draw(img, v);
            img = drawGrid(img, v, a);
        }
        
        return img;
    }
   
    /**
    * Method draws a shortest path with 3x3 pixel-maze. 
    *
    * @param img given BufferedImage.
    * @param v pixel-vertex that is being manipulated.
    * @param color colour that is set for specific algorithm.
    * 
    * @return BufferedImage, with the grid line of shortest path. 
    */
  
    public BufferedImage drawGrid(BufferedImage img, Vertex v, int color) {
        Color pathColor = new Color(0,0,0);
        if (color == 0) {
            pathColor = colorDijkstra;
        } else {
            pathColor = colorAstar;
        }
        
        int black = new Color(0, 0, 0).getRGB();
        for (int drawRow = -1; drawRow < 2; drawRow++) {
            for (int drawCol = -1; drawCol < 2; drawCol++) {
                if (img.getRGB(v.getRow() - drawRow, v.getColumn() - drawCol) == black) {
                    continue;
                }
                img.setRGB(v.getRow() - drawRow, v.getColumn() - drawCol, pathColor.getRGB());
            }
        }
        return img;
    }
  
}
