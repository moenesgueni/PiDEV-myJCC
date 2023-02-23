/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Services.UserService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class GestionController implements Initializable {

    @FXML
    private ImageView Background;
    @FXML
    private Label Titre;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Supprimer;
    @FXML
    private Button Modifier;
    @FXML
    private Button FilterByRole;
    @FXML
    private TextField SearchBYNameField;
    @FXML
    private Button SearchByNameTitle;
    @FXML
    private ListView list;
    private Stage primaryStage;
    
            public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Initializes the controller class.
     */
             User f = new User();
        UserService fs = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     /*   List<User> x=fs.afficherUser();
        ArrayList<User> myArrayList = new ArrayList<>();
        myArrayList.addAll(x);
    */
     ObservableList items = FXCollections.observableArrayList();
    for(int i=0; i<fs.afficherUser().size();i++){
        items.add(fs.afficherUser().get(i));
    }
     list.setItems(items);
    }
    @FXML
    private void Ajouter(ActionEvent event) {
    }

    @FXML
    private void Supprimer(ActionEvent event) {
    }

    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void FilterByRole(ActionEvent event) {
    }

    @FXML
    private void SearchByNameTitle(ActionEvent event) {
    }
    
}
