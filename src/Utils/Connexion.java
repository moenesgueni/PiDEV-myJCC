/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ASUS
 */
public class Connexion {
    

//DB Credentials
    final String USERNAME = "root";
    final String PASSWORD = "";
    final String URL = "jdbc:mysql://127.0.0.1:3306/myjcc1";
//var
    Connection cnx;
    //1 privatisation du constructeur
    //2 créer une instance static de MaConnection
    static Connexion instance;
    
    
    //constructeur
    private Connexion() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection etablie avec succes !!!");
        } catch (SQLException e) {
        }

    }
    
    //3 getter static
    public static Connexion getInstance() {
        if(instance == null){
            instance = new Connexion();
        }
        return instance;
    }

    public static void setInstance(Connexion instance) {
        Connexion.instance = instance;
    }

    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }
}