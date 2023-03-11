/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Salle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class SalledetController implements Initializable {

    @FXML
    private Label nms;
    @FXML
    private Label adr;
    @FXML
    private Label cap;
    @FXML
    private Label tel;
    @FXML
    private Label mail;
    @FXML
    private Label tmpsouv;
    @FXML
    private Label tmpsfer;
    @FXML
    private Label aviss;
    private Salle selectedSalle;

    /**
     * Initializes the controller class.
     */
    public void initSalleData(Salle s){
        selectedSalle = s;
        nms.setText(s.getNomSalle());
        adr.setText(s.getAdresse());
        cap.setText(Integer.toString(s.getCapacite()));
        tel.setText(s.getNumTel_salle());
        mail.setText(s.getEmail_Salle());
        tmpsouv.setText(s.getTemps_Ouverture());
        tmpsfer.setText(s.getTemps_Fermuture());
        aviss.setText(Float.toString(s.getAvis()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
    

