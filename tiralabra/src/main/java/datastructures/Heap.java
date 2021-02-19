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

    public void add(Vertex v) {
        int childIndex = this.size++;
        if (size > heap.length) {
            expandHeap();
        }
        
        this.heap[childIndex] = v;

        while (childIndex > 0) {
            int parentIndex = getParent(childIndex);
            Vertex parent = this.heap[parentIndex];
            // comparing distance
            if (v.compareTo(this.heap[parentIndex]) < 0) {
                //swap with parent
                this.heap[parentIndex] = v;
                this.heap[childIndex] = parent;
                childIndex = parentIndex;
            } else {
                break;
            }
        } 
    }
    
    public Vertex poll() {
        size--;
        Vertex root = getRoot();
        Vertex vertexOntheRun = getLast();
        int i = 0;
        this.heap[i] = vertexOntheRun;
        int leftChild = getLeftChild(i);
        
        while(leftChild < size) {
            int rightChild = getRightChild(i);
            if (rightChild == size) {
                Vertex betterCvertex = heap[leftChild];
                if (heap[i].compareTo(heap[leftChild]) > 1) {
                    heap[leftChild] = vertexOntheRun;
                    heap[i] = betterCvertex;
                    i = leftChild;
                }
                break;
            } else {    
                int betterChild = heap[leftChild].compareTo(heap[rightChild]) < 0 
                        ? leftChild : rightChild;
                Vertex betterCvertex = heap[betterChild];
                if (heap[i].compareTo(heap[betterChild]) > 0) {
                    heap[betterChild] = vertexOntheRun;
                    heap[i] = betterCvertex;
                    i = betterChild;
                    leftChild = getLeftChild(i);
                } else {
                    return heap[i];
                }
            }
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
        return this.heap[size];
    }
    
    @Override
    public String toString() {
        return this.heap[0].toString();
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
    
    public Vertex getVertexFromIndex(int i) {
        return this.heap[i];
    }
}
