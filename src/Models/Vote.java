/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author wael
 */
public class Vote {
    private int ID_Vote;
    private int ID_Film;
    private int ID_User;
    private Date Date_Debut; 
    private Date Date_Fin; 

    public Vote() {
    }

    public Vote(int ID_Film, int ID_User, Date Date_Debut, Date Date_Fin) {
        this.ID_Film = ID_Film;
        this.ID_User = ID_User;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
    }

    public Vote(int ID_Vote, int ID_Film, int ID_User, Date Date_Debut, Date Date_Fin) {
        this.ID_Vote = ID_Vote;
        this.ID_Film = ID_Film;
        this.ID_User = ID_User;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
    }

    public int getID_Vote() {
        return ID_Vote;
    }

    public void setID_Vote(int ID_Vote) {
        this.ID_Vote = ID_Vote;
    }

    public int getID_Film() {
        return ID_Film;
    }

    public void setID_Film(int ID_Film) {
        this.ID_Film = ID_Film;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public Date getDate_Debut() {
        return Date_Debut;
    }

    public void setDate_Debut(Date Date_Debut) {
        this.Date_Debut = Date_Debut;
    }

    public Date getDate_Fin() {
        return Date_Fin;
    }

    public void setDate_Fin(Date Date_Fin) {
        this.Date_Fin = Date_Fin;
    }

    @Override
    public String toString() {
        return "Vote{" + "ID_Vote=" + ID_Vote + ", ID_Film=" + ID_Film + ", ID_User=" + ID_User + ", Date_Debut=" + Date_Debut + ", Date_Fin=" + Date_Fin + '}';
    }

    
    
    
    
}
