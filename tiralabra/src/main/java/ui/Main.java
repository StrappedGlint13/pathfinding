/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import algorithms.Dijkstra;
import io.IOimage;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        
        Button searchButton = new Button("Reveal the map");
        TextField textfield = new TextField("https://movingai.com/benchmarks/street/Denver_2_256.png");
        
        searchButton.setOnAction(e -> {
            Dijkstra dijkstra = new Dijkstra();
            String url = textfield.getText();
            IOimage io = new IOimage();
            ImageHandler imgHand = new ImageHandler();
            BufferedImage img = io.readImage(url);

            int height = 1000;
            int width = 1000;

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
            ArrayList<Vertice> shortestPath = new ArrayList<>();
            shortestPath = dijkstra.findPath(pixelmap, 0, 0, 7, 8);
            System.out.println(shortestPath);
            JFrame frame = new JFrame();
            frame.setSize(1200, 1200);
            JLabel label = new JLabel(new ImageIcon(img));
            frame.add(label);
            frame.setVisible(true);
         
        });
        
    
        // Search panel
  
        HBox searchComponents = new HBox(10);
        Label searchInstructions = new Label("URL of the map (PNG)");
        searchInstructions.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        searchComponents.getChildren().addAll(searchInstructions, textfield, searchButton);
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
