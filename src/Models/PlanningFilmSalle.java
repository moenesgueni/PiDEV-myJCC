/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author dhia
 */
public class PlanningFilmSalle {
    private int ID_planning;
    private Salle salle;
    private Film film;
    private Date Datediffusion;
    private String Heurediffusion;

    public PlanningFilmSalle(int ID_planning, Salle salle, Film film, Date Datediffusion, String Heurediffusion) {
        this.ID_planning = ID_planning;
        this.salle = salle;
        this.film = film;
        this.Datediffusion = Datediffusion;
        this.Heurediffusion = Heurediffusion;
    }

    public PlanningFilmSalle(Salle salle, Film film, Date Datediffusion, String Heurediffusion) {
        this.salle = salle;
        this.film = film;
        this.Datediffusion = Datediffusion;
        this.Heurediffusion = Heurediffusion;
    }

    public PlanningFilmSalle() {
    }

    public int getID_planning() {
        return ID_planning;
    }

    public void setID_planning(int ID_planning) {
        this.ID_planning = ID_planning;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getDatediffusion() {
        return Datediffusion;
    }

    public void setDatediffusion(Date Datediffusion) {
        this.Datediffusion = Datediffusion;
    }

    public String getHeurediffusion() {
        return Heurediffusion;
    }

    public void setHeurediffusion(String Heurediffusion) {
        this.Heurediffusion = Heurediffusion;
    }

    @Override
    public String toString() {
        return "Planning{" + "salle=" + salle + ", film=" + film + ", Datediffusion=" + Datediffusion + ", Heurediffusion=" + Heurediffusion + '}';
    }
    
    
    
    
}
