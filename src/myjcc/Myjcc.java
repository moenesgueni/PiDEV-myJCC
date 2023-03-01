package myjcc;

import API.ColorPaletteCreator;
import Models.ContratSponsoring;
import Models.Galerie;
import Models.Photographie;
import Models.User;
import Services.ContratSponsorinService;
import Services.GalerieService;
import Services.PhotographieService;
import Services.UserService;
import Utils.EnumEtatContrat;
import Utils.EnumTypeContrat;
import Utils.Type;
import Utils.FileUpload;
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Myjcc {

    public static void main(String[] args) {
        /*FileUpload
                  try {
            FileUpload.uploadFile("C:/Users/Marwen/Desktop/mercedess.pdf", "contrats\\newMercedess.pdf");
        } catch (Exception ex) {
            Logger.getLogger(Myjcc.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        /*Get color palette
            try {
            //ColorPaletteCreator c = new ColorPaletteCreator("[44,43,44]");
            ColorPaletteCreator c = new ColorPaletteCreator("[43,42,45]","[116,84,78]","[184,169,163]","[226,236,232]");
            int[][] colors = c.getColors();
            System.out.println(colors[1][2]);
            } catch (Exception ex) {
            Logger.getLogger(Myjcc.class.getName()).log(Level.SEVERE, null, ex);
            }
         */
 /*CRUD Galerie *********************************************************************
            System.out.println("---------************-----------");
            System.out.println("---------CRUD Galerie-----------");
            System.out.println("---------************-----------");
            //Creation service galerie
            GalerieService gs = new GalerieService();
            //creation service user
            UserService ps = new UserService();
            //instance user
            User p1 = new User(1,"Khaled","Tounsi","Male","khaled.tounsi@esprit.tn","azertyuiop123",Type.ADMINSTRATEUR,"C/Path/photo.png");
            User p2 = new User(5,"Marwen","Hammami","Male","marwen.hammami@esprit.tn","azertyuiop123",Type.ADMINSTRATEUR,"C/Path/photo.png");
            
            //Creation Galerie
            Galerie g1 = new Galerie("GalerieNew", "Galerie De la seance Validation 23 16/02", p1);
            Galerie g2 = new Galerie(4,"Galerie4", "Description modifié de la 4ème galerie", p2);
            
            //ajout galerie
            //gs.ajouterGalerie(g1);
            
            //Read : Afficher toutes les galeries
            //gs.afficherGaleries().forEach(System.out::println);
            
            //GetById : Afficher une Galerie
            //System.out.println("Affichage de la deuxieme galerie---------");
            //System.out.println(gs.afficherGalerie(2));
            
            //Update
            //gs.modifierGalerie(g2);
            
            //Delete
            //gs.supprimerGalerie(3);
         */

 /*GRUD Photographie *********************************************************************
            System.out.println("---------************-----------");
            System.out.println("---------CRUD Photographie-----------");
            System.out.println("---------************-----------");
            //creation service Photographie & Galerie
            PhotographieService ps = new PhotographieService();
            GalerieService gs = new GalerieService();
            
            //Get Galerie
            Galerie g1 = gs.afficherGalerie(4);
            Galerie g2 = gs.afficherGalerie(5);
            //creation objet photographie
            Photographie p1 = new Photographie("Billy", "New photo De la seance Validation 16/02", "C/Path/photoNew.PNG", g1);
            Photographie p2 = new Photographie(4,"TITRE46", "Description photo", "C/Path/photo5.PNG", g2);
            
            //ajout Photographie
            //ps.ajouterPhotographie(p1);
            
            //Read : Afficher toutes les Photographies
            //ps.afficherPhotographies().forEach(System.out::println);
            
            //GetById : Afficher une photographie
            //System.out.println("Photo id 3---------");
            //System.out.println(ps.afficherPhotographie(3));
            
            //Filter : Afficher les Photographies d'un Photographe
            //System.out.println("Les phtotos du photographe ayant la galerie id = 2");
            //ps.afficherPhotographiesDunPhotographe(5).forEach(System.out::println);
            
            //Update
            //ps.modifierPhotographie(p2);
            
            //Delete
            //ps.SupprimerPhotographie(7);
         */
 /* CRUD ContratSponsoring *********************************************************************
            System.out.println("---------************-----------");
            System.out.println("---------CRUD Contrat Sponsoring-----------");
            System.out.println("---------************-----------");
            //creation de date
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 16);
            Date DateDebut = new Date(calendar.getTimeInMillis());
            calendar.set(2023, Calendar.JULY, 16);
            Date DateFin = new Date(calendar.getTimeInMillis());
            
            //creation de service contratSponsoring
            ContratSponsorinService css = new ContratSponsorinService();
            //creation service user
            UserService ps = new UserService();
            //instance user
            User p1 = new User(1,"Khaled","Tounsi","Male","khaled.tounsi@esprit.tn","azertyuiop123",Type.ADMINSTRATEUR,"C/Path/photo.png");
            User p2 = new User(5,"Marwen","Hammami","Male","marwen.hammami@esprit.tn","azertyuiop123",Type.ADMINSTRATEUR,"C/Path/photo.png");
            
            //creation de contrat
            ContratSponsoring cs1 = new ContratSponsoring(DateDebut, DateFin, EnumTypeContrat.ParHeure, EnumEtatContrat.EnCours, 50f, "C/Path/Termes.PDF", p1, p2);
            ContratSponsoring cs2 = new ContratSponsoring(3,DateDebut, DateFin, EnumTypeContrat.ParPhoto, EnumEtatContrat.Proposition, 30.3f, "C/Path/Termes..PDF", p2, p1);
            
            //ajout contrat
            //css.ajouterContratSponsorin(cs1);
            
            //afficher les contrats
            //css.afficherContratsSponsorin().forEach(System.out::println);
            
            //System.out.println("Contrat de ID = 2-----------");
            //System.out.println(css.afficherContratSponsoring(2));
            
            //modifier contrat
            //css.modifierContratSponsoring(cs2);
            
            //supprimer contrat
            //css.supprimerContratSponsoring(6);
            
            //afficher les contrats d'un sponsor ID_Sponsor = 2
            //System.out.println("les contrats d'un sponsor ID_Sponsor = 2");
            //css.afficherContratsDeSponsor(1).forEach(System.out::println);
            
            //afficher les contrats d'un sponsor ID_Photographe = 3
            //System.out.println(" les contrats d'un sponsor ID_Photographe = 3");
            //css.afficherContratsDephotographe(1).forEach(System.out::println);
         */
    }

}
