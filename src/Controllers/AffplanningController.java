/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.PlanningFilmSalle;
import Services.PlanningService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import myjcc.NewFXMain;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class AffplanningController implements Initializable {

    @FXML
    private ListView<String> ListP;
    @FXML
    private Button redajoutpla;
    @FXML
    private Button redmodifpla;
    @FXML
    private Button redsuppla;
    private PlanningFilmSalle selectedplan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         PlanningService ps = new PlanningService();
        List<PlanningFilmSalle> plannings = ps.afficherPlanning();
        
          ObservableList<String> items = FXCollections.observableArrayList();
    for (PlanningFilmSalle planning : plannings ) {
        String item = planning.getFilm().getTitre()+ " - " + planning.getSalle().getNomSalle()+ " - " + planning.getDatediffusion()+ " - " + planning.getHeurediffusion();
        
        items.add(item);
    }    
    ListP.setItems(items);
    ListP.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    selectedplan = plannings.get(ListP.getSelectionModel().getSelectedIndex());
});
    
   
            }

    @FXML
    private void RedAjoutPl(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ajoutpla.fxml"));
        Parent root = loader.load();
         Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.showAndWait(); // Wait for the updates window to close
        PlanningService ps = new PlanningService();
        List<PlanningFilmSalle> plannings = ps.afficherPlanning();
        
          ObservableList<String> items = FXCollections.observableArrayList();
    for (PlanningFilmSalle planning : plannings ) {
        String item = planning.getFilm().getTitre()+ " - " + planning.getSalle().getNomSalle()+ " - " + planning.getDatediffusion()+ " - " + planning.getHeurediffusion();
        
        items.add(item);
    }    
    ListP.setItems(items);
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RedModifPla(ActionEvent event) {
        if (selectedplan == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucun planning sélectionné");
        alert.setContentText("Veuillez sélectionner un planning à modifier.");
        alert.showAndWait();
        return;
    }
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/updatepla.fxml"));
        Parent root = loader.load();
        updateController controller = loader.getController();
        controller.initData(selectedplan);
         Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.showAndWait(); // Wait for the updates window to close
        PlanningService ps = new PlanningService();
        List<PlanningFilmSalle> plannings = ps.afficherPlanning();
        
          ObservableList<String> items = FXCollections.observableArrayList();
    for (PlanningFilmSalle planning : plannings ) {
        String item = planning.getFilm().getTitre()+ " - " + planning.getSalle().getNomSalle()+ " - " + planning.getDatediffusion()+ " - " + planning.getHeurediffusion();
        
        items.add(item);
    }    
    ListP.setItems(items);
        
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void RedSuppPla(ActionEvent event) {
        if (selectedplan == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucun planning sélectionné");
        alert.setContentText("Veuillez sélectionner un planning à supprimer.");
        alert.showAndWait();
        return;
    }
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/suppplanning.fxml"));
        Parent root = loader.load();
        SuppplanningController controller = loader.getController();
        controller.initData(selectedplan);
         Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.showAndWait(); // Wait for the updates window to close
        PlanningService ps = new PlanningService();
        List<PlanningFilmSalle> plannings = ps.afficherPlanning();
        
          ObservableList<String> items = FXCollections.observableArrayList();
    for (PlanningFilmSalle planning : plannings ) {
        String item = planning.getFilm().getTitre()+ " - " + planning.getSalle().getNomSalle()+ " - " + planning.getDatediffusion()+ " - " + planning.getHeurediffusion();
        
        items.add(item);
    }    
    ListP.setItems(items);
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
