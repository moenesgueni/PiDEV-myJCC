/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Hotel;
import Services.HotelService;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class ModifierHotelController implements Initializable {
    HotelService hs = new HotelService();

    @FXML
    private TextField nomh;
    @FXML
    private TextField adrH;
    @FXML
    private TextField nbreH;
    @FXML
    private TextField telH;
    @FXML
    private TextArea descH;
    
    private Hotel hotel ;
         Preferences userP = Preferences.userNodeForPackage(ModifierHotelController.class);
                String Id = userP.get("selectedHotelId", "..") ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

             System.out.println(Id);
    }    

    @FXML
    private void ModifierH(ActionEvent event) {
       HotelService hs = new HotelService();
       Hotel h1 = new Hotel(Integer.parseInt(Id) ,nomh.getText(), adrH.getText(), Integer.parseInt(telH.getText()), Integer.parseInt(nbreH.getText()), descH .getText());
      
       hs.updateHotel(h1); 
        
        
    }
    void ModifyData(Hotel hotel) {
        this.hotel = hotel;
        nomh.setText(hotel.getLibelle());
        adrH.setText(hotel.getAdresse());
        nbreH.setText(String.valueOf(hotel.getNbre_chambres()));
        telH.setText(String.valueOf(hotel.getTelephone())); 
        descH.setText(hotel.getDescription());
    }

    @FXML
    private void SupprimerH(ActionEvent event) {
        HotelService hs = new HotelService();
        int id = Integer.parseInt(Id);
        hs.deleteHotel(id);
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Hotel  est Supprimee avec succes");
        confirmation.show();
        
    }
   
    
}
