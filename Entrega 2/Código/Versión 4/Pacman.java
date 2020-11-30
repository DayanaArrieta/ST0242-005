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
    /**
     * puntos de vida(lifepoints) del pacman
     */
    public Pacman(Posicion posicion, int lifePoints) {
        super(posicion, REPRESENTATION);
        this.lifePoints = lifePoints;
    }

    /**
     * Método que accede a los lifePoints
     * retorna los puntos de vida "lifePoints"
     */

    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * Método que incrementa los puntos de vida
     * retorna los puntos de vida "lifePoints" aumentados
     */
    public void increasesLifePoints() {
        ++lifePoints;
    }

    /**
     * Método que reduce los puntos de vida
     * retorna los puntos de vida "lifePoints" reducidos
     */

    public void reduceLifePoints(int points) {
        lifePoints = (lifePoints-points);
    }
}