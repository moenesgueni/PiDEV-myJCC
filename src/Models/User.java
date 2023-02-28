/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

//import Utilities.Type;

import Utilities.Type;
import java.util.Objects;

//import javax.management.relation.Role;

/**
 *
 * @author moene
 */
public class User {
    private int ID_User;
    private String Nom;
    private String Prenom;
    private String Sexe;
    private String Email;
    private String MotDePasse;
    private Type Role;
    private String PhotoB64;

    public User() {
    }

    public User(int ID_User, String Nom, String Prenom, String Sexe, String Email, String MotDePasse, Type Role, String PhotoB64) {
        this.ID_User = ID_User;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Sexe = Sexe;
        this.Email = Email;
        this.MotDePasse = MotDePasse;
        this.Role = Role;
        this.PhotoB64 = PhotoB64;
    }

    public User(String Nom, String Prenom, String Sexe, String Email, String MotDePasse,Type Role, String PhotoB64) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Sexe = Sexe;
        this.Email = Email;
        this.MotDePasse = MotDePasse;
        this.Role = Role;
        this.PhotoB64 = PhotoB64;
    }


    

    public int getID_User() {
        return ID_User;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getSexe() {
        return Sexe;
    }

    public String getEmail() {
        return Email;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public Type getRole() {
        return Role;
    }

    public String getPhotoB64() {
        return PhotoB64;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setMotDePasse(String MotDePasse) {
        this.MotDePasse = MotDePasse;
    }

    public void setRole(Type Role) {
        this.Role = Role;
    }

    public void setPhotoB64(String PhotoB64) {
        this.PhotoB64 = PhotoB64;
    }

    @Override
    public String toString() {
        return "User{Nom=" + Nom + ", Prenom=" + Prenom + ", Sexe=" + Sexe + ", Email=" + Email + ", MotDePasse=" + MotDePasse + ", Role=" + Role + ", PhotoB64=" + PhotoB64 + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.MotDePasse);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return true;
    }

    
}
