/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.FilmInterface;
import Models.Film;
import Models.Salle;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dhia
 */
public class FilmService implements FilmInterface{
    
    Connection cnx = MaConnection.getInstance().getCnx();

    @Override
    public void ajouterFilm(Film f) {
        String req = "INSERT INTO `film`(`Titre`, `DateRealisation`, `Genre`, `Resume`, `Duree`, `Prix`, `ID_producteur`, `Acteur`) VALUES (?,?,?,?,?,?,?,?)";
        
         try {
            PreparedStatement ft = cnx.prepareStatement(req);
            ft.setString(1, f.getTitre());
            ft.setString(2, f.getDateRealisation());
            ft.setString(3, f.getGenre());
            ft.setString(4, f.getResume());
            ft.setString(5, f.getDuree());
            ft.setFloat(6, f.getPrix());
            ft.setString(7, f.getID_producteur());
            ft.setString(8, f.getActeur());
            ft.executeUpdate();
            System.out.println("Film ajouté avec success!!");
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Film> afficherFilm() {
        List<Film> Films = new ArrayList<>();
        String request = "SELECT * FROM Film";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                Film f = new Film();
                f.setID_film(rs.getInt(1));
                f.setTitre(rs.getString(2));
                f.setDateRealisation(rs.getString(3));
                f.setGenre(rs.getString(4));
                f.setResume(rs.getString(5));
                f.setDuree(rs.getString(6));
                f.setPrix(rs.getFloat(7));
                f.setID_producteur(rs.getString(8));
                f.setActeur(rs.getString(9));
                //
                Films.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Salle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Films;
    }

    @Override
    public void updateFilm(Film f) {
        String request = "UPDATE `film` SET `ID_film`=?,`Titre`= ?,`DateRealisation`= ?,`Genre`= ?,`Resume`= ?,"
                + "`Duree`= ?,`Prix`= ?,`ID_producteur`= ?,`Acteur`= ? WHERE Titre = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1,f.getID_film());
            ps.setString(2, f.getTitre());
            ps.setString(3, f.getDateRealisation());
            ps.setString(4, f.getGenre());
            ps.setString(5, f.getResume());
            ps.setString(6, f.getDuree());
            ps.setFloat(7, f.getPrix());
            ps.setString(8, f.getID_producteur());
            ps.setString(9, f.getActeur());
             
            System.out.println("Film modifiée avec success!!!");
}catch (SQLException ex) {
            Logger.getLogger(Film.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @Override
    public void supprimerFilm(String Titre) {
        String request = "DELETE FROM Film WHERE Titre = ?";
       try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1,Titre );
            ps.executeUpdate();
            System.out.println("Film supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(Film.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   @Override
    public Film GetFilmById(int id){
    Film f = new Film();
        String request = "SELECT * FROM Film WHERE `ID_film` ="+id+";";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                
            f.setID_film(rs.getInt(1));
                f.setTitre(rs.getString(2));
                f.setDateRealisation(rs.getString(3));
                f.setGenre(rs.getString(4));
                f.setResume(rs.getString(5));
                f.setDuree(rs.getString(6));
                f.setPrix(rs.getFloat(7));
                f.setID_producteur(rs.getString(8));
                f.setActeur(rs.getString(9));
                
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(Film.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    
    
    }

    
    
    @Override
    public Film GetFilmByTitre(String Titre) {
        Film f = new Film();
        String request = "SELECT * FROM `film` WHERE `Titre` ='"+Titre+"';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                
            f.setID_film(rs.getInt(1));
                f.setTitre(rs.getString(2));
                f.setDateRealisation(rs.getString(3));
                f.setGenre(rs.getString(4));
                f.setResume(rs.getString(5));
                f.setDuree(rs.getString(6));
                f.setPrix(rs.getFloat(7));
                f.setID_producteur(rs.getString(8));
                f.setActeur(rs.getString(9));
                
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(Film.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return f;
    }
    
    @Override
    public List<Film> GetFilmByGenre(String Genre){
    List<Film> Films = new ArrayList<>();
        String request = "SELECT * FROM Film WHERE `Genre` ='"+Genre+"';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                Film f = new Film();
            f.setID_film(rs.getInt(1));
                f.setTitre(rs.getString(2));
                f.setDateRealisation(rs.getString(3));
                f.setGenre(rs.getString(4));
                f.setResume(rs.getString(5));
                f.setDuree(rs.getString(6));
                f.setPrix(rs.getFloat(7));
                f.setID_producteur(rs.getString(8));
                f.setActeur(rs.getString(9));
                Films.add(f);
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(Film.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Films;
    
    
    }
    
    @Override
    public List<Film> GetFilmByProducteur(String producteur){
    List<Film> Films = new ArrayList<>();
        String request = "SELECT * FROM Film WHERE `ID_producteur` ='"+producteur+"';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                Film f = new Film();
            f.setID_film(rs.getInt(1));
                f.setTitre(rs.getString(2));
                f.setDateRealisation(rs.getString(3));
                f.setGenre(rs.getString(4));
                f.setResume(rs.getString(5));
                f.setDuree(rs.getString(6));
                f.setPrix(rs.getFloat(7));
                f.setID_producteur(rs.getString(8));
                f.setActeur(rs.getString(9));
                
              Films.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Film.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Films;
    
    
    }

    
}
