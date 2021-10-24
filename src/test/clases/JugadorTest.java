package test.clases;

import clases.Jugador;
import clases.Obstaculo;
import org.junit.*;

public class JugadorTest {
    Jugador jugador;

    @Before
    public void setup() {
        jugador = new Jugador(10, 0,  "1.jpg", "test");
    }

    @Test
    public void moverseHaciaDerechaTest() {
        jugador.moverse(1);
        Assert.assertEquals(11, jugador.getX());
    }

    @Test
    public void moverseHaciaIzquierdaTest() {
        jugador.moverse(-1);
        Assert.assertEquals(9, jugador.getX());
    }

    @Test
    public void colisionConObstaculoTest() {
        Obstaculo obstaculo = new Obstaculo(14, 0,  "1.jpg");
        Assert.assertTrue(jugador.colisionaCon(obstaculo));
        Assert.assertFalse(jugador.isVivo());
    }

    @Test
    public void colisionConObstaculoFalsaTest() {
        Obstaculo obstaculo = new Obstaculo(50, 0,  "1.jpg");
        Assert.assertFalse(jugador.colisionaCon(obstaculo));
        Assert.assertTrue(jugador.isVivo());
    }

    @Test
    public void getXTest() {
        Assert.assertEquals(10, jugador.getX());
    }

    @Test
    public void getYTest() {
        Assert.assertEquals(0, jugador.getY());
    }

    @Test
    public void getImagenTest() {
        Assert.assertEquals("1.jpg", jugador.getImagen());
    }

    @Test
    public void saltarTest() {
        jugador.saltar();
        Assert.assertTrue(jugador.getY() > 0);
        Assert.assertTrue(jugador.getVelocidadY() > 0);
    }

    @Test
    public void agacharseTest() {
        jugador.agacharse();
        Assert.assertTrue(jugador.isAgachado());
    }
    @Test
    public void agacharseSegundoClickTest() {
        jugador.agacharse();
        jugador.agacharse();
        Assert.assertFalse(jugador.isAgachado());
    }


    @Test
    public void isVivoTest() {
        Assert.assertTrue(jugador.isVivo());
    }

    @Test
    public void getNombreTest() {
        Assert.assertEquals("test", jugador.getNombre());
    }

    @Test
    public void getVelocidadYTest() {
        Assert.assertEquals(0, jugador.getVelocidadY());
    }
}