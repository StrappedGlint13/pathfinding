package ui;

import algorithms.AStar;
import algorithms.Dijkstra;
import algorithms.JPS;
import datastructures.List;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import static javax.swing.JOptionPane.showMessageDialog;


/**
 * A Class for user interface.
 * 
 * 
 * @author matibrax
 */

public class Main extends Application {

    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Button revealTheMapButton = new Button("Reveal the map");
        TextField textfield = new TextField("https://www.movingai.com/benchmarks/street/Berlin_0_1024.png");
        textfield.setPrefWidth(450);
        TextField inputField = new TextField("0");
       
        HBox searchComponents = new HBox(50);
        VBox textFields = new VBox(10);
        VBox labels = new VBox(10);
        searchComponents.setStyle("-fx-background-color:POWDERBLUE");
        Label searchLabel = new Label("URL of the map (PNG)");
        Label inputLabel = new Label("Input for the performance tests \n"
                + "(optional)");
        searchLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        inputLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        labels.getChildren().addAll(searchLabel, inputLabel);
        textFields.getChildren().addAll(textfield, inputField);
        searchComponents.getChildren().addAll(labels, textFields, revealTheMapButton);
        
        Scene searchPanel = new Scene(searchComponents);
        stage.setScene(searchPanel);
        stage.show();
     
        Label header = new Label("Instructions");
        header.setFont(Font.font("Arial", FontWeight.BOLD,  20)); 
        Label instructions = new Label("1. click is the start.\n"
                + "2. click is the finish.\n"
                + "3. click will setup next search.\n\n"
                + "You are able to see your \n"
                + "coordinates at the bottom \n"
                + "left corner of the window.\n"
                + "In addition, you are able to\n"
                + "see your searches with additional info\n"
                + "about the algorithms \n"
                + "at the command line!");
        GridPane instrSetup = new GridPane();
        Button returnbutton = new Button("Return to the start window");
        
        instrSetup.setStyle("-fx-background-color:POWDERBLUE");
        instrSetup.add(header, 0, 1);
        instrSetup.add(instructions, 0, 2);
        instrSetup.add(returnbutton, 0, 3);
        instrSetup.setPrefSize(300, 600);
        instrSetup.setAlignment(Pos.BASELINE_RIGHT);
        instrSetup.setVgap(10);
        instrSetup.setHgap(10);
        instrSetup.setPadding(new Insets(20, 20, 0, 20));
        
        GridPane manualSetup = new GridPane();
        
        Label header2 = new Label("Manual search");
        header2.setFont(Font.font("Arial", FontWeight.BOLD,  20));
        Label instructions2 = new Label("Here you are able to insert \n"
                + "the parameters manually.\n");
        
        VBox manualParameters = new VBox();
        
        TextField xStart = new TextField("747");
        Label xS = new Label("Start row");
        TextField yStart = new TextField("237");
        Label yS = new Label("Start column");   
        TextField xEnd = new TextField("173");
        Label xE = new Label("End row");
        TextField yEnd = new TextField("867");
        Label yE = new Label("Start column");
        
        Button manualButton = new Button("Start search");
        manualParameters.getChildren().addAll(xS, xStart, yS, yStart, 
                xE, xEnd, yE, yEnd);
        manualSetup.add(header2, 0, 0);
        manualSetup.add(instructions2, 0, 1);
        manualSetup.add(manualParameters, 0, 2);
        manualSetup.add(manualButton, 0, 3);
        manualSetup.setVgap(10);
        manualSetup.setHgap(10);
        
        instrSetup.add(manualSetup, 0, 4);
        
        Scene instrPanel = new Scene(instrSetup);
        
