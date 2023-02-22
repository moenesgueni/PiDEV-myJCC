/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjcc;

import GUI.SideBarFXMLController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author youssef
 */
public class MyjccFXMain extends Application {
     double x, y = 0;
     
    @Override
    public void start(Stage primaryStage) {
  try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/SideBarFXML.fxml"));
            Parent root = loader.load();
            
            SideBarFXMLController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            Scene scene = new Scene(root);
            //for sidebar
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);
            });
            //-----------

            //for full screen
            primaryStage.setMaximized(true);
            primaryStage.setFullScreen(true);
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.F11) {
                    if (primaryStage.isFullScreen()) {
                        primaryStage.setFullScreen(false);
                    } else {
                        primaryStage.setFullScreen(true);
                    }
                }
            });
            //--------------

            primaryStage.setTitle("MyJCC");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MyjccFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
