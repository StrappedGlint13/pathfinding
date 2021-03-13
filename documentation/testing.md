# Testing

## Unit tests with JUnit

There are comprehensive automated JUnit tests that tests the program is working correctly all the time. This tests don't focus on testing user inteface and its classes as these are mainly built with variety of ready-made Java libraries. Unit test line-coverage can be found here below with JacocoTestReport, or from the repository. 

### Algorithms

Algorithms are tested with AStarTest, DijkstraTest  & JPSTest test-classes. 

### Data Structures

Heap is tested with HeapTest test class and List is tested with ListTest class. 

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

## Performance testing

Performance is tested with different sized maps from 216x216=46656 (|V|), 512x512=262144(|V|) and 1024x1024=1048576(|V|) pixelmaps. Measurement is taken from running times for the pathfindings. We will cover average times for different sizes of runs. Performance testing is possible also to to from the user interface, as user can set how many runs user wants.

Here are some results about the performance tests with several different maps with different sizes. There are also some data: comparsion between the benchmark scenarios and total running times. 

The highest input were 10 000 runs, that took nearly 45 minutes to run, and the computer were a bit exhausted after the run. Thats why the biggest inputs were 1000. All the runs has been made from the command prompt, and all the programs are closed, unless one mozzilla browser (for accounting):


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

<img src="https://github.com/StrappedGlint13/pathfinding/blob/main/documentation/images/calculationComparsion.png" width="1084">

## Issues raised due to resolution and made some limits for these experiments

These runs raised an issue about the approach to make these performance tests. Due to getting the image from the website, brings it to the program as 1073 x 1073 pixels. This means that if we want to make reliable comparsions with the benchmark maps, we should resize them to the same size. Benchmark scenarios largest images are 1024 x 1024, so these results can not be compare _exactly_. However, resizing the image to the 1024 x 1024 and running the maximum length scenarios will give the most exact distances, and I compare two maps below with almost the same distance. These tests have been ran by netbeans, as it made a small difference between these two boards results.  

Berlin shortest path – 1539.8023074035596 & London shortest path – 1598.427632:
 
 | Map                 | Size        | Algorithm | Average time in sec. | Feed | Average time ns. | Start point | End point  | 2D Benchmark maps | Our Distance       | Total time in seconds |
| ------------------- | ----------- | --------- | -------------------- | ---- | ---------------- | ----------- | ---------- | ----------------- | ------------------ | --------------------- |
| Berlin\_0\_1024.map | 1024 x 1024 | Dijkstra  | 0.188784038          | 10   | 188784038        | 19, 3       | 1005, 1002 | 1539.80230712     | 1539.8023074035596 | 3.767531121           |
| Berlin\_0\_1024.map | 1024 x 1024 | A\*       | 0.174155795          | 10   | 174155795        | 19, 3       | 1005, 1002 | 1539.80230712     | 1539.8023074035596 | 3.767531121           |
| Berlin\_0\_1024.map | 1024 x 1024 | JPS       | 0.01380018           | 10   | 13800180         | 19, 3       | 1005, 1002 | 1539.80230712     | 1539.8023074035596 | 3.767531121           |
| Berlin\_0\_1024.map | 1024 x 1024 | Dijkstra  | 0.170998383          | 100  | 170998383        | 19, 3       | 1005, 1002 | 1539.80230712     | 1539.8023074035596 | 34.500684824          |
| Berlin\_0\_1024.map | 1024 x 1024 | A\*       | 0.163319282          | 100  | 163319282        | 19, 3       | 1005, 1002 | 1539.80230712     | 1539.8023074035596 | 34.500684824          |
| Berlin\_0\_1024.map | 1024 x 1024 | JPS       | 0.010681723          | 100  | 10681723         | 19, 3       | 1005, 1002 | 1539.80230712     | 1539.8023074035596 | 34.500684824          |
| Berlin\_0\_1024.map | 1024 x 1024 | Dijkstra  | 0.176106846          | 1000 | 176106846        | 19, 3       | 1005, 1002 | 1539.80230712     | 1539.8023074035596 | 360.177554994         |
| Berlin\_0\_1024.map | 1024 x 1024 | A\*       | 0.173453825          | 1000 | 173453825        | 19, 3       | 1005, 1002 | 1539.80230712     | 1539.8023074035596 | 360.177554994         |
| Berlin\_0\_1024.map | 1024 x 1024 | JPS       | 0.01060939           | 1000 | 10609390         | 19, 3       | 1005, 1002 | 1539.80230712     | 1539.8023074035596 | 360.177554994         |
| London\_0\_1024.map | 1024 x 1024 | Dijkstra  | 0.170910229          | 10   | 170910229        | 471, 82     | 45, 304    | 1600.52813        | 1598.427632        | 2.564869352           |
| London\_0\_1024.map | 1024 x 1024 | A\*       | 0.076581872          | 10   | 76581872         | 471, 82     | 45, 304    | 1600.52813        | 1598.427632        | 2.564869352           |
| London\_0\_1024.map | 1024 x 1024 | JPS       | 0.008983219          | 10   | 8983219          | 471, 82     | 45, 304    | 1600.52813        | 1598.427632        | 2.564869352           |
| London\_0\_1024.map | 1024 x 1024 | Dijkstra  | 0.173602332          | 100  | 173602332        | 471, 82     | 45, 304    | 1600.52813        | 1598.427632        | 26.268729938          |
| London\_0\_1024.map | 1024 x 1024 | A\*       | 0.079851357          | 100  | 79851357         | 471, 82     | 45, 304    | 1600.52813        | 1598.427632        | 26.268729938          |
| London\_0\_1024.map | 1024 x 1024 | JPS       | 0.009224851          | 100  | 9224851          | 471, 82     | 45, 304    | 1600.52813        | 1598.427632        | 26.268729938          |
| London\_0\_1024.map | 1024 x 1024 | Dijkstra  | 0.173602332          | 1000 | 170451572        | 471, 82     | 45, 304    | 1600.52813        | 1598.427632        | 259.153565624         |
| London\_0\_1024.map | 1024 x 1024 | A\*       | 0.079851357          | 1000 | 79540901         | 471, 82     | 45, 304    | 1600.52813        | 1598.427632        | 259.153565624         |
| London\_0\_1024.map | 1024 x 1024 | JPS       | 0.009224851          | 1000 | 9153109          | 471, 82     | 45, 304    | 1600.52813        | 1598.427632        | 259.153565624         |

<img src="https://github.com/StrappedGlint13/pathfinding/blob/main/documentation/images/londonBerlin.png" width="800">

Biggest differences were at the mazes, but there can be seen that the algorithms found the shortest paths with own eyes. In addition, the difference between the scenes and the projects algorithms are so big, that the resolution probelm is clear as the difference can't be that big.

## Conclusion

According the performance results, the algorithms finds the shortest path in most cases. There are several issues with JPS in the more open fields and large obstacles containing maps, where it performs more greedily. At the mazes it's always finding the shortest path according tests. 

Dijkstra is slowest among the algorithms in almost every maps. At the mazes A* seems to have more trouble, as the heuristic function becomes in useless due to large amount of walls, and especially large routes Dijkstra performns better. JPS is much more faster than these two. It can be seen from the diagrams produced at this document. 

According these two diagrams, input 10 runs most slower, and for some reason 100 feed are the fastest, as 1000 feed runs slower than 100. The differences are quite small, but still.

There are more conclusions regarding performance tests at the implementation document at the improvements and flaws.

Ps. One funny little thing was, that using zoom will almost double the running times (these are not included in the performance tests).  