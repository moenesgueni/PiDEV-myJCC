/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Interfaces.FilmInterface;
import Models.Film;
import Models.Reservation;
import Services.FilmService;
import Services.ReservationService;
import Utilities.Type;
import Utilities.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
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
import admin.NewFXMain;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class AffichageController implements Initializable {

    @FXML
    private ListView<Film> ListF;
    @FXML
    private Button redmodifF;
    @FXML
    private Button redajouterf;
    @FXML
    private Button redsupf;
    private Film selectedFilm;
    @FXML
    private Button reddetfilm;
        Preferences prefs = Preferences.userNodeForPackage(AffichageController.class);
    @FXML
    private ListView<Film> TopFilms;
    /**
     * Initializes the controller class.
     */
        
    @Override
   public void initialize(URL url, ResourceBundle rb) {
    // Display the list of films
    FilmService fs = new FilmService();
    List<Film> films = fs.afficherFilm();
    ObservableList<Film> items = FXCollections.observableArrayList(films);
    ListF.setItems(items);
    if (ListF != null) {
        ListF.setCellFactory(param -> new FilmListCell());
    }
    ListF.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            selectedFilm = films.get(ListF.getSelectionModel().getSelectedIndex());
        }
    });

    // Display the top films
        List<Film> topFilms = fs.getTopFilms();
        ObservableList<Film> topItems = FXCollections.observableArrayList(topFilms);
        TopFilms.setItems(topItems);
        if (TopFilms != null) {
            TopFilms.setCellFactory(param -> new FilmListCell());
        }
 if(UserSession.getRole()!=Type.ADMINSTRATEUR){
                redajouterf.setVisible(false);
                redsupf.setVisible(false);
                redmodifF.setVisible(false);
            }
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
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/updateF.fxml"));
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
ObservableList<Film> items = FXCollections.observableArrayList(films);
/*
for (Film film : films) {
    String item = film.getTitre() + " \n\n " + film.getDateRealisation() + " \n\n " + film.getGenre();
    items.add(item);
}
*/
ListF.setItems(items);
 //   ListF.setItems(items);
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void RedAjouterF(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/ajouterfilm.fxml"));
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.showAndWait(); // Wait for the updates window to close
        
        // Update the ListView with the latest data
       FilmService fs = new FilmService();
       List<Film> films = fs.afficherFilm();
ObservableList<Film> items = FXCollections.observableArrayList(films);
/*
for (Film film : films) {
    String item = film.getTitre() + " \n\n " + film.getDateRealisation() + " \n\n " + film.getGenre();
    items.add(item);
}
*/
ListF.setItems(items);
   // ListF.setItems(items);
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void RedSupF(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/supprimerf.fxml"));
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.showAndWait(); // Wait for the updates window to close
        
        // Update the ListView with the latest data
       FilmService fs = new FilmService();
       List<Film> films = fs.afficherFilm();
ObservableList<Film> items = FXCollections.observableArrayList(films);
/*
for (Film film : films) {
    String item = film.getTitre() + " \n\n " + film.getDateRealisation() + " \n\n " + film.getGenre();
    items.add(item);
}
*/
ListF.setItems(items);
    //ListF.setItems(items);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Filmdet.fxml"));
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