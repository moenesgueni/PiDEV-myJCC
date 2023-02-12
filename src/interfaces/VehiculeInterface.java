/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Hotel;
import models.Vehicule;

/**
 *
 * @author youssef
 */
public interface VehiculeInterface {
    //add
    public void addVehicule(Vehicule v);
    
    //liste : select
    public List<Vehicule> getAllVehicules();
    //get vehicule by matricule
    public Vehicule GetVehiculeBymatricule(String matricule);
    //update vehicule
    public void updateVehicule(Vehicule v);
    //delete vehicule
    public void deleteVehicule (String matricule);
    //Filter par marque
    public List<Vehicule> filterVehiculesbyMarque(String marque);
    //filter par couleur
    public List<Vehicule> filterVehiculesbyCouleur(String couleur);
    //filter par type 
    public List<Vehicule> filterVehiculesbyType(String type);
}
