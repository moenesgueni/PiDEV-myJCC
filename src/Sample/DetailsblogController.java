/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import Models.Blog;
import Services.BlogService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailsblogController implements Initializable {

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
    private Button update_button;
    @FXML
    private TextField tfauteur;
    @FXML
    private Button delete_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tftitre.setText(ComentController.ev.getTitre());
       // tfauteur.setText(ComentController.ev.getAuteur().getID_User());
        tfdate.setValue(ComentController.ev.getDate_publication().toLocalDate());
        tfetiquette.setText(ComentController.ev.getEtiquette());
        tfcontenu.setText(ComentController.ev.getContenu());
        tfimage.setText(ComentController.ev.getImage());
        
    }    

    @FXML
    private void update_button(ActionEvent event) {
        
        
        
            Blog evenement = new Blog();
                
            evenement.setId_blog(ComentController.ev.getId_blog());
            evenement.setTitre(tftitre.getText());
            evenement.setDate_publication(java.sql.Date.valueOf(tfdate.getValue()));
            evenement.setEtiquette(tfetiquette.getText());
            evenement.setContenu(tfcontenu.getText());
            evenement.setImage(tfimage.getText());
            
           //  evenement.set(ComentController.ev.getAuteur().getID_User());
            
           
            // Update the event in the database
            BlogService es = new BlogService();
            es.ModifierBlog(evenement);
        
        
    }

    @FXML
    private void delete_button(ActionEvent event) {
        
       
        Blog evenement = new Blog();
         evenement.setId_blog(ComentController.ev.getId_blog());
         BlogService es = new BlogService();
         es.SupprimerBlog(evenement);
       
    }
    
}
