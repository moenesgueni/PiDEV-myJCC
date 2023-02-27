/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjcc;


import java.sql.Date;
import Models.Blog;
import Models.Evenement;
import Models.User;
import Services.BlogService;
import Services.EventService;
import Services.UserService;
import Utils.Type;
import java.util.List;



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
    }}
        //System.out.println(bs.getBlogById(2));
        //Update
        //bs.ModifierBlog(b2);
        //Delete
        //bs.SupprimerBlog(7);
        /*
             EventService eventController = new EventService();
     Date date = new Date(2022, 23, 2);
           Evenement e1 = new Evenement("jcc1", date, "ariana", "test", "helloimanevent");
        Evenement e2 = new Evenement("jcc2", date, "ariana", "test", "hello im an event");
        Evenement e3 = new Evenement("jcc3", date, "ariana", "test", "hello im an event");
        Evenement e4 = new Evenement("jcc4", date, "ariana", "test", "hello im an event");
        Evenement e5 = new Evenement("jcc5", date, "ariana", "test", "hello im an event");

        eventController.AjouterEvent(e1);
        eventController.AjouterEvent(e2);
        eventController.AjouterEvent(e3);
        eventController.AjouterEvent(e4);
        eventController.AjouterEvent(e5);
        
       List<Evenement> list =  eventController.AfficherEvents();
       
        System.out.println(list);
    }*/

