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
    private float distance;
    private Vertex previous;
    private float heuristic; // this is only for A*
      
    public Vertex(int row, int column) {
        this.row = row;
        this.column = column;
        this.distance = 0;
        this.previous = null;
    }
    
    public Vertex(int row, int column, float distance, Vertex previous) {
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

    public float getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(float heuristic) {
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
        if (this.distance < v2.distance) {
            return -1;
        } else if (this.distance > v2.distance) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public int compareWithHeuristics(Vertex v2) {
        float difference = this.heuristic - v2.getHeuristic();
        float distance = this.distance - v2.getDistance();
        
        if (difference + distance < 0){
            //System.out.println("V2");
            return -1;
        } else if (difference + distance > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public float getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Vertice: row " + this.row + " column " + this.column + " distance " + this.distance;
    }

    public void setPrevious(Vertex previousV) {
        this.previous = previousV;
    }
    
    
}
