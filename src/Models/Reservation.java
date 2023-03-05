/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author dhia
 */
public class Reservation {
    private int ID_Reservation;
    private PlanningFilmSalle planning;
    private User user;

    public Reservation(int ID_Reservation, PlanningFilmSalle planning, User user) {
        this.ID_Reservation = ID_Reservation;
        this.planning = planning;
        this.user = user;
    }

    public Reservation(PlanningFilmSalle planning, User user) {
        this.planning = planning;
        this.user = user;
    }

    public Reservation() {
    }

    public int getID_Reservation() {
        return ID_Reservation;
    }

    public void setID_Reservation(int ID_Reservation) {
        this.ID_Reservation = ID_Reservation;
    }

    public PlanningFilmSalle getPlanning() {
        return planning;
    }

    public void setPlanning(PlanningFilmSalle planning) {
        this.planning = planning;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation{" + "ID_Reservation=" + ID_Reservation + ", planning=" + planning + ", user=" + user + '}';
    }
    
    
    
}
