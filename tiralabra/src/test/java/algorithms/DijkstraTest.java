/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import datastructures.Vertex;

/**
 *
 * @author matibrax
 */
public class DijkstraTest {

    SearchInterface d;
    ArrayList vertices;
    int[][] testmap;
    
    @Before
    public void setUp() {
        d = new Dijkstra();
        testmap = new int[][]{
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
        };
        vertices = new ArrayList<>();
    }
    /*
    @Test
    public void testStartingPointAndDistance() {
        vertices = d.findPath(testmap, 0, 0, 0, 0);
        Vertex v = new Vertex(0,0,0,null);
        assertEquals(v, vertices.get(0));
      
    }
*/
    
    @Test
    public void testAlgoFindsThePath() {
        vertices = d.findPath(testmap, 0, 0, 0, 7);
        assertEquals(11, vertices.size());
        
        vertices = d.findPath(testmap, 0, 0, 3, 0);
        assertEquals(3, vertices.size());
        
        vertices = d.findPath(testmap, 3, 7, 0, 0);
        assertEquals(9, vertices.size());
    }
    
    
    @Test
    public void testAlgoDontFindThePath() { 
        testmap = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 1, 1},
            {1, 1, 1, 0, 1, 0, 1, 1},
            {1, 1, 1, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
        };

        vertices = d.findPath(testmap, 0, 0, 5, 4);
        boolean isFound = true;
        
        if (vertices == null) {
            isFound = false;
        }
        
        assertEquals(false, isFound);
    }
    
    @Test
    public void testAlgoFindsTheShortestPath() { 
        testmap = new int[][]{
            {1, 0, 1, 1, 0, 0, 0, 1, 0},
            {1, 0, 1, 1, 0, 0, 0, 1, 0},
            {1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 0},
            {1, 1, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 0},
            {1, 0, 1, 0, 1, 1, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 0, 0, 0},
        };
        
        vertices = d.findPath(testmap, 0, 0, 7, 8);
        assertEquals(11, vertices.size());
        
        vertices = d.findPath(testmap, 3, 8, 8, 0);
        assertEquals(10, vertices.size());
    }
}
