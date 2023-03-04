/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import Services.FilmService;
import Services.VoteService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
    private Label thrdFilm4;
    @FXML
    private Label nbreV2;
    @FXML
    private Label nbreV3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String titreFilm;
        int v = 0;
        String x;
        String y;

        int j = 0;
        while (j < genre.size()) {
            x = vs.getMostVotedFilmByGenre(genre).get(0).getKey();
            Type1.setText(x);
            if (j == 0) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                firstFilm.setText(titreFilm);

                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV1.setText(y);;
            } else if (j == 1) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                sndFilm2.setText(titreFilm);

                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV2.setText(y);;
            } else if (j == 2) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(2).getValue();
                thrdFilm4.setText(titreFilm);
                v = vs.getMostVotedFilmByGenre2(genre).get(2).getValue();
                y = Integer.toString(v);
                nbreV3.setText(y);;
            } 
            j++;
        }
        int i=0;
        while (j < genre.size()) {
            for (i = 0; i < genre.size(); i++) {
                
            }
            x = vs.getMostVotedFilmByGenre(genre).get(0).getKey();
            Type1.setText(x);
            if (j == 0) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                firstFilm.setText(titreFilm);

                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV1.setText(y);;
            } else if (j == 1) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(j).getValue();
                sndFilm2.setText(titreFilm);

                v = vs.getMostVotedFilmByGenre2(genre).get(j).getValue();
                y = Integer.toString(v);
                nbreV2.setText(y);;
            } else if (j == 2) {

                titreFilm = vs.getMostVotedFilmByGenre(genre).get(2).getValue();
                thrdFilm4.setText(titreFilm);
                v = vs.getMostVotedFilmByGenre2(genre).get(2).getValue();
                y = Integer.toString(v);
                nbreV3.setText(y);;
            } 
            j++;
        }
    }

}
