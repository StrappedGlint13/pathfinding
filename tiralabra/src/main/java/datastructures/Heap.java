package datastructures;

/**
 *
 * A class for Min-HeapTest.
 * 
 * @author matibrax
 */

public class Heap {
    private Vertex[] heap;
    private int size;
    
    public Heap() {
        this.heap = new Vertex[16];
        this.size = 0;
    }

    public Vertex[] getHeap() {
        return heap;
    }

    public void add(Vertex v) {
        if (size + 1 > heap.length) {
            expandHeap();
        }
        this.heap[size] = v;
        size++;
        int k = size-1;
        while ((k > 1) && heap[k].compareTo(heap[getParent(k)]) < 0) {
            swap(k, getParent(k));
            k = getParent(k);
        }
    }
    
    public void swap(int v1, int v2) {
        Vertex k = heap[v1];
        heap[v1] = heap[v2];
        heap[v2] = k;
    }
    
    public Vertex poll() {
        Vertex root = getRoot();
        Vertex lastElement = getLast();
        int k = 0;
        this.heap[k] = lastElement;
        size--;
        
        if (size == k) {
            this.heap[k] = null;
            return root;
        }

        while(hasLeftChild(k)) {
            int rightChild = getRightChild(k);
            int betterChild = getLeftChild(k);
            Vertex rightChildVertex = heap[rightChild];
            Vertex leftChildVertex = heap[betterChild];
            
            if (hasRightChild(k) && rightChildVertex.compareTo(leftChildVertex) < 0) {
                betterChild = rightChild;
            }
            if (heap[k].compareTo(heap[betterChild]) < 0) {
                break;
            } else {
                swap(k, betterChild);
            }
            k = betterChild;
        }
        return root;
    } 

    public void expandHeap() {
        Vertex[] newHeap = new Vertex[size*2];
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        this.heap = newHeap;
    }

    public Vertex getRoot() {
        return this.heap[0];
    }
    
    public Vertex getLast() {
        return this.heap[size-1];
    }
    
    @Override
    public String toString() {
        return this.heap[0].toString();
    }
    
    public boolean hasLeftChild(int k) {
        if (getLeftChild(k) < size) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasRightChild(int k) {
        if (getRightChild(k) < size) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasParent(int k) {
        if (getParent(k) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getParent(int k) {
        return (k-1) / 2;
    }
    
    public int getLeftChild(int k) {
        return (2 * k) + 1;
    }
    
    public int getRightChild(int k) {
        return (2 * k) + 2;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public Vertex getVertexFromIndex(int k) {
        return this.heap[k];
    }
}
