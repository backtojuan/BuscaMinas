package excepcion;

public class WrongNumberException extends Exception {
	
	public WrongNumberException(int number){
		super("La opción " + number + " no es correcta. Ingrese un dato válido");
	}
}
