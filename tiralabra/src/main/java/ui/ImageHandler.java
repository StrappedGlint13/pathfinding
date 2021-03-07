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
    Color dijkstraPath;
    Color astarPath;
    Color samePathColor;
    Color jpsPath;
    Color visitedDijkstra;
    Color visitedAstar;
    Color startingAndEndingPoints;
    int a = 0;
    public ImageHandler() { 
        dijkstraPath = new Color(0,0,204);
        astarPath = new Color(255,0,0);
        jpsPath = new Color(255,255,0);
        samePathColor = new Color(255,0,255);
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
                List shortestPathJPS, boolean[][] visitedDijkstra, boolean[][] visitedAstar, boolean[][] visitedJPS) {   
        for (int i = 0; i < shortestPathDijkstra.size() - 1; i++) {
            Vertex d = shortestPathDijkstra.getFromIndex(i);
            Vertex as = shortestPathAStar.getFromIndex(i);
            Vertex jps = shortestPathJPS.getFromIndex(i);
            img = drawGrid(img, d, as, jps);   
        }
        img = drawVisited(img, visitedDijkstra, a);
        this.a = 1; // next A star
        img = drawVisited(img, visitedAstar, a);
        this.a = 2;
        img = drawVisited(img, visitedJPS, a);
        return img;
    }
   
   
    /**
    * Method draws a shortest path with 3x3 pixel-maze. 
    *
    * @param img given BufferedImage.
    * @param d pixel-vertex that is being manipulated.
    * @param color colour that is set for specific algorithm.
    * 
    * @return BufferedImage, with the grid line of shortest path. 
    */
  
    public BufferedImage drawGrid(BufferedImage img, Vertex d, Vertex a, Vertex jps) {
        for (int drawRow = -1; drawRow < 2; drawRow++) {
            for (int drawCol = -1; drawCol < 2; drawCol++) {
                int currentRowD = d.getRow() - drawRow;
                int currentColD = d.getColumn()- drawCol;
                
                int currentRowA = a.getRow() - drawRow;
                int currentColA = a.getColumn()- drawCol;
                
                int currentRowJPS = jps.getRow() - drawRow;
                int currentColJPS = jps.getColumn()- drawCol;
                /*
                if (currentRowD == currentRowA && currentColD == currentColA) {
                    if (img.getRGB(currentRowD, currentColD) != Color.BLACK.getRGB()) {
                    img.setRGB(d.getRow() - drawRow, d.getColumn() - drawCol, samePathColor.getRGB());
                    }
                } else {
                    if (img.getRGB(currentRowD, currentColD) != Color.BLACK.getRGB()) {
                        img.setRGB(currentRowD, currentColD, dijkstraPath.getRGB());
                    }
                    if (img.getRGB(currentRowA, currentColA) != Color.BLACK.getRGB()) {
                        img.setRGB(currentRowA, currentColA, astarPath.getRGB());
                    }*/
                    if (img.getRGB(currentRowJPS, currentColJPS) != Color.BLACK.getRGB()) {
                        img.setRGB(currentRowJPS, currentColJPS, jpsPath.getRGB());
                    
                }
            }
        }
        return img;
    }
    
    public BufferedImage drawVisited(BufferedImage img, boolean[][] visited, int color) {
        Color visitedColor = new Color(0,0,0);
        if (color == 0) {
            visitedColor = visitedDijkstra;
        } else if (color == 1){
            visitedColor = visitedAstar;
        } else {
            visitedColor = jpsPath;
        }
        
        for (int row = 0; row < visited.length; row++) {
            for (int col = 0; col < visited[0].length; col++) {
                if(!visited[row][col]) {
                    continue;
                }
                
                if (img.getRGB(row, col) == samePathColor.getRGB() || img.getRGB(row, col) == dijkstraPath.getRGB() || img.getRGB(row, col) == astarPath.getRGB()
                        || img.getRGB(row, col) == jpsPath.getRGB()) {
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
