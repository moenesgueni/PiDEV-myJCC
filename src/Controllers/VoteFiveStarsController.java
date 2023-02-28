/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.FilmService;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class VoteFiveStarsController implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private ImageView fullScreen;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView userPhoto;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane3;
    @FXML
    private ImageView userPhoto2;
    @FXML
    private ImageView menu2;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Button Vote;
    @FXML
    private Button goPrix;
    @FXML
    private Label nomPrenom;
    @FXML
    private Label role;
    @FXML
    private ImageView settings;
    @FXML
    private AnchorPane workPlace;
    @FXML
    private Label nomFilm;
    @FXML
    private Label lala;
    @FXML
    private TextArea AvisField;
    @FXML
    private Rating FiveStars;

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Parent root;
    private static String titreFilm;
    private static String erreur;
    private String test;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lala.setText(AjoutVoteController.filmname);
        System.out.println(AjoutVoteController.filmname);
    }    

    @FXML
    private void goVote(ActionEvent event) {
    }

    @FXML
    private void goPrix(ActionEvent event) {
    }

    @FXML
    private void SubmitBttn(ActionEvent event) {
        System.out.println(AjoutVoteController.filmname);
        
    }
    
}
