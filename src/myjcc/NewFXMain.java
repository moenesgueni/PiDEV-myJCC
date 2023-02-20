package myjcc;

import Controllers.SideBarFXMLController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class NewFXMain extends Application {

    //variables pour le menu déroulant
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
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
