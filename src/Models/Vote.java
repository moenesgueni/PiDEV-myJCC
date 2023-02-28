package Models;

import java.sql.Date;

/**
 *
 * @author wael
 */
public class Vote {

    private Film film;
    private User user;
    private int valeur;
    private String commentaire;
    private Date Date_Vote;

    public Vote() {
    }

    public Vote(int valeur , Film film, User user, String commentaire, Date Date_Vote) {
        this.film = film;
        this.user = user;
        this.valeur = valeur;
        this.commentaire = commentaire;
        this.Date_Vote = Date_Vote;
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

    @Override
    public String toString() {
        return "Vote{" + "film=" + film + ", user=" + user + ", valeur=" + valeur + ", commentaire=" + commentaire + ", Date_Vote=" + Date_Vote + '}';
    }

}
