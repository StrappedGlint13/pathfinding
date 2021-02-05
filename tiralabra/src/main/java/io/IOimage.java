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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static javax.swing.JOptionPane.showMessageDialog;
import utils.Vertex;

/**
 *
 * Class that imports the image with given URL. . 
 * 
 * @author matibrax
 */

public class IOimage {

    
    private URL url;
    
    public IOimage() {
        this.url = url;
    }
    
    public BufferedImage readImage(String imageUrl) {
        try {
            BufferedImage img;
            url = new URL(imageUrl);
            return img = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("Failed to read the image " + e);
            showMessageDialog(null, "This is not a 2D benchmark map!");
        }
        return null;
    }
    
}
