/******************	
 * 								Videoclub.java
 * 
 * Funcionalidad: El servicio “Videoclub” funcionará como una fábrica de usuarios del videoclub,
 * que será la que se registre en el rmiregistry.Tendra otros metodos como:
 * 			-Gestionar los usuarios y la eliminación de estos
 * 			-Obtener peliculas del videoclub
 * 			-Gestionar la reserva/alquiler de una pelicula en el videoclub
 * 			-Gestionar la devolución de una pelicula en el videoclub
 * 
 * 
 ****************/
/*Import correspondientes para los tipos List y para los metodos de RMI*/
import java.rmi.*;
import java.util.*;

/*Interfaz Videoclub*/

interface Videoclub extends Remote {
	Usuario crearUsuario(Informacion t) throws RemoteException;
	List<Usuario> obtenerUsuario() throws RemoteException;
	void eliminarUsuario(int id) throws RemoteException;
	public List<Pelicula> obtenerPeliculas() throws RemoteException;
	public void reservarPelicula(int id) throws RemoteException;
	public void devolverPelicula(int id) throws RemoteException;
}
