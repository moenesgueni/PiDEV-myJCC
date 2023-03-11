/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.PlanningFilmSalle;
import Models.Reservation;
import java.util.List;

/**
 *
 * @author dhia
 */
public interface ReservationInterface {
    
   public void ajouterReservation(Reservation r);
    
    
    public List<Reservation> afficherReservation();
    
    
    public void updateReservation(Reservation r);
    
    
    public void supprimerReservation(int id);
    
    
    public Reservation GetReservationById(int id);
    
    
    public List<Reservation> GetReservationByPlanning(int id);
    
}
