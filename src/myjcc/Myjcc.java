package myjcc;

import Models.ContratSponsoring;
import Models.Galerie;
import Models.Photographie;
import Services.ContratSponsorinService;
import Services.GalerieService;
import Services.PhotographieService;
import Utils.EnumEtatContrat;
import Utils.EnumTypeContrat;
import java.sql.Date;
import java.util.Calendar;


public class Myjcc {

    public static void main(String[] args) {
        /*CRUD Galerie *********************************************************************
        //Creation service galerie
        GalerieService gs = new GalerieService();
        
        //Creation Galerie
        Galerie g1 = new Galerie("GalerieNum6", "Description de la 6ème galerie", 22);
        Galerie g2 = new Galerie(4,"Galerie4", "Description modifié de la 4ème galerie", 19);
        
        //ajout galerie
        gs.ajouterGalerie(g1);
        
        //Read : Afficher toutes les galeries
        gs.afficherGaleries().forEach(System.out::println);
    
        //GetById : Afficher une Galerie
        System.out.println("Affichage de la deuxieme galerie---------");
        System.out.println(gs.afficherGalerie(2));

        //Update
        gs.modifierGalerie(g2);

        //Delete
        gs.supprimerGalerie(5);
        */
        
        /*GRUD Photographie *********************************************************************
        //creation service Photographie & Galerie
        PhotographieService ps = new PhotographieService();
        GalerieService gs = new GalerieService();
        
        //Get Galerie
        Galerie g1 = gs.afficherGalerie(2);
        
        //creation objet photographie
        Photographie p1 = new Photographie("NewPhoto", "Description New method photo", "C/Path/photoNew.PNG", g1);
        Photographie p2 = new Photographie(1,"Photo5", "Description photo", "C/Path/photo5.PNG", g1);
        
        //ajout Photographie
        ps.ajouterPhotographie(p1);
        
        //Read : Afficher toutes les Photographies
        ps.afficherPhotographies().forEach(System.out::println);
    
        //GetById : Afficher une photographie
        System.out.println("Photo id 3---------");
        System.out.println(ps.afficherPhotographie(3));
    
        //Filter : Afficher les Photographies d'un Photographe
        System.out.println("Les phtotos du photographe ayant la galerie id = 2");
        ps.afficherPhotographiesDunPhotographe(2).forEach(System.out::println);
    
        //Update
        ps.modifierPhotographie(p2);
    
        //Delete
        ps.SupprimerPhotographie(1);
        */
        
        /* CRUD ContratSponsoring *********************************************************************
        //creation de date
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 12);
        Date DateDebut = new Date(calendar.getTimeInMillis());
        calendar.set(2023, Calendar.JULY, 24);
        Date DateFin = new Date(calendar.getTimeInMillis());
        
        //creation de service contratSponsoring
        ContratSponsorinService css = new ContratSponsorinService();
        
        //creation de contrat
        ContratSponsoring cs1 = new ContratSponsoring(DateDebut, DateFin, EnumTypeContrat.ParPhoto, EnumEtatContrat.Proposition, 20f, "TermesPDF", 8, 9);
        ContratSponsoring cs2 = new ContratSponsoring(1,DateDebut, DateFin, EnumTypeContrat.ParPhoto, EnumEtatContrat.Proposition, 30.2f, "TermesPDF", 5, 7);
        
        //ajout contrat
        css.ajouterContratSponsorin(cs1);
        
        //afficher les contrats
        css.afficherContratsSponsorin().forEach(System.out::println);
        
        System.out.println("Contrat de ID = 2-----------");
        System.out.println(css.afficherContratSponsoring(2));
        
        //modifier contrat
        css.modifierContratSponsoring(cs2);
        
        //supprimer contrat
        css.supprimerContratSponsoring(5);
        
        //afficher les contrats d'un sponsor ID_Sponsor = 2
        System.out.println("les contrats d'un sponsor ID_Sponsor = 2");
        css.afficherContratsDeSponsor(2).forEach(System.out::println);
        
        //afficher les contrats d'un sponsor ID_Photographe = 3
        System.out.println(" les contrats d'un sponsor ID_Photographe = 3");
        css.afficherContratsDephotographe(3).forEach(System.out::println);
        */
        
    }
    
}
