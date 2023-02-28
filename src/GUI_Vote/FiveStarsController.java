/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Vote;

import Models.Film;
import Models.User;
import Models.Vote;
import Services.FilmService;
import Services.VoteService;
import Utils.Type;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 *
 * @author wael
 */
public class FiveStarsController implements Initializable {
    Calendar calendar = Calendar.getInstance();
        Date Date_Vote = new Date(calendar.getTimeInMillis());
    public static String xx1;
    private Stage stage;
    
    @FXML
    private Label nomFilm;
    @FXML
    private Label lala;
    @FXML
    private Rating FiveStars;
    @FXML
    private TextArea AvisField;

    @FXML
    public void SubmitBttn(ActionEvent event) throws IOException {
        String avis = AvisField.getText();
        Film film3 = new Film(11);
        Parent root = FXMLLoader.load(getClass().getResource("AjouterVote.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        VoteService vs = new VoteService();
        
        
        User user3 = new User(2);
        
        
        
        
        double b = FiveStars.getRating();
        
        
        Vote votee = new Vote((int)b,film3,user3,avis,Date_Vote);
        
        vs.voteee(votee);
        
    }

    

    

    public void display(String titreFilm) throws IOException {

       
            System.out.println(titreFilm);
            lala.setText(titreFilm);
            
        
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
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       System.out.println(xx1);
       lala.setText(xx1);
    }

}
