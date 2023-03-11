/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import API.MailerAPI;
import Models.User;
import Services.UserService;
import Utilities.MaConnexion;
import Utilities.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class ForgotPassword1FXMLController implements Initializable {

    @FXML
    private TextField Email;
    @FXML
    private TextField Code;
    @FXML
    private Button sendCode;
    @FXML
    private Button VerifierCode;
    @FXML
    private Label ChampsCode;
    @FXML
    private Label CodeEnvoye;
    @FXML
    private Label MailNotFound;
    
            Random rand = new Random();
        int randomCode = rand.nextInt(90000)+10000;
    
                 User f = new User();
        UserService fs = new UserService();
    private Stage primaryStage;
    @FXML
    private Button R;

            public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;}
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void send(ActionEvent event) {
        String x = Email.getText();
        if(fs.SearchByMail(x)!=null){
            UserSession.setEmail(x);
        MailerAPI.sendMail(x,randomCode);
        CodeEnvoye.setText("Code envoyé avec succées");
        }
        else {
        MailNotFound.setText("Email pas trouvé dans la base des données");
        }
    }

    @FXML
    private void VerifierCode(ActionEvent event) throws IOException {
        if(Integer.valueOf(Code.getText())==randomCode){
            Parent root = FXMLLoader.load(getClass().getResource("../gui/ForgotPassword2FXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
         }
        else{
            ChampsCode.setText("Code éronné");
        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/LoginFXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }



    
}
