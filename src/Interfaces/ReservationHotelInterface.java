/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import Models.ReservationHotel;

/**
 *
 * @author youssef
 */
public interface ReservationHotelInterface {
    //add Reservation
    public void addReservationHotel(ReservationHotel r);

    //liste : select
    public List<ReservationHotel> getAllReservationHotel();

    //getbyid: Reservation
    public ReservationHotel GetReservationHById(int Id);

    //update Reservation
    public void updateReservationHotel(ReservationHotel r);

    //delete Reservation
    public void deleteReservationHotel(int id);
    
    //Filter by nom de l'utilisateur 
     public ReservationHotel filterByName(String nom);
    //Filter by nom de l'hotel   
    public List<ReservationHotel> filterByHotel(String libelle);


}
