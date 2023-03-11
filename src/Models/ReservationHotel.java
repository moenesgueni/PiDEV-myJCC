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
public class ReservationHotel {
    private int idReservationH ;
    private Date dateReservation ;
    private Date date_debut ;
    private Date date_fin ;
    private float tarifTotal ;
    private String qrpath;
    private Hotel hotel;
    private User user;

    public ReservationHotel() {
    }

    public String getQrpath() {
        return qrpath;
    }

    public void setQrpath(String qrpath) {
        this.qrpath = qrpath;
    }

    public ReservationHotel(Date dateReservation, Date date_debut, Date date_fin, float tarifTotal, String qrpath, Hotel hotel, User user) {
        this.dateReservation = dateReservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarifTotal = tarifTotal;
        this.qrpath = qrpath;
        this.hotel = hotel;
        this.user = user;
    }

    public ReservationHotel(int idReservationH, Date dateReservation, Date date_debut, Date date_fin, float tarifTotal, Hotel hotel, User user) {
        this.idReservationH = idReservationH;
        this.dateReservation = dateReservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarifTotal = tarifTotal;
        this.hotel = hotel;
        this.user = user;
    }

    public ReservationHotel(Date dateReservation, Date date_debut, Date date_fin, float tarifTotal, Hotel hotel, User user) {
        this.dateReservation = dateReservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarifTotal = tarifTotal;
        this.hotel = hotel;
        this.user = user;
    }

    public ReservationHotel(int idReservationH, Date dateReservation, Date date_debut, Date date_fin, float tarifTotal) {
        this.idReservationH = idReservationH;
        this.dateReservation = dateReservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarifTotal = tarifTotal;
    }
 
    
    public int getIdReservationH() {
        return idReservationH;
    }

    public void setIdReservationH(int idReservationH) {
        this.idReservationH = idReservationH;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReservationHotel{" + "idReservationH=" + idReservationH + ", dateReservation=" + dateReservation + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", tarifTotal=" + tarifTotal +'\n' +", hotel=" + hotel +'\n'+ ", user=" + user+'\n' + '}'+'\n';
    }
    
    
}
