/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailscomentController implements Initializable {

    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfimage;
    @FXML
    private TextField tfcontenu;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfetiquette;
    @FXML
    private TextField tfauteur;
    @FXML
    private Button coment_button;
    @FXML
    private TextField commentField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tftitre.setText(ComentController.ev.getTitre());
        // tfauteur.setText(ComentController.ev.getAuteur().getID_User());
        tfdate.setValue(ComentController.ev.getDate_publication().toLocalDate());
        tfetiquette.setText(ComentController.ev.getEtiquette());
        tfcontenu.setText(ComentController.ev.getContenu());
        tfimage.setText(ComentController.ev.getImage());
    }

    @FXML
    private void coment_button(ActionEvent event) {
     String commentText = commentField.getText();
                        if (commentText.toLowerCase().contains("fuck") || commentText.toLowerCase().contains("shit") || commentText.toLowerCase().contains("test") || commentText.toLowerCase().contains("omek")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setHeaderText(null);
                            alert.setContentText("Veuillez respecter les ethiques de notre community");
                            alert.showAndWait();
                        }
    }

}
/*
String commentText = commentField.getText();
                        if (commentText.toLowerCase().contains("fuck") || commentText.toLowerCase().contains("shit") || commentText.toLowerCase().contains("test") || commentText.toLowerCase().contains("omek")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setHeaderText(null);
                            alert.setContentText("Veuillez respecter les ethiques de notre community");
                            alert.showAndWait();
                        }
*/