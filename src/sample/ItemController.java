/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import Models.Film;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label lieuLabel;
    
    private Film u;

    private myListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
    
    public void setData( Film u, myListener myListener) {
        this.u = u;
        this.myListener = myListener;
        nameLabel.setText(u.getTitre());
        lieuLabel.setText(u.getGenre());
        typeLabel.setText(u.getActeur());         //((u.getPrix());
    }
    
}
