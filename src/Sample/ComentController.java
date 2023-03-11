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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ComentController implements Initializable {

    @FXML
    private Label titrelablel;
    @FXML
    private Label etiquetteLabel;
    private Blog u;
    private myListener1 myListener1;
    @FXML
    private Button details;

    
     public static Blog  ev =new Blog();
    BlogService es = new BlogService();
       // private Blog tt;
    @FXML
    private Label date_pub;
    @FXML
    private Label contenu_label;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void setData(Blog u, myListener1 myListener1) {
        this.u = u;
        this.myListener1 = myListener1;
        titrelablel.setText(u.getTitre());
        etiquetteLabel.setText(u.getEtiquette());
      date_pub.setText(u.getDate_publication().toString());
      contenu_label.setText(u.getContenu());

    }


@FXML
        private void click(MouseEvent event) {
          myListener1.onClickListener(u);

    }

    @FXML
    private void details(ActionEvent event) {
        
         ev = es.getBlogById(u.getId_blog());
        FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../sample/detailscoment.fxml"));
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
    
}
