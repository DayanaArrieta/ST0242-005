package version2;
/**
 * Programa principal del juego
 * @author Dayana Arrieta & Juan José Quintana
 */
public  class Principal {
    /**
     * constructor vacío
     */
    public Principal() {
    }
    /**
     * Método main
     */
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.jugar();
    }
}
