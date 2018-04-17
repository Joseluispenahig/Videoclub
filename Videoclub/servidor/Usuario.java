
import java.rmi.*;
import java.util.List;

interface Usuario extends Remote {
	Informacion obtenerInformacion() throws RemoteException;
	public void reservarPelicula(PeliculaRes pelicula) throws RemoteException;
	public void devolverPelicula(int id) throws RemoteException;
	public double PrecioTotal(PeliculaRes peli) throws RemoteException;
	public double getSaldo() throws RemoteException;
	public void setSaldo(double recarga) throws RemoteException;
	public boolean realizaPago() throws RemoteException;
	public List<PeliculaRes> obtenerPeliculas() throws RemoteException;
}
