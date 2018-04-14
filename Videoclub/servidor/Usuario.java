
import java.rmi.*;

interface Usuario extends Remote {
	Informacion obtenerInformacion() throws RemoteException;
	public void reservarPelicula(PeliculaRes pelicula) throws RemoteException;
	public void devolverPelicula(PeliculaRes peli) throws RemoteException;
	public double PrecioTotal(PeliculaRes peli) throws RemoteException;
}
