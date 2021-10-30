package clases;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class Entidad {
    protected Ubicacion posicion;
    protected int ancho;
    protected int alto;
    private BufferedImage imagen;

    public Entidad(int x, int y, String imagen) {
        this.posicion = new Ubicacion(x, y);
        try {
            this.imagen = ImageIO.read(new File(imagen));
            this.ancho = this.imagen.getWidth();
            this.alto = this.imagen.getHeight();
        } catch (IOException e) {
            System.out.println("ERROR AL CARGAN IMAGEN");
            imagen = null;
        }
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

    public BufferedImage getImagen() {
        return imagen;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setY(int y) {
        this.posicion.setPosy(y);
    }
    public void setX(int x) {
    	this.posicion.setPosx(x);
    }

}
