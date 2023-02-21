/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import Models.Evenement;

/**
 *
 * @author ASUS
 */
public interface IEventController {
     public void AjouterEvent(Evenement object);

    public void ModifierEvent(Evenement object);

    public void SupprimerEvent(Evenement object);

    public List<Evenement> RechercherEvent(String object);

    public List<Evenement> AfficherEvents();

    public Evenement getEventById(int object);

    public List<Evenement>  TrieEvent(String object);

}
