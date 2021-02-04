/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import utils.Vertice;

/**
 *
 * Algorithm that searches the shortest path. 
 * 
 * @author matibrax
 */

public class Dijkstra implements SearchInterface {
    public Dijkstra() {
    }
    
    public ArrayList<Vertice> findPath(int[][]map, int startR, int startC, int endR, int endC) {
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
        };*/
        int rowLength = map.length;  
        int columnLength = map[0].length;

        int[][] distance = new int[rowLength][columnLength];
        boolean[][] visited = new boolean[rowLength][columnLength];
        PriorityQueue<Vertice> heap =  new PriorityQueue<>();
        
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < columnLength; c++) {
                distance[r][c] = Integer.MAX_VALUE;
            }
        }
        
        //testing purposes
        if (map[startR][startC] == 0) {
            System.out.println("Starting point is in the wall!");
            return null;
        }
        if(map[endR][endC] == 0) {
            System.out.println("Ending point is in the wall!");
            return null;
        }
        
        //Start and end vertices
        Vertice startPoint = new Vertice(startR, startC);
        distance[startR][startC] = 0;
        
        //Start searching
        heap.add(startPoint);
        while(!heap.isEmpty()) {
            Vertice currentV = heap.poll();
            System.out.println("Handling: " + currentV);
            System.out.println("Distance from the starting point: " + currentV.getDistance());
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
            for (int rowMove = -1; rowMove < 2; rowMove++) {
                for (int columnMove = -1; columnMove < 2; columnMove++) {
                    //if we are at the starting point
                    if(rowMove == 0 && columnMove == 0) {
                        continue;
                    }
                    int moveOneRow = currentRow + rowMove;
                    int moveOneColumn = currentColumn + columnMove;
                    
                    if (!checkLimits(map, moveOneRow, moveOneColumn, rowLength, columnLength)) {
                        continue;
                    }
                    
                    //if there is an obstacle
                    if (map[moveOneRow][moveOneColumn] == 0) {
                        continue;
                    }
                    
                    int nextDistance = currentV.getDistance() + 1;
                    //check, if we have not went to this vertice
                    if(nextDistance < distance[moveOneRow][moveOneColumn]) {
                        distance[moveOneRow][moveOneColumn] = nextDistance;
                        Vertice next = new Vertice(moveOneRow, moveOneColumn, nextDistance, currentV);
                        heap.add(next);
                    }
                }
            }                  
        }
        return null;
    }

    @Override
    public boolean checkLimits(int[][]map, int r, int c, int rowLength, int columnLength) {
        if (r < 0 || c < 0 || r >= rowLength || c >= columnLength) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Vertice> createShortestPath(Vertice vertice) {
        ArrayList<Vertice> shortestPath = new ArrayList<>();
        while (vertice.getPrevious() != null) {
            shortestPath.add(vertice);
            vertice = vertice.getPrevious();
        }
        return shortestPath;
    }
}
