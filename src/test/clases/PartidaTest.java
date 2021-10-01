package test.clases;

import java.io.FileNotFoundException;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Partida;

public class PartidaTest {
	
	Partida partida;
	
	@Before
	public void setup() throws FileNotFoundException {
		partida = new Partida(1, 4000, "mapaTest.in", 4);
	}
	
	@Test
	public void verificarCantJugadoresTotales() {
		assertEquals(4, partida.getCantJugadoresTotales());
	}
	
	@Test
	public void verificarCantJugadoresActivos() {
		assertEquals(4, partida.getCantJugadoresActivos());
	}
	
	@Test
	public void verificarCantObstaculos() {
		assertEquals(4, partida.getCantObstaculos());
	}
	
	@Test
    public void verificarColisionesTest() {
        partida.verificarColisiones();
        assertEquals(0, partida.getCantJugadoresActivos());
        assertEquals(4, partida.getCantJugadoresTotales());
    }
}
