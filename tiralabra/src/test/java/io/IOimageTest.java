/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import ui.IOimage;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 * @author matibrax
 */
public class IOimageTest {
    
    IOimage io;
    BufferedImage img;
    
  
    @Before
    public void setUp() {
        io = mock(IOimage.class);
        img = mock(BufferedImage.class);
    }
    /*
    @Test
    public void hello() {
        img = io.readImage("https://movingai.com/benchmarks/street/Denver_2_256.png");
        assertNotEquals(null, img);
    }
*/
}
