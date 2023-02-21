/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Interfaces.BlogInterface;
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
import Utils.Connexion;

/**
 *
 * @author ASUS
 */

public class BlogService implements BlogInterface{
    //connexion à la base de données 
     Connection conn = Connexion.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Object java;
    //creation service user 
     UserService ps = new UserService();

    @Override
    public void AjouterBlog(Blog object) {
   try {
            String req = "INSERT INTO `blog`(`titre`, `ID_Auteur`, `date_publication`,`contenu`,`etiquette`,`image`) VALUES ('" + object.getTitre() + "','" + object.getAuteur().getID_User() + "','" + object.getDate_publication() + "','" + object.getContenu() + "','" + object.getEtiquette() + "','" + object.getImage() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Blog Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void ModifierBlog(Blog object) {
     String req = "UPDATE blog SET titre ='" + object.getTitre() + "',ID_Auteur ='" + object.getAuteur().getID_User() + "',date_publication='" + object.getDate_publication() + "',contenu='" + object.getContenu() + "',etiquette = '" + object.getEtiquette()+ "',image = '" + object.getImage() + "' WHERE id = '" + object.getId_blog() + "'";     
     try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }     }

    @Override
    public void SupprimerBlog(Blog object) {
 String req = "DELETE FROM blog WHERE id='" + object.getId_blog() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<Blog> RechercherBlog(String object) {
        
List<Blog> list = new ArrayList<>();
        String req = " SELECT * FROM blog WHERE (titre LIKE '%" + object + "%' OR ID_Auteur LIKE '%" + object + "%'OR date_publication LIKE '%" + object + "%'OR contenu LIKE '%" + object+"%')" ;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id_blog"), rs.getString("titre"), ps.afficherUserbyID(rs.getInt("ID_Auteur")), rs.getDate("date_publication"), rs.getString("contenu"),rs.getString("etiquette"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; 
    }

    @Override
    public List<Blog> AfficherBlogs() {
List<Blog> list = new ArrayList<>();
        String req = " SELECT * FROM blog";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id_blog"), rs.getString("titre"), ps.afficherUserbyID(rs.getInt("ID_Auteur")), rs.getDate("date_publication"), rs.getString("contenu"),rs.getString("etiquette"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    }

    @Override
    public Blog getBlogById(int id_blog) {
  Blog b = new Blog();
        String req = "select * from blog WHERE id_blog='" + id_blog + "'";  
     try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            rs.next();
            b = new Blog(rs.getInt("id_blog"), rs.getString("titre"),ps.afficherUserbyID(rs.getInt("ID_Auteur")), rs.getDate("date_publication"), rs.getString("contenu"), rs.getString("etiquette"), rs.getString("image")); 
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;}

    @Override
    public List<Blog> TrieBlog(String object) {
 List<Blog> list = new ArrayList<>();
        String req;
        switch (object) {
            case "titre":
                req = " SELECT * FROM blog  Order By `titre`";
                break;
            case "auteur":
                req = " SELECT * FROM blog  Order By `auteur`";
                break;
            case "date_publication":
                req = " SELECT * FROM blog  Order By `date_publication`";
                break;
            default:
                req = " SELECT * FROM blog  Order By `id_blog`";

        }

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id_blog"), rs.getString("titre"), ps.afficherUserbyID(rs.getInt("ID_Auteur")), rs.getDate("date_publication"), rs.getString("contenu"), rs.getString("etiquette"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 }
