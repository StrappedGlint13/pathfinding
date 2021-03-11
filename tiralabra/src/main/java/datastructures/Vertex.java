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
      
    public Vertex(int row, int column) {
        this.row = row;
        this.column = column;
        this.distance = 0;
        this.previous = null;
    }
    
    public Vertex(int row, int column, double distance, Vertex previous) {
        this.row = row;
        this.column = column;
        this.distance = distance;
        this.previous = previous;
        this.heuristic = 0;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
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
    
    public int compareTo(Vertex v2) {
        //System.out.print("heuristic " + heuristic);
        //System.out.println("vs V2: " + v2.getHeuristic());
        if (this.heuristic > 0 || v2.getHeuristic() > 0) {
            return compareWithHeuristics(v2);
        }
        
        if ((this.heuristic > 0 && this.distance == 0) || (v2.getHeuristic() > 0 && this.distance == 0)) {
            return compareToJPS(v2);
        }
        
        if (this.distance < v2.distance) {
            return -1;
        } else if (this.distance > v2.distance) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public int compareToJPS(Vertex v2) {
        if (this.heuristic < v2.heuristic) {
            return -1;
        } else if (this.heuristic > v2.heuristic) {
            return 1;
        } else {
            return 0;
        }
    }
    
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

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Vertex: row " + this.row + " column " + this.column + " distance " + this.distance;
    }

    public void setPrevious(Vertex previousV) {
        this.previous = previousV;
    }

    public void setDistance(double newDist) {
        this.distance = newDist;
    }
    
    
}
