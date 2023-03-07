/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Film;
import Models.PlanningFilmSalle;
import Models.Reservation;
import Models.User;
import Services.PlanningService;
import Services.ReservationService;
import Services.UserService;
import Utils.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class ReservationController implements Initializable {

    @FXML
    private Label nomU;
    @FXML
    private Label prenomU;
    @FXML
    private Label cinU;
    @FXML
    private Label titre;
    @FXML
    private Label salle;
    @FXML
    private Label dated;
    @FXML
    private Label heure;
    @FXML
    private Label prix;
    @FXML
    private Button Confirmer;
    private PlanningFilmSalle selectedplan;
    User u = new User(1,"DHIA","ghammam","homme","dhiaghammam@esprit.tn","admin",Type.ADMINSTRATEUR,"abd")   ;
  

    /**
     * Initializes the controller class.
     */
    
    public void initData(PlanningFilmSalle f) {
        
        selectedplan = f;
       
        titre.setText(f.getFilm().getTitre());
        LocalDate date = f.getDatediffusion().toLocalDate();
        dated.setText(String.valueOf(date));
        salle.setText(f.getSalle().getNomSalle());
        heure.setText(f.getHeurediffusion());
        
        prix.setText(Float.toString(f.getFilm().getPrix()) + " DT ");
        
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         nomU.setText(u.getNom());
        prenomU.setText(u.getPrenom());
        cinU.setText(u.getEmail());
    }    

    @FXML
    private void ConfRES(ActionEvent event) {
        
        UserService us = new UserService();
        PlanningService ps = new PlanningService();
        ReservationService rs = new ReservationService();
         //String mail = cinU.getText();
        // User u = us.SearchByMail(mail);
         PlanningFilmSalle plan = selectedplan;
         
         Reservation res = new Reservation(plan,u);
         
         Alert alert = new Alert(AlertType.ERROR);
  alert.setTitle("Verification");
  alert.setHeaderText("Verification");
  alert.setContentText("Veuillez Remplir le checkbox.");
  
  // Create a checkbox to include in the Alert window
  CheckBox checkBox = new CheckBox("Je suis un humain");
  
  // Add the checkbox to the DialogPane
  DialogPane dialogPane = alert.getDialogPane();
  dialogPane.setExpandableContent(checkBox);
  
  // Show the Alert window and wait for user response
  Optional<ButtonType> result = alert.showAndWait();
 
  
  // Check if the user clicked on the OK button and the checkbox is selected
  if (result.isPresent() && result.get() == ButtonType.OK && checkBox.isSelected()) {
      Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Reservation Confirmée");
        confirmation.show();
    rs.ajouterReservation(res);
  } else {
    return;
  }
  
         
         
         
    }
    /*
    public List<Film> getTopFilms() {
    ReservationService rs = new ReservationService();
    List<Reservation> reservations = rs.afficherReservation();

    Map<Film, Integer> nbReservations = new HashMap<>();

    // Sum up the number of reservations for each film
    for (Reservation res : reservations) {
        Film film = res.getPlanning().getFilm();
        int count = nbReservations.getOrDefault(film, 0);
        nbReservations.put(film, count + 1);
    }

    // Sort the films based on the total number of reservations
    List<Film> topFilms = nbReservations.entrySet().stream()
            .sorted(Map.Entry.<Film, Integer>comparingByValue().reversed())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

    return topFilms;
}


// A custom class that encapsulates a film and the number of reservations for that film
class FilmWithReservations implements Comparable<FilmWithReservations> {
    private final Film film;
    private final int numReservations;

    public FilmWithReservations(Film film, int numReservations) {
        this.film = film;
        this.numReservations = numReservations;
    }

    public Film getFilm() {
        return film;
    }

    public int getNumReservations() {
        return numReservations;
    }

    @Override
    public int compareTo(FilmWithReservations other) {
        return Integer.compare(numReservations, other.numReservations);
    }
}
*/






    
    
    
}
