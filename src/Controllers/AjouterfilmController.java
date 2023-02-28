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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
public class AjouterfilmController implements Initializable {
    
    

    
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
    private Button bajouterf;
    
  
   
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       

}

    @FXML
    private void AjouterF(ActionEvent event) {
        Film f = new Film();
        FilmInterface fs = new FilmService();
        
        
        List<Film> films = fs.afficherFilm();
    String titre = TitreTF.getText();
    for (Film film : films) {
        if (film.getTitre().equals(titre)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Le titre du film doit être unique");
            alert.show();
            return;
        }
    }
    if (TitreTF.getText().isEmpty() || DateRTF.getText().isEmpty() || GenreTF.getText().isEmpty()
    || ResumeTF.getText().isEmpty() || DureeTF.getText().isEmpty() || PrixTF.getText().isEmpty()
    || ProducteurTF.getText().isEmpty() || ActeurTF.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Veuillez remplir tous les champs obligatoires.");
    alert.show();
    return;
}
        
        f.setTitre(TitreTF.getText());
        f.setDateRealisation(DateRTF.getText());
        f.setGenre(GenreTF.getText());
        f.setResume(ResumeTF.getText());
        f.setDuree(DureeTF.getText());
        f.setPrix(Float.parseFloat(PrixTF.getText()));
        f.setID_producteur(ProducteurTF.getText());
        f.setActeur(ActeurTF.getText());
        fs.ajouterFilm(f);
        
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Film " + TitreTF.getText() + " est ajouté avec succes");
        confirmation.show();
        
        TitreTF.setText("");
        DateRTF.setText("");
        GenreTF.setText("");
        ResumeTF.setText("");
        DureeTF.setText("");
        PrixTF.setText("");
        ProducteurTF.setText("");
        ActeurTF.setText("");
        
    }


   
}