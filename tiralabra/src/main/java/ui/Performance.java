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
    long[] averages;
    long[] times;
    double[] distances;
    int daPointer;
    int daJpsPointer;
    int jpsSame;
    int daSame;
    boolean sameIntADacc;
    boolean sameIntADJPSacc;
    int sameDecimal;
    long performance;

    /**
     *
     */
    public Performance () {
    }
    
    /**
     *
     * @param map
     * @param startR
     * @param startC
     * @param endR
     * @param endC
     * @param n
     */
    
    public void processingTimes(int[][]map, int startR, int startC, int endR, int endC, int n) {
        long performanceStart = System.nanoTime();
        Dijkstra dijkstra = new Dijkstra();
        List shortestPathDijkstra = new List();
                        
        AStar aStar = new AStar();
        List shortestPathAStar = new List();
                        
        JPS jps = new JPS();
        List shortestPathJPS = new List();        
        
        this.averages = new long[n];
        this.times = new long[n*3];
        this.distances = new double[n*3];
        daJpsPointer = n*2;
        daPointer = n;
        long tAcc = 0;
                
        long startD = System.nanoTime();
        long startA = System.nanoTime();
        long startJPS = System.nanoTime();

        for (int i = 0; i < n; i++) {
            startD = System.nanoTime();
            shortestPathDijkstra = dijkstra.findPath(map, startR, startC, endR, endC);
            tAcc += System.nanoTime() - startD;
            
            distances[i] = shortestPathDijkstra.getFromIndex(0).getDistance();
        }
        averages[0] = tAcc / n;
        tAcc = 0;
        
        for (int i = n; i < n*2; i++) {
            startA = System.nanoTime();
            shortestPathAStar = aStar.findPath(map, startR, startC, endR, endC);
            tAcc += System.nanoTime() - startA;
            
            distances[i] = shortestPathAStar.getFromIndex(0).getDistance();
        }
        averages[1] = tAcc / n;
        tAcc = 0;
        
        for (int i = n*2; i < n*3; i++) {
            startJPS = System.nanoTime();
            shortestPathJPS = jps.findPath(map, startR, startC, endR, endC);
            tAcc += System.nanoTime() - startJPS;
            
            distances[i] = shortestPathJPS.getFromIndex(0).getDistance();
        }
        averages[2] = tAcc / n;
        
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
        
        String integerAccDijkstra = Double.toString(distances[0]); 
        String integerAccAstar = Double.toString(distances[daPointer-1]);
        String integerAccJPS = Double.toString(distances[daJpsPointer-1]);
        
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
    
    /**
     *
     * @param map
     * @param startR
     * @param startC
     * @param endR
     * @param endC
     * @param n
     */
    public void runPerformance(int[][]map, int startR, int startC, int endR, int endC, int n) {
        Performance p = new Performance();
        p.processingTimes(map, startR, startC, endR, endC, n);
        boolean daRatio = p.getDa(n);
        boolean dajpsRatio = p.getDaJps(n);
        boolean integerAccDa = p.sameIntADacc;
        boolean integerAccDaJPS = p.sameIntADJPSacc;
        long[] times = p.getAverages();
                        
        System.out.println("PERFORMANCE RESULTS. With input of: " + n);
        System.out.println("");
                        
        System.out.println("Average running times in nanoseconds: ");
        System.out.println("Dijkstra runs: " + times[0] + " nanoseconds");
        System.out.println("A* runs: " + times[1] + " nanoseconds");
        System.out.println("JPS runs: " + times[2] + " nanoseconds");
        System.out.println("");
                        
        System.out.println("Average running times in seconds:");
        System.out.println("Dijkstra runs: " + times[0]/1e9 + " seconds");
        System.out.println("A* runs: " + times[1]/1e9 + " seconds");
        System.out.println("JPS runs: " + times[2]/1e9 + " seconds");
        System.out.println("");
                        
        System.out.println("Finding equally shortest paths:");
        System.out.println("");
        System.out.println("Dijkstra and A* found equally long paths to the accuracy of integers: " + integerAccDa);
        System.out.println("All the algorithms found equally long paths to the accuracy of integers: : " + integerAccDaJPS);
        System.out.println("");
        System.out.println("Dijkstra and A* found exactly equal long paths: " + daRatio);
        System.out.println("All the algorithms found exactly equal long paths: " + dajpsRatio);
        if (!dajpsRatio && p.isSameIntADJPSacc()) {
            System.out.println("All the algorithms found the shortest path to the accuracy of " + p.getSameDecimal() + ". decimals");
        }
        System.out.println("");
        System.out.println("Performance time for input " + n + " runs took" + p.getPerformance()/1e9 + " seconds");
        System.out.println("Performance tests ended. Click once for setup, then click twice for new searches.");
    }

    /**
     *
     * @return
     */
    public int getSameDecimal() {
        return sameDecimal;
    }

    /**
     *
     * @return
     */
    public boolean isSameIntADacc() {
        return sameIntADacc;
    }

    /**
     *
     * @return
     */
    public boolean isSameIntADJPSacc() {
        return sameIntADJPSacc;
    }

    /**
     *
     * @param n
     * @return
     */
    public boolean getDa(int n) {
        double ratio = (daSame*100)/n;
        if (ratio == 100.0) {
            return true;
        }
        return true;
    }
    
    /**
     *
     * @return
     */
    public long getPerformance() {
        return performance;
    }

    /**
     *
     * @param n
     * @return
     */
    public boolean getDaJps(int n) {
        double ratio = (jpsSame*100)/n;
        if (ratio == 100.0) {
            return true;
        }
        return false;
    }
    
    /**
     *
     * @return
     */
    public long[] getAverages() {
        return this.averages;
    }
}
