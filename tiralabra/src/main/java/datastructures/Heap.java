package datastructures;

/**
 *
 * A class for Min-Heap.
 * 
 * @author matibrax
 */

public class Heap {
    private Vertex[] heap;
    private int size;
    
    public Heap(int size) {
        this.heap = new Vertex[size];
        this.size = 0;
    }
    
    public void add(Vertex v) {
        size++;
        if (heap[0] == null) {
            heap[0] = v;
        }
    }
    
    public Vertex getRoot() {
        return heap[1];
    }
    
    public Vertex changeVertex() {
        Vertex v = new Vertex(0,0);
        return v;
    }
    
    public void remove() {
        size--;
    }
    
    public int getParent(int k) {
        return (k-1) / 2;
    }
    
    public int getLeftChild(int k) {
        return (2 * k) + 1;
    }
    
    public int getRightChild(int k) {
        return (2 * k) + 1;
    }

}
