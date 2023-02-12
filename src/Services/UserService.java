/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Interfaces.UserInterface;
import Models.User;
import Utilities.MaConnexion;
import Utilities.Type;
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
/**
 *
 * @author moene
 */
public class UserService implements UserInterface {
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterUser(User p) {
        String req = "INSERT INTO `user`(`Nom`, `Prenom`, `Sexe`, `Email` ,`MotDePasse`,`Role`,`PhotoB64`) VALUES ('"
                +p.getNom()+"','"+p.getPrenom()+"','"+p.getSexe()+"','"+p.getEmail()+"','"+p.getMotDePasse()+"','"+p.getRole().toString()+"','"+p.getPhotoB64()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne ajouté avec success!!");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterUser2(User p) {
        String req = "INSERT INTO `user`(`Nom`, `Prenom`, `Sexe`, `Email` ,`MotDePasse`,`Role`,`PhotoB64`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
//          ps.setInt(1, p.getID_User());
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getSexe());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getMotDePasse());
            ps.setString(6, p.getRole().toString());
            ps.setString(7, p.getPhotoB64());
            ps.executeUpdate();
            System.out.println("Personne ajouté avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

    @Override
    public List<User> afficherUser() {
        List<User> personnes = new ArrayList<>();
        String request = "SELECT * FROM user";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                User p = new User();
                p.setID_User(rs.getInt(1));
                p.setNom(rs.getString("Nom"));
                p.setPrenom(rs.getString("Prenom"));
                p.setSexe(rs.getString("Sexe"));
                p.setEmail(rs.getString("Email"));
                p.setMotDePasse(rs.getString("MotDePasse"));
                p.setRole(Type.valueOf(rs.getString("Role")));
                p.setPhotoB64(rs.getString("PhotoB64"));
                //
                personnes.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personnes;
    }

    @Override
   public void modifierUser(int id, String nom) {
            try{
             String req ="UPDATE `user` SET `Nom`= ? WHERE ID_User= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nom);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    };

   
    
   
    @Override
    public void supprimerUser(int id) {
        User p = new User();
        String request = "DELETE FROM `user` WHERE `ID_User` ="+id+";";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);

        } catch (SQLException ex) {
            Logger.getLogger(afficherUserbyID.class.getName()).log(Level.SEVERE, null, ex);
        }    }

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
                p.setRole(Type.valueOf(rs.getString("Role")));
                p.setPhotoB64(rs.getString("PhotoB64"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(afficherUserbyID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public List<User> FiltrerParRole(Type role) {
        List<User> personnes = new ArrayList<>();
        String request = "SELECT * FROM user WHERE role = '"+role+"';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                User p = new User();
                p.setID_User(rs.getInt(1));
                p.setNom(rs.getString("Nom"));
                p.setPrenom(rs.getString("Prenom"));
                p.setSexe(rs.getString("Sexe"));
                p.setEmail(rs.getString("Email"));
                p.setMotDePasse(rs.getString("MotDePasse"));
                p.setRole(Type.valueOf(rs.getString("Role")));
                p.setPhotoB64(rs.getString("PhotoB64"));
                //
                personnes.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personnes;
    }



}
