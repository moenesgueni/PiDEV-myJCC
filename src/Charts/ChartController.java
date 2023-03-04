/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import Models.Vote;
import Services.VoteService;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class ChartController implements Initializable {

    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label typeFilm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniLineChart();
        iniPieChart();
    } 
    
    public void display(String titreFilm) throws IOException {
            
       
            System.out.println(titreFilm);
            typeFilm.setText(titreFilm);
            
        
    }
    
    private void iniLineChart(){
        //hedhi kifech nconverti mn Date ll String
        /*String pattern = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String dateString = dateFormat.format(Date_Vote);
        System.out.println("date : "+dateString);*/
        /////////////////////////////////////////////////////////////////
        
        Vote v = new Vote();
        VoteService vs = new VoteService();

//nheb nejbed l date ml table
           
        
        
        
        
        //w nheb nekhou l valeur w na3mllou l somme hasseb l nhar
        // nkouou ahna l JCC mn 1janv l 7Janv
        
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Monday",vs.afficherVotesDate()));
        series.getData().add(new XYChart.Data("Tuesday",vs.afficherVotesDateMardi()));
        series.getData().add(new XYChart.Data("Wednesday",vs.afficherVotesDateMercredi()));
        series.getData().add(new XYChart.Data("Thursday",vs.afficherVotesDateJeudi()));
        series.getData().add(new XYChart.Data("Friday",vs.afficherVotesDateVendredi()));
        series.getData().add(new XYChart.Data("Saturday",vs.afficherVotesDateSamedi()));
        series.getData().add(new XYChart.Data("Sunday",vs.afficherVotesDateDimanche()));
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
