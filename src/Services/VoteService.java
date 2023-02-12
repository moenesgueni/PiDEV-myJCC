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
    
    

    //ajout vote
    @Override
    public void ajouterVote(Vote v) {
        Calendar calendar = Calendar.getInstance();
        Date today = (Date) calendar.getTime(); ///////// condition de date pour terminer le vote
        
    
        String req = "INSERT INTO `vote`(`ID_Film`, `ID_User`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1, v.getID_Vote());
            ps.setInt(1, v.getID_Film());
            ps.setInt(2, v.getID_User());
            ps.executeUpdate();
            System.out.println("Vote ajouté avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    
    //afficher tout
    @Override
    public List<Vote> afficherVote() {
        List<Vote> votes = new ArrayList<>();
        String request = "SELECT * FROM vote";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                Vote v = new Vote();
                v.setID_Vote(rs.getInt(1));
                v.setID_Film(rs.getInt(2));
                v.setID_User(rs.getInt(3));
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
        List<Vote> prixs = new ArrayList<>();
        Vote p = new Vote();
        String request = "SELECT * FROM vote WHERE ID_User = ? ;";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, VoteUser);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p.setID_Vote(rs.getInt(1));
                p.setID_User(rs.getInt(2));
                p.setID_Film(rs.getInt(3));
                
                prixs.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prixs;
    }

    
    //affichage par film
    @Override
    public List<Vote> afficherVoteFilm(int VoteFilm) {
        List<Vote> prixs = new ArrayList<>();
        Vote p = new Vote();
        String request = "SELECT * FROM vote WHERE ID_Film = ? ;";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, VoteFilm);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p.setID_Vote(rs.getInt(1));
                p.setID_User(rs.getInt(2));
                p.setID_Film(rs.getInt(3));
                
                prixs.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prixs;
    }

    @Override
    public void suppressionVote(int VoteFilm) {
        String request = "DELETE FROM vote WHERE ID_Film = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, VoteFilm);
            ps.executeUpdate();
            System.out.println("Vote supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    
    
}
