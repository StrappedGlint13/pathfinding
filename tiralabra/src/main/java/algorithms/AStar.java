/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;
import utils.AVertex;

/**
 *
 * Algorithm that searches the shortest path. 
 * 
 * @author matibrax
 */

public class AStar {
    final double diagonalMovement;
    
    
    public AStar() {
        this.diagonalMovement = 1.4142135623730951;
    }
    
    public ArrayList<AVertex> findPath(int[][]map, int startR, int startC, int endR, int endC) {
        //current data structures
        /*
        map = new int[][]{
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
        */
        int rowLength = map.length;  
        int columnLength = map[0].length;

        double[][] distance = new double[rowLength][columnLength];
        boolean[][] visited = new boolean[rowLength][columnLength];
        PriorityQueue<AVertex> heap =  new PriorityQueue<>();
        
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < columnLength; c++) {
                distance[r][c] = Integer.MAX_VALUE;
            }
        }
        //check if the user clicked obstacle
        if (map[startR][startC] == 0 || map[endR][endC] == 0) {
            return new ArrayList<>();
        }
         
        AVertex startPoint = new AVertex(startR, startC, 0, null);
        distance[startR][startC] = heuristics(endR, endC, startR, startC);
        
        //Start searching
        heap.add(startPoint);
        while(!heap.isEmpty()) {
            AVertex currentV = heap.poll();
     
            int currentRow = currentV.getRow();
            int currentColumn = currentV.getColumn();
            
            if (currentRow == endR && currentColumn == endC) {
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
                    
                    if (!checkLimits(map, moveOneRow, moveOneColumn, rowLength, columnLength)) {
                        continue;
                    }
                    
                    //if there is an obstacle
                    if (map[moveOneRow][moveOneColumn] == 0) {
                        continue;
                    }
                    
                    double nextDistance = currentV.getHeuristics() + 1;
                    
                    if (!movingStraight(rowStep, columnStep)) {
                        nextDistance = currentV.getHeuristics() + diagonalMovement;
                    }
            
                    if(nextDistance < distance[moveOneRow][moveOneColumn]) {
                        distance[moveOneRow][moveOneColumn] = nextDistance + heuristics(endR, endC, moveOneRow, moveOneColumn);
                        AVertex next = new AVertex(moveOneRow, moveOneColumn, nextDistance, currentV);
                        heap.add(next);
                    }
                }
            }                  
        }
        return null;
    }
    
    public double heuristics(int endX, int endY, int currentX, int currentY) {
        // Euclidean distance for A*
        return Math.sqrt((currentX-endX)*(currentX-endX) + (currentY-endY)*(currentY-endY));
    }

    public boolean checkLimits(int[][]map, int r, int c, int rowLength, int columnLength) {
        if (r < 0 || c < 0 || r >= rowLength || c >= columnLength) {
            return false;
        }
        return true;
    }

    public ArrayList<AVertex> createShortestPath(AVertex vertice) {
        ArrayList<AVertex> shortestPath = new ArrayList<>();
        while (vertice.getPrevious() != null) {
            shortestPath.add(vertice);
            vertice = vertice.getPrevious();
        }
        
        return shortestPath;
    }
    
    public boolean movingStraight(int moveOneRow, int moveOneColumn) {
        if (moveOneRow == 0 && moveOneColumn == 1) { // right
            return true;
        } else if (moveOneRow == 0 && moveOneColumn == -1) { // left
            return true;
        } else if (moveOneRow == -1 && moveOneColumn == 0) { // down
            return true;
        } else if (moveOneRow == 1 && moveOneColumn == 0) { // up
            return true;
        } else {
            return false;
        }
    }

}
