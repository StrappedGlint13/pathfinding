package ui;

import algorithms.AStar;
import algorithms.Dijkstra;
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
        
        instrSetup.setStyle("-fx-background-color:POWDERBLUE");
        instrSetup.add(header, 0, 1);
        instrSetup.add(instructions, 0, 2);
        instrSetup.setPrefSize(300, 300);
        instrSetup.setAlignment(Pos.BASELINE_RIGHT);
        instrSetup.setVgap(10);
        instrSetup.setHgap(10);
        instrSetup.setPadding(new Insets(20, 20, 0, 20));
        Scene instrPanel = new Scene(instrSetup);   
        
        revealTheMapButton.setOnAction(e -> {
            String url = textfield.getText();
            IOimage io = new IOimage();
            ImageHandler imgHand = new ImageHandler();
            BufferedImage img = io.readImage(url);

            int height = 800;
            int width = 800;

            img = imgHand.makeNewFrame(img, height, width);
            Map map = new Map(img, height, width);
            int[][] pixelmap = map.generateMaps();
              
            JFrame frame = new JFrame("Pathing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(height, width);
            JLabel label = new JLabel(new ImageIcon(img));
            frame.add(label);

            JTextField coordinates = new JTextField();;
            coordinates.setText("Start point: not set"); 
            frame.add(coordinates,BorderLayout.SOUTH);
            
            frame.addMouseListener(new MouseAdapter() {
                int clicked = 0;
                int startRow = -1;
                int startColumn = -1;
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

                        long startA = System.nanoTime();
                        shortestPathAStar = aStar.findPath(pixelmap, startRow, startColumn, x, y);
                        long endA= System.nanoTime();
                        
                        System.out.println("A* runs " +((endA-startA)/1e9)+ " seconds");
                        
                        long startD = System.nanoTime();
                        shortestPathDijkstra = dijkstra.findPath(pixelmap, startRow, startColumn, x, y);
                        long endD = System.nanoTime();
                                                
                        System.out.println("Dijkstra runs " +((endD - startD)/1e9) + " seconds"); 
                        
                        if (shortestPathAStar.isEmpty()) {
                            for (int i = 0; i < shortestPathAStar.size(); i++) {
                                System.out.println(shortestPathAStar.getFromIndex(i));
                            }
                            showMessageDialog(null, "You did not clicked the land!");
                            clicked = 0;
                            x = -1;
                            y = -1;
                            return;
                        }
                        System.out.println("Number of vertices in A*: " + shortestPathAStar.size());
                        System.out.println("Distance from the start: " + shortestPathAStar.getFromIndex(0).getHeuristic());
                        
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
                        BufferedImage img = io.readImage(url);
                        img = imgFrameHandler.makeNewFrame(img, height, width);
                        
                        boolean[][]visitedD = dijkstra.getVisited();
                        boolean[][]visitedA = aStar.getVisited();
                        img = imgFrameHandler.drawShortestPath(img, shortestPathAStar, shortestPathDijkstra, visitedD, visitedA);
                        
                        JFrame shortestPathFrame = new JFrame("Pathing");
                        shortestPathFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // Search panel
        HBox searchComponents = new HBox(10);
        searchComponents.setStyle("-fx-background-color:POWDERBLUE");
        Label searchInstructions = new Label("URL of the map (PNG)");
        searchInstructions.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        searchComponents.getChildren().addAll(searchInstructions, textfield, revealTheMapButton);
        Scene searchPanel = new Scene(searchComponents);
        stage.setScene(searchPanel);
        stage.show(); 
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop() {

    }
}
