/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.LocationInterface;
import java.util.List;
import Models.Location;
import Models.User;
import Models.Vehicule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyConnection;

/**
 *
 * @author youssef
 */
public class LocationVehiculeService implements LocationInterface {
    Connection cnx = MyConnection.getInstance().getCnx();
    VehiculeService vs = new VehiculeService();
    UserService us = new UserService();
   /* -----------ajouter une location  de vehicule pour un invité --------*/
    @Override
    public void addLocationBehicule(Location l) {
     String req = "INSERT INTO `location_vehicule` (`dateReservation`, `date_debut`, `date_fin`, `TarifTotal`, `id_User`, `matricule`)"
            + "VALUES (?,?,?,?,?,?)";        
     try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, l.getDateReservation());
            ps.setDate(2, l.getDate_debut());
            ps.setDate(3, l.getDate_fin());
            ps.setFloat(4, l.getTarifTotal());
            ps.setInt(5, l.getUser().getID_User());
            ps.setString(6, l.getVehicule().getMaricule());
            ps.executeUpdate();
            System.out.println("Location EFFECTUEE!!!");
        } catch (SQLException ex) {
            Logger.getLogger(LocationVehiculeService.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
   /* -----------afficher tous les Locations de Vehicule --------*/
    @Override
    public List<Location> getAllLocationVehicule() {
        VehiculeService vs = new VehiculeService();
     List<Location> locations = new ArrayList<>(); 
       String req = "SELECT * FROM location_vehicule";
        Statement st;
       try {
           st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
           while(rs.next()){
              Location l = new Location() ; 
              l.setIdLocationV(rs.getInt(1));
              l.setDateReservation(rs.getDate(2));
              l.setDate_debut(rs.getDate(3));
              l.setDate_fin(rs.getDate(4));
              l.setTarifTotal(rs.getFloat(5));
              
               String matricule = rs.getString(6);
                System.out.println(matricule);
                Vehicule vehicule = vs.GetVehiculeBymatricule(matricule);
                l.setVehicule(vehicule);
              int UserlId = rs.getInt(7);
                System.out.println(UserlId);
                User user = us.afficherUserbyID(UserlId);
                l.setUser(user);

             
              locations.add(l);
           }
       } catch (SQLException ex) {
           Logger.getLogger(LocationVehiculeService.class.getName()).log(Level.SEVERE, null, ex);
       }     
    return locations;
    }

    @Override
    public Location GetLocationVehiculeById(int Id) {
         Location l = new Location();
        String req = "SELECT * FROM location_vehicule where `ID_location ` =" + Id + ";";   
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                l.setIdLocationV(rs.getInt(1));
                l.setDateReservation(rs.getDate(2));
                l.setDate_debut(rs.getDate(3));
                l.setDate_fin(rs.getDate(4));
                l.setTarifTotal(rs.getFloat(5));
                String matricule = rs.getString(6);
                System.out.println(matricule);
                Vehicule vehicule = vs.GetVehiculeBymatricule(matricule);
                l.setVehicule(vehicule);
              int UserlId = rs.getInt(7);
                System.out.println(UserlId);
                User user = us.afficherUserbyID(UserlId);
                l.setUser(user);
            }
        }catch (SQLException ex) {
            Logger.getLogger(LocationVehiculeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     return l ;   
    }

    @Override
    public void updateLocationVehicule(Location l) {
     String req = "UPDATE location_vehicule SET dateReservation = ?, date_debut = ?, date_fin = ?, tarifTotale = ? "
                 +" WHERE ID_location = ?";    
 try {
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, l.getDateReservation());
            ps.setDate(2, l.getDate_debut());
            ps.setDate(3, l.getDate_fin());
            ps.setFloat(4, l.getTarifTotal());
            ps.setInt(5, l.getIdLocationV());
            ps.executeUpdate();
            System.out.println(" Location Modifiee avec success !!!");
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }     
    }

    @Override
    public void deleteLocationVehicule(int id) {
       String req = "DELETE FROM location_vehicule WHERE ID_location  = ?";        
       try {
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println(" Location  Supprimee avec success !!!");
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }  
    }

    @Override
    public Location filterByName(String nom) {
     Location l = new Location();
        String req = "SELECT * FROM location_vehicule l Join User u ON l.id_User = u.ID_User where u.Nom ='"+nom+"';";  
        try {
           Statement st= cnx.createStatement();
           ResultSet rs = st.executeQuery(req); 
             while(rs.next()){
                l.setIdLocationV(rs.getInt(1));
                l.setDateReservation(rs.getDate(2));
                l.setDate_debut(rs.getDate(3));
                l.setDate_fin(rs.getDate(4));
                l.setTarifTotal(rs.getFloat(5));
                
                String matricule = rs.getString(6);
                System.out.println(matricule);
                Vehicule vehicule = vs.GetVehiculeBymatricule(matricule);
                l.setVehicule(vehicule);
              int UserlId = rs.getInt(7);
                System.out.println(UserlId);
                User user = us.afficherUserbyID(UserlId);
                l.setUser(user);
         }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }   
    return l ;    
    }

    @Override
    public Location filterByVehicule(String matricule) {
      Location l = new Location();       
    String req = "SELECT * FROM location_vehicule l Join vehicule v ON l.matricule = v.matricule where v.matricule ='"+matricule+"';";  
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                l.setIdLocationV(rs.getInt(1));
                l.setDateReservation(rs.getDate(2));
                l.setDate_debut(rs.getDate(3));
                l.setDate_fin(rs.getDate(4));
                l.setTarifTotal(rs.getFloat(5));
                String matricule2 = rs.getString(6);
                System.out.println(matricule2);
                Vehicule vehicule = vs.GetVehiculeBymatricule(matricule2);
                l.setVehicule(vehicule);
              int UserlId = rs.getInt(7);
                System.out.println(UserlId);
                User user = us.afficherUserbyID(UserlId);
                l.setUser(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        } 
      return l ; 
    }

  
    
}