        stage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            System.exit(0);
        });

        manualButton.setOnAction(e -> {
            String input = inputField.getText();
            try {
                int inPut = Integer.parseInt(input);
            }catch (NumberFormatException ex) {
                showMessageDialog(null, "Enter positive integer");
            }

            String url = textfield.getText();
            int inPut = Integer.parseInt(input);
            IOimage io = new IOimage();
            ImageHandler imgHand = new ImageHandler();
            BufferedImage img = io.readImage(url);

            int height = 1024;
            int width = 1024;

            img = imgHand.makeNewFrame(img, height, width);
            Map map = new Map(img, height, width);
            int[][] pixelmap = map.generateMaps();
              
            JFrame manualFrame = new JFrame("Manual search:");

            manualFrame.setSize(height, width);
            JLabel label = new JLabel(new ImageIcon(img));
            manualFrame.add(label);

            JTextField coordinates = new JTextField();
            coordinates.setText("Start point: not set"); 

            int startX = Integer.parseInt(xStart.getText().toString());
            int startY = Integer.parseInt(yStart.getText().toString());
            int endX = Integer.parseInt(xEnd.getText().toString());
            int endY = Integer.parseInt(yEnd.getText().toString());
            
            if (pixelmap[startX][startY] == 0 || pixelmap[endX][endY] == 0) {
                showMessageDialog(null, "This is obstacle!");
                return;
            }
            
            JTextField manualCoordinates = new JTextField();
            manualCoordinates.setText("Shortest path from coordinates " 
                + startX + " , " + startY + " to " + endX + " , " + endY);

            Performance p = new Performance();
            //Algorithms
            Dijkstra dijkstra = new Dijkstra();
            List shortestPathDijkstra = new List();

            AStar aStar = new AStar();
            List shortestPathAStar = new List();

            JPS jps = new JPS();
            List shortestPathJPS = new List();

            System.out.println("Manual search");
            System.out.println("");

            long startA = System.nanoTime();
            shortestPathAStar = aStar.findPath(pixelmap, startX, startY, endX, endY);
            long endA= System.nanoTime();

            long startD = System.nanoTime();
            shortestPathDijkstra = dijkstra.findPath(pixelmap, startX, startY, endX, endY);
            long endD = System.nanoTime();

            long startJPS = System.nanoTime();
            shortestPathJPS = jps.findPath(pixelmap, startX, startY, endX, endY);
            long endJPS = System.nanoTime();

            System.out.println("A* runs " +((endA-startA)/1e9)+ " seconds");
            System.out.println("Dijkstra runs " +((endD - startD)/1e9) + " seconds");
            System.out.println("Jump Point Search path (JPS) runs " +((endJPS - startJPS)/1e9) + " seconds");
            System.out.println("");

            System.out.println("Number of vertices in A* path: " + shortestPathAStar.size());
            System.out.println("Distance from the start: " + shortestPathAStar.getFromIndex(0).getDistance());
            System.out.println("");
            
            if (shortestPathAStar == null) {
                showMessageDialog(null, "There is no path between the starting and ending point you chose.");  
                startX = -1;
                startY = -1;
                return;
            }
            
            if (shortestPathJPS == null) {
                showMessageDialog(null, "JPS did not found the way."); // this will be never used, just in case.  
                startX = -1;
                startY = -1;
                return;
            }

            System.out.println("Number of vertices in Dijkstra path: " + shortestPathDijkstra.size());
            System.out.println("Distance from the start: " + shortestPathDijkstra.getFromIndex(0).getDistance());
            System.out.println("");
            System.out.println("Number of vertices in JPS: " + shortestPathJPS.size());
            System.out.println("Distance from the start: " + shortestPathJPS.getFromIndex(0).getDistance());
            System.out.println("");
            System.out.println("Manual search ended.");
            System.out.println("");

            if (inPut > 0 && inPut != 1 && inPut != 2) {
                p.runPerformance(pixelmap, startX, startY, endX, endY, inPut);
            }  
            
            boolean[][]visitedD = dijkstra.getVisited();
            boolean[][]visitedA = aStar.getVisited();
            boolean[][]visitedJPS = jps.getVisitedForPainting();
            img = imgHand.drawShortestPath(img, shortestPathAStar, shortestPathDijkstra, shortestPathJPS, visitedD, visitedA, visitedJPS);
            
            returnbutton.setOnAction(o -> {
                manualFrame.setVisible(false);
                stage.setScene(searchPanel);
            });
            
            manualFrame.setVisible(true);
            stage.setScene(instrPanel);
        });
        
        revealTheMapButton.setOnAction(e -> {
            String input = inputField.getText();
            try {
                int inPut = Integer.parseInt(input);
            }catch (NumberFormatException ex) {
                showMessageDialog(null, "Enter positive integer");
            }

            String url = textfield.getText();
            int inPut = Integer.parseInt(input);
            IOimage io = new IOimage();
            ImageHandler imgHand = new ImageHandler();
            BufferedImage img = io.readImage(url);

            int height = 600;
            int width = 600;

            img = imgHand.makeNewFrame(img, height, width);
            Map map = new Map(img, height, width);
            int[][] pixelmap = map.generateMaps();
              
            JFrame frame = new JFrame("Pathing");
            
            frame.setSize(height, width);
            JLabel label = new JLabel(new ImageIcon(img));
            frame.add(label);

            JTextField coordinates = new JTextField();
            coordinates.setText("Start point: not set"); 
              
            returnbutton.setOnAction(o -> {
                frame.setVisible(false);
                stage.setScene(searchPanel);
            });
            
            frame.addMouseListener(new MouseAdapter() {
                int clicked = 0;
                int startRow = 0;
                int startColumn = 0;
                int searchNumber = 1;
                @Override
                public void mouseClicked(MouseEvent e) { 
                    int x = e.getX();
                    int y = e.getY();
                    
                    if (pixelmap[x][y] == 0) {
                        showMessageDialog(null, "You did not clicked the land!");
                            clicked = 0;
                            x = -1;
                            y = -1;
                            return;
                    }
 
                    ImageHandler imgFrameHandler = new ImageHandler();
                    
                    if (clicked == 1) {
                        JTextField coordinates = new JTextField();;
                        coordinates.setText("Shortest path from coordinates " 
                            + startRow + " , " + startColumn + " to " + x + " , " + y);
                        
                        Performance p = new Performance();
                        Dijkstra dijkstra = new Dijkstra();
                        List shortestPathDijkstra = new List();
                        
                        AStar aStar = new AStar();
                        List shortestPathAStar = new List();
                        
                        JPS jps = new JPS();
                        List shortestPathJPS = new List();

                        System.out.println(searchNumber + ". Search:");
                        System.out.println("");
                        
                        long startA = System.nanoTime();
                        shortestPathAStar = aStar.findPath(pixelmap, startRow, startColumn, x, y);
                        long endA= System.nanoTime();
                        
                        long startD = System.nanoTime();
                        shortestPathDijkstra = dijkstra.findPath(pixelmap, startRow, startColumn, x, y);
                        long endD = System.nanoTime();
                        
                        long startJPS = System.nanoTime();
                        shortestPathJPS = jps.findPath(pixelmap, startRow, startColumn, x, y);
                        long endJPS = System.nanoTime();
                        
                        System.out.println("A* runs " +((endA-startA)/1e9)+ " seconds");
                        System.out.println("Dijkstra runs " +((endD - startD)/1e9) + " seconds");
                        System.out.println("Jump Point Search path (JPS) runs " +((endJPS - startJPS)/1e9) + " seconds");
                        System.out.println("");
                        
                        if (shortestPathAStar == null) {
                            showMessageDialog(null, "There is no path between the starting and ending point you chose.");
                            clicked = 0;
                            x = -1;
                            y = -1;
                            return;
                        }
                        
                        if (shortestPathJPS == null) {
                            showMessageDialog(null, "JPS did not found the way."); // this will be never used, just in case.  
                            x = -1;
                            y = -1;
                            return;
                        }
                        
                        System.out.println("Number of vertices in A* path: " + shortestPathAStar.size());
                        System.out.println("Distance from the start: " + shortestPathAStar.getFromIndex(0).getDistance());
                        System.out.println("");
                        System.out.println("Number of vertices in Dijkstra path: " + shortestPathDijkstra.size());
                        System.out.println("Distance from the start: " + shortestPathDijkstra.getFromIndex(0).getDistance());
                        System.out.println("");
                        System.out.println("Number of vertices in JPS: " + shortestPathJPS.size());
                        System.out.println("Distance from the start: " + shortestPathJPS.getFromIndex(0).getDistance());
                        System.out.println("");
                        System.out.println(searchNumber + ". search ended.");
                        System.out.println("");
                        
                        if (inPut > 0 && inPut != 1 && inPut != 2) {
                            p.runPerformance(pixelmap, startRow, startColumn, x, y, inPut);
                        }             
                        BufferedImage img = io.readImage(url);
                        img = imgFrameHandler.makeNewFrame(img, height, width);
                        
                        boolean[][]visitedD = dijkstra.getVisited();
                        boolean[][]visitedA = aStar.getVisited();
                        boolean[][]visitedJPS = jps.getVisitedForPainting();
                        img = imgFrameHandler.drawShortestPath(img, shortestPathAStar, shortestPathDijkstra, shortestPathJPS, visitedD, visitedA, visitedJPS);  
                        img = imgHand.makeNewFrame(img, height, width);
                        JFrame shortestPathFrame = new JFrame("Search nro. "+ searchNumber);
                        
                        shortestPathFrame.setSize(height, width);
                        JLabel label = new JLabel(new ImageIcon(img));
                        shortestPathFrame.add(label);
                        shortestPathFrame.add(coordinates,BorderLayout.SOUTH);                 
                        shortestPathFrame.setVisible(true); 
                        frame.setVisible(false);
       
                        shortestPathFrame.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e) {
                                frame.setVisible(true);
                                clicked = 0;
                                searchNumber++;
                            }});
                    } else if (e.getClickCount() == 1) {
                        startRow = e.getX(); //START
                        startColumn = e.getY();
                        if (pixelmap[startRow][startColumn] == 0) {
                            showMessageDialog(null, "You did not clicked the land!");
                            clicked = 0;
                            x = -1;
                            y = -1;
                            return;
                        }
                        coordinates.setText("Start point: " + startRow + " , " + startColumn);
                        clicked++;
                    }
                }
                });
            frame.setVisible(true);
            stage.setScene(instrPanel);
        });
 
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     */
    @Override
    public void stop() {

    }
    
}