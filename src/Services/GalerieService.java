package Services;

import Interfaces.GalerieInterface;
import Models.Galerie;
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

public class GalerieService implements GalerieInterface {

    //Connection a la db
    Connection cnx = MaConnection.getInstance().getCnx();
    
    //creation service user
    UserService ps = new UserService();

    //Méthode création Galerie g utilisé lors des méthodes afficher
    private Galerie addGalerie(ResultSet rs) {
        Galerie g = new Galerie();
        try {
            g.setID_Galerie(rs.getInt(1));
            g.setCouleurHtml(rs.getString(2));
            g.setNom(rs.getString(3));
            g.setDescription(rs.getString(4));
            g.setPhotographe(ps.afficherUserbyID(rs.getInt(5)));
        } catch (SQLException ex) {
            Logger.getLogger(PhotographieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }

    //Create
    @Override
    public void ajouterGalerie(Galerie g) {
        String req = "INSERT INTO `galerie`(`couleurHtml`, `Nom`, `Description`, `ID_Photographe`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, g.getCouleurHtml());
            ps.setString(2, g.getNom());
            ps.setString(3, g.getDescription());
            ps.setInt(4, g.getPhotographe().getID_User());
            //
            ps.executeUpdate();
            System.out.println("Nouvelle Galerie Ajoute avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Read : Afficher toutes les galeries
    @Override
    public List<Galerie> afficherGaleries() {
        List<Galerie> galeries = new ArrayList<>();
        String request = "SELECT * FROM `galerie`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                Galerie g = addGalerie(rs);
                //
                galeries.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return galeries;
    }

    //GetById : Afficher une Galerie
    @Override
    public Galerie afficherGalerie(int id) {
        Galerie g = new Galerie();
        String request = "SELECT * FROM `galerie` WHERE `ID_Galerie` =" + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                g = addGalerie(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }

    //Delete
    @Override
    public void modifierGalerie(Galerie g) {
        String request = "UPDATE `galerie` SET `couleurHtml`= ?, `Nom`= ?,`Description`= ?,"
                + "`ID_Photographe`= ? WHERE `ID_Galerie`= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, g.getCouleurHtml());
            ps.setString(2, g.getNom());
            ps.setString(3, g.getDescription());
            ps.setInt(4, g.getPhotographe().getID_User());
            ps.setInt(5, g.getID_Galerie());
            //
            ps.executeUpdate();
            System.out.println("Galerie modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Delete
    @Override
    public void supprimerGalerie(int id) {
        String request = "DELETE FROM `galerie` WHERE `ID_Galerie`= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, id);
            //
            ps.executeUpdate();
            System.out.println("Galerie supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
