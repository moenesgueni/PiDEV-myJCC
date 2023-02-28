/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Film;
import Models.Prix;
import Services.PrixService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class ModifPrixController implements Initializable {

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
    private Button modif;
    @FXML
    private TextField modifff;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goVote(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutVote.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();;
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @FXML
    private void goPrix(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutPrix.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @FXML
    private void modifPrix(ActionEvent event) {
        Film f = new Film(11, "The Shawshank Redemption 2", "1994", "action", "the best of all time", "2:03", (float) 50.00, "Frank Darabont", "Morgan Freeman");
        Prix p = new Prix(20, f, modifff.getText());

        PrixService ps = new PrixService();
        ps.modifierPrix(p);
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Prix est modifiée avec succes");
        confirmation.show();
    }

    @FXML
    private void retourMain(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutPrix.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);

        }
    }
    

}
