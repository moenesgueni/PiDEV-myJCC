package Controllers;

import Services.GalerieService;
import Models.Galerie;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SponsorListGaleriesController implements Initializable {

    //Creation service galerie
    GalerieService gs = new GalerieService();
    private List<Galerie> listGaleires;

    private GridPane ListGrid = new GridPane();
    @FXML
    private ScrollPane scrollPane;

    private void getGaleries() {
        //Read : Afficher toutes les galeries
        listGaleires = gs.afficherGaleries();
    }

    private AnchorPane populerCard(int i) {
        AnchorPane p = new AnchorPane();
        p.setPrefSize(535, 270);
        Galerie g = listGaleires.get(i);
        p.setStyle("-fx-background-color: " + g.getCouleurHtml() + "; -fx-background-radius: 10px;");

        //Label 1 : Titre de La Galerie
        Label label1 = new Label(g.getNom());
        label1.setFont(new Font(30));
        AnchorPane.setTopAnchor(label1, 10.0);
        AnchorPane.setLeftAnchor(label1, 10.0);

        // Label 2 : Description de la galerie
        Label label2 = new Label("En Bref");
        label2.setFont(new Font(20));
        AnchorPane.setTopAnchor(label2, 50.0);
        AnchorPane.setLeftAnchor(label2, 10.0);

        // TexArea : Description de la galerie de la bd
        TextArea tar = new TextArea(g.getDescription());
        tar.setEditable(false);
        tar.setPrefSize(325, 140); // set preferred size
        tar.setMaxSize(325, 140); // set preferred size
        AnchorPane.setTopAnchor(tar, 80.0);
        AnchorPane.setLeftAnchor(tar, 10.0);
        AnchorPane.setBottomAnchor(tar, 30.0);

        //Get Photographe objct
        User u = g.getPhotographe();
        //Photographe picture
        ImageView img = new ImageView(u.getPhotoB64());
        img.setFitHeight(150);
        img.setFitWidth(100);
        AnchorPane.setTopAnchor(img, 10.0);
        AnchorPane.setRightAnchor(img, 25.0);

        //Photographe Nom
        Label label3 = new Label(u.getNom());
        label3.setFont(new Font(20));
        AnchorPane.setTopAnchor(label3, 170.0);
        AnchorPane.setRightAnchor(label3, 25.0);

        //Photographe Prenom
        Label label4 = new Label(u.getPrenom());
        label4.setFont(new Font(20));
        AnchorPane.setTopAnchor(label4, 190.0);
        AnchorPane.setRightAnchor(label4, 25.0);

        //Photographe Email
        Label label5 = new Label(u.getEmail());
        label5.setFont(new Font(20));
        AnchorPane.setBottomAnchor(label5, 10.0);
        AnchorPane.setRightAnchor(label5, 25.0);

        p.getChildren().addAll(label1, label2, tar, img, label3, label4, label5);
        //Si click sur une élément (une galerie) on ouvre la galerie en pop up
        p.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/GererPhotographiesFXML.fxml"));
                Parent root = loader.load();
                GererPhotographiesFXMLController gererPhotographiesFXMLController = loader.getController();
                gererPhotographiesFXMLController.ModifyIdGalerie(g.getID_Galerie());
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        );
        return p;
    }

    private void populerGridPane() {
        for (int i = 0; i < listGaleires.size(); i++) {
            ListGrid.add(populerCard(i), i % 3, i / 3);
        }
        ListGrid.setHgap(25);
        ListGrid.setVgap(25);
        scrollPane.setContent(ListGrid);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Récupérer la liste des galeries de la bd
        getGaleries();

        //Créer les lignes de Galeries dans la VBox
        populerGridPane();
        //*****************************************

    }

}