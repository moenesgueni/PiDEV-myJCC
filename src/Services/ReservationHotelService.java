/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.ReservationHotelInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Hotel;
import Models.ReservationHotel;
import Models.User;
import util.MyConnection;

/**
 *
 * @author youssef
 */
public class ReservationHotelService implements ReservationHotelInterface {
    Connection cnx = MyConnection.getInstance().getCnx();
    HotelService hs = new HotelService();  
    UserService us = new UserService();
   /* -----------ajouter une reservation d'hotel pour un invité --------*/
    @Override
    public void addReservationHotel(ReservationHotel r) {
         String req = "INSERT INTO `reservation_hotel` (`dateReservation`, `date_debut`, `date_fin`, `tarifTotale`, `QrPath`, `id_user`, `id_hotel`)"
                + "VALUES (?,?,?,?,?,?,?)";
         
         String req2="UPDATE hotel SET `nbre_chambres` = ?"
                 +" WHERE ID_Hotel ="+r.getHotel().getId();
         
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            PreparedStatement ps2 = cnx.prepareStatement(req2);
            ps.setDate(1, r.getDateReservation());
            ps.setDate(2, r.getDate_debut());
            ps.setDate(3, r.getDate_fin());
            ps.setFloat(4, r.getTarifTotal());
            ps.setString(5, r.getQrpath());
            ps.setInt(6, r.getUser().getID_User());
            ps.setInt(7, r.getHotel().getId());
            ps.executeUpdate();
            ps2.setInt(1,r.getHotel().getNbre_chambres()-1);
            ps2.executeUpdate();
            System.out.println("RESERVATION EFFECTUEE!!!");
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   /* -----------afficher tous les reservations --------*/
    @Override
    public List<ReservationHotel> getAllReservationHotel() {

     List<ReservationHotel> Reservations = new ArrayList<>();
       String req = "SELECT * FROM reservation_hotel";
        Statement st;
       try {
           st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
           while(rs.next()){
              ReservationHotel r = new ReservationHotel() ; 
              r.setIdReservationH(rs.getInt(1));
              r.setDateReservation(rs.getDate(2));
              r.setDate_debut(rs.getDate(3));
              r.setDate_fin(rs.getDate(4));             
              r.setTarifTotal(rs.getFloat(5));
              r.setQrpath(rs.getString(6));
              int UserlId = rs.getInt(7);
             
              User user = us.afficherUserbyID(UserlId);
              r.setUser(user);
              int hotelId = rs.getInt(8);
              
              Hotel hotel = hs.GetHotelById(hotelId);
              r.setHotel(hotel);
              Reservations.add(r);
           }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }            
     return Reservations;
    }
   /* -----------afficher  reservation BY ID  --------*/
    @Override
    public ReservationHotel GetReservationHById(int Id) {          
        ReservationHotel r = new ReservationHotel();
        String req = "SELECT * FROM reservation_hotel where `ID_ReservationH` =" + Id + ";";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                r.setIdReservationH(rs.getInt(1));
                r.setDateReservation(rs.getDate(2));
                r.setDate_debut(rs.getDate(3));
                r.setDate_fin(rs.getDate(4));
                r.setTarifTotal(rs.getFloat(5));
                r.setQrpath(rs.getString(6));
                int UserlId = rs.getInt(7);
              System.out.println(UserlId);
              User user = us.afficherUserbyID(UserlId);
              r.setUser(user);
              int hotelId = rs.getInt(8);
              System.out.println(hotelId);
              Hotel hotel = hs.GetHotelById(hotelId);
              r.setHotel(hotel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    /* -----------Modifier une  Reservation --------*/
    @Override
    public void updateReservationHotel(ReservationHotel r) {
     String req = "UPDATE reservation_hotel SET dateReservation = ?, date_debut = ?, date_fin = ?, tarifTotale = ? "
                 +" WHERE ID_ReservationH = ?";   
     try {
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, r.getDateReservation());
            ps.setDate(2, r.getDate_debut());
            ps.setDate(3, r.getDate_fin());
            ps.setFloat(4, r.getTarifTotal());
            ps.setInt(5, r.getIdReservationH());
            
            ps.executeUpdate();
            System.out.println(" Reservation Modifiee avec success !!!");
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    /* -----------Supprimer une Reservation --------*/
    @Override
    public void deleteReservationHotel(int id) {
      String req = "DELETE FROM reservation_hotel WHERE ID_ReservationH  = ?";        
       try {
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println(" Reservation  Supprimee avec success !!!");
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }  
  }

    @Override
    public ReservationHotel filterByName(String nom) {
      ReservationHotel r = new ReservationHotel();
        String req = "SELECT * FROM reservation_hotel r Join User u ON r.id_user = u.ID_User where u.Nom ='"+nom+"';";  
        try {
           Statement st= cnx.createStatement();
           ResultSet rs = st.executeQuery(req); 
             while(rs.next()){
                r.setIdReservationH(rs.getInt(1));
                r.setDateReservation(rs.getDate(2));
                r.setDate_debut(rs.getDate(3));
                r.setDate_fin(rs.getDate(4));
                r.setTarifTotal(rs.getFloat(5));
                r.setQrpath(rs.getString(6));
                 int UserlId = rs.getInt(7);
                 
                System.out.println(UserlId);
                User user = us.afficherUserbyID(UserlId);
                r.setUser(user);
                int hotelId = rs.getInt(8);
                System.out.println(hotelId);
                Hotel hotel = hs.GetHotelById(hotelId);
                r.setHotel(hotel);
         }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }
      return r;  
    }

    @Override
    public List<ReservationHotel> filterByHotel(String libelle) {
     List<ReservationHotel> Reservations = new ArrayList<>();
      String req = "SELECT * FROM reservation_hotel r Join Hotel h ON r.id_hotel = h.ID_Hotel where h.libelle ='"+libelle+"';"; 
     Statement st;
       try {
           st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
           while(rs.next()){
              ReservationHotel r = new ReservationHotel() ; 
              r.setIdReservationH(rs.getInt(1));
              r.setDateReservation(rs.getDate(2));
              r.setDate_debut(rs.getDate(3));
              r.setDate_fin(rs.getDate(4));
                r.setTarifTotal(rs.getFloat(5));
               r.setQrpath(rs.getString(6));
                int UserlId = rs.getInt(7);
                System.out.println(UserlId);
                User user = us.afficherUserbyID(UserlId);
                r.setUser(user);
                int hotelId = rs.getInt(8);
                System.out.println(hotelId);
                Hotel hotel = hs.GetHotelById(hotelId);
                r.setHotel(hotel);
              Reservations.add(r);
           }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }            
    return Reservations ;
    }

    @Override
    public ReservationHotel filterByIdUser(int id) {
   ReservationHotel r = new ReservationHotel();
        String req = "SELECT * FROM reservation_hotel where `id_user` =" + id + ";";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                r.setIdReservationH(rs.getInt(1));
                r.setDateReservation(rs.getDate(2));
                r.setDate_debut(rs.getDate(3));
                r.setDate_fin(rs.getDate(4));
                r.setTarifTotal(rs.getFloat(5));
                r.setQrpath(rs.getString(6));
                int UserlId = rs.getInt(7);
              System.out.println(UserlId);
              User user = us.afficherUserbyID(UserlId);
              r.setUser(user);
              int hotelId = rs.getInt(8);
              System.out.println(hotelId);
              Hotel hotel = hs.GetHotelById(hotelId);
              r.setHotel(hotel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r; 
    }
    
    
}
