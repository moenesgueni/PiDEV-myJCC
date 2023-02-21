/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author ASUS
 */
public class Evenement {

    private int id;
    private String nom_event;
    private Date date_et_heure;
    private String lieu;
    private String type_event;
    private String description;



    public Evenement(int id, String nom_event, Date date_et_heure, String lieu, String type_event, String description) {
        this.id = id;
        this.nom_event = nom_event;
        this.date_et_heure = date_et_heure;
        this.lieu = lieu;
        this.type_event = type_event;
        this.description = description;
    }
       public Evenement() {

    }
        public Evenement( String nom_event, Date date_et_heure, String lieu, String type_event, String description) {

        this.nom_event = nom_event;
        this.date_et_heure = date_et_heure;
        this.lieu = lieu;
        this.type_event = type_event;
        this.description = description;
    }

    public Evenement(int aInt, String string, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public Date getDate_et_heure() {
        return date_et_heure;
    }

    public void setDate_et_heure(Date date_et_heure) {
        this.date_et_heure = date_et_heure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom_event=" + nom_event + ", date_et_heure=" + date_et_heure + ", lieu=" + lieu + ", type_event=" + type_event + ", description=" + description + '}';
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
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom_event, other.nom_event)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.type_event, other.type_event)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.date_et_heure, other.date_et_heure);
    }

}
