/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import datastructures.Heap;
import datastructures.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matibrax
 */
public class JPSTest {
    JPS jps;
    List vertices;
    int[][] testmap;
    Heap heap;
    
    public JPSTest() {
    }
    
    @Before
    public void setUp() {
        this.heap = new Heap();
        jps = new JPS();
        testmap = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
        };
        vertices = new List();
    }
    
    
    
 
    @Test
    public void algoFindsTheForcedNeigboursFromTopAndBelow() {
        this.heap = new Heap();
        testmap = new int[][]{
            {1, 0, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 1},
        };
        vertices = jps.findPath(testmap, 1, 0, 1, 5);
        //assertEquals(2, vertices.size());
        
        for (int i = 0; i < jps.getHeap().getSize(); i++) {
           System.out.println("HEAP from index:"+ i + " " +jps.getHeap().getVertexFromIndex(i));
            System.out.println("Previous" + jps.getHeap().getVertexFromIndex(i).getPrevious());
            System.out.println("Heuristics" + jps.getHeap().getVertexFromIndex(i).getHeuristic());
        }
        assertEquals(5, vertices.size());
        assertEquals(3, jps.getHeap().getSize());
    }
    
    @Test
    public void jpsFindsTheForcedNeigboursFromRightToLeft() { 
        this.heap = new Heap();
        testmap = new int[][]{
            {0, 1, 0, 0, 1, 1, 1},
            {0, 1, 0, 0, 1, 1, 1},
            {0, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {0, 1, 0, 1, 1, 1, 1},
            {0, 1, 0, 1, 1, 1, 1},
            {0, 1, 0, 1, 1, 1, 1},
            {0, 1, 0, 1, 1, 1, 1},
        };
        
        vertices = jps.findPath(testmap, 0, 1, 4, 3);
        /*for (int i = 0; i < jps.getHeap().getSize(); i++) {
           System.out.println("HEAP from index:"+ i + " " +jps.getHeap().getVertexFromIndex(i));
           System.out.println("Previous" + jps.getHeap().getVertexFromIndex(i).getPrevious());
           System.out.println("Heuristics" + jps.getHeap().getVertexFromIndex(i).getHeuristic());
        }*/
        assertEquals(4, vertices.size());
        assertEquals(5, jps.getHeap().getSize());
        
        this.heap = new Heap();
        vertices = jps.findPath(testmap, 0, 1, 4, 6);
        
        for (int i = 0; i < jps.getHeap().getSize(); i++) {
           System.out.println("HEAP from index:"+ i + " " +jps.getHeap().getVertexFromIndex(i));
           System.out.println("Previous" + jps.getHeap().getVertexFromIndex(i).getPrevious());
           System.out.println("Heuristics" + jps.getHeap().getVertexFromIndex(i).getHeuristic());
        }
        assertEquals(7, vertices.size());
        assertEquals(5, jps.getHeap().getSize());
        
    }
    
    /*
    // Here are some passed test regarding to moving, these can be used maybe on later purposes.
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
    public void testmovingtogether() {
        testmap = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1},
        };
        //right diagonally and up
        vertices = jps.findPath(testmap, 7, 0, 1, 5);
        assertEquals(6, vertices.size());
        
        //right diagonally and right
        vertices = jps.findPath(testmap, 6, 1, 3, 7);
        assertEquals(6, vertices.size());
        
        // left diagonally
        vertices = jps.findPath(testmap, 5, 3, 0, 0);
        assertEquals(5, vertices.size());
        
        System.out.println("test");
        //left diagonally up, left and diagonally down
        vertices = jps.findPath(testmap, 7, 7, 7, 2);
        assertEquals(10, vertices.size());
           
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
*/
}
