package Controllers;

import Models.ContratSponsoring;
import Services.ContratSponsorinService;
import Services.UserService;
import Utils.EnumEtatContrat;
import Utils.EnumTypeContrat;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import java.sql.Date;
import java.time.LocalDate;
import Models.User;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AjouterContratFXMLController implements Initializable {
    //instance des services**************************
    //creation de service contratSponsoring

    ContratSponsorinService css = new ContratSponsorinService();
    //creation service user
    UserService ps = new UserService();

    User pSponsor = ps.afficherUserbyID(1);
    User pPhotographe = ps.afficherUserbyID(5);
    List<ContratSponsoring> contrats;
    ContratSponsoring contratAModifier;
    //***********************************************

    @FXML
    private DatePicker datedebut, datefin;
    @FXML
    private ChoiceBox<EnumTypeContrat> typecontrat;
    @FXML
    private TextField salaire;
    @FXML
    private Button choisirtermespdf, confirmerajoutcontrat, annulerajoutcontrat, confirmemodifiercontrat;
    @FXML
    private Label messageErr;
    @FXML
    private ListView<String> listContrats;
    @FXML
    private Label Titre;
    @FXML
    private AnchorPane confirmersuppression, blackpane;
    @FXML
    private Button supprimercontrat, confirmer_supression, annuler_supression;

    public static Date datePickerToSQLDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        java.util.Date utilDate = java.sql.Date.valueOf(localDate);
        return new java.sql.Date(utilDate.getTime());
    }

    public void clearFiels() {
        datedebut.setValue(null);
        datefin.setValue(null);
        typecontrat.setValue(null);
        salaire.setText("");
        messageErr.setVisible(false);
        confirmemodifiercontrat.setVisible(false);
        supprimercontrat.setVisible(false);
        confirmersuppression.setVisible(false);
        Titre.setText("Ajouter un Contrat");
    }

    public void LoadContrats(int idSponsor) {
        contrats = css.afficherContratsDeSponsor(idSponsor);
    }

    public void populateListContrats(List<ContratSponsoring> contrats) {
        // Créer une liste d'éléments de ListView qui contiennent les propriétés des instances de la classe Hotel
        ObservableList<String> items = FXCollections.observableArrayList();
        for (ContratSponsoring c : contrats) {

            String item = c.getDateDebut() + " - " + c.getDateFin() + " - " + c.getType().toString()
                    + " - " + c.getEtat().toString() + " - " + c.getSalaireDt() + " - " + c.getTermesPDF()
                    + " - " + c.getPhotoraphe().getNom() + " " + c.getPhotoraphe().getPrenom() + " " + c.getPhotoraphe().getEmail();
            items.add(item);
        }
        listContrats.setItems(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messageErr.setVisible(false);
        confirmemodifiercontrat.setVisible(false);
        supprimercontrat.setVisible(false);
        confirmersuppression.setVisible(false);
        blackpane.setVisible(false);
        LoadContrats(1);

        //Remplir la liste des contrats du sponsor à l'id 1
        populateListContrats(contrats);
        //***********************************************
        //controled de saisie sur salaire textfield pour n'accepter que les floats
        UnaryOperator<TextFormatter.Change> floatFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^\\d*\\.?\\d*$") && !newText.startsWith(".")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(floatFilter);
        salaire.setTextFormatter(textFormatter);

        //************************************************************************
        //populet les choicebox*************************
        ObservableList<EnumTypeContrat> items = Arrays.stream(EnumTypeContrat.values()).collect(Collectors.toCollection(FXCollections::observableArrayList));
        typecontrat.setItems(items);
        //**********************************************

        //Ajouter un contrat****************************
        confirmerajoutcontrat.setOnMouseClicked(event -> {
            if (datedebut.getValue() != null && datefin.getValue() != null && typecontrat.getValue() != null && !salaire.getText().equals("")) {
                ContratSponsoring cs = new ContratSponsoring(datePickerToSQLDate(datedebut), datePickerToSQLDate(datefin),
                        typecontrat.getValue(), EnumEtatContrat.Proposition, Float.parseFloat(salaire.getText()), "", pSponsor, pPhotographe);
                css.ajouterContratSponsorin(cs);
                clearFiels();
            } else {
                messageErr.setVisible(true);
            }
        });
        //**********************************************

        //annuler ajout*********************************
        annulerajoutcontrat.setOnMouseClicked(event -> {
            clearFiels();
        });
        //**********************************************

        //Modifier un contrat***************************
        confirmemodifiercontrat.setOnMouseClicked(event -> {
            if (datedebut.getValue() != null && datefin.getValue() != null && typecontrat.getValue() != null && !salaire.getText().equals("")) {
                contratAModifier.setDateDebut(datePickerToSQLDate(datedebut));
                contratAModifier.setDateFin(datePickerToSQLDate(datefin));
                contratAModifier.setType(typecontrat.getValue());
                contratAModifier.setSalaireDt(Float.parseFloat(salaire.getText()));
                css.modifierContratSponsoring(contratAModifier);
                clearFiels();
                LoadContrats(1);
                populateListContrats(contrats);
            } else {
                messageErr.setVisible(true);
            }

        });
        //**********************************************

        //Supprimer un contrat**************************
        supprimercontrat.setOnMouseClicked(event -> {
            blackpane.setVisible(true);
            confirmersuppression.setVisible(true);
            blackpane.toFront();
            confirmersuppression.toFront();
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), blackpane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.3);
            fadeTransition1.play();
        });
        confirmer_supression.setOnMouseClicked(event -> {
            css.supprimerContratSponsoring(contratAModifier.getID_Contrat());
            blackpane.setVisible(false);
            confirmersuppression.setVisible(false);
            clearFiels();
            LoadContrats(1);
            populateListContrats(contrats);
        });
        annuler_supression.setOnMouseClicked(event -> {
            blackpane.setVisible(false);
            confirmersuppression.setVisible(false);
            clearFiels();
        });
        //**********************************************
    }

    @FXML
    private void modifierContrat(MouseEvent event) {
        Titre.setText("Modifier un Contrat");
        confirmemodifiercontrat.setVisible(true);
        supprimercontrat.setVisible(true);
        confirmemodifiercontrat.toFront();
        //String selectedContrat = listContrats.getSelectionModel().getSelectedItem();
        int selectedint = listContrats.getSelectionModel().getSelectedIndices().get(0);
        contratAModifier = contrats.get(selectedint);
        //set les fields des contrats a modifier
        datedebut.setValue(contratAModifier.getDateDebut().toLocalDate());
        datefin.setValue(contratAModifier.getDateFin().toLocalDate());
        typecontrat.setValue(contratAModifier.getType());
        salaire.setText(Float.toString(contratAModifier.getSalaireDt()));

        ContratSponsoring c1 = new ContratSponsoring();
        //System.out.println(selectedContrat);
    }
}
