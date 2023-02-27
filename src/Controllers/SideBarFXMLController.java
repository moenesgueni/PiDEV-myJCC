/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Evenement;
import Models.User;
import Services.EventService;
import Services.UserService;
import Utils.Connexion;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class SideBarFXMLController implements Initializable {

    //var
    private boolean isAlreadyTranslated = false;
    private Stage primaryStage;

    @FXML
    private TextField tfnom_event;
    @FXML
    private DatePicker tfdate_et_heure;
    @FXML
    private TextField tflieu;
    @FXML
    private TextField tftype_event;
    @FXML
    private TextField tfdescription;
    @FXML
    private Button update_button;
    @FXML
    private Button delete_button;

    @FXML
    private ListView<Evenement> listview;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Label nomPrenom;
    @FXML
    private Label role;
    @FXML
    private ImageView settings;
    @FXML
    private AnchorPane pane1;
    @FXML
    private ImageView fullScreen;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView userPhoto;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane3;
    @FXML
    private ImageView userPhoto2;
    @FXML
    private ImageView menu2;
    @FXML
    private AnchorPane workPlace;

    private EventService ES = new EventService();
    @FXML
    private Button insert_button;
    @FXML
    private TableView<?> tvEvent;
    @FXML
    private TableColumn<?, ?> colonne_nom;
    @FXML
    private TableColumn<?, ?> colonne_date;
    @FXML
    private TableColumn<?, ?> colonne_lieu;
    @FXML
    private TableColumn<?, ?> colonne_type;
    @FXML
    private TableColumn<?, ?> colonne_description;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize(URL url, ResourceBundle rb) {

        listview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Récupérer l'objet Evenement sélectionné
                Evenement evenement = listview.getSelectionModel().getSelectedItem();
                // Mettre à jour les champs de texte avec les valeurs de l'événement
                tfnom_event.setText(evenement.getNom_event());
                tfdate_et_heure.setValue(evenement.getDate_et_heure().toLocalDate());
                tflieu.setText(evenement.getLieu());
                tftype_event.setText(evenement.getType_event());
                tfdescription.setText(evenement.getDescription());
            }
        });
        updateEvent(listview, update_button, tfnom_event, tfdate_et_heure, tflieu, tftype_event, tfdescription);
        deleteEvent(listview, delete_button, tfnom_event, tfdate_et_heure, tflieu, tftype_event, tfdescription);
       // delete_button.setOnAction(this::deleteEvent);

  ObservableList ObList = FXCollections.observableList(ES.AfficherEvents()); 
        listview.setItems(ObList);

       
    }

    public void showevent() {
        listview.getItems().clear();
  ObservableList ObList = FXCollections.observableList(ES.AfficherEvents()); 
        listview.setItems(ObList);
    }

   

    @FXML
    private void insert_button(ActionEvent event) {

        String resProd1 = tfnom_event.getText();

        LocalDate date = tfdate_et_heure.getValue();

        java.sql.Date date_et_heure = java.sql.Date.valueOf(date);

        String resNom2 = tflieu.getText();
        String resNum1 = tftype_event.getText();
        String resNum2 = tfdescription.getText();

        EventService os = new EventService();

        Evenement t = new Evenement(resProd1, date_et_heure, resNom2, resNum1, resNum2);

        os.AjouterEvent(t);
        System.out.println("Done!");
        showevent();
    }

    public void updateEvent(ListView<Evenement> listView, Button updateButton, TextField tfNomEvent, DatePicker tfDateEtHeure, TextField tfLieu, TextField tfTypeEvent, TextField tfDescription) {
        updateButton.setOnAction(event -> {
            // Get the selected item
            Evenement evenement = listView.getSelectionModel().getSelectedItem();

            // Update the values of the event object
            evenement.setNom_event(tfNomEvent.getText());
            evenement.setDate_et_heure(java.sql.Date.valueOf(tfDateEtHeure.getValue()));
            evenement.setLieu(tfLieu.getText());
            evenement.setType_event(tfTypeEvent.getText());
            evenement.setDescription(tfDescription.getText());

            // Refresh the ListView to reflect the changes
            listView.refresh();
            // Update the event in the database
            EventService es = new EventService();
            es.ModifierEvent(evenement);
        });
    }
        public void delete_button(ActionEvent event) {
        // Get the selected event

    }
        private void deleteEvent(ListView<Evenement> listView, Button updateButton, TextField tfNomEvent, DatePicker tfDateEtHeure, TextField tfLieu, TextField tfTypeEvent, TextField tfDescription)
        {
                    Evenement evenement = listview.getSelectionModel().getSelectedItem();
            System.out.println(evenement);
        if (evenement != null) {
            // Confirmation dialog
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer l'événement sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Remove the event from the database
                EventService es = new EventService();
                es.SupprimerEvent(evenement);

                // Remove the event from the table view
                listview.getItems().remove(evenement);
                listview.refresh();
            }
        } else {
            // Error dialog if no event is selected
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un événement à supprimer.");

            alert.showAndWait();
        }
        }

    @FXML
    private void update_button(ActionEvent event) {
    }

}
/*
    public void deleteEvent(ActionEvent event) {
        // Get the selected event
        Evenement evenement = tvEvent.getSelectionModel().getSelectedItem();

        if (evenement != null) {
            // Confirmation dialog
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer l'événement sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Remove the event from the database
                EventService es = new EventService();
                es.SupprimerEvent(evenement);

                // Remove the event from the table view
                tvEvent.getItems().remove(evenement);
                tvEvent.refresh();
            }
        } else {
            // Error dialog if no event is selected
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un événement à supprimer.");

            alert.showAndWait();
        }
    }}

    @FXML
    private void delete_button(ActionEvent event) {
                // Get the selected event
        Evenement evenement = tvEvent.getSelectionModel().getSelectedItem();

        if (evenement != null) {
            // Confirmation dialog
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer l'événement sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Remove the event from the database
                EventService es = new EventService();
                es.SupprimerEvent(evenement);

                // Remove the event from the table view
               
            }
        } else {
            // Error dialog if no event is selected
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un événement à supprimer.");

            alert.showAndWait();
        }
         tvEvent.getItems().remove(evenement);
                tvEvent.refresh();
    }
}

    @FXML
    public void deleteEvent(ListView<Evenement> listView, Button deleteButton, TextField tfNomEvent, DatePicker tfDateEtHeure, TextField tfLieu, TextField tfTypeEvent, TextField tfDescription) {
        deleteButton.setOnAction(event -> {
            // Get the selected item
            Evenement evenement = listView.getSelectionModel().getSelectedItem();

            // Refresh the ListView to reflect the changes
            listView.refresh();
            // Update the event in the database
            EventService es = new EventService();
            es.SupprimerEvent(evenement);
        });
    }

    
    void delete() {
        String query = "DELETE FROM evenement WHERE nom = '" + tfnom_event.getText() + "'";

        executeQuery(query);
        showevent();
    }

    public void executeQuery(String query) {
        Connection conn = Connexion.getInstance().getCnx();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}*/
