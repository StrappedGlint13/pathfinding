package algorithms;


import datastructures.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author matibrax
 */
public class DijkstraTest {

    SearchInterface d;
    List vertices;
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
        vertices = new List();
    }

    @Test
    public void testAlgoFindThePath() {
        testmap = new int[][]{
            {1, 0},
            {0, 1},
        };
        vertices = d.findPath(testmap, 0, 0, 1, 1);
        assertEquals(1, vertices.size());
    }
    
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
