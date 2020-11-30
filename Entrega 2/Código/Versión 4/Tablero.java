/**
 * En esta clase se mantiene la información del tablero
 * cellMatrix es una matriz (arreglo de dos dimensiones) de Celdas
 * Es necesario tener una referencia a juego para poder acceder
 * a la información del pacman
 * @author Dayana Arrieta & Juan José Quintana
 */
import java.util.*;

public class Tablero {
    private static final int DIMENSIONS_LINE = 0;
    private static final int PUZZLE_FIRST_LINE = 1;
    private static final int PACMAN_INFO_LINE = 11;
    private static final int OUTPUT_INFO_LINE = 17;
    private static final int FANTASMA_INFO_LINE1 = 18;
    private static final int FANTASMA_INFO_LINE2 = 19;
    private static final char WALL = '*';
    private static final char HALL = ' ';
    private static final double AREPITA_THRESHOLD = 0.9;
    private Pacman pacman;
    private List<Fantasma> fantasmas;
    /**
     * Matriz de la celda
     */
    Celda[][] cellMatrix;
    /**
     * número de las filas
     */
    int numFilas;
    /**
     * número de las columnas
     */
    int numCols;
    /**
     * información del laberinto
     */
    String[] puzzleInfo = {
            "15 17",
            "*****************",
            "*               *",
            "* ****** ****** *",
            "* *    * *    * *",
            "*               *",
            "* *    * *    * *",
            "* ****** ****** *",
            "*               *",
            "* ****** ****** *",
            "* *    * *    * *",
            "*               *",
            "* *    * *    * *",
            "* ****** ****** *",
            "*               *",
            "*****************",
            "P 0 1",
            "O 13 15",
            "F 1 15",
            "F 3 4"

    };
    /**
     * Constructor
     * Se recibe la referencia al juego, para poder acceder al pacman
     * Se lee el "archivo" con la información del laberinto, la posición
     * del pacman y la salida
     * @param
     */

    public Tablero(){
        this.fantasmas = new ArrayList<>();
        this.readPuzzleInfo();
    }

    /**
     * En este método se definen las dimensiones del laberinto
     * Se crea la matriz de la celda con los datos de filas y cols
     */

    private void setDimensions(){
        String dimensionsLine = this.puzzleInfo[DIMENSIONS_LINE];
        Scanner lineScan = new Scanner(dimensionsLine);
        this.numFilas = lineScan.nextInt();
        this.numCols = lineScan.nextInt();
        this.cellMatrix = new Celda[this.numFilas][this.numCols];
    }

    /**
     * En este método se crean las celdas
     */

    private void createCells(){
        int row;
        int column;
        int i = PUZZLE_FIRST_LINE;
        for(row = 0; row < this.numFilas; ++row) {
            String puzzleLine = this.puzzleInfo[i];
            ++i;

            for(column = 0; column < this.numCols; ++column) {
                char cell = puzzleLine.charAt(column);
                if (cell == WALL) {
                    this.cellMatrix[row][column] = Celda.createWallCell();
                }

                if (cell == HALL) {
                    this.cellMatrix[row][column] = Celda.createHallCell();
                }
            }
        }
    }
    /**
     * En este método se crean las celdas con arepitas
     * @return celda
     */

