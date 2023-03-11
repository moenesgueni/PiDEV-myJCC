/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import gui.Gui.myListener1;
import Models.Blog;
import Services.BlogService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
public class UserBlogController implements Initializable {

    @FXML
    private TextField recherche;
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
        // TODO
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

                if (column == 1) {
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
    
}
