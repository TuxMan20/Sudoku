/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Louis
 */
public class Jeu {
    
    
    int partieCharge = 0;
    int premiereVue = 1; // Variable qui ne sert qu'à s'assurer que le message de bienvenue n'est vu qu'une seule fois.
   
    public Jeu(){
        
    }
    
    public int getPartieCharge(){
        return this.partieCharge;
    }
    public void setPartieCharge(int choix){
        this.partieCharge = choix;
    }

    
    public int menu(){
        if (premiereVue == 1){ // Affiche le mot de bienvenue seulement la première fois
            User.clearScreen();
            System.out.println("Bienvenue au jeu de Sudoku");
            premiereVue = 0;
        }
        System.out.println("Choisissez parmis les options suivantes:");
        if (partieCharge == 0){ // Si une partie est chargée ou non, le message à l'écran change
            System.out.printf("(1) Charger une partie\n");
        }else{
            System.out.printf("(1) Sauvegarder une partie\n");
        }
        
        System.out.printf("(2) Jouer au Sudoku\n(3) Quitter\n");   
        int usrChoice = 0;
        
        do {
            System.out.print("Choix: ");
            usrChoice = User.getInt();
        } while (usrChoice < 1 || usrChoice > 3);
        
        return usrChoice;
    }
    
    public int chargerJeu(String fichier, ArrayList<Case> liste){
        int x; // Valeurs qui seront ajoutés au tableau de la grille
        int y;
        int v;
        Scanner scan;
        try{
            // Pour l'expérience utilisateur, j'ajoute l'extension automatiquement
            scan = new Scanner(new File(fichier + ".txt"));
        }
        catch (FileNotFoundException f){ 
            System.out.println("\n\nErreur: Fichier inexistant\n");
            return 0; // Booléen 'false' pour la partie chargée
        }
        
        while (scan.hasNext()){
            
            String triolet = scan.next();
            x = Character.getNumericValue(triolet.charAt(0));
            y = Character.getNumericValue(triolet.charAt(1));
            v = Character.getNumericValue(triolet.charAt(2));
            
            liste.add(new Case(x, y, v));
              
        }
        scan.close();
        return 1; // Booléen 'true' pour la partie chargée
        
    }
    
    public void sauvegarderJeu(Grille grille) {
        PrintWriter sortie = null;
        System.out.printf("Entrez le nom du fichier pour enregistrer (Sans l'extension .txt): ");
        String nomFichier = User.getString();
        try{
            File nomFichierComplet = new File(nomFichier + ".txt");
            sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichierComplet)));

        } catch (FileNotFoundException e){
            System.out.println("ERREUR: Le fichier n'a pas pu être créé.\n");
        } catch (IOException i){
            System.out.println("ERREUR: La sauvegarde n'a pas réussi.\n");
           Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, i);
        } finally {
            if (sortie != null){
                ArrayList<Case> liste = grille.grilleToArrayList();
                for (Case chaque: liste){
                    sortie.printf("%d", chaque.x);
                    sortie.printf("%d", chaque.y);
                    sortie.printf("%d", chaque.v);
                    sortie.printf(" ");
                }
                
                sortie.close();
            }
        }
    }
    
    public void jouerSudoku(Grille grille){
        int x; // Valeurs qui seront entrées par le joueur et passées vers la grille
        int y;
        int v;
        grille.imprimerGrille();
        do{
            System.out.printf("Entrez une colonne (1 à 9): ");
            x = User.getInt();
        } while (x < 1 || x > 9);
        do{
            System.out.printf("Entrez une rangée (1 à 9): ");
            y = User.getInt();
        } while (y < 1 || y > 9);
        do{
            System.out.printf("Entrez une valeur (0 à 9): ");
            v = User.getInt();
        } while (v < 0 || v > 9); 
        // Je permets à l'utilisateur d'entrer 0 comme valeur afin de pouvoir effacer une erreur
        if (grille.estSurColonne(x-1,v) == 1 && v != 0)
            System.out.printf("\nLa valeur %d est déjà sur la colonne %d\n\n", v, x);
        else if (grille.estSurLigne(y-1,v) == 1 && v != 0)
            System.out.printf("\nLa valeur %d est déjà sur la ligne %d\n\n", v, y);
        else if (grille.estDansLeBloc(x-1,y-1,v) == 1 && v != 0)
            System.out.printf("\nLa valeur %d est déjà dans le bloc\n\n", v);
        else
            grille.grille[y-1][x-1]=v;

            /* Explications du 'x-1' et 'y-1':
            Pour une meilleure expérience utilisateur, je leur laisse entrer une
            colone et une rangée avec des index de 1 à 9. Je les reconvertis ensuite
            en index de 0 à 8 pour les index du tableau de la grille.
            */
        User.clearScreen();
        grille.imprimerGrille();
    }
    
    // Méthode qui vérifie si le joueur a gagné (Aucunes cases ne contienent la valeur 0)
    public boolean verifierVictoire(Grille grille){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (grille.grille[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
    
     
}
