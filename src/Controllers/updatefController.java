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
import javafx.collections.ObservableList;
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
public class updatefController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierF(ActionEvent event) {
         Film f = new Film();
       //  ObservableList<String> otherList = AffichageController.getList();
        FilmInterface fs = new FilmService();
        f.setTitre(TitreTF.getText());
        f.setDateRealisation(DateRTF.getText());
        f.setGenre(GenreTF.getText());
        f.setResume(ResumeTF.getText());
        f.setDuree(DureeTF.getText());
        f.setPrix(Float.parseFloat(PrixTF.getText()));
        f.setID_producteur(ProducteurTF.getText());
        f.setActeur(ActeurTF.getText());
        fs.updateFilm(f);
    }
    
}
