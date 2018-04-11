/**
 * 
 */

/**
 * @author José Luis Peña
 *
 */
public class Pelicula {
	
	private int id;
	private String nombre;
	private String tipo;
	private int numero;
	
	public Pelicula(int id, String nombre, String tipo, int numero) {
		//super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.numero = numero;
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
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
