/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author wael
 */
public class Vote {
    private int ID_Vote;
    private int ID_Film;
    private int ID_User;

    public Vote() {
    }

    public Vote(int ID_Film, int ID_User) {
        this.ID_Film = ID_Film;
        this.ID_User = ID_User;
    }

    public Vote(int ID_Vote, int ID_Film, int ID_User) {
        this.ID_Vote = ID_Vote;
        this.ID_Film = ID_Film;
        this.ID_User = ID_User;
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

    @Override
    public String toString() {
        return "Vote{" + "ID_Vote=" + ID_Vote + ", ID_Film=" + ID_Film + ", ID_User=" + ID_User + '}';
    }
    
    
    
}
