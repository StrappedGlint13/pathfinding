/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import datastructures.List;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import datastructures.Vertex;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * A Class for handling the images of the maps.
 * 
 * @author matibrax
 */

public class ImageHandler {
    Color colorDijkstra;
    Color colorAstar;
    Color visitedSameVertices;
    Color visitedDijkstra;
    Color visitedAstar;
    Color startingAndEndingPoints;
    int a = 0;
    public ImageHandler() { 
        colorDijkstra = new Color(0,0,204);
        colorAstar = new Color(255,0,0);
        visitedSameVertices = new Color(204,102,0);
        visitedAstar = new Color(255,175,175);
        visitedDijkstra = new Color(51,153,255);
        startingAndEndingPoints = new Color(0,0,204);
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
    
    public BufferedImage drawShortestPath(BufferedImage img, List shortestPathAStar, List shortestPathDijkstra, 
                boolean[][] visitedDijkstra, boolean[][] visitedAstar) {   
        for (int i = 1; i < shortestPathDijkstra.size() - 1; i++) {
            Vertex v = shortestPathDijkstra.getFromIndex(i);
            img = drawGrid(img, v, 0);   
        }
        img = drawVisited(img, visitedDijkstra, a);
        this.a = 1; // next A star
        for (int i = 1; i < shortestPathAStar.size() - 1; i++) {
            Vertex v = shortestPathAStar.getFromIndex(i);
            img = drawGrid(img, v, a);
        }
        
        img = drawVisited(img, visitedAstar, a);
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
        
        for (int drawRow = -1; drawRow < 2; drawRow++) {
            for (int drawCol = -1; drawCol < 2; drawCol++) {
                if (img.getRGB(v.getRow() - drawRow, v.getColumn() - drawCol) == Color.BLACK.getRGB()) {
                    continue;
                }
                img.setRGB(v.getRow() - drawRow, v.getColumn() - drawCol, pathColor.getRGB());
            }
        }
        return img;
    }
    
    public BufferedImage drawVisited(BufferedImage img, boolean[][] visited, int color) {
        Color visitedColor = new Color(0,0,0);
        if (color == 0) {
            visitedColor = visitedDijkstra;
        } else {
            visitedColor = visitedAstar;
        }
        
        for (int row = 0; row < visited.length; row++) {
            for (int col = 0; col < visited[0].length; col++) {
                if(!visited[row][col]) {
                    continue;
                }
                
                if (img.getRGB(row, col) == colorDijkstra.getRGB() || img.getRGB(row, col) == colorAstar.getRGB() ||
                       img.getRGB(row, col) == Color.BLACK.getRGB()) {
                    continue;
                }
                img.setRGB(row, col, visitedColor.getRGB());
            }
        }
        return img;
    }
    
    public BufferedImage makeNewFrame(BufferedImage img, int height, int width) {
        try {
            return img = resizeImage(img, height, width);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  
}
