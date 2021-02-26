package datastructures;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matibrax
 */

public class ListTest {
    List testList = new List();
    
    @Before
    public void setUp() {
        this.testList = testList;
    }
    
    @Test
    public void listIsEmpty() {
        assertEquals(true, testList.isEmpty());
    }
    
    @Test
    public void expandingWorks() {
        for (int i = 0; i < 10000000; i++) {
            Vertex v = new Vertex(i,i);
            testList.add(v);
        }
        assertEquals(10000000, testList.size());
    }

    @Test
    public void addingWorks() {
        Vertex v = new Vertex(0,1);
        testList.add(v);
        Vertex v1 = new Vertex(2,0);
        testList.add(v1);
        Vertex v2 = new Vertex(1,0);
        testList.add(v2);
        Vertex v3 = new Vertex(1,2);
        testList.add(v3);
        
        assertEquals(4, testList.size());
        assertEquals(v, testList.getFromIndex(0));
        assertEquals(v3, testList.getFromIndex(3)); 
    }
    
}
