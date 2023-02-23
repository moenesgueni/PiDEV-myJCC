/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author youssef
 */
public class Location {
    private int idLocationV ;
    private Date dateReservation ;
    private Date date_debut ;
    private Date date_fin ;
    private float tarifTotal ;
    private Vehicule vehicule;
    private User user;

    public Location() {
    }

    public Location(int idLocationV, Date dateReservation, Date date_debut, Date date_fin, float tarifTotal) {
        this.idLocationV = idLocationV;
        this.dateReservation = dateReservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarifTotal = tarifTotal;
    }

    public Location(int idLocationV, Date dateReservation, Date date_debut, Date date_fin, float tarifTotal, Vehicule vehicule, User user) {
        this.idLocationV = idLocationV;
        this.dateReservation = dateReservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarifTotal = tarifTotal;
        this.vehicule = vehicule;
        this.user = user;
    }

    public Location(Date dateReservation, Date date_debut, Date date_fin, float tarifTotal, Vehicule vehicule, User user) {
        this.dateReservation = dateReservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarifTotal = tarifTotal;
        this.vehicule = vehicule;
        this.user = user;
        
    }

    @Override
    public String toString() {
        return "Location{" + "idLocationV=" + idLocationV + ", dateReservation=" + dateReservation + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", tarifTotal=" + tarifTotal + ", vehicule=" + vehicule + ", user=" + user + '}';
    }

    public int getIdLocationV() {
        return idLocationV;
    }

    public void setIdLocationV(int idLocationV) {
        this.idLocationV = idLocationV;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public float getTarifTotal() {
        return tarifTotal;
    }

    public void setTarifTotal(float tarifTotal) {
        this.tarifTotal = tarifTotal;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
