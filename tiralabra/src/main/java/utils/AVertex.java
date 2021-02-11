/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * A Class for the vertices in A*
 * 
 * @author matibrax
 */

public class AVertex implements Comparable<AVertex>{
    private int row;
    private int column;
    private double heuristics;
    private AVertex previous;
    
    public AVertex(int row, int column, double heuristics, AVertex previous) {
        this.row = row;
        this.column = column;
        this.heuristics = heuristics;
        this.previous = previous;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public AVertex getPrevious() {
        return previous;
    }

    @Override   
    public int compareTo(AVertex v2) {
        if (this.heuristics < v2.heuristics) {
            return -1;
        } else if (this.heuristics > v2.heuristics) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public double getHeuristics() {
        return heuristics;
    }

    public void setDistance(int distance) {
        this.heuristics = distance;
    }
    @Override
    public String toString() {
        return "Vertice: row " + this.row + " column " + this.column;
    }
    
    
}
