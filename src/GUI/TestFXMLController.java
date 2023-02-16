package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TestFXMLController implements Initializable {

    @FXML
    private TextField nomTF;
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField cinTf;
    @FXML
    private TextField ageTF;
    @FXML
    private Label showLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterPersonne(ActionEvent event) {
        /*
        PersonneInterface ps = new PersonneService();
        Personne p = new Personne();
        p.setNom(nomTF.getText());
        p.pre
        p.cin
        i.setAge(Integer.parseInt(ageTF.etText()));
        */
        System.out.println("test interface");
    }

    @FXML
    private void afficherPersonne(ActionEvent event) {
        /*
        PersonneInterface ps = new PersonneService();
        showLabel.setText(ps.afficherPersonne().toString());
        */
    }
    
}
