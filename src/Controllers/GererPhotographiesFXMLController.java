package Controllers;

import Models.Photographie;
import Services.GalerieService;
import Services.PhotographieService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class GererPhotographiesFXMLController implements Initializable {

    //var
    private int teteDeLecture = 0;
    private int imageModifieActuel = 0;
    private final int idCurrentUserGalerie = 4;
    Path LocalPathToImage;
    File selectedFile;

    //service Photographie
    //creation service Photographie & Galerie
    PhotographieService ps = new PhotographieService();
    //Creation service galerie
    GalerieService gs = new GalerieService();
    //Filter : Afficher les Photographies d'un Photographe avec l'id de sa galerie
    List<Photographie> l;

    public void getPhotos(int id) {
        l = ps.afficherPhotographiesDunPhotographe(id);
    }
    //----------------------

    //afficher image
    @FXML
    private ImageView left, right;
    @FXML
    private ImageView photo1, photo2, photo3, grandimage;
    @FXML
    private ImageView delete1, delete2, delete3;
    @FXML
    private ImageView update1, update2, update3;
    @FXML
    private Label Nom1, Nom2, Nom3;
    @FXML
    private TextArea description1, description2, description3;
    //modifier image
    @FXML
    private Pane blackpane;
    @FXML
    private AnchorPane modifier_pane;
    @FXML
    private ImageView modifier_photo, modifier_close;
    @FXML
    private TextField modifier_nom;
    @FXML
    private TextArea modifier_description;
    @FXML
    private Button modifier_confirmer, modifier_annuler;
    @FXML
    //ajouter image
    private AnchorPane ajouter_pane;
    @FXML
    private Button ajouterphoto, choisiruneimage, ajouter_annuler, ajouter_confirmer;
    @FXML
    private ImageView ajouter_image_preview, ajouter_close;
    @FXML
    private TextField ajouter_nom;
    @FXML
    private TextArea ajouter_description;

    //black fade effect
    public void fadeIn() {
        blackpane.setVisible(true);
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), blackpane);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(0.3);
        fadeTransition1.play();
    }

    public void fadeOut() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.1), blackpane);
        fadeTransition1.setFromValue(0.05);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(event1 -> {
            blackpane.setVisible(false);
            grandimage.setVisible(false);
            modifier_pane.setVisible(false);
            ajouter_pane.setVisible(false);
        });
    }
    //-----------------

    public void AgrandirImage(int t) {
        fadeIn();
        grandimage.setVisible(true);
        Image newImage = new Image(l.get(teteDeLecture + t).getPhotographiePath());
        grandimage.setImage(newImage);
        blackpane.toFront();
        grandimage.toFront();
    }

    public void ModifierImage(int t) {
        fadeIn();
        modifier_pane.setVisible(true);
        Image newImage = new Image(l.get(teteDeLecture + t).getPhotographiePath());
        modifier_photo.setImage(newImage);
        modifier_nom.setText(l.get(teteDeLecture + t).getNom());
        modifier_description.setText(l.get(teteDeLecture + t).getDescription());
        blackpane.toFront();
        modifier_pane.toFront();
        imageModifieActuel = t;
    }

    public void SupprimerImage(int t) {
        ps.SupprimerPhotographie(l.get(teteDeLecture + t).getID_Photographie());
        if (teteDeLecture == l.size() - 3) {
            teteDeLecture--;
        }
        getPhotos(idCurrentUserGalerie);
        populerHBox(l);
    }

    //populer la HBox
    public void populerHBox(List<Photographie> l) {
        //photo1
        Image newImage = new Image(l.get(teteDeLecture).getPhotographiePath());
        photo1.setImage(newImage);
        Nom1.setText(l.get(teteDeLecture).getNom());
        description1.setText(l.get(teteDeLecture).getDescription());
        //photo2
        newImage = new Image(l.get(teteDeLecture + 1).getPhotographiePath());
        photo2.setImage(newImage);
        Nom2.setText(l.get(teteDeLecture + 1).getNom());
        description2.setText(l.get(teteDeLecture + 1).getDescription());
        //photo3
        newImage = new Image(l.get(teteDeLecture + 2).getPhotographiePath());
        photo3.setImage(newImage);
        Nom3.setText(l.get(teteDeLecture + 2).getNom());
        description3.setText(l.get(teteDeLecture + 2).getDescription());
    }
    //--------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Initialisation du carousel
        getPhotos(idCurrentUserGalerie);
        populerHBox(l);
        //-------------------------

        //Initialisation de l'effet fade
        blackpane.setVisible(false);
        grandimage.setVisible(false);
        modifier_pane.setVisible(false);
        ajouter_pane.setVisible(false);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), blackpane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        //----------------------------

        //Faire deffiler les images**************************************
        left.setOnMouseClicked(event -> {
            if (teteDeLecture > 0) {
                teteDeLecture--;
                populerHBox(l);
            }
        });
        right.setOnMouseClicked(event -> {
            if (teteDeLecture < l.size() - 3) {
                teteDeLecture++;
                populerHBox(l);
            }
        });
        //**************************************************************

        //Agrandir les images*******************************************
        photo1.setOnMouseClicked(event -> {
            AgrandirImage(0);
        });
        photo2.setOnMouseClicked(event -> {
            AgrandirImage(1);
        });
        photo3.setOnMouseClicked(event -> {
            AgrandirImage(2);
        });
        blackpane.setOnMouseClicked(event -> {
            fadeOut();
        });
        //**************************************************************

        //Ajouter une image*********************************************
        ajouterphoto.setOnMouseClicked(event -> {
            ajouter_pane.setVisible(true);
            fadeIn();
            blackpane.toFront();
            ajouter_pane.toFront();
        });
        choisiruneimage.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Ouvrir votre image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            selectedFile = fileChooser.showOpenDialog(new Stage());
            if (selectedFile != null) {
                LocalPathToImage = Paths.get(selectedFile.toString());
                Image image = new Image(selectedFile.toURI().toString());
                ajouter_image_preview.setImage(image);
            }

        });
        //anuler l'ajout
        ajouter_annuler.setOnMouseClicked(event -> {
            fadeOut();
        });
        ajouter_close.setOnMouseClicked(event -> {
            fadeOut();
        });
        //ajouter l'image dans la bd
        ajouter_confirmer.setOnMouseClicked(event -> {
            Photographie p = new Photographie(ajouter_nom.getText(), ajouter_description.getText(),
                    "", gs.afficherGalerie(idCurrentUserGalerie));
            ps.ajouterPhotographie(p);

            getPhotos(idCurrentUserGalerie);
            populerHBox(l);
            fadeOut();
        });
        //**************************************************************

        //supprimer une image*******************************************
        delete1.setOnMouseClicked(event -> {
            SupprimerImage(0);
        });
        delete2.setOnMouseClicked(event -> {
            SupprimerImage(1);
        });
        delete3.setOnMouseClicked(event -> {
            SupprimerImage(2);
        });
        //**************************************************************

        //modifier une image********************************************
        update1.setOnMouseClicked(event -> {
            ModifierImage(0);
        });
        update2.setOnMouseClicked(event -> {
            ModifierImage(1);
        });
        update3.setOnMouseClicked(event -> {
            ModifierImage(2);
        });
        //Annuler la modification
        modifier_annuler.setOnMouseClicked(event -> {
            fadeOut();
        });
        modifier_close.setOnMouseClicked(event -> {
            fadeOut();
        });
        //confirmer la modification
        modifier_confirmer.setOnMouseClicked(event -> {
            Photographie p = l.get(teteDeLecture + imageModifieActuel);
            p.setNom(modifier_nom.getText());
            p.setDescription(modifier_description.getText());
            ps.modifierPhotographie(p);
            getPhotos(idCurrentUserGalerie);
            populerHBox(l);
            fadeOut();
        });
        //**************************************************************
    }

}
