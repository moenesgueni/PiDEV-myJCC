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
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import java.io.InputStream;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.util.Duration;

public class ContratsAfficherController implements Initializable {

    //var
    int currentUserId = 5;
    Type roleCurrentUser = Type.PHOTOGRAPHE;
    //Creation service Contrats & get mes contracts
    ContratSponsorinService cs = new ContratSponsorinService();
    private List<ContratSponsoring> listContrats;

    @FXML
    private ListView<AnchorPane> listAffichage;
    @FXML
    private ScrollPane scrollPDF;
    @FXML
    private AnchorPane blackpane;

    private void getContrats() {
        if (roleCurrentUser == Type.PHOTOGRAPHE) {
            listContrats = cs.afficherContratsDephotographe(currentUserId);
        } else {
            listContrats = cs.afficherContratsDeSponsor(currentUserId);
        }
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
        scrollPDF.setVisible(true);
        blackpane.toFront();
        scrollPDF.toFront();
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
            scrollPDF.setVisible(false);
        });
    }

    public void seePDF(String path) throws IOException {
        //PDDocument document = PDDocument.load(new File(c.getTermesPDF()));
        PDDocument document = PDDocument.load(new File("C:\\Users\\Marwen\\Desktop\\demandeDeStage.pdf"));
        PDFRenderer renderer = new PDFRenderer(document);

        BufferedImage image = renderer.renderImageWithDPI(0, 300);

        Canvas pdfDisplayCanvas = new Canvas();
        pdfDisplayCanvas.setWidth(image.getWidth());
        pdfDisplayCanvas.setHeight(image.getHeight());

        GraphicsContext gc = pdfDisplayCanvas.getGraphicsContext2D();
        gc.drawImage(SwingFXUtils.toFXImage(image, null), 0, 0);

        document.close();
        fadeIn();
        scrollPDF.setContent(pdfDisplayCanvas);

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
            }
        });

        //seePDF(c.get)
        //----------------------------------
        p.getChildren().addAll(label1, label2, label3, label4, label5, img, label6, label7, bPDF);
        //Si click sur une élément (une galerie) on ouvre la galerie en pop up
        p.setOnMouseClicked(event -> {
            //detailsPane
        }
        );
        return p;
    }

    private void populateListView() throws IOException {
        for (int i = 0; i < listContrats.size(); i++) {
            listAffichage.getItems().add(populerCard(i));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPDF.setVisible(false);
        blackpane.setVisible(false);
        try {
            // get mes contrats
            getContrats();
            //populate listview
            populateListView();
        } catch (IOException ex) {
            Logger.getLogger(ContratsAfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        blackpane.setOnMouseClicked(event -> {
            fadeOut();
        });
    }

}
