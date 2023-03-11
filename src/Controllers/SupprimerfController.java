/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Film;
import Services.FilmService;
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
public class SupprimerfController implements Initializable {

    @FXML
    private TextField nomftodel;
    @FXML
    private Button bsupprimerf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SupprimerF(ActionEvent event) {
        Film f = new Film();
        FilmService fs = new FilmService();
        fs.supprimerFilm(nomftodel.getText());
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Film " + nomftodel.getText() + " est supprimé avec succes");
        confirmation.show();
    }
    
}
