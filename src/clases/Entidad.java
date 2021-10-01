package clases;

abstract public class Entidad {
    protected Ubicacion posicion;
    protected int ancho;
    protected int alto;
    private String imagen;

    public Entidad(int x, int y, int ancho, int alto, String imagen) {
        this.posicion = new Ubicacion(x, y);
        this.ancho = ancho;
        this.alto = alto;
        this.imagen = imagen;
    }

    public void moverse(int d) {
        this.posicion.setPosx(this.posicion.getPosx() + d);
    }

    public boolean colisionaCon(Entidad otro) {
        boolean colisionaEnX = this.posicion.getPosx() + this.ancho >=
                otro.posicion.getPosx() && otro.ancho + otro.posicion.getPosx() >= this.posicion.getPosx();
        boolean colisionaEnY = this.posicion.getPosy() + this.alto >=
                otro.posicion.getPosy() && otro.alto + otro.posicion.getPosy() >= this.posicion.getPosy();
        return colisionaEnX && colisionaEnY;
    }

    public int getX() {
        return posicion.getPosx();
    }

    public int getY() {
        return posicion.getPosy();
    }

    public String getImagen() {
        return imagen;
    }
}
