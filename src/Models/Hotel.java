/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author youssef
 */
public class Hotel {
    // att
    private int id ;
    private String lebelle ;
    private String adresse ;
    private int nbre_chambres ;
    private int telephone ;
    private String description ;
    //cont
    public Hotel() {
    }

    public Hotel(int id, String lebelle, String adresse, int nbre_chambres, int telephone, String description) {
        this.id = id;
        this.lebelle = lebelle;
        this.adresse = adresse;
        this.nbre_chambres = nbre_chambres;
        this.telephone = telephone;
        this.description = description;
    }

    public Hotel(String lebelle, String adresse, int nbre_chambres, int telephone, String description) {
        this.lebelle = lebelle;
        this.adresse = adresse;
        this.nbre_chambres = nbre_chambres;
        this.telephone = telephone;
        this.description = description;
    }
    //getters and setters   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLebelle() {
        return lebelle;
    }

    public void setLebelle(String lebelle) {
        this.lebelle = lebelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNbre_chambres() {
        return nbre_chambres;
    }

    public void setNbre_chambres(int nbre_chambres) {
        this.nbre_chambres = nbre_chambres;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //display 
    @Override
    public String toString() {
        return "hotel{" + "id=" + id + ", lebelle=" + lebelle + ", adresse=" + adresse + ", nbre_chambres=" + nbre_chambres + ", telephone=" + telephone + ", description=" + description + '}';
    }
    
    
}
