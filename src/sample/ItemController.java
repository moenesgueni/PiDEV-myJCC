/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import GUI.myListener;
import Models.Evenement;
import Services.EventService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label lieuLabel;

   
    private myListener myListener;
    @FXML
    private Button details;
    
    
    public static Evenement  ev =new Evenement();
    EventService es = new EventService();
    private Evenement u;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Evenement u, myListener myListener) {
        this.u = u;
        this.myListener = myListener;
        nameLabel.setText(u.getNom_event());
        lieuLabel.setText(u.getLieu());
        typeLabel.setText(u.getType_event());

    }

 

    @FXML
    private void click(MouseEvent event) {
          myListener.onClickListener(u);
    }

    @FXML
    private void details(ActionEvent event) {
        
                          ev = es.getEventById(u.getId());
                           FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../sample/detailsevent.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
        
        
        
    }

   
    /**
     * Initializes the controller class.
     */
}
