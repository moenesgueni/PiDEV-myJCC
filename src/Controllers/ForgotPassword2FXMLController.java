/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.UserService;
import Utilities.MaConnexion;
import Utilities.Type;
import Utilities.UserSession;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class ForgotPassword2FXMLController implements Initializable {

    @FXML
    private Label ChampsPassword;
    @FXML
    private Button SaveNewPassword;
    @FXML
    private PasswordField MDP1;
    @FXML
    private PasswordField MDP2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SaveNewPassword(ActionEvent event) {
                        if(MDP1.getText().equals(MDP2.getText())){
            Connection cnx = MaConnexion.getInstance().getCnx();
             String req ="UPDATE `user` SET `MotDePasse`=? WHERE Email= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, MDP2.getText());
            ps.setString(2, UserSession.getEmail());
            ps.executeUpdate();
            ChampsPassword.setText("Mot de passe changé !!");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);}
        }else{
        ChampsPassword.setText("Les mots de passe ne sont pas les mêmes"); 
        }
    
    }
    
}
