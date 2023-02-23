/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ReservationHotel;

import Models.ReservationHotel;
import Services.ReservationHotelService;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class ModifierReservationController implements Initializable {

    @FXML
    private DatePicker datef;
    @FXML
    private DatePicker dated;
    @FXML
    private TextField tarif;
    private ReservationHotel r = new ReservationHotel();
     Preferences userP = Preferences.userNodeForPackage(ModifierReservationController.class);
                String Id = userP.get("selectedReservationId", "..") ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void ModifyData(ReservationHotel r1) {
       this.r = r1;
        dated.setValue(r1.getDate_debut().toLocalDate());
        datef.setValue(r1.getDate_fin().toLocalDate());
        tarif.setText(String.valueOf(r1.getTarifTotal()));
        
    }

    @FXML
    private void modifierR(ActionEvent event) {
        ReservationHotelService rs = new ReservationHotelService();
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
    ReservationHotel r1 = new ReservationHotel(Integer.parseInt(Id),DateReservation,DateDebut,DateFin,Float.parseFloat(tarif.getText()) );    
       rs.updateReservationHotel(r1); 
    }

    @FXML
    private void SupprimerR(ActionEvent event) {
        ReservationHotelService rs = new ReservationHotelService();
        int id = Integer.parseInt(Id);
        rs.deleteReservationHotel(id);
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Hotel  est Supprimee avec succes");
        confirmation.show();        
    }
    
 }
    

