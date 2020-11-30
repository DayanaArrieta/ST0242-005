/**
 * Información acerca del Caracter
 * @author Dayana Arrieta & Juan José Quintana
 */

public class Caracter {
    protected Posicion posicion;
    protected char representacion;

    /**
     * Constructor
     * @param posicion posición actual del caracter en fila y columna
     * @param representacion  muestra la  este caracter
     */

    public Caracter(Posicion posicion, char representacion) {
        this.posicion = posicion;
        this.representacion = representacion;
    }
}