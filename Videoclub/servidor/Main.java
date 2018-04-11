import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int id=1;
		String nombre="Avengers";
		String tipo="Drama";
		int numero=3;
		Date fecha=new Date();
		Date fecha2=new Date();
		
		Pelicula pelicula = new Pelicula(id,nombre,tipo,numero);
		System.out.println("Pelicula nombre:" +  pelicula.getNombre());
		
		PeliculaRes otrapeli= new PeliculaRes(id,nombre,tipo,5,fecha,fecha2);
		System.out.println("El precio de la pelicula es " + otrapeli.getPreciopordia());
		List<PeliculaRes> lista=new ArrayList<PeliculaRes>();
		
		lista.add(otrapeli);
		
	}

}
