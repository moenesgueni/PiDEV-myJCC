package Controllers;

import API.ColorPaletteCreator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PhotographePaletteController implements Initializable {

    //Colors
    private Color c1, c2, c3, c4 = null;
    private ColorPaletteCreator c;

    @FXML
    private HBox paletteBox;
    @FXML
    private ColorPicker color1_picker, color2_picker, color3_picker, color4_picker;
    @FXML
    private Label errorMessage;
    @FXML
    private Button genererUnePalette;

    private void callPaletteAPI() throws Exception {
        if (c2 != null) {
            if (c3 != null) {
                if (c4 != null) {
                    int red1 = (int) (c1.getRed() * 255);
                    int green1 = (int) (c1.getGreen() * 255);
                    int blue1 = (int) (c1.getBlue() * 255);
                    int red2 = (int) (c2.getRed() * 255);
                    int green2 = (int) (c2.getGreen() * 255);
                    int blue2 = (int) (c2.getBlue() * 255);
                    int red3 = (int) (c3.getRed() * 255);
                    int green3 = (int) (c3.getGreen() * 255);
                    int blue3 = (int) (c3.getBlue() * 255);
                    int red4 = (int) (c4.getRed() * 255);
                    int green4 = (int) (c4.getGreen() * 255);
                    int blue4 = (int) (c4.getBlue() * 255);
                    c = new ColorPaletteCreator("[" + red1 + "," + green1 + "," + blue1 + "]", "[" + red2 + "," + green2 + "," + blue2 + "]",
                            "[" + red3 + "," + green3 + "," + blue3 + "]", "[" + red4 + "," + green4 + "," + blue4 + "]");
                } else {
                    int red1 = (int) (c1.getRed() * 255);
                    int green1 = (int) (c1.getGreen() * 255);
                    int blue1 = (int) (c1.getBlue() * 255);
                    int red2 = (int) (c2.getRed() * 255);
                    int green2 = (int) (c2.getGreen() * 255);
                    int blue2 = (int) (c2.getBlue() * 255);
                    int red3 = (int) (c3.getRed() * 255);
                    int green3 = (int) (c3.getGreen() * 255);
                    int blue3 = (int) (c3.getBlue() * 255);
                    c = new ColorPaletteCreator("[" + red1 + "," + green1 + "," + blue1 + "]", "[" + red2 + "," + green2 + "," + blue2 + "]",
                            "[" + red3 + "," + green3 + "," + blue3 + "]");
                }
            } else {
                int red1 = (int) (c1.getRed() * 255);
                int green1 = (int) (c1.getGreen() * 255);
                int blue1 = (int) (c1.getBlue() * 255);
                int red2 = (int) (c2.getRed() * 255);
                int green2 = (int) (c2.getGreen() * 255);
                int blue2 = (int) (c2.getBlue() * 255);
                c = new ColorPaletteCreator("[" + red1 + "," + green1 + "," + blue1 + "]", "[" + red2 + "," + green2 + "," + blue2 + "]");
            }
        } else {
            int red1 = (int) (c1.getRed() * 255);
            int green1 = (int) (c1.getGreen() * 255);
            int blue1 = (int) (c1.getBlue() * 255);
            c = new ColorPaletteCreator("[" + red1 + "," + green1 + "," + blue1 + "]");
        }
    }

    private void populerPalette() {
        //Vider l'encienne palette de la HBox
        paletteBox.getChildren().clear();
        paletteBox.setSpacing(10);
        try {
            //Vérifier si au moins une couleur est sélectionné
            if (c1 != null) {
                callPaletteAPI();
                int[][] colors = c.getColors();
                //pour chaque couleur créer une anchorpane de la meme couleur et l'ajouter à la VBox
                for (int i = 0; i < colors.length; i++) {
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.setPrefSize(300, 200);
                    int red = colors[i][0];
                    int green = colors[i][1];
                    int blue = colors[i][2];
                    anchorPane.setStyle("-fx-background-color: rgb(" + red + "," + green + "," + blue + ")");
                    int rgb = (red << 16) | (green << 8) | blue;
                    String htmlCode = "#" + Integer.toHexString(rgb).substring(2).toUpperCase();
                    paletteBox.getChildren().add(create2labelsForPalette(anchorPane, "[" + red + "," + green + "," + blue + "]", htmlCode));
                }
            } else {
                errorMessage.setVisible(true);
            }
        } catch (Exception ex) {
            System.out.println("!!ERR : methode populer palette dans PhotographePaletteController" + ex);
        }
    }

    private AnchorPane create2labelsForPalette(AnchorPane a, String l1, String l2) {
        //Label 1 : Code RGB [0,0,0]
        Label label1 = new Label(l1);
        label1.setFont(new Font(24));
        AnchorPane.setTopAnchor(label1, a.getHeight() / 2 + 40);
        AnchorPane.setLeftAnchor(label1, 60.0);
        AnchorPane.setRightAnchor(label1, 60.0);
        AnchorPane.setBottomAnchor(label1, 0.0);

        //Label 2 : Code HTML #0000
        Label label2 = new Label(l2);
        label2.setFont(new Font(24));
        AnchorPane.setTopAnchor(label2, a.getHeight() / 2 - 40);
        AnchorPane.setLeftAnchor(label2, 90.0);
        AnchorPane.setRightAnchor(label2, 90.0);
        AnchorPane.setBottomAnchor(label2, 0.0);

        a.getChildren().addAll(label1, label2);
        return a;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMessage.setVisible(false);

        //call api on click sur générer palette de couleur
        genererUnePalette.setOnMouseClicked(event -> {
            populerPalette();
        });

    }

    @FXML
    private void color1_pickerHandler(ActionEvent event) {
        c1 = color1_picker.getValue();
    }

    @FXML
    private void color2_pickerHandler(ActionEvent event) {
        c2 = color1_picker.getValue();
    }

    @FXML
    private void color3_pickerHandler(ActionEvent event) {
        c3 = color1_picker.getValue();
    }

    @FXML
    private void color4_pickerHandler(ActionEvent event) {
        c4 = color1_picker.getValue();
    }

}
