/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.HotelInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Hotel;
import util.MyConnection;

/**
 *
 * @author youssef
 */
public class HotelService implements HotelInterface {
   Connection cnx = MyConnection.getInstance().getCnx();
    
   /* -----------ajouter hotel --------*/
    @Override
    public void addHotel(Hotel h) {
        String req = "INSERT INTO `hotel`(`libelle`, `adresse`, `nbre_chambres`, `telephone`, `description`)"
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, h.getLebelle());
            ps.setString(2, h.getAdresse());
            ps.setInt(3, h.getNbre_chambres());
            ps.setInt(4, h.getTelephone());
            ps.setString(5, h.getDescription());
            ps.executeUpdate();
            System.out.println("Nouveau Hotel Ajoute avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   /* -----------afficher tous les hotels --------*/
    @Override
    public List<Hotel> getAllHotels() {
       List<Hotel> hotels = new ArrayList<>();
       String req = "SELECT * FROM hotel";
        Statement st;
       try {
           st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
           while(rs.next()){
              Hotel h = new Hotel() ; 
              h.setId(rs.getInt(1));
              h.setLebelle(rs.getString(2));
              h.setAdresse(rs.getString(3));
              h.setNbre_chambres(rs.getInt(4));
              h.setTelephone(rs.getInt(5));
              h.setDescription(rs.getString(6));
              hotels.add(h);
           }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }       
       return hotels;
    }
    /* -----------GETBYID HOTEL --------*/   

    @Override
    public Hotel GetHotelById(int Id) {
     Hotel h =new Hotel();
     String req = "SELECT * FROM hotel where `ID_Hotel` ="+Id+";";
     try {
      Statement st= cnx.createStatement();
       ResultSet rs = st.executeQuery(req); 
       while(rs.next()){
             h.setId(rs.getInt(1));
             h.setLebelle(rs.getString(2));
             h.setAdresse(rs.getString(3));
             h.setNbre_chambres(rs.getInt(4));
             h.setTelephone(rs.getInt(5));
             h.setDescription(rs.getString(6));
         }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return h ;
    }
    /* -----------Modifier un  HOTEL --------*/
    @Override
    public void updateHotel(Hotel h) {
        String req = "UPDATE hotel SET libelle = ?, adresse = ?, nbre_chambres = ?, telephone = ?, description = ? "
                 +" WHERE ID_Hotel = ?";
       try {
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, h.getLebelle());
            ps.setString(2, h.getAdresse());
            ps.setInt(3, h.getNbre_chambres());
            ps.setInt(4, h.getTelephone());
            ps.setString(5, h.getDescription());
            ps.setInt(6, h.getId());
            ps.executeUpdate();
            System.out.println(" Hotel Modifie avec success !!!");
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    /* -----------Supprimer un  HOTEL --------*/
    @Override
    public void deleteHotel(int id) {
      String req = "DELETE FROM hotel WHERE ID_Hotel = ?";
       try {
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println(" Hotel Supprime avec success !!!");
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }

    }
    /* -----------Filtre par nom d'HOTEL --------*/
    @Override
    public Hotel filterByName(String nom) {
     Hotel h =new Hotel();
     String req = "SELECT * FROM hotel where `libelle` ='"+nom+"';";
       try {
           Statement st= cnx.createStatement();
           ResultSet rs = st.executeQuery(req); 
             while(rs.next()){
             h.setId(rs.getInt(1));
             h.setLebelle(rs.getString(2));
             h.setAdresse(rs.getString(3));
             h.setNbre_chambres(rs.getInt(4));
             h.setTelephone(rs.getInt(5));
             h.setDescription(rs.getString(6));
         }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }     
     return h ;
    }
    /* -----------Filtre par adresse d'HOTEL --------*/
    @Override
    public Hotel filterByAdress(String adresse) {
    Hotel h =new Hotel();
     String req = "SELECT * FROM hotel where `adress` ='"+adresse+"';";
     Statement st;
       try {
           st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req); 
             while(rs.next()){
             h.setId(rs.getInt(1));
             h.setLebelle(rs.getString(2));
             h.setAdresse(rs.getString(3));
             h.setNbre_chambres(rs.getInt(4));
             h.setTelephone(rs.getInt(5));
             h.setDescription(rs.getString(6));
         }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }
      return h; 
    }
    
   
}
