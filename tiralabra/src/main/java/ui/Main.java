/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import algorithms.Dijkstra;
import io.IOimage;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import utils.Vertice;


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
        TextField textfield = new TextField("https://movingai.com/benchmarks/street/Milan_0_256.png");
        
        // Coordinates Scene and instructions
          Label header = new Label("Instructions");
          header.setFont(Font.font("Arial", FontWeight.BOLD,  20)); 
          Label instructions = new Label("1. click is the start \n"
                  + "2. click is the finish\n\n"
                  + "You are able to see your \n"
                  + "coordinates at the bottom \n"
                  + "left corner of the window.");

          GridPane instrSetup = new GridPane();
          
          instrSetup.setStyle("-fx-background-color:POWDERBLUE");
          instrSetup.add(header, 0, 1);
          instrSetup.add(instructions, 0, 2);
          instrSetup.setPrefSize(250, 200);
          instrSetup.setAlignment(Pos.BASELINE_RIGHT);
          instrSetup.setVgap(10);
          instrSetup.setHgap(10);
          instrSetup.setPadding(new Insets(20, 20, 0, 20));
        
          Scene actionPanel = new Scene(instrSetup);
        
        
        revealTheMapButton.setOnAction(e -> {
            String url = textfield.getText();
            IOimage io = new IOimage();
            ImageHandler imgHand = new ImageHandler();
            BufferedImage img = io.readImage(url);

            int height = 800;
            int width = 800;

            try {
                img = imgHand.resizeImage(img, height, width);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            Map map = new Map(img, height, width);
            int[][] pixelmap = map.generateMaps();
            
            
            /* Will be used when best path is known. 
            try {
                img = imgHand.draw(img, 200, 200);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }*/
     
            JFrame frame = new JFrame("Pathing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(height, width);
            JLabel label = new JLabel(new ImageIcon(img));
            frame.add(label);

            JTextField coordinates = new JTextField();;
            coordinates.setText("Start point: not set"); 
            frame.add(coordinates,BorderLayout.SOUTH);
            
            frame.addMouseListener(new MouseListener() {
                int clicked = 0;
                int startRow = -1;
                int startColumn = -1;
                public void mousePressed(MouseEvent e) { }
                public void mouseReleased(MouseEvent e) { }
                public void mouseEntered(MouseEvent e) { }
                public void mouseExited(MouseEvent e) { }
                public void mouseClicked(MouseEvent e) { 
                  int x = e.getX();
                  int y = e.getY();
  
                  if (clicked == 1) {
                    JTextField coordinates = new JTextField();;
                    coordinates.setText("Shortest path from coordinates " 
                            + startRow + " , " + startColumn + " to " + x + " , " + y);
                    Dijkstra dijkstra = new Dijkstra();
                    ArrayList<Vertice> shortestPath = new ArrayList<>();
                    shortestPath = dijkstra.findPath(pixelmap, startRow, startColumn, x, y);
                    // System.out.println(shortestPath);
                    if (shortestPath == null) {
                        showMessageDialog(null, "There is no path between the starting and ending point you chose.");
                    }
                    
                    if (shortestPath.isEmpty()) {
                        showMessageDialog(null, "You did not clicked the land!");
                    }
                    BufferedImage img = io.readImage(url);

                    try {
                        img = imgHand.resizeImage(img, height, width);
                    } catch (Exception ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    img = imgHand.drawShortestPath(img, shortestPath);
                    JFrame shortestPathFrame = new JFrame("Pathing");
                    shortestPathFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    shortestPathFrame.setSize(height, width);
                    JLabel label = new JLabel(new ImageIcon(img));
                    shortestPathFrame.add(label);
                    shortestPathFrame.add(coordinates,BorderLayout.SOUTH);                 
                    shortestPathFrame.setVisible(true); 
                    frame.setVisible(false);
                  } else if (e.getClickCount() == 1) {
                      startRow = x;
                      startColumn = y;  
                      coordinates.setText("Start point: " + startRow + " , " + startColumn);
                      clicked++;
                  }
                }
            });

            frame.setVisible(true);
            
            
            stage.setScene(actionPanel);
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
