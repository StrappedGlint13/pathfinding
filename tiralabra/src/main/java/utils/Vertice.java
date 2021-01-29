/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * A class for the vertices
 * 
 * @param number row of the vertice, known also as "node"
 * @param distance weight of the vertice, known also as "weight"
 * 
 * @author matibrax
 */
public class Vertice implements Comparable<Vertice>{
    private int row;
    private int column;
    private int distance;
    private Vertice pervious;

    public Vertice(int row, int column) {
        this.row = row;
        this.column = column;
        this.distance = 0;
        this.pervious = null;
    }
    
    public Vertice(int number, int column, int distance, Vertice previous) {
        this.row = number;
        this.column = column;
        this.distance = 0;
        this.pervious = null;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Vertice getPervious() {
        return pervious;
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

    public int getNumber() {
        return row;
    }

    public void setNumber(int number) {
        this.row = number;
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
