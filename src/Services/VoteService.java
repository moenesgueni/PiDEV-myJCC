/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    

    //ajout vote
    @Override
    public void ajouterVote(Vote v) {
        Calendar calendar = Calendar.getInstance();
        Date today = (Date) calendar.getTime(); ///////// condition de date pour terminer le vote
        
    
        String req = "INSERT INTO `vote`(`ID_User`, `ID_Film` , `Date_Debut` , `Date_Fin`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1, v.getID_Vote());
            ps.setInt(1, v.getID_User());
            ps.setInt(2, v.getID_Film());
            ps.setDate(3, v.getDate_Debut());
            ps.setDate(4, v.getDate_Fin());
            ps.executeUpdate();
            System.out.println("Vote ajouté avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /////////////////////////////////////////////////////getbyid
    @Override
    public Vote afficherVote(int VoteID) {
        
        String request = "SELECT * FROM prix WHERE ID_Vote ="+VoteID;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                
                v.setID_Vote(rs.getInt(1));
                v.setID_Film(rs.getInt(2));
                v.setID_User(rs.getInt(3));
                v.setDate_Debut(rs.getDate(4));
                v.setDate_Fin(rs.getDate(5));
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
                v.setID_User(rs.getInt(2));
                v.setID_Film(rs.getInt(3));
                v.setDate_Debut(rs.getDate(4));
                v.setDate_Fin(rs.getDate(5));
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
    public List<Vote> afficherVoteUser(int VoteUser) {
        List<Vote> votes = new ArrayList<>();
        
        String request = "SELECT * FROM vote WHERE ID_User = ? ;";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, VoteUser);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                v.setID_Vote(rs.getInt(1));
                v.setID_User(rs.getInt(2));
                v.setID_Film(rs.getInt(3));
                v.setDate_Debut(rs.getDate(4));
                v.setDate_Fin(rs.getDate(5));
                
                votes.add(v);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes;
    }

    
    //affichage par film
    @Override
    public List<Vote> afficherVoteFilm(int VoteFilm) {
        List<Vote> votes = new ArrayList<>();
        
        String request = "SELECT * FROM vote WHERE ID_Film = ? ;";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, VoteFilm);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                v.setID_Vote(rs.getInt(1));
                v.setID_User(rs.getInt(2));
                v.setID_Film(rs.getInt(3));
                v.setDate_Debut(rs.getDate(4));
                v.setDate_Fin(rs.getDate(5));
                
                votes.add(v);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes;
    }
    
    
    @Override
    public void modifierVoteFilm(int voteId, int voteFilm) {
        
        String request = "UPDATE vote SET ID_Film = ?"
                +" WHERE ID_Vote = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(2, voteId);
            ps.setInt(1, voteFilm);
            ps.setDate(3, v.getDate_Debut());
            ps.setDate(4, v.getDate_Fin());
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
            ps.setDate(3, v.getDate_Debut());
            ps.setDate(4, v.getDate_Fin());
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
            ps.setDate(3, v.getDate_Debut());
            ps.setDate(4, v.getDate_Fin());
            ps.executeUpdate();
            System.out.println("ID_User et ID_Film modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @Override
    public void suppressionVoteFilm(int VoteFilm) {
        String request = "DELETE FROM vote WHERE ID_Film = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, VoteFilm);
            ps.executeUpdate();
            System.out.println("Film supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void suppressionVoteUser(int VoteUser) {
        String request = "DELETE FROM vote WHERE ID_User = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, VoteUser);
            ps.executeUpdate();
            System.out.println("User supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    

    

    
    
}
