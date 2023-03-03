/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Hotel;
import Services.HotelService;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class AjouterHotelController implements Initializable {

    @FXML
    private TextField nomh;
    @FXML
    private TextField adrh;
    @FXML
    private TextField nbr_chmbre;
    @FXML
    private TextField telh;
    @FXML
    private TextArea desch;
    @FXML
    private Button ajth;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    @FXML
    private void ajouterHotel(ActionEvent event) {
     String nom = nomh.getText();
    String adresse = adrh.getText();
    String nbr_chambres_str = nbr_chmbre.getText();
    String tel_str = telh.getText();
    String description = desch.getText();
/*---------------Verification que tous les champs sont rempli-----------------*/
    if (nom.isEmpty() || adresse.isEmpty() || nbr_chambres_str.isEmpty() || tel_str.isEmpty() || description.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.show();
        return;
    }
/*-------------Controle de saisie sur le champ nombre de chambre positive et entier ------*/
if (!nbr_chambres_str.matches("\\d+")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Veuillez saisir un nombre entier positif pour le nombre de chambres");
    alert.show();
    return;
}
int nbr_chambres = Integer.parseInt(nbr_chambres_str);
if (nbr_chambres <= 0) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Veuillez saisir un nombre entier positif pour le nombre de chambres");
    alert.show();
    return;
}
/*-------------Controle de saisie sur le champ telephone: chaine numerique de longueur 8 ------*/
if (!tel_str.matches("\\d{8}")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Veuillez saisir un numero de telephone de 8 chiffres");
    alert.show();
    return;
}

        Hotel h =new Hotel();
        HotelService hs = new HotelService();
        h.setLibelle(nomh.getText());
        h.setAdresse(adrh.getText());
        h.setNbre_chambres(Integer.parseInt(nbr_chmbre.getText()));
        h.setTelephone( Integer.parseInt(telh.getText()));
        h.setDescription(desch.getText());
        hs.addHotel(h);
        /************Banner ******************/
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Hotel " + nomh.getText() + " est effectuee avec succes");
        confirmation.show();
        /*******Vider les texteFiled********/
             nomh.setText("");
             adrh.setText("");
             nbr_chmbre.setText("");
             telh.setText("");
             desch.setText("");

    }

    @FXML
    private void AnnulerH(ActionEvent event) {
       
    }
    
}
