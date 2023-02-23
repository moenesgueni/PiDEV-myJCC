/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.PlanningFilmSalle;
import Services.PlanningService;
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
public class SuppplanningController implements Initializable {

    @FXML
    private TextField pltodel;
    @FXML
    private Button suppplanningb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SupPlanning(ActionEvent event) {
        PlanningFilmSalle p = new PlanningFilmSalle();
        PlanningService ps = new PlanningService();
        
        ps.supprimerPlanning(Integer.parseInt(pltodel.getText()));
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Planning supprimé avec succes");
        confirmation.show();
        
    }
    
}
