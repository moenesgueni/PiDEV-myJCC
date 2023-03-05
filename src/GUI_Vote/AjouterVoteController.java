/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Vote;

import Charts.AlreadyVotedController;
import Charts.RateController;
import Models.Vote;
import Services.FilmService;
import Services.VoteService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class AjouterVoteController implements Initializable {

    private Stage stage;
    private Parent root;
    private static String titreFilm;
    private static String erreur;
    private String test;

    @FXML
    TextField Titre;

    public void vote(ActionEvent event) throws IOException {

        FilmService fs = new FilmService();

        test = Titre.getText();
        if (fs.existFilm(test)) {
            titreFilm = Titre.getText();

            //nheb nzid condition idha l film deja voté ykharrejli alert si non yhezni ll FiveStars.fxml
            FiveStarsController.xx1 = titreFilm;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Charts/AlreadyVoted.fxml"));
            root = loader.load();

            AlreadyVotedController alreadyVotedController = loader.getController();
            //fiveStarsController.display(titreFilm);

            //Parent root = FXMLLoader.load(getClass().getResource("FiveStars.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if (!fs.existFilm(test)){
            titreFilm = Titre.getText();

            //nheb nzid condition idha l film deja voté ykharrejli alert si non yhezni ll FiveStars.fxml
            FiveStarsController.xx1 = titreFilm;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Charts/Rate.fxml"));
            root = loader.load();

            RateController rateController = loader.getController();
            //fiveStarsController.display(titreFilm);

            //Parent root = FXMLLoader.load(getClass().getResource("FiveStars.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        
        else {
            erreur = Titre.getText();
            /*Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setContentText("Le film " + erreur + " n'existe pas");
            confirmation.show();*/
            ErreurController.xx2 = "Le film  ( " + erreur + " ) n'existe pas";
            //FiveStarsController.xxx="Le film " +erreur+ " n'existe pas";
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Erreur.fxml"));
            root = loader.load();

            ErreurController erreurController = loader.getController();
            // erreurController.display1("Le film " +erreur+ " n'existe pas");

            //Parent root = FXMLLoader.load(getClass().getResource("FiveStars.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
