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
    private static final int PACMAN_INFO_LINE = 16;
    private static final int OUTPUT_INFO_LINE = 17;
    private static final char WALL = '*';
    private static final char HALL = ' ';
    private Pacman pacman;
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
            "P 1 1",
            "O 13 15",

    };
    /**
     * Constructor
     * Se recibe la referencia al juego, para poder acceder al pacman
     * Se lee el "archivo" con la información del laberinto, la posición
     * del pacman y la salida
     * @param
     */

    public Tablero(){
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
            }
        }
    }

    /**
     * En este método se muestra el pacman(get)
     * @return pacman
     */
    public Pacman getPacman(){
        return pacman;
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
     * En este método dice si la celda contiene la salida
     * @return
     */
    public boolean cellIsOutput(int row, int column){
        return this.cellMatrix[row][column].esSalida;
    }

    /**
     * En este método dice se muestra la información del pacman
     */
    private void createPacman(){
        String pacmanInfoLine = this.puzzleInfo[PACMAN_INFO_LINE];
        Scanner lineScan  = new Scanner(pacmanInfoLine.substring(1));
        int row = lineScan.nextInt();
        int column = lineScan.nextInt();
        Posicion posicion = new Posicion(row, column);
        this.pacman = new Pacman(posicion);
        this.cellMatrix[posicion.fila][posicion.col].caracter = this.pacman;
    }
    /**
     * Este método establece la salida
     */
    private void setOutput(){
        String outputInfoLine = this.puzzleInfo[OUTPUT_INFO_LINE];
        Scanner lineScan  = new Scanner(outputInfoLine.substring(1));
        int row = lineScan.nextInt();
        int column = lineScan.nextInt();
        this.cellMatrix[row][column].esSalida = true;
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
