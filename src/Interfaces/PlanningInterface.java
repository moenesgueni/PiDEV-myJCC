/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Film;
import Models.PlanningFilmSalle;
import Models.Salle;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author dhia
 */
public interface PlanningInterface {
    public void ajouterPlanning(PlanningFilmSalle p);
    
    
    public List<PlanningFilmSalle> afficherPlanning();
    
    
    public void updatePlanning(PlanningFilmSalle p);
    
    
    public void supprimerPlanning(int id);
    
    
    public List<PlanningFilmSalle> GetPlanningByFilm(int id);
    
    public PlanningFilmSalle GetPlanningByID(int id);
    
    
    public List<PlanningFilmSalle> GetPlanningByDate(Date date);
    
    
}
