package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
    private int velocidad;
    private long tiempo;

    ArrayList<Jugador> jugadores;
    ArrayList<Obstaculo> obstaculos;


    public Partida(int velocidad, long tiempo, String mapa) throws FileNotFoundException {
        this.velocidad = velocidad;
        this.tiempo = tiempo;

        this.cargarObstaculos(mapa);
        jugadores.add(new Jugador(0, 0, 10, 10, "/img/dino.jpg", "Jugador"));
    }

    //Esto tiene que ejecutarse cada frame del juego
    private void verificarColisiones() {
        for (Jugador jugador : jugadores) {
            if (jugador.isVivo()) {
                for (Obstaculo obstaculo : obstaculos) {
                    jugador.colisionaCon(obstaculo);
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
