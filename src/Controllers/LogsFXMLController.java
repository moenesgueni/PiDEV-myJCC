/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.LOGS;
import Services.LogsService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class LogsFXMLController implements Initializable {

    @FXML
    private ListView<String> list;
    private Stage primaryStage;
    
   public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;}
   
               
                     LOGS f = new LOGS();
        LogsService fs = new LogsService();
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            List<LOGS> U = fs.afficherLogs();
    ObservableList<String> items = FXCollections.observableArrayList();
    for (LOGS salle : U) {
    String item = salle.getID_Logs() + " - " + salle.getID_User() + " - " + salle.getAction() + " - " + salle.getDate()  ;
    items.add(item);}    
    list.setItems(items);
    }   
    }    


