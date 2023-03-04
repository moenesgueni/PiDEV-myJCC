/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author moene
 */
public class LOGS {
    int ID_Logs;
    int ID_User;
    Date Date;
    String Action;

    public LOGS() {
    }

    public LOGS(int ID_Logs, int ID_User, Date Date, String Action) {
        this.ID_Logs = ID_Logs;
        this.ID_User = ID_User;
        this.Date = Date;
        this.Action = Action;
    }


    public LOGS(int ID_User, Date Date, String Action) {
        this.ID_User = ID_User;
        this.Date = Date;
        this.Action = Action;
    }


    public LOGS(java.sql.Date Date, String Action) {
        this.Date = Date;
        this.Action = Action;
    }
   
    
    public LOGS(String Action) {
        this.Action = Action;
    }
    
    public int getID_Logs() {
        return ID_Logs;
    }

    public int getID_User() {
        return ID_User;
    }

    public Date getDate() {
        return Date;
    }

    public String getAction() {
        return Action;
    }

    public void setID_Logs(int ID_Logs) {
        this.ID_Logs = ID_Logs;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public void setAction(String Action) {
        this.Action = Action;
    }

    @Override
    public String toString() {
        return "LOGS{" + "ID_Logs=" + ID_Logs + ", ID_User=" + ID_User + ", Date=" + Date + ", Action=" + Action + '}';
    }
    
}
