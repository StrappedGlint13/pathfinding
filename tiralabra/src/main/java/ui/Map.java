/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import datastructures.Vertex;

/**
 *
 * A Class that generates the map for the application and UI. 
 * 
 * @author matibrax
 */

public class Map {
    private Color[][] pixelMap;
    private int[][] land; // for moving
    private BufferedImage img;
    private int height;
    private int width;
    
    public Map (BufferedImage img, int height, int width) {
        this.img = img;
        this.height = height;
        this.width = width;
        this.pixelMap = new Color[height][width];
        this.land = new int[height][width];
    }
    
    /**
    * Method that generates binary map. 
    * 
    * @return 2D-integer-table with 0 (obstacle) and 1 (land). 
    */
    
    public int[][] generateMaps() {
        for (int r = 0; r < this.height; r++) {
            for (int c = 0; c < width; c++) {
                pixelMap[r][c] = new Color(img.getRGB(r, c));
                if (pixelMap[r][c].getGreen()== 229 && pixelMap[r][c].getBlue()== 229 && pixelMap[r][c].getRed() == 229 ) {
                    land[r][c] = 1;
                } else {
                    land[r][c] = 0;
                }
            }
        }
        return land;
    }         

}
