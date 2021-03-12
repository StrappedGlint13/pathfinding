# Project structure

Project builds for three different levels. Below is package-diagram of the program:

<img src="https://github.com/StrappedGlint13/pathfinding/blob/main/documentation/images/package_yuml.png" width="300">

`ui` package contains all the elements that are needed for user interface. These are IOImage, ImageHandler, Main and Map -classes. `algorithms` package contains A* and Dijkstra with methods combining interface SearhInterface. `utis` are package for the data structures. 

## Basic user steps

Program starts from the Main-method, that opens a box for url-input and informs user to put input into text field. Then program uploads an png picture with assumption that user is using 2D-benchmark-maps. Then the resulution is a little fixed for better performance and next scene is the map and instructions box. 

User clicks twice. Algorithms make their magic and giving vertex-list to the ui. Program draws the 3x3 pixel-mazes to the map and shows the shortest paths these algorithms found.

## Complexity and performance of Algorithms

Distances and heuristics preferred to be double- instead of float -data types. I tried both in the program, but the doubles gave much more accurate distances than doubles (talking about the decimals). This became clear to me at the end of the project, as I was comparing distances between the scenarios of the benchmark maps site and my own runs. When I used doubles, I had the same distance as on the scenarios. After that, I had a little better or worse shortest paths – I assume that this was because of the inaccuracy. Doubles has slower operations and require more memory space as their decimals are much longer than floats, but in this project, I preferred floats. 

### Dijkstra and A*

Dijkstra has following structure: 

1.  Add starting point to the heap.
2. *  If heap is not empty, continue examining the best path to the end. Else go to part 8.
3.  Poll the best vertice from the root of the heap.
4.  Check, if this is the ending point. If it is, create the shortest path.
5.  Visit all the neighbours from the current vertex – make sure that vertex is not crossing the borders and there are no obstacle at this point of the map.
6.  Compare current vertex distance to the array that is consisting best distances of the map. 
7.  If the distance is better than the point at the distance table, update the distance table and add the neighbour vertex to the heap.
* Return to 2. part. 
8. Return _null_ that means there are no path from the starting point to the ending point. 

Dijkstras time complexity is O((|n|+|m|)log(|n|)), where n is number of vertices and m is the number of edges. Nets are quite sparse, thus binary heap reduces time complexity. 

A* is following the same principles as Dijkstra, but it also has heuristics in use. When adding new vertices to the heap, it also add information using heuristic function – _euclidean distance_ as known as _bee line_. We can use this, because we know what are the the starting and ending vertices. We use heuristic variable to store euclidean distance and we use this additionally to neighbour straight and diagonal distances in the heap when comparing the vertices in adding opreation of the heap.  

## Jump Point Search

Jump Point Search was the trickiest of 'em all. Jump Point Search is using same heuristics and binary heap as A*, but it is memory constrait as it does not keep distance information in the 2D-table as Dijkstra and A*. Instead, JPS takes these "leaps" with a couple of neighbour pruning rules. This makes it :

1.  We will start moving vertically and horizontally. Horizontally, we will make a check to the up and down. These are "natural neighbours". If there is an obstacle up or down, we will add this vertex to the heap, as this is best way to go diagonally up/down past the obstacle. These are called "forced neighbours". Then we recursively go back to the vertex, from which we start moving. If there is obstacle or map boundraries come from straig, we will recursively go back, thus there are nowhere to go. We will use the same logic vertically, but we look forced neighbours from left and right.  

2.  When we have jumped vertically and horizontally, we start moving diagonally. Here we use following rules:
-   First, we will go diagonally right up. We will check obstacles from down and left. If there are one, we will add this vertex to the heap, and go back recursively to the node we started. Because this is the best vertex to go past the obstacle that we discovered.
-   We go all the diagonal ways like this, changing the checking rule depenging where we came from. 

After these vertical, horizontal and diagonal jumping, we will take the root (best vertex according same heuristics used as A* and distance). And we will do these steps again. If the heap is empty, it means that there is no path to the goal. If we have found the best way and made the list of the vertices, we can return the best shortest path.

## Complexity and performance of Data structures

### Binary heap

Heap has been implemented as _minimum binary heap_. All the algorithms are using the heap, where the saved vertices are in the array of vertices. The heap has four two main operations:

1.  Adding a node to the heap. This takes O(log |V|).
2.  Removing the root from the heap takes O(1) Because there are O(log n) layers, the total for the operation is O(log n).

### List 

List class is here for replacing utils Arraylist. It contains a list of vertices, and a couple of pointers (size and index). It has a few operations:

1.  Add: adding a vertex to the list will take O(1) time, because we are alway putting the new vertex to the last element. 
2.  Get: getting vertex with given index. This takes also only O(1) time. 
3.  IsEmpty: Checking if the first element of list is empty. O(1).

# Possible flaws and improvements

Binary heap is quite good, but With Fibonacci heap, we could improve time complexity to O(nlog(|n| + |m|)) even better. This could make these algorithms very efficient, as all the algorithms are using the heap for comparsion operations. 

# Sources

Amit Patel: [Introduction to the A* Algorithm](https://www.redblobgames.com/pathfinding/a-star/introduction.html), last modified 2020.

Amit Patel: [Introduction to A*](http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html), Last modified 2020. 

Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C: [Introduction to algorithms](https://ebookcentral-proquest-com.libproxy.helsinki.fi), 2009. 

Laaksonen Antti: [Tietorakenteet ja algoritmit](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/tirakirja-2020k.pdf), 2020.

Nathan Witmer: [Jump point search](https://zerowidth.com/2013/a-visual-explanation-of-jump-point-search.html), read 2020. 

Wikipedia: [A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), read 2020. 

Wikipedia: [Jump point search](https://en.wikipedia.org/wiki/Jump_point_search), read 2020. 

Wikipedia: [Dijkstra](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), read 2020. 