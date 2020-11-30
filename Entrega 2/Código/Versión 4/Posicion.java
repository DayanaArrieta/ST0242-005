/**
 * Esta clase permite guardar la fila y columna de una posición
 * @author Dayana Arrieta & Juan José Quintana
 */
public class Posicion {
    /**
     * field para fila
     */
    int fila;
    /**
     * field para columna
     */
    int col;
    /**
     * Constructor Posicion
     * @Param fila y col
     */
    public Posicion(int fila, int col) {
        this.fila = fila;
        this.col = col;
    }

    public String toString(){
        return fila+":"+col;
    }
}