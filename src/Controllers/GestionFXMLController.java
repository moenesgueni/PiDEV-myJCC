/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Services.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class GestionFXMLController implements Initializable {

    private Stage primaryStage;
    @FXML
    private ListView<String> list;
    @FXML
    private Button FilterByRole;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private TextField ChercherField;

    /**
     * Initializes the controller class.
     */
            public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
            
        User f = new User();
        UserService fs = new UserService();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    List<User> U = fs.afficherUser();
    ObservableList<String> items = FXCollections.observableArrayList();
    for (User salle : U) {
    String item = salle.getNom() + " - " + salle.getPrenom() + " - " + salle.getEmail() + " - " + salle.getMotDePasse() + " - " + salle.getRole().toString()+ " - " + salle.getGenre();
    items.add(item);}    
    list.setItems(items);
    }    

    @FXML
    private void FilterByRole(ActionEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) {
    }

    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void Supprimer(ActionEvent event) {
    }
    
}
