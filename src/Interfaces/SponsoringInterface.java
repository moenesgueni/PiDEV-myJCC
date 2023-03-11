package Interfaces;

import Models.ContratSponsoring;
import java.util.List;

public interface SponsoringInterface {
    //Create : creation d'une proposition de contrat
    public void ajouterContratSponsorin(ContratSponsoring c);
    
    //Read : Affichage de tout les contrats
    public List<ContratSponsoring> afficherContratsSponsorin();
    
    //GetById : Affichage d'un contrat
    public ContratSponsoring afficherContratSponsoring(int id);
    
    //Filter : Affichage les Contrats d'un sponsor
    public List<ContratSponsoring> afficherContratsDeSponsor(int id);
    
    //Filter : Affichage les Contrats d'un photographe
    public List<ContratSponsoring> afficherContratsDephotographe(int id);
    
    //Update : Changer les details du contrat
    public void modifierContratSponsoring(ContratSponsoring c);
    
    //Delete : Supprimer un contrat
    public void supprimerContratSponsoring(int id);

}
