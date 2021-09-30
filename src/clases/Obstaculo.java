package clases;

public class Obstaculo{
	private Ubicacion posicion;
	private String descripcion;
	
	public Obstaculo(Ubicacion posicion, String descripcion) {
		super();
		this.posicion = posicion;
		this.descripcion = descripcion;
		System.out.println("pruebaGithub");
	}

	public Ubicacion getPosicion() {
		return posicion;
	}

	public void setPosicion(Ubicacion posicion) {
		this.posicion = posicion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
