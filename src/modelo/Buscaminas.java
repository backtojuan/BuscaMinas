/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Icesi (Cali - Colombia)
 * Propuesta de solución laboratorio Unidad 5
 * @author Camilo Barrios - camilo.barrios@correo.icesi.edu.co
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package modelo;

import java.util.InputMismatchException;

public class Buscaminas {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel
	 * principiante
	 */
	public static final int FILAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel
	 * intermedio
	 */
	public static final int FILAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel
	 * experto
	 */
	public static final int FILAS_EXPERTO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel
	 * principiante
	 */
	public static final int COLUMNAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel
	 * intermedio
	 */
	public static final int COLUMNAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel
	 * experto
	 */
	public static final int COLUMNAS_EXPERTO = 30;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el
	 * nivel principiante
	 */
	public static final int PRINCIPIANTE = 1;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el
	 * nivel intermedio
	 */
	public static final int INTERMEDIO = 2;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el
	 * nivel experto
	 */
	public static final int EXPERTO = 3;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel
	 * principiante
	 */
	public static final int CANTIDAD_MINAS_PRINCIPANTE = 10;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel
	 * intermedio
	 */
	public static final int CANTIDAD_MINAS_INTERMEDIO = 40;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel experto
	 */
	public static final int CANTIDAD_MINAS_EXPERTO = 99;

	// -----------------------------------------------------------------
	// Atributos y relaciones
	// -----------------------------------------------------------------

	/**
	 * Relacion que tiene la matriz de casillas
	 */
	private Casilla[][] casillas;

	/**
	 * Atributo que representa el nivel del juego <Solo puede tomar valores
	 * PRINCIPIANTE, INTERMEDIO, EXPERTO>
	 */
	private int nivel;

	/**
	 * Atributo que tiene la cantidad de minas en el tablero
	 */
	private int cantidadMinas;

	/**
	 * Atributo que representa si el usuario perdio al abrir una mina
	 */
	private boolean perdio;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructo de la clase Buscaminas
	 * 
	 * @param nivel - el nivel seleccionado por el usuario
	 */
	public Buscaminas(int nivel) {
		this.nivel = nivel;
		perdio = false;
		inicializarPartida();
	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a
	 * partir del nivel elegido por el usuario
	 */
	private void inicializarPartida() {

		if (nivel == PRINCIPIANTE) {
			cantidadMinas = CANTIDAD_MINAS_PRINCIPANTE;
			casillas = new Casilla[FILAS_PRINCIPIANTE][COLUMNAS_PRINCIPIANTE];

		} else if (nivel == INTERMEDIO) {
			cantidadMinas = CANTIDAD_MINAS_INTERMEDIO;
			casillas = new Casilla[FILAS_INTERMEDIO][COLUMNAS_INTERMEDIO];

		} else if (nivel == EXPERTO) {
			cantidadMinas = CANTIDAD_MINAS_EXPERTO;
			casillas = new Casilla[FILAS_EXPERTO][COLUMNAS_EXPERTO];

		}

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[0].length; j++) {
				casillas[i][j] = new Casilla(Casilla.LIBRE);
			}
		}

