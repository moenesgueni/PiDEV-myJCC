/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import Models.LOGS;
import Models.User;
import Services.LogsService;
import Services.UserService;
import Utilities.MaConnexion;
import Utilities.Type;
import static Utilities.Type.ADMINSTRATEUR;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;



/**
 *
 * @author moene
 */
public class ADMIN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  
        UserService ps = new UserService();
        LogsService Ls = new LogsService();
        User p;
        User p2;
        LOGS LM;
        LM = new LOGS ("CCCCCCCCCCC");
        
        p = new User("Mohamed","Ali","Male","medali@esprit.tn","azertyuiop123",Type.ADMINSTRATEUR,"cjckcfk");
       //p2 = new User("","Ali","Femme","asmaali@esprit.tn","azertyuiop123",Type.SPECTATEUR,"cjckcfk");
        //ps.ajouterUser(p2);
       // ps.ajouterUser(p);
        //ps.ajouterUser2(p);
       // ps.modifierUser(689,p);
        //ps.supprimerUser(26);
        //ps.afficherUser().forEach(System.out::println);
        //System.out.println(ps.afficherUserbyID(28));
        //ps.FiltrerParRole(Type.SPECTATEUR).forEach(System.out::println);

        System.out.println("*************************************");
        
        java.sql.Date DateLog =new java.sql.Date(2023-02-15);
        
        //Ls.AjouterLogs(LM);
        //Ls.afficherLogs().forEach(System.out::println);
        Ls.Supprimer(3);
        //System.out.println(DateLog);
        //Ls.FiltrerParDate(DateLog).forEach(System.out::println);
        Ls.FiltrerParAction("CCCCCCCCCCC").forEach(System.out::println);
        Type[] listtype = Type.values();
        for (Type listtype1 : listtype) {
            System.out.print(listtype);
        }
}  
}    
    
