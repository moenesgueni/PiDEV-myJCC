/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Interfaces.FilmInterface;
import Models.Film;
import Services.FilmService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.collections.ObservableList;
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
public class UpdateFController implements Initializable {

    @FXML
    private TextField TitreTF;
    @FXML
    private TextField DateRTF;
    @FXML
    private TextField GenreTF;
    @FXML
    private TextField ResumeTF;
    @FXML
    private TextField DureeTF;
    @FXML
    private TextField PrixTF;
    @FXML
    private TextField ProducteurTF;
    @FXML
    private TextField ActeurTF;
    @FXML
    private Button bmodifierf;
    
    private Film film;
    @FXML
    private TextField IDF;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
    

    @FXML
    private void ModifierF(ActionEvent event) {
        if (IDF.getText().isEmpty() || TitreTF.getText().isEmpty() || DateRTF.getText().isEmpty() || GenreTF.getText().isEmpty()
    || ResumeTF.getText().isEmpty() || DureeTF.getText().isEmpty() || PrixTF.getText().isEmpty()
    || ProducteurTF.getText().isEmpty() || ActeurTF.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Veuillez remplir tous les champs obligatoires.");
    alert.show();
    return;
}
         Film f = new Film(Integer.parseInt(IDF.getText()),TitreTF.getText(),DateRTF.getText(),GenreTF.getText(),ResumeTF.getText(),DureeTF.getText(),Float.parseFloat(PrixTF.getText()),ProducteurTF.getText(),ActeurTF.getText());
      
        FilmInterface fs = new FilmService();
        
      fs.updateFilm(f);
      Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Film est modifié avec succes");
        confirmation.show();
    }
      
     
}
