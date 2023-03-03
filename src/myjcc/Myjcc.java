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
import java.util.logging.Level;
import java.util.logging.Logger;
import util.FileUpload;
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
      /*  // TODO code application logic here
        User u1 = new User(1, "", "", "", "", "", Type.ADMINSTRATEUR, "") ;
      Hotel h3 = new Hotel(2,"LaicoTunis", "tunis ", 5, 12345678, "Hotel 5 etoiles ");
        /**********Crud hotel **********/
        // Connection cnx = MyConnection.getInstance().getCnx();
        
       // HotelService hs = new HotelService();
       // hs.addHotel(h3);
      //  System.out.println(hs.getAllHotels());
       //System.out.println(hs.GetHotelById(4));
       
       
       
       /**********Crud Vehicule **********/
       
      /* Vehicule v5 = new Vehicule("200 Tunisie 2222", "Berline", "bmw", "noir");
       VehiculeService vs = new VehiculeService();
        vs.addVehicule(v5);
        System.out.println(vs.getAllVehicules());
       vs.deleteVehicule("200 Tunisie 2222");*/
       
      /**********Crud Reservation Hotel **********/
    // Calendar calendar = Calendar.getInstance();
      //  calendar.set(2023, Calendar.JANUARY, 12);
        //Date DateDebut = new Date(calendar.getTimeInMillis());
        //calendar.set(2023, Calendar.FEBRUARY, 1);
        //Date DateFin = new Date(calendar.getTimeInMillis());
      
       // ReservationHotel reservation = new ReservationHotel(DateFin, DateDebut, DateFin, 0, h3, u1);
      //  ReservationHotelService RH = new ReservationHotelService();
        //RH.addReservationHotel(reservation);
       //System.out.println( RH.getAllReservationHotel());
     //   System.out.println( RH.filterByHotel("africa"));
      
        
       /**********Crud Location  Vehicule **********/  
     /*  Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 12);
        Date DateDebut = new Date(calendar.getTimeInMillis());
        calendar.set(2023, Calendar.FEBRUARY, 1);
        Date DateFin = new Date(calendar.getTimeInMillis());
        LocationVehiculeService ls = new LocationVehiculeService();
         Location l = new Location(DateDebut, DateDebut, DateFin, 0, v4, u1) ;
        ls.addLocationBehicule(l);
     LocationVehiculeService ls = new LocationVehiculeService();
       System.out.println(ls.filterByVehicule("200 Tunisie 4567"));*/
       
       
       
      //  System.out.println(vs.GetVehiculeBymatricule("220Tunisie1111"));
       //Hotel h1 = new Hotel(1,"africa", " avenue hbib bourgiba", 5, 87654321, "Hotel 5 etoiles ");
      //hs.updateHotel(h1);
     // Vehicule v1 = new Vehicule("220Tunisie1111", "Berline", "BMW E-64", "noir");
     // hs.deleteHotel(1);
    // vs.updateVehicule(v1);
    // vs.deleteVehicule("220Tunisie1111");
        //System.out.println(hs.filterByAdress("tunis centre ville , avenue hbib bourgiba"));
        //System.out.println(vs.filterVehiculesbyCouleur("bleu"));  
        
        
      //System.out.println( RH.filterByHotel("africa"));
       // RH.updateReservationHotel(reservation);
      //  System.out.println(RH.filterByHotel("africa"));
       // 

       // System.out.println(hs.GetHotelById(2));
    //  FileUpload
                  try {
            FileUpload.uploadFile("C:/Users/youssef/Desktop/temp.png", "QRimages\\temps.png");
        } catch (Exception ex) {
            Logger.getLogger(Myjcc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
}
