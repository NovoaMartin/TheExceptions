package clases;

public class Entidad {
    protected int x;
    protected int y;
    private int ancho;
    protected int alto;
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
        System.out.println("prueba");
    }

    public boolean colisionaCon(Entidad otro) {
        boolean colisionaEnX = this.x + this.ancho >= otro.x && otro.ancho + otro.x >= this.x;
        boolean colisionaEnY = this.y + this.alto >= otro.y && otro.alto + otro.y >= this.y;
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
