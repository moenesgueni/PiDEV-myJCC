/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Interfaces.FilmInterface;
import Models.Film;
import Services.FilmService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import myjcc.NewFXMain;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class AffichageController implements Initializable {

    @FXML
    private ListView<String> ListF;
    @FXML
    private Button redmodifF;
    @FXML
    private Button redajouterf;
    @FXML
    private Button redsupf;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         FilmService fs = new FilmService();
        List<Film> films = fs.afficherFilm();
        
        ObservableList<String> items = FXCollections.observableArrayList();
    for (Film film : films) {
        String item = film.getTitre() + " - " + film.getDateRealisation() + " - " + film.getGenre() + " - " + film.getResume() + " - " + film.getDuree() + " - " + film.getPrix() + " - " + film.getID_producteur() + " - " + film.getActeur();
        items.add(item);
    }    
    ListF.setItems(items);
    
    
        
     
             }
    

    @FXML
    private void RedModifF(ActionEvent event) {
        
    try {
    
    Parent root = FXMLLoader.load(getClass().getResource("../GUI/Map.fxml"));
        Scene scene = new Scene(root);
         Stage newStage = new Stage();
          newStage.setScene(scene);
          newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void RedAjouterF(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajouterfilm.fxml"));
        Scene scene = new Scene(root);
         Stage newStage = new Stage();
          newStage.setScene(scene);
          newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void RedSupF(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/supprimerf.fxml"));
        Scene scene = new Scene(root);
         Stage newStage = new Stage();
          newStage.setScene(scene);
          newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

