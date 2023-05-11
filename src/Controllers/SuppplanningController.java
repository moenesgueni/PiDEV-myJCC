/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.PlanningFilmSalle;
import Services.PlanningService;
import Utilities.NotificationUtil;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class SuppplanningController implements Initializable {

    
    @FXML
    private Button suppplanningb;
    @FXML
    private TextField TitreF;
    @FXML
    private TextField NomSa;
    
    private PlanningFilmSalle selectedplan;
    @FXML
    private TextField ddiff;
    @FXML
    private TextField hdiff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(PlanningFilmSalle p) {
        selectedplan = p;
        TitreF.setText(p.getFilm().getTitre());
        NomSa.setText(p.getSalle().getNomSalle());
         LocalDate date = p.getDatediffusion().toLocalDate();
        ddiff.setText(String.valueOf(date));
        hdiff.setText(p.getHeurediffusion());
    }

    @FXML
   private void SupPlanning(ActionEvent event) {
    PlanningFilmSalle p = new PlanningFilmSalle();
    PlanningService ps = new PlanningService();
    
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation de suppression");
    confirmation.setHeaderText("Voulez-vous vraiment supprimer le planning sélectionné ?");
    confirmation.setContentText("Cette action est irréversible.");
    Optional<ButtonType> result = confirmation.showAndWait();
    
    if (result.get() == ButtonType.OK) {
        ps.supprimerPlanning(selectedplan.getID_planning());
        NotificationUtil.showNotification("Un planning a été supprimé", "Consulter l'application MyJcc pour plus de détails.");
        
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setContentText("Planning supprimé avec succès");
        success.show();
    } else {
        // Do nothing, user clicked on cancel or closed the dialog
    }
}
    
}
