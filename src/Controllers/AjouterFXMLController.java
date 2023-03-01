/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Services.UserService;
import Utilities.TestUser;
import Utilities.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.E;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeMath.E;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class AjouterFXMLController implements Initializable {

    @FXML
    private TextField NomU;
    @FXML
    private TextField PrenomU;
    private RadioButton Sexe1;
    @FXML
    private TextField EmailU;
    @FXML
    private PasswordField PasswordU;
    @FXML
    private TextField Photo;
    @FXML
    private ImageView Background;
    private Stage primaryStage;
    @FXML
    private ComboBox comb;
    @FXML
    private Button ButtonAjouterU;
    @FXML
    private RadioButton homme;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private RadioButton femme;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelprenom;
    @FXML
    private Label labelmail;
    @FXML
    private Label labelpass;

    /**
     * Initializes the controller class.
     */
        public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        String[] U = Stream.of(Type.values()).map(Type::name).toArray(String[]::new);
        ObservableList<String> list = FXCollections.observableArrayList(U[0],U[1],U[2],U[3],U[4],U[5],U[6],U[7]);
        comb.setItems(list);
        
    }    

         User f = new User();
        UserService fs = new UserService();




    @FXML
    private void AjouterU(ActionEvent event) {
    String s="";
        s=(String) comb.getValue();
        f.setNom(NomU.getText());
        f.setPrenom(PrenomU.getText());
        getsexe(event);
        f.setEmail(EmailU.getText());
        f.setMotDePasse(PasswordU.getText());
        f.setRole(Type.valueOf(s));
        f.setPhotoB64(Photo.getText());
                if (!TestUser.verifierNomPrenom(f.getNom())) {
                    labelnom.setText("Le nom est invalide");
                }else{
                labelnom.setText("");

                }

                if (!TestUser.verifierNomPrenom(f.getPrenom())) {
                    labelprenom.setText("Le prénom est invalide");
                }else{
                    labelprenom.setText("");
                }

                if (!TestUser.verifierMotDePasse(PasswordU.getText())) {
                    labelpass.setText("Le mot de passe est invalide");
                }else{
                    labelpass.setText("");
  
                }

                if (!TestUser.verifierAdresseEmail(f.getEmail())) {
                    labelmail.setText("L'adresse e-mail est invalide");
                }else{
                    labelmail.setText("");
                }
        
        fs.ajouterUser2(f);
        
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("User " + NomU.getText() + " est ajouté avec succes");
        confirmation.show();
        
        NomU.setText("");
        PrenomU.setText("");
       // Sexe1.setText("");
        EmailU.setText("");
        PasswordU.setText("");
        comb.setValue("");
        Photo.setText("");
        
    
    }

    @FXML
    private void Select(ActionEvent event) {
        String s = comb.getSelectionModel().getSelectedItem().toString();
       // label.setText(s);
    }


    @FXML
    private void getsexe(ActionEvent event) {
           if(homme.isSelected()){
       f.setGenre("Homme");
   }
   else if(femme.isSelected()){
       f.setGenre("Femme");
   }
    }

    
}
