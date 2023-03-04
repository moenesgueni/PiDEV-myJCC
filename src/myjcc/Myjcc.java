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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Myjcc {

    Button button;

    public static void main(String[] args) {
        // just pour test de connection : tjik fel console --> Connection etablie avec succes !!!
        //ken c bon naheha w hotha fel service

        //crud prix
        Connection cnx = MaConnection.getInstance().getCnx();
        Calendar calendar = Calendar.getInstance();
        Date Date_Vote = new Date(calendar.getTimeInMillis());
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String dateString = dateFormat.format(Date_Vote);
        //System.out.println("date : "+dateString);

        PrixService ps = new PrixService();
        VoteService vs = new VoteService();
        FilmService fs = new FilmService();
        UserService us = new UserService();
        
        
        
        //System.out.println(vs.afficherVotesDate());
        ArrayList<Integer> liste = vs.countStars();
        //System.out.println(vs.countStars());
        //System.out.println(liste.get(2));
        //ArrayList<Integer> liste = vs.countVoteFilmByType("Action");
        ArrayList<String> genre = fs.getAllGenres();
        //System.out.println(vs.countVoteFilmByType(genre).get(0).getKey());
        //System.out.println(fs.getAllGenres());
        //System.out.println(vs.getMostVotedFilmByGenre(genre));
        System.out.println(vs.getMostVotedFilmByGenre(genre).get(8).getKey());
                
        //vs.afficherVotesDate();
        
        //Film f = new Film(4, "The Shawshank Redemption 2", "1994", "action", "the best of all time", "2:03", (float) 50.00, "Frank Darabont", "Morgan Freeman");
        //User u = new User(1, "Moenes", "Gueni", "Male", "moenes.gueni@esprit.tn", "azertyuiop123", Type.ADMINSTRATEUR, "cjckcfk");
        //
        //Prix p = new Prix(7, f, "babababa");
        //Vote v = new Vote(2, f, u, Date_Vote);
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
        //System.out.println(vs.afficherVote(46));
        //System.out.println(vs.afficherVoteUser("Gueni"));
        //vs.afficherVotes().forEach(System.out::println);
        //System.out.println(vs.afficherVoteFilm("The Shawshank Redemption"));
        //vs.suppressionVoteFilm(3);
        
        
        User user1 = new User(2, "wael", "ben rejeb", "Male", "wael.benrejeb@esprit.tn", "azertyuiop123", Type.ADMINSTRATEUR, "cjckcfk");
        User user2 = new User(1, "Moenes", "Gueni", "Male", "moenes.gueni@esprit.tn", "azertyuiop123", Type.ADMINSTRATEUR, "cjckcfk");
        Film film1 = new Film(3, "The Conjuring", "2014", "horror", "Don't be alone", "2:03", (float) 50.00, "Frank Darabont", "Morgan Freeman");
        Film film2 = new Film(4, "The Shawshank Redemption 4", "1994", "action", "the best of all time", "2:03", (float) 50.00, "Frank Darabont", "Morgan Freeman");
        Vote vote1 = new Vote();
        Vote vote2 = new Vote( 4, film2, user1,"hi world", Date_Vote,80);
        Vote vote3 = new Vote( 1, film2, user2,"hi world", Date_Vote,90);
        
        
        //fs.ajouterFilm(film1);
        //us.ajouterUser(u);
        //vs.voteee(vote1);//test vota walla lee
        // System.out.println("somme des votes : "+vs.countVote(3));//cout vte par film
        

        //launch(args);*/
    }

    /*Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // hedhi Alert nest7akkelha ki nselctioni film + marbout bl classe AlertBox
        window = primaryStage;
        window.setTitle("new button");
        
        button = new Button("Click me");
        button.setOnAction(e->AlertBox.display("Title of window", "alet work"));
        
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout,300,250);
        window.setScene(scene);
        window.show();
       
    }*/
}
