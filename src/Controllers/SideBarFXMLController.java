package Controllers;

import Models.User;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SideBarFXMLController implements Initializable {

    //var
    private boolean isAlreadyTranslated = false;
    private Stage primaryStage;

    @FXML
    private ImageView fullScreen, settings, logout, menu, menu2, userPhoto, userPhoto2,
            iconGererGalerie, iconAjouerContrat, iconPaletteCouleurs, iconListGaleries, iconGraph;
    @FXML
    private AnchorPane pane1, pane2, pane3;
    @FXML
    private BorderPane workPlace;
    @FXML
    private Label nomPrenom, role;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void translate() {
        pane1.setVisible(true);
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(0.3);
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
        User u = ps.afficherUserbyID(5);
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
        menu2.setOnMouseClicked(event -> {
            if (!isAlreadyTranslated) {
                translate();
            } else {
                unTranslate();
            }
        });

        pane1.setOnMouseClicked(event -> {
            unTranslate();
        });

        //Icon Routes*******************************
        //call gerer photographies in pane frrom icon
        iconGererGalerie.setOnMouseClicked(event -> {
            FxmlLoader fl = new FxmlLoader();
            Pane view = fl.getPage("GererPhotographiesFXML");
            workPlace.setCenter(view);
        });
        //Afficher mes contrats
        iconAjouerContrat.setOnMouseClicked(event -> {
            FxmlLoader fl = new FxmlLoader();
            Pane view = fl.getPage("ContratsAfficher");
            workPlace.setCenter(view);
        });
        //Palette de couleurs
        iconPaletteCouleurs.setOnMouseClicked(event -> {
            FxmlLoader fl = new FxmlLoader();
            Pane view = fl.getPage("PhotographePalette");
            workPlace.setCenter(view);
        });
        //Liste des Galeries
        iconListGaleries.setOnMouseClicked(event -> {
            FxmlLoader fl = new FxmlLoader();
            Pane view = fl.getPage("SponsorListGaleries");
            workPlace.setCenter(view);
        });
        //Statistiques
        iconGraph.setOnMouseClicked(event -> {
            FxmlLoader fl = new FxmlLoader();
            Pane view = fl.getPage("SponsorGraph");
            workPlace.setCenter(view);
        });

    }

    //Routes*******************************
    //call gerer photographies in pane
    @FXML
    private void handleMaGalerie(ActionEvent event) {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("GererPhotographiesFXML");
        workPlace.setCenter(view);
        unTranslate();
    }

    //Afficher mes contrats
    @FXML
    private void handleAjouterContrat(ActionEvent event) {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("ContratsAfficher");
        workPlace.setCenter(view);
        unTranslate();
    }

    @FXML
    private void handlePaletteCouleurs(ActionEvent event) {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("PhotographePalette");
        workPlace.setCenter(view);
        unTranslate();
    }

    @FXML
    private void handleListGaleries(ActionEvent event) {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("SponsorListGaleries");
        workPlace.setCenter(view);
        unTranslate();
    }

    public void goToGalerie() {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("AjouterContratFXML");
        workPlace.setCenter(view);
    }

    @FXML
    private void handleGraph(ActionEvent event) {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("SponsorGraph");
        workPlace.setCenter(view);
        unTranslate();
    }
}
