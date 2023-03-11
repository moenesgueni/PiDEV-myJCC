package GUI;
import Models.Film;
import Services.FilmService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Test1Controller implements Initializable {

    @FXML
    private ListView<Film> listView;
    @FXML
    private ImageView imageView;
    @FXML
    private Text text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FilmService fs = new FilmService();
        ObservableList<Film> films = FXCollections.observableArrayList(fs.afficherFilm());

        listView.setItems(films);
        listView.setCellFactory(param -> new ListCell<Film>() {
            private final ImageView imageView = new ImageView();
            private final Text titleText = new Text();

            @Override
            public void updateItem(Film film, boolean empty) {
                super.updateItem(film, empty);
                if (empty || film == null) {
                    setGraphic(null);
                } else {
                    titleText.setText(film.getTitre());
                    imageView.setImage(new Image(film.getImage()));
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    HBox hbox = new HBox(imageView, titleText);
                    hbox.setSpacing(10);
                    setGraphic(hbox);
                }
            }
        });
    }
}
