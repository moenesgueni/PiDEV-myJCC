/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Models.Salle;
import java.util.List;
/**
 *
 * @author dhia
 */
public interface SalleInterface {
    
    public void ajouterSalle(Salle s);
    
    
    public List<Salle> afficherSalle();
    
    
    public void updateSalle(Salle s);
    
    
    public void supprimerSalle(String NomSalle);
    
    
    public Salle GetSalleByName(String NomSalle);
    
    public Salle GetSalleByID(int id);
    
    
    public Salle GetSalleByAdresse(String Adresse);
    
    
}
