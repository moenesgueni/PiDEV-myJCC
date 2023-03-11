/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.LocationGUI;

import Models.Location;
import Services.LocationVehiculeService;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class ModifierLocationController implements Initializable {
    @FXML
    private DatePicker datef;
    @FXML
    private DatePicker dated;
    @FXML
    private TextField tarif;
    private Location l = new Location();
         Preferences userP = Preferences.userNodeForPackage(ModifierLocationController.class);
                String Id = userP.get("selectedLocationId", "..") ;

    public void ModifyData(Location l1) {
       this.l = l1;
        dated.setValue(l1.getDate_debut().toLocalDate());
        datef.setValue(l1.getDate_fin().toLocalDate());
        tarif.setText(String.valueOf(l1.getTarifTotal())); 
    }



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void modifierR(ActionEvent event) {
        LocationVehiculeService rs = new LocationVehiculeService();
        LocalDate DateD;
        DateD = dated.getValue();
        Date DateDebut = Date.valueOf(DateD);
    
         LocalDate DateF;
         DateF = datef.getValue();
         Date DateFin = Date.valueOf(DateF); 
    // obtenir la date système actuelle
        LocalDate localDate = LocalDate.now();
        // convertir en objet java.sql.Date
        Date DateReservation = Date.valueOf(localDate);          
        Location l2 = new Location(Integer.parseInt(Id), DateReservation, DateDebut, DateFin, Float.parseFloat(tarif.getText()));

       rs.updateLocationVehicule(l2);         
    }

    @FXML
    private void SupprimerR(ActionEvent event) {
         LocationVehiculeService vs = new LocationVehiculeService();
        int id = Integer.parseInt(Id);
        vs.deleteLocationVehicule(id);
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Vehicule  est Supprimee avec succes");
        confirmation.show();          
    }
    
}
