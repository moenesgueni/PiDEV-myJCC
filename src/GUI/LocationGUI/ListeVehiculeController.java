/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.LocationGUI;

import Models.Hotel;
import Models.Vehicule;
import Services.HotelService;
import Services.VehiculeService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.ListView;
/**
 * FXML Controller class
 *
 * @author youssef
 */
public class ListeVehiculeController implements Initializable {


    @FXML
    private ListView<String> listeV;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         VehiculeService vs = new VehiculeService();
        List<Vehicule> vehicules = vs.getAllVehicules();
    // Créer une liste d'éléments de ListView qui contiennent les propriétés des instances de la classe Hotel
    ObservableList<String> items = FXCollections.observableArrayList();
    for (Vehicule vehicule : vehicules) {
        
        String item =vehicule.getMaricule()+ " - " + vehicule.getType()+ " - " + vehicule.getMarque()+ " - " + vehicule.getCouleur();
        items.add(item);
    }
    // Ajouter les éléments de la liste à la ListView
    listeV.setItems(items);
       // 2. Créez une ArrayList de maps pour stocker les attributs de chaque hôtel
    listeV.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
    // Récupérer l'index de l'élément sélectionné
    int selectedIndex = newValue.intValue();   
    // Récupérer l'objet Hotel correspondant à cet index
    Vehicule selectedVehicule = vehicules.get(selectedIndex);   
    // Récupérer l'ID de l'hôtel
  
        
        
        
            
    
});
    }
public static Vehicule fromString(String vehiculeString) {
    String[] parts = vehiculeString.split(" - ");
    String matricule = parts[0];
    String type = parts[1];
    String marque = parts[2];
    String couleur = parts[3];
    
    String description = parts[4];
    
    return new Vehicule(matricule, type, marque, couleur);
    
}
}