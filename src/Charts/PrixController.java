/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import Services.FilmService;
import Services.VoteService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class PrixController implements Initializable {

    VoteService vs = new VoteService();
    FilmService fs = new FilmService();
    ArrayList<String> genre = fs.getAllGenres();

    @FXML
    private Label firstFilm;
    @FXML
    private Label Type1;
    @FXML
    private Label nbreV1;
    @FXML
    private Label sndFilm2;
    @FXML
    private Label nbreV2;
    @FXML
    private Label nbreV3;
    private Label type2;
    @FXML
    private Label thrdFilm3;
    @FXML
    private Label Type11;
    @FXML
    private Label firstFilm1;
    @FXML
    private Label nbreV11;
    @FXML
    private Label sndFilm21;
    @FXML
    private Label nbreV21;
    @FXML
    private Label thrdFilm31;
    @FXML
    private Label nbreV31;
    @FXML
    private Label Type111;
    @FXML
    private Label firstFilm11;
    @FXML
    private Label nbreV111;
    @FXML
    private Label sndFilm211;
    @FXML
    private Label nbreV211;
    @FXML
    private Label thrdFilm311;
    @FXML
    private Label nbreV311;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String titreFilm;
        int v = 0;
        String x;
        String y;

        int j = 0;
        
        
        
        
        x = vs.getMostVotedFilmByGenre(genre).get(0).getKey();
        Type1.setText(x);

        x = vs.getMostVotedFilmByGenre(genre).get(3).getKey();
        Type11.setText(x);

        x = vs.getMostVotedFilmByGenre(genre).get(6).getKey();
        Type111.setText(x);
        List<Pair<String, String>> pair = vs.getMostVotedFilmByGenre(genre);
        while (j < pair.size()) {

            if (j == 0) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                firstFilm.setText(titreFilm);

                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV1.setText(y);
            } else if (j == 1) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                sndFilm2.setText(titreFilm);

                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV2.setText(y);
            } else if (j == 2) {
                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                thrdFilm3.setText(titreFilm);
                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV3.setText(y);
            } else if (j == 3) {
                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                firstFilm1.setText(titreFilm);
                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV11.setText(y);
            } else if (j == 4) {
                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                sndFilm21.setText(titreFilm);
                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV21.setText(y);
            } else if (j == 5) {
                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                thrdFilm31.setText(titreFilm);
                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV31.setText(y);
            } else if (j == 6) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                firstFilm11.setText(titreFilm);
                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV111.setText(y);

            } else if (j == 7) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                sndFilm211.setText(titreFilm);
                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV211.setText(y);

            } else if (j == 8) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                thrdFilm311.setText(titreFilm);
                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV311.setText(y);

            }
            j++;
        }
        
    }

}
