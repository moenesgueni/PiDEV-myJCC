package Services;

import Interfaces.SponsoringInterface;
import Models.ContratSponsoring;
import Utils.EnumTypeContrat;
import Utils.EnumEtatContrat;
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

public class ContratSponsorinService implements SponsoringInterface {

    //Connection a la db
    Connection cnx = MaConnection.getInstance().getCnx();
    //creation service user
    UserService ps = new UserService();

    //Méthode création ContratSponsoring c utilisé lors des méthodes afficher
    private ContratSponsoring addContratSponsoring(ResultSet rs) {
        ContratSponsoring c = new ContratSponsoring();
        try {
            c.setID_Contrat(rs.getInt(1));
            c.setDateDebut(rs.getDate(2));
            c.setDateFin(rs.getDate(3));
            c.setType(EnumTypeContrat.valueOf(rs.getString(4)));
            c.setEtat(EnumEtatContrat.valueOf(rs.getString(5)));
            c.setSalaireDt(rs.getFloat(6));
            c.setTermesPDF(rs.getString(7));
            c.setSponsor(ps.afficherUserbyID(rs.getInt(8)));
            c.setPhotoraphe(ps.afficherUserbyID(rs.getInt(9)));
        } catch (SQLException ex) {
            Logger.getLogger(PhotographieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    //Create : creation d'une proposition de contrat
    @Override
    public void ajouterContratSponsorin(ContratSponsoring c) {
        String req = "INSERT INTO `contratsponsoring`(`DateDebut`, `DateFin`, `Type`, `Etat`, `SalaireDt`, `TermesPDF`, `ID_Sponsor`, `ID_Photographe`) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, c.getDateDebut());
            ps.setDate(2, c.getDateFin());
            ps.setString(3, c.getType().toString());
            ps.setString(4, c.getEtat().toString());
            ps.setFloat(5, c.getSalaireDt());
            ps.setString(6, c.getTermesPDF());
            ps.setInt(7, c.getSponsor().getID_User());
            ps.setInt(8, c.getPhotoraphe().getID_User());
            //
            ps.executeUpdate();
            System.out.println("Nouveau Contrat Ajoute avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Read : Affichage de tout les contrats
    @Override
    public List<ContratSponsoring> afficherContratsSponsorin() {
        List<ContratSponsoring> contratsSponsoring = new ArrayList<>();
        String request = "SELECT * FROM contratsponsoring";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                ContratSponsoring c = addContratSponsoring(rs);
                //
                contratsSponsoring.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contratsSponsoring;
    }

    //GetById : Affichage d'un contrat
    @Override
    public ContratSponsoring afficherContratSponsoring(int id) {
        ContratSponsoring c = new ContratSponsoring();
        String request = "SELECT * FROM contratsponsoring WHERE `ID_Contrat` =" + id + ";";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                c = addContratSponsoring(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    //Filter : Affichage les Contrats d'un sponsor
    @Override
    public List<ContratSponsoring> afficherContratsDeSponsor(int id) {
        List<ContratSponsoring> contratsSponsoring = new ArrayList<>();
        String request = "SELECT * FROM contratsponsoring WHERE ID_Sponsor = " + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                ContratSponsoring c = addContratSponsoring(rs);
                //
                contratsSponsoring.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contratsSponsoring;
    }

    //Filter : Affichage les Contrats d'un photographe
    @Override
    public List<ContratSponsoring> afficherContratsDephotographe(int id) {
        List<ContratSponsoring> contratsSponsoring = new ArrayList<>();
        String request = "SELECT * FROM contratsponsoring WHERE ID_Photographe = " + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                ContratSponsoring c = addContratSponsoring(rs);
                //
                contratsSponsoring.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contratsSponsoring;
    }

    //Update : Changer les details du contrat
    @Override
    public void modifierContratSponsoring(ContratSponsoring c) {
        String request = "UPDATE contratsponsoring SET DateDebut = ?, DateFin = ?, Type = ?, Etat = ?, SalaireDt = ?, TermesPDF = ?, ID_Sponsor = ?, ID_Photographe = ?"
                + " WHERE ID_Contrat = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setDate(1, c.getDateDebut());
            ps.setDate(2, c.getDateFin());
            ps.setString(3, c.getType().toString());
            ps.setString(4, c.getEtat().toString());
            ps.setFloat(5, c.getSalaireDt());
            ps.setString(6, c.getTermesPDF());
            ps.setInt(7, c.getSponsor().getID_User());
            ps.setInt(8, c.getPhotoraphe().getID_User());
            ps.setInt(9, c.getID_Contrat());
            //
            ps.executeUpdate();
            System.out.println("Contrat modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Delete : Supprimer un contrat
    @Override
    public void supprimerContratSponsoring(int id) {
        String request = "DELETE FROM contratsponsoring WHERE ID_Contrat = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, id);
            //
            ps.executeUpdate();
            System.out.println("Contrat supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
