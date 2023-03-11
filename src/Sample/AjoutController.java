/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import gui.Gui.myListener;
import Models.Blog;
import Models.Evenement;
import Services.EventService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutController implements Initializable {

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
    private Stage primaryStage;
    
    private myListener myListener;
    
    private List<Evenement> fruits = new ArrayList<>();
    EventService gs = new EventService();
    public String rech = "";
    
    @FXML
    private TextField recherche;
    @FXML
    private Pane ChosenFruitCard;

    private List<Evenement> getData() {
        List<Evenement> fruits = new ArrayList<>();

        fruits = gs.AfficherEvents();

        return fruits;
    }
/*
    private void setChosenFruit(Evenement fruit) {

        tfnom_event.setText(fruit.getNom_event());
        tfdate_et_heure.setValue(fruit.getDate_et_heure().toLocalDate());
        tflieu.setText(fruit.getLieu());
        tftype_event.setText(fruit.getType_event());
        tfdescription.setText(fruit.getDescription());

    }

    /**
     * Initializes the controller class.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setdata();
        fruits.addAll(getData());
        if (fruits.size() > 0) {
            setChosenFruit(fruits.get(0));
            
            myListener = new myListener() 
            {

                @Override
                    public void onClickListener(Evenement u) {
                    setChosenFruit(u);
                }

                
            };
        }
    }

    /* fruits.addAll(getData());
        if (fruits.size() > 0) {
            setChosenFruit(fruits.get(0));
       
            myListener = new myListener() {
                @Override
                public void onClickListener(Fruit fruit) {
                    setChosenFruit(fruit);
                }

                @Override
                public void onClickListener(Evenement u) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
        }*/
    public void setdata() {
        fruits = gs.RechercherEvent(rech);

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i), myListener);

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

    /*
    private List<Evenement> getRecentlyPlayed() {
        List<Evenement> fruits = new ArrayList<>();

        fruits = gs.AfficherEvents();

        return fruits;
    }
     */
    @FXML
    private void insert_button(ActionEvent event) {

        String resProd1 = tfnom_event.getText();

        LocalDate date = tfdate_et_heure.getValue();

        java.sql.Date date_et_heure = java.sql.Date.valueOf(date);

        String resNom2 = tflieu.getText();
        String resNum1 = tftype_event.getText();
        String resNum2 = tfdescription.getText();

        EventService os = new EventService();

        Evenement t = new Evenement(resProd1, date_et_heure, resNom2, resNum1, resNum2);

        os.AjouterEvent(t);
        System.out.println("Done!");
        fruits = gs.AfficherEvents();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i), myListener);

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
    
    
     private void setChosenFruit(Evenement fruit) {

        tfnom_event.setText(fruit.getNom_event());
        tfdate_et_heure.setValue(fruit.getDate_et_heure().toLocalDate());
        tflieu.setText(fruit.getLieu());
        tftype_event.setText(fruit.getType_event());
        tfdescription.setText(fruit.getDescription());

    }
    
    

    @FXML
    public void onclicksearch() {
        if (recherche.getText().isEmpty()) {
            rech = "";
        } else {
            rech = recherche.getText();
        }
        setdata();
    }

    
}
