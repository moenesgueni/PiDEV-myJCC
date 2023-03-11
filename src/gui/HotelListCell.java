/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Hotel;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class HotelListCell extends ListCell<Hotel> {
    private ImageView imageView = new ImageView();
    private Label titleLabel = new Label();
    private Label AdresseLabel = new Label();
    private Label Nbre_chmbreLabel = new Label();
    private Label teleponeLabel = new Label();
    
    public HotelListCell() {  
        super();
        VBox vBox = new VBox(titleLabel, AdresseLabel,Nbre_chmbreLabel,teleponeLabel);
        HBox hBox = new HBox(imageView, vBox);
        hBox.setSpacing(10);
        vBox.setSpacing(5);
        setGraphic(hBox);
                titleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        titleLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

titleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
AdresseLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
Nbre_chmbreLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
teleponeLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

imageView.setFitWidth(100); // définir une largeur de 100 pixels
imageView.setFitHeight(100);
    }
 @Override
    public void updateItem(Hotel hotel, boolean empty) {
        super.updateItem(hotel, empty);
        if (empty || hotel == null) {
            setText(null);
            setGraphic(null);
        } else {
            titleLabel.setText("Nom d'Hotel : "+hotel.getLibelle());
            AdresseLabel.setText("Adresse de l'hotel: "+hotel.getAdresse());
            Nbre_chmbreLabel.setText("Le nombre de chambre encore disponible : "+String.valueOf(hotel.getNbre_chambres()));
            teleponeLabel.setText("Numero de Telephone : "+hotel.getTelephone());
            //Path tempFilePath = Paths.get(System.getProperty("user.home"), "Desktop", "hotel.png");
            Image image = new Image("http://localhost/myjcc/logos/hotel.png");
            imageView.setImage(image);
            setGraphic(getListCell());
        }

    }  

    private HBox getListCell() {
        HBox hBox = new HBox(imageView, new VBox(titleLabel, AdresseLabel,Nbre_chmbreLabel,teleponeLabel));
          hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.setStyle("-fx-background-color: #edece6; -fx-background-radius: 10px;");
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }    
}
