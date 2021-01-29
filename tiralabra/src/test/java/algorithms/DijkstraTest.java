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
    int[][] testmap;
    
    @Before
    public void setUp() {
        dijkstra = new Dijkstra();
        testmap = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
        };
    }
    /*
    @Test
    public void testConstructor() {
        ArrayList<Vertice> path = dijkstra.findPath(testmap, 0, 0, 1, 1);
        assertEquals(1, path.size());
    }*/

    @Test
    public void testFindingMap() {
        ArrayList<Vertice> path = dijkstra.findPath(testmap, 0, 0, 3, 0);
        assertEquals(3, path.size());
    }
}
