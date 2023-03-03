/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Film;
import Models.Salle;
import Services.SalleService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import myjcc.NewFXMain;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class AffichagesController implements Initializable {

    @FXML
    private ListView<String> ListS;
    @FXML
    private Button redmodifs;
    @FXML
    private Button redsupprimers;
    @FXML
    private Button redajouts;
    @FXML
    private Button MapB;
    private Salle selectedSalle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SalleService ss = new SalleService();
        
        List<Salle> salles = ss.afficherSalle();
        
          ObservableList<String> items = FXCollections.observableArrayList();
    for (Salle salle : salles) {
        String item = salle.getNomSalle() + " - " + salle.getAdresse() + " - " + salle.getCapacite() + " - " + salle.getNumTel_salle() + " - " + salle.getEmail_Salle() + " - " + salle.getTemps_Ouverture() + " - " + salle.getTemps_Fermuture() + " - " + salle.getAvis();
        items.add(item);
    }    
    ListS.setItems(items);
    ListS.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    selectedSalle = salles.get(ListS.getSelectionModel().getSelectedIndex());
});
        
    }    
    


    @FXML
private void RedModifS(ActionEvent event) {
    if (selectedSalle == null) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucune salle sélectionnée");
        alert.setContentText("Veuillez sélectionner une salle à modifier.");
        alert.showAndWait();
        return;
    }
    try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/updates.fxml"));
        Parent root = loader.load();
        updatesController controller = loader.getController();
        controller.initData(selectedSalle);
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.showAndWait(); // Wait for the updates window to close
        
        // Update the ListView with the latest data
        SalleService ss = new SalleService();
        List<Salle> salles = ss.afficherSalle();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Salle salle : salles) {
            String item = salle.getNomSalle() + " - " + salle.getAdresse() + " - " + salle.getCapacite() + " - " + salle.getNumTel_salle() + " - " + salle.getEmail_Salle() + " - " + salle.getTemps_Ouverture() + " - " + salle.getTemps_Fermuture() + " - " + salle.getAvis();
            items.add(item);
        }    
        ListS.setItems(items);
        
    } catch (IOException ex) {
        Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    @FXML
    private void RedSupprimerS(ActionEvent event) {
     try{
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/supprimers.fxml"));
        Scene scene = new Scene(root);
         Stage newStage = new Stage();
          newStage.setScene(scene);
          
        newStage.showAndWait(); // Wait for the updates window to close
        SalleService ss = new SalleService();
        List<Salle> salles = ss.afficherSalle();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Salle salle : salles) {
            String item = salle.getNomSalle() + " - " + salle.getAdresse() + " - " + salle.getCapacite() + " - " + salle.getNumTel_salle() + " - " + salle.getEmail_Salle() + " - " + salle.getTemps_Ouverture() + " - " + salle.getTemps_Fermuture() + " - " + salle.getAvis();
            items.add(item);
        }    
        ListS.setItems(items);
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }

    @FXML
    private void RedAjouterS(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajoutersalle.fxml"));
        Scene scene = new Scene(root);
         Stage newStage = new Stage();
         newStage.setScene(scene);
          
        newStage.showAndWait(); // Wait for the updates window to close
        SalleService ss = new SalleService();
        List<Salle> salles = ss.afficherSalle();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Salle salle : salles) {
            String item = salle.getNomSalle() + " - " + salle.getAdresse() + " - " + salle.getCapacite() + " - " + salle.getNumTel_salle() + " - " + salle.getEmail_Salle() + " - " + salle.getTemps_Ouverture() + " - " + salle.getTemps_Fermuture() + " - " + salle.getAvis();
            items.add(item);
        }    
        ListS.setItems(items);
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ShowMap(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Map.fxml"));
        Scene scene = new Scene(root);
         Stage newStage = new Stage();
          newStage.setScene(scene);
          newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
