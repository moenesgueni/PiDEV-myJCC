/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Models.Film;
import java.util.List;
/**
 *
 * @author dhia
 */
public interface FilmInterface {
    
    public void ajouterFilm(Film f);
    
    
    public List<Film> getFilms();
    
    
    public void updateFilm(Film f);
    
    
    public void supprimerFilm(String Titre);
    
    
    public Film GetFilmById(int id);
    
    
    public Film GetFilmByTitre(String Titre);
    
    public List<Film> GetFilmByGenre(String Genre);
    
    public List<Film> GetFilmByProducteur(String Producteur);
    
    
}