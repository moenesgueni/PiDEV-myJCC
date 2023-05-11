/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.LOGS;
import Models.User;
import Services.LogsService;
import Services.UserService;
import Utilities.FileUpload;
import Utilities.PasswordHasher;
import Utilities.Type;
import Utilities.UserSession;
import Utilities.UserSession1;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
public class ModifierFXMLController1 implements Initializable {

    @FXML
    private Button Selection;
    @FXML
    private TextField NomU;
    @FXML
    private TextField PrenomU;
    @FXML
    private TextField EmailU;
    private TextField PasswordU;
    @FXML
    private ComboBox comb;
    private TextField Photo;
    @FXML
    private RadioButton homme;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private RadioButton femme;
    @FXML
    private Button Modif;
    @FXML
    private Button R;
    File selectedFile;

    /**
     * Initializes the controller class.
     */
                    User f = new User();
                LOGS L=new LOGS();
                LogsService Ls = new LogsService();
        UserService fs = new UserService();
    private Stage primaryStage;
    @FXML
    private Button choisirImage;
    @FXML
    private ImageView imagePreview;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                String[] U = Stream.of(Type.values()).map(Type::name).toArray(String[]::new);
        ObservableList<String> list = FXCollections.observableArrayList(U[0],U[1],U[2],U[3],U[4],U[5],U[6],U[7]);
        comb.setItems(list);
        
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

    @FXML
    private void Selectionner(ActionEvent event) {
          String x =UserSession1.getEmail();
                //String s="";
               // s=(String) f.getRole().name();
                //f.setRole(Type.valueOf(s));

       // Integer x = Integer.valueOf(IDfield.getText());
        f=fs.SearchByMail(x);
                        comb.setValue(f.getRole());
              //  comb.setItems(f.getRole());
        NomU.setText(f.getNom());
        PrenomU.setText(f.getPrenom());
        if(f.getGenre().equals("Homme")){
            homme.setSelected(true);
        }
        if(f.getGenre().equals("Femme")){
            femme.setSelected(true);
        }
        EmailU.setText(f.getEmail());
        f.getRole();
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
        String u =UserSession1.getEmail();
        f=fs.SearchByMail(u);
        int x=f.getID_User();
//        Integer x = Integer.valueOf(IDfield.getText());

        try {
            long millis = System.currentTimeMillis();
            String fileExtention = selectedFile.toString().substring(selectedFile.toString().lastIndexOf("."));
            String newName = millis + fileExtention;
            FileUpload.uploadFile(selectedFile.toString(), "profile\\" + newName);
        s=(String) comb.getValue();
        f.setNom(NomU.getText());
        f.setPrenom(PrenomU.getText());
        getsexe(event);
        f.setEmail(EmailU.getText());
        f.setMotDePasse(f.getMotDePasse());
        f.setRole(Type.valueOf(s));
                f.setPhotoB64("http://localhost/myjcc/profile/" + newName);

        fs.modifierUser(x,f);
         Date currentDate = new Date();
                         java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
                         L= new LOGS(UserSession.getID_User(),sqlDate,"l'"+UserSession.getRole().toString()+" "+UserSession.getPrenom()+ " a ajouté l'utilisateur "+f.getPrenom());
                            Ls.AjouterLogs(L);
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("User " + NomU.getText() + " est modifié avec succes");
        confirmation.show();} catch (Exception ex) {
            Logger.getLogger(GererPhotographiesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        NomU.setText("");
        PrenomU.setText("");
       // Sexe1.setText("");
        EmailU.setText("");
        comb.setValue("");
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
                            Parent root = FXMLLoader.load(getClass().getResource("../gui/SideBarFXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}
