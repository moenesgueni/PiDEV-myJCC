/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.VehiculeInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Vehicule;
import util.MyConnection;

/**
 *
 * @author youssef
 */
public class VehiculeService implements VehiculeInterface {
 Connection cnx = MyConnection.getInstance().getCnx();
   /* -----------ajouter une vehicule--------*/ 
    @Override
    public void addVehicule(Vehicule v) {
             String req = "INSERT INTO `vehicule`(`matricule`, `type`, `marque`, `couleur`)"
                + "VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, v.getMaricule());
            ps.setString(2, v.getType());
            ps.setString(3, v.getMarque());
            ps.setString(4, v.getCouleur());

            ps.executeUpdate();
            System.out.println("Nouveau Vehicule Ajoutee avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   /* -----------afficher tous les vehicules --------*/
    @Override
    public List<Vehicule> getAllVehicules() {
        List<Vehicule> vehicules = new ArrayList<>();
       String req = "SELECT * FROM vehicule";
        Statement st;
       try {
           st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
           while(rs.next()){
              Vehicule v = new Vehicule() ; 
              v.setMaricule(rs.getString(1));
              v.setType(rs.getString(2));
              v.setMarque(rs.getString(3));
              v.setCouleur(rs.getString(4));
              vehicules.add(v);
           }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }       
       return vehicules;
    }
   /* -----------afficher une  vehicule par sa matricule  --------*/
    @Override
    public Vehicule GetVehiculeBymatricule(String matricule) {
       Vehicule v = new Vehicule();
       String req = "SELECT * FROM vehicule where `matricule` ='"+matricule+"';"; 
     try {
      Statement st= cnx.createStatement();
       ResultSet rs = st.executeQuery(req); 
       while(rs.next()){
              v.setMaricule(rs.getString(1));
              v.setType(rs.getString(2));
              v.setMarque(rs.getString(3));
              v.setCouleur(rs.getString(4));
         }
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return v ;
    }
   /* -----------modifier les données d'une vehicule --------*/
    @Override
    public void updateVehicule(Vehicule v) {
String req = "UPDATE vehicule SET type = ?, marque = ?, couleur = ?"
                 +" WHERE matricule = ?";
       try {
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, v.getType());
            ps.setString(2, v.getMarque());
            ps.setString(3, v.getCouleur());
            ps.setString(4, v.getMaricule());
            ps.executeUpdate();
            System.out.println(" vehicule Modifie avec success !!!");         
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteVehicule(String matricule) {
         String req = "DELETE FROM vehicule WHERE matricule = ?";
       try {
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, matricule);
            ps.executeUpdate();
            System.out.println(" Vehicule Supprime avec success !!!");
       } catch (SQLException ex) {
           Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public List<Vehicule> filterVehiculesbyMarque(String marque) {
        List<Vehicule> vehicules = new ArrayList<>();
        String req = "SELECT * FROM vehicule where marque ='"+marque+"'";
        try {
             Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Vehicule v = new Vehicule();
                v.setMaricule(rs.getString(1));
                v.setType(rs.getString(2));
                v.setMarque(rs.getString(3));
                v.setCouleur(rs.getString(4));
                vehicules.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicules;   
    }

    @Override
    public List<Vehicule> filterVehiculesbyCouleur(String couleur) {
                List<Vehicule> vehicules = new ArrayList<>();
        String req = "SELECT * FROM vehicule where couleur ='"+couleur+"'";
        try {
             Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Vehicule v = new Vehicule();
                v.setMaricule(rs.getString(1));
                v.setType(rs.getString(2));
                v.setMarque(rs.getString(3));
                v.setCouleur(rs.getString(4));
                vehicules.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicules; 
    }

    @Override
    public List<Vehicule> filterVehiculesbyType(String type) {
                List<Vehicule> vehicules = new ArrayList<>();
        String req = "SELECT * FROM vehicule where type ='"+type+"'";
        try {
             Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Vehicule v = new Vehicule();
                v.setMaricule(rs.getString(1));
                v.setType(rs.getString(2));
                v.setMarque(rs.getString(3));
                v.setCouleur(rs.getString(4));
                vehicules.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicules; 
    }


}
