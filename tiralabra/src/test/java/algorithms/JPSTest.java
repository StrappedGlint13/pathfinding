/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import datastructures.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matibrax
 */
public class JPSTest {
    SearchInterface jps;
    List vertices;
    int[][] testmap;
    
    public JPSTest() {
    }
    
    @Before
    public void setUp() {
        jps = new JPS();
        testmap = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
        };
        vertices = new List();
    }
    
    @Test
    public void testMovingRight() {
        vertices = jps.findPath(testmap, 0, 0, 0, 4);
        assertEquals(4, vertices.size());  
    }
    
    @Test
    public void algoDontFindThePaht() {
        testmap = new int[][]{
            {1, 1, 1, 0, 1, 1, 1, 1},
        };
        vertices = jps.findPath(testmap, 0, 0, 0, 5);
        assertEquals(0, vertices.size());
    }

}
