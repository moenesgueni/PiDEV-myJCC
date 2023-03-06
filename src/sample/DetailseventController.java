/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import Models.Evenement;
import Services.EventService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailseventController implements Initializable {

    @FXML
    private TextField tfnom_event;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tflieu;
    @FXML
    private DatePicker tfdate_et_heure;
    @FXML
    private TextField tftype_event;
    @FXML
    private Button update_button;
    @FXML
    private Button delete_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfnom_event.setText(ItemController.ev.getNom_event());
        tfdate_et_heure.setValue(ItemController.ev.getDate_et_heure().toLocalDate());
        tflieu.setText(ItemController.ev.getLieu());
        tftype_event.setText(ItemController.ev.getType_event());
        tfdescription.setText(ItemController.ev.getDescription());
        
        
        
        
    }    

    @FXML
    private void update_button(ActionEvent event) {
        
        
        
            Evenement evenement = new Evenement();
                
            evenement.setId(ItemController.ev.getId());
            evenement.setNom_event(tfnom_event.getText());
            evenement.setDate_et_heure(java.sql.Date.valueOf(tfdate_et_heure.getValue()));
            evenement.setLieu(tflieu.getText());
            evenement.setType_event(tftype_event.getText());
            evenement.setDescription(tfdescription.getText());

           
            // Update the event in the database
            EventService es = new EventService();
            es.ModifierEvent(evenement);
        
        
        
        
    }

    @FXML
    private void delete_button(ActionEvent event) {
        
         
         Evenement evenement = new Evenement();
         evenement.setNom_event(tfnom_event.getText());
         EventService es = new EventService();
         es.SupprimerEvent(evenement);
        
    }
    
}
