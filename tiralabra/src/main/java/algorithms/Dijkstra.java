package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import datastructures.Vertex;
import datastructures.Heap;

/**
 *
 * Algorithm that searches the shortest path. 
 * 
 * @author matibrax
 */

public class Dijkstra implements SearchInterface {
    final double diagonalMovement;
    public boolean visited[][];
    public Dijkstra() {
        this.diagonalMovement = Math.sqrt(2);
        this.visited = new boolean [1][1];
    }
    
    /**
    * Method creates a shortest path.
    *
    * @param map given pixel-map.
    * @param startR start row, x-coordinate.
    * @param startC start column, y-coordinate.
    * @param endR end row, point x-coordinate.
    * @param endC end column, point y-coordinate.
    * 
    * @return shortest path as a list of Vertices.
    */
    
    @Override
    public ArrayList<Vertex> findPath(int[][]map, int startR, int startC, int endR, int endC) {
        int rowLength = map.length;  
        int columnLength = map[0].length;

        double[][] distance = new double[rowLength][columnLength];
        visited = new boolean[rowLength][columnLength];
        Heap heap = new Heap();
        
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < columnLength; c++) {
                distance[r][c] = Integer.MAX_VALUE;
            }
        }
        
        //check if the user clicked obstacle
        if (map[startR][startC] == 0 || map[endR][endC] == 0) {
            return new ArrayList<>();
        }
       
        Vertex startPoint = new Vertex(startR, startC);
        distance[startR][startC] = 0;
        
        //Start searching
        heap.add(startPoint);
        while(heap.getSize()!=0) {
            //System.out.println("Printing heap: ");
            /*for (int i = 0; i < heap.getSize(); i++) {   
                System.out.println(heap.getVertexFromIndex(i));
            }*/
            //System.out.println("");
            Vertex currentV = heap.poll();
            //System.out.println("Polled vertex");
            //System.out.println("V: " + currentV);
            int currentRow = currentV.getRow();
            int currentColumn = currentV.getColumn();
            
            //found the path!
            if (currentRow == endR && currentColumn == endC) {
                //System.out.println("Goal!! " + currentV);
                return createShortestPath(currentV);
            }
           
            
            if (visited[currentRow][currentColumn]) {
                continue;
            }
            
            visited[currentRow][currentColumn] = true;
            
            //start moving
            for (int rowStep = -1; rowStep < 2; rowStep++) {
                for (int columnStep = -1; columnStep < 2; columnStep++) {
                    //if we are at the starting point
                    if(rowStep == 0 && columnStep == 0) {
                        continue;
                    }
                    
                    int moveOneRow = currentRow + rowStep;
                    int moveOneColumn = currentColumn + columnStep;
                    
                    //we don't want double distances
            
                    if (!checkLimits(map, moveOneRow, moveOneColumn, rowLength, columnLength)) {
                        continue;
                    }
                                 
                    //if there is an obstacle
                    if (map[moveOneRow][moveOneColumn] == 0) {
                        continue;
                    }
                    
                    double nextDistance = currentV.getDistance() + diagonalMovement;
                    //if we are moving straight
                    if (rowStep == 0 || columnStep == 0) {
                        nextDistance = currentV.getDistance() + 1;
                    }
                                
                    if(nextDistance < distance[moveOneRow][moveOneColumn]) {
                        distance[moveOneRow][moveOneColumn] = nextDistance;
                        Vertex next = new Vertex(moveOneRow, moveOneColumn, nextDistance, currentV);
                        heap.add(next);
                    }
                }
            }                  
        }
        return null;
    }
    
    /**
    * Method for checking the limits of moving.
    *
    * @param map given pixel-map.
    * @param c current y-coordinate.
    * @param r current x-coordinate.
    * @param rowLength dimension of the rows.
    * @param columnLength dimension of the columns.
    * 
    * @return true or false, depends on if we are in the limits. 
    */

    @Override
    public boolean checkLimits(int[][]map, int r, int c, int rowLength, int columnLength) {
        if (r < 0 || c < 0 || r >= rowLength || c >= columnLength) {
            return false;
        }
        return true;
    }
    
    /**
    * Method for creating the shortest path of Vertex recursively.
    *
    * @param vertice currently handling vertex
    * 
    * @return list of vertex for the shortest path, if there is previous vertex, 
    * call the method again, if null is found from the "previous",
    * we are at the starting vertex. 
    * 
    */

    @Override
    public ArrayList<Vertex> createShortestPath(Vertex vertice) {
        ArrayList<Vertex> shortestPath = new ArrayList<>();
        while (vertice.getPrevious() != null) {
            shortestPath.add(vertice);
            vertice = vertice.getPrevious();
        }
        return shortestPath;
    }
    
    public boolean[][] getVisited() {
        return this.visited;
    }

}


