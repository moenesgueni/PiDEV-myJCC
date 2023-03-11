package Models;

import java.sql.Date;

/**
 *
 * @author wael
 */
public class Vote {

    private int valeur;
    private Film film;
    private User user;
    private String commentaire;
    private Date Date_Vote;
    private int Vote_Film;

    public Vote() {
    }

    public Vote(User user, Film film,  String commentaire, Date Date_Vote) {
        this.film = film;
        this.user = user;
        this.commentaire = commentaire;
        this.Date_Vote = Date_Vote;
    }

    public Vote(int valeur,  User user,Film film, String commentaire, Date Date_Vote) {
        this.valeur = valeur;
        this.film = film;
        this.user = user;
        this.commentaire = commentaire;
        this.Date_Vote = Date_Vote;
    }

    public Vote( User user,Film film, String commentaire, Date Date_Vote, int Vote_Film) {
        this.film = film;
        this.user = user;
        this.commentaire = commentaire;
        this.Date_Vote = Date_Vote;
        this.Vote_Film = Vote_Film;
    }
    
    

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Date getDate_Vote() {
        return Date_Vote;
    }

    public void setDate_Vote(Date Date_Vote) {
        this.Date_Vote = Date_Vote;
    }

    public int getVote_Film() {
        return Vote_Film;
    }

    public void setVote_Film(int Vote_Film) {
        this.Vote_Film = Vote_Film;
    }

    @Override
    public String toString() {
        return "Vote{" + "film=" + film + ", user=" + user + ", valeur=" + valeur + ", commentaire=" + commentaire + ", Date_Vote=" + Date_Vote + ", Vote_Film=" + Vote_Film + '}';
    }

}
