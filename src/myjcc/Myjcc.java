package myjcc;

import Models.Film;
import Models.Salle;
import Services.FilmService;
import Services.SalleService;
import Utils.MaConnection;
import java.sql.Connection;
import java.util.Date;

public class Myjcc {

    public static void main(String[] args) {
        
          FilmService fs = new FilmService();
          SalleService ss = new SalleService();
          Film f = new Film("The dark knight", "2013","action","the best of all time", "2:03", (float) 50.00, "chris Nolan", "bruce wayne");
          Film f2 = new Film("The Shawshank Redemption ", "1994","action","the best of all time", "2:03", (float) 50.00, "Frank Darabont", "Morgan Freeman");
          Film f3 = new Film("The Godfather ", "1972","drama","the best of all time", "2:03", (float) 50.00, "Francis Ford Coppola", "Al Pacino");
          
          Salle s = new Salle("lafayette", "lafayette", 1000);
          Salle s1 = new Salle("moncefbay", "moncefbay", 1000);
          Salle s2 = new Salle("esprit", "esprit", 1000);
          Salle s3 = new Salle("myu", "myu", 1000);
          
          //ss.ajouterSalle(s);
          //ss.ajouterSalle(s1);
          //ss.ajouterSalle(s2);
          //ss.ajouterSalle(s3);
          //fs.ajouterFilm(f);
          //fs.ajouterFilm(f2);
          //fs.ajouterFilm(f3);
          
          //ss.afficherSalle().forEach(System.out::println);
          //fs.afficherFilm().forEach(System.out::println);
          //fs.supprimerFilm("The dark knight");
           //fs.updateFilm(f2);
          
         //ss.supprimerSalle("lafayette");
         
         //ss.updateSalle(s1);
         
        System.out.println(ss.GetSalleByID(15));
         //System.out.println(ss.GetSalleByName("esprit"));
         
         //System.out.println(fs.GetFilmById(3));
         
         //System.out.println(fs.GetFilmByTitre("Shrek"));
         
         
         //fs.GetFilmByGenre("action").forEach(System.out::println);
         
         
         //fs.GetFilmByProducteur("chris nolan").forEach(System.out::println);
         
         
         //System.out.println(ss.GetSalleByAdresse("esprit"));
         
          
        
        
    }
    
}
