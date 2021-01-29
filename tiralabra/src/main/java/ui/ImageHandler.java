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
import javax.imageio.ImageIO;

/**
 *
 * A Class for handling the images of the maps.
 * 
 * @author matibrax
 */

public class ImageHandler {
    public ImageHandler() {   
    }
   
    public BufferedImage resizeImage(BufferedImage img, int width, int heigth) throws Exception {
        BufferedImage resizedImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
        Graphics2D writer = resizedImage.createGraphics();
        writer.drawImage(img, 0, 0, width, heigth, null);
        writer.dispose();
        return resizedImage;
    }
    
    // Add drawing functionality when we have the best path
    public BufferedImage draw(BufferedImage img, int width, int heigth) throws Exception {
        for (int j = 0; j < heigth; j++) {
            for (int k = 0; k < width; k++) {
                img.setRGB(j, k, Color.green.getRGB());
            }
        }
        
        
        return img;
    }
   
}
