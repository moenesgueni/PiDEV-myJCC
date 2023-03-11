/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
/**
 *
 * @author moene
 */
public class FilmSession {
    private static FilmSession instance;
    private static int ID_film;
    private static String Titre;
    private static String DateRealisation;
    private static String Genre;
    private static String Resume;
    private static String Duree;
    private static float Prix;
    private static String ID_producteur;
    private static String Acteur;
    private static String Image;
    public FilmSession(int ID_film, String Titre, String DateRealisation, String Genre, String Resume, String Duree, float Prix, String ID_producteur, String Acteur, String Image) {
        this.ID_film = ID_film;
        this.Titre = Titre;
        this.DateRealisation = DateRealisation;
        this.Genre = Genre;
        this.Resume = Resume;
        this.Duree = Duree;
        this.Prix = Prix;
        this.ID_producteur = ID_producteur;
        this.Acteur = Acteur;
        this.Image = Image;
    }

    public static synchronized FilmSession getInstance(int ID_film, String Titre, String DateRealisation, String Genre, String Resume, String Duree, float Prix, String ID_producteur, String Acteur, String Image) {
        if (instance == null) {
            instance = new FilmSession(ID_film,Titre,DateRealisation,Genre,Resume,Duree,Prix,ID_producteur,Acteur,Image);
        }
        return instance;
    }
    
    public static FilmSession EndSession(){
    instance =null;
    return instance;
    }
    
    
     public static FilmSession getInstance() {
        return instance;
    }

    public static void setInstance(FilmSession instance) {
        FilmSession.instance = instance;
    }

    public static int getID_film() {
        return ID_film;
    }

    public static String getTitre() {
        return Titre;
    }

    public static String getDateRealisation() {
        return DateRealisation;
    }

    public static String getGenre() {
        return Genre;
    }

    public static String getResume() {
        return Resume;
    }

    public static String getDuree() {
        return Duree;
    }

    public static float getPrix() {
        return Prix;
    }

    public static String getID_producteur() {
        return ID_producteur;
    }

    public static String getActeur() {
        return Acteur;
    }

    public static String getImage() {
        return Image;
    }

    public static void setID_film(int ID_film) {
        FilmSession.ID_film = ID_film;
    }

    public static void setTitre(String Titre) {
        FilmSession.Titre = Titre;
    }

    public static void setDateRealisation(String DateRealisation) {
        FilmSession.DateRealisation = DateRealisation;
    }

    public static void setGenre(String Genre) {
        FilmSession.Genre = Genre;
    }

    public static void setResume(String Resume) {
        FilmSession.Resume = Resume;
    }

    public static void setDuree(String Duree) {
        FilmSession.Duree = Duree;
    }

    public static void setPrix(float Prix) {
        FilmSession.Prix = Prix;
    }

    public static void setID_producteur(String ID_producteur) {
        FilmSession.ID_producteur = ID_producteur;
    }

    public static void setActeur(String Acteur) {
        FilmSession.Acteur = Acteur;
    }

    public static void setImage(String Image) {
        FilmSession.Image = Image;
    }




  

    
    
}
