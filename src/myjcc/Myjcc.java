/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjcc;

import java.sql.Connection;
import Models.Hotel;
import Models.Location;
import Models.ReservationHotel;
import Models.Vehicule;
import Services.HotelService;
import Services.VehiculeService;
import util.MyConnection;
import java.sql.Date;
import java.util.Calendar;
import Models.User;
import Services.LocationVehiculeService;
import Services.ReservationHotelService;
import util.Type;

/**
 *
 * @author youssef
 */
public class Myjcc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection cnx = MyConnection.getInstance().getCnx();
      //  Hotel h1 = new Hotel(2,"africa", "tunis centre ville , avenue hbib bourgiba", 5, 12345678, "Hotel 5 etoiles ");
       HotelService hs = new HotelService();
         //hs.addHotel(h1);
        // System.out.println(hs.getAllHotels());
        Vehicule v4 = new Vehicule("200 Tunisie 4567", "Berline", "bmw", "gris");
       //Vehicule v1 = new Vehicule("220Tunisie1111", "Berline", "mercedes-benz", "noir");
      // VehiculeService vs = new VehiculeService();
      //vs.addVehicule(v4);
        //System.out.println(vs.getAllVehicules());
      //  System.out.println(hs.GetHotelById(1));
      //  System.out.println(vs.GetVehiculeBymatricule("220Tunisie1111"));
       //Hotel h1 = new Hotel(1,"africa", " avenue hbib bourgiba", 5, 87654321, "Hotel 5 etoiles ");
      //hs.updateHotel(h1);
     // Vehicule v1 = new Vehicule("220Tunisie1111", "Berline", "BMW E-64", "noir");
     // hs.deleteHotel(1);
    // vs.updateVehicule(v1);
    // vs.deleteVehicule("220Tunisie1111");
        //System.out.println(hs.filterByAdress("tunis centre ville , avenue hbib bourgiba"));
        //System.out.println(vs.filterVehiculesbyCouleur("bleu"));  
        User u1 = new User(1, "", "", "", "", "", Type.ADMINSTRATEUR, "") ;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 12);
        Date DateDebut = new Date(calendar.getTimeInMillis());
        calendar.set(2023, Calendar.JULY, 29);
        Date DateFin = new Date(calendar.getTimeInMillis());
        ReservationHotel reservation = new ReservationHotel(3,DateDebut, DateDebut, DateFin, 500);
        ReservationHotelService RH = new ReservationHotelService();
      //  RH.addReservationHotel(reservation);
      System.out.println( RH.filterByHotel("africa"));
       // RH.updateReservationHotel(reservation);
      //  System.out.println(RH.filterByHotel("africa"));
       // LocationVehiculeService ls = new LocationVehiculeService();
      // Location l = new Location(DateDebut, DateDebut, DateFin, 0, v4, u1) ;
      //  ls.addLocationBehicule(l);
       //System.out.println(ls.filterByVehicule("200 Tunisie 4567"));
       // System.out.println(hs.GetHotelById(2));
        
        
       
        
    }
}
