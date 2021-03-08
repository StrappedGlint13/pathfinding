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
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;


/**
 * A Class for user interface.
 * 
 * 
 * @author matibrax
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button revealTheMapButton = new Button("Reveal the map");
        TextField textfield = new TextField("https://movingai.com/benchmarks/maze/maze512-16-5.png");
        textfield.setPrefWidth(450);
        TextField inputField = new TextField("0");
       
        // https://movingai.com/benchmarks/street/Boston_0_512.png boston
        // https://movingai.com/benchmarks/maze/maze512-16-5.png laby
        // https://movingai.com/benchmarks/dao/brc204d.png 
        // Coordinates Scene and instructions
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
        instrSetup.setPrefSize(300, 300);
        instrSetup.setAlignment(Pos.BASELINE_RIGHT);
        instrSetup.setVgap(10);
        instrSetup.setHgap(10);
        instrSetup.setPadding(new Insets(20, 20, 0, 20));
        Scene instrPanel = new Scene(instrSetup);
        
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        // Search panel
        HBox searchComponents = new HBox(50);
        VBox textFields = new VBox(10);
        VBox labels = new VBox(10);
        searchComponents.setStyle("-fx-background-color:POWDERBLUE");
        Label searchLabel = new Label("URL of the map (PNG)");
        Label inputLabel = new Label("Input for the performance tests \n"
                + "(voluntary)");
        searchLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        inputLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        
        labels.getChildren().addAll(searchLabel, inputLabel);
        textFields.getChildren().addAll(textfield, inputField);
        searchComponents.getChildren().addAll(labels, textFields, revealTheMapButton);
        
        Scene searchPanel = new Scene(searchComponents);
        stage.setScene(searchPanel);
        stage.show();

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

            int height = 800;
            int width = 800;

            img = imgHand.makeNewFrame(img, height, width);
            Map map = new Map(img, height, width);
            int[][] pixelmap = map.generateMaps();
              
            JFrame frame = new JFrame("Pathing");
            
            frame.setSize(height, width);
            JLabel label = new JLabel(new ImageIcon(img));
            frame.add(label);

            JTextField coordinates = new JTextField();;
            coordinates.setText("Start point: not set"); 
            frame.add(coordinates,BorderLayout.SOUTH);
            returnbutton.setOnAction(o -> {
                frame.setVisible(false);
                stage.setScene(searchPanel);
            });
            
            frame.addMouseListener(new MouseAdapter() {
                int clicked = 0;
                int startRow = -1;
                int startColumn = -1;
                int searchNumber = 1;
                @Override
                public void mouseClicked(MouseEvent e) { 
                    int x = e.getX();
                    int y = e.getY();
                    ImageHandler imgFrameHandler = new ImageHandler();
                    
                    if (clicked == 1) {
                        JTextField coordinates = new JTextField();;
                        coordinates.setText("Shortest path from coordinates " 
                            + startRow + " , " + startColumn + " to " + x + " , " + y);
                        
                        //Algorithms
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
                        System.out.println("JPS runs " +((endJPS - startJPS)/1e9) + " seconds");
                        System.out.println("");
                        
                        if (shortestPathAStar.isEmpty()) {
                            showMessageDialog(null, "You did not clicked the land!");
                            clicked = 0;
                            x = -1;
                            y = -1;
                            return;
                        }
                        System.out.println("Number of vertices in A*: " + shortestPathAStar.size());
                        System.out.println("Distance from the start: " + shortestPathAStar.getFromIndex(0).getHeuristic());
                        System.out.println("");
                        if (shortestPathAStar == null) {
                            showMessageDialog(null, "There is no path between the starting and ending point you chose.");
                            clicked = 0;
                            x = -1;
                            y = -1;
                            return;
                        }
              
                        System.out.println("Number of vertices in Dijkstra: " + shortestPathDijkstra.size());
                        System.out.println("Distance from the start: " + shortestPathDijkstra.getFromIndex(0).getDistance());
                        System.out.println("");
                        System.out.println("Number of vertices in Jump Point Search: " + shortestPathJPS.size());
                        System.out.println("Distance from the start: " + shortestPathJPS.getFromIndex(0).getDistance());
                        System.out.println("");
                        System.out.println(searchNumber + ". search ended.");
                        System.out.println("");
                        
                        if (inPut > 0 && inPut != 1 && inPut != 2) {
                            runPerformance(pixelmap, startRow, startColumn, x, y, inPut);
                        }             
                        BufferedImage img = io.readImage(url);
                        img = imgFrameHandler.makeNewFrame(img, height, width);
                        
                        boolean[][]visitedD = dijkstra.getVisited();
                        boolean[][]visitedA = aStar.getVisited();
                        boolean[][]visitedJPS = jps.getVisited();
                        img = imgFrameHandler.drawShortestPath(img, shortestPathAStar, shortestPathDijkstra, shortestPathJPS, visitedD, visitedA, visitedJPS);
                        
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
                        startRow = x;
                        startColumn = y;  
                        coordinates.setText("Start point: " + startRow + " , " + startColumn);
                        clicked++;
                    }
                }
                });
            
            frame.setVisible(true);
            stage.setScene(instrPanel);
        });
 
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop() {

    }
    
    public void runPerformance(int[][]map, int startR, int startC, int endR, int endC, int n) {
        Performance p = new Performance();
        p.ProcessingTimes(map, startR, startC, endR, endC, n);
        boolean daRatio = p.getDa(n);
        boolean dajpsRatio = p.getDaJps(n);
        boolean integerAccDa = p.sameIntADacc;
        boolean integerAccDaJPS = p.sameIntADJPSacc;
        long[] times = p.getTimes();
                        
        System.out.println("PERFORMANCE RESULTS. With input of: " + n);
        System.out.println("");
                        
        System.out.println("Average running times in nanoseconds: ");
        System.out.println("Dijkstra runs: " + times[0] + " nanoseconds");
        System.out.println("A* runs: " + times[1] + " nanoseconds");
        System.out.println("JPS runs: " + times[2] + " nanoseconds");
        System.out.println("");
                        
        System.out.println("Average running times in seconds:");
        System.out.println("Dijkstra runs: " + times[0]/1e9 + " seconds");
        System.out.println("A* runs: " + times[1]/1e9 + " seconds");
        System.out.println("JPS runs: " + times[2]/1e9 + " seconds");
        System.out.println("");
                        
        System.out.println("Finding equally shortest paths:");
        System.out.println("");
        System.out.println("Dijkstra and A* found equally long paths to the accuracy of integers: " + integerAccDa);
        System.out.println("All the algorithms found equally long paths to the accuracy of integers: : " + integerAccDaJPS);
        System.out.println("");
        System.out.println("Dijkstra and A* found exactly equal long paths: " + daRatio);
        System.out.println("All the algorithms found exactly equal long paths: " + dajpsRatio);
            if (!dajpsRatio && p.isSameIntADJPSacc()) {
                System.out.println("All the algorithms found the shortest path to the accuracy of " + p.getSameDecimal() + ". decimals");
            }
        System.out.println("");
        System.out.println("Performance time for input " + n + " runs took" + p.getPerformance()/1e9 + " seconds");
        System.out.println("Performance tests ended. Click once for setup, then click twice for new searches.");
    }
}
