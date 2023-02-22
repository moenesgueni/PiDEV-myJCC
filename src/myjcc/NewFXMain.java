/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjcc;

import Controllers.SideBarFXMLController;
import java.io.IOException;
import static java.lang.System.load;
import static java.util.ServiceLoader.load;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Charge le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/SideBarFXML.fxml"));
        AnchorPane root = loader.load();

        // Crée une nouvelle scène avec le layout chargé depuis le FXML
        Scene scene = new Scene(root);

        // Définit la scène sur la fenêtre principale
        primaryStage.setScene(scene);

        // Obtient une référence sur le contrôleur FXML et définit la fenêtre principale sur le contrôleur
        SideBarFXMLController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        // Affiche la fenêtre principale
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
