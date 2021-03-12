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

<img src="https://github.com/StrappedGlint13/pathfinding/blob/main/documentation/images/jacocoTestReport.png" width="300">

# Performance testing

Performance is tested with different sized maps from 216x216=46656 (|V|), 512x512=262144(|V|) and 1024x1024=1048576(|V|) pixelmaps. Measurement is taken from running times for the pathfindings. We will cover average times for different sizes of runs. Performance testing is possible also to to from the user interface, as user can set how many runs user wants. 

We are comparing results between the algorithms below.


Results of the comparsion showed that..