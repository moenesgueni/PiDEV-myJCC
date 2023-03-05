package GUI;

import Controllers.FxmlLoader;
import Models.User;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SideBarFXMLController implements Initializable {

    //var
    private boolean isAlreadyTranslated = false;
    private Stage primaryStage;

    @FXML
    private ImageView fullScreen, settings, logout, menu, userPhoto, userPhoto2;

    @FXML
    private AnchorPane pane1, pane2, pane3;

    @FXML
    private Label nomPrenom, role;
    @FXML
    private ImageView menu1;
    @FXML
    private BorderPane workPlace;
    @FXML
    private ImageView IH;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void translate() {
        pane1.setVisible(true);
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(0.15);
        fadeTransition1.play();

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(+600);
        translateTransition1.play();
        isAlreadyTranslated = true;
    }

    public void unTranslate() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(event1 -> {
            pane1.setVisible(false);
        });

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
        isAlreadyTranslated = false;
    }

    public void changeImage(String path) {
        String newImageUrl = path;
        Image newImage = new Image(newImageUrl);
        userPhoto.setImage(newImage);
        userPhoto2.setImage(newImage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        //enter and exit full screen
        fullScreen.setOnMouseClicked(event -> {
            if (primaryStage.isFullScreen()) {
                primaryStage.setFullScreen(false);
            } else {
                primaryStage.setFullScreen(true);
            }
        });

        //go to settings fxml
        settings.setOnMouseClicked(event -> {
            System.out.println("go to settings");
        });
        //log Out
        logout.setOnMouseClicked(event -> {
            System.out.println("log out");
        });

        //set user data
        //creation service user
        UserService ps = new UserService();
        User u = ps.afficherUserbyID(2);
        if (!u.getPhotoB64().equals("")) {
            changeImage(u.getPhotoB64());
        }
        nomPrenom.setText(u.getNom() + " " + u.getPrenom());
        role.setText(u.getRole().toString());
        //------------

        pane1.setVisible(false);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition.setByX(-600);
        translateTransition.play();

        pane1.toFront();
        pane2.toFront();
        pane3.toFront();
        menu.setOnMouseClicked(event -> {
            if (!isAlreadyTranslated) {
                translate();
            } else {
                unTranslate();
            }
        });
          menu1.setOnMouseClicked(event -> {
            if (!isAlreadyTranslated) {
                translate();
            } else {
                unTranslate();
            }
        });

        pane1.setOnMouseClicked(event -> {
            unTranslate();
        });   
        
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListeHotel.fxml"));
        Pane autreInterface = loader.load();
        workPlace.setCenter(autreInterface);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    @FXML
    private void SetHotelPage(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListeHotel.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
        
    }

    @FXML
    private void VehiculePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/VehiculeGUI/ListeVehicule.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }


    @FXML
    private void ReservationHPage(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ReservationHotel/ListeReservation.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();       
    }




    @FXML
    private void LocationPage(ActionEvent event) {
                 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/LocationGUI/ListeLocation.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();   
    }



}
