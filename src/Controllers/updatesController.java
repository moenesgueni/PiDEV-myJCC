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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class updatesController implements Initializable {

    @FXML
    private TextField NomS;
    @FXML
    private TextField adresse;
    @FXML
    private TextField cap;
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
    private Button bmodifiers;
    private TextField IDS;

    private Salle selectedSalle;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void initData(Salle s) {
        selectedSalle = s;
        NomS.setText(s.getNomSalle());
        adresse.setText(s.getAdresse());
        cap.setText(Integer.toString(s.getCapacite()));
        numtel.setText(s.getNumTel_salle());
        email.setText(s.getEmail_Salle());
        tempsouv.setText(s.getTemps_Ouverture());
        tempsfer.setText(s.getTemps_Fermuture());
        avis.setText(Float.toString(s.getAvis()));
    }
    
    @FXML
    private void ModifierS(ActionEvent event) {
        if (NomS.getText().isEmpty() || adresse.getText().isEmpty() || cap.getText().isEmpty() || numtel.getText().isEmpty() || email.getText().isEmpty() || tempsouv.getText().isEmpty() || tempsfer.getText().isEmpty() || avis.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tous les champs doivent être remplis");
            alert.show();
            return;
        }

        Salle s = new Salle(selectedSalle.getID_salle(),NomS.getText(),adresse.getText(),Integer.parseInt(cap.getText()),numtel.getText(),email.getText(),tempsouv.getText(),tempsfer.getText(),Float.parseFloat(avis.getText()));

        SalleService ss = new SalleService();

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

        ss.updateSalle(s);
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Salle est modifiée avec succes");
        confirmation.show();
    }

}
