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
        vertices = new List();
    }
    
    @Test
    public void algoFindsForcedNeigboursDiagonalRightAndLeft() {
        this.heap = new Heap();
        testmap = new int[][]{
            {1, 1, 1},
            {0, 1, 0},
            {1, 1, 1},
        };
        /*
        // from left bottom to right above
        vertices = jps.findPath(testmap, 2, 0, 0, 2);
        assertEquals(2, vertices.size());
        assertEquals(0, jps.getHeap().getSize());
        */
        // from right bottom to left up
        this.heap = new Heap();
        vertices = jps.findPath(testmap, 2, 2, 0, 0);
        
      
        assertEquals(2, vertices.size());
        assertEquals(0, jps.getHeap().getSize());
       
        // from left up to right bottom
        this.heap = new Heap();
        vertices = jps.findPath(testmap, 0, 0, 2, 2);
        
 
        assertEquals(2, vertices.size());
        assertEquals(0, jps.getHeap().getSize());

        // from right up to left bottom
        this.heap = new Heap();
        vertices = jps.findPath(testmap, 0, 2, 2, 0);
       
        assertEquals(2, vertices.size());
        assertEquals(0, jps.getHeap().getSize());   
    }
    
    
    @Test
    public void algoFindsTheForcedNeigboursFromDiagonalUpAndDown() {
        this.heap = new Heap();
        testmap = new int[][]{
            {1, 0, 1},
            {1, 1, 1},
            {1, 0, 1},
        };
       
        vertices = jps.findPath(testmap, 2, 0, 0, 2);
        assertEquals(2, vertices.size());
        assertEquals(0, jps.getHeap().getSize());
        
        this.heap = new Heap();
        vertices = jps.findPath(testmap, 2, 2, 0, 0);
        
 
        assertEquals(2, vertices.size());
        assertEquals(0, jps.getHeap().getSize());
  
        
        this.heap = new Heap();
        vertices = jps.findPath(testmap, 0, 2, 2, 0);
        
 
        assertEquals(2, vertices.size());
        assertEquals(0, jps.getHeap().getSize());
        
        this.heap = new Heap();
        vertices = jps.findPath(testmap, 0, 2, 2, 0);
       
        assertEquals(2, vertices.size());
        assertEquals(0, jps.getHeap().getSize());
        
        this.heap = new Heap();
        vertices = jps.findPath(testmap, 0, 0, 2, 2);
       
        assertEquals(2, vertices.size());
        assertEquals(0, jps.getHeap().getSize());
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
        assertEquals(5, vertices.size());
        assertEquals(0, jps.getHeap().getSize());
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
            {0, 1, 0, 1, 0, 1, 1},
            {0, 1, 0, 1, 1, 1, 1},
        }; 
        vertices = jps.findPath(testmap, 7, 1, 0, 6);
       
        assertEquals(8, vertices.size());
        assertEquals(2  , jps.getHeap().getSize());


    }
    
    @Test
    public void testFindingThePath() {
        testmap = new int[][]{
            { 0, 0, 0, 0, 0, 1}, //0
            { 0, 0, 0, 0, 1, 1}, //1
            { 0, 0, 0, 1, 1, 1}, //2
            { 0, 0, 1, 1, 1, 1}, //3
            { 0, 1, 1, 0, 1, 1}, //4
            //0//1 //2 //3//4//5
        };
        vertices = jps.findPath(testmap, 4, 5, 4, 1);
        assertEquals(4, vertices.size()); 
       
        vertices = jps.findPath(testmap, 4, 1, 0, 5);
        assertEquals(4, vertices.size());
        
    }
    
    
    @Test
    public void testFindingThePathFromM11X11() {
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
      
        vertices = jps.findPath(testmap, 0, 0, 0, 11); 
        assertEquals(11, vertices.size()); 
        
        vertices = jps.findPath(testmap, 11, 11, 11, 4); 
        assertEquals(16, vertices.size()); 
        
        vertices = jps.findPath(testmap, 11, 7, 11, 0); 
        assertEquals(15, vertices.size()); 
        
        vertices = jps.findPath(testmap, 11, 4, 0, 0); 
        assertEquals(11, vertices.size());  
    }
    
    @Test
    public void testFindingThePathFrom11X11Complex() {
        testmap = new int[][]{
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1},
            {0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1},
            {0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
        };
      
        vertices = jps.findPath(testmap, 11, 0, 0, 11); 
        assertEquals(13, vertices.size());  
        
        vertices = jps.findPath(testmap, 0,11 , 11, 0); 
        assertEquals(13, vertices.size());
    }
      
    @Test
    public void testFindingNeighboursNearTheWall() {
        testmap = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},// 5
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
        };
        vertices = jps.findPath(testmap, 5, 3, 5, 7);
        assertEquals(8, vertices.size()); 
        assertEquals(3, jps.getHeap().getSize());
        /*
        vertices = jps.findPath(testmap, 5, 7, 5, 3);
        assertEquals(8, vertices.size()); 
        assertEquals(2, jps.getHeap().getSize());
        testmap = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},// 6
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        
        vertices = jps.findPath(testmap, 6, 4, 0, 4);
        assertEquals(8, vertices.size()); 
        assertEquals(2, jps.getHeap().getSize());
*/
    }
}
