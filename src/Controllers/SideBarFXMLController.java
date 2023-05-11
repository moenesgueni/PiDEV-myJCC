package Controllers;

import Models.User;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import Utilities.UserSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SideBarFXMLController implements Initializable {

    //var
    private boolean isAlreadyTranslated = false;
    private Stage primaryStage;

    @FXML
    private AnchorPane pane1;
    @FXML
    private ImageView fullScreen;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView userPhoto;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane3;
    @FXML
    private ImageView userPhoto2;
    @FXML
    private VBox VBoxIcons;
    @FXML
    private ImageView menu2;
    @FXML
    private BorderPane workPlace;
    @FXML
    private AnchorPane pane2;
    @FXML
    private VBox VBOXButtons;
    @FXML
    private Label nomPrenom;
    @FXML
    private Label role;
    @FXML
    private ImageView settings;
    @FXML
    private ImageView Home;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void translate() {
        pane1.setVisible(true);
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(0.3);
        fadeTransition1.play();

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(+600);
        translateTransition1.play();
        isAlreadyTranslated = true;
    }

    public void unTranslate() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(event1 -> {
            pane1.setVisible(false);
        });

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
        isAlreadyTranslated = false;
    }

    public void changeImage(String path) {
        String newImageUrl = path;
        Image newImage = new Image(newImageUrl);
        userPhoto.setImage(newImage);
        userPhoto2.setImage(newImage);
    }

    //Les pages à appeler*******************************************************
    private void SetHotelPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ListeHotel.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void VehiculePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/VehiculeGUI/ListeVehicule.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void ReservationHPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ReservationHotel/ListeReservation.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void LoactionPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/LocationGUI/ListeLocation.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void handleAjouterContrat() {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("ContratsAfficher");
        workPlace.setCenter(view);
        unTranslate();
    }

    private void handlePaletteCouleurs() {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("PhotographePalette");
        workPlace.setCenter(view);
        unTranslate();
    }

    private void handleListGaleries() {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("SponsorListGaleries");
        workPlace.setCenter(view);
        unTranslate();
    }

    /*
    public void goToGalerie() {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("AjouterContratFXML");
        workPlace.setCenter(view);
    }
     */
    private void handleGraph() {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("SponsorGraph");
        workPlace.setCenter(view);
        unTranslate();
    }

    private void handleMaGalerie() {
        FxmlLoader fl = new FxmlLoader();
        Pane view = fl.getPage("GererPhotographiesFXML");
        workPlace.setCenter(view);
        unTranslate();
    }

    private void ConsulterFilm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/affichagef.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void ConsulterSalle() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/affichages.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void ConsulterPlanning() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/affplanning.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void VoirPrix() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Charts/Prix.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void VoirCharts() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Charts/Chart.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void ConsulterEvenementA() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../sample/ajout.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void ConsulterEvenementS() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../sample/userEvent.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void ConsulterBlogA() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../sample/commentaire.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    private void ConsulterBlogS() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../sample/userBlog.fxml"));
            Pane autreInterface = loader.load();
            workPlace.setCenter(autreInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        unTranslate();
    }

    //**************************************************************************
    private void populerSideBar() {
        //Déclarer les routes
        Button buttonGererLesHotels = new Button("Gérer Les Hotels");
        buttonGererLesHotels.setOnAction(event -> {
            SetHotelPage();
        });
        Button buttonGererLesVehicules = new Button("Gérer Les Véhicules");
        buttonGererLesVehicules.setOnAction(event -> {
            VehiculePage();
        });
        Button buttonGererReservationsHotels = new Button("Gérer Les Réservations de Hotels");
        buttonGererReservationsHotels.setOnAction(event -> {
            ReservationHPage();
        });
        Button buttonGererLocationVehicules = new Button("Gérer Les Locations de Vehicules");
        buttonGererLocationVehicules.setOnAction(event -> {
            LoactionPage();
        });
        Button buttonAfficherContrats = new Button("Afficher Mes Contrats");
        buttonAfficherContrats.setOnAction(event -> {
            handleAjouterContrat();
        });
        Button buttonPaletteCouleur = new Button("Générer Palette de Couleur");
        buttonPaletteCouleur.setOnAction(event -> {
            handlePaletteCouleurs();
        });
        Button buttonListGalerie = new Button("Liste des galeries");
        buttonListGalerie.setOnAction(event -> {
            handleListGaleries();
        });
        Button buttonGraph = new Button("Graph sur mes contrats");
        buttonGraph.setOnAction(event -> {
            handleGraph();
        });
        Button buttonMaGalerie = new Button("Ma Galerie");
        buttonMaGalerie.setOnAction(event -> {
            handleMaGalerie();
        });
        Button buttonConsulterFilm = new Button("Consulter Film");
        buttonConsulterFilm.setOnAction(event -> {
            ConsulterFilm();
        });
        Button buttonConsulterSalle = new Button("Consulter Salle");
        buttonConsulterSalle.setOnAction(event -> {
            ConsulterSalle();
        });
        Button buttonConsulterPlanning = new Button("ConsulterPlanning");
        buttonConsulterPlanning.setOnAction(event -> {
            ConsulterPlanning();
        });
        Button buttonVoirPrix = new Button("VoirPrix");
        buttonVoirPrix.setOnAction(event -> {
            VoirPrix();
        });

        Button buttonVoirCharts = new Button("VoirCharts");
        buttonVoirCharts.setOnAction(event -> {
            VoirCharts();
        });
        Button buttonConsulterEvenementA = new Button("ConsulterEvenements");
        buttonConsulterEvenementA.setOnAction(event -> {
            ConsulterEvenementA();
        });
        Button buttonConsulterEvenementS = new Button("ConsulterEvenements");
        buttonConsulterEvenementS.setOnAction(event -> {
            ConsulterEvenementS();
        });
        Button buttonConsulterBlogA = new Button("Consulter Blog");
        buttonConsulterBlogA.setOnAction(event -> {
            ConsulterBlogA();
        });
        Button buttonConsulterBlogS = new Button("Consulter BlogS");
        buttonConsulterBlogS.setOnAction(event -> {
            ConsulterBlogS();
        });

        //populer vBoxButtons selon role
        Pane view;
        switch (UserSession.getRole()) {
            case ADMINSTRATEUR:
                //add the buttons
                VBOXButtons.getChildren().addAll(buttonGererLesHotels, buttonGererLesVehicules, buttonGererReservationsHotels,
                        buttonGererLocationVehicules, buttonConsulterFilm, buttonConsulterSalle, buttonConsulterPlanning, 
                        buttonVoirPrix, buttonVoirCharts, buttonConsulterEvenementA, buttonConsulterBlogA);

                FxmlLoader fl = new FxmlLoader();
                view = fl.getPage("Ajout");
                workPlace.setCenter(view);

                break;
            case ACTEUR:

                break;
            case EXPERT:

                break;
            case INVITE:

                break;
            case PHOTOGRAPHE:
                VBOXButtons.getChildren().addAll(buttonAfficherContrats, buttonPaletteCouleur, buttonGraph, buttonMaGalerie);
                break;
            case PRODUCTEUR:

                break;
            case SPECTATEUR:
                VBOXButtons.getChildren().addAll(buttonConsulterEvenementS, buttonConsulterBlogS, 
                        buttonVoirPrix, buttonVoirCharts, buttonConsulterFilm, buttonConsulterSalle);

                break;
            case SPONSOR:
                VBOXButtons.getChildren().addAll(buttonAfficherContrats, buttonListGalerie, buttonGraph);
                break;

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populerSideBar();
        /*
        fullScreen.setOnMouseClicked(event -> {
            if (primaryStage.isFullScreen()) {
                primaryStage.setFullScreen(false);
            } else {
                primaryStage.setFullScreen(true);
            }
        });
         */
        //go to settings fxml

        settings.setOnMouseClicked(event -> {
            FxmlLoader fl = new FxmlLoader();
            Pane view = fl.getPage("ModifierFXML");
            workPlace.setCenter(view);
            System.out.println("go to settings");
            unTranslate();
        });

        // go home
        Home.setOnMouseClicked(event -> {
            FxmlLoader fl = new FxmlLoader();
            Pane view = fl.getPage("Ajout");
            workPlace.setCenter(view);
            System.out.println("go to home");
            unTranslate();
        });

        //log Out
        logout.setOnMouseClicked(event -> {
            try {
                UserSession.EndSession();
                Parent root = FXMLLoader.load(getClass().getResource("../gui/LoginFXML.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                System.out.println("log out");
            } catch (IOException ex) {
                Logger.getLogger(SideBarFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //set user data
        //creation service user
        UserService ps = new UserService();

        User u = ps.SearchByMail(UserSession.getEmail());
        if (!u.getPhotoB64().equals("")) {
            changeImage(u.getPhotoB64());
        }
        nomPrenom.setText(u.getNom() + " " + u.getPrenom());
        role.setText(u.getRole().toString());
        //------------

        pane1.setVisible(false);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition.setByX(-600);
        translateTransition.play();

        pane1.toFront();
        pane2.toFront();
        pane3.toFront();
        menu.setOnMouseClicked(event -> {
            if (!isAlreadyTranslated) {
                translate();
            } else {
                unTranslate();
            }
        });
        menu2.setOnMouseClicked(event -> {
            if (!isAlreadyTranslated) {
                translate();
            } else {
                unTranslate();
            }
        });

        pane1.setOnMouseClicked(event -> {
            unTranslate();
        });
    }

    @FXML
    private void HomeHandle(MouseEvent event) {
    }
}
