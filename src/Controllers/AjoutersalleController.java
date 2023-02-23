/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Salle;
import Services.SalleService;
import java.net.URL;
import java.util.List;
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
        if (NomS.getText().isEmpty() || adresse.getText().isEmpty() || cap.getText().isEmpty() || numtel.getText().isEmpty() || email.getText().isEmpty() || tempsouv.getText().isEmpty() || tempsfer.getText().isEmpty() || avis.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Tous les champs doivent être remplis");
    alert.show();
    return;
}

        
        List<Salle> salles = ss.afficherSalle();
    String nomSalle = NomS.getText();
    for (Salle salle : salles) {
        if (salle.getNomSalle().equals(nomSalle)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Le nom de la salle doit être unique");
            alert.show();
            return;
        }
    }
    
    String numTel = numtel.getText();
    if (!numTel.matches("\\d+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Le numéro de téléphone ne doit contenir que des chiffres");
        alert.show();
        return;
    }
    
    
    String mail = this.email.getText();
    if (!mail.contains("@")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("L'adresse e-mail doit contenir le caractère @");
        alert.show();
        return;
    }
    
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
