/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import Models.Location;

/**
 *
 * @author youssef
 */
public interface LocationInterface {
    //add LocationVehicule
    public void addLocationBehicule(Location l);

    //liste : select
    public List<Location> getAllLocationVehicule();

    //getbyid: LocationVehicule
    public Location GetLocationVehiculeById(int Id);

    //update LocationVehicule
    public void updateLocationVehicule(Location l);

    //delete LocationVehicule
    public void deleteLocationVehicule(int id);
    //Filter by nom de l'utilisateur 
     public Location filterByName(String nom);
    //Filter by matricule 
    public Location filterByVehicule(String matricule);
    
}
