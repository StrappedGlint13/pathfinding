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
import utils.Vertice;

/**
 *
 * @author matibrax
 */
public class DijkstraTest {

    Dijkstra dijkstra;
    ArrayList vertices;
    int[][] testmap;
    
    @Before
    public void setUp() {
        dijkstra = new Dijkstra();
        testmap = new int[][]{
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
        };
        
    /*
    @Test
    public void testConstructor() {
        ArrayList<Vertice> path = dijkstra.findPath(testmap, 0, 0, 1, 1);
        assertEquals(1, path.size());
    }*/
    }

    @Test
    public void testFindThePath() {
        vertices = new ArrayList<>();
        
        vertices = dijkstra.findPath(testmap, 0, 0, 0, 7);
        assertEquals(11, vertices.size());
        
        vertices = dijkstra.findPath(testmap, 0, 0, 3, 0);
        assertEquals(3, vertices.size());
        
        vertices = dijkstra.findPath(testmap, 3, 7, 0, 0);
        assertEquals(9, vertices.size());
    }
    
    @Test
    public void testAlgoDontFindThePath() { 
        vertices = new ArrayList<>();
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

        vertices = dijkstra.findPath(testmap, 0, 0, 5, 4);
        boolean isFound = true;
        
        if (vertices == null) {
            isFound = false;
        }
        
        assertEquals(false, isFound);
    }
    
    
}
