package Interfaces;

import Models.Prix;
import java.util.List;


public interface PrixInterface {
    //CRUD
    //1:Create
    public void ajouterPrix(Prix p);
    
    //2: Read
    public List<Prix> getAllPrix();
    
    //3: getById
    public Prix afficherPrix(int PrixID);
    
    //4: Filter par type/Film/
    public List<Prix> afficherPrixType(String PrixType);
    public Prix afficherTitreFilm(String TitreFilm); //String // afficher film par titre
    
    //5: modifier donnee
    public void modifierPrix(Prix p);
    
    //6: Suppression par film
    public void suppressionPrix(int ID);
    
}