package Services;

import Interfaces.VoteInterface;
import Models.Vote;
import Utilities.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import sun.security.util.Length;

/**
 *
 * @author wael
 */
public class VoteService implements VoteInterface {

    Connection cnx = MaConnexion.getInstance().getCnx();
    Vote v = new Vote();
    PrixService pss = new PrixService();
    FilmService fs = new FilmService();
    UserService us = new UserService();

    //ajout rate
    @Override
    public void ajouterVote(Vote v) {
        Calendar calendar = Calendar.getInstance();
        Date today = (Date) calendar.getTime(); ///////// condition de date pour terminer le vote

        String req = "INSERT INTO `vote`(`Valeur`,`ID_User` , `ID_Film` ,`Commentaire`, `Date_Vote` , `Vote_Film`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1, v.getID_Vote());
            ps.setInt(1, v.getValeur());
            ps.setInt(2, v.getUser().getID_User());
            ps.setInt(3, v.getFilm().getID_film());
            ps.setString(4, v.getCommentaire());
            ps.setDate(5, v.getDate_Vote());
            ps.setInt(6, 0);
            ps.executeUpdate();
            System.out.println("Vote ajouté avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            //System.out.println("le cle composé ID_Film et ID_User ne doit d'etre repeté");
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /////////////////////////////////////////////////////update
    
    public void updateVoteForUser(int userId,int filmId , int newValeur) {
        
        try {
            // SQL query to update the vote for the given user
            String req = "UPDATE vote SET Valeur = ? WHERE ID_User = ? AND ID_Film = ?";

            // Create prepared statement and set parameters
            try (PreparedStatement st = cnx.prepareStatement(req)) {
                st.setInt(1, newValeur);
                st.setInt(2, userId);
                st.setInt(3, filmId);

                // Execute update and get number of rows affected
                int rowsUpdated = st.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Vote updated successfully.");
                } else {
                    System.out.println("No vote found for user " + userId + ".");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating vote: " + e.getMessage());
        }
    }
    
    ////////////////////////////////////////////// Ajout vote
    public void ajouterVote1(Vote v) {
        Calendar calendar = Calendar.getInstance();
        Date today = (Date) calendar.getTime(); ///////// condition de date pour terminer le vote

        // Check if the user has already voted for a film
        String query = "SELECT * FROM vote WHERE ID_User = ? AND ID_Film <> ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, v.getUser().getID_User());
            ps.setInt(2, v.getFilm().getID_film());
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                // The user has already voted for a different film, so subtract -1 from the previous film's vote count
                int previousFilmId = result.getInt("ID_Film");
                query = "UPDATE vote SET Vote_Film = Vote_Film - 1 WHERE ID_User = ? AND ID_Film = ?";
                ps = cnx.prepareStatement(query);
                ps.setInt(1, v.getUser().getID_User());
                ps.setInt(2, previousFilmId);
                ps.executeUpdate();
                
                int x = 0;
                // Delete the previous vote from the "vote" table
                query = "DELETE FROM vote WHERE ID_User = ? AND ID_Film = ?";
                ps = cnx.prepareStatement(query);
                ps.setInt(1, v.getUser().getID_User());
                ps.setInt(2, previousFilmId);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            //System.out.println("Error while checking for previous votes.");
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        // Add +1 to the new film's vote count
        query = "UPDATE vote SET Vote_Film = Vote_Film + 1 WHERE ID_film = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, v.getFilm().getID_film());
            ps.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println("Error while updating film's vote count.");
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        // Add the new vote to the "vote" table
        query = "INSERT INTO vote (Valeur , ID_User, ID_Film, Commentaire,Date_Vote, Vote_Film) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setInt(2, v.getUser().getID_User());
            ps.setInt(3, v.getFilm().getID_film());
            ps.setString(4, v.getCommentaire());
            ps.setDate(5, v.getDate_Vote());
            ps.setInt(6, v.getVote_Film());
            ps.executeUpdate();
            /*
            ps.setInt(1, v.getValeur());
            ps.setInt(2, v.getUser().getID_User());
            ps.setInt(3, v.getFilm().getID_film());
            ps.setString(4, v.getCommentaire());
            ps.setDate(5, v.getDate_Vote());
            */
            System.out.println("Vote ajouté avec succès via prepared Statement!!!");
        } catch (SQLException ex) {
            //System.out.println("Error while adding the new vote to the database.");
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
    /////////////////////////////////////////////////////update1
    
    public void updateVoteForUser1(int userId, int newVoteValue) {
        
        try {
            // SQL query to update the vote for the given user
            String req = "UPDATE vote SET Vote_Film = ? WHERE ID_User = ?";
            try (PreparedStatement st = cnx.prepareStatement(req)) {
                st.setInt(1, newVoteValue);
                st.setInt(2, userId);

                // Execute update and get number of rows affected
                int rowsUpdated = st.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Vote updated successfully.");
                } else {
                    System.out.println("No vote found for user " + userId + ".");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating vote: " + e.getMessage());
        }
    }


    /////////////////////////////////////////////////////getbyid
    @Override
    public Vote afficherVote(int userID, int filmID) {

        String request = "SELECT * FROM vote WHERE ID_User =" + userID + " and ID_Film = " + filmID;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {

                v.setValeur(rs.getInt(1));
                v.setUser(us.afficherUserbyID(rs.getInt(2)));
                v.setFilm(fs.GetFilmById(rs.getInt(3)));
                v.setCommentaire(rs.getString(4));
                v.setDate_Vote(rs.getDate(5));
                v.setVote_Film(rs.getInt(6));
                //
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;

    }
    
    public boolean afficherVoteee(int userID, int filmID) {
        boolean t = true ;
        String request = "SELECT * FROM vote WHERE ID_User =" + userID + " and ID_Film = " + filmID;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {

                return t;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

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
            while (rs.next()) {
                Vote v = new Vote();
                v.setValeur(rs.getInt(1));
                v.setUser(us.afficherUserbyID(rs.getInt(2)));
                v.setFilm(fs.GetFilmById(rs.getInt(3)));
                v.setCommentaire(rs.getString(4));
                v.setDate_Vote(rs.getDate(5));
                v.setVote_Film(rs.getInt(6));
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
    public List<Vote> afficherVoteUser(String emailUser) {
        List<Vote> votes = new ArrayList<>();

        String request = "SELECT * FROM vote v Join user u ON v.ID_User = u.ID_User where u.Email ='" + emailUser + "';";///////////////////////////
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                v.setValeur(rs.getInt(1));
                v.setUser(us.afficherUserbyID(rs.getInt(2)));
                v.setFilm(fs.GetFilmById(rs.getInt(3)));
                v.setCommentaire(rs.getString(4));
                v.setDate_Vote(rs.getDate(5));
                v.setVote_Film(rs.getInt(6));

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

        String request = "SELECT * FROM vote v Join film f ON v.ID_Film = f.ID_film where f.Titre ='" + TitreFilm + "';";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                v.setValeur(rs.getInt(1));
                v.setUser(us.afficherUserbyID(rs.getInt(2)));
                v.setFilm(fs.GetFilmById(rs.getInt(3)));
                v.setCommentaire(rs.getString(4));
                v.setDate_Vote(rs.getDate(5));
                v.setVote_Film(rs.getInt(6));

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
    //javaFX
    public void suppressionVoteTitreFilm(String Titre) {
        String request = "Delete vote from vote Right Join film ON vote.ID_Film = film.ID_film where film.Titre ='" + Titre + "';";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.executeUpdate();
            System.out.println("Vote supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Vote> afficherVoteUserFilm(int idu, int idf) {
        List<Vote> votes = new ArrayList<>();

        //String request = "SELECT * FROM vote v Join user u ON v.ID_User = u.ID_User where u.ID_User = " + idu + " join film f on v.ID_Film = f.ID_film where f.ID_film = " + idf + ';';
///////////////////////////                                             "SELECT * FROM vote where ID_User = " +idu+" and ID_Film = "+idf+" ;"
        String request = "SELECT * FROM user u ,film f,vote v where v.ID_Film=f.ID_film and v.ID_User=u.ID_User and v.ID_User = " + idu + " and v.ID_Film = " + idf + ";";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                //System.out.println(rs.getInt("ID_Vote"));
                v.setValeur(rs.getInt("Valeur"));
                v.setUser(us.afficherUserbyID(rs.getInt("ID_User")));
                v.setFilm(fs.GetFilmById(rs.getInt("ID_Film")));
                v.setCommentaire(rs.getString("Commentaire"));
                v.setDate_Vote(rs.getDate("Date_Vote"));
                v.setVote_Film(rs.getInt("Vote_Film"));
                votes.add(v);
                //rs.next();
                //System.out.println(rs.getInt("ID_Vote"));
                //System.out.println(rs.getInt("ID_User"));
                //System.out.println(rs.getInt(3));
                //System.out.println(rs.getDate(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes;
    }

    //////////////////////////////////////////////// pour rating
    public boolean canVote(int idu, int idf) {
        String request = "SELECT * FROM user u ,film f,vote v where v.ID_Film=f.ID_film and v.ID_User=u.ID_User and v.ID_User = " + idu + " and v.ID_Film = " + idf + ";";
        boolean a = true;

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                a = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public void voteee(Vote v) {
        if (canVote(v.getUser().getID_User(), v.getFilm().getID_film())) {
            ajouterVote(v);

        } else {
            System.out.println("vous avez deja voté ce film");

        }
    }

    //////////////////////////////////////////////// pour vote
    public boolean canVote1(int idu, int idf) {
        String request = "SELECT * FROM user u ,film f,vote v where v.ID_Film=f.ID_film and v.ID_User=u.ID_User and v.ID_User = " + idu + " and v.ID_Film = " + idf + ";";
        boolean a = true;

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                a = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public void voteee1(Vote v) {
        if (canVote(v.getUser().getID_User(), v.getFilm().getID_film())) {
            ajouterVote1(v);

        } else {
            System.out.println("vous avez deja voté ce film");

        }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    public int afficherVotesDate() {
        //List<Vote> votes = new ArrayList<>();
        String dateString = "";
        String datee = "";
        int result = 0;
        String request = "SELECT * FROM vote";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // specify your desired date format

            while (rs.next()) {
                Date date = rs.getDate("Date_Vote"); // get the date field from the current row
                dateString = dateFormat.format(date); // format the date using SimpleDateFormat
                // System.out.println(dateString);
                if (dateString.equals("03/01/2023")) {
                    result = countVote(date);
                    //System.out.println(countVote(date));
                    //System.out.println(datee);
                }/*else if (dateString.equals("01/02/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/03/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/04/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/05/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/06/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/07/2023")){
                    result = countVote(date);
                }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public int afficherVotesDateJeudi() {
        //List<Vote> votes = new ArrayList<>();
        String dateString = "";
        String datee = "";
        int result = 0;
        String request = "SELECT * FROM vote";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // specify your desired date format

            while (rs.next()) {
                Date date = rs.getDate("Date_Vote"); // get the date field from the current row
                dateString = dateFormat.format(date); // format the date using SimpleDateFormat
                // System.out.println(dateString);
                if (dateString.equals("03/02/2023")) {
                    result = countVote(date);
                    //System.out.println(countVote(date));
                    //System.out.println(datee);
                }/*else if (dateString.equals("01/02/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/03/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/04/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/05/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/06/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/07/2023")){
                    result = countVote(date);
                }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public int afficherVotesDateVendredi() {
        //List<Vote> votes = new ArrayList<>();
        String dateString = "";
        String datee = "";
        int result = 0;
        String request = "SELECT * FROM vote";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // specify your desired date format

            while (rs.next()) {
                Date date = rs.getDate("Date_Vote"); // get the date field from the current row
                dateString = dateFormat.format(date); // format the date using SimpleDateFormat
                // System.out.println(dateString);
                if (dateString.equals("03/03/2023")) {
                    result = countVote(date);
                    //System.out.println(countVote(date));
                    //System.out.println(datee);
                }/*else if (dateString.equals("01/02/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/03/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/04/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/05/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/06/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/07/2023")){
                    result = countVote(date);
                }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public int afficherVotesDateSamedi() {
        //List<Vote> votes = new ArrayList<>();
        String dateString = "";
        String datee = "";
        int result = 0;
        String request = "SELECT * FROM vote";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // specify your desired date format

            while (rs.next()) {
                Date date = rs.getDate("Date_Vote"); // get the date field from the current row
                dateString = dateFormat.format(date); // format the date using SimpleDateFormat
                // System.out.println(dateString);
                if (dateString.equals("03/04/2023")) {
                    result = countVote(date);
                    //System.out.println(countVote(date));
                    //System.out.println(datee);
                }/*else if (dateString.equals("01/02/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/03/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/04/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/05/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/06/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/07/2023")){
                    result = countVote(date);
                }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public int afficherVotesDateDimanche() {
        //List<Vote> votes = new ArrayList<>();
        String dateString = "";
        String datee = "";
        int result = 0;
        String request = "SELECT * FROM vote";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // specify your desired date format

            while (rs.next()) {
                Date date = rs.getDate("Date_Vote"); // get the date field from the current row
                dateString = dateFormat.format(date); // format the date using SimpleDateFormat
                // System.out.println(dateString);
                if (dateString.equals("03/05/2023")) {
                    result = countVote(date);
                    //System.out.println(countVote(date));
                    //System.out.println(datee);
                }/*else if (dateString.equals("01/02/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/03/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/04/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/05/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/06/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/07/2023")){
                    result = countVote(date);
                }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public int afficherVotesDateLundi() {
        //List<Vote> votes = new ArrayList<>();
        String dateString = "";
        String datee = "";
        int result = 0;
        String request = "SELECT * FROM vote";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // specify your desired date format

            while (rs.next()) {
                Date date = rs.getDate("Date_Vote"); // get the date field from the current row
                dateString = dateFormat.format(date); // format the date using SimpleDateFormat
                // System.out.println(dateString);
                if (dateString.equals("03/06/2023")) {
                    result = countVote(date);
                    //System.out.println(countVote(date));
                    //System.out.println(datee);
                }/*else if (dateString.equals("01/02/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/03/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/04/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/05/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/06/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/07/2023")){
                    result = countVote(date);
                }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public int afficherVotesDateMardi() {
        //List<Vote> votes = new ArrayList<>();
        String dateString = "";
        String datee = "";
        int result = 0;
        String request = "SELECT * FROM vote";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // specify your desired date format

            while (rs.next()) {
                Date date = rs.getDate("Date_Vote"); // get the date field from the current row
                dateString = dateFormat.format(date); // format the date using SimpleDateFormat
                // System.out.println(dateString);
                if (dateString.equals("03/07/2023")) {
                    result = countVote(date);
                    //System.out.println(countVote(date));
                    //System.out.println(datee);
                }/*else if (dateString.equals("01/02/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/03/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/04/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/05/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/06/2023")){
                    result = countVote(date);
                }else if (dateString.equals("01/07/2023")){
                    result = countVote(date);
                }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }
    //System.out.println(countVote(date));
    //System.out.println(datee);

    public int countVote(Date idF) {
        String request = "select sum(Valeur) from vote where Date_Vote = ' " + idF + " ' ;";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                int c = rs.getInt("sum(Valeur)");
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Integer> countStars() {
        ArrayList<Integer> liste = new ArrayList<Integer>();
        String request = "select count(Valeur) from vote where Valeur =  ";
        String a;
        for (int i = 1; i < 6; i++) {
            a = request + i + ";";
            try {
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(a);
                while (rs.next()) {
                    int c = rs.getInt("count(Valeur)");
                    liste.add(liste.size(), c);

                }
            } catch (SQLException ex) {
                Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return liste;

    }
    ArrayList<String> genre = fs.getAllGenres();

    public List<Pair<String, Integer>> countVoteFilmByType(ArrayList<String> genres) {
        List<Pair<String, Integer>> resultList = new ArrayList<>();
        String request = "SELECT f.genre, SUM(v.Vote_Film) as total_votes "
                + "FROM vote v "
                + "JOIN film f ON v.ID_Film = f.ID_Film "
                + "WHERE f.genre = ? "
                + "GROUP BY f.genre";
        try {
            PreparedStatement st = cnx.prepareStatement(request);
            for (String genre : genres) {
                st.setString(1, genre);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String filmGenre = rs.getString("genre");
                    int count = rs.getInt("total_votes");
                    resultList.add(new Pair<>(filmGenre, count));
                }
                rs.close();
            }
            Collections.sort(resultList, new Comparator<Pair<String, Integer>>() {
                @Override
                public int compare(Pair<String, Integer> p1, Pair<String, Integer> p2) {
                    return p2.getValue().compareTo(p1.getValue());
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                PreparedStatement st = cnx.prepareStatement(request);
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList;
    }

    public List<Pair<String, String>> getMostVotedFilmByGenre(ArrayList<String> genres) {
        List<Pair<String, String>> resultList = new ArrayList<>();
        String request = "SELECT f.genre, f.titre, SUM(v.Vote_Film) as total_votes "
                + "FROM film f "
                + "JOIN vote v ON v.ID_Film = f.ID_Film "
                + "GROUP BY f.genre, f.ID_Film "
                + "HAVING total_votes = "
                + "(SELECT SUM(v2.Vote_Film) "
                + "FROM vote v2 "
                + "WHERE v2.ID_Film = f.ID_Film "
                + "GROUP BY v2.ID_Film "
                + "ORDER BY SUM(v2.Vote_Film) DESC "
                + "LIMIT 1)";
        try {
            PreparedStatement st = cnx.prepareStatement(request);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String filmGenre = rs.getString("genre");
                String filmTitle = rs.getString("titre");
                resultList.add(new Pair<>(filmGenre, filmTitle));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                PreparedStatement st = cnx.prepareStatement(request);
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList;
    }

    public List<Pair<String, Integer>> getMostVotedFilmByGenre2(ArrayList<String> genres) {
        List<Pair<String, Integer>> resultList = new ArrayList<>();
        String request = "SELECT f.titre, SUM(v.Vote_Film) as total_votes "
                + "FROM film f "
                + "JOIN vote v ON v.ID_Film = f.ID_Film "
                + "WHERE f.genre = ? "
                + "GROUP BY f.ID_Film "
                + "HAVING total_votes = "
                + "(SELECT SUM(v2.Vote_Film) "
                + "FROM vote v2 "
                + "WHERE v2.ID_Film = f.ID_Film "
                + "GROUP BY v2.ID_Film "
                + "ORDER BY SUM(v2.Vote_Film) DESC "
                + "LIMIT 1)";
        try {
            PreparedStatement st = cnx.prepareStatement(request);
            for (String genre : genres) {
                st.setString(1, genre);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String filmTitle = rs.getString("titre");
                    int totalVotes = rs.getInt("total_votes");
                    resultList.add(new Pair<>(filmTitle, totalVotes));
                }
                rs.close();
            }
            // Sort the result list in descending order by the second element of each pair (total votes)
            Collections.sort(resultList, new Comparator<Pair<String, Integer>>() {
                @Override
                public int compare(Pair<String, Integer> p1, Pair<String, Integer> p2) {
                    return p2.getValue().compareTo(p1.getValue());
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                PreparedStatement st = cnx.prepareStatement(request);
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList;
    }

}
