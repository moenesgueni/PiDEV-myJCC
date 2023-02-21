/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjcc;


import java.sql.Date;
import Models.Blog;
import Models.User;
import Services.BlogService;
import Services.UserService;
import Utils.Type;



/**
 *
 * @author ASUS
 */
public class MyJCC {

    public static void main(String[] args) {
            Date date = new Date(2022, 23, 2);

          //Creation service galerie
        BlogService bs = new BlogService();
        //creation service user
        UserService ps = new UserService();
        //instance user
        User p1 = new User(1,"Khaled","Tounsi","Male","khaled.tounsi@esprit.tn","azertyuiop123",Type.ADMINSTRATEUR,"C/Path/photo.png");
        User p2 = new User(5,"Marwen","Hammami","Male","marwen.hammami@esprit.tn","azertyuiop123",Type.ADMINSTRATEUR,"C/Path/photo.png");
        ps.ajouterUser(p2);
        ps.ajouterUser(p1);
        //Creation Galerie
        Blog b1 = new Blog("hello",p1, date, "khourafa", "moumathel machhour","test");
        Blog b2 = new Blog("hello",p2, date, "khourafa", "moumathel machhour","test");
        
        //ajout galerie
       // bs.AjouterBlog(b1);
        bs.AjouterBlog(b2);

        
        //Read : Afficher toutes les galeries
        bs.AfficherBlogs().forEach(System.out::println);
        //GetById : Afficher une Galerie
        System.out.println("Affichage de le deuxieme blog---------");
        //System.out.println(bs.getBlogById(2));
        //Update
        //bs.ModifierBlog(b2);
        //Delete
        //bs.SupprimerBlog(7);
        
    }
}
