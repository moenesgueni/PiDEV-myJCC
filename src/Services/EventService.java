/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.IEventController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Evenement;
import Utils.Connexion;

/**
 *
 * @author ASUS
 */

public class EventService implements IEventController {  Connection conn = Connexion.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Object java;

   @Override
    public void AjouterEvent(Evenement object) {
        try {
            String req = "INSERT INTO `evenement`(`nom_event`, `date_et_heure`, `lieu`,`type_event`,`description`) VALUES ('" + object.getNom_event() + "','" + object.getDate_et_heure() + "','" + object.getLieu() + "','" + object.getType_event() + "','" + object.getDescription() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Event Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void ModifierEvent(Evenement object) {
        String req = "UPDATE evenement SET nom_event ='" + object.getNom_event() + "',date_et_heure ='" + object.getDate_et_heure() + "',lieu='" + object.getLieu() + "',type_event='" + object.getType_event() + "',description = '" + object.getDescription() + "' WHERE id = '" + object.getId() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void SupprimerEvent(Evenement object) {
        String req = "DELETE FROM evenement WHERE id='" + object.getId() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Evenement> RechercherEvent(String object) {
        List<Evenement> list = new ArrayList<>();
        String req = " SELECT * FROM evenement WHERE (nom_event LIKE '%"+object+"%' OR date_et_heure LIKE '%" + object + "%'OR lieu LIKE '%" + object + "%'OR type_event LIKE '%" + object + "%')";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Evenement(rs.getInt("id"), rs.getString("nom_event"), rs.getDate("date_et_heure"), rs.getString("lieu"), rs.getString("type_event"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Evenement> AfficherEvents() {
        List<Evenement> list = new ArrayList<>();
        String req = " SELECT * FROM evenement ";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Evenement(rs.getInt("id"), rs.getString("nom_event"), rs.getDate("date_et_heure"), rs.getString("lieu"), rs.getString("type_event"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Evenement getEventById(int id) {
        Evenement C = new Evenement();
        String req = "select * from evenement WHERE id='" + id + "'";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            rs.next();
            C = new Evenement(rs.getInt("id"), rs.getString("nom_event"), rs.getDate("date_et_heure"), rs.getString("lieu"), rs.getString("type_event"), rs.getString("description"));
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;
    }

    @Override
    public List<Evenement> TrieEvent(String object) {
        List<Evenement> list = new ArrayList<>();
        String req;
        switch (object) {
            case "nom_event":
                req = " SELECT * FROM evenement  OrderBy nom_event";
                break;
            case "date_et_heure":
                req = " SELECT * FROM evenement  OrderBy date_et_heure";
                break;
            case "type_event":
                req = " SELECT * FROM evenement  OrderBy date_et_heure";
                break;
            default:
                req = " SELECT * FROM evenement  OrderBy id";

        }

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Evenement(rs.getInt("id"), rs.getString("nom_event"), rs.getDate("date_et_heure"), rs.getString("lieu"), rs.getString("type_event"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
}
