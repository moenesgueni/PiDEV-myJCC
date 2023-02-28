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
import javafx.fxml.Initializable;
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
        int randomCode = rand.nextInt(99999);
    
                 User f = new User();
        UserService fs = new UserService();
    @FXML
    private TextField MDP1;
    @FXML
    private TextField MDP2;
    @FXML
    private Button Save;
    @FXML
    private Label ChampsPassword;
    private Stage primaryStage;

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
        MailerAPI.sendMail(x,randomCode);
        CodeEnvoye.setText("Code envoyé avec succées");
        }
        else {
        MailNotFound.setText("Email pas trouvé dans la base des données");
        }
    }

    @FXML
    private void VerifierCode(ActionEvent event) {
        if(Integer.valueOf(Code.getText())==randomCode){
            ChampsCode.setText("le Code est correcte");
         }
        else{
            ChampsCode.setText("Code éronné");
        }
    }

    @FXML
    private void SaveNewPassword(ActionEvent event) {
                if(MDP1.getText().equals(MDP2.getText())){
            Connection cnx = MaConnexion.getInstance().getCnx();
             String req ="UPDATE `user` SET `MotDePasse`=? WHERE Email= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, MDP2.getText());
            ps.setString(2, Email.getText());
            ps.executeUpdate();
            ChampsPassword.setText("Mot de passe changé !!");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);}
        }else{
        ChampsPassword.setText("Les mots de passe ne sont pas les mêmes"); 
        }
    }
    
}
