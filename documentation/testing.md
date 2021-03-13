# Testing

# Unit testing with JUnit

There are comprehensive automated JUnit tests that tests the program is working correctly all the time. This tests don't focus on testing user inteface and its classes as these are mainly built with variety of ready-made Java libraries. 

## Algorithms

Algorithms are tested with AStarTest and DijkstraTest test classes. 

## Data Structures

Heap is tested with HeapTest test class. 

## How to test?

Assumption is that the user has cloned this repository.

### Gradle

 Tests can be run from `pathing/tiralabra` subfolder:

`./gradlew test` or `gradle test`

That depends what version of gradle user has. 

### Netbeans

Tests can be run via netbeans from navigation bar `Run` and then `Test project` or shortcut `Alt+F6`.

## Unit Test results

Test results can be generated from `pathing/tiralabra` subfolder:

`./gradlew jacocoTestReport` or `gradle jacocoTestReport`

These results can be viewed with chromium-browser (f.ex) from `build/reports/jacoco/test/html/`. 

<img src="https://github.com/StrappedGlint13/pathfinding/blob/main/documentation/images/jacocoTestReport.png" width="600">

# Performance testing

Performance is tested with different sized maps from 216x216=46656 (|V|), 512x512=262144(|V|) and 1024x1024=1048576(|V|) pixelmaps. Measurement is taken from running times for the pathfindings. We will cover average times for different sizes of runs. Performance testing is possible also to to from the user interface, as user can set how many runs user wants. 

Here are some results about the performance tests with several different maps with different sizes:


