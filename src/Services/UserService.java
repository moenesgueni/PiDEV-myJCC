/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.UserInterface;
import Models.User;
import java.sql.Connection;
import java.util.List;
import util.MyConnection;
import Interfaces.UserInterface;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Type;

/**
 *
 * @author youssef
 */
public class UserService implements UserInterface{
  Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouterUser(User p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterUser2(User p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierUser(int id, String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> afficherUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User afficherUserbyID(int id) {
           User p = new User();
        String request = "SELECT * FROM user WHERE `ID_User` ="+id+";";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                p.setID_User(rs.getInt(1));
                p.setNom(rs.getString("Nom"));
                p.setPrenom(rs.getString("Prenom"));
                p.setSexe(rs.getString("Sexe"));
                p.setEmail(rs.getString("Email"));
                p.setMotDePasse(rs.getString("MotDePasse"));
                p.setRole(Type.ACTEUR.valueOf(rs.getString("Role")));
                p.setPhotoB64(rs.getString("PhotoB64"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public List<User> FiltrerParRole(Type role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
