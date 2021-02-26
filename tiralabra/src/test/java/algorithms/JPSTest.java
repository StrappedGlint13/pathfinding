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
    public void testMovingRightAndLeft() {
        testmap = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1},
        };
        vertices = jps.findPath(testmap, 3, 0, 3, 7);
        assertEquals(7, vertices.size()); 
        
        vertices = jps.findPath(testmap, 7, 7, 7, 3);
        assertEquals(4, vertices.size());
    }
    
    @Test
    public void testMovingUpAndDown() {
        testmap = new int[][]{
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
        };
        vertices = jps.findPath(testmap, 0, 0, 11, 0);
        assertEquals(11, vertices.size()); 
        
        vertices = jps.findPath(testmap, 11, 0, 0, 0);
        assertEquals(11, vertices.size());
    }
    
     @Test
    public void testMovingManhattanWay() {
        testmap = new int[][]{
            {1, 0, 0, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
        };
        vertices = jps.findPath(testmap, 4, 3, 7, 3);
        assertEquals(3, vertices.size()); 
    }
    

    @Test
    public void testMovingDiagonally() {
        testmap = new int[][]{
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
        };
        vertices = jps.findPath(testmap, 0, 0, 7, 7); // east-south
        assertEquals(7, vertices.size()); 
        
        vertices = jps.findPath(testmap, 7, 4, 0, 11); // east-north
        assertEquals(7, vertices.size());
        
        vertices = jps.findPath(testmap, 1, 11, 8, 4); // west-south
        assertEquals(7, vertices.size());
        
        vertices = jps.findPath(testmap, 11, 7, 8, 4); // west-north
        assertEquals(3, vertices.size()); 
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
