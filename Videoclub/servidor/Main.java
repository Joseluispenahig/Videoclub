import java.text.SimpleDateFormat;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		int id=1;
		String nombre="Avengers";
		String tipo="Drama";
		int numero=3;
		//Date fecha=new Date();
		//Date fecha2=new Date(2018,4,16);
		
		Calendar fecha;
		Calendar fecha2;
		
		
		
		Pelicula pelicula = new Pelicula(id,nombre,tipo,numero);
		System.out.println("Pelicula nombre:" +  pelicula.getNombre());
		
		fecha=Calendar.getInstance();
		fecha2=Calendar.getInstance();
		fecha.set(2018, 12,3);
		fecha2.set(2018, 12,5);
		PeliculaRes otrapeli= new PeliculaRes(id,nombre,tipo,5.00,fecha,fecha2);
		System.out.println("El precio por dia de la pelicula es " + otrapeli.getPreciopordia());
		double preciofinal=otrapeli.getPreciopordia()*otrapeli.getDiasRestantes(otrapeli.getFechaInicio(), otrapeli.getFechaFin());
		System.out.println("El precio total de la reserva de la pelicula es " + preciofinal);
		
		List<PeliculaRes> lista=new ArrayList<PeliculaRes>();
		
		lista.add(otrapeli);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
		
		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaActual2 = Calendar.getInstance();
		//fechaActual.set(2018, 4, 16);
		fechaActual2.set(2018, 4, 16);
		fechaActual.add(Calendar.MONTH, 1);
		String cadenaFecha = String.format("%04d-%02d-%02d",
				fechaActual.get(Calendar.YEAR),
				fechaActual.get(Calendar.MONTH),
				fechaActual.get(Calendar.DAY_OF_MONTH));
		System.out.println("la fecha de hoy es : " + cadenaFecha);
		String cadenaFecha2 = String.format("%04d-%02d-%02d",
				fechaActual2.get(Calendar.YEAR),
				fechaActual2.get(Calendar.MONTH),
				fechaActual2.get(Calendar.DAY_OF_MONTH));
				System.out.println("la fecha final es: " + cadenaFecha2);

		//fechaActual2.add(Calendar.MONTH, 1);
		
		int dias2=getDiasRestantes(fechaActual, fechaActual2);
		System.out.println("Dias restantes: " + dias2);
		
		
		
		Calendar calendar = new GregorianCalendar(2013,9,28);
		
		calendar.add(Calendar.MONTH, 1);
		System.out.println("Date : " + sdf.format(calendar.getTime()));
	}
	
	public static int getDiasRestantes(Calendar fechaInicial,Calendar fechaFinal){
		int diffDays=0;
			if(fechaFinal.before(fechaInicial) || fechaInicial.equals(fechaFinal)){
				diffDays=0;
			}else{
				while(fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)){
					diffDays++;
					fechaInicial.add(Calendar.DATE, 1);
				}
			}
			return diffDays==0?0:diffDays-1;
		}

}
