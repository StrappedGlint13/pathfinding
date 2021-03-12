package datastructures;

/**
 *
 * Data structure for keeping the vertices of the shortest path
 * 
 * @author matibrax
 */

public class List {
    private Vertex[] list;
    int i;
    int size;
    
    /**
     *
     */
    public List() {
        this.list = new Vertex[1000];
        this.i = -1;
        this.size = 0;
    }
    
    /**
    * Method adds a Vertex to the list to the last index.
    *
    * @param v Vertex.
    * 
    */

    public void add(Vertex v) {
        i++;
        size++;
        
        if (size > list.length) {
            expandList();
        }
        this.list[i] = v;
    }
    
    /**
    * Method for expanding the list, if necessary.
    *
    */
    
    public void expandList() {
        Vertex[] newList = new Vertex[size*2];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        this.list = newList;
    }

    /**
     *
     * @param i
     * @return
     */
    public Vertex getFromIndex(int i) {
        return list[i];
    }
    
    /**
     *
     * @return
     */
    public int size() {
        return this.size;
    }
    
    /**
    * Method for checking if the list is empty.
    *
    * @return true, if the list is empty, else false. 
    */
    
    public boolean isEmpty() {
        if (this.list[0] == null) {
            return true;
        }
        return false;
    }
}
