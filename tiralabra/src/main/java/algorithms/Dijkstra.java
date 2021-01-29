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
 * @author matibrax
 */
public class Dijkstra implements SearchInterface {
    public Dijkstra() {
    }
   
    public ArrayList<Vertice> findPath(int[][]map, int startC, int startR, int endC, int endR) {
        //current data structures
        int n = map.length; 
        int[][] distance = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Vertice> heap =  new PriorityQueue<>();
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
              distance[r][c] = Integer.MAX_VALUE;
            }
        }
        //Start and end vertices
        Vertice startPoint = new Vertice(startC, startR);
        Vertice endPoint = new Vertice(endC, endR);
        distance[startR][startC] = 0;
        
        //Start searching
        heap.add(startPoint);
        while(!heap.isEmpty()) {
            Vertice currentV = heap.poll();
            System.out.println("Next vertice from the heap:"+ currentV);
            
            // If start and end is the same
            if (currentV.equals(endPoint)) {
                return createShortestPath(currentV);
            }
            
            int currentRow = currentV.getRow();
            int currentColumn = currentV.getColumn();
            
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
                    
                    if (!checkLimits(map, moveOneRow, moveOneColumn, n)) {
                        continue;
                    }
                    
                    //if there is an obstacle
                    if (map[moveOneRow][moveOneColumn] == 1) {
                        continue;
                     }
                    
                    int nextDistance = currentV.getDistance() + 1;
                    
                    //check, if the distance is better
                    if(nextDistance < distance[moveOneRow][moveOneColumn]) {
                        distance[moveOneRow][moveOneColumn] = nextDistance;
                        System.out.println("");
                        Vertice next = new Vertice(moveOneRow, moveOneColumn, nextDistance, currentV);
                        System.out.println(next);
                        heap.add(next);
                    }
            }
        }                  
    }
        return null;
}

    @Override
    public boolean checkLimits(int[][]map, int r, int c, int n) {
        if (r < 0 || c < 0 || r >= n || c >= n) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Vertice> createShortestPath(Vertice vertice) {
        ArrayList<Vertice> shortestPath = new ArrayList<>();
        while (vertice != null) {
            shortestPath.add(vertice);
            vertice.getPervious();
        }
        return shortestPath;
    }
}
