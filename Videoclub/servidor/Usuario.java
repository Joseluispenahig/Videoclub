/******************	
 * 								Usuario.java
 * 
 * Funcionalidad: Se encarga de gestionar los siguientes atributos del usuario:
 * 			-Información: Es la información personal del usuario.
 * 			-PeliculasRes: Es una lista con las peliculas que el usuario ha reservado.
 * 			-pago: Es el pago que tiene que realizar el usuario por la reserva/alquiler.
 * 			-saldo:Es el saldo que tiene el usuario para realizar los pagos correspondientes.
 * 
 ****************/
/*Import correspondientes para los tipos List y para los metodos de RMI*/
import java.rmi.*;
import java.util.List;

/*Interfaz Usuario*/

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
