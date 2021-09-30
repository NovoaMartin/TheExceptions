package clases;

import javax.swing.*;

public class Jugador extends Entidad {
    private boolean vivo;
    private String nombre;
    //private int punto
    //private int idUsuario

    public Jugador(int x, int y, int ancho, int alto, String imagen) {
        super(x, y, ancho, alto, imagen);
    }

    public void saltar() {
        //TODO
    }

    public void agacharse() {
        //TODO
    }


    public boolean isVivo() {
        return vivo;
    }

    public String getNombre() {
        return nombre;
    }
}
