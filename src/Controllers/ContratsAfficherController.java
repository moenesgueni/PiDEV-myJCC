package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import Services.ContratSponsorinService;
import Models.ContratSponsoring;
import Models.User;
import Utils.Type;
import Utils.EnumEtatContrat;
import Utils.EnumTypeContrat;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import java.io.InputStream;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.scene.control.TextFormatter;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.SnapshotParameters;

public class ContratsAfficherController implements Initializable {

    //var
    int currentUserId = 1;//5
    Type roleCurrentUser = Type.SPONSOR;//PHOTOGRAPHE
    //Creation service Contrats & get mes contracts
    ContratSponsorinService cs = new ContratSponsorinService();
    private List<ContratSponsoring> listContrats;
    ContratSponsoring contratEnCours = new ContratSponsoring();

    @FXML
    private ListView<AnchorPane> listAffichage;
    @FXML
    private AnchorPane blackpane, modifierPane;
    @FXML
    private Label contratTitreEtat;
    @FXML
    private DatePicker dateDebut, dateFin;
    @FXML
    private ChoiceBox<EnumTypeContrat> typeContrat;
    @FXML
    private TextField salaire;
    @FXML
    private Label messageERR, signatureObligatoire;
    @FXML
    private Button btnVoirPdf, accepterContrat, refuserContrat, contraProposition;
    @FXML
    private Canvas canvasSignature;
    private GraphicsContext gc;
    private double prevX, prevY;
    private boolean hasUsedCanvas;
    @FXML
    private ImageView closeBtn;
    @FXML
    private ImageView otherUserImage;
    @FXML
    private Label otherUserNomPren, otherUserEmail;
    @FXML
    private Label typContratText;

    private void getContrats() {
        if (roleCurrentUser == Type.PHOTOGRAPHE) {
            listContrats = cs.afficherContratsDephotographe(currentUserId);
        } else {
            listContrats = cs.afficherContratsDeSponsor(currentUserId);
        }
        //filtrer les contrats selon l'Etat
        List<ContratSponsoring> filteredContrats = new ArrayList<>();
        for (EnumEtatContrat etat : EnumEtatContrat.values()) {
            for (ContratSponsoring contrat : listContrats) {
                if (contrat.getEtat() == etat) {
                    filteredContrats.add(contrat);
                }
            }
        }
        listContrats = filteredContrats;
    }
    //retourne une couleur html selon l'Etat du contrat

    private String getBackgroundColor(EnumEtatContrat e) {
        switch (e) {
            case Proposition:
                return "#FFFACD";//yellow
            case ContreProposition:
                return "#FFE5B4";//orange
            case EnCours:
                return "#7FFF7F";//green
            case Expire:
                return "#FFB6C1";//red
            default:
                return "#DEDEDE";//gray
        }
    }

    private User getTheOtherUser(ContratSponsoring c) {
        if (roleCurrentUser == Type.PHOTOGRAPHE) {
            return c.getSponsor();
        } else {
            return c.getPhotoraphe();
        }
    }

