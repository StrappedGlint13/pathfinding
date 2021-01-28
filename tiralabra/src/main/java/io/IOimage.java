/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author matibrax
 */
public class IOimage {
    private URL url;
    
    public IOimage() {
        this.url = url;
    }
    
    public BufferedImage readImage(String imageUrl) {
        BufferedImage img;
        try {
            url = new URL(imageUrl);
            return img = ImageIO.read(url);
         } catch (IOException e) {
            System.out.println("Failed to read the image " + e);
            showMessageDialog(null, "This is not a 2D benchmark map!");
         }
       return null;
    }
}
