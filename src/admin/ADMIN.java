/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import API.MailerAPI;
import Models.LOGS;
import Models.User;
import Services.LogsService;
import Services.UserService;
import Utilities.MaConnexion;
import Utilities.PasswordHasher;
import Utilities.TestUser;
import Utilities.Type;
import static Utilities.Type.ADMINSTRATEUR;
import Utilities.UserSession;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;
import java.util.Random;
import javax.mail.MessagingException;



/**
 *
 * @author moene
 */
public class ADMIN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
  
        UserService ps = new UserService();
        LogsService Ls = new LogsService();
        User f;
        User p2;
        LOGS LM;
       // LM = new LOGS ("CCCCCCCCCCC");
        
    //  f = new User("Gueni","Moenes","Homme","moenes.gueni@esprit.tn",PasswordHasher.hashPassword("azertyuiop123"),Type.ADMINSTRATEUR,"http://localhost/myjcc/profile/me.jpg",96498278);
       //p2 = new User("","Ali","Femme","asmaali@esprit.tn","azertyuiop123",Type.SPECTATEUR,"cjckcfk");
/*if (!TestUser.verifierNomPrenom("Gueni")) {
    System.out.println("Le nom est invalide");
}

if (!TestUser.verifierNomPrenom("Moenes")) {
    System.out.println("Le prénom est invalide");
}

if (!TestUser.verifierMotDePasse("azertyuiop123")) {
    System.out.println("Le mot de passe est invalide");
}

if (!TestUser.verifierAdresseEmail("moenes.gueni@esprit.tn")) {
    System.out.println("L'adresse e-mail est invalide");
}*/
       // ps.ajouterUser(f);
       // ps.ajouterUser(p);
        //ps.ajouterUser2(p);
       // ps.modifierUser(689,p);
        //ps.supprimerUser(26);
        //ps.afficherUser().forEach(System.out::println);
        //System.out.println(ps.SearchByMail("moenesgueni@myjcc.com"));
        //ps.FiltrerParRole(Type.SPECTATEUR).forEach(System.out::println);

        //System.out.println(PasswordHasher.hashPassword("azertyuiop123"));
        //Random rand = new Random();
        //int randomCode = rand.nextInt(99999);
       // MailerAPI.sendMail("moenes.gueni.21@gmail.com",randomCode);
        
      //  java.sql.Date DateLog =new java.sql.Date(2023-02-15);
        
     //   Ls.AjouterLogs(LM);
       // Ls.afficherLogs().forEach(System.out::println);
        //Ls.Supprimer(3);
        //System.out.println(DateLog);
        //Ls.FiltrerParDate(DateLog).forEach(System.out::println);
       // Ls.FiltrerParAction("CCCCCCCCCCC").forEach(System.out::println);
        //MailerAPI.sendMail("moenes.gueni.21@gmail.com", f.getEmail(), f.getMotDePasse());
     //   Ls.modifierLogs(UserSession.getID_User(), LM.getDate(),LM.getAction());      
}  
}    
    

