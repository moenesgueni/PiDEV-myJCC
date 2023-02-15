/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.LOGS;
import java.sql.Date;
//import java.util.Date;
import java.util.List;

/**
 *
 * @author moene
 */
public interface LogsInterface {
   public void AjouterLogs(LOGS L);       
   public List<LOGS> afficherLogs();
   public List<LOGS> FiltrerParID(int idlog);
   public List<LOGS> FiltrerParDate(Date DateLog);
   public List<LOGS> FiltrerParAction(String Action);
   public void Supprimer(int idlog);
}
