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
import utils.Vertice;

/**
 *
 * A Class for handling the images of the maps.
 * 
 * @author matibrax
 */

public class ImageHandler {
    Color pathColor;
    public ImageHandler() {  
        pathColor = new Color(255, 178, 102);
    }
   
    public BufferedImage resizeImage(BufferedImage img, int width, int heigth) throws Exception {
        BufferedImage resizedImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
        Graphics2D writer = resizedImage.createGraphics();
        writer.drawImage(img, 0, 0, width, heigth, null);
        writer.dispose();
        return resizedImage;
    }
    
    // Add drawing functionality when we have the best path
    /*
    public BufferedImage draw(BufferedImage img, int width, int heigth) throws Exception {
        for (int j = 0; j < heigth; j++) {
            for (int k = 0; k < width; k++) {
                img.setRGB(j, k, Color.green.getRGB());
            }
        }
        
        
        return img;
    }*/

  public BufferedImage drawShortestPath(BufferedImage img, ArrayList<Vertice> shortestPath) {   
        for (int i = 1; i < shortestPath.size() - 1; i++) {
            Vertice v = shortestPath.get(i);
            //img = draw(img, v);
            img = drawGrid(img, v);
        }
        return img;
    }
  
  public BufferedImage drawGrid(BufferedImage img, Vertice v) {
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
  
  public BufferedImage draw(BufferedImage img, Vertice v) {
        img.setRGB(v.getRow(), v.getColumn(), pathColor.getRGB());
      return img;
  }
 
}
