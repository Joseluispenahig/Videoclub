/******************	
 * 								Información.java
 * 
 * Funcionalidad: Será una clase que implementara la interfaz “Serializable” ya 
 * que se va a usar objetos de esta clase como parámetros y valores de retorno de métodos RMI.
 * 
 * Se encarga de gestionar la información personal del usuario que sera:
 * 			-id: Identificador del usuario
 * 			-nombre: Nombre del usuario
 * 			-apellido1: Primer apellido del usuario
 * 			-apellido2: Segundo apellido del usuario
 * 			-email: Email del usuario
 * 			-telefono: Teléfono del usuario
 * 
 ****************/

/*Import para la interfaz Serializable*/
import java.io.Serializable;

/*Clase Informacion que implementa la interfaz Serializable*/

public class Informacion implements Serializable {
	
	/*Identificador del usuario*/
	private int id;
	
	/*Nombre del usuario*/
    private String nombre;
    
    /*Primer apellido del usuario*/
    private String apellido1;
    
    /*Segundo apellido del usuario*/
    private String apellido2;
    
    /*Email del usuario*/
    private String email;
    
    /*Teléfono del usuario*/
    private String telefono;
    
    /*
     *	Constructor de Informacion
     */
	public Informacion(int id, String nombre, String apellido1, String apellido2,
			String email, String telefono) {
		//super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
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
     *	Getter Apellido1
     */
	public String getApellido1() {
		return apellido1;
	}
	
	/*
     *	Setter Apellido1
     */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	/*
     *	Getter Apellido2
     */
	public String getApellido2() {
		return apellido2;
	}
	
	/*
     *	Setter Apellido2
     */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	/*
     *	Getter Email
     */
	public String getEmail() {
		return email;
	}
	
	/*
     *	Setter Email
     */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/*
     *	Getter Telefono
     */
	public String getTelefono() {
		return telefono;
	}
	
	/*
     *	Setter Telefono
     */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
    
}
