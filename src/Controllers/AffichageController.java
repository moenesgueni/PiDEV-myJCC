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
    private Film selectedFilm;
    @FXML
    private Button reddetfilm;
   
    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
    FilmService fs = new FilmService();
    List<Film> films = fs.afficherFilm();
    
    ObservableList<String> items = FXCollections.observableArrayList();
    for (Film film : films) {
        String item = film.getTitre() + " - " + film.getDateRealisation() + " - " + film.getGenre() + " - " + film.getResume() + " - " + film.getDuree() + " - " + film.getPrix() + " - " + film.getID_producteur() + " - " + film.getActeur() + " - " + film.getImage();
        items.add(item);
    }    
    ListF.setItems(items);
    ListF.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            selectedFilm = films.get(ListF.getSelectionModel().getSelectedIndex());
        }
    });
}

    

    @FXML
    private void RedModifF(ActionEvent event) {
        if (selectedFilm == null) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucun Film sélectionné");
        alert.setContentText("Veuillez sélectionner un film à modifier.");
        alert.showAndWait();
        return;
    }
        
    try {
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/updateF.fxml"));
        Parent root = loader.load();
        UpdateFController controller = loader.getController();
        controller.initData(selectedFilm);
         Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.showAndWait(); // Wait for the updates window to close
        
        // Update the ListView with the latest data
       FilmService fs = new FilmService();
        List<Film> films = fs.afficherFilm();
        
        ObservableList<String> items = FXCollections.observableArrayList();
    for (Film film : films) {
         String item = film.getTitre() + " - " + film.getDateRealisation() + " - " + film.getGenre() + " - " + film.getResume() + " - " + film.getDuree() + " - " + film.getPrix() + " - " + film.getID_producteur() + " - " + film.getActeur() + " - " + film.getImage();
        items.add(item);
    }    
    ListF.setItems(items);
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
        newStage.showAndWait(); // Wait for the updates window to close
        
        // Update the ListView with the latest data
       FilmService fs = new FilmService();
        List<Film> films = fs.afficherFilm();
        
        ObservableList<String> items = FXCollections.observableArrayList();
    for (Film film : films) {
         String item = film.getTitre() + " - " + film.getDateRealisation() + " - " + film.getGenre() + " - " + film.getResume() + " - " + film.getDuree() + " - " + film.getPrix() + " - " + film.getID_producteur() + " - " + film.getActeur() + " - " + film.getImage();
        items.add(item);
    }    
    ListF.setItems(items);
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
        newStage.showAndWait(); // Wait for the updates window to close
        
        // Update the ListView with the latest data
       FilmService fs = new FilmService();
        List<Film> films = fs.afficherFilm();
        
        ObservableList<String> items = FXCollections.observableArrayList();
    for (Film film : films) {
        String item = film.getTitre() + " - " + film.getDateRealisation() + " - " + film.getGenre() + " - " + film.getResume() + " - " + film.getDuree() + " - " + film.getPrix() + " - " + film.getID_producteur() + " - " + film.getActeur() + " - " + film.getImage();
        items.add(item);
    }    
    ListF.setItems(items);
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RedirectDetails(ActionEvent event) {
         if (selectedFilm == null) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucun Film sélectionné");
        alert.setContentText("Veuillez sélectionner un film à modifier.");
        alert.showAndWait();
        return;
    }
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Filmdet.fxml"));
        Parent root = loader.load();
        FilmdetController controller = loader.getController();
        controller.initData(selectedFilm);
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.showAndWait();
    }catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
