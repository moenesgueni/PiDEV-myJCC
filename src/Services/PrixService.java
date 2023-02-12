/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.PrixInterface;
import Models.Prix;
import Utils.MaConnection;
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
 * @author wael
 */
public class PrixService implements PrixInterface{

    Connection cnx = MaConnection.getInstance().getCnx();
    
    @Override
    //ajout Prix
    public void ajouterPrix(Prix p) {
        String req = "INSERT INTO `prix`(`ID_Film`, `TypePrix`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1, p.getID_Prix());
            ps.setInt(1, p.getID_Film());
            ps.setString(2, p.getTypePrix());
            ps.executeUpdate();
            System.out.println("Prix ajouté avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //affiche tout
    @Override
    public List<Prix> afficherPrix() {
        List<Prix> prixs = new ArrayList<>();
        String request = "SELECT * FROM prix";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                Prix p = new Prix();
                p.setID_Prix(rs.getInt(1));
                p.setID_Film(rs.getInt(2));
                p.setTypePrix(rs.getString("TypePrix"));
                //
                prixs.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prixs;
    }

    
    //affichage par Type Prix
    @Override
    public List<Prix> afficherPrixType(String PrixType) {
        List<Prix> prixs = new ArrayList<>();
        Prix p = new Prix();
        String request = "SELECT * FROM prix WHERE TypePrix = ? ;";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, PrixType);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p.setID_Prix(rs.getInt(1));
                p.setID_Film(rs.getInt(2));
                p.setTypePrix(rs.getString("TypePrix"));
                
                prixs.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prixs;
    }

    
    //affichage par film
    @Override
    public List<Prix> afficherPrixFilm(int PrixFilm) {
        List<Prix> prixs = new ArrayList<>();
        Prix p = new Prix();
        String request = "SELECT * FROM prix WHERE ID_Film = ? ;";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, PrixFilm);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p.setID_Prix(rs.getInt(1));
                p.setID_Film(rs.getInt(2));
                p.setTypePrix(rs.getString("TypePrix"));
                
                prixs.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prixs;
    }
    
    
    @Override
    public void modifierPrixFilm(int prixId , int prixFilm) {
        String request = "UPDATE prix SET ID_Film = ?"
                +" WHERE ID_Prix = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(2, prixId);
            ps.setInt(1, prixFilm);
            ps.executeUpdate();
            System.out.println("ID_Film modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void modifierPrixType(int prixId, String prixType) {
        String request = "UPDATE prix SET TypePrix = ?"
                +" WHERE ID_Prix = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(2, prixId);
            ps.setString(1, prixType);
            ps.executeUpdate();
            System.out.println("TypePrix modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierPrixFilmType(int prixId, int prixFilm, String prixType) {
        String request = "UPDATE prix SET ID_Film = ? ,TypePrix = ?"
                +" WHERE ID_Prix = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(3, prixId);
            ps.setInt(1, prixFilm);
            ps.setString(2, prixType);
            ps.executeUpdate();
            System.out.println("TypePrix et ID_Film modifiés avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void suppressionPrixFilm(int PrixFilm) {
        String request = "DELETE FROM prix WHERE ID_Film = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, PrixFilm);
            ps.executeUpdate();
            System.out.println("Film supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void suppressionPrixType(String PrixType) {
        String request = "DELETE FROM prix WHERE TypePrix = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, PrixType);
            ps.executeUpdate();
            System.out.println("prix supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    
    
}
