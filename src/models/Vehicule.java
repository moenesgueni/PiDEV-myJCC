/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author youssef
 */
public class Vehicule {
    private String maricule ;
    private String type ;
    private String marque ;
    private String couleur ;

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    //const
    public Vehicule() {
    }

    public Vehicule(String maricule, String type, String marque, String couleur) {
        this.maricule = maricule;
        this.type = type;
        this.marque = marque;
        this.couleur = couleur;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }


//getters and setters 
    public String getMaricule() {
        return maricule;
    }

    public void setMaricule(String maricule) {
        this.maricule = maricule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //display 

    @Override
    public String toString() {
        return "Vehicule{" + "maricule=" + maricule + ", type=" + type + ", marque=" + marque + ", couleur=" + couleur + '}';
    }



    
}
