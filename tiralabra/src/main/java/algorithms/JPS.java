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
    private final float diagonalMovement;
    private boolean[][] visited;
    private int[][] map; 
    private int endR;
    private int endC;
    private int rowLength;
    private int columnLength;
    List l;
    public Heap heap;
    private boolean[][] jumped;
    
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
        this.heap = new Heap();
        
        visited = new boolean[rowLength][columnLength];
        jumped = new boolean[rowLength][columnLength];
        
        
        //check if the user clicked obstacle
        if (map[startR][startC] == 0 || map[endR][endC] == 0) {
            return null;
        }

        Vertex startPoint = new Vertex(startR, startC, 0, null);
        startPoint.setHeuristic(heuristics(endR, endC, startR, startC));
        jumped[startR][startR] = true;
        heap.add(startPoint);
        
        while(heap.getVertexFromIndex(0) != null && l.isEmpty()) {
            /*System.out.println("Heap:");
            for (int i = 0; i < heap.getSize(); i++) {
                System.out.println(heap.getVertexFromIndex(i) + "heuristics" + 
                       heap.getVertexFromIndex(i).getHeuristic());
            }*/
            
            Vertex currentV = heap.poll();
            
            /*System.out.println("POLLED VERTICE: " +currentV + "heuristics" + 
                       currentV.getHeuristic());*/
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
    
    //moving right or left, checking forced neighbours
    //Logic is that when we are moving right or left, we have row = 0, but column +1 or -1
    // When we made the check from top and below we change this so that
    // row = +-1 and column = 0
    // If we are moving down or up this is contrary:
    // row = +1 and column = 0 – is the way we are moving
    
    //moving right or left, checking forced neighbours
    public boolean checkForcedNeighboursFromTheTopAndBelow(Vertex currentV, int leftOrRight) {
        int currentRow = currentV.getRow();
        int currentColumn = currentV.getColumn();
        float distance = currentV.getDistance() + diagonalMovement;
        boolean add = false; //if up or down is obstacle
        
        if (currentRow-1 <= rowLength && currentRow-1 >= 0 && currentColumn >= 0 &&  
                currentColumn <= columnLength) { // check if we are still inside the boundraries
            if (map[currentRow-1][currentColumn] == 0) { // if there is an obstacle up
                add = true;
                if (checkLimits(map, currentRow-1, leftOrRight, rowLength, columnLength)) {
                    if (map[currentRow-1][leftOrRight] == 1 && visited[currentRow-1][leftOrRight] == false) { // if right-up is land
                        Vertex rightUpNeighbour = new Vertex(currentRow-1, leftOrRight, distance, currentV); 
                        rightUpNeighbour.setHeuristic(distance + heuristics(endR, endC, currentRow-1, leftOrRight));
                        jumped[rightUpNeighbour.getRow()][rightUpNeighbour.getColumn()] = true;
                        heap.add(rightUpNeighbour);
                    }
                }
            }
        }
     
        if (currentRow+1 < rowLength && currentRow+1 >= 0 && currentColumn >= 0 &&  
                currentColumn < columnLength) { // check if we are still inside the boundraries
            if (map[currentRow+1][currentColumn] == 0) { // if there is an obstacle down
                add = true;
                
                
                if (checkLimits(map, currentRow+1, leftOrRight, rowLength, columnLength)) {
                    if (map[currentRow+1][leftOrRight] == 1 && visited[currentRow+1][leftOrRight] == false) { // if right-down is land
                        Vertex rightDownNeighbour = new Vertex(currentRow+1, leftOrRight, distance, currentV); 
                        rightDownNeighbour.setHeuristic(distance + heuristics(endR, endC, currentRow+1, leftOrRight));
                        jumped[rightDownNeighbour.getRow()][rightDownNeighbour.getColumn()] = true;
                        heap.add(rightDownNeighbour);
                    }
                }   
            }
        } 
        
        if (add == true) {
            jumped[currentV.getRow()][currentV.getColumn()] = true;
            heap.add(currentV);
        }
        return add;
    }
    
    public boolean checkForcedNeighboursFromTheRightAndLeft(Vertex currentV, int upOrDown) {
        int currentRow = currentV.getRow();
        int currentColumn = currentV.getColumn();
        float distance = currentV.getDistance() + diagonalMovement;
        boolean add = false; //if up or down is obstacle
        
        //left
        if (currentRow <= rowLength && currentRow >= 0 && currentColumn-1 >= 0 &&  
                currentColumn-1 <= columnLength) { // check if we are still inside the boundraries
            if (map[currentRow][currentColumn-1] == 0) { // if there is an obstacle right
                add = true;
                if (checkLimits(map, upOrDown, currentColumn-1, rowLength, columnLength)) {
                    if (map[upOrDown][currentColumn-1] == 1 && visited[upOrDown][currentColumn-1] == false) { // if right-up is land
                        Vertex leftNeighbour = new Vertex(upOrDown, currentColumn-1, distance, currentV); 
                        leftNeighbour.setHeuristic(distance + heuristics(endR, endC, upOrDown, currentColumn-1));
                        jumped[leftNeighbour.getRow()][leftNeighbour.getColumn()] = true;
                        heap.add(leftNeighbour);
                    }
                }
            }
        }
        // right
        if (currentRow < rowLength && currentRow >= 0 && currentColumn+1 >= 0 &&  
                currentColumn+1 < columnLength) { // check if we are still inside the boundraries
            if (map[currentRow][currentColumn+1] == 0) { // if there is an obstacle down
                add = true;
                
                if (checkLimits(map, upOrDown, currentColumn+1, rowLength, columnLength)) {
                    if (map[upOrDown][currentColumn+1] == 1 && visited[upOrDown][currentColumn+1]) { // if right-down is land
                        Vertex rightNeighbour = new Vertex(upOrDown, currentColumn+1, distance, currentV); 
                        rightNeighbour.setHeuristic(distance + heuristics(endR, endC, upOrDown, currentColumn+1));
                        jumped[rightNeighbour.getRow()][rightNeighbour.getColumn()] = true;
                        heap.add(rightNeighbour);
                    }
                }   
            }
        } 
        
        if (add == true) {
            jumped[currentV.getRow()][currentV.getColumn()] = true;
            heap.add(currentV);
        }
        return add;
    }

   
    
    private boolean move(Vertex currentV, int movementRow, int movementCol) {
        int nextRow = currentV.getRow() + movementRow;
        int nextColumn = currentV.getColumn() + movementCol;
        float newDistance = currentV.getDistance() + 1;
        
        //System.out.println("Move vertically and hor Current vertex row: " + nextRow + " column: "  + nextColumn);
            
        if (!checkLimits(map, nextRow, nextColumn, rowLength, columnLength) || map[nextRow][nextColumn] == 0) {
            return false;
        }
        
        //if we already checked this vertex
        if (visited[nextRow][nextColumn]) {
            return false;
        }
        
        Vertex nextStep = new Vertex(nextRow, nextColumn, newDistance, currentV);
        nextStep.setHeuristic(newDistance + heuristics(endR, endC, nextRow, nextColumn));
        
        visited[nextRow][nextColumn] = true;
        
        if(checkForcedNeighboursFromTheTopAndBelow(nextStep, movementCol)) {
            return false; // stop
        }
        
        if(checkForcedNeighboursFromTheRightAndLeft(nextStep, movementRow)) {
            return false; // stop
        }
        
        if (foundTheEnd(nextStep)) {
            return true; // stop
        }

        return move(nextStep, movementRow, movementCol);
    }
    
        
    private boolean moveDiagonalGrids(Vertex currentV, int movementRow, int movementCol) {
        int nextRow = currentV.getRow() + movementRow;
        int nextColumn = currentV.getColumn() + movementCol;
        float newDistance = currentV.getDistance() + diagonalMovement;
        
        //System.out.println("Move diagonally after first Current vertex row: " + nextRow + " column: "  + nextColumn);
            
        if (!checkLimits(map, nextRow, nextColumn, rowLength, columnLength) || map[nextRow][nextColumn] == 0) {
            return false;
        }
        //if we already checked this vertex
        if (visited[nextRow][nextColumn]) {
            return false;
        }
        
             
        Vertex nextStep = new Vertex(nextRow, nextColumn, newDistance, currentV);
        visited[nextRow][nextColumn] = true;
        if (foundTheEnd(nextStep)) {
            return true;
        } else {
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
        }
       
        return moveDiagonalGrids(nextStep, movementRow, movementCol);
    }

    private void jumpDiagonally(Vertex currentV) {
        boolean roundMade = false;
        while (!roundMade) {
            if (moveDiagonalGrids(currentV, -1, 1)) { // right-up
                break;
            } else if (moveDiagonalGrids(currentV, -1, -1)) { // left-up
                break;
            } else if (moveDiagonalGrids(currentV, 1, 1)) { // right-down
                break;
            } else if(moveDiagonalGrids(currentV, 1, -1)) { // left-down
                break;
            }
            roundMade = true;
        }
    }
    
    private void jumpStraight(Vertex currentV) {
        boolean roundMade = false;
        while (!roundMade) {
            if (move(currentV, 0, 1)) { // right
                break;
            }
            if (move(currentV, -1, 0)) { // up
                break;
            }
            if (move(currentV, 1, 0)) { // down
                break;
            }
            if(move(currentV, 0, -1)) { // left
                break;
            }
            
            jumpDiagonally(currentV);
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
        // This is for walls, that are not interesting
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
            /*System.out.println("Shortest path");
            System.out.println(vertice);*/
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
        return this.jumped;
    }
    
        
    public Heap getHeap() {
        return heap;
    }

}