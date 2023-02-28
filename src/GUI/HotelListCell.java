/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Hotel;
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

/**
 *
 * @author youssef
 */
public class HotelListCell extends ListCell<Hotel> {
    private ImageView imageView = new ImageView();
    private Label titleLabel = new Label();
    private Label AdresseLabel = new Label();
    public HotelListCell() {  
        super();
        VBox vBox = new VBox(titleLabel, AdresseLabel);
        HBox hBox = new HBox(imageView, vBox);
        hBox.setSpacing(10);
        vBox.setSpacing(5);
        setGraphic(hBox);
    }
 @Override
    public void updateItem(Hotel hotel, boolean empty) {
        super.updateItem(hotel, empty);
        if (empty || hotel == null) {
            setText(null);
            setGraphic(null);
        } else {
            titleLabel.setText(hotel.getLibelle());
            AdresseLabel.setText(hotel.getAdresse());
            Image image = new Image("file:///C:/Users/youssef/Desktop/hotel.png");
            imageView.setImage(image);
            setGraphic(getListCell());
        }

    }  

    private HBox getListCell() {
        HBox hBox = new HBox(imageView, new VBox(titleLabel, AdresseLabel));
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }   
    
    
    
    
    
    
    
    
}
