/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author dhia
 */


import Models.Salle;
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
public class SalleListCell extends ListCell<Salle> {
    private ImageView imageView = new ImageView();
    private Label nomS = new Label();
    private Label adresse = new Label();
    private Label numtel = new Label();
    
    public SalleListCell() {  
        super();
        VBox vBox = new VBox(nomS, adresse, numtel);
        HBox hBox = new HBox(imageView, vBox);
        hBox.setSpacing(10);
        vBox.setSpacing(5);
        setGraphic(hBox);
                

nomS.setFont(Font.font("System", FontWeight.BOLD, 30));
adresse.setFont(Font.font("System", FontWeight.BOLD, 20));
numtel.setFont(Font.font("System", FontWeight.BOLD, 20));

imageView.setFitWidth(100); // définir une largeur de 100 pixels
imageView.setFitHeight(150);
    }
 @Override
    public void updateItem(Salle salle, boolean empty) {
        super.updateItem(salle, empty);
        if (empty || salle == null) {
            setText(null);
            setGraphic(null);
        } else {
            nomS.setText(salle.getNomSalle());
            adresse.setText(salle.getAdresse());
            numtel.setText(salle.getNumTel_salle());
            
            Image image = new Image("http://localhost/myjcc/films/cinema.png");
            imageView.setImage(image);
            setGraphic(getListCell());
        }

    }  

    private HBox getListCell() {
        HBox hBox = new HBox(imageView, new VBox(nomS, adresse, numtel));
          hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.setStyle("-fx-background-color: #edece6; -fx-background-radius: 10px;");
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }    
}
    

