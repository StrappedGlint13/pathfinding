/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import io.IOimage;
import javafx.event.ActionEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author matibrax
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button searchButton = new Button("Show a map");
        TextField textfield = new TextField("https://movingai.com/benchmarks/street/Denver_2_256.png");
        
        searchButton.setOnAction(e -> {
            String url = textfield.getText();
            IOimage IO = new IOimage();
            ImageHandler ir = new ImageHandler();

            //"https://movingai.com/benchmarks/street/Denver_2_256.png"
            
            BufferedImage img = IO.readImage(url);

            int height = 800;
            int width = 800;

            try {
                img = ir.resizeImage(img, height, width);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            Map map = new Map(img, height, width);

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
