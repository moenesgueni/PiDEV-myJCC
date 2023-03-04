/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.User;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import gui.myListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author moene
 */
public class AjoutController implements Initializable {

    @FXML
    private TextField recherche;
    @FXML
    private Button insert_button;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private Stage primaryStage;
        User song;
    private myListener myListener;
    private List<User> fruits = new ArrayList<>();
    UserService gs = new UserService();
    public String rech = "";

    /**
     * Initializes the controller class.
     */
    
            public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setdata();
    }    

    @FXML
    private void onclicksearch(ActionEvent event) {
    }

    @FXML
    private void insert_button(ActionEvent event) throws IOException {
                    Parent root = FXMLLoader.load(getClass().getResource("../gui/AjouterFXML.fxml"));            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

 public void setdata() {
               fruits =gs.afficherUser();

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                gui.ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
}
