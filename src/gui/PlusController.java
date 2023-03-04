/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.User;
import Services.UserService;
import Utilities.Type;
import Utilities.UserSession;
import Utilities.UserSession1;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class PlusController implements Initializable {

    @FXML
    private ImageView imgprofile;
    private Label nameLabel;
    private Label typeLabel;
    private Label lieuLabel;
    private myListener myListener;
    private User u;
    @FXML
    private Button R;
    @FXML
    private Label Prenom;
    @FXML
    private Label Nom;
    @FXML
    private Label Role;
    @FXML
    private Label Email;
    @FXML
    private Label Password;
    @FXML
    private Label Genre;
    @FXML
    private Label Photo;
    @FXML
    private Button mod;
    @FXML
    private Button sup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                Nom.setText(UserSession1.getNom());
        System.out.println("le nom est "+UserSession1.getNom());
        Prenom.setText(UserSession1.getPrenom());
        Role.setText(UserSession1.getRole().toString());
         Image newImage = new Image(UserSession1.getPhotoBD46());
        imgprofile.setImage(newImage);
        Email.setText(UserSession1.getEmail());
        Password.setText(UserSession1.getMotDePasse());
        Genre.setText(UserSession1.getGenre());
        
    }    
        public void setData(User u, myListener myListener) {
        this.u = u;
        this.myListener = myListener;


    }
    @FXML
    private void Retour(ActionEvent event) throws IOException {
        UserSession1.EndSession();
            Parent root = FXMLLoader.load(getClass().getResource("../gui/SideBarFXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void Mdifier(ActionEvent event) throws IOException {
        if(UserSession.getRole().equals(Type.ADMINSTRATEUR)){
            Parent root = FXMLLoader.load(getClass().getResource("../gui/ModifierFXML_1.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();    }
    }

    @FXML
    private void Supprimer(ActionEvent event) throws IOException {
        if(UserSession.getID_User()!=UserSession1.getID_User()){
            UserService us= new UserService();
            us.supprimerUser(UserSession1.getID_User());
            Parent root = FXMLLoader.load(getClass().getResource("../gui/SideBarFXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
