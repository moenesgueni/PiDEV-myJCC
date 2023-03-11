/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Salle;
import Services.SalleService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class SupprimersController implements Initializable {

    @FXML
    private TextField nomftodel;
    @FXML
    private Button bsupprimers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
@FXML
    private void SupprimerS(ActionEvent event) {
        Salle s = new Salle();
        SalleService ss = new SalleService();
         ss.supprimerSalle(nomftodel.getText());
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Salle " + nomftodel.getText() + " est supprimée avec succes");
        confirmation.show();
    }

    
    
}
