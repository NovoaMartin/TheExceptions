package clases;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;

abstract public class Entidad {
    protected Ubicacion posicion;
    protected int ancho;
    protected int alto;
    private Image imagen;

    public Entidad(int x, int y, String imagen) {
            this.posicion = new Ubicacion(x, y);
            try {
            	Toolkit toolkit = Toolkit.getDefaultToolkit();
                this.imagen = toolkit.getImage(imagen);
                this.ancho = 70;
                this.alto = 40;
            } catch (NullPointerException e) {
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

    public Image getImagen() {
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
