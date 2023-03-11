package Services;

import Interfaces.PrixInterface;
import Models.Prix;
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
 * @author wael
 */
public class PrixService implements PrixInterface {

    Connection cnx = MaConnexion.getInstance().getCnx();
    FilmService fs = new FilmService();

    @Override
    //ajout Prix
    public void ajouterPrix(Prix p) {
        String req = "INSERT INTO `prix`(`ID_Film` , `TypePrix`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1, p.getID_Prix());
            ps.setInt(1, p.getFilm().getID_film());
            //ps.setString(2, );
            ps.setString(2, p.getTypePrix());
            ps.executeUpdate();
            System.out.println("Prix ajouté avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Prix afficherPrix(int PrixID) {
        Prix p = new Prix();
        String request = "SELECT * FROM prix WHERE ID_Prix =" + PrixID;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {

                p.setID_Prix(rs.getInt(1));
                p.setFilm(fs.GetFilmById(rs.getInt(2)));
                p.setTypePrix(rs.getString("TypePrix"));
                //
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    //affiche tout
    @Override
    public List<Prix> getAllPrix() {
        List<Prix> prixs = new ArrayList<>();
        String request = "SELECT * FROM prix";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                Prix p = new Prix();
                p.setID_Prix(rs.getInt(1));
                p.setFilm(fs.GetFilmById(rs.getInt(2)));
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
            while (rs.next()) {
                p.setID_Prix(rs.getInt(1));
                p.setFilm(fs.GetFilmById(rs.getInt(2)));
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
    public Prix afficherTitreFilm(String TitreFilm) {
        Prix p = new Prix();
        String request = "SELECT * FROM prix p Join film f ON p.ID_Film = f.ID_film where f.Titre ='" + TitreFilm + "';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                p.setID_Prix(rs.getInt(1));
                p.setFilm(fs.GetFilmById(rs.getInt(2)));
                p.setTypePrix(rs.getString("TypePrix"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public void suppressionPrix(int ID) {
        String request = "DELETE FROM prix WHERE ID_Prix = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, ID);
            ps.executeUpdate();
            System.out.println("Prix supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierPrix(Prix p) {
        String req = "UPDATE prix SET TypePrix = ?"
                + " WHERE ID_Prix = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getTypePrix());
            ps.setInt(2, p.getID_Prix());

            ps.executeUpdate();
            System.out.println(" Prix Modifiee avec success !!!");
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifierPrixTitre(Prix p, Prix px) {
        String req = "UPDATE prix SET TypePrix = ?"
                + " WHERE TypePrix = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getTypePrix());
            ps.setString(2, px.getTypePrix());

            ps.executeUpdate();
            System.out.println(" Prix Modifiee avec success !!!");
        } catch (SQLException ex) {
            Logger.getLogger(PrixService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void suppressionPrixTitreFilm(String Titre) {////////////////
        String request = "Delete prix from prix Right Join film ON prix.ID_Film = film.ID_film where film.Titre ='"+Titre+"';";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.executeUpdate();
            System.out.println("Vote supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
