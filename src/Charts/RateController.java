/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

//import static GUI_Vote.FiveStarsController.xx1;
import Models.Film;
import Models.User;
import Models.Vote;
import Services.UserService;
import Services.VoteService;
import Utilities.FilmSession;
import Utilities.UserSession;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class RateController implements Initializable {

    Calendar calendar = Calendar.getInstance();
    Date Date_Vote = new Date(calendar.getTimeInMillis());

    @FXML
    private Label lala;
    @FXML
    private Rating FiveStars;
    @FXML
    private TextArea AvisField;
    @FXML
    private HBox consFilm;
    @FXML
    private HBox consSalles;
    @FXML
    private HBox consInvites;
    @FXML
    private HBox constPrix;
    @FXML
    private HBox constVotes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println(xx1);
  //      lala.setText(xx1);
        consSalles.setVisible(false);
        consFilm.setVisible(false);
        consInvites.setVisible(false);
        constPrix.setVisible(false);
        constVotes.setVisible(false);
    }

    @FXML
    private void SubmitBttn(ActionEvent event) throws IOException {
        String avis = AvisField.getText();
        Film film3 = new Film(FilmSession.getID_film());
        Parent root = FXMLLoader.load(getClass().getResource("../gui/affichagef.fxml"));// change
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        VoteService vs = new VoteService();

        UserSession.getEmail();
        UserService us = new UserService();
         User user3 = new User(UserSession.getID_User());

        double b = FiveStars.getRating();

        Vote votee = new Vote((int) b,  user3,film3, avis, Date_Vote);

        vs.voteee(votee);
    }

    public void display(String titreFilm) throws IOException {

        System.out.println(titreFilm);
        lala.setText(titreFilm);

    }

    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Cloe window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
