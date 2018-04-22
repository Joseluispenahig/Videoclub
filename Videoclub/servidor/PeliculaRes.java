/******************	
 * 								Pelicula.java
 * 
 * Funcionalidad:Será una clase que implementara la interfaz “Serializable” ya 
 * que se va a usar objetos de esta clase como parámetros y valores de retorno de métodos RMI.
 *  
 * Se encarga de gestionar la información de la película reservada por el usuario que sera:
 * 			-id: Identificador de la película
 * 			-nombre: Nombre de la película
 * 			-tipo: Género\Tipo de películ.
 * 			-preciopordia: Precio por día
 * 			-FechaInicio: Fecha de comienzo de la reserva/alquiler
 * 			-FechaFin:Fecha de fin de la reserva/alquiler
 * 
 ****************/
/*Import para la interfaz Serializable y para los tipos Calendar*/
import java.util.*;
import java.io.*;

/*Clase Informacion que implementa la interfaz Serializable*/

public class PeliculaRes implements Serializable{
	
	/*Identificador de la película*/
	private int id;
	
	/*Nombre de la película*/
	private String nombre;
	
	/*Género\Tipo de película*/
	private String tipo;
	
	/*Precio por día*/
	private double preciopordia;
	
	/*Fecha de comienzo de la reserva/alquiler*/
	private Calendar FechaInicio;
	
	/*Fecha de fin de la reserva/alquiler*/
	private Calendar FechaFin;
	
	/*
     *	Constructor de PeliculaRes
     */
	public PeliculaRes(int id, String nombre, String tipo, double preciopordia, 
			Calendar fechaInicio, Calendar fechaFin) {
		//super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.preciopordia = preciopordia;
		FechaInicio = fechaInicio;
		FechaFin = fechaFin;
	}
	/*
     *	Getter Id
     */
	public int getId() {
		return id;
	}
	/*
     *	Setter Id
     */
	public void setId(int id) {
		this.id = id;
	}
	/*
     *	Getter Nombre
     */
	public String getNombre() {
		return nombre;
	}
	/*
     *	Setter Nombre
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/*
     *	Getter Tipo
     */
	public String getTipo() {
		return tipo;
	}
	/*
     *	Setter Tipo
     */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/*
     *	Getter Preciopordia
     */
	public double getPreciopordia() {
		return preciopordia;
	}
	/*
     *	Setter Preciopordia
     */
	public void setPreciopordia(double preciopordia) {
		this.preciopordia = preciopordia;
	}
	/*
     *	Getter FechaInicio
     */
	public Calendar getFechaInicio() {
		return FechaInicio;
	}
	/*
     *	Setter FechaInicio
     */
	public void setFechaInicio(Calendar fechaInicio) {
		FechaInicio = fechaInicio;
	}
	/*
     *	Getter FechaFin
     */
	public Calendar getFechaFin() {
		return FechaFin;
	}
	/*
     *	Setter FechaFin
     */
	public void setFechaFin(Calendar fechaFin) {
		FechaFin = fechaFin;
	}
	/*
     * Método que se encarga de calcular los dias entre la fecha de inicio y la fecha de fin
     * 
     */
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
