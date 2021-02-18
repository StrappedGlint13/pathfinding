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
        this.size++;
        int childIndex = this.size-1;
        expandHeap();
        this.heap[childIndex] = v;
        
        while (childIndex > 0) {
            int parentIndex = getParent(childIndex);
            Vertex parent = this.heap[parentIndex];
            
            // comparing distance
            if (v.compareTo(this.heap[parentIndex]) == -1) {
                //swap with parents
                this.heap[parentIndex] = v;
                this.heap[childIndex] = parent;
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
        this.heap[i] = vertexOntheRun;
        this.heap = copyTable();
        int leftChild = getLeftChild(i);
        
        while(leftChild < this.heap.length) {
            int rightChild = getRightChild(i);
            if (rightChild == this.heap.length) {
                Vertex betterCvertex = this.heap[leftChild];
                if (heap[i].compareTo(heap[leftChild])==1) {
                    this.heap[leftChild] = vertexOntheRun;
                    this.heap[i] = betterCvertex;
                    i = leftChild;
                }
                leftChild = Integer.MAX_VALUE;
            } else {    
                int betterChild = heap[leftChild].compareTo(heap[rightChild]) == -1 
                ? leftChild : rightChild;
                Vertex betterCvertex = this.heap[betterChild];
                if (heap[i].compareTo(heap[betterChild]) == 1) {
                    this.heap[betterChild] = vertexOntheRun;
                    this.heap[i] = betterCvertex;
                    i = betterChild;
                    leftChild = getLeftChild(i);
                } else {
                    size--;
                    return this.heap[i];
                }
            }
        }
        size--;
        return root;
    } 
    
    public Vertex[] copyTable() {
        Vertex[] newHeap = new Vertex[size-1];
        for (int i = 0; i < newHeap.length; i++) {
            newHeap[i] = this.heap[i];
        }
        
        if (newHeap.length == 0) {
            newHeap = new Vertex[1];
            return newHeap;
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
        return this.heap[0];
    }
    
    public Vertex getLast() {
        return this.heap[size-1];
    }
    
    @Override
    public String toString() {
        return this.heap[0].toString();
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
        return this.heap[i];
    }
 

}
