package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import static javax.swing.JOptionPane.showMessageDialog;


/**
 *
 * Class that imports the image with given URL.
 * 
 * @author matibrax
 */

public class IOimage {
    private URL url;
    
    public IOimage() {
        this.url = url;
    }
    
    /**
    * Method creates a reads given URL and forming BufferedImage.
    *
    * @param imageUrl given URL.
    * 
    * @return BufferedImage from the source.
    */
    
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
