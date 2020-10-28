public class Pacman extends Caracter {
    int lifePoints;
    private static final char REPRESENTATION = '^';
    public Pacman(Posicion posicion, int lifePoints) {
        super(posicion, REPRESENTATION);
        this.lifePoints = lifePoints;
    }
}