/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.PlanningInterface;
import Models.Film;
import Models.PlanningFilmSalle;
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
public class PlanningService implements PlanningInterface {
    
    Connection cnx = MaConnection.getInstance().getCnx();
    
   FilmService fs = new FilmService();
   SalleService ss = new SalleService();

    @Override
    public void ajouterPlanning(PlanningFilmSalle p) {
        String req = "INSERT INTO `planningfilmsalle`(`ID_film`, `ID_salle`, `Datediffusion`, `Heurediffusion`) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ft = cnx.prepareStatement(req);
            ft.setInt(1,p.getFilm().getID_film());
            ft.setInt(2,p.getSalle().getID_salle());
            ft.setDate(3, p.getDatediffusion());
            ft.setString(4, p.getHeurediffusion());
            ft.executeUpdate();
            System.out.println("Planning ajouté avec success!!");
        }
        catch (SQLException ex) {
            Logger.getLogger(PlanningService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<PlanningFilmSalle> afficherPlanning() {
        List<PlanningFilmSalle> plannings = new ArrayList<>();
        String req = "SELECT * from `planningfilmsalle`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                PlanningFilmSalle p = new PlanningFilmSalle();
                p.setID_planning(rs.getInt(1));
                p.setFilm(fs.GetFilmById(rs.getInt(2)));
                p.setSalle(ss.GetSalleByID(rs.getInt(3)));
                p.setDatediffusion(rs.getDate(4));
                p.setHeurediffusion(rs.getString(5));

                
                plannings.add(p);
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanningService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plannings;
    }

    @Override
    public void updatePlanning(PlanningFilmSalle p) {
        String req = "UPDATE `planningfilmsalle` SET `ID_film`= ?,`ID_salle`= ?,`Datediffusion`= ?,`Heurediffusion`= ? WHERE `ID_Planning`= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getFilm().getID_film());
            ps.setInt(2, p.getSalle().getID_salle());
            ps.setDate(3, p.getDatediffusion());
            ps.setString(4, p.getHeurediffusion());
            //
            ps.executeUpdate();
            System.out.println("Planning modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(PlanningService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerPlanning(int id) {
       String req = "DELETE FROM `planningfilmsalle` WHERE `ID_Planning`= ?";
       Statement st;
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            //
            ps.executeUpdate();
            System.out.println("Planning supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(PlanningService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<PlanningFilmSalle> GetPlanningByFilm(int id){
         List<PlanningFilmSalle> plannings = new ArrayList<>();
        String req = "SELECT * from `planningfilmsalle` WHERE `ID_film`="+id   ;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                PlanningFilmSalle p = new PlanningFilmSalle();
                p.setID_planning(rs.getInt(1));
                p.setFilm(fs.GetFilmById(rs.getInt(2)));
                p.setSalle(ss.GetSalleByID(rs.getInt(3)));
                p.setDatediffusion(rs.getDate(4));
                p.setHeurediffusion(rs.getString(5));

                
                plannings.add(p);
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanningService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plannings;
    }
    @Override
    public PlanningFilmSalle GetPlanningByID(int id) {
        PlanningFilmSalle p = new PlanningFilmSalle();
        String request ="SELECT * FROM `planningfilmsalle` WHERE `ID_Planning` ="+id; 
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                p.setID_planning(rs.getInt(1));
                p.setFilm(fs.GetFilmById(rs.getInt(2)));
                p.setSalle(ss.GetSalleByID(rs.getInt(3)));
                p.setDatediffusion(rs.getDate(4));
                p.setHeurediffusion(rs.getString(5));
              
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(PlanningService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return p;
    }

    @Override
    public List<PlanningFilmSalle> GetPlanningByDate(Date date) {
        List<PlanningFilmSalle> plannings = new ArrayList<>();
        String req = "SELECT * from `planningfilmsalle` WHERE `Datediffusion`= '"+date+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                PlanningFilmSalle p = new PlanningFilmSalle();
                p.setID_planning(rs.getInt(1));
                p.setFilm(fs.GetFilmById(rs.getInt(2)));
                p.setSalle(ss.GetSalleByID(rs.getInt(3)));
                p.setDatediffusion(rs.getDate(4));
                p.setHeurediffusion(rs.getString(5));

                
                plannings.add(p);
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanningService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plannings;
    }
    
}
