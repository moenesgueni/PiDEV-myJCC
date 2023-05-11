/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import API.MailerAPI;
import Models.User;
import Services.UserService;
import Utilities.FileUpload;
import Utilities.TestUser;
import Utilities.Type;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class AjoutSpectateurFXMLController implements Initializable {

    @FXML
    private TextField NomU;
    @FXML
    private TextField PrenomU;
    private RadioButton Sexe1;
    @FXML
    private TextField EmailU;
    @FXML
    private PasswordField PasswordU;
    private TextField Photo;
    @FXML
    private ImageView Background;
    private Stage primaryStage;
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
    @FXML
    private Button R;
    @FXML
    private Button choisirImage;
    @FXML
    private ImageView imagePreview;
    File selectedFile;

    /**
     * Initializes the controller class.
     */
        public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        choisirImage.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Ouvrir votre image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            selectedFile = fileChooser.showOpenDialog(new Stage());
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imagePreview.setImage(image);
            }

        });
        
    }    

         User f = new User();
        UserService fs = new UserService();




    @FXML
    private void AjouterU(ActionEvent event) {
                try {
            long millis = System.currentTimeMillis();
            String fileExtention = selectedFile.toString().substring(selectedFile.toString().lastIndexOf("."));
            String newName = millis + fileExtention;
            FileUpload.uploadFile(selectedFile.toString(), "profile\\" + newName);
        f.setNom(NomU.getText());
        f.setPrenom(PrenomU.getText());
        getsexe(event);
        f.setEmail(EmailU.getText());
        f.setMotDePasse(PasswordU.getText());
        f.setRole(Type.SPECTATEUR);
        f.setPhotoB64("http://localhost/myjcc/profile/" + newName);

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

             
                if(TestUser.verifierNomPrenom(f.getNom())&&TestUser.verifierNomPrenom(f.getPrenom())&&TestUser.verifierAdresseEmail(f.getEmail())&&TestUser.verifierMotDePasse(PasswordU.getText())){
                    MailerAPI.sendMail(f.getEmail(), f.getEmail(),PasswordU.getText());
        fs.ajouterUser2(f);
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("User " + NomU.getText() + " est ajouté avec succes");
        confirmation.show();
                }
                        } catch (Exception ex) {
            Logger.getLogger(GererPhotographiesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        NomU.setText("");
        PrenomU.setText("");
       // Sexe1.setText("");
        EmailU.setText("");
        PasswordU.setText("");
        comb.setValue("");
        Photo.setText("");
        
    
    }

    private void Select(ActionEvent event) {

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

    @FXML
    private void Retour(ActionEvent event) throws IOException {
                                    Parent root = FXMLLoader.load(getClass().getResource("../gui/LoginFXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

}
