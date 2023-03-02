/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Controllers.ForgotPassword1FXMLController;
import Models.User;
import Services.UserService;
import Utilities.PasswordHasher;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import Utilities.Type;
import Utilities.UserSession;
import admin.NewFXMain;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField Email;
    @FXML
    private TextField Password;
    @FXML
    private Button SeConnecter;
    private BorderPane workPlace;
    private Stage primaryStage;
    @FXML
    private Button FP;
                 User f = new User();
        UserService fs = new UserService();
    @FXML
    private Label PassInvalide;
    @FXML
    private Button CC;
    /**
     * Initializes the controller class.
     */
            public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Connexion(ActionEvent event) throws IOException {
        f=fs.SearchByMail(Email.getText());
        if(f==null){
        PassInvalide.setText("Email n'existe pas"); 
        }
        else if(f.getEmail()!=null){
        if(PasswordHasher.checkPassword(Password.getText(),f.getMotDePasse())){
            
            switch(f.getRole()) {
            case ADMINSTRATEUR:
            System.out.println("ADMIN ACTIF");
            UserSession.getInstance(f.getNom(),f.getPrenom(),f.getGenre(),f.getEmail(),f.getMotDePasse(),f.getRole(),f.getPhotoB64(),f.getNumTel());
            Parent root = FXMLLoader.load(getClass().getResource("../gui/SideBarFXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

             break;
             case SPECTATEUR:
    // code block
              break;
  
              case ACTEUR:
    // code block
              break;
              case PRODUCTEUR:
    // code block
              break;
              case EXPERT:
    // code block
              break;
              case INVITE:
    // code block
              break;
              case PHOTOGRAPHE:
    // code block
              break;
              case SPONSOR:
    // code block
              break;
            }
                   UserSession.getInstance(f.getNom(),f.getPrenom(),f.getGenre(),f.getEmail(),f.getMotDePasse(),f.getRole(),f.getPhotoB64(),f.getNumTel());
        }
        }else{
        PassInvalide.setText("Mot de passe éronné");   
        }
    }     
 



    @FXML
    private void ForgotPassword(ActionEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("../gui/ForgotPassword1FXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void CreerCompte(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/AjoutSpectateurFXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

        
    
}
