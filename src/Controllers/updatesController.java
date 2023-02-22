/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierS(ActionEvent event) {
    }
    
}
