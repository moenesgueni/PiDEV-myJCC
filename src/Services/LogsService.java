/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.LogsInterface;
import Models.LOGS;
import Utilities.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;


/**
 *
 * @author moene
 */
/**/public class LogsService implements LogsInterface {
    Connection cnx = MaConnexion.getInstance().getCnx();
    
    @Override
    public void AjouterLogs(LOGS L) {
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);
        String req = "INSERT INTO `logs`(`Date`, `Action`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

           // ps.setInt(1, L.getID_Logs());
            //ps.setInt(1,L.getID_User());
            ps.setDate(1,date);
            ps.setString(2,L.getAction());
            ps.executeUpdate();
            System.out.println("Log ajouté avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(LogsService.class.getName()).log(Level.SEVERE, null, ex);
        }   }

    @Override
    public List<LOGS> afficherLogs() {
        List<LOGS> logs = new ArrayList<>();
        String request = "SELECT * FROM logs";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                LOGS L = new LOGS();
                L.setID_User(rs.getInt(1));
                L.setID_Logs(rs.getInt(2));
                L.setDate(rs.getDate("Date"));
                L.setAction(rs.getString("Action"));

                //
                logs.add(L);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logs;    }

    @Override
    public List<LOGS> FiltrerParID(int iduser) {
    List<LOGS> logs = new ArrayList<>();
        String request = "SELECT * FROM logs WHERE ID_User = '"+iduser+"';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                LOGS L = new LOGS();
                L.setID_User(rs.getInt(1));
                L.setID_Logs(rs.getInt(2));
                L.setDate(rs.getDate("Date"));
                L.setAction(rs.getString("Action"));

                //
                logs.add(L);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logs;    }



    @Override
    public List<LOGS> FiltrerParAction(String Action) {
    List<LOGS> logs = new ArrayList<>();
        String request = "SELECT * FROM logs WHERE Action = '"+Action+"';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                LOGS L = new LOGS();
                L.setID_User(rs.getInt(1));
                L.setID_Logs(rs.getInt(2));
                L.setDate(rs.getDate("Date"));
                L.setAction(rs.getString("Action"));

                //
                logs.add(L);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logs;      }

    @Override
    public void Supprimer(int idlog) {
        LOGS L = new LOGS();
        String request = "DELETE FROM `logs` WHERE `ID_Logs` ="+idlog+";";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);

        } catch (SQLException ex) {
            Logger.getLogger(LogsService.class.getName()).log(Level.SEVERE, null, ex);
        }       }

    @Override
    public List<LOGS> FiltrerParDate(java.sql.Date DateLog) {
    List<LOGS> logs = new ArrayList<>();
        String request = "SELECT * FROM logs WHERE Date = '"+DateLog+"';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                LOGS L = new LOGS();
                L.setID_User(rs.getInt(1));
                L.setID_Logs(rs.getInt(2));
                L.setDate(rs.getDate("Date"));
                L.setAction(rs.getString("Action"));

                //
                logs.add(L);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logs;     }

    
}
