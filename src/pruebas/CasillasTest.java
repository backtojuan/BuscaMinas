package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Casilla;

class CasillasTest {

	private Casilla casillasAndrea;

	private Casilla[][] casillasAndrea2;

	private void setupEscenario2() {
		casillasAndrea = new Casilla(Casilla.MINA);
	}

	private void setupEscenario1() {
		casillasAndrea = new Casilla(Casilla.LIBRE);
	}

	@Test
	public void modificarTipoTest() {
		setupEscenario1();
		casillasAndrea.modificarTipo(Casilla.MINA);
		assertTrue(casillasAndrea.darTipo() == Casilla.MINA);
	}

	@Test
	public void esMinaTest() {
		setupEscenario2();
		assertTrue(casillasAndrea.esMina());
	}

	@Test
	public void destaparTest() {
		setupEscenario2();
		casillasAndrea.destapar();
		assertTrue(casillasAndrea.darSeleccionada());

	}
}
