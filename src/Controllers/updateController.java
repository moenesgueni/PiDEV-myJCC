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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
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
    private DatePicker datedif;
    @FXML
    private TextField heuredif;
    private PlanningFilmSalle selectedplan;
    

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
    
    public void initData(PlanningFilmSalle p) {
        selectedplan = p;
        combfilm.setValue(p.getFilm().getTitre());
        sallecomb.setValue(p.getSalle().getNomSalle());
         LocalDate date = p.getDatediffusion().toLocalDate();
         datedif.setValue(date);
        heuredif.setText(p.getHeurediffusion());
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
       LocalDate date = datedif.getValue();

        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
       
      
       PlanningFilmSalle p = new PlanningFilmSalle(selectedplan.getID_planning(),s,f,sqlDate,heuredif.getText());
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
