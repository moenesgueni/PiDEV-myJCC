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
    
        
    }    


    @FXML
    private void RedModifS(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/updates.fxml"));
        Scene scene = new Scene(root);
         Stage newStage = new Stage();
          newStage.setScene(scene);
          newStage.show();
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
          newStage.show();
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
          newStage.show();
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
