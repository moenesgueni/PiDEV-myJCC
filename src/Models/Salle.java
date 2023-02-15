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
    private String NumTel_salle;
    private String Email_Salle;
    private String Temps_Ouverture;
    private String Temps_Fermuture;
    private float Avis;

    public Salle(int ID_salle, String NomSalle, String Adresse, int Capacite, String NumTel_salle, String Email_Salle, String Temps_Ouverture, String Temps_Fermuture, float Avis) {
        this.ID_salle = ID_salle;
        this.NomSalle = NomSalle;
        this.Adresse = Adresse;
        this.Capacite = Capacite;
        this.NumTel_salle = NumTel_salle;
        this.Email_Salle = Email_Salle;
        this.Temps_Ouverture = Temps_Ouverture;
        this.Temps_Fermuture = Temps_Fermuture;
        this.Avis = Avis;
    }

    public Salle(String NomSalle, String Adresse, int Capacite, String NumTel_salle, String Email_Salle, String Temps_Ouverture, String Temps_Fermuture, float Avis) {
        this.NomSalle = NomSalle;
        this.Adresse = Adresse;
        this.Capacite = Capacite;
        this.NumTel_salle = NumTel_salle;
        this.Email_Salle = Email_Salle;
        this.Temps_Ouverture = Temps_Ouverture;
        this.Temps_Fermuture = Temps_Fermuture;
        this.Avis = Avis;
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

    public String getNumTel_salle() {
        return NumTel_salle;
    }

    public void setNumTel_salle(String NumTel_salle) {
        this.NumTel_salle = NumTel_salle;
    }

    public String getEmail_Salle() {
        return Email_Salle;
    }

    public void setEmail_Salle(String Email_Salle) {
        this.Email_Salle = Email_Salle;
    }

    public String getTemps_Ouverture() {
        return Temps_Ouverture;
    }

    public void setTemps_Ouverture(String Temps_Ouverture) {
        this.Temps_Ouverture = Temps_Ouverture;
    }

    public String getTemps_Fermuture() {
        return Temps_Fermuture;
    }

    public void setTemps_Fermuture(String Temps_Fermuture) {
        this.Temps_Fermuture = Temps_Fermuture;
    }

    public float getAvis() {
        return Avis;
    }

    public void setAvis(float Avis) {
        this.Avis = Avis;
    }

    @Override
    public String toString() {
        return "Salle{NomSalle=" + NomSalle + ", Adresse=" + Adresse + ", Capacite=" + Capacite + ", NumTel_salle=" + NumTel_salle + ", Email_Salle=" + Email_Salle + ", Temps_Ouverture=" + Temps_Ouverture + ", Temps_Fermuture=" + Temps_Fermuture + ", Avis=" + Avis + '}';
    }

    
    
    
}
