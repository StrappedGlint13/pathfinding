# Project structure

Project builds for three different levels. Below is package-diagram of the program:

<img src="https://github.com/StrappedGlint13/pathfinding/blob/main/documentation/images/package_yuml.png" width="300")>

`ui` package contains all the elements that are needed for user interface. These are IOImage, ImageHandler, Main and Map -classes. `algorithms` package contains A* and Dijkstra with methods combining interface SearhInterface. `utis` are package for the data structures. 

## Basic user steps

Program starts from the Main-method, that opens a box for url-input and informs user to put input into text field. Then program uploads an png picture with assumption that user is using 2D-benchmark-maps. Then the resulution is a little fixed for better performance and next scene is the map and instructions box. 

User clicks twice. Algorithms make their magic and giving vertex-list to the ui. Program draws the 3x3 pixel-mazes to the map and shows the shortest paths these algorithms found.

# Implemented time and space complexities (big-O complexity analysis of (pseudo)code)

# Comparative performance and complexity analysis if applicable

# Possible flaws and improvements

# Sources
