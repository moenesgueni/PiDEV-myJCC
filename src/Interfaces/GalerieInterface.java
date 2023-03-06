package Interfaces;
import Models.Galerie;
import java.util.List;
public interface GalerieInterface {
    //Create
    public void ajouterGalerie(Galerie g);
    
    //Read : Afficher toutes les galeries
    public List<Galerie> afficherGaleries();
    
    //GetById : Afficher une Galerie
    public Galerie afficherGalerie(int id);
    
    //GetById : Afficher une Galerie d'un photographe
    public Galerie afficherGalerieDuPhotographe(int id);
    
    //Update
    public void modifierGalerie(Galerie g);
    
    //Delete
    public void supprimerGalerie(int id);
    
}
