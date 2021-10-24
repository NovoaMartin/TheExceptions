package clases;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Pantalla extends JPanel {
    Jugador jugador;
    Partida partida;

    ArrayList<Obstaculo> obstaculos;

    public Pantalla(Jugador jugador, Partida partida) {
        this.jugador = jugador;
        this.partida = partida;
        obstaculos = new ArrayList<>();

        jugador.setY(100);
        for (int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * 1000 + 1000);
            obstaculos.add(new Obstaculo(x, getGroundHeight(),  "nada"));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Dimension currentDimension = partida.getContentPane().getSize();
        //Render jugador:
        if (jugador.isVivo()) {
            g2d.drawImage(jugador.getImagen(), jugador.getX(), jugador.getY() / partida.PLAYER_AMOUNT - jugador.getImagen().getHeight(), null);
        }

        //Render piso:
        g2d.drawRect(0, getGroundHeight() / partida.PLAYER_AMOUNT, 100000, 1);


        //Render obstaculos:
        for (Obstaculo obstaculo : obstaculos) {
            g2d.drawRect(obstaculo.getX(), getGroundHeight() / partida.PLAYER_AMOUNT, 50, -50);
        }
    }

    private int getGroundHeight() {
        return (int) (partida.getContentPane().getSize().getHeight() * Partida.GROUND_HEIGHT_PERCENTAGE);
    }

    public void update() {
        for (Obstaculo obstaculo : obstaculos) {
            obstaculo.setY(getGroundHeight());
            obstaculo.moverse(-5);
            if (obstaculo.getX() <= -50) {
                obstaculo.setX((int) (Math.random() * 1000 + partida.getContentPane().getWidth()));
            }
        }
        verificarColisiones();
        System.out.println("Vivo? " + jugador.isVivo());
//        System.out.println(jugador);
//        System.out.println(getGroundHeight());
    }

    public void verificarColisiones() {

//        for (Obstaculo obstaculo : obstaculos) {
//            if (jugador.colisionaCon(obstaculo)) {
//                break;
//            }
//        }

        Rectangle j = new Rectangle(jugador.getX(), jugador.getY(), jugador.getAncho(), jugador.getAlto());
        for (Obstaculo obstaculo : obstaculos) {
            Rectangle o = new Rectangle(obstaculo.getX(), getGroundHeight(), 50, 50);
            System.out.println(j.intersects(o));
            if (j.intersects(o)) {
                jugador.morir();
                break;
            }
        }
    }
}
