/******************	
 * 								UsuarioImpl.java
 * 
 * Funcionalidad: Se encarga de gestionar los siguientes atributos del usuario:
 * 			-Informacion: Es la información personal del usuario.
 * 			-PeliculasRes: Es una lista con las peliculas que el usuario ha reservado.
 * 			-pago: Es el pago que tiene que realizar el usuario por la reserva/alquiler.
 * 			-saldo:Es el saldo que tiene el usuario para realizar los pagos correspondientes.
 * 
 ****************/
/*Import correspondientes para los tipos List y para los metodos de RMI*/
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

/*Clase UsuarioImpl que implementa la interfaz Usuario*/

class UsuarioImpl extends UnicastRemoteObject implements Usuario {
	
	/*Información personal del usuario*/
    private Informacion inf;
    
    /*Lista de peliculas reservadas por el usuario*/
    private List<PeliculaRes> peliculasRes;
    
    /*Pago que tendrá que realizar el usuario*/
    private double pago=0.0;
    
    /*Saldo que tendrá el usuario*/
    private double saldo=0.0;
    
    /*
     *	Constructor de UsuarioImpl
     */
    UsuarioImpl(Informacion t) throws RemoteException {
        inf = t;
        peliculasRes = new LinkedList<PeliculaRes>();
    }
    
    /*
     * Método que se encarga obtener la información del usuario
     * 
     */
    public Informacion obtenerInformacion() throws RemoteException {
        return inf;
    }
    
    /*
     * Método que se encarga de añadir la pelicula seleccionada en la lista 
     * de peliculas reservadas por el usuario
     * 
     */
    public void reservarPelicula(PeliculaRes pelicula) throws RemoteException{
    	peliculasRes.add(pelicula);
    }
    
    /*
     * Método que se encarga de quitar la pelicula con un id de la lista 
     * de peliculas reservadas por el usuario
     * 
     */
    public void devolverPelicula(int id) throws RemoteException{
    		peliculasRes.remove(id); 	
    }
    
    /*
     * Método que se encarga de calcular el pago que tiene que realizar el usuario por el
     * la reserva de una pelicula.Para ello se calcula asi:
     * 		precio= preciopordia(de la pelicula) * dias(desde que se realizo la reserva hasta que termina)
     * 
     */
    public double PrecioTotal(PeliculaRes peli) throws RemoteException{
    	double precio;
    	precio=peli.getPreciopordia()*PeliculaRes.getDiasRestantes(peli.getFechaInicio(), peli.getFechaFin());
    	this.pago=precio;
    	return precio;
    }
    
    /*
     * Método que se encarga de obtener el saldo actual del cliente
     * 
     */
    public double getSaldo() throws RemoteException{
    	return this.saldo;
    }
    
    /*
     * Método que se encarga de recargar el saldo de un Usuario,para poder realizar el pago
     * 
     */
    public void setSaldo(double recarga) throws RemoteException{
    	this.saldo+=recarga;
    }
    
    /*
     * Método que se encarga de realizar el pago comprobando si el usuario tiene saldo
     * o si puede pagarlo con el saldo que tiene actualmente. 
     * 
     */
    public boolean realizaPago() throws RemoteException{
    	boolean correcto=true;
    	if(this.saldo==0 || this.saldo<this.pago){
    		correcto=false;
    	}
    	else{
    		this.saldo-=this.pago;
    	}
    	return correcto;
    }
    
    /*
     * Método que se encarga de obtener la lista de peliculas reservadas por el usuario
     * 
     */
    public List<PeliculaRes> obtenerPeliculas() throws RemoteException{
    	return peliculasRes;
    }
}
