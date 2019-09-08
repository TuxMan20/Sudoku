
package sudoku;

/**
 * Classe qui sert à créer et définir les différentes cases remplies.
 * 
 * @author Louis
 */
public class Case {
    int x; // colones
    int y; // lignes ou rangs
    int v; // valeur
    
    // Constructeur qui reçoit les valeurs 
    public Case(int x, int y, int v){
        this.x = x;
        this.y = y;
        this.v = v;
    }
    
    // Méthodes Get et Set
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getV(){
        return v;
    }
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setV(int v){
        this.v = v;
    }
}
