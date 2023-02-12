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
    
    //4: Suppression par film
    public void suppressionPrix(int PrixFilm);
    
}