| Map                 | Size        | Feed  | Algorithm | Average time in sec. | Average time ns. | Start point | End point | 2D Benchmark maps | Our Distance | Total time in seconds |
| ------------------- | ----------- | ----- | --------- | -------------------- | ---------------- | ----------- | --------- | ----------------- | ------------ | --------------------- |
| Berlin\_0\_1024.map | 1024 x 1024 | 10    | Dijkstra  | 0.187566279          | 187566279        | 747, 237    | 173, 867  | 943.86919         | 943.86919    | 2.510023399           |
| Berlin\_0\_1024.map | 1024 x 1024 | 10    | A\*       | 0.056022921          | 56022921         | 747, 237    | 173, 867  | 943.86919         | 943.86919    | 2.510023399           |
| Berlin\_0\_1024.map | 1024 x 1024 | 10    | JPS       | 0.007403622          | 7403622          | 747, 237    | 173, 867  | 943.86919         | 943.86919    | 2.510023399           |
| London\_2\_512.map  | 512x512     | 10    | Dijkstra  | 0.04178452           | 41784520         | 58, 2       | 506, 482  | 752.15346         | 748.98188    | 0.58418128            |
| London\_2\_512.map  | 512x512     | 10    | A\*       | 0.013686737          | 13686737         | 58, 2       | 506, 482  | 752.15346         | 748.98188    | 0.58418128            |
| London\_2\_512.map  | 512x512     | 10    | JPS       | 0.002932686          | 2932686          | 58, 2       | 506, 482  | 752.15346         | 748.98188    | 0.58418128            |
| London\_2\_512.map  | 512x512     | 10    | Dijkstra  | 0.040783174          | 40783174         | 362, 93     | 37, 485   | 645.83261         | 644.70267    | 0.627093081           |
| London\_2\_512.map  | 512x512     | 10    | A\*       | 0.020119438          | 1798800          | 362, 93     | 37, 485   | 645.83261         | 644.70267    | 0.627093081           |
| London\_2\_512.map  | 512x512     | 10    | JPS       | 0.0017988            | 17988            | 362, 93     | 37, 485   | 645.83261         | 644.70267    | 0.627093081           |
| maze512-16-0.map    | 1024 x 1024 | 10    | Dijkstra  | 0.163180109          | 163180109        | 501, 498    | 133, 253  | 2440.12453        | 3356.19004   | 3.73347065            |
| maze512-16-0.map    | 1024 x 1024 | 10    | A\*       | 0.194295985          | 194295985        | 501, 498    | 133, 253  | 2440.12453        | 3356.19004   | 3.73347065            |
| maze512-16-0.map    | 1024 x 1024 | 10    | JPS       | 0.01585386           | 15853860         | 501, 498    | 133, 253  | 2440.12453        | 3356.19004   | 3.73347065            |
| Cauldron.map        | 1024 x 1024 | 10    | Dijkstra  | 0.144773818          | 144773818        | 212, 237    | 866, 933  | 1268.62864        | 1268.04285   | 2.581237361           |
| Cauldron.map        | 1024 x 1024 | 10    | A\*       | 0.117330432          | 117330432        | 212, 237    | 866, 933  | 1268.62864        | 1268.04285   | 2.581237361           |
| Cauldron.map        | 1024 x 1024 | 10    | JPS       | 0.10021645           | 10021645         | 212, 237    | 866, 933  | 1268.62864        | 1268.04285   | 2.581237361           |
| Berlin\_0\_1024.map | 1024 x 1024 | 100   | Dijkstra  | 0.192712579          | 192712579        | 747, 237    | 173, 867  | 943.86919         | 943.86919    | 25.957602227          |
| Berlin\_0\_1024.map | 1024 x 1024 | 100   | A\*       | 0.059515871          | 59515871         | 747, 237    | 173, 867  | 943.86919         | 943.86919    | 25.957602227          |
| Berlin\_0\_1024.map | 1024 x 1024 | 100   | JPS       | 0.007340058          | 7340058          | 747, 237    | 173, 867  | 943.86919         | 943.86919    | 25.957602227          |
| London\_2\_512.map  | 512x512     | 100   | Dijkstra  | 0.040783174          | 40783174         | 362, 93     | 37, 485   | 645.83261         | 644.70267    | 0.627093081           |
| London\_2\_512.map  | 512x512     | 100   | A\*       | 0.020119438          | 1798800          | 362, 93     | 37, 485   | 645.83261         | 644.70267    | 0.627093081           |
| London\_2\_512.map  | 512x512     | 100   | JPS       | 0.0017988            | 17988            | 362, 93     | 37, 485   | 645.83261         | 644.70267    | 0.627093081           |
| Cauldron.map        | 1024 x 1024 | 100   | Dijkstra  | 0.139923177          | 139923177        | 212, 237    | 866, 933  | 1268.62864        | 1268.04285   | 27.138230236          |
| Cauldron.map        | 1024 x 1024 | 100   | A\*       | 0.122901434          | 122901434        | 212, 237    | 866, 933  | 1268.62864        | 1268.04285   | 27.138230236          |
| Cauldron.map        | 1024 x 1024 | 100   | JPS       | 0.00854894           | 8548940          | 212, 237    | 866, 933  | 1268.62864        | 1268.04285   | 27.138230236          |
| Cauldron.map        | 1024 x 1024 | 1000  | Dijkstra  | 0.139353151          | 139353151        | 212, 237    | 866, 933  | 1268.62864        | 1268.04285   | 273.537730643         |
| Cauldron.map        | 1024 x 1024 | 1000  | A\*       | 0.12658332           | 12658332         | 212, 237    | 866, 933  | 1268.62864        | 1268.04285   | 273.537730643         |
| Cauldron.map        | 1024 x 1024 | 1000  | JPS       | 0.007593093          | 7593093          | 212, 237    | 866, 933  | 1268.62864        | 1268.04285   | 273.537730643         |
| Berlin\_0\_1024.map | 1024 x 1024 | 10000 | Dijkstra  | 0.193780778          | 192712579        | 747, 237    | 173, 867  | 943.86919         | 943.86919    | 2651.987484442        |
| Berlin\_0\_1024.map | 1024 x 1024 | 10000 | A\*       | 0.063629552          | 59515871         | 747, 237    | 173, 867  | 943.86919         | 943.86919    | 2651.987484442        |
| Berlin\_0\_1024.map | 1024 x 1024 | 10000 | JPS       | 0.007781747          | 7340058          | 747, 237    | 173, 867  | 943.86919         | 943.86919    | 2651.987484442        |


Here below are some comparsion about the above runs with these algorithms:

<img src="https://github.com/StrappedGlint13/pathfinding/blob/main/documentation/images/calculationComparsion.png" width="600">

## Issues raised due to resolution

This raised an issue about the approach to make these performance tests. Due to getting the image from the website, brings it to the program as 1073 x 1073 pixels. This means that if we want to make reliable comparsions with the benchmark maps, we should resize them to the same size. Benchmark scenarios largest images are 1024 x 1024, so these results can not be compare _exactly_. However, resizing the image to the 1024 x 1024 and running the maximum length scenarios will give the most exact distances

Biggest differences were at the mazes, but there can be seen that the algorithms found the shortest paths with own eyes. In addition, the difference between the scenes and the projects algorithms are so big, that the resolution probelm is clear as it can't be that big difference.

## 

At the implementation document, there is more about how to get the input for the project properly, if the main goal is to compare distance with the scenes distances.  