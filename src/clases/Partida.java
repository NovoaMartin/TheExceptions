package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Partida extends JFrame implements Runnable {
    public static final double GROUND_HEIGHT_PERCENTAGE = 0.85;
    private static final int SECOND = 1000;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int SKIP_FRAMES = SECOND / FRAMES_PER_SECOND;
    private static final int TICKS_PER_SECOND = 30;
    private static final int SKIP_TICKS = SECOND / TICKS_PER_SECOND;
    public int PLAYER_AMOUNT;
    boolean isRunning = true;

    public static void main(String[] args) throws FileNotFoundException {
        Partida p = new Partida(1, 0, "0", 4);
        p.run();
    }


    private int velocidad;
    private long tiempo;

    ArrayList<Pantalla> pantallas;


    private ArrayList<Jugador> jugadores;
    private ArrayList<Obstaculo> obstaculos;

    public Partida(int velocidad, long tiempo, String mapa, int cantidadJugadores) throws FileNotFoundException {
        super();
        init(velocidad, tiempo, mapa, cantidadJugadores);
    }

    private void init(int velocidad, long tiempo, String mapa, int cantidadJugadores) throws FileNotFoundException {
        this.velocidad = velocidad;
        this.tiempo = tiempo;
//        this.cargarObstaculos(mapa);
        this.jugadores = new ArrayList<>();
        this.pantallas = new ArrayList<>();
        this.PLAYER_AMOUNT = cantidadJugadores;


        //Init pantallas y jugadores
        for (int i = 1; i <= PLAYER_AMOUNT; i++) {
            Jugador jugador = new Jugador(50, 0, "estoico.png", "dino#" + i);
            jugador.saltar();
            jugadores.add(jugador);
        }
        for (int i = 0; i < PLAYER_AMOUNT; i++) {
            pantallas.add(new Pantalla(jugadores.get(i), this));
        }
        for (Pantalla pantalla : pantallas) {
            add(pantalla);
        }

        //Setup display
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        requestFocusInWindow();
        setTitle("TheExceptions");
        setExtendedState(MAXIMIZED_BOTH);


        for (Jugador jugador : jugadores) {
            jugador.setY((int) (getContentPane().getSize().getHeight() * GROUND_HEIGHT_PERCENTAGE));
            System.out.println(jugador.getY());
        }


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    jugadores.get(0).revivir();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    jugadores.get(0).saltar();
                }
            }
        });

    }

    //Esto tiene que ejecutarse cada frame del juego
    public void verificarColisiones() {
        Iterator<Jugador> iterator = jugadores.iterator();

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

    public void cargarObstaculos(String nombreArchivo) throws FileNotFoundException {
        this.obstaculos = new ArrayList<>();
        Scanner scanner = new Scanner(new File("Archivos/entrada/" + nombreArchivo));
        while (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String imagen = "imagenes/" + scanner.nextInt();
            obstaculos.add(new Obstaculo(x, y, imagen));
        }
    }

    public int getCantJugadores() {
        return this.jugadores.size();
    }


    public int getCantObstaculos() {
        return this.obstaculos.size();
    }

    @Override
    public void run() {
        long next_game_tick = System.currentTimeMillis();
        long next_game_frame = System.currentTimeMillis();
        long next_frame_calc = System.currentTimeMillis();
        int frames = 0;

        while (isRunning) {
            if (System.currentTimeMillis() > next_game_tick) {
                next_game_tick += SKIP_TICKS;
                update();
            }
            if (System.currentTimeMillis() > next_game_frame) {
                frames++;
                next_game_frame += SKIP_FRAMES;
                display();
            }
            if (System.currentTimeMillis() > next_frame_calc) {
                next_frame_calc += SECOND;
                frames = 0;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void display() {
        for (Pantalla pantalla : pantallas) {
            pantalla.repaint();
        }
    }

    private void update() {
        for (Pantalla pantalla : pantallas) {
            pantalla.update();
        }
        for (Jugador jugador : jugadores) {
            jugador.manejarSalto((int) (getContentPane().getHeight() * GROUND_HEIGHT_PERCENTAGE));
        }

    }


}
