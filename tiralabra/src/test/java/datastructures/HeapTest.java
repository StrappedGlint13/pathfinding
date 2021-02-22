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
    //fix
    @Test
    public void addingWorks() {
        Vertex newRoot = new Vertex(2, 1, -1, null);
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
        Heap testHeap = new Heap();
        Vertex testV5 = new Vertex(4, 5, 1, null);
        Vertex testV6 = new Vertex(5, 6, Math.sqrt(2), testV5);
        Vertex testV7 = new Vertex(6, 7, Math.sqrt(2)+testV6.getDistance(), testV6);
        Vertex testV8 = new Vertex(5, 6, 1, testV7);
        
        testHeap.add(testV6);
        testHeap.poll();
        testHeap.add(testV5);
        testHeap.add(testV8);
        Vertex v = testHeap.poll();
        assertEquals(v, testV5);
        Vertex v2 = testHeap.poll();
        assertEquals(v2, testV8);
       
        assertEquals(0, testHeap.getSize());
        Vertex testV9 = new Vertex(4, 5, 1, null);
        testHeap.add(testV9);
        Vertex v3 = testHeap.poll();
        assertEquals(v3, testV9);
        assertEquals(0, testHeap.getSize());  
    }
    
    @Test
    public void heapIsEmptyAfterPolling() {
        testHeap.poll();
        testHeap.poll();
        testHeap.poll();
        testHeap.poll();
        assertEquals(null, testHeap.getVertexFromIndex(0));
    }
    
    @Test
    public void printRootWorks() {
        Vertex v = testHeap.poll();
        String s = "Vertice: row " + 2 + " column " + 2 + " distance " + 0.0;
        assertEquals(v.toString(), s);
    }
}
