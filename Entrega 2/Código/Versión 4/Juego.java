/** Esta clase permite la interacción del juego con el usuario
 * @author Dayana Arrieta & Juan José Quintana
 */

import java.util.List;
import java.util.Scanner;

public class Juego {
    /**
     * contador de turnos llevados en el juego
     */
    int counter = 0;
    /**
     * tablero del laberinto
     */
    Tablero board = new Tablero();
    /**
     * pacman del puzzle
     */
    Pacman pacman = board.getPacman();
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    private static final String QUIT = "q";
    private static final String ENTER ="";
    /**
     * Constructor vacío
     */
    public Juego() {
    }
    
    /**
     * Interacción con el usuario
     */
    public void jugar() {
        boolean wonGame = false;
        board.dibujarTablero();
        Scanner systemInput = new Scanner(System.in);
        String userInput = systemInput.nextLine();
        while (userInput.equals(ENTER) && !wonGame) {
            int pacmanRow = pacman.posicion.fila;
            int pacmanColumn = pacman.posicion.col;
            int newRow = pacmanRow;
            int newColumn = pacmanColumn;
            Posicion posicion = pacman.posicion;
            int direction = pacman.getDirection();           
            switch (direction) {
                case UP:
                    newRow = pacmanRow - 1;
                    break;
                case DOWN:
                    newRow = pacmanRow + 1;
                    break;
                case LEFT:
                    newColumn = pacmanColumn - 1;
                    break;
                case RIGHT:
                    newColumn = pacmanColumn + 1;
                    break;

            }
            counter++;
            

            if (cellIsValid(newRow, newColumn)) {
                updateCaracterPosition(pacman, new Posicion(newRow, newColumn));
                if(board.cellIsOutput(newRow, newColumn)){

                    wonGame = true;
                    board.dibujarTablero();
                    break;
                }

            }
            board.dibujarTablero();
            userInput = systemInput.nextLine();
        }
        if (wonGame) {
            System.out.println("Has ganado el juego, ¡felicitaciones!");
        }
        System.out.println("Fin del juego");
    }

    /**
     * Método que actualiza la posición de los caracteres
     */
    private void updateCaracterPosition(Caracter caracter, Posicion posicion){
        int caracterRow = caracter.posicion.fila;
        int caracterColumn = caracter.posicion.col;
        Celda previousCell = board.cellMatrix[caracterRow][caracterColumn];
        Celda newCell = board.cellMatrix[posicion.fila][posicion.col];
        newCell.caracter = caracter;
        previousCell.caracter = null;
        caracter.posicion = posicion;
    }
    
    /**
     * En este metodo se debe chequear las siguientes condiciones:
     * (i) Que el usuario no se salga de las filas del tablero
     * (ii) Que el usuario no se salga de las columnas del tablero
     * (iii) Que la posición no sea un muro
     * (iv) Que no haya un caracter en esa posición
     *
     * @param newRow    Fila hacia donde se quiere mover el usuario
     * @param newColumn Columna hacia donde se quiere mover el usuario
     * @return true si es una jugada válida, false de lo contrario
     */
    private boolean cellIsValid(int newRow, int newColumn) {
        return (
                !board.cellIsWall(newRow, newColumn) &&
                        !board.cellHasCharacter(newRow, newColumn)
        );
    }
    

}