    private Celda createHallCellWithArepita(){
        Celda cell = Celda.createHallCell();
        double arepitaProbability = Math.random();
        Celda.Arepita arepita = new Celda.Arepita();
        if(arepitaProbability > AREPITA_THRESHOLD) {
            arepita.setBadArepita(true);
        }
        cell.setArepita(arepita);
        return cell;
    }
    /**
     * En este método se verifican las celdas con arepitas
     * @return celda con arepita
     */
    public boolean cellHasArepita(int row, int column){
        return this.cellMatrix[row][column].arepita != null;
    }
    /**
     * En este método se verifican las celdas con arepitas
     * explosivas
     * @return celda con arepita explosiva
     */
    public boolean cellHasBadArepita(int row, int column){
        if(!cellHasArepita(row, column)) {
            return false;
        }
        return this.cellMatrix[row][column].arepita.isBadArepita();
    }
    /**
     * En este método se remueven las arepitas de la celda
     */
    public void removeArepitaFromCell(int row, int column){
        this.cellMatrix[row][column].arepita = null;
    }
    /**
     * En este método se muestra el pacman(get)
     * @return pacman
     */
    public Pacman getPacman(){
        return pacman;
    }
    /**
     * En este método se muestran los fantasmas
     * @return pacman
     */
    public List<Fantasma> getFantasmas() {
        return fantasmas;
    }
    /**
     * En este método dice si la celda es pared
     * @return celda con pared
     */

    public boolean cellIsWall(int row, int column){
        return  this.cellMatrix[row][column].isWall;
    }
    /**
     * En este método dice si la contiene un caracter
     * @return celda con caracter
     */
    public boolean cellHasCharacter(int row, int column) {
        return this.cellMatrix[row][column].caracter != null;
    }
    /**
     * En este método dice si la contiene un fantasma
     * @return falso y representación del fantasma
     */
    public boolean cellHasFantasma(int row, int column) {
        if (this.cellMatrix[row][column].caracter == null) {
            return false;
        }
        return this.cellMatrix[row][column].caracter.representacion == Fantasma.REPRESENTATION;
    }

    /**
     * En este método dice si la celda contiene la salida
     * @return
     */
    public boolean cellIsOutput(int row, int column){
        return this.cellMatrix[row][column].esSalida;
    }
    /**
     * En este método dice se muestra la información del fantasma
     */
    private void createFantasma(int infoline){
        String fantasmaInfoline = this.puzzleInfo[infoline];
        Scanner lineScan = new Scanner(fantasmaInfoline.substring(1));
        int row = lineScan.nextInt();
        int column = lineScan.nextInt();
        Posicion posicion = new Posicion(row, column);
        Fantasma fantasma = new Fantasma(posicion);
        this.fantasmas.add(fantasma);
        this.cellMatrix[posicion.fila][posicion.col].caracter = fantasma;
    }
    /**
     * En este método dice se muestra la información del pacman
     */
    private void createPacman(){
        //String pacmanInfoLine = this.puzzleInfo[PACMAN_INFO_LINE];
        //Scanner lineScan  = new Scanner(pacmanInfoLine.substring(1));
        //int row = lineScan.nextInt();
        //int column = lineScan.nextInt();
        Posicion posicion = new Posicion(0, 1);
        this.pacman = new Pacman(posicion, Juego.PUNTOS_VIDA_INICIALES);
        this.cellMatrix[posicion.fila][posicion.col].caracter = this.pacman;
        removeArepitaFromCell(posicion.fila, posicion.col);
    }
    /**
     * Este método establece la salida
     */
    private void setOutput(){

        this.cellMatrix[8][7].esSalida = true;
    }
    /**
     * Este método lee la información del laberinto
     * Organiza los demás métodos de información del laberinto
     */
    private void readPuzzleInfo() {
        setDimensions();
        createCells();
        createPacman();
        setOutput();
        //createFantasma(FANTASMA_INFO_LINE1);
        //createFantasma(FANTASMA_INFO_LINE2);
    }

    /**
     * En este método se dibuja el tablero.
     * A cada celda se le invoca el métoco "caracterCelda", que devuelve
     * un caracter que representa el contenido de la celda.
     */

    public void dibujarTablero() {
        String s = "";

        for(int fila = 0; fila < this.numFilas; ++fila) {
            for(int col = 0; col < this.numCols; ++col) {
                s = s + this.cellMatrix[fila][col].caracterCelda();
            }

            s = s + "\n";
        }

        System.out.println(s);
    }
}