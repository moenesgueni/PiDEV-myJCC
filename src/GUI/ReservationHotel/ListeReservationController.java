/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ReservationHotel;

import GUI.ListeHotelController;
import Models.Hotel;
import Models.ReservationHotel;
import Models.User;
import Services.HotelService;
import Services.ReservationHotelService;
import Services.UserService;
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
public class ListeReservationController implements Initializable {

    @FXML
    private ListView<String> listR;
Preferences prefs = Preferences.userNodeForPackage(ListeReservationController.class);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // TODO
    ReservationHotelService rs = new ReservationHotelService();
     List<ReservationHotel> reservations = rs.getAllReservationHotel();
    // Créer une liste d'éléments de ListView qui contiennent les propriétés des instances de la classe Hotel
    ObservableList<String> items = FXCollections.observableArrayList();
    for (ReservationHotel reservation : reservations) {
        String item =reservation.getDateReservation()+ " - " + reservation.getDate_debut()+ " - " + reservation.getDate_fin()+ " - " + reservation.getTarifTotal()+ " - " + reservation.getHotel().getLibelle()+ " - " + reservation.getUser().getID_User();
        items.add(item);
    }
    // Ajouter les éléments de la liste à la ListView
    listR.setItems(items);
    // 2. Créez une ArrayList de maps pour stocker les attributs de chaque hôtel
    listR.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
    // Récupérer l'index de l'élément sélectionné
    int selectedIndex = newValue.intValue();   
    // Récupérer l'objet Hotel correspondant à cet index
    ReservationHotel selectedReservation = reservations.get(selectedIndex);  
    // Récupérer l'ID de la reservation
    int ReservationId = selectedReservation.getIdReservationH();
     prefs.putInt("selectedReservationId", ReservationId);
     System.out.println(ReservationId); 
    
});    
    
 }   

    @FXML
    private void modifierReservation(MouseEvent event) {
    String selectedreservation = listR.getSelectionModel().getSelectedItem();
    ReservationHotel r1 = new ReservationHotel();
    ReservationHotelService rs = new ReservationHotelService();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReservation.fxml"));
            Parent root = loader.load();
            ModifierReservationController modifierReservationController = loader.getController();
            r1=ListeReservationController.fromString(selectedreservation);
            modifierReservationController.ModifyData(r1);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
              stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }}      
  
    public static ReservationHotel fromString(String reservationString) {
    String[] parts = reservationString.split(" - ");
    // Le format de la chaîne de caractères
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String DateR = parts[0];
    Date DateReservation = Date.valueOf(LocalDate.parse(DateR, formatter));
    String DateD = parts[1];
    Date DateDebut = Date.valueOf(LocalDate.parse(DateD, formatter));
    String DateF = parts[2];
    Date DateFin = Date.valueOf(LocalDate.parse(DateF, formatter));
    float TarifT = Float.parseFloat(parts[3]);
    String nomH = parts[4];
    int ID_User =Integer.parseInt(parts[5]); 
    Hotel h = new Hotel();
    HotelService hs = new HotelService();
    h=hs.filterByName(nomH);
         
    User u = new User();
    UserService us = new UserService();
    u=us.afficherUserbyID(ID_User);
 return new ReservationHotel(DateReservation, DateDebut, DateFin, TarifT, h, u);
}

    @FXML
    private void ajouterReservationH(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReservationHotel.fxml"));
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
