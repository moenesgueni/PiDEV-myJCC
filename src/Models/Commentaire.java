/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Commentaire {

    private int id_commentaire;
    private User Auteur;
    private Date date_commentaire;
    private String contenu;
    private Blog Blog;

    public Commentaire(int id_commentaire, User Auteur, Date date_commentaire, String contenu, Blog Blog ) {
        this.id_commentaire = id_commentaire;
        this.Blog = Blog;
        this.Auteur = Auteur;
        this.date_commentaire = date_commentaire;
        this.contenu = contenu;
    }

    public Commentaire( User Auteur, Date date_commentaire, String contenu,Blog Blog) {
        this.Blog = Blog;
        this.Auteur = Auteur;
        this.date_commentaire = date_commentaire;
        this.contenu = contenu;
    }

    public Commentaire() {

    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public Blog getBlog() {
        return Blog;
    }

    public void setBlog(Blog Blog) {
        this.Blog = Blog;
    }

    public void setAuteur(User Auteur) {
        this.Auteur = Auteur;
    }

    public User getAuteur() {
        return Auteur;
    }

    public Date getDate_commentaire() {
        return date_commentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setDate_commentaire(Date date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_commentaire=" + id_commentaire + ", auteur=" + Auteur + ", date_commentaire=" + date_commentaire + ", contenu=" + contenu + '}';
    }

}

