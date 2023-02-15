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
        LOGS LM;
      LM = new LOGS ("CCCCCCCCCCC");
       // p = new User("Moenes","Gueni","Male","moenes.gueni@esprit.tn","azertyuiop123",Type.ADMINSTRATEUR,"cjckcfk");
       // p = new User("gsdrr","gsrgssg","Male","moenes.gueni@esprit.tn","azertyuiop123","Admin","cjckcfk");
       // ps.ajouterUser(p);
        //ps.ajouterUser2(p);
        //ps.afficherUser().forEach(System.out::println);
        //System.out.println(ps.afficherUserbyID(25));
             Ls.afficherLogs().forEach(System.out::println);
                System.out.println("*************************************");
               // Ls.AjouterLogs(LM);
       //ps.FiltrerParRole(Type.ADMINSTRATEUR).forEach(System.out::println);
     //System.out.println(Ls.FiltrerParAction("Ajouté"));
     Ls.FiltrerParAction("CCCCCCCCCCC").forEach(System.out::println);
 
}  
}    
    

