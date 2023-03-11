/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import Models.Commentaire;
/**
 *
 * @author ASUS
 */
public interface CommentaireInterface {
    
    
    public void AjouterCommentaire(Commentaire object);

    public void ModifierCommentaire(Commentaire object);

    public void SupprimerCommentaire(Commentaire object);

    public List<Commentaire> RechercherCommentaire(String object);

    public List<Commentaire> AfficherCommentaire(int _id_blog);

    public Commentaire getCommentaireById(int object);

    public List<Commentaire>  TrieBlog(String object);


}
