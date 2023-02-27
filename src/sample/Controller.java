package sample;

import GUI.myListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Models.Evenement;
import Services.EventService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.stage.Stage;


public class Controller implements Initializable {

    @FXML
    private HBox recentlyPlayedContainer;

    Evenement song;
     private myListener myListener;
    List<Evenement> recentlyPlayed;
    EventService gs = new EventService();
    private Stage primaryStage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recentlyPlayed = new ArrayList<>(getRecentlyPlayed());

        try {
            for (Evenement song : recentlyPlayed) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("song.fxml"));

                VBox vBox = fxmlLoader.load();
                SongController songController = fxmlLoader.getController();
                songController.setData(song,myListener);

                recentlyPlayedContainer.getChildren().add(vBox);
            }

           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Evenement> getRecentlyPlayed() {
         List<Evenement> fruits = new ArrayList<>();
        
        fruits =gs.AfficherEvents();
        
        

        return fruits;
    }

   

    public void setPrimaryStage(Stage primaryStage) {
        // public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
}
