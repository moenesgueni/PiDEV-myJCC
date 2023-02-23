package myjcc;

import Models.Film;
import Models.Prix;
import Models.User;
import Models.Vote;
import Services.PrixService;
import Services.VoteService;
import Services.FilmService;
import Services.UserService;
import Utils.MaConnection;
import Utils.Type;
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
        Date Date_Vote = new Date(calendar.getTimeInMillis());

        PrixService ps = new PrixService();
        VoteService vs = new VoteService();
        FilmService fs = new FilmService();
        UserService us = new UserService();

        Film f = new Film(4, "The Shawshank Redemption 2", "1994", "action", "the best of all time", "2:03", (float) 50.00, "Frank Darabont", "Morgan Freeman");
        User u = new User(1, "Moenes", "Gueni", "Male", "moenes.gueni@esprit.tn", "azertyuiop123", Type.ADMINSTRATEUR, "cjckcfk");
        //fs.ajouterFilm(f);
        //us.ajouterUser(u);
        Prix p = new Prix(7, f, "babababa");
        Vote v = new Vote(2, f, u, Date_Vote);
        //vs.ajouterVote(v);
        //ps.ajouterPrix(p);

        //ps.getAllPrix().forEach(System.out::println);
        //System.out.println(ps.afficherPrix(7));
        //ps.afficherPrixType("or").forEach(System.out::println);        
        //System.out.println(ps.afficherTitreFilm("The Shawshank Redemption"));
        //ps.modifierPrix(p);
        //ps.suppressionPrix(6);
        //crud vote
        //vs.ajouterVote(v);
        //System.out.println(vs.afficherVote(2));
        //System.out.println(vs.afficherVoteUser("Gueni"));
        //vs.afficherVotes().forEach(System.out::println);
        //System.out.println(vs.afficherVoteFilm("The Shawshank Redemption"));
        //vs.suppressionVoteFilm(3);
    }

}
