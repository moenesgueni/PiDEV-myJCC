/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.User;
import java.util.List;
import util.Type;

/**
 *
 * @author youssef
 */
public interface UserInterface {



    //CRUD
    //1:Create
    public void ajouterUser(User p);
    
    //1.1:Create methode2
    public void ajouterUser2(User p);
    //2: Read
     public void modifierUser(Integer id, User u);
    //3:Update
    public void supprimerUser(int id);
    //4:Delete
    public List<User> afficherUser();
    public User afficherUserbyID(int id);
    public User SearchByMail(String MAIL);
    
    public List<User> FiltrerParRole(Type role);
  
}