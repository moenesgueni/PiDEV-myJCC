package Models;

import java.sql.Date;

/**
 *
 * @author wael
 */
public class Vote {

    private int ID_Vote;
    private Film film;
    private User user;

    private Date Date_Vote;

    public Vote() {
    }

    public Vote(Film film, User user, Date Date_Vote) {
        this.film = film;
        this.user = user;
        this.Date_Vote = Date_Vote;
    }

    public Vote(int ID_Vote, Film film, User user, Date Date_Vote) {
        this.ID_Vote = ID_Vote;
        this.film = film;
        this.user = user;
        this.Date_Vote = Date_Vote;
    }

    public int getID_Vote() {
        return ID_Vote;
    }

    public void setID_Vote(int ID_Vote) {
        this.ID_Vote = ID_Vote;
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

    public Date getDate_Vote() {
        return Date_Vote;
    }

    public void setDate_Vote(Date Date_Vote) {
        this.Date_Vote = Date_Vote;
    }

    @Override
    public String toString() {
        return "Vote{" + "ID_Vote=" + ID_Vote + ", \n || film=" + film + "||, \n || user=" + user + ", || \n Date_Vote=" + Date_Vote + '}';
    }

}
