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
        
       
                
          
          //creation film
          //Film f = new Film("The dark knight", "2013","action","the best of all time", "2:03", (float) 50.00, "chris Nolan", "bruce wayne");
          Film f2 = new Film(3,"The Shawshank Redemption ", "1994","action","the best of all time", "2:03", (float) 50.00, "Frank Darabont", "Morgan Freeman");
          Film f3 = new Film(4,"The Godfather ", "1972","drama","the best of all time", "2:03", (float) 50.00, "Francis Ford Coppola", "Al Pacino");
          Film f4 = new Film("12 Angry Men", "1957","crime","the best of all time1", "1:36", (float) 50.00, "Sidney Lumet", "Martin Balsam");
          
          //creation salle
          //Salle s = new Salle(16,"Cinema la Palace", "1001, Tunis République، 54 Ave Habib Bourguiba, Tunis 1001", 1000, "71 344 755", "lapalace@gmail.com","09:00","20:00",(float) 3.5);
          //Salle s1 = new Salle("Cinema la Palace1", "1001, Tunis République، 54 Ave Habib Bourguiba, Tunis 1001", 1000, "71 344 755", "lapalace@gmail.com","09:00","20:00",(float) 3.5);
         Salle s5 = new Salle(20,"abcDEF", "1001, Tunis République، 54 Ave Habib Bourguiba, Tunis 1001", 1000, "71 344 755", "lapalace@gmail.com","09:00","20:00",(float) 3.5);
          
          //creation planning
          //PlanningFilmSalle p1 = new PlanningFilmSalle (s,f2,date,"19:00");
          //PlanningFilmSalle p2 = new PlanningFilmSalle (15,s,f3,date,"20:00");
          
          
          //ajout salle
          //ss.ajouterSalle(s5);
          //ss.ajouterSalle(s1);
          //ss.ajouterSalle(s2);
          //ss.ajouterSalle(s3);
          
          
          //ajout film
          //fs.ajouterFilm(f4);
          //fs.ajouterFilm(f2);
          //fs.ajouterFilm(f4);
          
          
          //affichage salles
          //ss.afficherSalle().forEach(System.out::println);
          
          
          //delete film
         // fs.supprimerFilm("a");
          
          //update film
          
          //Film f5 = new Film(8,"13 Angry Men", "17","crime","the best of all time1", "1:36", (float) 50.00, "Sidney Lumet", "Martin Balsam");
          //fs.updateFilm(f5);
          
          //affichage films
          //fs.afficherFilm().forEach(System.out::println);
          
          
          //delete salle
         //ss.supprimerSalle("Cinema la Palace1");
         
         
         //update salle
         //ss.updateSalle(s5);
         
         
         
         //getsallebyid
        //System.out.println(ss.GetSalleByID(18));
        
        //getsallebyname
         //System.out.println(ss.GetSalleByName("Cinema la Palace1"));
         
        //getsallebyadresse         
        //System.out.println(ss.GetSalleByAdresse("1001, Tunis République، 54 Ave Habib Bourguiba, Tunis 1001"));
         
         
         //getfilmbyid
         //System.out.println(fs.GetFilmById(3));
         
         
         //getfilmbyname
         //System.out.println(fs.GetFilmByTitre("12 angry men"));
         
         //getfilmbygenre
         //fs.GetFilmByGenre("action").forEach(System.out::println);
         
         //get film by producer
         //fs.GetFilmByProducteur("chris nolan").forEach(System.out::println);
         
         
         
          //ajout planning
          //ps.ajouterPlanning(p1);
          
          //affichage planning
          //ps.afficherPlanning().forEach(System.out::println);
          
          //ps.updatePlanning(p1);
          
          //getplanningbyid
          //System.out.println(ps.GetPlanningByID(1));
          
           //getplanningbyfilm
          //ps.GetPlanningByFilm(3).forEach(System.out::println);
          
          //getplanningbydate
          //ps.GetPlanningByDate(date).forEach(System.out::println);
          
          
        
        
    }
    
}
