/**
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
        board.dibujarTablero();
        try{
            solve(StartingPoint[0],StartingPoint[1],0);
        }
        catch(Exception e){
            System.out.print("error en solve");
        }
    }

    private static final char Wall = '*';
    private static final char HALL = ' ';
    private static final char SomeDude = '^';
    int MazeWidth = 17;
    int MazeHeight = 15;
    private static final int[] StartingPoint = {1, 1};
    private static final int[] EndingPoint = {15, 13};

    private boolean solve(int X,int Y,int S) throws Exception{
        S++;
        board.cellMatrix[Y][X].isFree = false;
        board.dibujarTablero();
        Thread.sleep(10);

        if (X == EndingPoint[0] && Y == EndingPoint[1]){
            return true;
        }
        if (X > 0 && board.cellMatrix[Y][X - 1].caracterCelda() == ' ' && solve(X - 1, Y,S))
        {
            return true;
        }


        if (X < MazeWidth && board.cellMatrix[Y][X + 1].caracterCelda() == ' ' && solve(X + 1, Y,S))
        {
            return true;
        }

        if (Y > 0 && board.cellMatrix[Y - 1][X].caracterCelda() == ' ' && solve(X, Y - 1,S))
        {
            return true;
        }

        if (Y < MazeHeight && board.cellMatrix[Y + 1][X].caracterCelda() == ' ' && solve(X, Y + 1,S))
        {
            return true;
        }
        board.cellMatrix[Y][X].isFree = true;
        board.dibujarTablero();
        Thread.sleep(10);
        return false;
    }

}