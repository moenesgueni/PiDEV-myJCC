/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Film;
import Models.PlanningFilmSalle;
import Models.Salle;
import Services.FilmService;
import Services.PlanningService;
import Services.SalleService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class updateController implements Initializable {

    @FXML
    private Button modifpla;
    @FXML
    private ComboBox<String> combfilm;
    @FXML
    private ComboBox<String> sallecomb;
    @FXML
    private TextField datedif;
    @FXML
    private TextField heuredif;
    @FXML
    private TextField IDtoup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FilmService fs = new FilmService();
        List<Film> films = fs.afficherFilm();
        
        ObservableList<String> items = FXCollections.observableArrayList();
    for (Film film : films) {
        String item = film.getTitre(); 
        items.add(item);
        
        combfilm.setItems(items);
        // TODO
    } ;
    SalleService ss = new SalleService();
        List<Salle> salles = ss.afficherSalle();
        
          ObservableList<String> itemsS = FXCollections.observableArrayList();
    for (Salle salle : salles) {
        String item = salle.getNomSalle();
        itemsS.add(item);
     
    sallecomb.setItems(itemsS);
    };
    }    

    @FXML
    private void ModifierPla(ActionEvent event) {
        String sf = combfilm.getValue();
       String ss = sallecomb.getValue();
       FilmService fs = new FilmService();
       SalleService sss = new SalleService();
       PlanningService ps = new PlanningService();
       Film f = new Film();
       Salle s = new Salle();
       
       f = fs.GetFilmByTitre(sf);
       s = sss.GetSalleByName(ss);
       String dateString = datedif.getText();
       java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
       PlanningFilmSalle p = new PlanningFilmSalle(Integer.parseInt(IDtoup.getText()),s,f,sqlDate,heuredif.getText());
       ps.updatePlanning(p);
       Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Planning modifié avec succes");
        confirmation.show();
    }

    @FXML
    private void selectFlm(ActionEvent event) {
        String s = combfilm.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void selectSalle(ActionEvent event) {
        String s = sallecomb.getSelectionModel().getSelectedItem().toString();
    }
    
}
