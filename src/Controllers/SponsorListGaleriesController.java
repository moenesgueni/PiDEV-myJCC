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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SponsorListGaleriesController implements Initializable {

    //Creation service galerie
    GalerieService gs = new GalerieService();
    private List<Galerie> listGaleires;

    private int teteDeLecture = 0;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView left, rigth;

    private void getGaleries() {
        //Read : Afficher toutes les galeries
        listGaleires = gs.afficherGaleries();
    }

    private AnchorPane populerCard(int i) {
        AnchorPane p = new AnchorPane();
        p.setPrefSize(550, 270);
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
        p.setOnMouseClicked(event -> {
            //SideBarFXMLController controller = new SideBarFXMLController();
            //controller.goToGalerie();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GererPhotographiesFXML.fxml"));
                Parent root = loader.load();
                GererPhotographiesFXMLController gererPhotographiesFXMLController = loader.getController();
                int idGalerie = g.getID_Galerie();
                gererPhotographiesFXMLController.ModifyIdGalerie(idGalerie);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        );
        return p;
    }

    private HBox populerLigne(int tetDLec, int pas, int plusLigne) {
        HBox h = new HBox();
        h.setSpacing(20);
        if (pas == 1) {
            for (int i = tetDLec + plusLigne; i < tetDLec + 3 + plusLigne; i++) {
                h.getChildren().add(populerCard(i));
            }
        } else if (pas == -1) {
            for (int i = tetDLec + 3 + plusLigne; i > tetDLec + plusLigne; i--) {
                h.getChildren().add(populerCard(i));
            }
        }

        return h;
    }

    private void populerVBox() {
        vbox.setSpacing(20);
        //Ligne 1
        vbox.getChildren().add(populerLigne(teteDeLecture, 1, 0));
        //Ligne 2
        vbox.getChildren().add(populerLigne(teteDeLecture, -1, 2));
        //Ligne 3
        vbox.getChildren().add(populerLigne(teteDeLecture, 1, 6));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Récupérer la liste des galeries de la bd
        getGaleries();

        //Créer les lignes de Galeries dans la VBox
        populerVBox();
        //*****************************************

        rigth.setOnMouseClicked(event -> {
            if (teteDeLecture < listGaleires.size() - 9) {
                vbox.getChildren().clear();
                teteDeLecture += 1;
                populerVBox();
            }
        });
        left.setOnMouseClicked(event -> {
            if (teteDeLecture > 0) {
                vbox.getChildren().clear();
                teteDeLecture -= 1;
                populerVBox();
            }
        });
    }

}
