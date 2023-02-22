/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Hotel;
import Services.HotelService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class ListeHotelController implements Initializable {

    @FXML
    private ListView<String> listeH;
    Preferences prefs = Preferences.userNodeForPackage(ListeHotelController.class);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         HotelService hs = new HotelService();
        List<Hotel> hotels = hs.getAllHotels();
    // Créer une liste d'éléments de ListView qui contiennent les propriétés des instances de la classe Hotel
    ObservableList<String> items = FXCollections.observableArrayList();
    for (Hotel hotel : hotels) {
        
        String item =hotel.getLibelle() + " - " + hotel.getAdresse() + " - " + hotel.getNbre_chambres() + " - " + hotel.getTelephone() + " - " + hotel.getDescription();
        items.add(item);
    }
    // Ajouter les éléments de la liste à la ListView
    listeH.setItems(items);
       // 2. Créez une ArrayList de maps pour stocker les attributs de chaque hôtel
    listeH.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
    // Récupérer l'index de l'élément sélectionné
    int selectedIndex = newValue.intValue();   
    // Récupérer l'objet Hotel correspondant à cet index
    Hotel selectedHotel = hotels.get(selectedIndex);   
    // Récupérer l'ID de l'hôtel
    int hotelId = selectedHotel.getId();
     prefs.putInt("selectedHotelId", hotelId);
        System.out.println(hotelId);
   
});   


    }    

   @FXML
    private void modifierHotel(MouseEvent event) {
    String selectedHotel = listeH.getSelectionModel().getSelectedItem();
    Hotel h1 = new Hotel();
    HotelService hs = new HotelService();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ModifierHotel.fxml"));
            Parent root = loader.load();
            ModifierHotelController modifierHotelController = loader.getController();
            h1=ListeHotelController.fromString(selectedHotel);
            modifierHotelController.ModifyData(h1);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
              stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        
    }
public static Hotel fromString(String hotelString) {
    String[] parts = hotelString.split(" - ");
    String nom = parts[0];
    String adresse = parts[1];
    int nbChambres = Integer.parseInt(parts[2]);
    int telephone = Integer.parseInt(parts[3]);
    
    String description = parts[4];
    
    return new Hotel(nom, adresse, telephone, nbChambres, description);
}



}
