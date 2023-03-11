/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;


/**
 *
 * @author dhia
 */
public class Film {
    private int ID_film;
    private String Titre;
    private String DateRealisation;
    private String Genre;
    private String Resume;
    private String Duree;
    private float Prix;
    private String ID_producteur;
    private String Acteur;
    private String Image;

    public Film(int ID_film) {
        this.ID_film = ID_film;
    }
    
    

    public Film(int ID_film, String Titre, String DateRealisation, String Genre, String Resume, String Duree, float Prix, String ID_producteur, String Acteur) {
        this.ID_film = ID_film;
        this.Titre = Titre;
        this.DateRealisation = DateRealisation;
        this.Genre = Genre;
        this.Resume = Resume;
        this.Duree = Duree;
        this.Prix = Prix;
        this.ID_producteur = ID_producteur;
        this.Acteur = Acteur;
    }

    public Film() {
    }

    public Film(String Titre, String DateRealisation, String Genre, String Resume, String Duree, float Prix, String ID_producteur, String Acteur, String Image) {
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

    public Film(int ID_film, String Titre, String DateRealisation, String Genre, String Resume, String Duree, float Prix, String ID_producteur, String Acteur, String Image) {
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

   

    public int getID_film() {
        return ID_film;
    }

    public void setID_film(int ID_film) {
        this.ID_film = ID_film;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getDateRealisation() {
        return DateRealisation;
    }

    public void setDateRealisation(String DateRealisation) {
        this.DateRealisation = DateRealisation;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getResume() {
        return Resume;
    }

    public void setResume(String Resume) {
        this.Resume = Resume;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String Duree) {
        this.Duree = Duree;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public String getID_producteur() {
        return ID_producteur;
    }

    public void setID_producteur(String ID_producteur) {
        this.ID_producteur = ID_producteur;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getActeur() {
        return Acteur;
    }

    public void setActeur(String Acteur) {
        this.Acteur = Acteur;
    }

    

    @Override
    public String toString() {
        return "Film{" + "ID_film=" + ID_film + ", Titre=" + Titre + ", DateRealisation=" + DateRealisation + ", Genre=" + Genre + ", Resume=" + Resume + ", Duree=" + Duree + ", Prix=" + Prix + ", ID_producteur=" + ID_producteur + ", Acteur=" + Acteur + ", Image=" + Image + '}';
    }
    
    
    
    
}
