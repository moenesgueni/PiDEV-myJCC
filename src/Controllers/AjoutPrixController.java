package Controllers;

import Models.Film;
import Models.Prix;
import Models.User;
import Services.FilmService;
import Services.PrixService;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class AjoutPrixController implements Initializable {

    //var
    private boolean isAlreadyTranslated = false;
    private Stage primaryStage;

    @FXML
    private ImageView fullScreen, settings, logout, menu, userPhoto, userPhoto2, menu2;

    @FXML
    private AnchorPane pane1, pane2, pane3;

    @FXML
    private Label nomPrenom, role;
    @FXML
    private AnchorPane workPlace;
    @FXML
    private Button Vote;
    @FXML
    private Button goPrix;
    @FXML
    private TextField typePrix;
    @FXML
    private TextField titreFilm;
    @FXML
    private Button ajoutprix;
    @FXML
    private Button ListPrix;

    @FXML
    private void ajoutPrix(ActionEvent event) {
        Prix p = new Prix();
        PrixService ps = new PrixService();
        Film f = new Film();
        FilmService fs = new FilmService();
        //f = fs.GetFilmByTitre(titreFilm.getText());
        //int a = f.getID_film();
        
        /*TextField x=;
        if(x == titreFilm){*/
        p.setFilm(f = fs.GetFilmByTitre(titreFilm.getText()));
        p.setTypePrix(typePrix.getText());
        ps.ajouterPrix(p);/*}*/
        /**
         * **********Banner *****************
         */
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Prix " + titreFilm.getText() + " est effectuee avec succes");
        confirmation.show();
        /**
         * *****Vider les texteFiled*******
         */
        titreFilm.setText("");
        typePrix.setText("");
        
    }

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
        User u = ps.afficherUserbyID(1);
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

    }

    @FXML
    private void goVote(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutVote.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MyFit - Mon Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @FXML
    private void goPrix(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutPrix.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @FXML
    private void ListPrix(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichagePrix.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

}
