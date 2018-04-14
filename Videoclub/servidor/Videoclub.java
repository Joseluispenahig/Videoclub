
import java.rmi.*;
import java.util.*;

interface Videoclub extends Remote {
	Usuario crearUsuario(Informacion t) throws RemoteException;
	List<Usuario> obtenerUsuario() throws RemoteException;
	void eliminarUsuario(Informacion t) throws RemoteException;
	public List<Pelicula> obtenerPeliculas() throws RemoteException;
}