
import java.rmi.*;
import java.util.*;

interface Videoclub extends Remote {
	Usuario crearUsuario(Informacion t) throws RemoteException;
	List<Usuario> obtenerUsuario() throws RemoteException;
	void eliminarUsuario(int id) throws RemoteException;
	public List<Pelicula> obtenerPeliculas() throws RemoteException;
	public void reservarPelicula(int id) throws RemoteException;
	public void devolverPelicula(int id) throws RemoteException;
}
