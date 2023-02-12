/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Vote;
import java.util.List;

/**
 *
 * @author wael
 */
public interface VoteInterface {
    //CRUD
    //1:Create
    public void ajouterVote(Vote v);
    
    //2: Read
    public List<Vote> afficherVote();
    
    //3: Filter par type/Film/
    public List<Vote> afficherVoteUser(int VoteUser);
    public List<Vote> afficherVoteFilm(int VoteFilm);
    
    //4: Suppression par film
    public void suppressionVote(int VoteFilm);
    
    
}
