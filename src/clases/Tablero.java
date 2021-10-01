//package clases;
//
//import java.util.List;
//
//public class Tablero {
//	private int [][] tablero=new int[5][5];
//	private List<Obstaculo> listaObstaculos;
//
//	public Tablero() {
//		for(int i=0;i<tablero.length;i++) {
//			for(int j=0;j<tablero.length;j++) {
//				if(i==4 && j==0)
//					this.tablero[i][j]=1; //almaceno donde va arrancar el jugador
//			}
//		}
//		Archivo a=new Archivo();
//		this.listaObstaculos=a.leerUbicacionObstaculos("Archivos/Entrada/ubicacionesObstaculos.in");
//	}
//
//	public void mostrarTablero() {
//		for (int i = 0; i < tablero.length; i++) {
//			for (int j = 0; j < tablero.length; j++)
//				System.out.printf("%-10s", this.tablero[i][j]);
//			System.out.println();
//		}
//		System.out.println();
//	}
//	public void mostrarObstaculos() {
//		for (int i=0;i<this.listaObstaculos.size();i++) {
//			System.out.println(this.listaObstaculos.get(i).getPosicion().getPosx()+" "+this.listaObstaculos.get(i).getPosicion().getPosy()+" "+this.listaObstaculos.get(i).getDescripcion());
//		}
//	}
//
//	public void cargarObstaculosTablero() {
//		for(int i=0;i<this.listaObstaculos.size();i++) {
//			this.tablero[this.listaObstaculos.get(i).getPosicion().getPosx()][this.listaObstaculos.get(i).getPosicion().getPosy()]=2;
//		}
//	}
//	public static void main(String[] args) {
//		Tablero t=new Tablero();
//		System.out.println("TABLERO INICIALIZADO");
//		t.mostrarTablero();
//		System.out.println("MOSTRANDO LOS OBSTACULOS");
//		t.mostrarObstaculos();
//		t.cargarObstaculosTablero();
//		System.out.println("TABLERO AL CARGAR LOS OBSTACULOS");
//		t.mostrarTablero();
//	}
//}
