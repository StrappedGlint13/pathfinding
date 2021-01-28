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
    private Color[][] land;
    private Color[][] obstacle;
    private BufferedImage img;
    private int height;
    private int width;
    
    public Map (BufferedImage img, int height, int width) {
        this.img = img;
        this.height = height;
        this.width = width;
        this.pixelMap = new Color[height][width];
        this.land = new Color[height][width];
        this.obstacle = new Color[height][width];
        
        for (int j = 0; j < height; j++) {
                for (int k = 0; k < width; k++) {
                    pixelMap[j][k] = new Color(img.getRGB(j, k));
                    if (pixelMap[j][k].getGreen()== 299 && pixelMap[j][k].getBlue()== 299 && pixelMap[j][k].getRed() == 299 ) {
                        land[j][k] = new Color(img.getRGB(j, k));
                    } else {
                        obstacle[j][k] = new Color(img.getRGB(j, k));
                    }
                }
        }
    }
        
    
}
