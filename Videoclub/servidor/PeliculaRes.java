import java.util.*;

/**
 * 
 */

/**
 * @author salas
 *
 */
public class PeliculaRes {
	private int id;
	private String nombre;
	private String tipo;
	private int preciopordia;
	private Date FechaInicio;
	private Date FechaFin;
	
	public PeliculaRes(int id, String nombre, String tipo, int preciopordia, Date fechaInicio, Date fechaFin) {
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
	public int getPreciopordia() {
		return preciopordia;
	}
	public void setPreciopordia(int preciopordia) {
		this.preciopordia = preciopordia;
	}
	public Date getFechaInicio() {
		return FechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return FechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		FechaFin = fechaFin;
	}
	
	

}
