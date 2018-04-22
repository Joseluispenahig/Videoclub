/******************	
 * 								Pelicula.java
 * 
 * Funcionalidad:Será una clase que implementara la interfaz “Serializable” ya 
 * que se va a usar objetos de esta clase como parámetros y valores de retorno de métodos RMI.
 *  
 * Se encarga de gestionar la información de la película del videoclub que sera:
 * 			-id: Identificador de la película
 * 			-nombre: Nombre de la película
 * 			-tipo: Género\Tipo de película
 * 			-numero: Cantidad de películas
 * 			-preciopordia: Precio por día.
 * 
 ****************/

/*Import para la interfaz Serializable*/
import java.io.*;

/*Clase Informacion que implementa la interfaz Serializable*/

public class Pelicula implements Serializable{
	
	/*Identificador de la película*/
	private int id;
	
	/*Nombre de la película*/
	private String nombre;
	
	/*Género\Tipo de película*/
	private String tipo;
	
	/*Cantidad de películas*/
	private int numero;
	
	/*Precio por día*/
	private double preciopordia;
	
	/*
     *	Constructor de Pelicula
     */
	public Pelicula(int id, String nombre, String tipo, int numero,double precio) {
		//super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.numero = numero;
		this.preciopordia=precio;
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
     *	Getter Numero
     */
	public int getNumero() {
		return numero;
	}
	
	/*
     *	Setter Numero
     */
	public void setNumero(int numero) {
		this.numero = numero;
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
	
	
}
