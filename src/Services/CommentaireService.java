/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.CommentaireInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Blog;
import Models.Commentaire;
import Utils.Connexion;

/**
 *
 * @author ASUS
 */
public class CommentaireService implements CommentaireInterface {

    Connection conn = Connexion.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Object java;
    UserService ps = new UserService();
    BlogService bs = new BlogService();

    @Override
    public void AjouterCommentaire(Commentaire object) {
        try {
            String req = "INSERT INTO `blog`(`ID_Auteur`, `date_commentaire`, `contenu`,`ID_blog`) VALUES ('" + object.getAuteur().getID_User() + "','" + object.getDate_commentaire() + "','" + object.getContenu() + "','" + object.getBlog().getId_blog() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Blog Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void ModifierCommentaire(Commentaire object) {
        String req = "UPDATE commentaire SET auteur ='" + object.getAuteur().getID_User() + "',date_commentaire ='" + object.getContenu() + "' WHERE id = '" + object.getId_commentaire() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void SupprimerCommentaire(Commentaire object) {
        String req = "DELETE FROM commentaire WHERE id='" + object.getId_commentaire() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Commentaire> RechercherCommentaire(String object) {
        List<Commentaire> list = new ArrayList<>();
        String req = " SELECT * FROM commentaire WHERE (auteur LIKE '%" + object + "%' OR date_commentaire LIKE '%" + object + "%')";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Commentaire(rs.getInt("id_commentaire"), ps.afficherUserbyID(rs.getInt("ID_Auteur")), rs.getDate("date_commentaire"), rs.getString("contenu"), bs.getBlogById(rs.getInt("Id_blog"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override

    public List<Commentaire> AfficherCommentaire(int id_blog) {
        List<Commentaire> list = new ArrayList<>();
        String req = " SELECT * FROM commentaire where id_blog = '" + id_blog + "'";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Commentaire(rs.getInt("id_commentaire"), ps.afficherUserbyID(rs.getInt("ID_Auteur")), rs.getDate("date_commentaire"), rs.getString("contenu"), bs.getBlogById(rs.getInt("Id_blog"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Commentaire getCommentaireById(int object) {
        Commentaire c = new Commentaire();
        String req = "select * from commentaire WHERE id_commentaire='" + object + "'";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            rs.next();
            c = new Commentaire(rs.getInt("id_commentaire"), ps.afficherUserbyID(rs.getInt("ID_Auteur")), rs.getDate("date_commentaire"), rs.getString("contenu"), bs.getBlogById(rs.getInt("Id_blog")));
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public List<Commentaire> TrieBlog(String object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
