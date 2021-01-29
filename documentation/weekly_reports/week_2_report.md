# Week 1

Time used: 3 + 5 + 4 + 8 = 20 hours.

## What have you done this week?

Tuesday: I finished configurations with GitHub actions and implemented checkstyle and testing to the project and to the CI-pipeline of the project. 

Wednesday: Todays topic was to create a simple user interface, thus I made a UI, where user can give a link to the PNG-picture (assumption that the user is using 2D maps). Then the 2D map will appear shortly. I also cutted image to 2D pixel-map, where I will handle the pixels (grids) as nodes.

Thursday: I started implementing Dijkstra's algorithm to the program. I also made utils like Vertice and Edge for making a net from pixels. 

Friday: I made preliminary version of Dijkstra that is working and write some tests. 

## How has the project progressed?

I think project has been progressed quite well. I have set configurations well by implementing GitHub actions, Codecov, Javadocs etc. In addition I made one workable (?) algorithm (Dijkstra) with some tests and also created graphic UI for the program. 

## What did you learn this week / today?

I have learned a lot about 2D graphics. Also the Dijkstra's algorithm is much familiar for me now. 2D tables has been always a challenge for me, that is why I chose this subject in the first place. Thus I have learned a lot about 2D tables and next learning step is to test the program efficiently with the tables. 

## What has been inclear or problematic? 

I had a little problems with checkstyle but finally build worked. I am wondering what are suitable checkstyle conditions for the project, that's why the current conditions might be messy a bit. 

Pixel-maps are quite large, so I am wondering is one pixel per one vertice good approach, or should I try to put pixels into a one grid like 2x2 pixel = 1 grid? In addition I am not sure what is the best way to draw the line there. I will try to visualize the algorithm searching progress to the screen, thus this might be the cure. 

## What next?

Next week I will improve the Dijkstra's algorithm and make more tests. As above, I'll make the UI better as well for visualization for the path finding process. If there is time left, I'll start implementing A* star to the program.  


