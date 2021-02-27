# Project structure

Project builds for three different levels. Below is package-diagram of the program:

<img src="https://github.com/StrappedGlint13/pathfinding/blob/main/documentation/images/package_yuml.png" width="300">

`ui` package contains all the elements that are needed for user interface. These are IOImage, ImageHandler, Main and Map -classes. `algorithms` package contains A* and Dijkstra with methods combining interface SearhInterface. `utis` are package for the data structures. 

## Basic user steps

Program starts from the Main-method, that opens a box for url-input and informs user to put input into text field. Then program uploads an png picture with assumption that user is using 2D-benchmark-maps. Then the resulution is a little fixed for better performance and next scene is the map and instructions box. 

User clicks twice. Algorithms make their magic and giving vertex-list to the ui. Program draws the 3x3 pixel-mazes to the map and shows the shortest paths these algorithms found.

## Complexity and performance 

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

### Binary heap

Heap has been implemented as _minimum binary heap_. All the algorithms are using the heap, where the saved vertices are in the array of vertices. The heap has four main operations:

1.  Adding a node to the heap. This takes O(log n).
2.  Removing the root from the heap takes O(1) Because there are O(log n) layers, the total for the operation is O(log n).

# Possible flaws and improvements

Binary heap is quite good, but With Fibonacci heap, we could improve time complexity to O(nlog(|n| + |m|)) even better. This could make these algorithms very efficient, as all the algorithms are using the heap for comparsion operations.   

# Sources

Amit Patel: [Introduction to the A* Algorithm](https://www.redblobgames.com/pathfinding/a-star/introduction.html), last modified 2020.
tirati
Amit Patel: [Introduction to A*](http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html), Last modified 2020. 

Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C: [Introduction to algorithms](https://ebookcentral-proquest-com.libproxy.helsinki.fi), 2009. 

Laaksonen Antti: [Tietorakenteet ja algoritmit](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/tirakirja-2020k.pdf), 2020.

Wikipedia: [A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), read 2020. 

Wikipedia: [Jump point search](https://en.wikipedia.org/wiki/Jump_point_search), read 2020. 

Wikipedia: [Dijkstra](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), read 2020. 