		generarMinas();
		inicializarCasillasLibres();

	}

	/**
	 * Metodo que se encarga de inicializar todas las casillas que no son minas
	 */
	public void inicializarCasillasLibres() {
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[0].length; j++) {

				if (casillas[i][j].darValor() != 0) {
					casillas[i][j].modificarValor(cantidadMinasAlrededor(i, j));
				}
			}

		}

	}

	/**
	 * Metodo que permite contar la cantidad de minas que tiene alrededor una
	 * casillas
	 * 
	 * @param i - La fila de la matriz
	 * @param j - la columna de la matriz
	 * @return int - La cantidad de minas que tiene alrededor la casilla [i][j]
	 */
	public int cantidadMinasAlrededor(int fil, int col) {

		int count = 0;

		int v = 0;
		int h = 0;

		// Si el uno está en el centro
		for (int i = fil - 1; v < 3 && i < casillas.length; i++) {
			for (int j = col - 1; h < 3; j++) {
				if ((i >= 0 && j >= 0) && (j < casillas[i].length)) {
					if (casillas[i][j].esMina()) {
						count++;
					}
				}
				h++;
			}
			v++;
			h = 0;
		}

		return count;
	}

	/**
	 * Método que se encarga de generar aleatoriomente las minas
	 */
	public void generarMinas() {

		if (nivel == PRINCIPIANTE) {

			for (int m = 0; m < 10; m++) {

				int x = 0;
				int y = 0;

				do {
					x = (int) (Math.random() * 8);
					y = (int) (Math.random() * 8);
				} while (casillas[x][y].darValor() == 0);

				casillas[x][y].modificarValor(0);
				casillas[x][y].modificarTipo(Casilla.MINA);
			}

		} else if (nivel == INTERMEDIO) {

			for (int m = 0; m < 16; m++) {

				int x = 0;
				int y = 0;

				do {
					x = (int) (Math.random() * 16);
					y = (int) (Math.random() * 16);
				} while (casillas[x][y].darValor() == 0);

				casillas[x][y].modificarValor(0);
				casillas[x][y].modificarTipo(Casilla.MINA);
			}

		} else if (nivel == EXPERTO) {

			for (int m = 0; m < 99; m++) {

				int x = 0;
				int y = 0;

				do {
					x = (int) (Math.random() * 16);
					y = (int) (Math.random() * 30);
				} while (casillas[x][y].darValor() == 0);

				casillas[x][y].modificarValor(0);
				casillas[x][y].modificarTipo(Casilla.MINA);
			}

		}
	}

	/**
	 * Metodo que se encarga de convertir el tablero a un String para poder verlo en
	 * pantalla
	 * 
	 * @return String - El tablero en formato String
	 */
	
	public String mostrarTablero() {
		String msg= "";
		msg+=("   ");
		for (int c=0; c<casillas[0].length;c++) {
			msg+=((c+1) + " ");
		}
			msg+="\n" + "\n";
			for(int f=0; f< casillas.length;f++) {
				if(f<=8) {
					msg+=((f+1) + "   ");
					
				}
				if(f>=9) {
					msg+= (f+1 + "  ");
				}
					for(int c=0; c < casillas[0].length; c++) {
						if(c<=9) {
							msg+= (casillas[f][c].mostrarValorCasilla()+" ");
							
						}
						if(c>9) {
							msg+=(" " + casillas[f][c].mostrarValorCasilla()+ " ");
						}
					}
					msg+= "\n";
				}
			return msg;
			}
	
//	public String mostrarTablero() {
//		String msj = "";
//		
//		for (int i = 0; i < casillas.length; i++) {
//			msj += "\n";
//			for (int j = 0; j < casillas[0].length; j++) {
//				
//					msj +=  casillas[i][j].mostrarValorCasilla(); 
//				
//				
//			}
//		}
//
//		return msj;
//	}

	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver() {

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[0].length; j++) {
				casillas[i][j].destapar();
			}
		}

	}

	/**
	 * Metodo dar del atributo casillas
	 * 
	 * @return la relacion casillas
	 */
	public Casilla[][] darCasillas() {
		return casillas;
	}

	/**
	 * Este metodo se encargaa de abrir una casilla Si se abre una casilla de tipo
	 * Mina, se marca que el jugador perdio el juego.
	 * 
	 * @param i - la fila donde esta la casilla
	 * @param j - la columna donde esta la casilla
	 * @return boolean - true si fue posible destaparla, false en caso contrario
	 */
	public boolean abrirCasilla(int fil, int col) throws ArrayIndexOutOfBoundsException, InputMismatchException{

		boolean close = false;

		if (casillas[fil][col].darSeleccionada() != true) {
			casillas[fil][col].destapar();
			close = true;
			if (casillas[fil][col].darTipo() == Casilla.MINA) {
				perdio = true;
			}
		}
		return close;
	}

	/**
	 * Metodo que se encarga de revisar si el jugador gano el juego
	 * 
	 * @return boolean - true si gano el juego, false en caso contrario
	 */
	public boolean gano() {
		boolean t = false;
		boolean x = false;

		for (int i = 0; i < casillas.length && !x; i++) {
			for (int j = 0; j < casillas[0].length && !x; j++) {
				if (casillas[i][j].esMina() == false && casillas[i][j].darSeleccionada() == true) {

					t = true;
				} else {
					darPerdio();
					x = false;
				}

			}
		}
		return t;
	}

	/**
	 * Metodo que se encarga de abrir la primera casilla que no sea una Mina y cuyo
	 * valor sea Mayor que 0
	 * 
	 * @return int[], arreglo de 2 posiciones que indica la fila y la columna de la casilla
	 * que se dió pista
	 */
	public int[] darPista() {

		int[] rc = new int[2];
		
		boolean t = false;

		for (int i = 0; i < casillas.length && !t; i++) {
			for (int j = 0; j < casillas[0].length && !t; j++) {

				if ((casillas[i][j].esMina() == false) && (casillas[i][j].darValor() > 0)
						&& (casillas[i][j].darSeleccionada() == false)) {

					casillas[i][j].destapar();
					t = true;
					rc[0] = i;
					rc[1] = j;
				}

			}
		}

		return rc;
	}

	/***
	 * Metodo dar del atributo perdio
	 * 
	 * @return boolean el atributo
	 */
	public boolean darPerdio() {
		return perdio;
	}

}
