/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ReservationHotel;

import Models.Hotel;
import Models.ReservationHotel;
import Models.User;
import Services.HotelService;
import Services.ReservationHotelService;
import Services.UserService;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
public class AjouterReservationHotelController implements Initializable {

    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField tarifT_H;
    @FXML
    private TextField nomH;
    @FXML
    private TextField iduser;
    HotelService hs = new HotelService();
    UserService us = new UserService();
    ReservationHotelService rs = new ReservationHotelService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterReservation(ActionEvent event) {
         if (nomH.getText().isEmpty() || iduser.getText().isEmpty() || 
            date_debut.getValue() == null || date_fin.getValue() == null ||
            tarifT_H.getText().isEmpty()) {
        // Alert user to fill in all fields
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs!");
        alert.showAndWait();
        return;
    }
     Hotel h=new Hotel();
     h=hs.filterByName(nomH.getText());
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
    ReservationHotel reservation = new ReservationHotel(DateReservation, dateD, dateF,Float.parseFloat(tarifT_H.getText()), h, u);
   rs.addReservationHotel(reservation);
    /************Banner ******************/
    Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
    confirmation.setContentText("Reservation est effectuee avec succes");
    confirmation.show();
    /*******Vider les texteFiled********/
    tarifT_H.setText("");
    iduser.setText("");
    nomH.setText("");
    date_debut.setValue(null);
    date_fin.setValue(null);

 
    }

    @FXML
    private void AnnulerR(ActionEvent event) {
    }
    
}
