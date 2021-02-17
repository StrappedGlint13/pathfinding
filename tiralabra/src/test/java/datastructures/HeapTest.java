/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

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
public class HeapTest {
    Heap testHeap = new Heap();
    Vertex testV1;
    Vertex testV2;
    Vertex testV3;
    Vertex testV4;
    
    @Before
    public void setUp() {
        this.testHeap = testHeap;
        testV1 = new Vertex(2, 2, 0, null);
        testV2 = new Vertex(2, 3, 1, testV1);
        testV3 = new Vertex(2, 4, 2, testV2);
        testV4 = new Vertex(3, 4, 3, testV3);
        testHeap.add(testV1);
        testHeap.add(testV2);
        testHeap.add(testV3);
        testHeap.add(testV4);
    }
    
    @Test
    public void constructorIsCorrect() {
        assertEquals(4, testHeap.getSize());
        
    }
    
    @Test
    public void addingWorks() {
        Vertex newRoot = new Vertex(2, 1, 0, null);
        testHeap.add(newRoot);
        assertEquals(newRoot.getRow(), testHeap.getRoot().getRow());
        assertEquals(newRoot.getColumn(), testHeap.getRoot().getColumn());
        
        Vertex nextVertex = testHeap.getVertexFromIndex(1);
        assertEquals(nextVertex, testV1);
    }
    
    @Test
    public void pollingWorks() {
        Vertex polledVertex = testHeap.poll();
        assertEquals(polledVertex, testV1);
        Vertex newRoot = testHeap.getRoot();
        assertEquals(newRoot, testV2);
        Vertex polledVertex2 = testHeap.poll();
        assertEquals(polledVertex2, testV2);
        assertEquals(2, testHeap.getSize());
    }
    
    @Test
    public void addingAndPollingWorks() {
        Vertex testV5 = new Vertex(4, 5, Math.sqrt(2)+3, testV4);
        Vertex testV6 = new Vertex(5, 6, Math.sqrt(2)+testV5.distance, testV5);
        Vertex testV7 = new Vertex(6, 7, Math.sqrt(2)+testV6.distance, testV6);
        Vertex testV8 = new Vertex(7, 8, Math.sqrt(2)+testV7.distance, testV7); 
    }
}
