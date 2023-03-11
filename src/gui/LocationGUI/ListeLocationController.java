/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.LocationGUI;

import Models.Location;
import Models.User;
import Models.Vehicule;
import Services.LocationVehiculeService;
import Services.UserService;
import Services.VehiculeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class ListeLocationController implements Initializable {

    @FXML
    private ListView<Location> listL;
Preferences prefs = Preferences.userNodeForPackage(ListeLocationController.class);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //TODO
        LocationVehiculeService ls = new LocationVehiculeService();
     
    listL.setCellFactory(param -> new LocationListCell());
    LocationVehiculeService locationservice = new LocationVehiculeService();
    List<Location> locations = locationservice.getAllLocationVehicule();
    ObservableList<Location> items = FXCollections.observableArrayList(locations);
    listL.setItems(items);


    // Ajouter les éléments de la liste à la ListView
    listL.setItems(items);
    // 2. Créez une ArrayList de maps pour stocker les attributs de chaque hôtel
    listL.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
    // Récupérer l'index de l'élément sélectionné
    int selectedIndex = newValue.intValue();    
    // Récupérer l'objet Location correspondant à cet index
    Location selectedVehicule = locations.get(selectedIndex);
        // Récupérer l'ID de la reservation
    int ReservationId = selectedVehicule.getIdLocationV();
     prefs.putInt("selectedLocationId", ReservationId);
     System.out.println(ReservationId); 
});   

}

    @FXML
    private void modifiervehicule(MouseEvent event) {
    Location selectedLocation = listL.getSelectionModel().getSelectedItem();
    Location l1 = new Location();
    LocationVehiculeService ls = new LocationVehiculeService();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierLocation.fxml"));
            Parent root = loader.load();
            ModifierLocationController modifierLocationController = loader.getController();
            l1=selectedLocation;
            modifierLocationController.ModifyData(l1);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
              stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }}      
  
    public static Location fromString(String LocationString) {
    String[] parts = LocationString.split(" - ");
    // Le format de la chaîne de caractères
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String DateR = parts[0];
    Date DateReservation = Date.valueOf(LocalDate.parse(DateR, formatter));
    String DateD = parts[1];
    Date DateDebut = Date.valueOf(LocalDate.parse(DateD, formatter));
    String DateF = parts[2];
    Date DateFin = Date.valueOf(LocalDate.parse(DateF, formatter));
    float TarifT = Float.parseFloat(parts[3]);
    String matricule = parts[4];
    int ID_User =Integer.parseInt(parts[5]); 
    Vehicule v = new Vehicule();
    VehiculeService hs = new VehiculeService();
    v=hs.GetVehiculeBymatricule(matricule);
         
    User u = new User();
    UserService us = new UserService();
    u=us.afficherUserbyID(ID_User);
 return new Location(DateReservation, DateDebut, DateFin, TarifT, v, u);
        
    }



    @FXML
    private void ajouterLocation(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterLocation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ListeLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
