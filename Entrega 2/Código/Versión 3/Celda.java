package version2;

/**
 * En esta clase se guarda la información de una celda y su contenido
 * @author Dayana Arrieta & Juan José Quintana
 */

public class Celda {
    /**
     * field de la pared
     */
    boolean isWall;
    /**
     * field de la salida
     */
    boolean esSalida;
    /**
     * field de la arepita
     */
    Arepita arepita;
    /**
     * field de la pared
     */

    Caracter caracter;
    /**
     * Esta clase es una anidación de la clase celda
     * se guarda la información de las arepitas
     * explosivas o no explosivas
     */
    public static class Arepita{
        boolean badArepita;
        public void setBadArepita(boolean badArepita) {
            this.badArepita = badArepita;
        }

        public boolean isBadArepita() {
            return badArepita;
        }
    }
    /**
     * Constructor de la clase
     * sin parametros
     */
    public Celda() {

    }

    /**
     * Método que define la arepita
     * void(sin retorno)
     */
    public void setArepita(Arepita arepita) {
        this.arepita = arepita;
    }

    /**
     * Método que crea la pared en la celda
     * @return celda con pared
     */
    public static Celda createWallCell(){
        Celda cell = new Celda();
        cell.isWall = true;
        return cell;
    }

    /**
     * Método que crea la pared en la celda
     * @return new celda
     */
    public static Celda createHallCell(){
        return new Celda();
    }

    /**
     * Retorna el caracter con el que se representa esta celda
     * @return caracter que representa el contenido de la celda
     */

    public char caracterCelda() {
        if (this.isWall) {
            return '*';
        }
        else if (this.caracter != null) {
            return this.caracter.representacion;
        }
        else if(this.esSalida) {
            return 'O';
        }
        else if (arepita != null) {
            return '.';
        }
        return ' ';
    }
}