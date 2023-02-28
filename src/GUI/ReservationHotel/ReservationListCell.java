/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ReservationHotel;

import Models.ReservationHotel;
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
public class ReservationListCell extends ListCell<ReservationHotel> {
    private ImageView imageView = new ImageView();
    private Label titleLabel = new Label();
    private Label UserLabel = new Label();
    private Label tarifLabel = new Label(); 
     public ReservationListCell() {
        super();
        VBox vBox = new VBox(titleLabel, UserLabel, tarifLabel);
        HBox hBox = new HBox(imageView, vBox);
        hBox.setSpacing(10);
        vBox.setSpacing(5);
        setGraphic(hBox);
    }
    @Override
    public void updateItem(ReservationHotel reservation, boolean empty) {
        super.updateItem(reservation, empty);
        if (empty || reservation == null) {
            setText(null);
            setGraphic(null);
        } else {
            titleLabel.setText(reservation.getHotel().getLibelle());
            UserLabel.setText(reservation.getUser().getEmail());
            tarifLabel.setText(String.valueOf(reservation.getTarifTotal()));
            Image image = new Image("file:///C:/Users/youssef/Desktop/hotel.png");
            imageView.setImage(image);
            setGraphic(getListCell());
        }
    
}
        private HBox getListCell() {
        HBox hBox = new HBox(imageView, new VBox(titleLabel, UserLabel, tarifLabel));
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }
}
