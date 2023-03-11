/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class MapController implements Initializable {

    @FXML
    private WebView webview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         File file = new File("src/Utilities/index.html");
        String fileUrl = file.toURI().toString();

        // Load the file into the WebView's engine
        webview.getEngine().load(fileUrl);
    }    
    
}
