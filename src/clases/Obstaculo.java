package clases;

/**
 * Por el momento esta clase solo necesita la funcionalidad dada por Entidad
 * Puede cambiar en el futuro
 */
public class Obstaculo extends Entidad {
    public Obstaculo(int x, int y, String imagen) {
        super(x, y, imagen);
    }

    public void setX(int x) {
        this.posicion.setPosx(x);
    }
}
