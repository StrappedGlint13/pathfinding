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
    private final double diagonalMovement;
    private boolean[][] visitedForPainting;
    private int[][] map; 
    private int endR;
    private int endC;
    private int rowLen;
    private int colLen;
    List l;
    public Heap heap;
    private boolean[][] forcedneigh;
    
    public JPS () {
        this.diagonalMovement = Math.sqrt(2);
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
    public List findPath(int[][]map, int startR, int startC, int endR, int endC) {
        this.map = map;
        this.endR = endR;
        this.endC = endC;
        this.rowLen = map.length;  
        this.colLen = map[0].length;
        this.l = new List();
        this.heap = new Heap();
        
        visitedForPainting = new boolean[rowLen][colLen];
        forcedneigh = new boolean[rowLen][colLen];
        
        
        //check if the user clicked obstacle
        if (map[startR][startC] == 0 || map[endR][endC] == 0) {
            return null;
        }

        Vertex startPoint = new Vertex(startR, startC, 0, null);
        startPoint.setHeuristic(heuristics(endR, endC, startR, startC));
       
        heap.add(startPoint);
        
        
        while(heap.getVertexFromIndex(0) != null && l.isEmpty()) {  
            /*
            System.out.println("Heap:");
            for (int i = 0; i < heap.getSize(); i++) {
                System.out.println(heap.getVertexFromIndex(i) + "distance estimation:" + 
                       (heap.getVertexFromIndex(i).getHeuristic()));
            }
            System.out.println("");   */ 
            Vertex currentV = heap.poll();
            
            if (foundTheEnd(currentV)) {
                break;
            }
            
            forcedneigh[startR][startC] = true;
            jumpStraight(currentV);
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
    
    private void jumpStraight(Vertex curV) {
        boolean roundMade = false;
        while (!roundMade) {
            if (move(curV, 0, 1)) { // right
                break;
            }
            if (move(curV, -1, 0)) { // up
                break;
            }
            if (move(curV, 1, 0)) { // down
                break;
            }
            if(move(curV, 0, -1)) { // left
                break;
            }
            
            jumpDiagonally(curV);
            roundMade = true;
        }
    }
    
    private void jumpDiagonally(Vertex curV) {
        boolean roundMade = false;
        while (!roundMade) {
            if (moveDiagonalGrids(curV, -1, 1)) { // right-up
                break;
            } else if (moveDiagonalGrids(curV, -1, -1)) { // left-up
                break;
            } else if (moveDiagonalGrids(curV, 1, 1)) { // right-down
                break;
            } else if(moveDiagonalGrids(curV, 1, -1)) { // left-down
                break;
            }
            roundMade = true;
        }
    }
    
    private boolean move(Vertex curV, int movementRow, int movementCol) {
        int nextRow = curV.getRow() + movementRow;
        int nextColumn = curV.getColumn() + movementCol;
        double newDist = curV.getDistance() + 1.0;
        
        if (!checkLimits(map, nextRow, nextColumn, rowLen, colLen) || map[nextRow][nextColumn] == 0) {
            return false;
        }
        
        //if we already checked this vertex
        if (forcedneigh[nextRow][nextColumn]) {
            return false;
        }
        
        Vertex nextStep = new Vertex(nextRow, nextColumn, newDist, curV);
        nextStep.setDistance(newDist);
        nextStep.setHeuristic(heuristics(endR, endC, nextRow, nextColumn));
        
        forcedneigh[nextRow][nextColumn] = true;
        
        if (foundTheEnd(nextStep)) {
            return true; // stop
        }
        
        // make these to one
        // moving right or left, check up or down neighbours
        if ((movementRow == 0 && movementCol == 1) || movementRow == 0 ||
                movementCol == -1) {
            if(checkForcedNeighboursFromTheTopAndBelow(nextStep, movementCol)) {
                return false; // stop
            }
        }
        // moving up or down, check left and right neighbours
        if ((movementRow == 1 && movementCol == 0) || movementRow == -1 && movementCol == 0) {
            if(checkForcedNeighboursFromTheRightAndLeft(nextStep, movementRow)) {
                return false; // stop
            }
        }
 
        return move(nextStep, movementRow, movementCol);
    }
    
        
    private boolean moveDiagonalGrids(Vertex currentV, int movementRow, int movementCol) {
        int nextRow = currentV.getRow() + movementRow;
        int nextColumn = currentV.getColumn() + movementCol;
        double newDist = currentV.getDistance() + diagonalMovement;
          
        if (!checkLimits(map, nextRow, nextColumn, rowLen, colLen)) {
            return false;
        }
        //if we already checked this vertex
        if (forcedneigh[nextRow][nextColumn]) {
            return false;
        }
       
        if(map[nextRow][nextColumn] == 0) {
            if (!forcedneigh[currentV.getRow()][currentV.getColumn()]) {
                heap.add(currentV);
                forcedneigh[currentV.getRow()][currentV.getColumn()] = true;
                visitedForPainting[currentV.getRow()][currentV.getColumn()] = true;
                return false; 
            }
            return false;     
        }
    
        Vertex nextStep = new Vertex(nextRow, nextColumn, newDist, currentV);
        nextStep.setDistance(newDist);
        nextStep.setHeuristic(heuristics(endR, endC, nextRow, nextColumn));
        forcedneigh[nextRow][nextColumn] = true;
        
        
        if (foundTheEnd(nextStep)) {
            return true;
        }
        /*
        if (nextRow == endR && nextColumn == endC) {
            heap.add(nextStep);
            jumped[currentV.getRow()][currentV.getColumn()] = true;
            return false;
        }*/
        
           // moving diagonal up , check right or left and down
        if ((movementRow == -1 && movementCol != 0)) {
            if(forcedNeighboursDiagonalUpRightOrLeftAndDown(nextStep, movementCol)) {
                return false; // stop
            }
        }
        
         // moving diagonal down , check right or left and up
        if ((movementRow == 1 && movementCol != 0)) {
            if(forcedNeighboursDiagonalDownRightOrLeftAndUp(nextStep, movementCol)) {
                return false; // stop
            }
        }

        if (move(nextStep, 0, 1) || move(nextStep, -1, 0)) { // right and up
            return true;
        }

        if (move(nextStep, 0, -1) || move(nextStep, -1, 0)) { // left and up
            return true;
        }

        if (move(nextStep, 0, -1) || move(nextStep, 1, 0)) { // left and down
            return true;
        }

        if (move(nextStep, 0, 1) || move(nextStep, 1, 0)) { // right and down
            return true;
        }

       
        return moveDiagonalGrids(nextStep, movementRow, movementCol);
    }
 
    //moving right or left, checking forced neighbours
    public boolean checkForcedNeighboursFromTheTopAndBelow(Vertex curV, int lOrRCol) {
        int curRow = curV.getRow();
        int curCol = curV.getColumn();
        
        
        if (curRow-1 <= rowLen && curRow-1 >= 0 && curCol >= 0 &&  
                curCol <= colLen) { // check if we are still inside the boundraries
            if (map[curRow-1][curCol] == 0) { // if there is an obstacle up
                curCol += lOrRCol;
                if (checkLimits(map, curRow-1, curCol, rowLen, colLen)) {
                    if (map[curRow-1][curCol] == 1 && !forcedneigh[curRow-1][curCol]) { // if right-up is land
                        curCol = curV.getColumn();
                        visitedForPainting[curRow][curCol] = true;
                        heap.add(curV);
                        return true;
                    }
                }
            }
        }
        curCol = curV.getColumn();
     
        if (curRow+1 <= rowLen && curRow+1 >= 0 && curCol >= 0 &&  
                curCol < colLen) { // check if we are still inside the boundraries
            if (map[curRow+1][curCol] == 0) { // if there is an obstacle down
                curCol += lOrRCol;
                if (checkLimits(map, curRow+1, curCol, rowLen, colLen)) {
                    if (map[curRow+1][curCol] == 1 && !forcedneigh[curRow+1][curCol]) { // if right-down is land
                        curCol = curV.getColumn();
                        visitedForPainting[curRow][curCol] = true;
                        heap.add(curV);
                        return true;
                    }
                }   
            }
        }  
        return false;
    }
    
    public boolean forcedNeighboursDiagonalUpRightOrLeftAndDown(Vertex curV, int lOrRCol) {
        int curRow = curV.getRow();
        int curCol = curV.getColumn();

        // we don't have to check limits here, because we came from this direction.
        if (map[curRow+1][curCol] == 0) { // if there is an obstacle down
                curCol += lOrRCol; //+-1
                if (checkLimits(map, curRow+1, curCol, rowLen, colLen)) {
                    if (map[curRow+1][curCol] == 1) {
                        curCol = curV.getColumn();
                        visitedForPainting[curRow][curCol] = true;
                        heap.add(curV);
                        return true;
                    }
                }
        }
        curCol = curV.getColumn();
        
        // we don't have to check limits here, because we came from this direction.
        if (map[curRow][curCol-lOrRCol] == 0) { // if there is an obstacle left or right
                curCol -= lOrRCol;  //+-1
                if (checkLimits(map, curRow-1, curCol, rowLen, colLen)) {
                    if (map[curRow-1][curCol] == 1) { // if right/left-up is land
                        curCol = curV.getColumn();
                        visitedForPainting[curRow][curCol] = true;
                        heap.add(curV);
                        return true;
                    }
                }   
           
        } 
        return false;
    }
    
    public boolean forcedNeighboursDiagonalDownRightOrLeftAndUp(Vertex curV, int colRightOrLeft) {
        int curRow = curV.getRow();
        int curCol = curV.getColumn();
        
        
        // we don't have to check limits here, because we came from this direction.
        if (map[curRow-1][curCol] == 0) { // if there is an obstacle up
                curCol += colRightOrLeft; //+-1
                if (checkLimits(map, curRow-1, curCol, rowLen, colLen)) {
                    if (map[curRow-1][curCol] == 1) {
                        curCol = curV.getColumn();
                        visitedForPainting[curRow][curCol] = true;
                        heap.add(curV);
                        return true;
                    }
                }
        }
        curCol = curV.getColumn();
        
        // we don't have to check limits here, because we came from this direction.
        if (map[curRow][curCol-colRightOrLeft] == 0) { // if there is an obstacle left or right
                curCol -= colRightOrLeft;  //+-1
                if (checkLimits(map, curRow+1, curCol, rowLen, colLen)) {
                    if (map[curRow+1][curCol] == 1) { // if right-down is land
                        curCol = curV.getColumn();
                        visitedForPainting[curRow][curCol] = true;
                        heap.add(curV);
                        return true;
                    }
                }      
        }  
        return false;
    }
    
    public boolean checkForcedNeighboursFromTheRightAndLeft(Vertex curV, int upOrDown) {
        int curRow = curV.getRow();
        int curCol = curV.getColumn();

        //left
        if (curRow <= rowLen && curRow >= 0 && curCol-1 >= 0 &&  
                curCol-1 <= colLen) { // check if we are still inside the boundraries
            if (map[curRow][curCol-1] == 0) { // if there is an obstacle right
                curRow += upOrDown;
                if (checkLimits(map, curRow, curCol-1, rowLen, colLen)) {
                    if (map[curRow][curCol-1] == 1 && !forcedneigh[curRow][curCol-1]) { // if right-up is land
                        curRow = curV.getRow();
                        visitedForPainting[curRow][curCol] = true;
                        heap.add(curV);
                        return true;
                    }
                }
            }
        }
        
        curRow = curV.getRow();
        
        // right
        if (curRow <= rowLen && curRow >= 0 && curCol+1 >= 0 &&  
                curCol+1 <= colLen) { // check if we are still inside the boundraries
            if (map[curRow][curCol+1] == 0) { // if there is an obstacle down
                curRow += upOrDown;
                
                if (checkLimits(map, curRow, curCol+1, rowLen, colLen)) {
                    if (map[curRow][curCol+1] == 1 && !forcedneigh[curRow][curCol+1]) { // if right-down is land
                        curRow = curV.getRow();
                        visitedForPainting[curRow][curCol] = true;
                        heap.add(curV);
                        return true;
                    }
                }   
            }
        } 
        return false;
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
    
    private double heuristics(int endX, int endY, int currentX, int currentY) {
        // Euclidean distance for A*
        return Math.sqrt(((currentX-endX)*(currentX-endX)) + (currentY-endY)*(currentY-endY));
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
    * Method for returning all the visitedForPainting notes.
    * 
    * @return list of vertex for the shortest path, if there is previous vertex, 
    * call the method again, if null is found from the "previous",
    * we are at the starting vertex. 
    * 
    */
    
    public boolean[][] getVisitedForPainting() {
        return this.visitedForPainting;
    }
    
        
    public Heap getHeap() {
        return heap;
    }

}