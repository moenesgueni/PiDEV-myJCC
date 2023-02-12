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
    
    //4: modifier donnee
    public void modifierVoteFilm(int voteId , int voteFilm );
    public void modifierVoteType(int voteId , int voteUser );
    public void modifierVoteFilmType(int voteId , int voteUser , int voteFilm);
    
    //5: Suppression par film
    public void suppressionVoteFilm(int VoteFilm);
    public void suppressionVoteUser(int VoteUser);
    
    
}
