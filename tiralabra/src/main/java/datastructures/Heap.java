package datastructures;

/**
 *
 * Data structure for Min-Binary heap.
 * 
 * @author matibrax
 */

public class Heap {
    private Vertex[] heap;
    private int size;
    
    public Heap() {
        this.heap = new Vertex[1000];
        this.size = 0;
    }
    
    public Vertex[] getHeap() {
        return heap;
    }
    
    /**
    * Method adds a Vertex to the min-heap according its distance or distance + heuristics.
    *
    * @param v Vertex.
    * 
    */

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
    
    /**
    * Method swaps parent and child leaf.
    *
    * @param v1 leaf.
    * @param v2 parent.
    */
    
    public void swap(int v1, int v2) {
        Vertex k = heap[v1];
        heap[v1] = heap[v2];
        heap[v2] = k;
    }
    
    /**
    * Method for returning all the visited notes.
    *
    * 
    * @return the root of the Heap. 
    * 
    */
    
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
    
    /**
    * Method for expanding the heap, if necessary.
    *
    */

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
    
    /**
    * Method check if we have a left child.
    *
    * @param k index. 
    * 
    * @return true if we have a left child, else false.
    */
    
    public boolean hasLeftChild(int k) {
        if (getLeftChild(k) < size) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
    * Method check if we have a right child.
    *
    * @param k index. 
    * 
    * @return true if we have a right child, else false.
    */
    
    public boolean hasRightChild(int k) {
        if (getRightChild(k) < size) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
    * Method check if a leaf has a parent.
    *
    * @param k index. 
    * 
    * @return true if we have a parent, else false.
    */
    
    public boolean hasParent(int k) {
        if (getParent(k) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
    * Method gets a parent of a leaf.
    *
    * @param k index. 
    * 
    * @return parents index.
    */
    
    public int getParent(int k) {
        return (k-1) / 2;
    }
    
    /**
    * Method gets a left leaf of a parent vertex.
    *
    * @param k index. 
    * 
    * @return parents left leaf.
    */
    
    public int getLeftChild(int k) {
        return (2 * k) + 1;
    }
    
    /**
    * Method gets a right leaf of a parent vertex.
    *
    * @param k index. 
    * 
    * @return parents left leaf.
    */
    
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
