/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import datastructures.List;
import datastructures.Vertex;

/**
 *
 * Interface for path finding algorithms.
 * 
 * @author matibrax
 */
public interface SearchInterface {
    boolean checkLimits(int[][]map, int r, int c, int rowLength, int columnLength);
    public List createShortestPath(Vertex vertice);
    public List findPath(int[][]map, int startR, int startC, int endR, int endC);
}
