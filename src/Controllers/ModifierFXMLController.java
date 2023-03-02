/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Services.UserService;
import Utilities.PasswordHasher;
import Utilities.Type;
import Utilities.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class ModifierFXMLController implements Initializable {

    private TextField IDfield;
    @FXML
    private Button Selection;
    @FXML
    private TextField NomU;
    @FXML
    private TextField PrenomU;
    @FXML
    private TextField EmailU;
    @FXML
    private TextField PasswordU;
    @FXML
    private ComboBox comb;
    @FXML
    private TextField Photo;
    @FXML
    private RadioButton homme;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private RadioButton femme;
    @FXML
    private Button Modif;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] U = Stream.of(Type.values()).map(Type::name).toArray(String[]::new);
        ObservableList<String> list = FXCollections.observableArrayList(U[0],U[1],U[2],U[3],U[4],U[5],U[6],U[7]);
        comb.setItems(list);
    }   
                User f = new User();
        UserService fs = new UserService();
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML
    private void Selectionner(ActionEvent event) {
        String x =UserSession.getEmail();
                //String s="";
               // s=(String) f.getRole().name();
                //f.setRole(Type.valueOf(s));
                comb.setValue(f.getRole());
       // Integer x = Integer.valueOf(IDfield.getText());
        f=fs.SearchByMail(x);
        NomU.setText(f.getNom());
        PrenomU.setText(f.getPrenom());
        if(f.getGenre().equals("Homme")){
            homme.isSelected();
        }
        if(f.getGenre().equals("Femme")){
            femme.isSelected();
        }
        EmailU.setText(f.getEmail());
        PasswordU.setText(f.getMotDePasse());
        f.getRole();
        Photo.setText(f.getPhotoB64());
    }

    @FXML
    private void select(ActionEvent event) {
           String s = comb.getSelectionModel().getSelectedItem().toString();
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
    private void ModifierU(ActionEvent event) {
        String s="";
        String u =UserSession.getEmail();
        f=fs.SearchByMail(u);
        int x=f.getID_User();
//        Integer x = Integer.valueOf(IDfield.getText());
        s=(String) comb.getValue();
        f.setNom(NomU.getText());
        f.setPrenom(PrenomU.getText());
        getsexe(event);
        f.setEmail(EmailU.getText());
        f.setMotDePasse(PasswordHasher.hashPassword(PasswordU.getText()));
        f.setRole(Type.valueOf(s));
        f.setPhotoB64(Photo.getText());
        fs.modifierUser(x,f);
        
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("User " + NomU.getText() + " est modifié avec succes");
        confirmation.show();
        
        NomU.setText("");
        PrenomU.setText("");
       // Sexe1.setText("");
        EmailU.setText("");
        PasswordU.setText("");
        comb.setValue("");
        Photo.setText("");  

    }
    
}
