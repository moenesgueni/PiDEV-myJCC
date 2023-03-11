/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import Models.Vote;
import Services.FilmService;
import Services.VoteService;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;


/**
 * FXML Controller class
 *
 * @author wael
 */
public class ChartController implements Initializable {

    VoteService vs = new VoteService();
    FilmService fs = new FilmService();
    ArrayList<String> genre = fs.getAllGenres();
    
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label typeFilm;
List<Pair<String, Integer>> countVoteFilmByType = vs.countVoteFilmByType(genre);
    @FXML
    private Label type2;
    @FXML
    private Label type3;
    @FXML
    private Label moreV0;
    @FXML
    private Label moreV1;
    @FXML
    private Label moreV2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniLineChart();
        iniPieChart();
        String titreFilm;
        int v = 0 ;
        String x = "";

        //System.out.println(vs.countVoteFilmByType(genre).get(0).getKey());
        int j = 0;
        while (j < genre.size()) {
            if (j == 0) {
                titreFilm = vs.countVoteFilmByType(genre).get(j).getKey();
                typeFilm.setText(titreFilm);
                v = vs.countVoteFilmByType(genre).get(j).getValue();
                x = Integer.toString(v);
                moreV0.setText(x); ;
            } else if (j == 1) {
                titreFilm = vs.countVoteFilmByType(genre).get(j).getKey();
                type2.setText(titreFilm);
                v = vs.countVoteFilmByType(genre).get(j).getValue();
                x = Integer.toString(v);
                moreV1.setText(x); ;
            } else {
                titreFilm = vs.countVoteFilmByType(genre).get(j).getKey();
                type3.setText(titreFilm);
                v = vs.countVoteFilmByType(genre).get(j).getValue();
                x = Integer.toString(v);
                moreV2.setText(x); 
            }
            j++;
        }
        
    } 
    
    
    
    private void iniLineChart(){
        
        
        Vote v = new Vote();
        VoteService vs = new VoteService();


           
        
        
        
        
        
        
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Mercredi",vs.afficherVotesDate()));
        series.getData().add(new XYChart.Data("Jeudi",vs.afficherVotesDateJeudi()));
        series.getData().add(new XYChart.Data("Vendredi",vs.afficherVotesDateVendredi()));
        series.getData().add(new XYChart.Data("Samedi",vs.afficherVotesDateSamedi()));
        series.getData().add(new XYChart.Data("Dimanche",vs.afficherVotesDateDimanche()));
        series.getData().add(new XYChart.Data("Lundi",vs.afficherVotesDateLundi()));
        series.getData().add(new XYChart.Data("Mardi",vs.afficherVotesDateMardi()));
        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        series.getNode().setStyle("-fx-stroke: #FFD6DC");
    }
    
    private void iniPieChart(){
        VoteService vs = new VoteService();
        ArrayList<Integer> liste = vs.countStars();
	       ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
               new PieChart.Data("ETOILE",liste.get(0)),
               new PieChart.Data("2 ETOILES",liste.get(1)), 
               new PieChart.Data("3 ETOILES",liste.get(2)),
               new PieChart.Data("4 ETOILES",liste.get(3)),
               new PieChart.Data("5 ETOILES",liste.get(4))
               ); 
               pieChart.setData(pieChartData);
                }
    
}
