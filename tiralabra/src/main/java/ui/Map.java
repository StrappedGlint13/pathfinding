/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author matibrax
 */
public class Map {
    
    private Color[][] pixelMap;
    private int[][] land; // moving
    private Color[][] obstacle; // not sure if needed, walls etc, this is for user, if user pick no-land for start or end points
    private BufferedImage img;
    private int height;
    private int width;
    
    public Map (BufferedImage img, int height, int width) {
        this.img = img;
        this.height = height;
        this.width = width;
        this.pixelMap = new Color[height][width];
        this.land = new int[height][width];
        this.obstacle = new Color[height][width];
    }
    
    public void generateMaps() {
        for (int j = 0; j < this.height; j++) {
            for (int k = 0; k < width; k++) {
                pixelMap[j][k] = new Color(img.getRGB(j, k));
                if (pixelMap[j][k].getGreen()== 229 && pixelMap[j][k].getBlue()== 229 && pixelMap[j][k].getRed() == 229 ) {
                    land[j][k] = 0;
                } else {
                    obstacle[j][k] = new Color(img.getRGB(j, k));
                }
            }
        }
    }         
}
