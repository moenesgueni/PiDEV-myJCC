/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.Date ;
import Charts.AlreadyVotedController;
import Charts.RateController;
import GUI.ErreurController;
import Models.Film;
import Models.PlanningFilmSalle;
import Models.User;
import Models.Vote;
import Services.FilmService;
import Services.PlanningService;
import Services.VoteService;
import Utilities.FilmSession;
import Utilities.UserSession;
import gui.FiveStarsController;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class FilmdetController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private ImageView imzge;
    @FXML
    private Label genre;
    @FXML
    private Label date;
    @FXML
    private Label Duree;
    @FXML
    private Label prix;
    @FXML
    private Label producteur;
    @FXML
    private Label acteur;
    @FXML
    private ListView<String> listplannings;
    private PlanningFilmSalle selectedPlan;
    private Film selectedFilm;
    @FXML
    private TextArea resume;
    @FXML
    private Button V;
    @FXML
    private Button R;

    /**
     * Initializes the controller class.
     */
    public void initData(Film f) {
        selectedFilm = f;
       FilmSession k = new FilmSession(f.getID_film(),f.getTitre(),f.getDateRealisation(),f.getGenre(),f.getResume(),f.getDuree(),f.getPrix(),f.getID_producteur(),f.getActeur(),f.getImage());
        titre.setText(f.getTitre());
        date.setText(f.getDateRealisation());
        genre.setText(f.getGenre());
        resume.setText(f.getResume());
        Duree.setText(f.getDuree());
        prix.setText(Float.toString(f.getPrix()));
        producteur.setText(f.getID_producteur());
        acteur.setText(f.getActeur());
        Image image = new Image (f.getImage());
        imzge.setImage(image);
        PlanningFilmSalle p = new PlanningFilmSalle();
        PlanningService ps = new PlanningService();
        List<PlanningFilmSalle> plannings = ps.GetPlanningByFilm(f.getID_film());
       
        System.out.println(plannings);
          ObservableList<String> items = FXCollections.observableArrayList();
    for (PlanningFilmSalle planning : plannings ) {
        String item = planning.getSalle().getNomSalle()+ " - " + planning.getDatediffusion()+ " - " + planning.getHeurediffusion();
        
        items.add(item);
    }    
    listplannings.setItems(items);
    listplannings.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                selectedPlan = plannings.get(listplannings.getSelectionModel().getSelectedIndex());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/reservation.fxml"));
                Parent root = loader.load();
                ReservationController controller = loader.getController();
                controller.initData(selectedPlan);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FilmdetController.class.getName()).log(Level.SEVERE, null, ex);
            }
});
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    }    
    private static String titreFilm;
    private static String erreur;
    @FXML
    private void Vote(ActionEvent event) throws IOException {
                long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);
         VoteService vs = new VoteService();
         Film f = new Film(FilmSession.getID_film());
         User u = new User(UserSession.getID_User());
         Vote v =new Vote(u,f, "", date);
         vs.ajouterVote1(v);
    }

    @FXML
    private void Rating(ActionEvent event) throws IOException {

          FilmService fs = new FilmService();
           VoteService vs = new VoteService();
        
        if (vs.afficherVoteee(UserSession.getID_User(), FilmSession.getID_film())) {
            titreFilm = FilmSession.getTitre();

            //nheb nzid condition idha l film deja voté ykharrejli alert si non yhezni ll FiveStars.fxml
            FiveStarsController.xx1 = titreFilm;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Charts/AlreadyVoted.fxml"));
             Parent root = loader.load();

            AlreadyVotedController alreadyVotedController = loader.getController();
            //fiveStarsController.display(titreFilm);

            //Parent root = FXMLLoader.load(getClass().getResource("FiveStars.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if (!vs.afficherVoteee(UserSession.getID_User(), FilmSession.getID_film())){
            titreFilm = FilmSession.getTitre();

            //nheb nzid condition idha l film deja voté ykharrejli alert si non yhezni ll FiveStars.fxml
            FiveStarsController.xx1 = titreFilm;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Charts/Rate.fxml"));
            Parent root = loader.load();

            RateController rateController = loader.getController();
            //fiveStarsController.display(titreFilm);

            //Parent root = FXMLLoader.load(getClass().getResource("FiveStars.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
    
    
}
