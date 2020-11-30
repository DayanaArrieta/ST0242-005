package version2;


 /**
 * En esta clase se guarda la información acerca del fantasma
 * El fantasma se extiende de la clase Caracter, y por lo tanto hereda sus
 * atributos y métodos
 * @author Dayana Arrieta & Juan José Quintana
 */

import java.util.List;
public class Fantasma extends Caracter {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final char REPRESENTATION = 'F';
    public int direction = LEFT;

    public Fantasma(Posicion posicion) {
        super(posicion, REPRESENTATION);
    }
    /**
     * Método que actualiza la posición
     */
    public void updatePosition() {
    }
    /**
     * Método que cambia la dirección del fantasma
     */
    public void changeDirection(){
        direction = (int) Math.floor(Math.random()*4);
    }
    /**
     * Método que obtiene la dirección
     */
    public int getDirection(){
        return (int) Math.floor(Math.random()*4);
    }
}

