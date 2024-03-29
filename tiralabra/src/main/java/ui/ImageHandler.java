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
    Color visitedJps;
    int colorIndex = 0;
    int visitedD;
    int visitedA;
    int visitedJPS;

    /**
     *
     */
    public ImageHandler() { 
        dijkstraPath = new Color(0,0,204);
        astarPath = new Color(255,0,0);
        jpsPath = new Color(255,255,0);
        samePathColor = new Color(255,0,255);
        visitedAstar = new Color(255,175,175);
        visitedDijkstra = new Color(51,153,255);
        visitedJps = new Color(255,102,255);
        startingAndEndingPoints = new Color(0,0,204);
        visitedA = 0;
        visitedJPS = 0;
        visitedD = 0;
        
    }
   
    /**
    * Method resizes the BufferedImage. 
    *
    * @param img given BufferedImage.
    * @param width target width.
    * @param heigth target height.
    * 
    * @return resized BufferedImage.
     * @throws java.lang.Exception
    */
    
    public BufferedImage resizeImage(BufferedImage img, int width, int heigth) throws Exception {
        BufferedImage resizedImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
        Graphics2D writer = resizedImage.createGraphics();
        writer.drawImage(img, 0, 0, width, heigth, null);
        writer.dispose();
        return resizedImage;
    }

    /**
    * Method draws colorIndex shortest path. 
    *
    * @param img given BufferedImage.
    * @param shortestPathAStar list of vertex from the A* algorithm.
    * @param shortestPathDijkstra  list of vertex from the Dijkstra algorithm.
     * @param shortestPathJPS
     * @param visitedAstar
     * @param visitedDijkstra
     * @param visitedJPS
    * 
    * @return BufferedImage, where are shortest paths drawn.
    */
    
    public BufferedImage drawShortestPath(BufferedImage img, List shortestPathAStar, List shortestPathDijkstra, 
                List shortestPathJPS, boolean[][] visitedDijkstra, boolean[][] visitedAstar, boolean[][] visitedJPS) {   
        img = drawVisited(img, visitedDijkstra, colorIndex);
        this.colorIndex = 1;
        img = drawVisited(img, visitedAstar, colorIndex);
        this.colorIndex = 2;
        img = drawVisited(img, visitedJPS, colorIndex);
        
        for (int i = 0; i < shortestPathDijkstra.size() - 1; i++) {
            Vertex d = shortestPathDijkstra.getFromIndex(i);
            Vertex as = shortestPathAStar.getFromIndex(i);
            img = drawGrid(img, d, as);
        }
        
        for (int i = 0; i < shortestPathJPS.size() - 1; i++) {
            Vertex jps = shortestPathJPS.getFromIndex(i);
            img = drawGridJPS(img, jps);
        }
        return img;
    }
    
    /**
     *
     * @param img
     * @param jps
     * @return
     */
    public BufferedImage drawGridJPS(BufferedImage img, Vertex jps) {
        for (int drawRow = -1; drawRow < 2; drawRow++) {
            for (int drawCol = -1; drawCol < 2; drawCol++) {
                int currentRowJPS = jps.getRow() - drawRow;
                int currentColJPS = jps.getColumn()- drawCol;
              
                if (img.getRGB(currentRowJPS, currentColJPS) != Color.BLACK.getRGB() && img.getRGB(currentRowJPS, currentColJPS) != samePathColor.getRGB()) {
                    img.setRGB(currentRowJPS, currentColJPS, jpsPath.getRGB());
                }
            }
        }
        return img;
    }
   
   
    /**
    * Method draws colorIndex shortest path with 3x3 pixel-maze. 
    *
    * @param img given BufferedImage.
    * @param d pixel-vertex that is being manipulated.
     * @param a
    * 
    * @return BufferedImage, with the grid line of shortest path. 
    */
  
    public BufferedImage drawGrid(BufferedImage img, Vertex d, Vertex a) {
        for (int drawRow = -1; drawRow < 2; drawRow++) {
            for (int drawCol = -1; drawCol < 2; drawCol++) {
                int currentRowD = d.getRow() - drawRow;
                int currentColD = d.getColumn()- drawCol;
                
                int currentRowA = a.getRow() - drawRow;
                int currentColA = a.getColumn()- drawCol;
                      
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
                    }
                }
            }
        }
        return img;
    }
    
    /**
     *
     * @param img
     * @param visited
     * @param color
     * @return
     */
    public BufferedImage drawVisited(BufferedImage img, boolean[][] visited, int color) {
        Color visitedColor = new Color(0,0,0);
        switch (color) {
            case 0:
                visitedColor = visitedDijkstra;
                break;
            case 1:
                visitedColor = visitedAstar;
                break;
            default:
                visitedColor = visitedJps;
                break;
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

    /**
     *
     * @return
     */
    public int getVisitedD() {
        return visitedD;
    }

    /**
     *
     * @return
     */
    public int getVisitedA() {
        return visitedA;
    }

    /**
     *
     * @return
     */
    public int getVisitedJPS() {
        return visitedJPS;
    }
    
    /**
     *
     * @param img
     * @param height
     * @param width
     * @return
     */
    public BufferedImage makeNewFrame(BufferedImage img, int height, int width) {
        try {
            return img = resizeImage(img, height, width);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  
}