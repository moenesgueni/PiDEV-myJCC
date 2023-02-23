/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Prix;
import Models.Vote;
import Services.PrixService;
import Services.VoteService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class AffichagePrixController implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private ImageView fullScreen;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView userPhoto;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane3;
    @FXML
    private ImageView userPhoto2;
    @FXML
    private ImageView menu2;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Button Vote;
    @FXML
    private Button goPrix;
    @FXML
    private Label nomPrenom;
    @FXML
    private Label role;
    @FXML
    private ImageView settings;
    @FXML
    private AnchorPane workPlace;
    @FXML
    private ListView<String> listP;
    @FXML
    private Button reload;
    @FXML
    private Button retour;
    @FXML
    private Button suppID;
    @FXML
    private Button modif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
PrixService ps = new PrixService();
        List<Prix> prixx = ps.getAllPrix();
        // Créer une liste d'éléments de ListView qui contiennent les propriétés des instances de la classe Hotel
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Prix prix : prixx) {

            String item = prix.getFilm() + " - \n Prix : " + prix.getTypePrix();
            items.add(item);
        }
        listP.setItems(items);
    }    

    @FXML
    private void goVote(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutVote.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MyFit - Mon Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @FXML
    private void goPrix(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutPrix.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MyFit - Mon Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @FXML
    private void Reload(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichagePrix.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MyFit - Mon Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @FXML
    private void retourPrix(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/SideBarFXML.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MyFit - Mon Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @FXML
    private void suppPrix(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/SuppPrix.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MyFit - Mon Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    
}

    @FXML
    private void modifierPrixx(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ModifPrix.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MyFit - Mon Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }
}
    

