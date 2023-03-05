/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.ReservationInterface;
import Models.PlanningFilmSalle;
import Models.Reservation;
import Utils.MaConnection;
import java.sql.Connection;
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
public class ReservationService implements ReservationInterface {
Connection cnx = MaConnection.getInstance().getCnx();
PlanningService ps = new PlanningService();
UserService us = new UserService();
    @Override
    public void ajouterReservation(Reservation r) {
        String req = "INSERT INTO `reservation`(`id_plan`, `id_user`) VALUES (?, ?)";
        try {
            PreparedStatement ft = cnx.prepareStatement(req);
            ft.setInt(1,r.getPlanning().getID_planning());
            ft.setInt(2,r.getUser().getID_User());
            ft.executeUpdate();
            System.out.println("Reservation ajouté avec success!!");
        }
        catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reservation> afficherReservation() {
        List<Reservation> reservations = new ArrayList<>();
        String req = "SELECT * from `reservation`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Reservation r = new Reservation();
                r.setID_Reservation(rs.getInt(1));
                r.setPlanning(ps.GetPlanningByID(rs.getInt(2)));
                r.setUser(us.afficherUserbyID(rs.getInt(3)));
                

                
                reservations.add(r);
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservations;
    }

    @Override
    public void updateReservation(Reservation r) {
       String req = "UPDATE `reservation` SET `id_plan`= ?,`id_user`= ? WHERE `id_res`= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getPlanning().getID_planning());
            ps.setInt(2, r.getUser().getID_User());
            ps.setInt(3, r.getID_Reservation());
            //
            ps.executeUpdate();
            System.out.println("Reservation modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerReservation(int id) {
        String req = "DELETE FROM `reservation` WHERE `id_res`= ?";
       Statement st;
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            //
            ps.executeUpdate();
            System.out.println("reservation supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Reservation GetReservationById(int id) {
        Reservation r = new Reservation();
        String request ="SELECT * FROM `reservation` WHERE `id_res` ="+id; 
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                r.setID_Reservation(rs.getInt(1));
                r.setPlanning(ps.GetPlanningByID(rs.getInt(2)));
                r.setUser(us.afficherUserbyID(rs.getInt(3)));
                
              
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return r;
    }

    @Override
    public List<Reservation> GetReservationByPlanning(int id) {
      List<Reservation> reservations = new ArrayList<>();
        String req = "SELECT * from `reservation` WHERE `id_plan`="+id   ;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Reservation r = new Reservation();
                 r.setID_Reservation(rs.getInt(1));
                r.setPlanning(ps.GetPlanningByID(rs.getInt(2)));
                r.setUser(us.afficherUserbyID(rs.getInt(3)));
                

                
                reservations.add(r);
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanningService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservations; 
    }
}
    

