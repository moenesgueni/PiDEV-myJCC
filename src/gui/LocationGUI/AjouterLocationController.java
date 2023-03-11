/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.LocationGUI;


import Models.Location;
import Models.User;
import Models.Vehicule;
import Services.LocationVehiculeService;
import Services.UserService;
import Services.VehiculeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import API.EmailAPI;
import API.FileUpload;
import API.SMSUtil;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class AjouterLocationController implements Initializable {

    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField tarifT_H;
    @FXML
    private TextField iduser;
    @FXML
    private TextField matricule;
    VehiculeService vs = new VehiculeService();
    UserService us = new UserService();
    LocationVehiculeService ls =new LocationVehiculeService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterLocation(ActionEvent event) {
     if (tarifT_H.getText().isEmpty() || iduser.getText().isEmpty() || matricule.getText().isEmpty() || date_debut.getValue() == null || date_fin.getValue() == null) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez remplir tous les champs.");
    alert.showAndWait();
    return;
}
     if(Float.parseFloat(tarifT_H.getText())<0){
       // Alert user to fill in all fields
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le champs Tarif Total doit etre positive");
            alert.showAndWait();
            return;
}
     Vehicule v=new Vehicule();
     v=vs.GetVehiculeBymatricule(matricule.getText());
     if (v.getMaricule()==null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Vehicule n'existe pas!");
        alert.showAndWait();
        return;
    }
     User u =new User();
     u=us.afficherUserbyID(Integer.parseInt(iduser.getText()));
    if (u.getNom()==null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur n'existe pas!");
        alert.showAndWait();
        return;
    }
    LocalDate dd =date_debut.getValue();
    Date dateD = Date.valueOf(dd);
    LocalDate df =date_fin.getValue();
   Date dateF = Date.valueOf(df);
   LocalDate currentDate = LocalDate.now();
    // valider que la date de debut est avant la date de fin
    if(df.isBefore(dd)||dd.isBefore(currentDate)){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Date de location invalide !");
        alert.showAndWait();
        return;
    }
 /*------------Verifier le date ---------------*/

 
   // Obtenir la date système actuelle
    LocalDate localDate = LocalDate.now();
    // Convertir en java.sql.Date
    Date DateReservation = Date.valueOf(localDate.toString());
    /*--------------------------------------------------------------------------------------------*/   
    UserService us = new UserService();
        User u1 = new User();
        u1=us.afficherUserbyID(Integer.parseInt(iduser.getText()));
        String reservationInfo = "- Nom & Prenom de l'invite : " + u1.getNom()+" " +u1.getPrenom()+ "\n"
                +"-Genre : " + u1.getGenre()+"\n"
                +"-Matricule de Vehicule loue : "+v.getMaricule()+"\n"
                +"-Modele de vehicule : " +v.getMarque()+"\n"
                +"-Type de Vehicule : "+v.getType()+"\n"
                + "- Date de debut de location : " + date_debut.getValue().toString() + "\n"
                + "- Depart de fin de location : " + date_fin.getValue().toString() + "\n"
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
            Logger.getLogger(AjouterLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Récupérer le bureau
        File desktopDir = new File(System.getProperty("user.home") + "/Desktop");
        
        // Créer un fichier temporaire avec le nom "temp.png"
        File outputFile = new File(desktopDir, "temps.png");

        // Enregistrer l'image sur le fichier
        try {
            ImageIO.write(bufferedImage, "png", outputFile);
            System.out.println("L'image a été enregistrée sur le bureau avec le nom 'temps.png'.");
        } catch (IOException ex) {
            System.out.println("Une erreur s'est produite lors de l'enregistrement de l'image.");
            ex.printStackTrace();
        }
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));   
   
  /*---------------------ajout dans le serveur----------------*/
        long millis = System.currentTimeMillis();
        String newName = String.valueOf(u.getID_User()) + millis + ".png";
        Path tempFilePath = Paths.get(System.getProperty("user.home"), "Desktop", "temps.png");
        try {
            FileUpload.uploadFile(tempFilePath.toString(), "QRimages\\Vehicule\\" + newName);
        } catch (Exception ex) {
            Logger.getLogger(AjouterLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
 /*------------------Ajouter Location Vehicule---------------*/
   Location location = new Location(DateReservation, dateD, dateF, Float.parseFloat(tarifT_H.getText()),"http://localhost/myjcc/QRimages/Vehicule/" + newName, v, u);
   ls.addLocationBehicule(location);
/*----------------------Envoyer un sms--------------------------------*/
    String phoneNumber = "+21626360693"; // Remplacer par le numéro de téléphone du client
   //  SMSUtil.sendSMS("Bonjour Youssef verifier votre email pour le code QR \n Si Vous ne Trouverez pas verifier votre SPAM \n ", "+216 26 360 693");   
/*------------------------Envoyer QR code dans un email--------------------*/
    EmailAPI e = new EmailAPI();
      e.sendMail(u.getEmail(),tempFilePath.toString(),"LocationVehicule"); 
    
 /************Banner ******************/
    Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
    confirmation.setContentText("Reservation est effectuee avec succes");
    confirmation.show();
    /*******Vider les texteFiled********/
    tarifT_H.setText("");
    iduser.setText("");
    matricule.setText("");
    date_debut.setValue(null);
    date_fin.setValue(null);
       
        
    }

    @FXML
    private void AnnulerL(ActionEvent event) {
    }
    
}
