/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.LocationGUI;

import Models.Vehicule;
import Services.VehiculeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class AjouterVehiculeController implements Initializable {

    @FXML
    private TextField matriculeV;
    @FXML
    private TextField typeV;
    @FXML
    private TextField marqueV;
    @FXML
    private TextField couleurV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterVehicule(ActionEvent event) {
        Vehicule v = new Vehicule();
        VehiculeService vs = new VehiculeService();
        v.setMaricule(matriculeV.getText());
        v.setType(typeV.getText());
        v.setMarque(marqueV.getText());
        v.setCouleur(couleurV.getText());
        vs.addVehicule(v);
        /**
         * **********Banner *****************
         */
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Vehicule de matricule =  " + matriculeV.getText() + " est Ajoutee avec succes");
        confirmation.show();
        /**
         * *****Vider les texteFiled*******
         */
        matriculeV.setText("");
        typeV.setText("");
        marqueV.setText("");
        couleurV.setText("");
       
    }

    @FXML
    private void annulerV(ActionEvent event) {
    }
    
}
