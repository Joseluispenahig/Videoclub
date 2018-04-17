import java.util.*;
import java.io.*;

/**
 * 
 */

/**
 * @author salas
 *
 */
public class PeliculaRes implements Serializable{
	private int id;
	private String nombre;
	private String tipo;
	private double preciopordia;
	private Calendar FechaInicio;
	private Calendar FechaFin;
	
	public PeliculaRes(int id, String nombre, String tipo, double preciopordia, Calendar fechaInicio, Calendar fechaFin) {
		//super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.preciopordia = preciopordia;
		FechaInicio = fechaInicio;
		FechaFin = fechaFin;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPreciopordia() {
		return preciopordia;
	}
	public void setPreciopordia(double preciopordia) {
		this.preciopordia = preciopordia;
	}
	public Calendar getFechaInicio() {
		return FechaInicio;
	}
	public void setFechaInicio(Calendar fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public Calendar getFechaFin() {
		return FechaFin;
	}
	public void setFechaFin(Calendar fechaFin) {
		FechaFin = fechaFin;
	}
	
	//Metodo que calcula los dias entre la fecha de inicio y la fecha de fin
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
