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

## Test results

Test results can be generated from `pathing/tiralabra` subfolder:

`./gradlew jacocoTestReport` or `gradle jacocoTestReport`

These results can be viewed with chromium-browser (f.ex) from `build/reports/jacoco/test/html/`. 

PIC here

# Performance testing

Performance is tested with different sized maps from 216x216=46656 (n), 512x512=262144(n) and 1024x1024=1048576(n) pixelmaps. Measurement is taken from nodes that have been visited, running time and distance between the starting and ending point. Results are printed to the console. 

We are comparing results between the algorithms and also between the data structures that has been made (f.ex. heap) and with Javas libraries.

Here below is graphic of the performance results on the completed program:

Results of the comparsion showed that..