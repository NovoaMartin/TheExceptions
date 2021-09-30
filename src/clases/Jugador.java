package clases;


public class Jugador extends Entidad {
    public int velocidadY;
    private boolean vivo;
    private String nombre;
    private boolean agachado;
    private boolean tadeo;
    //private int punto
    //private int idUsuario

    public Jugador(int x, int y, int ancho, int alto, String imagen, String nombre) {
        super(x, y, ancho, alto, imagen);
        this.velocidadY = 0;
        this.vivo = true;
        this.nombre = nombre;
        this.agachado = false;
    }

    public void saltarInterno() {
        if (this.velocidadY > 0) {
            this.y += velocidadY;
            velocidadY--;
        }
    }

    public void saltar() {
        if (this.velocidadY == 0) {
            this.velocidadY = 5;
            this.y = 1;
            //Cada X tiempo:
            if (this.y > 0) {
                this.y += velocidadY;
                velocidadY--;
            }
        }


    }

    public void agacharse() {
        //Pensado para ejecutarse cada vez que se presione la tecla de agacharse
        //A Adaptar al motor grafico:
        if (!this.agachado) {
            this.alto /= 2;
            this.agachado = true;
        } else {
            this.alto *= 2;
            this.agachado = false;
        }
    }

    private void morir() {
        this.vivo = false;
    }

    public boolean colisionaCon(Obstaculo obstaculo) {
        if (super.colisionaCon(new Entidad(0, 0, 0, 0, ""))) {
            this.morir();
            return true;
        }
        return false;
    }


    public boolean isVivo() {
        return vivo;
    }

    public String getNombre() {
        return nombre;
    }
}
