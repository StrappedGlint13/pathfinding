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
    private double distance;
    private Vertex previous;
    private double heuristic; // this is only for A*
      
    /**
     *
     * @param row
     * @param column
     */
    public Vertex(int row, int column) {
        this.row = row;
        this.column = column;
        this.distance = 0;
        this.previous = null;
    }
    
    /**
     *
     * @param row
     * @param column
     * @param distance
     * @param previous
     */
    public Vertex(int row, int column, double distance, Vertex previous) {
        this.row = row;
        this.column = column;
        this.distance = distance;
        this.previous = previous;
        this.heuristic = 0;
    }

    /**
     *
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     *
     * @param column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     *
     * @return
     */
    public double getHeuristic() {
        return heuristic;
    }

    /**
     *
     * @param heuristic
     */
    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    /**
     *
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     *
     * @return
     */
    public Vertex getPrevious() {
        return previous;
    }
    
    /**
     *
     * @param v2
     * @return
     */
    public int compareTo(Vertex v2) {
        if (this.heuristic > 0 || v2.getHeuristic() > 0) {
            return compareWithHeuristics(v2);
        }
        
        if (this.distance < v2.distance) {
            return -1;
        } else if (this.distance > v2.distance) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @param v2
     * @return
     */
    public int compareWithHeuristics(Vertex v2) {
        double difference = this.heuristic - v2.getHeuristic();
        double distance = this.distance - v2.getDistance();
        
        if (difference + distance < 0){
            return -1;
        } else if (difference + distance > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @return
     */
    public double getDistance() {
        return distance;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Vertex: row " + this.row + " column " + this.column + " distance " + this.distance;
    }

    /**
     *
     * @param previousV
     */
    public void setPrevious(Vertex previousV) {
        this.previous = previousV;
    }

    /**
     *
     * @param newDist
     */
    public void setDistance(double newDist) {
        this.distance = newDist;
    }
    
    
}