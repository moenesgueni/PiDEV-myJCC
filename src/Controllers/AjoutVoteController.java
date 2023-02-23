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
import Utils.MaConnection;
import Utils.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class AjoutVoteController implements Initializable {

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
    private MenuButton menuButtonFilm;
    @FXML
    private Button retour;
    @FXML
    private Button LesVotes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeFilmsTitles();
    }    
    
    private void initializeFilmsTitles() {
        List<String> films = getFilmsNames();
        films.forEach(film -> {
            MenuItem item = new MenuItem(film);
            item.setOnAction(event -> handleSelectedFilm(item.getText()));
            menuButtonFilm.getItems().add(item);
        }
        );

    }
    
    private void handleSelectedFilm(String filmName) {
        System.out.println("selected film:" + filmName);
        saveVote(filmName);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListeVote.fxml"));
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
    private List<String> getFilmsNames() {
        return new FilmService()
                .getFilms().stream()
                .map(Film::getTitre)
                .collect(Collectors.toList());
    }
    private Film getFilmByTitre(String filmName) {
        return new FilmService()
                .GetFilmByTitre(filmName);
    }
    
    private void saveVote(String filmName) {
        Connection cnx = MaConnection.getInstance().getCnx();
        User user = new User(1,"Moenes","Gueni","Male","moenes.gueni@esprit.tn","azertyuiop123",Type.ADMINSTRATEUR,"cjckcfk");
        Film film = getFilmByTitre(filmName);
        System.out.println(film.toString());
        Film film1 = new Film(4,"The Shawshank Redemption ", "1994","action","the best of all time", "2:03", (float) 50.00, "Frank Darabont", "Morgan Freeman");
        System.out.println(film1.toString());
        
        Vote vote = new Vote(film,user,getCurrentDate());
        //TODO insert vote into DB
        new VoteService().ajouterVote(vote);
    }

    private Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
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
    private void afficherVoteID(ActionEvent event) {
    }

    @FXML
    private void mainbar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/SideBarFXML.fxml"));
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
    private void afficherVotttttttt(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListeVote.fxml"));
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
    
}
