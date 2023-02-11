package myjcc;

import Models.ContratSponsoring;
import Models.Galerie;
import Models.Photographie;
import Services.ContratSponsorinService;
import Services.GalerieService;
import Services.PhotographieService;
import Utils.EnumTypeContrat;
import java.sql.Date;
import java.util.Calendar;


public class Myjcc {

    public static void main(String[] args) {
        /*GRUD Photographie *********************************************************************
        //creation service Photographie
        PhotographieService ps = new PhotographieService();
        
        //creation objet photographie
        Photographie p1 = new Photographie("Photo7", "Description photo", "C/Path/photo7.PNG", 13);
        Photographie p2 = new Photographie(1,"Photo5", "Description photo", "C/Path/photo5.PNG", 3);
        
        //ajout Photographie
        ps.ajouterPhotographie(p1);
        
        //Read : Afficher toutes les Photographies
        ps.afficherPhotographies().forEach(System.out::println);
    
        //GetById : Afficher une photographie
        System.out.println("Photo id 2---------");
        System.out.println(ps.afficherPhotographie(2));
    
        //Filter : Afficher les Photographies d'un Photographe
        System.out.println("Les phtotos du photographe ayant la galerie id = 4");
        ps.afficherPhotographiesDunPhotographe(4).forEach(System.out::println);
    
        //Update
        ps.modifierPhotographie(p2);
    
        //Delete
        ps.SupprimerPhotographie(6);
        */
        
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
        ContratSponsoring cs1 = new ContratSponsoring(DateDebut, DateFin, EnumTypeContrat.ParPhoto, 0, "TermesPDF", 2, 3);
        ContratSponsoring cs2 = new ContratSponsoring(1,DateDebut, DateFin, EnumTypeContrat.ParPhoto, 30.2f, "TermesPDF", 0, 0);
        
        //ajout contrat
        //css.ajouterContratSponsorin(cs1);
        
        //afficher contrat
        css.afficherContratsSponsorin().forEach(System.out::println);
        
        System.out.println("Contrat de ID = 2-----------");
        System.out.println(css.afficherContratSponsoring(2));
        
        //modifier contrat
        css.modifierContratSponsoring(cs2);
        
        //supprimer contrat
        //css.supprimerContratSponsoring(2);
        
        //afficher les contrats d'un sponsor ID_Sponsor = 0
        System.out.println("les contrats d'un sponsor ID_Sponsor = 0");
        css.afficherContratsDeSponsor(0).forEach(System.out::println);
        
        //afficher les contrats d'un sponsor ID_Photographe = 0
        System.out.println(" les contrats d'un sponsor ID_Photographe = 0");
        css.afficherContratsDephotographe(0).forEach(System.out::println);
        */
    }
    
}
