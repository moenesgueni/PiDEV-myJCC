/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author dhia
 */
public class Salle {
    private int ID_salle;
    private String NomSalle;
    private String Adresse;
    private int Capacite;

    public Salle(int ID_salle, String NomSalle, String Adresse, int Capacite) {
        this.ID_salle = ID_salle;
        this.NomSalle = NomSalle;
        this.Adresse = Adresse;
        this.Capacite = Capacite;
    }

    public Salle(String NomSalle, String Adresse, int Capacite) {
        this.NomSalle = NomSalle;
        this.Adresse = Adresse;
        this.Capacite = Capacite;
    }

    public Salle() {
    }

    public int getID_salle() {
        return ID_salle;
    }

    public void setID_salle(int ID_salle) {
        this.ID_salle = ID_salle;
    }

    public String getNomSalle() {
        return NomSalle;
    }

    public void setNomSalle(String NomSalle) {
        this.NomSalle = NomSalle;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public int getCapacite() {
        return Capacite;
    }

    public void setCapacite(int Capacite) {
        this.Capacite = Capacite;
    }

    @Override
    public String toString() {
        return "salle{" + "NomSalle=" + NomSalle + ", Adresse=" + Adresse + ", Capacite=" + Capacite + '}';
    }
    
    
    
}
