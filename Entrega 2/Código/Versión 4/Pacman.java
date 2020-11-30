/**
 * En esta clase se guarda la información acerca del Pacman
 * El Pacman extiende la clase vCaracter, y por lo tanto hereda sus
 * atributos y métodos
 * @author Dayana Arrieta & Juan José Quintana
 */

public class Pacman extends Caracter {
    /**
     * puntos de vida(lifepoints) del pacman
     */
    int lifePoints;
    private static final char REPRESENTATION = '^';
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public int direction = LEFT;
    /**
     * puntos de vida(lifepoints) del pacman
     */
    public Pacman(Posicion posicion) {
        super(posicion, REPRESENTATION);
    }
    
    public void changeDirection(){
        direction = (int) Math.floor(Math.random()*4);
    }
    
    public int getDirection(){
        return (int) Math.floor(Math.random()*4);
    }
}
