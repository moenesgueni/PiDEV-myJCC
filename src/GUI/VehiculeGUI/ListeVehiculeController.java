/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.VehiculeGUI;

import GUI.ListeHotelController;
import GUI.ModifierHotelController;
import Models.Hotel;
import Models.Vehicule;
import Services.HotelService;
import Services.LocationVehiculeService;
import Services.VehiculeService;
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


import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author youssef
 */
public class ListeVehiculeController implements Initializable {


    @FXML
    private ListView<Vehicule> listeV;
    Preferences prefs = Preferences.userNodeForPackage(ListeHotelController.class);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         VehiculeService vs = new VehiculeService();
    
    listeV.setCellFactory(param -> new VehiculeListCell());
        VehiculeService viheculeservice = new VehiculeService();
    List<Vehicule> vehicules = viheculeservice.getAllVehicules();
    ObservableList<Vehicule> items = FXCollections.observableArrayList(vehicules);
    listeV.setItems(items);
    // Ajouter les éléments de la liste à la ListView
    listeV.setItems(items);
    // 2. Créez une ArrayList de maps pour stocker les attributs de chaque hôtel
    listeV.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
    // Récupérer l'index de l'élément sélectionné
    int selectedIndex = newValue.intValue();   
    // Récupérer l'objet Hotel correspondant à cet index
    Vehicule selectedVehicule = vehicules.get(selectedIndex);   

 
});
    }
public static Vehicule fromString(String vehiculeString) {
    String[] parts = vehiculeString.split(" - ");
    String matricule = parts[0];
    String type = parts[1];
    String marque = parts[2];
    String couleur = parts[3];
   
    
    return new Vehicule(matricule, type, marque, couleur);
    
}

    @FXML
    private void modifierVehicule(MouseEvent event) {
        
    Vehicule selectedVehicule = listeV.getSelectionModel().getSelectedItem();
    Vehicule v1 = new Vehicule();
    VehiculeService vs = new VehiculeService();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierVehicule.fxml"));
            Parent root = loader.load();
            ModifierVehiculeController modifierVehiculeController = loader.getController();
            v1=selectedVehicule;
            modifierVehiculeController.ModifyData(v1);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajouterV(ActionEvent event) {
    try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjouterVehicule.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);   
    stage.showAndWait();
    } catch (IOException ex) {
      Logger.getLogger(ListeHotelController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}