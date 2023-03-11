/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.VehiculeGUI;

import Models.Vehicule;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author youssef
 */
public class VehiculeListCell  extends ListCell<Vehicule>  {
   private ImageView imageView = new ImageView();
    private Label titleLabel = new Label();
    private Label TypeLabel = new Label();
    private Label MarqueLabel = new Label();
    private Label CouleurLabel = new Label();

     public VehiculeListCell() {
        super();
        VBox vBox = new VBox(titleLabel,TypeLabel, MarqueLabel,CouleurLabel);
        HBox hBox = new HBox(imageView, vBox);
        hBox.setSpacing(10);
        vBox.setSpacing(5);
        setGraphic(hBox);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        titleLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

TypeLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
MarqueLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
CouleurLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

imageView.setFitWidth(100); // définir une largeur de 100 pixels
imageView.setFitHeight(100);

    }

 @Override
    public void updateItem(Vehicule vehicule, boolean empty) {
        super.updateItem(vehicule, empty);
        if (empty || vehicule == null) {
            setText(null);
            setGraphic(null);
        } else {
            titleLabel.setText("Matricule : "+vehicule.getMaricule());
            TypeLabel.setText("Type de voiture: "+vehicule.getType());
            MarqueLabel.setText("Marque de voiture : "+vehicule.getMarque());
            CouleurLabel.setText("Couleur de la voiture : "+vehicule.getCouleur());
            Image image = new Image("http://localhost/myjcc/logos/transport_car.png");
            imageView.setImage(image);
            setGraphic(getListCell());
        }
    } 

        private HBox getListCell() {
        HBox hBox = new HBox(imageView, new VBox(titleLabel,TypeLabel, MarqueLabel,CouleurLabel));
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.setStyle("-fx-background-color: #edece6; -fx-background-radius: 10px;");
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }   
}
   

