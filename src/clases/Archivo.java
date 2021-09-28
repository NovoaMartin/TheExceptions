package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archivo {
	
	public List<Obstaculo> leerUbicacionObstaculos(String path){
		ArrayList<Obstaculo>obstaculos=new ArrayList<>();
		Scanner sc=null;
		try {
			sc=new Scanner(new File(path));
			int cantObstaculos=sc.nextInt();
			
			for(int i=0;i<cantObstaculos;i++) {
				Ubicacion u=new Ubicacion(sc.nextInt(),sc.nextInt());
				String desc=sc.next();
				
				obstaculos.add(new Obstaculo(u,desc));
			}
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
		return obstaculos;
	}
	public static void main(String[] args) {
		Archivo a=new Archivo();
		ArrayList<Obstaculo> obstaculos;
		obstaculos= (ArrayList<Obstaculo>) a.leerUbicacionObstaculos("Archivos/Entrada/ubicacionesObstaculos.in");
		
		for (int i=0;i<obstaculos.size();i++) {
			System.out.println(obstaculos.get(i).getPosicion().getPosx()+" "+obstaculos.get(i).getPosicion().getPosy()+" "+obstaculos.get(i).getDescripcion());
		}
	}
}
