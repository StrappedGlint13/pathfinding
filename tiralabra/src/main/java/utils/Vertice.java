/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author matibrax
 */
public class Vertice implements Comparable<Vertice>{
    int number;
    int distance;

    public Vertice(int number, int distance) {
        this.number = number;
        this.distance = distance;
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
}
