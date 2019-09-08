/*
 *  
 */
package sudoku;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe qui sert à contenir toutes les méthodes qui prennent une entrée au
 * clavier de l'utilisateur
 * 
 * @author Louis
 */
public class User {
   
    public User(){
        
    }
    
    // Méthode de menu afin de demander un chiffre à l'utilisateur
    public static int getInt(){
        Scanner k = new Scanner(System.in);
        int usrInt = 0;
        try{
            usrInt = k.nextInt(); 
        } catch (InputMismatchException i){ // Si l'entrée est autre chose qu'un Int (lettres ou nombre TRÈS grand), 
                                            //une erreur est affichée
            System.out.println("ERREUR: Caractères numériques seulement");
        }
        return usrInt;
    }

    // Méthode afin de demander une chaîne de charactère à l'utilisateur
     public static String getString(){
        Scanner k = new Scanner(System.in);
        
        String usrStr = k.nextLine();

        return usrStr;
        
    }
     
    // Méthode pour vider l'écran de texte: https://stackoverflow.com/questions/2979383/java-clear-the-console
    // Ne semble pas fonctionner dans la console de Netbeans
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }   
     
}

