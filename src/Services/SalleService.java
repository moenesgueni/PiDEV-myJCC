/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.SalleInterface;
import Models.Salle;
import Utilities.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dhia
 */
public class SalleService implements SalleInterface {
    
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterSalle(Salle s) {
        String req = "INSERT INTO `salle`(`NomSalle`, `Adresse`, `Capacite`, `NumTel_salle`, `Email_Salle`, `Temps_Ouverture`, `Temps_Fermuture`, `Avis`) VALUES (?,?,?,?,?,?,?,?)";
                
                
    try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, s.getNomSalle());
            st.setString(2, s.getAdresse());
            st.setInt(3, s.getCapacite());
            st.setString(4, s.getNumTel_salle());
            st.setString(5, s.getEmail_Salle());
            st.setString(6, s.getTemps_Ouverture());
            st.setString(7, s.getTemps_Fermuture());
            st.setFloat(8, s.getAvis());
            st.executeUpdate();
            System.out.println("Salle ajoutée avec success!!");
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Salle> afficherSalle() {
       List<Salle> Salles = new ArrayList<>();
        String request = "SELECT * FROM Salle";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                Salle s = new Salle();
                s.setID_salle(rs.getInt(1));
                s.setNomSalle(rs.getString(2));
                s.setAdresse(rs.getString(3));
                s.setCapacite(rs.getInt(4));
                s.setNumTel_salle(rs.getString(5));
                s.setEmail_Salle(rs.getString(6));
                s.setTemps_Ouverture(rs.getString(7));
                s.setTemps_Fermuture(rs.getString(8));
                s.setAvis(rs.getFloat(9));
                
                //
                Salles.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Salles;
    }

    

    @Override
    public void updateSalle(Salle s) {
        String request = "UPDATE Salle SET NomSalle = ?, Adresse = ?, Capacite = ?, NumTel_salle = ?, Email_Salle = ?, Temps_Ouverture = ?, Temps_Fermuture = ?, Avis = ?  "
                +" WHERE ID_salle = ?";
try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, s.getNomSalle());
            ps.setString(2, s.getAdresse());
            ps.setInt(3, s.getCapacite());
            ps.setString(4, s.getNumTel_salle());
            ps.setString(5, s.getEmail_Salle());
            ps.setString(6, s.getTemps_Ouverture());
            ps.setString(7, s.getTemps_Fermuture());
            ps.setFloat(8, s.getAvis());
            ps.setInt(9, s.getID_salle());
            ps.executeUpdate();
            System.out.println("Salle modifiée avec success!!!");
}catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    public void supprimerSalle(String NomSalle) {
       String request = "DELETE FROM Salle WHERE NomSalle = ?";
       try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1,NomSalle );
            ps.executeUpdate();
            System.out.println("Salle supprimée avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    @Override
        public Salle GetSalleByID(int id) {
        Salle s = new Salle();
        String request = "SELECT * FROM salle WHERE `ID_salle` ="+id+";";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                
                s.setID_salle(rs.getInt(1));
                s.setNomSalle(rs.getString(2));
                s.setAdresse(rs.getString(3));
                s.setCapacite(rs.getInt(4));
                s.setNumTel_salle(rs.getString(5));
                s.setEmail_Salle(rs.getString(6));
                s.setTemps_Ouverture(rs.getString(7));
                s.setTemps_Fermuture(rs.getString(8));
                s.setAvis(rs.getFloat(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
        
    }
    

    @Override
    public Salle GetSalleByName(String NomSalle) {
        Salle s = new Salle();
        String request = "SELECT * FROM salle WHERE `NomSalle` = '"+NomSalle+"' ;";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                
                s.setID_salle(rs.getInt(1));
                s.setNomSalle(rs.getString(2));
                s.setAdresse(rs.getString(3));
                s.setCapacite(rs.getInt(4));
                s.setNumTel_salle(rs.getString(5));
                s.setEmail_Salle(rs.getString(6));
                s.setTemps_Ouverture(rs.getString(7));
                s.setTemps_Fermuture(rs.getString(8));
                s.setAvis(rs.getFloat(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
        
    }
    
     @Override
    public Salle GetSalleByAdresse(String Adresse){
    Salle s = new Salle();
        String request = "SELECT * FROM salle WHERE `Adresse` = '"+Adresse+"' ;";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                
                s.setID_salle(rs.getInt(1));
                s.setNomSalle(rs.getString(2));
                s.setAdresse(rs.getString(3));
                s.setCapacite(rs.getInt(4));
                s.setNumTel_salle(rs.getString(5));
                s.setEmail_Salle(rs.getString(6));
                s.setTemps_Ouverture(rs.getString(7));
                s.setTemps_Fermuture(rs.getString(8));
                s.setAvis(rs.getFloat(9));
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
        
    
    
    }
    
    
    
}
