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
public class UserSession {
    private static UserSession instance;
    private static String Nom,Prenom,Genre,Email,MotDePasse,PhotoBD64;
    private static Type Role;
    private static int NumTel,ID_User;
    private UserSession(int ID_User,String Nom,String Prenom,String Genre,String Email,String MotDePasse,Type Role,String PhotoBD64,int NumTel) {
    this.ID_User=ID_User;
    this.Nom=Nom;
    this.Prenom=Prenom;
    this.Genre =Genre;
    this.Email=Email;
    this.MotDePasse =MotDePasse;
    this.PhotoBD64 = PhotoBD64;
    this.Role=Role;
    this.NumTel=NumTel;
    }

    public static synchronized UserSession getInstance(int ID_User,String Nom,String Prenom,String Genre,String Email,String MotDePasse,Type Role,String PhotoBD64,int NumTel) {
        if (instance == null) {
            instance = new UserSession(ID_User,Nom,Prenom,Genre,Email,MotDePasse,Role,PhotoBD64,NumTel);
        }
        return instance;
    }
    
    public static UserSession EndSession(){
    instance =null;
    return instance;
    }
    
    
     public static UserSession getInstance() {
        return instance;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }




    public static String getNom() {
        return Nom;
    }

    public static void setNom(String Nom) {
        UserSession.Nom = Nom;
    }

    public static String getPrenom() {
        return Prenom;
    }

    public static void setPrenom(String Prenom) {
        UserSession.Prenom = Prenom;
    }
    public static String getGenre() {
        return Genre;
    }

    public static void setGenre(String Genre) {
        UserSession.Genre = Genre;
    }
    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String Email) {
        UserSession.Email = Email;
    }

    public static String getMotDePasse() {
        return MotDePasse;
    }

    public static void setMotDePasse(String MotDePasse) {
        UserSession.MotDePasse = MotDePasse;
    }
    public static String getPhotoBD46() {
        return PhotoBD64;
    }

    public static void setPhotoBD64(String PhotoBD64) {
        UserSession.PhotoBD64 = PhotoBD64;
    }
    
    public static Type getRole() {
        return Role;
    }

    public static void setRoles(Type Role) {
        UserSession.Role = Role;
    }


    
    public static int getNumTel() {
        return NumTel;
    }

    public static void setNumTel(int NumTel) {
        UserSession.NumTel= NumTel;
    }

    public static int getID_User() {
        return ID_User;
    }

    public static void setID_User(int NumTel) {
        UserSession.ID_User= ID_User;
    }
    

    
    
}
