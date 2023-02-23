/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.LocationGUI;

import Models.Location;
import Models.User;
import Models.Vehicule;
import Services.LocationVehiculeService;
import Services.UserService;
import Services.VehiculeService;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
public class AjouterLocationController implements Initializable {

    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField tarifT_H;
    @FXML
    private TextField iduser;
    @FXML
    private TextField matricule;
    VehiculeService vs = new VehiculeService();
    UserService us = new UserService();
    LocationVehiculeService ls =new LocationVehiculeService();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterLocation(ActionEvent event) {
   /*     if (tarifT_H.getText().isEmpty() || iduser.getText().isEmpty() || matricule.getText().isEmpty() || date_debut.getValue() == null || date_fin.getValue() == null) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez remplir tous les champs.");
    alert.showAndWait();
    return;
}
*/
     Vehicule v=new Vehicule();
     v=vs.GetVehiculeBymatricule(matricule.getText());
     User u =new User();
     u=us.afficherUserbyID(Integer.parseInt(iduser.getText()));
 
    LocalDate dd =date_debut.getValue();
    Date dateD = Date.valueOf(dd);
    LocalDate df =date_fin.getValue();
   Date dateF = Date.valueOf(df);
   // Obtenir la date système actuelle
    LocalDate localDate = LocalDate.now();
    // Convertir en java.sql.Date
    Date DateReservation = Date.valueOf(localDate.toString());
    Location location = new Location(DateReservation, dateD, dateF, Float.parseFloat(tarifT_H.getText()), v, u);
   ls.addLocationBehicule(location);
    /************Banner ******************/
    Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
    confirmation.setContentText("Reservation est effectuee avec succes");
    confirmation.show();
    /*******Vider les texteFiled********/
    tarifT_H.setText("");
    iduser.setText("");
    matricule.setText("");
    date_debut.setValue(null);
    date_fin.setValue(null);
       
        
    }

    @FXML
    private void AnnulerL(ActionEvent event) {
    }
    
}
