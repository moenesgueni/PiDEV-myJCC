/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import gui.Gui.myListener;
import gui.Gui.myListener1;
import Models.Blog;
import Models.Evenement;
import Models.User;
import Services.BlogService;
import Services.EventService;
import Utilities.Type;
import Utilities.UserSession;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CommentaireController implements Initializable {

    @FXML
    private TextField recherche;
    @FXML
    private Pane ChosenFruitCard;
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
    private Button insert_button;
    @FXML
    private TextField tfauteur;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    private Stage primaryStage;

    private myListener1 myListener1;

    private List<Blog> Blogs = new ArrayList<>();
    BlogService bs = new BlogService();
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

    public void setdata() {
        Blogs = bs.RechercherBlog(rech);

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < Blogs.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("coment.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ComentController ComentController = fxmlLoader.getController();
                ComentController.setData(Blogs.get(i), myListener1);

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

    @FXML
    private void onclicksearch(ActionEvent event) {
    }

    @FXML
    private void insert_button(ActionEvent event) {
        String resProd1 = tftitre.getText();
        String resNum3 = tfauteur.getText();

        LocalDate date = tfdate.getValue();

        java.sql.Date date_et_heure = java.sql.Date.valueOf(date);

        String resNom2 = tfcontenu.getText();
        String resNum1 = tfetiquette.getText();
        String resNum2 = tfimage.getText();
        
        BlogService os = new BlogService();

        
        User sy = new User(UserSession.getID_User());
        
        
        Blog t = new Blog(resProd1,sy,date_et_heure, resNom2, resNum1, resNum2);

        os.AjouterBlog(t);
        System.out.println("Done!");
        Blogs = bs.AfficherBlogs();
    }

}
