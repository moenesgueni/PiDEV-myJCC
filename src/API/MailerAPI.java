package API;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import static javax.mail.Transport.send;
import javax.mail.internet.*;



/**
 *
 * @author moene
 */
public class MailerAPI {
    
        static Session sesh;
        static Properties prop = new Properties();
    
       public static void sendMail( String to, int CODE){
    //Propriétés

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
            message.setSubject("Réinitialisation de mot de passe");
            message.setText("Bonjour, le code de réinitialisation de votre mot de passe du compte MyJCC est"+CODE);

            // Envoi du message
            Transport.send(message);

            System.out.println("Le message a été envoyé avec succès.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
              public static void sendMail( String to, String a,String p){
    //Propriétés

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
            message.setSubject("Bienvenue à MyJCC");
            message.setText("Bonjour, Bienvenue avec nous \n Votre Adresse est "+a+"\n Votre Mot De Passe est "+p);
            
            // Envoi du message
            Transport.send(message);

            System.out.println("Le message a été envoyé avec succès.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
  }

