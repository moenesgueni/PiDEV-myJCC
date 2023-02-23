/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moene
 */

public class SupprimerFXMLController implements Initializable {

    @FXML
    private TextField IDfield;
    @FXML
    private Button Supprimer;
    private Stage primaryStage;
        public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;}
    /**
     * Initializes the controller class.
     */

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SupprimerU(ActionEvent event) {
        User f = new User();
        UserService fs = new UserService();
        Integer x = Integer.valueOf(IDfield.getText());
        //f=fs.afficherUserbyID(x);
        fs.supprimerUser(x);
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("User est supprimé avec succes");
        confirmation.show();
    }


    
}
