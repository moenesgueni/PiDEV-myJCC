package Services;

import Interfaces.VoteInterface;
import Models.Vote;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wael
 */
public class VoteService implements VoteInterface{

    Connection cnx = MaConnection.getInstance().getCnx();
    Vote v = new Vote();
    PrixService pss = new PrixService();
    FilmService fs = new FilmService();
    UserService us = new UserService();
    

    //ajout vote
    @Override
    public void ajouterVote(Vote v) {
        Calendar calendar = Calendar.getInstance();
        Date today = (Date) calendar.getTime(); ///////// condition de date pour terminer le vote
        
    
        String req = "INSERT INTO `vote`(`ID_User` , `ID_Film` , `Date_Vote`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1, v.getID_Vote());
            ps.setInt(1, v.getUser().getID_User());
            ps.setInt(2, v.getFilm().getID_film());
            ps.setDate(3, v.getDate_Vote());
            ps.executeUpdate();
            System.out.println("Vote ajouté avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /////////////////////////////////////////////////////getbyid
    @Override
    public Vote afficherVote(int VoteID) {
        
        String request = "SELECT * FROM vote WHERE ID_Vote =" +VoteID ;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                
                v.setID_Vote(rs.getInt(1));
                v.setUser(us.afficherUserbyID(rs.getInt(2)));              
                v.setFilm(fs.GetFilmById(rs.getInt(3)));
                v.setDate_Vote(rs.getDate(4));
                //
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
        
    }
    //////////////////////////////////////
    //afficher tout
    @Override
    public List<Vote> afficherVotes() {
        List<Vote> votes = new ArrayList<>();
        String request = "SELECT * FROM vote";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                Vote v = new Vote();
                v.setID_Vote(rs.getInt(1));
                v.setUser(us.afficherUserbyID(rs.getInt(2)));            
                v.setFilm(fs.GetFilmById(rs.getInt(3)));
                v.setDate_Vote(rs.getDate(4));
                //
                votes.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes;
        
    }

    
    //affichage par user
    @Override
    public List<Vote> afficherVoteUser(String RoleUser) {
        List<Vote> votes = new ArrayList<>();
        
        String request = "SELECT * FROM vote v Join user u ON v.ID_User = u.ID_User where u.Prenom ='"+RoleUser+"';";///////////////////////////
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                v.setID_Vote(rs.getInt(1));
                v.setUser(us.afficherUserbyID(rs.getInt(2)));                
                v.setFilm(fs.GetFilmById(rs.getInt(3)));
                v.setDate_Vote(rs.getDate(4));
                
                votes.add(v);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes;
    }

    
    //affichage par film
    @Override
    public List<Vote> afficherVoteFilm(String TitreFilm) {
        List<Vote> votes = new ArrayList<>();
        
        String request = "SELECT * FROM vote v Join film f ON v.ID_Film = f.ID_film where f.Titre ='"+TitreFilm+"';";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                v.setID_Vote(rs.getInt(1));
                v.setUser(us.afficherUserbyID(rs.getInt(2)));                
                v.setFilm(fs.GetFilmById(rs.getInt(3)));
                v.setDate_Vote(rs.getDate(4));
                
                votes.add(v);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes;
    }
    
    
    /*@Override
    public void modifierVoteFilm(Prix prix) {
        
        String request = "UPDATE vote SET TypePrix = ?"
                +" WHERE ID_Vote = ?";
        try {
            Vote v = new Vote();
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, v.getID_());
            ps.setInt(2, v.getID_Vote());
            ps.executeUpdate();
            System.out.println("ID_Film modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierVoteType(int voteId, int voteUser) {
        String request = "UPDATE vote SET ID_User = ?"
                +" WHERE ID_Vote = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(2, voteId);
            ps.setInt(1, voteUser);
            ps.executeUpdate();
            System.out.println("ID_User modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierVoteFilmType(int voteId, int voteUser, int voteFilm) {
        String request = "UPDATE vote SET ID_User = ? , ID_Film = ?"
                +" WHERE ID_Vote = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(3, voteId);
            ps.setInt(2, voteFilm);
            ps.setInt(1, voteUser);
            ps.executeUpdate();
            System.out.println("ID_User et ID_Film modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    

    @Override
    public void suppressionVoteFilm(int ID_Vote) {
        String request = "DELETE FROM vote WHERE ID_Vote = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, ID_Vote);
            ps.executeUpdate();
            System.out.println("Vote supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //javaFX
    public void suppressionVoteTitreFilm(String Titre) {
        String request = "Delete vote from vote Right Join film ON vote.ID_Film = film.ID_film where film.Titre ='"+Titre+"';";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.executeUpdate();
            System.out.println("Vote supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    }

    

    

    

    
    
