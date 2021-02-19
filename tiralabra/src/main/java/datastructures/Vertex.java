/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * A Class for the vertices.
 * 
 * @author matibrax
 */

public class Vertex {
    private int row;
    private int column;
    double distance;
    private Vertex previous;
    private double heuristic; // this is only for A*
    private boolean diagonallyMoved; // this is for comparsion, we are preferring diagonally moving
    
    public Vertex(int row, int column) {
        this.row = row;
        this.column = column;
        this.distance = 0;
        this.previous = null;
        this.heuristic = 0;
        
    }
    
    public Vertex(int row, int column, double distance, Vertex previous, boolean diagonallyMoved) {
        this.row = row;
        this.column = column;
        this.distance = distance;
        this.previous = previous;
        this.heuristic = 0;
        this.diagonallyMoved = diagonallyMoved;
    }

    public boolean isDiagonallyMoved() {
        return diagonallyMoved;
    }

    public void setDiagonallyMoved(boolean diagonallyMoved) {
        this.diagonallyMoved = diagonallyMoved;
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
    
    public boolean getDiagonalyMovement() {
        return diagonallyMoved;
    }
    
    public int compareTo(Vertex v2) {
        if (this.distance < v2.distance || (this.distance == v2.distance && (this.diagonallyMoved
                && !v2.getDiagonalyMovement()))) {
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
        return "Vertice: row " + this.row + " column " + this.column + " distance " + this.distance
                + " diagonallyMoved " + this.diagonallyMoved;
    }
    
    
}
