/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUI_Vote.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class ErreurController implements Initializable {
    public static String xx2;
    @FXML
    private Button Retour;
    @FXML
    private Label err;
    Stage stage;
    /**
     * Initializes the controller class.
     */
    public void display1(String titreFilm) throws IOException {

       
            System.out.println(titreFilm);
            err.setText(titreFilm);
            
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(xx2);
        err.setText(xx2);
    }    

    @FXML
    private void switchToScene3(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterVote.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
