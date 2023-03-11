/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import API.MailerAPI1;
import Models.PlanningFilmSalle;
import Models.Reservation;
import Models.User;
import Services.PlanningService;
import Services.ReservationService;
import Services.UserService;
import Utilities.Type;
import Utilities.UserSession;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
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
         nomU.setText(UserSession.getNom());
        prenomU.setText(UserSession.getPrenom());
        cinU.setText(UserSession.getEmail());
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
    MailerAPI1.sendMail(UserSession.getEmail(),titre.getText()+"\n"+dated.getText()+"\n"+salle.getText()+"\n"+heure.getText());
    
  } else {
    return;
  }
  
         
         
         
    }

    
}
