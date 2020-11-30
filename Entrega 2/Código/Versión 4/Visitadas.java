/**
 * En esta clase se guarda la información acerca del fantasma
 * El fantasma se extiende de la clase Caracter, y por lo tanto hereda sus
 * atributos y métodos
 * @author Dayana Arrieta & Juan José Quintana
 */

import java.util.List;
public class Visitadas extends Caracter {
    public static final char REPRESENTATION = 'X';
    public Visitadas(Posicion posicion) {
        super(posicion, REPRESENTATION);
    }
}
