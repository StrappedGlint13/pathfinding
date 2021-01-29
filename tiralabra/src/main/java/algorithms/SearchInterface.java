/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import utils.Vertice;

/**
 *
 * Interface for path finding algorithms.
 * 
 * @author matibrax
 */
public interface SearchInterface {
    boolean checkLimits(int[][]map, int r, int c, int n);
    public ArrayList<Vertice> createShortestPath(Vertice vertice);
}
