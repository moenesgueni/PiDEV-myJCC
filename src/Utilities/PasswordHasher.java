/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author moene
 */
public class PasswordHasher {
     public static String hashPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        boolean passwordMatch = BCrypt.checkpw(password, hashedPassword);
        return passwordMatch;
    }
    
}
