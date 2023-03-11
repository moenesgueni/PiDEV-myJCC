/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.VehiculeGUI;

import Models.Vehicule;
import Services.VehiculeService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
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
public class ModifierVehiculeController implements Initializable {

    @FXML
    private TextField matricule;
    @FXML
    private TextField typeV;
    @FXML
    private TextField marqueV;
    @FXML
    private TextField couleurV;
    private Vehicule v = new Vehicule();
             Preferences userP = Preferences.userNodeForPackage(ModifierVehiculeController.class);
                String Id = userP.get("selectedVehiculeMat", "..") ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModiferVehicule(ActionEvent event) {
        VehiculeService vs = new VehiculeService();
       Vehicule v1 = new Vehicule(matricule.getText() , typeV.getText(), marqueV.getText(), couleurV.getText());
        vs.updateVehicule(v1); 
        
        
        
    }

    @FXML
    private void SupprimerVehicule(ActionEvent event) {
        
        VehiculeService vs = new VehiculeService();
        vs.deleteVehicule(matricule.getText());
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Vehicule Supprimee avec succes");
        confirmation.show();
    }


    void ModifyData(Vehicule v1) {
        this.v = v1;
        matricule.setText(v1.getMaricule());
        typeV.setText(v1.getType());
        marqueV.setText(v1.getMarque());
        couleurV.setText(v1.getCouleur());
   
    }
    
}
