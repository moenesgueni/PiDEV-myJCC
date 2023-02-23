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

public class AjouterContratFXMLController implements Initializable {

    @FXML
    private DatePicker datedebut, datefin;
    @FXML
    private ChoiceBox<EnumTypeContrat> typecontrat;
    @FXML
    private TextField salaire;
    @FXML
    private Button choisirtermespdf, confirmerajoutcontrat, annulerajoutcontrat;
    @FXML
    private Label messageErr;

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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messageErr.setVisible(false);

        //instance des services**************************
        //creation de service contratSponsoring
        ContratSponsorinService css = new ContratSponsorinService();
        //creation service user
        UserService ps = new UserService();

        User pSponsor = ps.afficherUserbyID(1);
        User pPhotographe = ps.afficherUserbyID(5);
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
            }else{
                messageErr.setVisible(true);
            }
        });
        //**********************************************

        //annuler ajout*********************************
        annulerajoutcontrat.setOnMouseClicked(event -> {
            clearFiels();
        });
        //**********************************************
    }

}
