public class Celda {
    boolean isWall;
    boolean esSalida;
    boolean isHall;
    Caracter caracter;
   
    public Celda() {

 

    }

 

    public static Celda createWallCell(){
        Celda cell = new Celda();
        cell.isWall = true;
        return cell;
    }

 

    public static Celda createHallCell(){
        return new Celda();
    }

 

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
        else{
            return ' ';

 

        }
        
    }
}
 