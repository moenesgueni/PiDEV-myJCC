package Services;

import Interfaces.PhotographieInterface;
import Models.Photographie;
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

public class PhotographieService implements PhotographieInterface{
    //Connection a la db
    Connection cnx = MaConnection.getInstance().getCnx();

    //Create
    @Override
    public void ajouterPhotographie(Photographie p) {
        String req = "INSERT INTO `photographie`(`Nom`, `Description`, "
                + "`PhotographieB64`, `ID_Galerie`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getPhotographieB64());
            ps.setInt(4, p.getID_Galerie());
            //
            ps.executeUpdate();
            System.out.println("Nouvelle Photographie Ajoute avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Read : Afficher toutes les Photographies
    @Override
    public List<Photographie> afficherPhotographies() {
        List<Photographie> photographies = new ArrayList<>();
        String request ="SELECT * FROM `photographie`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                Photographie p = new Photographie();
                p.setID_Photographie(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPhotographieB64(rs.getString(4));
                p.setID_Galerie(rs.getInt(5));
                //
                photographies.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photographies;
    }

    //GetById : Afficher une photographie
    @Override
    public Photographie afficherPhotographie(int id) {
        Photographie p = new Photographie();
        String request ="SELECT * FROM `photographie` WHERE `ID_Photographie` ="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                p.setID_Photographie(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPhotographieB64(rs.getString(4));
                p.setID_Galerie(rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    //Filter : Afficher les Photographies d'un Photographe
    @Override
    public List<Photographie> afficherPhotographiesDunPhotographe(int id) {
        List<Photographie> photographies = new ArrayList<>();
        String request ="SELECT * FROM `photographie` WHERE `ID_Galerie` ="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                Photographie p = new Photographie();
                p.setID_Photographie(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPhotographieB64(rs.getString(4));
                p.setID_Galerie(rs.getInt(5));
                //
                photographies.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photographies;
    }

    //Update
    @Override
    public void modifierPhotographie(Photographie p) {
        String request = "UPDATE `photographie` SET `Nom`= ?,`Description`= ?"
                + ",`PhotographieB64`= ?,`ID_Galerie`= ? WHERE `ID_Photographie`= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getPhotographieB64());
            ps.setInt(4, p.getID_Galerie());
            ps.setInt(5, p.getID_Photographie());
            //
            ps.executeUpdate();
            System.out.println("Photographie modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Delete
    @Override
    public void SupprimerPhotographie(int id) {
        String request = "DELETE FROM `photographie` WHERE `ID_Photographie`= ?";
        Statement st;
       try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, id);
            //
            ps.executeUpdate();
            System.out.println("Photographie supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
