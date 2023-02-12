package Interfaces;

import Models.Prix;
import java.util.List;


public interface PrixInterface {
    //CRUD
    //1:Create
    public void ajouterPrix(Prix p);
    
    //2: Read
    public List<Prix> afficherPrix();
    
    //3: Filter par type/Film/
    public List<Prix> afficherPrixType(String PrixType);
    public List<Prix> afficherPrixFilm(int PrixFilm);
    
    //4: modifier donnee
    public void modifierPrixFilm(int prixId , int prixFilm );
    public void modifierPrixType(int prixId , String prixType );
    public void modifierPrixFilmType(int prixId , int prixFilm , String prixType);
    
    //5: Suppression par film
    public void suppressionPrixFilm(int PrixFilm);
    public void suppressionPrixType(String PrixType);
    
}
