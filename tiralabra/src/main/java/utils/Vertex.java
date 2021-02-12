/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * A Class for the vertices.
 * 
 * @author matibrax
 */

public class Vertex implements Comparable<Vertex>{
    private int row;
    private int column;
    double distance;
    private Vertex previous;
    private double heuristic;
    
    public Vertex(int row, int column) {
        this.row = row;
        this.column = column;
        this.distance = 0;
        this.previous = null;
        this.heuristic = 0;
    }
    
    public Vertex(int row, int column, double distance, Vertex previous) {
        this.row = row;
        this.column = column;
        this.distance = distance;
        this.previous = previous;
        this.heuristic = 0;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Vertex getPrevious() {
        return previous;
    }

    @Override   
    public int compareTo(Vertex v2) {
        if (this.distance < v2.distance) {
            return -1;
        } else if (this.distance > v2.distance) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public double getDistance() {
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
