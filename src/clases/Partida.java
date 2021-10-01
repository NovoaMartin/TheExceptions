package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Partida {
    private int velocidad;
    private long tiempo;

    ArrayList<Jugador> jugadoresActivos;
    ArrayList<Jugador> jugadoresTotales;
    ArrayList<Obstaculo> obstaculos;


    public Partida(int velocidad, long tiempo, String mapa, int cantidadJugadores) throws FileNotFoundException {
        this.velocidad = velocidad;
        this.tiempo = tiempo;
        this.cargarObstaculos(mapa);

        for (int i = 1; i <= cantidadJugadores; i++) {
            Jugador jugador = new Jugador(0, 0, 5, 10, "dino.jpg", "dino#" + i);
            jugadoresActivos.add(jugador);
            jugadoresTotales.add(jugador);
        }
    }

    //Esto tiene que ejecutarse cada frame del juego
    private void verificarColisiones() {
        Iterator<Jugador> iterator = jugadoresActivos.iterator();

        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            for (Obstaculo obstaculo : obstaculos) {
                if (jugador.colisionaCon(obstaculo)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    private void cargarObstaculos(String nombreArchivo) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Archivos/entrada/" + nombreArchivo));
        while (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int alto = scanner.nextInt();
            int ancho = scanner.nextInt();
            String imagen = "imagenes/" + scanner.nextInt();
            obstaculos.add(new Obstaculo(x, y, alto, ancho, imagen));
        }
    }
}
