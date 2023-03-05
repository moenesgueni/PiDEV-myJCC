package Controllers;

import Models.ContratSponsoring;
import Models.Photographie;
import Models.Galerie;
import Models.User;
import Services.ContratSponsorinService;
import Services.GalerieService;
import Services.PhotographieService;
import Services.UserService;
import Utils.EnumEtatContrat;
import Utils.EnumTypeContrat;
import Utils.FileUpload;
import Utils.Type;
import java.awt.image.BufferedImage;
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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javafx.scene.image.WritablePixelFormat;

public class GererPhotographiesFXMLController implements Initializable {

    //var
    private int teteDeLecture = 0;
    private int imageModifieActuel = 0;
    private int idCurrentUserGalerie = 4; //idCurrentGalerie (Sponsor peut y accéder depuis liste galeries)
    File selectedFile;
    int CurrentUserId = 1;//5
    Type CurrentUserRole = Type.SPONSOR;//PHOTOGRAPHE
    Image CurrentGrandImage;

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

    //creation service user
    UserService us = new UserService();

    User pSponsor = us.afficherUserbyID(CurrentUserId);
    User pPhotographe = gs.afficherGalerie(idCurrentUserGalerie).getPhotographe();
    //----------------------

    //afficher image
    @FXML
    private ImageView left, right;
    @FXML
    private ImageView photo1, photo2, photo3, grandimage, telechargerImage;
    @FXML
    private ImageView delete1, delete2, delete3;
    @FXML
    private ImageView update1, update2, update3;
    @FXML
    private Label Nom1, Nom2, Nom3, maGalerie;
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
    //modifier galerie
    @FXML
    private AnchorPane galeriePane;
    @FXML
    private ImageView galerie_modifier_close;
    @FXML
    private TextField galerie_modifier_nom;
    @FXML
    private TextArea galerie_modifier_description;
    @FXML
    private ColorPicker galerie_modifier_couleur;
    @FXML
    private Button modifierMaGalerie, galerie_modifier_confirmer, galerie_modifier_annuler;
    @FXML
    private HBox galerieBackgroundColor;
    //POroposer Contrat Sponsoring
    @FXML
    private Button proposerContrat, ajouterContrat, annulerContrat;
    @FXML
    private AnchorPane proposerContrat_pane;
    @FXML
    private ImageView proposerContratClose, contratPhotoUser;
    @FXML
    private DatePicker contratDateDebut, ContratDateFin;
    @FXML
    private ChoiceBox<EnumTypeContrat> typeContrat;
    @FXML
    private TextField salairecontrat;
    @FXML
    private Label contratNomPrenom, contratEmail;
    @FXML
    private Label messageErr, signatureObligatoire;
    //Signature
    @FXML
    private Canvas canvasSignature;
    private GraphicsContext gc;
    private double prevX, prevY;
    private boolean hasUsedCanvas;

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
            galeriePane.setVisible(false);
            telechargerImage.setVisible(false);
            proposerContrat_pane.setVisible(false);
        });
    }
    //-----------------

    public void AgrandirImage(int t) {
        fadeIn();
        grandimage.setVisible(true);
        telechargerImage.setVisible(true);
        CurrentGrandImage = new Image(l.get(teteDeLecture + t).getPhotographiePath());
        grandimage.setImage(CurrentGrandImage);
        blackpane.toFront();
        grandimage.toFront();
        telechargerImage.toFront();
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

    //
    void ModifyIdGalerie(int idGalerie) {
        idCurrentUserGalerie = idGalerie;
        //rechargement des données
        getPhotos(idCurrentUserGalerie);
        populerHBox(l);
        Galerie currentGalerie = gs.afficherGalerie(idCurrentUserGalerie);
        maGalerie.setText(currentGalerie.getNom());
        galerieBackgroundColor.setStyle("-fx-background-color:" + currentGalerie.getCouleurHtml() + "; -fx-background-radius: 10px;");
    }

    public static Date datePickerToSQLDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        java.util.Date utilDate = java.sql.Date.valueOf(localDate);
        return new java.sql.Date(utilDate.getTime());
    }

    public void saveSignature() {
        try {
            Image snapshot = canvasSignature.snapshot(new SnapshotParameters(), null);
            File outputFile = new File("C:\\Users\\Marwen\\Desktop\\signature.png");
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Initialisation du carousel
        getPhotos(idCurrentUserGalerie);
        populerHBox(l);
        proposerContrat.setVisible(false);
        proposerContrat_pane.setVisible(false);
        Galerie currentGalerie = gs.afficherGalerie(idCurrentUserGalerie);
        if (CurrentUserId != currentGalerie.getPhotographe().getID_User()) {
            ajouterphoto.setVisible(false);
            delete1.setVisible(false);
            delete2.setVisible(false);
            delete3.setVisible(false);
            update1.setVisible(false);
            update2.setVisible(false);
            update3.setVisible(false);
            modifierMaGalerie.setVisible(false);
            if (CurrentUserRole == Type.SPONSOR) {
                proposerContrat.setVisible(true);
                messageErr.setVisible(false);
                signatureObligatoire.setVisible(false);
                //populet les choicebox*************************
                ObservableList<EnumTypeContrat> items = Arrays.stream(EnumTypeContrat.values()).collect(Collectors.toCollection(FXCollections::observableArrayList));
                typeContrat.setItems(items);
                //controle de saisie sur salaire textfield pour n'accepter que les floats
                UnaryOperator<TextFormatter.Change> floatFilter = change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("^\\d*\\.?\\d*$") && !newText.startsWith(".")) {
                        return change;
                    }
                    return null;
                };
                TextFormatter<String> textFormatter = new TextFormatter<>(floatFilter);
                salairecontrat.setTextFormatter(textFormatter);
                //Canvas for Signature
                gc = canvasSignature.getGraphicsContext2D();
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, canvasSignature.getWidth(), canvasSignature.getHeight());
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(2.0);
                hasUsedCanvas = false;
                canvasSignature.setOnMousePressed(event -> {
                    prevX = event.getX();
                    prevY = event.getY();
                    hasUsedCanvas = true;
                });

                canvasSignature.setOnMouseDragged(event -> {
                    gc.strokeLine(prevX, prevY, event.getX(), event.getY());
                    prevX = event.getX();
                    prevY = event.getY();
                    hasUsedCanvas = true;
                });
            }
        }
        maGalerie.setText(currentGalerie.getNom());
        galerieBackgroundColor.setStyle("-fx-background-color:" + currentGalerie.getCouleurHtml() + "; -fx-background-radius: 10px;");
        //-------------------------

        //Initialisation de l'effet fade
        blackpane.setVisible(false);
        grandimage.setVisible(false);
        modifier_pane.setVisible(false);
        ajouter_pane.setVisible(false);
        galeriePane.setVisible(false);
        telechargerImage.setVisible(false);
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
        //télécharger l'image
        telechargerImage.setOnMouseClicked(event -> {
            // Create a FileChooser object to allow the user to choose the destination path and filename
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG Images", "*.png"),
                    new FileChooser.ExtensionFilter("JPEG Images", "*.jpg", "*.jpeg"));
            File file = fileChooser.showSaveDialog(new Stage());

            if (file != null) {
                try {
                    // Convert the Image object to a BufferedImage object
                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(CurrentGrandImage, null);

                    // Save the BufferedImage object to the chosen file using the ImageIO class
                    ImageIO.write(bufferedImage, "png", file);

                    System.out.println("Image saved to file: " + file.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("Error saving image: " + e.getMessage());
                }
            }
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
            //ajout de l'image dans le serveur
            try {
                long millis = System.currentTimeMillis();
                String fileExtention = selectedFile.toString().substring(selectedFile.toString().lastIndexOf("."));
                String newName = String.valueOf(idCurrentUserGalerie) + millis + fileExtention;
                FileUpload.uploadFile(selectedFile.toString(), "photographies\\" + newName);

                //ajout dans la table
                Photographie p = new Photographie(ajouter_nom.getText(), ajouter_description.getText(),
                        "http://localhost/myjcc/photographies/" + newName, currentGalerie);
                ps.ajouterPhotographie(p);
            } catch (Exception ex) {
                Logger.getLogger(GererPhotographiesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

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

        //Modifier Galerie**********************************************
        //ouverir la pane qui contient la galerie à modifier
        modifierMaGalerie.setOnMouseClicked(event -> {
            fadeIn();
            galeriePane.setVisible(true);
            galerie_modifier_nom.setText(currentGalerie.getNom());
            galerie_modifier_description.setText(currentGalerie.getDescription());
            galerie_modifier_couleur.setValue(Color.web(currentGalerie.getCouleurHtml()));
            blackpane.toFront();
            galeriePane.toFront();
        });
        //annuler la modification de la galerie
        galerie_modifier_annuler.setOnMouseClicked(event -> {
            fadeOut();
        });
        galerie_modifier_close.setOnMouseClicked(event -> {
            fadeOut();
        });
        //Confirmer la modification de la galerie
        galerie_modifier_confirmer.setOnMouseClicked(event -> {
            currentGalerie.setNom(galerie_modifier_nom.getText());
            currentGalerie.setDescription(galerie_modifier_description.getText());
            String htmlColorCode = String.format("#%02X%02X%02X",
                    (int) (galerie_modifier_couleur.getValue().getRed() * 255),
                    (int) (galerie_modifier_couleur.getValue().getGreen() * 255),
                    (int) (galerie_modifier_couleur.getValue().getBlue() * 255));
            currentGalerie.setCouleurHtml(htmlColorCode);
            //Enregistrement des changements dans la base
            gs.modifierGalerie(currentGalerie);
            //Actualiser Nom & Couleur Galerie
            maGalerie.setText(currentGalerie.getNom());
            galerieBackgroundColor.setStyle("-fx-background-color:" + currentGalerie.getCouleurHtml() + "; -fx-background-radius: 10px;");
            fadeOut();
        });
        //**************************************************************

        //Proposer Contrat Sponsoring***********************************
        proposerContrat.setOnMouseClicked(event -> {
            fadeIn();
            proposerContrat_pane.setVisible(true);
            contratNomPrenom.setText(currentGalerie.getPhotographe().getNom() + " " + currentGalerie.getPhotographe().getPrenom());
            contratEmail.setText(currentGalerie.getPhotographe().getEmail());
            Image newImage = new Image(currentGalerie.getPhotographe().getPhotoB64());
            contratPhotoUser.setImage(newImage);
            blackpane.toFront();
            proposerContrat_pane.toFront();
        });
        //Annuler la proposition
        proposerContratClose.setOnMouseClicked(event -> {
            fadeOut();
        });
        annulerContrat.setOnMouseClicked(event -> {
            fadeOut();
        });
        //proposer le contrat
        ajouterContrat.setOnMouseClicked(event -> {
            ContratSponsorinService css = new ContratSponsorinService();
            if (!hasUsedCanvas) {
                signatureObligatoire.setVisible(true);
            } else {
                signatureObligatoire.setVisible(false);
                if (contratDateDebut.getValue() != null && ContratDateFin.getValue() != null && typeContrat.getValue() != null && !salairecontrat.getText().equals("")
                        && ContratDateFin.getValue().compareTo(contratDateDebut.getValue()) > 0) {
                    saveSignature();
                    ContratSponsoring cs = new ContratSponsoring(datePickerToSQLDate(contratDateDebut), datePickerToSQLDate(ContratDateFin),
                            typeContrat.getValue(), EnumEtatContrat.Proposition, Float.parseFloat(salairecontrat.getText()), "", pSponsor, "", pPhotographe, "");
                    css.ajouterContratSponsorin(cs);
                    fadeOut();
                } else {
                    messageErr.setVisible(true);
                }
            }
        });
        //**************************************************************
    }

}
