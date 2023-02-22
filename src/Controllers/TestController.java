/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Evenement;
import Services.EventService;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TestController implements Initializable {
@FXML
    private TableColumn<Evenement, String> colonne_id;
    @FXML
    private TableColumn<Evenement, String> colonne_nom;
    @FXML
    private TableColumn<Evenement, Date> colonne_date;
    @FXML
    private TableColumn<Evenement, String> colonne_lieu;
    @FXML
    private TableColumn<Evenement, String> colonne_type;
    @FXML
    private TableColumn<Evenement, String> colonne_description;
    
    @FXML
    private TableView<Evenement> tvEvent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         EventService os = new EventService();
        List<Evenement> listEvenement;
        listEvenement = os.AfficherEvents();
        ObservableList<Evenement> liste = FXCollections.observableList(listEvenement);
        
        colonne_id.setCellValueFactory(new PropertyValueFactory<Evenement, String>("id"));
        colonne_nom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_event"));
        colonne_date.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_et_heure"));
        colonne_lieu.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieu"));
        colonne_type.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_event"));
        colonne_description.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));

        tvEvent.setItems(liste);
    }    
    
}
