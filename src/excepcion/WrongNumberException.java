package excepcion;

public class WrongNumberException extends Exception {
	
	public WrongNumberException(int number){
		super("La opci�n " + number + " no es correcta. Ingrese un dato v�lido");
	}
}
