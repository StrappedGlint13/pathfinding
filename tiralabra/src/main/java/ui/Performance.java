/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import algorithms.AStar;
import algorithms.Dijkstra;
import algorithms.JPS;
import datastructures.List;

/**
 *
 * Class for testing performance of the program.
 * 
 * @author matibrax
 */

public class Performance {
    Dijkstra dijkstra = new Dijkstra();
    AStar aStar = new AStar();
    JPS jps = new JPS();
    long[] times;
    float[] distances;
    int daPointer;
    int daJpsPointer;
    int jpsSame;
    int daSame;
    boolean sameIntADacc;
    boolean sameIntADJPSacc;
    int sameDecimal;
    long performance;

    
    public Performance () {
    }
    
    public void ProcessingTimes(int[][]map, int startR, int startC, int endR, int endC, int n) {
        long performanceStart = System.nanoTime();
        Dijkstra dijkstra = new Dijkstra();
        List shortestPathDijkstra = new List();
                        
        AStar aStar = new AStar();
        List shortestPathAStar = new List();
                        
        JPS jps = new JPS();
        List shortestPathJPS = new List();        
        
        this.times = new long[n];
        this.distances = new float[n*3];
        daJpsPointer = n*2;
        daPointer = n;
        long tAcc = 0;
                
        long startD = System.nanoTime();
        long startA = System.nanoTime();
        long startJPS = System.nanoTime();;

        for (int i = 0; i < n; i++) {
            startD = System.nanoTime();
            shortestPathDijkstra = dijkstra.findPath(map, startR, startC, endR, endC);
            tAcc += System.nanoTime() - startD;
            
            distances[i] = shortestPathDijkstra.getFromIndex(0).getDistance();
        }
        times[0] = tAcc / n;
        tAcc = 0;
        
        for (int i = n; i < n*2; i++) {
            startA = System.nanoTime();
            shortestPathAStar = aStar.findPath(map, startR, startC, endR, endC);
            tAcc += System.nanoTime() - startA;
            
            distances[i] = shortestPathAStar.getFromIndex(0).getDistance();
        }
        times[1] = tAcc / n;
        tAcc = 0;
        
        for (int i = n*2; i < n*3; i++) {
            startJPS = System.nanoTime();
            shortestPathJPS = jps.findPath(map, startR, startC, endR, endC);
            tAcc += System.nanoTime() - startJPS;
            
            distances[i] = shortestPathJPS.getFromIndex(0).getDistance();
        }
        times[2] = tAcc / n;
        
        daJpsPointer = n*2;
        daPointer = n;

        for (int i = 0; i < n; i++) {
            if (distances[i] == distances[daPointer+i]) {
                 daSame++;
            }
            
            if (distances[i] == distances[daJpsPointer] && distances[daPointer+i] == distances[daJpsPointer]) {
                jpsSame++;
            }
            daPointer++;
            daJpsPointer++; 
        }
        
        String integerAccDijkstra = Float.toString(distances[0]); 
        String integerAccAstar = Float.toString(distances[daPointer-1]);
        String integerAccJPS = Float.toString(distances[daJpsPointer-1]);
        
        int indexOfDecimalD = integerAccDijkstra.indexOf(".");
        int indexOfDecimalA = integerAccAstar.indexOf(".");
        int indexOfDecimalJPS = integerAccJPS.indexOf(".");
        
        String intD = integerAccDijkstra.substring(0, indexOfDecimalD);
        String intA = integerAccAstar.substring(0, indexOfDecimalA);
        String intJPS = integerAccJPS.substring(0, indexOfDecimalJPS);
        
        if (intD.equals(intA)) {
            sameIntADacc = true;
        }
        
        if (sameIntADacc && intD.equals(intJPS)) {
            sameIntADJPSacc = true;
        }
        
        String decimalD = integerAccDijkstra.substring(indexOfDecimalD);
        String decimalJPS = integerAccJPS.substring(indexOfDecimalJPS);
        char[]arrDecD = new char[decimalD.length()-1];
        char[]arrDecJPS = new char[decimalJPS.length()-1];
        
        for (int i = 0; i < decimalD.length()-1; i++) {
            arrDecD[i] = decimalD.charAt(i+1);
        }
        
        for (int i = 0; i < decimalJPS.length()-1; i++) {
            arrDecJPS[i] = decimalJPS.charAt(i+1);
        }
        
        if (!getDaJps(n)) {
            int smallestDecimal = 0;
            
            if (decimalD.length() < decimalJPS.length()) {
                smallestDecimal = arrDecD.length;
            } else {
                smallestDecimal = arrDecJPS.length;
            }
            sameDecimal = 0;
            while (smallestDecimal > sameDecimal) {
                if (arrDecD[sameDecimal] != arrDecJPS[sameDecimal]) {
                    break;
                }
                sameDecimal++;
            }
        }
        performance = System.nanoTime() - performanceStart;
    }

    public int getSameDecimal() {
        return sameDecimal;
    }

    public boolean isSameIntADacc() {
        return sameIntADacc;
    }

    public boolean isSameIntADJPSacc() {
        return sameIntADJPSacc;
    }

    public boolean getDa(int n) {
        double ratio = (daSame*100)/n;
        if (ratio == 100.0) {
            return true;
        }
        return true;
    }
    
    
    public long getPerformance() {
        return performance;
    }

    public boolean getDaJps(int n) {
        double ratio = (jpsSame*100)/n;
        if (ratio == 100.0) {
            return true;
        }
        return false;
    }
    
    public long[] getTimes() {
        return this.times;
    }
}
