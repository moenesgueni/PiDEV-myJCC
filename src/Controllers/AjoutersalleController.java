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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class AjoutersalleController implements Initializable {

    @FXML
    private TextField NomS;
    @FXML
    private TextField numtel;
    @FXML
    private TextField email;
    @FXML
    private TextField tempsouv;
    @FXML
    private TextField tempsfer;
    @FXML
    private TextField avis;
    @FXML
    private TextField adresse;
    @FXML
    private TextField cap;
    @FXML
    private Button bajouters;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterS(ActionEvent event) {
        Salle s = new Salle();
        SalleService ss = new SalleService();
        s.setNomSalle(NomS.getText());
       s.setAdresse(adresse.getText());
       s.setCapacite(Integer.parseInt(cap.getText()));
       s.setNumTel_salle(numtel.getText());
       s.setEmail_Salle(email.getText());
       s.setTemps_Ouverture(tempsouv.getText());
       s.setTemps_Fermuture(tempsfer.getText());
       s.setAvis(Float.parseFloat(avis.getText()));
       ss.ajouterSalle(s);
       Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Salle cinéma " + NomS.getText() + " est ajoutée avec succes");
        confirmation.show();
        NomS.setText("");
        adresse.setText("");
        cap.setText("");
        numtel.setText("");
        email.setText("");
        tempsouv.setText("");
        tempsfer.setText("");
        avis.setText("");
    }
    
}
