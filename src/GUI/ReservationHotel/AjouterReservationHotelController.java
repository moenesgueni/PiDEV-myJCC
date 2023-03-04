/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ReservationHotel;

import Models.Hotel;
import Models.ReservationHotel;
import Models.User;
import Services.HotelService;
import Services.ReservationHotelService;
import Services.UserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import static com.google.zxing.client.result.ParsedResultType.SMS;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;
import myjcc.Myjcc;
import util.EmailAPI;
import util.FileUpload;
import util.QRCodeUtil;
import util.SMSUtil;


/**
 * FXML Controller class
 *
 * @author youssef
 */
public class AjouterReservationHotelController implements Initializable {

    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField tarifT_H;
    @FXML
    private TextField nomH;
    @FXML
    private TextField iduser;
    HotelService hs = new HotelService();
    UserService us = new UserService();
    ReservationHotelService rs = new ReservationHotelService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterReservation(ActionEvent event) throws IOException, WriterException {
        if (nomH.getText().isEmpty() || iduser.getText().isEmpty()
                || date_debut.getValue() == null || date_fin.getValue() == null
                || tarifT_H.getText().isEmpty()) {
            // Alert user to fill in all fields
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
            return;
        }
if(Float.parseFloat(tarifT_H.getText())  <0){
       // Alert user to fill in all fields
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le champs Tarif Total doit etre positive");
            alert.showAndWait();
            return;
}

       

        Hotel h = new Hotel();
        h = hs.filterByName(nomH.getText());
        if (h.getLibelle()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'hôtel n'existe pas!");
            alert.showAndWait();
            return;
        }
        User u = new User();
        u = us.afficherUserbyID(Integer.parseInt(iduser.getText()));
        if (u.getNom()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" utilisateur n'existe pas!");
            alert.showAndWait();
            return;
        }
        // valider que la date de debut est avant la date de fin
        LocalDate dd = date_debut.getValue();
        Date dateD = Date.valueOf(dd);
        LocalDate df = date_fin.getValue();
        Date dateF = Date.valueOf(df);
        if (df.isBefore(dd)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La date de fin doit être supérieure à la date de début!");
            alert.showAndWait();
            return;
        }

        // Obtenir la date système actuelle
        LocalDate localDate = LocalDate.now();
        // Convertir en java.sql.Date
        Date DateReservation = Date.valueOf(localDate.toString());

        /*--------------------------------------------------------------------------------------------*/
        UserService us = new UserService();
        User u1 = new User();
        u1=us.afficherUserbyID(Integer.parseInt(iduser.getText()));
        String reservationInfo = "- Nom de l'hotel : " + nomH.getText() + "\n"
                + "- Nom & Prenom de l'invite : " + u1.getNom()+" " +u1.getPrenom()+ "\n"
                +"-Genre : " + u1.getGenre()+"\n"
                + "- Arrivee : " + date_debut.getValue().toString() + "\n"
                + "- Depart : " + date_fin.getValue().toString() + "\n"
                + "- Tarif total : " + tarifT_H.getText() + "\n"; // Construire la chaîne de texte pour le code QR
        /*generer le qr code */
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        int width = 300;
        int height = 300;
        String fileType = "png";
        System.out.println(reservationInfo);
        BufferedImage bufferedImage = null;
        try {
            ByteMatrix byteMatrix = qrCodeWriter.encode(reservationInfo, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j) == 0) {
                        graphics.fillRect(i, j, 1, 1);
                    } else {
                    }
                }
            }
            System.out.println("Success...");
        } catch (WriterException ex) {
            Logger.getLogger(AjouterReservationHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Récupérer le bureau
        File desktopDir = new File(System.getProperty("user.home") + "/Desktop");

// Créer un fichier temporaire avec le nom "temp.png"
        File outputFile = new File(desktopDir, "temp.png");

// Enregistrer l'image sur le fichier
        try {
            ImageIO.write(bufferedImage, "png", outputFile);
            System.out.println("L'image a été enregistrée sur le bureau avec le nom 'temp.png'.");
        } catch (IOException ex) {
            System.out.println("Une erreur s'est produite lors de l'enregistrement de l'image.");
            ex.printStackTrace();
        }
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        /*---------------------ajout dans le serveur----------------*/
        long millis = System.currentTimeMillis();
        String newName = String.valueOf(u.getID_User()) + millis + ".png";
        String emplacement ="C:/Users/youssef/Desktop/temp.png";
        try {

            FileUpload.uploadFile(emplacement, "QRimages\\" + newName);
        } catch (Exception ex) {
            Logger.getLogger(AjouterReservationHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*------------------Ajouter reservation--------------------*/
        ReservationHotel reservation = new ReservationHotel(DateReservation, dateD, dateF, Float.parseFloat(tarifT_H.getText()), "http://localhost/myjcc/QRimages/" + newName, h, u);
        rs.addReservationHotel(reservation);
        /**
         * *******************************************************
         */
        String phoneNumber = "+21626360693"; // Remplacer par le numéro de téléphone du client
      //  SMSUtil.sendSMS("Bonjour Youssef verifier votre email pour le code QR ", "+216 26 360 693");

        //  SMSUtil.sendQRCodeMMS(phoneNumber, reservationInfo, 250, 250);
/*------------------------Envoyer QR code dans un email--------------------*/
        EmailAPI e = new EmailAPI();
        e.sendMail("youssefkchaou4@gmail.com",emplacement,"ReservationHotel");

        /**
         * **********Banner *****************
         */
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setContentText("Reservation est effectuee avec succes");
        confirmation.show();

        /**
         * *****Vider les texteFiled*******
         */
        tarifT_H.setText("");
        iduser.setText("");
        nomH.setText("");
        date_debut.setValue(null);
        date_fin.setValue(null);

  }

    @FXML
    private void AnnulerR(ActionEvent event) {
        
    }
    
}
