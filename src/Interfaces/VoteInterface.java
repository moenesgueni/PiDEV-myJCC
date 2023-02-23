package Interfaces;

import Models.Prix;
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
    public List<Vote> afficherVotes();
    
    public Vote afficherVote(int VoteID);
    
    //3: Filter par type/Film/
    public List<Vote> afficherVoteUser(String RoleUser);
    public List<Vote> afficherVoteFilm(String TitreFilm);
    
    //4: modifier donnee
    //public void modifierVoteFilm(Prix prix);
    //public void modifierVoteType(int voteId , int voteUser );
    
    //5: Suppression par film
    public void suppressionVoteFilm(int ID_Vote);
    
    
}