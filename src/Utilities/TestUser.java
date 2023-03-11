/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author moene
 */
public class TestUser {
      // Vérification du nom et prénom
    public static boolean verifierNomPrenom(String texte) {
        // Le texte ne doit pas être vide
        if (texte == null || texte.trim().isEmpty()) {
            return false;
        }
        // Le texte ne doit contenir que des lettres, des tirets et des espaces
        Pattern pattern = Pattern.compile("[a-zA-ZÀ-ÿ\\-\\s]+");
        Matcher matcher = pattern.matcher(texte);
        return matcher.matches();
    }

    // Vérification du mot de passe
    public static boolean verifierMotDePasse(String texte) {
        // Le mot de passe doit contenir au moins 8 caractères, au moins une lettre majuscule, une lettre minuscule et un chiffre
        if(texte.length()>8){
        return true;}
        else{
            return false;
        }
    }

    // Vérification de l'adresse e-mail
    public static boolean verifierAdresseEmail(String texte) {
        // L'adresse e-mail doit être valide selon la RFC 5322
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(texte);
        return matcher.matches();
    }
}
