/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import datastructures.Heap;
import datastructures.List;
import datastructures.Vertex;

/**
 *
 * A Class for Jump Point Search -algorithm
 * 
 * @author matibrax
 * 
 */

public class JPS implements SearchInterface {
    final float diagonalMovement;
    boolean[][] visited;
    int[][] map; 
    int endR;
    int endC;
    int rowLength;
    int columnLength;
    List l;
    
    public JPS () {
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
        this.map = map;
        this.endR = endR;
        this.endC = endC;
        this.rowLength = map.length;  
        this.columnLength = map[0].length;
        this.l = new List();
   
        visited = new boolean[rowLength][columnLength];
        Heap heap = new Heap();
        
        //check if the user clicked obstacle
        if (map[startR][startC] == 0 || map[endR][endC] == 0) {
            return null;
        }

        Vertex startPoint = new Vertex(startR, startC, 0, null);
        startPoint.setHeuristic(heuristics(endR, endC, startR, startC));
        
        heap.add(startPoint);
        while(heap.getVertexFromIndex(0) != null) {
            Vertex currentV = heap.poll();
            jumpVerticallyAndHorizontally(currentV);
            jumpDiagonally(currentV);
        }
        return l;
    }
    
    private boolean foundTheEnd(Vertex v) {
        if (v.getRow() == endR && v.getColumn() == endC) {
            List l = createShortestPath(v);
            this.l = l;
            return true;
        }
        return false;
    }
    
    private boolean move(Vertex currentV, int movementRow, int movementCol) {
        boolean found = false;
        int nextRow = currentV.getRow() + movementRow;
        int nextColumn = currentV.getColumn() + movementCol;
            
        if (!checkLimits(map, nextRow, nextColumn, rowLength, columnLength)) {
            return false; // here check forced neighbours
        }
            
        Vertex nextStep = new Vertex(nextRow, nextColumn, nextColumn, currentV);
        if (foundTheEnd(nextStep)) {
            found =  true;
        }
        
        if (found == true) {
            return true;
        }
        move(nextStep, movementRow, movementCol);
        return found;
    }
    
    private void jumpDiagonally(Vertex currentV) {
        boolean roundMade = false;
        while (!roundMade) {
            if (move(currentV, -1, 1)) { // east-north
                break;
            } else if (move(currentV, -1, -1)) { // west-north
                break;
            } else if (move(currentV, 1, 1)) { // east-south
                break;
            } else if(move(currentV, 1, -1)) { // west-south
                break;
            }
            roundMade = true;
        }
    }
    
    private void jumpVerticallyAndHorizontally(Vertex currentV) {
        boolean roundMade = false;
        while (!roundMade) {
            if (move(currentV, 0, 1)) { // right
                break;
            } else if (move(currentV, -1, 0)) { // up
                break;
            } else if (move(currentV, 1, 0)) { // down
                break;
            } else if(move(currentV, 0, -1)) { // left
                break;
            }
            roundMade = true;
        }
    }
    
    
    /**
    * Method for JPS to estimate Euclidean distance.
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

    @Override
    public boolean checkLimits(int[][]map, int r, int c, int rowLength, int columnLength) {
        if (r < 0 || c < 0 || r >= rowLength || c >= columnLength) {
            return false;
        }
        
        if (map[r][c] == 0) {
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
    
    @Override
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