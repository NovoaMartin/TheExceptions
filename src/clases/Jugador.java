package clases;

public class Jugador extends Entidad {

    private int velocidadY;
    private boolean vivo;
    private String nombre;
    private boolean agachado;
    //private int punto
    //private int idUsuario

    public Jugador(int x, int y, int ancho, int alto, String imagen, String nombre) {
        super(x, y, ancho, alto, imagen);
        this.velocidadY = 0;
        this.vivo = true;
        this.nombre = nombre;
        this.agachado = false;
    }

    /**
     * Cada X tiempo se debe incrementar/decrementar la posicion Y
     * dependiendo de velocidadY.
     * No se implementarlo, dejo pseudocodigo comentado de como seria la logica
     */
    public void saltar() {
        //Solo puede saltar si esta en el piso, es decir, NO tiene velocidad en Y
        if (this.velocidadY == 0) {
            this.velocidadY = 5;
            this.posicion.setPosy(1);
        }

        //Logica a ejecutarse cada frame del juego:
//        if (velocidadY != 0) {
//            int posicionFinal = this.posicion.getPosy() + velocidadY;
//            if (posicionFinal < 0) {
//                this.posicion.setPosy(0);
//            } else {
//                this.posicion.setPosy(posicionFinal);
//            }
//            velocidadY--;
//        }


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
        if (super.colisionaCon(obstaculo)) {
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

    public int getVelocidadY() {
        return velocidadY;
    }

    public boolean isAgachado() {
        return agachado;
    }

}
