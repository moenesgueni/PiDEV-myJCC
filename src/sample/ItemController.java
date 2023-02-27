/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import GUI.myListener;
import Models.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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

    private Evenement u;

    private myListener myListener;

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

    /**
     * Initializes the controller class.
     */
}
