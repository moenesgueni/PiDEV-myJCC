package Controllers;

import Models.ContratSponsoring;
import Services.ContratSponsorinService;
import Utilities.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import Utilities.UserSession;

public class SponsorGraphController implements Initializable {

    //Service contrat
    ContratSponsorinService css = new ContratSponsorinService();
    List<ContratSponsoring> contrats;

    //var pour les charts
    private int ParPhoto, ParHeure, ParSoiree, ParEdition = 0;
    private int Proposition, ContreProposition, EnCours, Expire = 0;

    @FXML
    private PieChart typePieChart, EtatPieChart;

    public void LoadContrats(int idSponsor) {
        if (UserSession.getRole() == Type.SPONSOR) {
            contrats = css.afficherContratsDeSponsor(idSponsor);
        } else {
            contrats = css.afficherContratsDephotographe(idSponsor);
        }
        for (int i = 0; i < contrats.size(); i++) {
            ContratSponsoring c = contrats.get(i);
            switch (c.getType().toString()) {
                case "ParPhoto":
                    ParPhoto++;
                    break;
                case "ParHeure":
                    ParHeure++;
                    break;
                case "ParSoiree":
                    ParSoiree++;
                    break;
                case "ParEdition":
                    ParEdition++;
                    break;
                default:
                    System.out.println("defaultType" + i);
            }
            switch (c.getEtat().toString()) {
                case "Proposition":
                    Proposition++;
                    break;
                case "ContreProposition":
                    ContreProposition++;
                    break;
                case "EnCours":
                    EnCours++;
                    break;
                case "Expire":
                    Expire++;
                    break;
                default:
                //System.out.println("defaultEtat" + i);
            }
        }
    }

    private void populerTypePie() {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("ParPhoto", ParPhoto),
                        new PieChart.Data("ParHeure", ParHeure),
                        new PieChart.Data("ParSoiree", ParSoiree),
                        new PieChart.Data("ParEdition", ParEdition));
        typePieChart.setTitle("Les Types De Contrats");
        typePieChart.setData(pieChartData);
    }

    private void populerEtatPie() {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Proposition", Proposition),
                        new PieChart.Data("ContreProposition", ContreProposition),
                        new PieChart.Data("EnCours", EnCours),
                        new PieChart.Data("Expire", Expire));
        EtatPieChart.setTitle("Les Etats De Contrats");
        EtatPieChart.setData(pieChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadContrats(UserSession.getID_User());

        populerTypePie();
        populerEtatPie();
    }

}
