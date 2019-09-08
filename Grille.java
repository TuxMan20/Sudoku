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
public class Grille {
    
     // Grille du jeu: Tableau 9x9 initialisé à 0, représentant les cases vides
    int[][] grille = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
    ArrayList<Case> grilleListe;    
      
    public Grille(ArrayList<Case> liste){
        for (Case chaque : liste){
            this.grille[chaque.y][chaque.x] = chaque.v;
        }
    }
    
    public void imprimerGrille(){
        int x = 0;
        int y = 0; // Variables qui suivront les positions de la grille
        /*
            Le but est d'incrémenter X et Y seulement lorsque l'on passe à
            la rangée ou la colone suivante. Nous les utiliserons pour trouver
            les bonnes positions des cases occupées.
        */
        
        // Ces boucles imbriquées vont dessiner la grille du Sudoku
        // Motif inspiré de https://lh3.googleusercontent.com/-_8Z9dXgUa8g/VplrexUcDUI/AAAAAAAAI6w/LEbJcIOk57o/s800-Ic42/sudoku.O3k4.gif
        // Imprimer la ligne des numéros de colones 
            int numCol = 1;
            System.out.printf("   ");
            for (int i = 0; i < 3; i++){
                System.out.printf(" ");
                for (int j = 0; j < 3; j++){
                    System.out.printf("%d ", numCol);
                    numCol += 1;
                }
                System.out.printf(" ");
            }
            System.out.printf("\n");
            
        for (int n = 0; n < 3; n++){
                        
            // Imprimer la première ligne décorative de la grille
            System.out.printf("  ");
            for (int i = 0; i < 3; i++){
                System.out.printf("+");
                for (int j = 0; j < 7; j++){
                    System.out.printf("-");
                }
            }
            System.out.printf("+\n");

            // Imprimer 3 lignes de cases & décorations
            for (int i = 0; i < 3; i++){
                System.out.printf("%d ", y+1); // Imprime les numéros de rangées
                for (int j = 0; j < 3; j++){
                    System.out.printf("|");
                    for (int k = 0; k < 3; k++){
                        System.out.printf(" ");
                      
                        // Si c'est la position d'une Case Pleine, remplacer le "." par la valeur
                        if (grille[y][x] > 0){
                            System.out.printf("%d", grille[y][x]);
                        }else {
                            System.out.printf("."); 
                        }
                        
                        x = (x + 1) % 9; // Garder le compte des colones pouvant accepter des valeurs
                    }
                    System.out.printf(" ");
                }
                System.out.printf("|\n");
                y = y + 1; // Garder le compte des rangs pouvant accepter des valeurs 

            }
        }
        // Imprimer la ligne décorative du bas de la grille
        System.out.printf("  ");
        for (int i = 0; i < 3; i++){
            System.out.printf("+");
            for (int j = 0; j < 7; j++){
                System.out.printf("-");
            }
        }
        System.out.printf("+\n");
    }
    
    
    public int estSurColonne(int x, int v){ 
        for (int i = 0; i < 9;i++){
            if (grille[i][x] == v){
                return 1;
            }
        }
        return 0;
    }
    
    public int estSurLigne(int y, int v){ 
        for (int i = 0; i < 9;i++){
            if (grille[y][i] == v){
                return 1;
            }
        }
        return 0;
    }
    
    public int estDansLeBloc(int x,int y,int v){
        //Il faut premièrement savoir où cette case se trouve dans son bloc
        int ajustementX = x%3;
        int ajustementY = y%3;
        int xTemp = x - ajustementX;
        int yTemp = y - ajustementY;
        
        // Ensuite on peut itérer sur les 9 cases en commençant par la case
        // d'en haut à gauche de ce bloc
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (grille[yTemp+i][xTemp+j] == v){
                return 1;
                 }
            }
        }
                
        return 0;
    }
    
    public ArrayList<Case> grilleToArrayList(){
        ArrayList<Case> liste = new ArrayList<>();
        
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (grille[i][j] != 0)
                    liste.add(new Case(j, i, grille[i][j]));
            }
        }
        return liste;
    }
}


