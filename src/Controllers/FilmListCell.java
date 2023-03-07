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


import Models.Film;
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
public class FilmListCell extends ListCell<Film> {
    private ImageView imageView = new ImageView();
    private Label titleLabel = new Label();
    private Label daterealisation = new Label();
    private Label Genre = new Label();
    
    public FilmListCell() {  
        super();
        VBox vBox = new VBox(titleLabel, daterealisation,Genre);
        HBox hBox = new HBox(imageView, vBox);
        hBox.setSpacing(10);
        vBox.setSpacing(5);
        setGraphic(hBox);
                

titleLabel.setFont(Font.font("System", FontWeight.BOLD, 30));
daterealisation.setFont(Font.font("System", FontWeight.BOLD, 20));
Genre.setFont(Font.font("System", FontWeight.BOLD, 20));

imageView.setFitWidth(100); // définir une largeur de 100 pixels
imageView.setFitHeight(150);
    }
 @Override
    public void updateItem(Film film, boolean empty) {
        super.updateItem(film, empty);
        if (empty || film == null) {
            setText(null);
            setGraphic(null);
        } else {
            titleLabel.setText(film.getTitre());
            daterealisation.setText(film.getDateRealisation());
            Genre.setText(film.getGenre());
            
            if (film.getImage() == null) {
               Image image = new Image("http://localhost/myjcc/films/null.png");
    imageView.setImage(image);
    imageView.setImage(null); // Set to null or a default image
} else {
    Image image = new Image(film.getImage());
    imageView.setImage(image);
}
            setGraphic(getListCell());
        }

    }  

    private HBox getListCell() {
        HBox hBox = new HBox(imageView, new VBox(titleLabel, daterealisation, Genre));
          hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.setStyle("-fx-background-color: #edece6; -fx-background-radius: 10px;");
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }    
}
    

