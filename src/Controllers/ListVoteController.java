/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Vote;
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
import javafx.scene.control.ButtonType;
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
public class ListVoteController implements Initializable {

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
    private ListView<String> listV;
    @FXML
    private Button reload;
    @FXML
    private Button retour;
    @FXML
    private Button delete;
    @FXML
    private Button close;
    
    Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
VoteService vs = new VoteService();
        List<Vote> votes = vs.afficherVotes();
        // Créer une liste d'éléments de ListView qui contiennent les propriétés des instances de la classe Hotel
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Vote vote : votes) {

            String item = vote.getUser() + " - \n " + vote.getFilm() + " - \n Date de vote : " + vote.getDate_Vote();
            items.add(item);
        }
        listV.setItems(items);
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
    private void reloadAff(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListeVote.fxml"));
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
    private void RetourMainBar(ActionEvent event) {
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
    private void deleteButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/SuppVote.fxml"));
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
    private void closeWindow(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fermer");
        alert.setHeaderText("Vous etes sur le point de fermer");
        alert.setContentText("Voulez-vous sauvegarder avant de fermer!");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) workPlace.getScene().getWindow();
            System.out.println("successfully closed");
            stage.close();
        }
    }
    
}
