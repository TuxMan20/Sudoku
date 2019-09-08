/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;
import java.util.ArrayList;

/**
 *
 * @author Louis
 */
public class Sudoku {
    static ArrayList<Case> tableauDeJeu = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        // Création des objets Jeu & Grille et instanciation des méthodes
        Jeu jouer = new Jeu();
        Grille tableau = null;
                
        /* Logique du jeu:///
            Dans une boucle infinie:
                - Afficher le menu, et offrir les 3 choix
                - Si une partie est chargée, afficher la grille de jeu et offrir les 3 choix
                    - Lorsqu'une tentative de remplir une case est faite, on ré-affiche la grille et les 3 choix
                - Si "Quitter" est choisi, demander la permission et terminer le programme
        */
        
        while (true){ 
            switch (jouer.menu()){
                case 1: // Si la partie n'était pas chargée, on va ouvrir le fichier et on change la partie chargée à 1 (vrai)
                        if (jouer.getPartieCharge() == 0){ 
                            System.out.printf("Nom du fichier à charger (Sans l'extension .txt): ");
                            // On fait d'une pierre deux coups: Puisque jouer.charger() retourne 0 ou 1, on peut le combiner à jouer.setPartieCharge()
                            jouer.setPartieCharge(jouer.chargerJeu(User.getString(), tableauDeJeu)); 
                            
                            if (jouer.getPartieCharge() == 1){
                                tableau = new Grille(tableauDeJeu); // Construire le tableau à partir du ArrayList
                                System.out.println("\n\nLa partie est chargée. Vous pouvez maintenant jouer\n");
                            }
                        } else{
                            jouer.sauvegarderJeu(tableau);
                        }
                        
                        break;
                case 2: if (jouer.getPartieCharge() == 0){ 
                            System.out.println("\n\nERREUR: Aucune partie chargée\n");
                            break;
                        } else {
                            User.clearScreen();
                            jouer.jouerSudoku(tableau);
                            if (jouer.verifierVictoire(tableau)){ // Condition de victoire qui ferme le jeu
                                System.out.println("Vous avez gagné!!");
                                System.exit(0);
                            }
                            break;
                        }
                case 3: User.clearScreen();
                        System.out.println("Êtes-vous certains de vouloir quitter? (y) ou (n)");
                        String choix;
                        do { 
                            System.out.print("Choix: ");
                             choix = User.getString();                                
                        } while (!choix.equalsIgnoreCase("y") && !choix.equalsIgnoreCase("n") ); // Comparer le String de 'choix' à "y" ou "n"
                                // méthode equalsIgnoreCase: Programmer en Java, Delannoy, C., 2018, Page 259
                        if (choix.equalsIgnoreCase("y")){
                            System.exit(0); // Quitter avec la valeur 0 (Quitté normalement)  
                        } else {
                            User.clearScreen();
                            break; // Recommencer la liste de choix
                        }               
            }
        }
    }  
}
