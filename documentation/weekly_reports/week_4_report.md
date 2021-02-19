# Week 4

Time used: 1 + 8 + 8 = 17 hours.

## What have you done this week?

Wednesday: Read a bit about heuristics and A*. 

Thursday: I changed the heuristic of A* to Euclidean distance, because Manhattan distance can be only used when moving "straight". I managed to get A* to find the shortest path with heuristic (same as Dijkstra), but there seem to be a problem with efficienty. The A* is slower currently, this should be fixed. 

Friday: I made some changes and got A* working efficiently in many cases, unless the feed is large. I also made some documentations: set javadocs properly, testing & implementation documents started. Also started with binary heap. 

## How has the project progressed?

I have implemented the correct heuristics to the A*. Altought, not sure if it's correctly working. Both algorithm A* and Dijkstra handles the same amount of vertices every time, but when I am looking the map, it's still a a bit confusing. 

A* is working faster than Dijkstra in most of the cases. If the feed is bigger (longer distance between the start and the end), Dijkstra is working faster, especially with the labyrinth maps. 

Also started to learn about binary heap. Implemented some skeleton for it. 

## What did you learn this week / today?
 
I learned a lot about heuristics. Also read more about binary heap. 

## What has been inclear or problematic? 

I am not sure are my algorithms working right. They took the same amount of vertices, but my distance calculation is prooving that Dijkstra is not finding the shortest path?? When looking at the visualization, there it looks like the A* is moving longer routes..

## What next?

I will implement binary heap to the project. Also make more documentation, and possible corrections to the dijkstra or A*. If time is left, I'll start looking at JPS. 