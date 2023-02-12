package myjcc;

import Models.Prix;
import Models.Vote;
import Services.PrixService;
import Services.VoteService;
import Utils.MaConnection;
import java.sql.Connection;

public class Myjcc {

    public static void main(String[] args) {
        // just pour test de connection : tjik fel console --> Connection etablie avec succes !!!
        //ken c bon naheha w hotha fel service
        
        //crud prix
        Connection cnx = MaConnection.getInstance().getCnx();
        PrixService ps = new PrixService();
        Prix p = new Prix(1111, "or");
        //ps.ajouterPrix(p);
        //ps.afficherPrix().forEach(System.out::println);
        //ps.afficherPrixType("Bronze").forEach(System.out::println);
        //ps.afficherPrixFilm(15).forEach(System.out::println);
        //ps.suppressionPrix(15);
        
        
        
        //crud vote
        VoteService vs = new VoteService();
        Vote v = new Vote(19,8);
        //vs.ajouterVote(v);
        //vs.afficherVote().forEach(System.out::println);
        //vs.afficherVoteUser(777).forEach(System.out::println);
        //vs.afficherVoteFilm(19).forEach(System.out::println);
        //vs.suppressionVote(19);
        
    
    }
    
}
