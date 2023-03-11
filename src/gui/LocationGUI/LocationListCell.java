/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.LocationGUI;

import Models.Location;
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
public class LocationListCell extends ListCell<Location> {
    private ImageView imageView = new ImageView();
   private Label titleLabel = new Label();
       private Label UserLabel = new Label();
    private Label VahiculelLabel = new Label();
    private Label DateDLabel = new Label();
    private Label DateFLabel = new Label();
    private Label tarifLabel = new Label(); 
     public LocationListCell() {
        super();
        VBox vBox = new VBox(titleLabel,VahiculelLabel, UserLabel,DateDLabel,DateFLabel, tarifLabel);
        HBox hBox = new HBox(imageView, vBox);
        hBox.setSpacing(10);
        vBox.setSpacing(5);
        setGraphic(hBox);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        titleLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

VahiculelLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
UserLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
DateDLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
DateFLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
tarifLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
imageView.setFitWidth(100); // définir une largeur de 100 pixels
imageView.setFitHeight(100);
    }
 @Override
    public void updateItem(Location location, boolean empty) {
        super.updateItem(location, empty);
        if (empty || location == null) {
            setText(null);
            setGraphic(null);
        } else {
           titleLabel.setText("Location n° : "+String.valueOf(location.getIdLocationV())); 
            VahiculelLabel.setText("Matriule : " + location.getVehicule().getMaricule());
            UserLabel.setText("Email invite : "+location.getUser().getEmail());
            DateDLabel.setText("Date de debut location : "+String.valueOf(location.getDate_debut()));
            DateFLabel.setText("Date de fin location : "+(String.valueOf(location.getDate_fin())));
            tarifLabel.setText("Tarif Totale :"+String.valueOf(location.getTarifTotal()));
            //Path tempFilePath = Paths.get(System.getProperty("user.home"), "Desktop", "reservation.png");
            Image image = new Image("http://localhost/myjcc/logos/reservation.png");
            imageView.setImage(image);
            setGraphic(getListCell());
        }
    } 
        private HBox getListCell() {
        HBox hBox = new HBox(imageView, new VBox(titleLabel,VahiculelLabel, UserLabel,DateDLabel,DateFLabel, tarifLabel));
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.setStyle("-fx-background-color: #edece6; -fx-background-radius: 10px;");
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }   
}