    //black fade effect
    public void fadeIn() {
        blackpane.setVisible(true);
        modifierPane.setVisible(true);
        blackpane.toFront();
        modifierPane.toFront();
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
            modifierPane.setVisible(false);
            //Réactiver les btn
            accepterContrat.setVisible(true);
            refuserContrat.setVisible(true);
            contraProposition.setVisible(true);
            dateDebut.setEditable(true);
            dateFin.setEditable(true);
            typeContrat.setVisible(true);
            salaire.setEditable(true);
        });
    }

    public void seePDF(String path) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI(path));
    }

    private void populerContratEdit(ContratSponsoring c) {
        contratEnCours = c;
        //partie other user du contrat droite
        User u = getTheOtherUser(c);
        otherUserImage.setImage(new Image(u.getPhotoB64()));
        otherUserNomPren.setText(u.getNom() + " " + u.getPrenom());
        otherUserEmail.setText(u.getEmail());

        //partie gauche
        contratTitreEtat.setText("Contrat Sponsoring " + c.getEtat());
        dateDebut.setValue(c.getDateDebut().toLocalDate());
        dateFin.setValue(c.getDateFin().toLocalDate());
        typContratText.setText(c.getType().toString());
        typeContrat.setValue(c.getType());
        salaire.setText(Float.toString(c.getSalaireDt()));
    }

    private void desactiverLesBtnSelonRole(ContratSponsoring c) {
        //Désactiver les boutons selon l'etat du contrat et le role
        if (c.getEtat() == EnumEtatContrat.Proposition) {
            if (roleCurrentUser == Type.SPONSOR) {
                accepterContrat.setVisible(false);
                refuserContrat.setVisible(false);
                contraProposition.setVisible(false);
                dateDebut.setEditable(false);
                dateFin.setEditable(false);
                typeContrat.setVisible(false);
                salaire.setEditable(false);
            }
        } else if (c.getEtat() == EnumEtatContrat.ContreProposition) {
            contraProposition.setVisible(false);
            dateDebut.setEditable(false);
            dateFin.setEditable(false);
            typeContrat.setVisible(false);
            salaire.setEditable(false);
            if (roleCurrentUser == Type.PHOTOGRAPHE) {
                accepterContrat.setVisible(false);
                refuserContrat.setVisible(false);
            }
        } else {
            accepterContrat.setVisible(false);
            refuserContrat.setVisible(false);
            contraProposition.setVisible(false);
            dateDebut.setEditable(false);
            dateFin.setEditable(false);
            typeContrat.setVisible(false);
            salaire.setEditable(false);
        }
    }

    private AnchorPane populerCard(int i) throws IOException {
        AnchorPane p = new AnchorPane();
        p.setPrefSize(1000, 300);
        ContratSponsoring c = listContrats.get(i);

        p.setStyle("-fx-background-color: " + getBackgroundColor(c.getEtat()) + "; -fx-background-radius: 10px;");

        //Left side--------------------------
        //Label 1 : Date debut
        Label label1 = new Label("Date de début du Contrat : " + c.getDateDebut());
        label1.setFont(new Font(30));
        AnchorPane.setTopAnchor(label1, 20.0);
        AnchorPane.setLeftAnchor(label1, 10.0);

        // Label 2 : Date Fin
        Label label2 = new Label("Date de fin du Contrat : " + c.getDateFin());
        label2.setFont(new Font(30));
        AnchorPane.setTopAnchor(label2, 70.0);
        AnchorPane.setLeftAnchor(label2, 10.0);

        //Label 3 : Etat
        Label label3 = new Label("Etat du contrat : " + c.getEtat());
        label3.setFont(new Font(30));
        AnchorPane.setTopAnchor(label3, 120.0);
        AnchorPane.setLeftAnchor(label3, 10.0);

        //Label 4 : Type
        Label label4 = new Label("Type du contrat : " + c.getType());
        label4.setFont(new Font(30));
        AnchorPane.setTopAnchor(label4, 170.0);
        AnchorPane.setLeftAnchor(label4, 10.0);

        //Label 5 : Salaire
        Label label5 = new Label("Salaire du contrat : " + c.getSalaireDt());
        label5.setFont(new Font(30));
        AnchorPane.setTopAnchor(label5, 220.0);
        AnchorPane.setLeftAnchor(label5, 10.0);
        //----------------------------------
        //Mid side--------------------------
        //Get Photographe objct
        User u = getTheOtherUser(c);
        //Photographe picture
        ImageView img = new ImageView(u.getPhotoB64());
        img.setFitHeight(200);
        img.setFitWidth(170);
        AnchorPane.setTopAnchor(img, 10.0);
        AnchorPane.setLeftAnchor(img, 700.0);

        //Label 6 : Nom & Prenom Photographe
        Label label6 = new Label(u.getNom() + " " + u.getPrenom());
        label6.setFont(new Font(30));
        AnchorPane.setTopAnchor(label6, 220.0);
        AnchorPane.setLeftAnchor(label6, 700.0);

        //Label 7 : Email
        Label label7 = new Label(u.getEmail());
        label7.setFont(new Font(30));
        AnchorPane.setTopAnchor(label7, 260.0);
        AnchorPane.setLeftAnchor(label7, 700.0);
        //----------------------------------

        //Rigth Side------------------------
        Button bPDF = new Button("Voir le Document PDF");
        AnchorPane.setTopAnchor(bPDF, 140.0);
        AnchorPane.setRightAnchor(bPDF, 100.0);
        bPDF.setOnMouseClicked(event -> {
            try {
                seePDF(c.getTermesPDF());
            } catch (IOException ex) {
                Logger.getLogger(ContratsAfficherController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ContratsAfficherController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //seePDF(c.get)
        //----------------------------------
        p.getChildren().addAll(label1, label2, label3, label4, label5, img, label6, label7, bPDF);
        //Si click sur une élément (une galerie) on ouvre la galerie en pop up
        p.setOnMouseClicked(event -> {
            fadeIn();
            populerContratEdit(c);
            desactiverLesBtnSelonRole(c);
        }
        );
        return p;
    }

    private void populateListView() throws IOException {
        for (int i = 0; i < listContrats.size(); i++) {
            listAffichage.getItems().add(populerCard(i));
        }
    }

    private void reloadData() {
        try {
            // get mes contrats
            getContrats();
            //populate listview
            populateListView();
        } catch (IOException ex) {
            Logger.getLogger(ContratsAfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        blackpane.setVisible(false);
        modifierPane.setVisible(false);
        messageERR.setVisible(false);
        signatureObligatoire.setVisible(false);
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
        //populet les choicebox*************************
        ObservableList<EnumTypeContrat> items = Arrays.stream(EnumTypeContrat.values()).collect(Collectors.toCollection(FXCollections::observableArrayList));
        typeContrat.setItems(items);
        //**********************************************
        //controled de saisie sur salaire textfield pour n'accepter que les floats
        UnaryOperator<TextFormatter.Change> floatFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^\\d*\\.?\\d*$") && !newText.startsWith(".")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(floatFilter);
        salaire.setTextFormatter(textFormatter);

        //************************************************************************
        //get the contracts & les affichers
        reloadData();

        //Close Contrat Update
        blackpane.setOnMouseClicked(event -> {
            fadeOut();
        });
        closeBtn.setOnMouseClicked(event -> {
            fadeOut();
        });
        //voir contrat
        btnVoirPdf.setOnMouseClicked(event -> {
            try {
                seePDF(contratEnCours.getTermesPDF());
            } catch (IOException ex) {
                Logger.getLogger(ContratsAfficherController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ContratsAfficherController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //refuser : delete contrat
        refuserContrat.setOnMouseClicked(event -> {
            cs.supprimerContratSponsoring(contratEnCours.getID_Contrat());
            reloadData();
            fadeOut();
        });
        accepterContrat.setOnMouseClicked(event -> {
            if (!hasUsedCanvas && roleCurrentUser == Type.PHOTOGRAPHE) {
                signatureObligatoire.setVisible(true);
            } else {
                if (roleCurrentUser == Type.PHOTOGRAPHE) {
                    saveSignature();
                }
                contratEnCours.setEtat(EnumEtatContrat.EnCours);
                cs.modifierContratSponsoring(contratEnCours);
                reloadData();
                fadeOut();
            }
        });
        contraProposition.setOnMouseClicked(event -> {
            if (!hasUsedCanvas) {
                signatureObligatoire.setVisible(true);
            } else {
                saveSignature();
                signatureObligatoire.setVisible(false);
                if (dateDebut.getValue() != null && dateFin.getValue() != null && typeContrat.getValue() != null && !salaire.getText().equals("")) {
                    contratEnCours.setDateDebut(datePickerToSQLDate(dateDebut));
                    contratEnCours.setDateFin(datePickerToSQLDate(dateFin));
                    contratEnCours.setType(typeContrat.getValue());
                    contratEnCours.setSalaireDt(Float.parseFloat(salaire.getText()));
                    contratEnCours.setEtat(EnumEtatContrat.ContreProposition);
                    cs.modifierContratSponsoring(contratEnCours);
                    reloadData();
                    fadeOut();
                    messageERR.setVisible(false);
                } else {
                    messageERR.setVisible(true);
                }
            }
        });
    }

}
