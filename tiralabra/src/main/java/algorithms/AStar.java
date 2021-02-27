package algorithms;

import datastructures.Heap;
import datastructures.List;
import datastructures.Vertex;

/**
 *
 * Algorithm that searches the shortest path. 
 * 
 * @author matibrax
 */

public class AStar implements SearchInterface {
    final float diagonalMovement;
    boolean[][] visited;
    
    public AStar() {
        this.diagonalMovement = (float) Math.sqrt(2);
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
    
    //@Override
    public List findPath(int[][]map, int startR, int startC, int endR, int endC) {
        int rowLength = map.length;  
        int columnLength = map[0].length;
    
        float[][] distance = new float[rowLength][columnLength];
        visited = new boolean[rowLength][columnLength];
        Heap heap = new Heap();
        
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < columnLength; c++) {
                distance[r][c] = 10000000;
            }
        }
        //check if the user clicked obstacle
        if (map[startR][startC] == 0 || map[endR][endC] == 0) {
            return null;
        }

        Vertex startPoint = new Vertex(startR, startC, 0, null);
        distance[startR][startC] = 0;
        startPoint.setHeuristic(heuristics(endR, endC, startR, startC));
        
        heap.add(startPoint);
        while(heap.getVertexFromIndex(0) != null) {
            Vertex currentV = heap.poll();
     
            int currentRow = currentV.getRow();
            int currentColumn = currentV.getColumn();
            
            if (currentRow == endR && currentColumn == endC) {
                return createShortestPath(currentV);
            }
              
            visited[currentRow][currentColumn] = true;
            
            for (int rowStep = -1; rowStep < 2; rowStep++) {
                for (int columnStep = -1; columnStep < 2; columnStep++) {
                    if(rowStep == 0 && columnStep == 0) {
                        continue;
                    }
                    int moveOneRow = currentRow + rowStep;
                    int moveOneColumn = currentColumn + columnStep;
                    
                    if (!checkLimits(map, moveOneRow, moveOneColumn, rowLength, columnLength)) {
                        continue;
                    }

                    if (map[moveOneRow][moveOneColumn] == 0) {
                        continue;
                    }
                    
                    float nextDistance = (float) (currentV.getDistance() + diagonalMovement);
                    
                    //if moving straight
                    if (rowStep == 0 || columnStep == 0) {
                        nextDistance = (float) (currentV.getDistance() + 1);
                    }
                    
                    if(nextDistance < distance[moveOneRow][moveOneColumn]) {
                        distance[moveOneRow][moveOneColumn] = nextDistance;
                        Vertex next = new Vertex(moveOneRow, moveOneColumn, nextDistance, currentV);
                        next.setHeuristic(nextDistance + heuristics(endR, endC, moveOneRow, moveOneColumn));
                        heap.add(next);
                    }
                }
            }                  
        }
        return null;
    }
    
    
    
    /**
    * Method for A* to estimate Euclidean distance.
    *
    * @param currentX current x-coordinate.
    * @param currentY current y-coordinate.
    * @param endX end x-coordinate.
    * @param endY end y-coordinate.
    * 
    * @return Euclidean distance.
    */
    
    private float heuristics(int endX, int endY, int currentX, int currentY) {
        // Euclidean distance for A*
        return (float) (Math.sqrt(((currentX-endX)*(currentX-endX)) + (currentY-endY)*(currentY-endY)));
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

    //@Override
    public boolean checkLimits(int[][]map, int r, int c, int rowLength, int columnLength) {
        if (r < 0 || c < 0 || r >= rowLength || c >= columnLength) {
            return false;
        }
        return true;
    }

    /**
    * Method for creating the shortest path of Vertex recursively.
    *
    * @param vertice currently handling vertex.
    * 
    * @return list of vertex for the shortest path, if there is previous vertex, 
    * call the method again, if null is found from the "previous",
    * we are at the starting vertex. 
    * 
    */
    
    //@Override
    public List createShortestPath(Vertex vertice) {
        List shortestPath = new List();
        while (vertice.getPrevious() != null) {
            shortestPath.add(vertice);
            vertice = vertice.getPrevious();
        }
        
        return shortestPath;
    }
    
    /**
    * Method for returning all the visited notes.
    * 
    * @return list of vertex for the shortest path, if there is previous vertex, 
    * call the method again, if null is found from the "previous",
    * we are at the starting vertex. 
    * 
    */
    
    public boolean[][] getVisited() {
        return this.visited;
    }

}
