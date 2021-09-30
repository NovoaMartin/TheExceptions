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


    public Partida(int velocidad, long tiempo) throws FileNotFoundException {
        this.velocidad = velocidad;
        this.tiempo = tiempo;

        this.cargarObstaculos();
        jugadores.add(new Jugador(0, 0, 10, 10, "/img/dino.jpg", "Jugador"));
    }

    //Esto tiene que ejecutarse cada frame del juego
    private void verificarColisiones() {
        for (Jugador jugador : jugadores) {
            for (Obstaculo obstaculo : obstaculos) {
                jugador.colisionaCon(obstaculo);
            }
        }
    }

    private void cargarObstaculos() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Archivos/entrada/mapa1.in"));
        while (scanner.hasNextInt()) {
            obstaculos.add(new Obstaculo(
                    new Ubicacion(scanner.nextInt(), scanner.nextInt()),
                    "obstaculo"
            ));
        }
    }
}
