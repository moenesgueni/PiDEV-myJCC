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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import gui.myListener;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author moene
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label lieuLabel;
    private Stage primaryStage;
    private myListener myListener;
    private User u;
    @FXML
    private ImageView imgprofile;
    @FXML
    private Button P;


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
    
        public void setData(User u, myListener myListener) {
        this.u = u;
        this.myListener = myListener;
        nameLabel.setText(u.getNom());

        typeLabel.setText(u.getPrenom());
        lieuLabel.setText(u.getRole().toString());
         Image newImage = new Image(u.getPhotoB64());
        imgprofile.setImage(newImage);
        UserService S = new UserService();


    }

    @FXML
    private void Plus(ActionEvent event) throws IOException {
                UserSession1 K =new UserSession1(u.getID_User(),u.getNom(),u.getPrenom(),u.getGenre(),u.getEmail(),u.getMotDePasse(),u.getRole(), u.getPhotoB64(), 0);
       // UserSession.getInstance(u.getNom(),u.getPrenom(),u.getGenre(),u.getEmail(),u.getMotDePasse(),u.getRole(), u.getPhotoB64(), 0);
     System.out.println(UserSession1.getEmail());
            Parent root = FXMLLoader.load(getClass().getResource("../gui/PlusFXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
