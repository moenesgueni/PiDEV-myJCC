/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Film;
import Models.User;
import Models.Vote;
import Services.FilmService;
import Services.VoteService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class VoteFiveStarsController implements Initializable {
    
    Calendar calendar = Calendar.getInstance();
        Date Date_Vote = new Date(calendar.getTimeInMillis());

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
    private void SubmitBttn(ActionEvent event) throws IOException {
        String avis = AvisField.getText();
        Film film3 = new Film(13);
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutVote.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        VoteService vs = new VoteService();
        
        
        User user3 = new User(2);
        
        
        
        
        double b = FiveStars.getRating();
        
        
        Vote votee = new Vote((int)b,user3,film3,avis,Date_Vote);
        
        vs.voteee(votee);
        
        
    }
    public static void display(String title,String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Cloe window");
        closeButton.setOnAction(e-> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    
}
