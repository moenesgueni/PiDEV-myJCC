package Interfaces;

import Models.Photographie;
import java.util.List;

public interface PhotographieInterface {
    //Create
    public void ajouterPhotographie(Photographie p);
    
    //Read : Afficher toutes les Photographies
    public List<Photographie> afficherPhotographies();
    
    //GetById : Afficher une photographie
    public Photographie afficherPhotographie(int id);
    
    //Filter : Afficher les Photographies d'un Photographe
    public List<Photographie> afficherPhotographiesDunPhotographe(int id);
    
    //Update
    public void modifierPhotographie(Photographie p);
    
    //Delete
    public void SupprimerPhotographie(int id);
    
}
