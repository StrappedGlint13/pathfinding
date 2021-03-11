# Specification

The main target of the project is to make efficient pathing algorithms to find the shortest path with minimal cost. There will be comparsion of the algorithms. 

The algorithms will use _Nathan Sturtevant's Moving AI Lab_ benchmark maps that are focused on problems for 2D grids.This project is done by student of bachelor’s in computer science (CS). All the documenation is in english and programming language is Java in this project. 

# Data structures and algorithms

Algorithms that I chose for the project are: Dijkstra's, A* and Jump point search (JPS) Algorithms. These algorithms share some of the data structures like priorityqueue but are a bit different by their nature. Thus the results of the comparsion should be fruitful. In addition we want to use also algorithms that use "weights" that can be used for simulating different kinds of travelling costs such as swimming, hill climbing, door openings etc. 

**Input**: maps from the Moving AI Lab. On the map, there are locations and connections. Naturally we handle these as a graph made with nodes and edges.

**Output**: the algorithm is a path found from nodes and edges of the chosen graph. The result should be shortest path draw in the chosen map. 

Dijkstra's algorithm is designed for finding the shortest path and is used f.ex in road networks. The Algorithm favors lower cost paths. For this, we will store the costs to min-priority queue. The assumption is that Dijkstra's Algorithm is good for finding path to many locations but for a one path it might be too slow, because it is exploring all the paths. 

A* is an algorithm that exploits Dijkstra's and also greedy Best-First-Search algorithms structures. Best-First-Search will estimate the distance to the goal point and A* is using this data for estimation similary. A* data structure is close to Dijkstra, thus it will also find the shortest path presumably. Jump point search algorithm is better version from A*, because it is using data from its neighbor nodes and making long leaps instead of small steps that A* is performing. Comparing these two algorithms will be intriguing.  

## Time and space complexity targets

Lets consider vertices as _n_ and edges as _m_. *Dijkstra's algorithm* uses the min-priority queue with three operations: INSERT (O(1)), EXTRACT MIN (O(n) and DECREASE-KEY (O(1)). We use arrays for maintaining the vertices numbered 1 to |n|. Total algorithm time complexity is O(n²). If the net is sparse, we could improve the algorithm with binary min-heap with time complexity of O((n+m)log(n)) or even better – Fibonacci heap O(nlog(n) + m).

A* uses the same data structure as Dijkstra, but it also use some time estimation (heuristic) that defines the time complexity. Jump point search would be more faster as it is optimazed version of A*.

# Sources

Amit Patel: [Introduction to the A* Algorithm](https://www.redblobgames.com/pathfinding/a-star/introduction.html), last modified 2020.


Amit Patel: [Introduction to A*](http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html), Last modified 2020. 

Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C: [Introduction to algorithms](https://ebookcentral-proquest-com.libproxy.helsinki.fi), 2009. 

Wikipedia: [A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), read 2020. 

Wikipedia: [Jump point search](https://en.wikipedia.org/wiki/Jump_point_search), read 2020. 

Wikipedia: [Dijkstra](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), read 2020. 

 






