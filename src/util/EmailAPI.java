/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Models.ReservationHotel;
import Models.User;
import Services.ReservationHotelService;
import Services.UserService;
import com.twilio.rest.taskrouter.v1.workspace.task.Reservation;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import static javax.mail.Transport.send;
import javax.mail.internet.*;

/**
 *
 * @author youssef
 */
public class EmailAPI {
        static Session sesh;
        static Properties prop = new Properties();
    
 public static void sendMail(String to , String emplacement,String nom) {
    // Propriétés
    String from = "myjcc2023@outlook.com";// Adresse email de l'expéditeur
    String password = "azertyuiop123!";// Mot de passe de l'expéditeur

    // Configuration de la propriété système pour l'envoi d'un e-mail via le serveur SMTP d'Outlook
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.office365.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");

    // Création d'une session pour l'authentification de l'expéditeur
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
        }
    });

    try {
        // Création d'un objet Message pour composer l'e-mail
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Qr Code de votre "+nom);

        // Création d'un objet Multipart pour le message texte et l'image en pièce jointe
        Multipart multipart = new MimeMultipart();

        // Ajout du message texte
        MimeBodyPart messageBodyPart = new MimeBodyPart();
  String msg = "Nous sommes heureux de confirmer votre réservation à l'Hôtel \n" +
                      "Ci-dessous se trouve le QR code correspondant à votre réservation. Veuillez le présenter à la réception de l'hôtel lors de votre arrivée \n" +
                      "Nous espérons que vous passerez un agréable séjour à Tunisie. \n" ;
        messageBodyPart.setText(msg);
        multipart.addBodyPart(messageBodyPart);

        // Création d'une partie pour l'image en pièce jointe
     /*   User user1 = new User();
        UserService us = new UserService();
        user1 = us.SearchByMail(to);
        int id =user1.getID_User();
        ReservationHotel r = new ReservationHotel();
        ReservationHotelService  rs = new ReservationHotelService();
        r=rs.filterByIdUser(id);*/
        MimeBodyPart imagePart = new MimeBodyPart();
        DataSource source = new FileDataSource(emplacement);
        imagePart.setDataHandler(new DataHandler(source));
        imagePart.setFileName(nom+".png");

        // Ajout de la partie image au Multipart
        multipart.addBodyPart(imagePart);

        // Ajout du Multipart à l'e-mail
        message.setContent(multipart);

        // Envoi du message
        Transport.send(message);

        System.out.println("Le message a été envoyé avec succès.");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
}
