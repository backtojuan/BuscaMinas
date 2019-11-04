package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Buscaminas;
import modelo.Casilla;

class BuscaminasTest {

	private Buscaminas buscaminasAndrea;

	private void setupEscenario1() {
		buscaminasAndrea = new Buscaminas(Buscaminas.PRINCIPIANTE);
	}

	private void setupEscenario3() {

		buscaminasAndrea = new Buscaminas(Buscaminas.PRINCIPIANTE);
		for (int i = 0; i < buscaminasAndrea.darCasillas().length; i++) {
			for (int j = 0; j < buscaminasAndrea.darCasillas()[0].length; j++) {
				buscaminasAndrea.darCasillas()[i][j].modificarTipo(Casilla.LIBRE);
			}
		}
		buscaminasAndrea.darCasillas()[0][0].modificarValor(0);
		buscaminasAndrea.darCasillas()[0][0].modificarTipo(Casilla.MINA);

	}

	private void setupEscenario4() {
		buscaminasAndrea = new Buscaminas(Buscaminas.INTERMEDIO);
		for (int i = 0; i < buscaminasAndrea.darCasillas().length; i++) {
			for (int j = 0; j < buscaminasAndrea.darCasillas()[0].length; j++) {
				buscaminasAndrea.darCasillas()[i][j].modificarTipo(Casilla.LIBRE);
			}
		}
	}

	private void setupEscenario5() {
		buscaminasAndrea = new Buscaminas(Buscaminas.PRINCIPIANTE);

	}

	private void setupEscenario6() {
		buscaminasAndrea = new Buscaminas(Buscaminas.PRINCIPIANTE);
		buscaminasAndrea.darPista();
	}

	@Test
	public void testInicializarPartida() {

		setupEscenario1();
		int resultadoEsperado = 10;
		int resultado = Buscaminas.CANTIDAD_MINAS_PRINCIPANTE;
		assertEquals(resultadoEsperado, resultado);
	}

	// Comprueba la cantidad de minas que hay en el tablero
	@Test
	public void inicializarPartidaTest() {

		setupEscenario1();
		int expected = 0;
		int actual = 10;
		int contadorMinas = 0;
		for (int i = 0; i < buscaminasAndrea.darCasillas().length; i++) {
			for (int j = 0; j < buscaminasAndrea.darCasillas()[0].length; j++) {
				if (buscaminasAndrea.darCasillas()[i][j].darTipo() == Casilla.MINA) {

					contadorMinas++;

				}
			}
		}
		expected = contadorMinas;
		assertEquals(expected, actual);
	}

	// Comprueba que haya la cantidad de casillas libres en un buscaminas
	@Test
	public void inicializarCasillasLibresTest() {

		setupEscenario1();
		int expected = 0;
		int actual = 54;
		int contadorCasillasLibres = 0;
		for (int i = 0; i < buscaminasAndrea.darCasillas().length; i++) {
			for (int j = 0; j < buscaminasAndrea.darCasillas()[0].length; j++) {
				if (buscaminasAndrea.darCasillas()[i][j].darTipo() == Casilla.LIBRE) {
					contadorCasillasLibres++;
				}
			}
		}
		expected = contadorCasillasLibres;
		assertEquals(expected, actual);
	}

	// Cuenta la cantidad de minas que hay en la casilla 1,0 del buscaminas del
	// escenario tres
	@Test
	public void cantidadDeMinasAlrededorTest() {
		setupEscenario3();
		int expected = 0;
		int actual = 1;
		expected = buscaminasAndrea.cantidadMinasAlrededor(1, 0);
		assertEquals(expected, actual);

	}

	// Cuenta la cantidad de minas que hay en un tablero de buscaminas de nivel
	// intermedio
	@Test
	public void generarMinasTest() {
		setupEscenario4();
		buscaminasAndrea.generarMinas();
		int actual = 0;
		int expected = 16;
		for (int i = 0; i < buscaminasAndrea.darCasillas().length; i++) {
			for (int j = 0; j < buscaminasAndrea.darCasillas()[0].length; j++) {
				if (buscaminasAndrea.darCasillas()[i][j].darTipo() == Casilla.MINA) {
					actual++;
				}
			}
		}
		assertEquals(expected, actual);
	}

	@Test
	public void resolverTest() {
		setupEscenario5();
		buscaminasAndrea.resolver();
		int contador = 0;
		int expected = 0;
		for (int i = 0; i < buscaminasAndrea.darCasillas().length; i++) {
			for (int j = 0; j < buscaminasAndrea.darCasillas()[0].length; j++) {
				if (buscaminasAndrea.darCasillas()[i][j].equals("-")) {
					contador++;
				}
			}
		}
		assertEquals(contador, expected);
	}

//	@Test
//	public void darPistaTest() {
//		setupEscenario6();
//		
//		int contarnumero=0;
//		int contarguiones=0;
//		int expected=63;
//		for(int i=0; i<buscaminasAndrea.darCasillas().length;i++) {
//			for(int j=0; j<buscaminasAndrea.darCasillas()[0].length;j++) {
//				if(buscaminasAndrea.darCasillas()[i][j].equals("-")) {
//					contarguiones++;
//				}
//			}
//		}
//		assertEquals(contarguiones,expected);
//	}
	
}
