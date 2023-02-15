package myjcc;

import Models.Film;
import Models.PlanningFilmSalle;
import Models.Salle;
import Services.FilmService;
import Services.PlanningService;
import Services.SalleService;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
    
public class Myjcc {

    public static void main(String[] args) {
      
          FilmService fs = new FilmService();
          SalleService ss = new SalleService();
          PlanningService ps = new PlanningService();
          
           //creation de date
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 01, 15);
        Date date = new Date(calendar.getTimeInMillis());
        
        //System.out.println(date);
        
       
                
          
          
          //Film f = new Film("The dark knight", "2013","action","the best of all time", "2:03", (float) 50.00, "chris Nolan", "bruce wayne");
          Film f2 = new Film(3,"The Shawshank Redemption ", "1994","action","the best of all time", "2:03", (float) 50.00, "Frank Darabont", "Morgan Freeman");
          //Film f3 = new Film("The Godfather ", "1972","drama","the best of all time", "2:03", (float) 50.00, "Francis Ford Coppola", "Al Pacino");
          
          Salle s = new Salle(16,"Cinema la Palace", "1001, Tunis République، 54 Ave Habib Bourguiba, Tunis 1001", 1000, "71 344 755", "lapalace@gmail.com","09:00","20:00",(float) 3.5);
          //Salle s1 = new Salle("Cinema la Palace1", "1001, Tunis République، 54 Ave Habib Bourguiba, Tunis 1001", 1000, "71 344 755", "lapalace@gmail.com","09:00","20:00",(float) 3.5);
          //Salle s2 = new Salle("esprit", "esprit", 1000);
          //Salle s3 = new Salle("myu", "myu", 1000);
          
          
          PlanningFilmSalle p1 = new PlanningFilmSalle (s,f2,date,"20:00");
          
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
          
           //ss.supprimerSalle("Cinema la Palace1");
         
         //ss.updateSalle(s1);
         
        //System.out.println(ss.GetSalleByID(18));
         //System.out.println(ss.GetSalleByName("Cinema la Palace1"));
         
         //System.out.println(fs.GetFilmById(3));
         
         //System.out.println(fs.GetFilmByTitre("Shrek"));
         
         
         //fs.GetFilmByGenre("action").forEach(System.out::println);
         
         
         //fs.GetFilmByProducteur("chris nolan").forEach(System.out::println);
         
         
         //System.out.println(ss.GetSalleByAdresse("1001, Tunis République، 54 Ave Habib Bourguiba, Tunis 1001"));
         
          //ps.ajouterPlanning(p1);
          //ps.afficherPlanning().forEach(System.out::println);
          
          //System.out.println(ps.GetPlanningByID(1)); works
          //ps.GetPlanningByFilm(3).forEach(System.out::println); works
          ps.GetPlanningByDate(date).forEach(System.out::println);
          
        
        
    }
    
}
