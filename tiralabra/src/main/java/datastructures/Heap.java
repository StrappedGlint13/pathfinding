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
        this.heap = new Vertex[1];
        this.size = 0;
    }

    public void add(Vertex v) {
        size++;
        int childIndex = size-1;
        if (heap[0] == null) {
            heap[0] = v;
            return;
        }
        expandHeap();
        heap[childIndex] = v;
        
        while (childIndex > 0) {
            int parentIndex = getParent(childIndex);
            Vertex parent = heap[parentIndex];
            
            // comparing distance
            if (v.getDistance() < heap[parentIndex].getDistance()) {
                //swap with parents
                heap[parentIndex] = v;
                heap[childIndex] = parent;
                childIndex = parentIndex;
            } else {
                break;
            }
        } 
    }
    
    public Vertex poll() {
        Vertex root = getRoot();
        Vertex vertexOntheRun = getLast();
        int i = 0;
        heap[i] = vertexOntheRun;
        heap = copyTable();
        int leftChild = getLeftChild(i);
        
        while(leftChild < heap.length) {
            int rightChild = getRightChild(i);
            if (rightChild == heap.length) {
                Vertex betterCvertex = heap[leftChild];
                if (heap[i].getDistance() > heap[leftChild].getDistance()) {
                    heap[leftChild] = vertexOntheRun;
                    heap[i] = betterCvertex;
                    i = leftChild;
                }
                leftChild = Integer.MAX_VALUE;
            } else {
                int betterChild = heap[leftChild].getDistance() < heap[rightChild].getDistance() 
                ? leftChild : rightChild;
                Vertex betterCvertex = heap[betterChild];
                if (heap[i].getDistance() > heap[betterChild].getDistance()) {
                    heap[betterChild] = vertexOntheRun;
                    heap[i] = betterCvertex;
                    i = betterChild;
                    leftChild = getLeftChild(i);
                }
            }
        }
        size--;
        return root;
    } 
    
    public Vertex[] copyTable() {
        Vertex[] newHeap = new Vertex[size-1];
        
        for (int i = 0; i < newHeap.length; i++) {
            newHeap[i] = heap[i];
        }
        return newHeap;
    }

 
    public void expandHeap() {
        Vertex[] newHeap = new Vertex[size];
        for (int i = 0; i < this.heap.length; i++) {
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
    }
    
    
    public Vertex getRoot() {
        return heap[0];
    }
    
    public Vertex getLast() {
        return heap[size-1];
    }
    
    @Override
    public String toString() {
        return heap[0].toString();
    }
    
    public Vertex changeVertex() {
        Vertex v = new Vertex(0,0);
        return v;
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
        return heap[i];
    }
 

}
