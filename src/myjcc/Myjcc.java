package myjcc;

import Models.Prix;
import Models.Vote;
import Services.PrixService;
import Services.VoteService;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;

public class Myjcc {

    public static void main(String[] args) {
        // just pour test de connection : tjik fel console --> Connection etablie avec succes !!!
        //ken c bon naheha w hotha fel service
        
        //crud prix
        Connection cnx = MaConnection.getInstance().getCnx();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 12);
        Date Date_Debut = new Date(calendar.getTimeInMillis());
        calendar.set(2023, Calendar.JULY, 24);
        Date Date_Fin = new Date(calendar.getTimeInMillis());
        
        
        PrixService ps = new PrixService();
        VoteService vs = new VoteService();
        Vote v = new Vote(19,8,Date_Debut,Date_Fin);
        Prix p = new Prix(1231, "or",v);
        //vs.ajouterVote(v);
        //ps.ajouterPrix(p);
        
        ps.afficherPrix().forEach(System.out::println);
        //ps.afficherPrixType("Bronze").forEach(System.out::println);
        //ps.afficherPrixFilm(15).forEach(System.out::println);
        //ps.modifierPrix(15, 7);
        //ps.modifierPrixType(15, "Argent");
        //ps.modifierPrixFilmType(15, 100, "Or");
        //ps.suppressionPrixFilm(15);
        //ps.suppressionPrixType("Bronze");
        
        
        
        
        
        //crud vote
        //VoteService vs = new VoteService();
        //Vote v2 = new Vote(19,8,Date_Debut,Date_Fin);
        //vs.ajouterVote(v);
        //vs.afficherVotes().forEach(System.out::println);
        //vs.afficherVoteUser(777).forEach(System.out::println);
        //vs.afficherVoteFilm(19).forEach(System.out::println);
        //vs.modifierVoteFilm(10 , 11111);
        //vs.modifierVoteType(2, 15);
        //vs.modifierVoteFilmType(3, 5, 87);
        //vs.suppressionVoteFilm(53);
        
        
    
    }
    
}
