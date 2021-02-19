# Week 5

Time used: 8 + 6 + 6 = 20 hours.

## What have you done this week?

Wednesday: I got A* and Dijkstra working correctly – both finding the same path with same amount of nodes and every unit test passing. Made a couple methods and add-function to the heap. 

Thursday: I completed the heap with unit test passing. I started to change heap to the java version, but there seem to be some problems – Dijkstra is finding sometimes a couple of steps longer path. I'll look into that,

Friday: I debugged almost hole day. 

## How has the project progressed?

The binaryheap is made with comprehensive tests and implemented to the Dijkstra. A* and Dijkstra are finding the shortest path in every map with Java util priorityqueue, but with my heap, not always. There are 0-2 step longer paths with Dijkstra, thus I think I'm close. I also made some documentation and refactoring. 

## What did you learn this week / today?
 
I have repeted my information about the tree structure especially with the binary one. Also the structure of the heap has become quite clear to me. 

## What has been inclear or problematic? 

The biggest problem at the moment is that my heap is not finding the shortest path as A* does as I opened this issue above. There are 0-2 step longer paths with Dijkstra sometimes, thus I think I'm close. 

## What next?

Next week I will find out why my heap is not working correctly and then implement it to the A*. I also will replace ArrayList of Java Utils libary. 