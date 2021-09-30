package clases;

public class Entidad {
    protected int x;
    protected int y;
    private int ancho;
    private int alto;
    private String imagen;

    public Entidad(int x, int y, int ancho, int alto, String imagen) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.imagen = imagen;
    }

    public void moverse(int d) {
        this.x += d;
    }

    public boolean colisionaCon(Entidad otro) {
        boolean colisionaEnX = this.x + this.x >= otro.x && otro.x + otro.x >= this.x;
        boolean colisionaEnY = this.y + this.y >= otro.y && otro.y + otro.y >= this.y;
        return colisionaEnX && colisionaEnY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImagen() {
        return imagen;
    }
}
