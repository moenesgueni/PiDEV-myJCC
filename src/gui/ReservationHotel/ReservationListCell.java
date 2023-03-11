/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ReservationHotel;

import Models.ReservationHotel;
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
public class ReservationListCell extends ListCell<ReservationHotel> {
    private ImageView imageView = new ImageView();
    private Label titleLabel = new Label();
    private Label HotelLabel = new Label();
    private Label UserLabel = new Label();
    private Label DateDLabel = new Label();
    private Label DateFLabel = new Label();
    private Label tarifLabel = new Label(); 
     public ReservationListCell() {
        super();
        VBox vBox = new VBox(titleLabel,HotelLabel, UserLabel,DateDLabel,DateFLabel, tarifLabel);
        HBox hBox = new HBox(imageView, vBox);
        hBox.setSpacing(10);
        vBox.setSpacing(5);
        setGraphic(hBox);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        titleLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

HotelLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
UserLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
DateDLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
DateFLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
tarifLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
imageView.setFitWidth(100); // définir une largeur de 100 pixels
imageView.setFitHeight(100);

    }
    @Override
    public void updateItem(ReservationHotel reservation, boolean empty) {
        super.updateItem(reservation, empty);
        if (empty || reservation == null) {
            setText(null);
            setGraphic(null);
        } else {
            titleLabel.setText("Reservation n° : "+String.valueOf(reservation.getIdReservationH())); 
            HotelLabel.setText("Hotel : " + reservation.getHotel().getLibelle());
            UserLabel.setText("Email invite : "+reservation.getUser().getEmail());
            DateDLabel.setText("Date d'Arrive : "+String.valueOf(reservation.getDate_debut()));
            DateFLabel.setText("Date d'Arrive : "+(String.valueOf(reservation.getDate_fin())));
            tarifLabel.setText("Tarif Totale :"+String.valueOf(reservation.getTarifTotal()));
            //Path tempFilePath = Paths.get(System.getProperty("user.home"), "Desktop", "reservation.png");
            Image image = new Image("http://localhost/myjcc/logos/reservation.png");
            imageView.setImage(image);
            setGraphic(getListCell());
        }   
}
        private HBox getListCell() {
        HBox hBox = new HBox(imageView, new VBox(titleLabel,HotelLabel, UserLabel,DateDLabel,DateFLabel, tarifLabel));
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.setStyle("-fx-background-color: #edece6; -fx-background-radius: 10px;");
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }
}