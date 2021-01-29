/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * A Class for the vertices and edges.
 * 
 * @author matibrax
 */

public class Vertice implements Comparable<Vertice>{
    private int row;
    private int column;
    private int distance;
    private Vertice previous;

    public Vertice(int row, int column) {
        this.row = row;
        this.column = column;
        this.distance = 0;
        this.previous = null;
    }
    
    public Vertice(int row, int column, int distance, Vertice previous) {
        this.row = row;
        this.column = column;
        this.distance = 0;
        this.previous = previous;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Vertice getPrevious() {
        return previous;
    }

    @Override   
    public int compareTo(Vertice v2) {
        if (this.distance < v2.distance) {
            return -1;
        } else if (this.distance > v2.distance) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    @Override
    public String toString() {
        return "Vertice: row " + this.row + " column " + this.column;
    }
    
    
}
