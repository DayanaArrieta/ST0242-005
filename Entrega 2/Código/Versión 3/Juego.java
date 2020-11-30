package version2; /**
 * Esta clase permite la interacción del juego con el usuario
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
     * El número de puntos iniciales de vida del pacman
     */
    public static int PUNTOS_VIDA_INICIALES = 10;
    /**
     * tablero del laberinto
     */
    Tablero board = new Tablero();
    /**
     * pacman del puzzle
     */
    Pacman pacman = board.getPacman();
    List<Fantasma> fantasma = board.getFantasmas();
    private static final String UP = "w";
    private static final String DOWN = "s";
    private static final String LEFT = "a";
    private static final String RIGHT = "d";
    private static final String QUIT = "q";
    private boolean lostGame = false;
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
        while (!userInput.equals(QUIT) && !wonGame && !lostGame) {
            int pacmanRow = pacman.posicion.fila;
            int pacmanColumn = pacman.posicion.col;
            int newRow = pacmanRow;
            int newColumn = pacmanColumn;
            switch (userInput) {
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
            if (counter == 10) {
                counter = 0;
                pacman.reduceLifePoints(1);
            }

            if (cellIsValid(newRow, newColumn)) {
                updateFantasmasPosition();
                updateCaracterPosition(pacman, new Posicion(newRow, newColumn));
                if(lostGame){
                    System.out.println("perdiste:(");
                    break;
                }
                if(board.cellHasArepita(newRow,newColumn)){
                    if(board.cellHasBadArepita(newRow, newColumn)){
                        pacman.reduceLifePoints(5);
                    }
                    else {
                        pacman.increasesLifePoints();
                    }

                    if(pacman.getLifePoints() <= 0){
                        break;

                    }
                    board.removeArepitaFromCell(newRow, newColumn);
                }
                if(board.cellIsOutput(newRow, newColumn)){

                    wonGame = true;
                    board.dibujarTablero();
                    break;
                }

            }
            System.out.println("Puntos de vida: " + pacman.getLifePoints());
            board.dibujarTablero();
            userInput = systemInput.nextLine();
        }
        if (wonGame) {
            System.out.println("Has ganado el juego, ¡felicitaciones!");
        }
        System.out.println("Fin del juego");
    }
    /**
     * Método que actualiza la posición de los fantasmas
     */
    private void updateFantasmasPosition () {
        List<Fantasma> fantasmas = board.getFantasmas();
        for (Fantasma fantasma : fantasmas) {
            if(isPacmanOnFantasmaLineOfSight(fantasma)){
                moveFantasmaTowardsPacman(fantasma);
                moveFantasmaTowardsPacman(fantasma);
                continue;
            }
            Posicion newPosition = createFantasmaPosition(fantasma);
            if(cellIsValid(newPosition.fila, newPosition.col)){
                updateCaracterPosition(fantasma, newPosition);
            } else{
                fantasma.changeDirection();
            }
        }
    }
    /**
     * Método que mueve en dirección del pacman
     */
    private void moveFantasmaTowardsPacman(Fantasma fantasma) {
        int newRow = fantasma.posicion.fila;
        int newColumn = fantasma.posicion.col;
        if(fantasma.posicion.fila == pacman.posicion.fila){
           int colDif = pacman.posicion.col - fantasma.posicion.col;
           if(colDif == 1 || colDif == -1){
               lostGame = true;
               System.out.println("perdiste:(");
               return;
           }
           if(colDif > 0){
               newColumn += 1;
           }
           else{
               newColumn -= 1;
           }
        }
        if(fantasma.posicion.col == pacman.posicion.col){
           int filDif = pacman.posicion.fila - fantasma.posicion.fila;
            if(filDif == 1 || filDif == -1) {
                lostGame = true;
                System.out.println("perdiste:(");
                return;
            }
           if(filDif > 0){
               newRow += 1;
           }
           else{
               newRow -= 1;
           }
        }

        if(cellIsValid(newRow, newColumn)){
            updateCaracterPosition(fantasma, new Posicion(newRow,newColumn));
        }
    }
    /**
     * Método que verifica si el pacman está en la linea de visión del fantasma
     */
    private boolean isPacmanOnFantasmaLineOfSight(Fantasma fantasma) {
        return (fantasma.posicion.fila == pacman.posicion.fila || fantasma.posicion.col == pacman.posicion.col );
    }
    /**
     * Se crea la posición del fantasma
     */
    private Posicion createFantasmaPosition(Fantasma fantasma){
        Posicion posicion = fantasma.posicion;
        int direction = fantasma.getDirection();
        int fantasmaRow = posicion.fila;
        int fantasmaColumn = posicion.col;
        int newRow = fantasmaRow;
        int newColumn = fantasmaColumn;
        switch (direction) {
            case Fantasma.UP:
                newRow = fantasmaRow - 1;
                break;
            case Fantasma.DOWN:
                newRow = fantasmaRow + 1;
                break;
            case Fantasma.LEFT:
                newColumn = fantasmaColumn- 1;
                break;
            case Fantasma.RIGHT:
                newColumn = fantasmaColumn + 1;
                break;
        }
        return new Posicion(newRow, newColumn);
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