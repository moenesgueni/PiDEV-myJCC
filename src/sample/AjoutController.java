/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import Models.Film;
import Services.FilmService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class AjoutController implements Initializable {

    private TextField recherche;
    @FXML
    private TextField tfnom_event;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tflieu;
    @FXML
    private DatePicker tfdate_et_heure;
    @FXML
    private TextField tftype_event;
    @FXML
    private Button insert_button;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private myListener myListener;
    private List<Film> films = new ArrayList<>();
    FilmService fs = new FilmService();
    public String rech = "";
    @FXML
    private TextField recherch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setdata();
    }

    public void setdata() {
       /* films = fs.RechercherFilm(rech);

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < films.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(films.get(i), myListener);

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
        }*/
    }
    
    @FXML
    private void insert_button(ActionEvent event) {
     //  String resProd1 = tfnom_event.getText();

        /*LocalDate date = tfdate_et_heure.getValue();*/

        /* String date_et_heure ;// = tfdate_et_heure.getTe;  value to String////////////////////////////////////////
        
        String resNom2 = tflieu.getText();
        String resNum1 = tftype_event.getText();
        String resNum2 = tfdescription.getText();

        FilmService os = new FilmService();

        Film t = new Film(resProd1, date_et_heure, resNom2, resNum1, resNum2);*/
///////////////********************************************************************wkeft houni ****************************************/
       /* os.AjouterEvent(t);
        System.out.println("Done!");
        films = fs.AfficherEvents();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < films.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(films.get(i), myListener);

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
        }*/
    }
    

    @FXML
    private void onclicksearch(ActionEvent event) {
       /* if (recherche.getText().isEmpty()) {
            String rech = "";
        } else {
            String rech = recherche.getText();
        }
        setdata();*/
    }

    

    private void setChosenFruit(Film fruit) {//eventnbaddelha b film

       /* tfnom_event.setText(fruit.getNom_event());
        tfdate_et_heure.setValue(fruit.getDate_et_heure().toLocalDate());
        tflieu.setText(fruit.getLieu());
        tftype_event.setText(fruit.getType_event());
        tfdescription.setText(fruit.getDescription());*/

    }

}
