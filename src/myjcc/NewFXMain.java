package myjcc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import Controllers.FiveStarsController;
import Controllers.SideBarFXMLController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;


/**
 *
 * @author wael
 */
public class NewFXMain extends Application {

    //String css = this.getClass().getResource("myjcc/Styles.css").toExternalForm();
    Button button;
    //variables pour le menu déroulant
    double x, y = 0;

    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("../GUI_Vote/AjouterVote.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("myjcc/Styles.css");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListeVote.fxml"));
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
        Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    private void closeWindow(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fermer");
        alert.setHeaderText("Vous etes sur le point de fermer");
        alert.setContentText("Voulez-vous sauvegarder avant de fermer!");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("successfully closed");
            stage.close();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
