/******************
 * 								VideoclubImpl.java
 * 
 *	Funcionalidad: El servicio “Videoclub” funcionará como una fábrica de usuarios del videoclub,
 * que será la que se registre en el rmiregistry.Tendra otros metodos como:
 * 			-Gestionar los usuarios y la eliminación de estos
 * 			-Obtener peliculas del videoclub
 * 			-Gestionar la reserva/alquiler de una pelicula en el videoclub
 * 			-Gestionar la devolución de una pelicula en el videoclub
 *	
 ****************/

/*Import correspondientes para los tipos List y para los metodos de RMI*/
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

/*Clase VideoclubImpl que implementa la interfaz Videoclub*/

class VideoclubImpl extends UnicastRemoteObject implements Videoclub {
	
	/*Lista de usuarios*/
    List<Usuario> l;
    
    /*Lista de peliculas disponibles por el videoclub*/
    List <Pelicula> peldisponibles;
    
    /*
     *	Constructor de VideoclubImpl
     */
    VideoclubImpl() throws RemoteException {
    	
        l = new LinkedList<Usuario>();
        peldisponibles = new LinkedList<Pelicula>();
        
        /*Se añade los atributos de las peliculas disponibles*/
        String [] nombre={"007","Batman Begins"};
    	String [] tipo={"Accion","Accion"};
    	int [] cantidad={1,2};
    	double [] precio={4.5,3};
    	
    	/* Se generan las peliculas con los atributos correspondientes de cada una,para ello
    	 * usamos un bucle para ir rellenando la lista
    	 */
    	for(int i=0;i<nombre.length;i++){
    		Pelicula peli=new Pelicula(i,nombre[i],tipo[i],cantidad[i],precio[i]);
    		peldisponibles.add(peli);
    	}
    }
    
    /*
     * Método que se encarga de crear un objeto "Usuario" con sus metodos correspondientes y
     * añadirlo en la lista de usuarios.
     * 
     */
    public Usuario crearUsuario(Informacion t) throws RemoteException {
        Usuario c = new UsuarioImpl(t);
        l.add(c);
        return c;
    }
    
    /*
     * Método que se encarga de obtener la lista de usuarios que hay conectados al Videoclub
     * en ese instante
     * 
     */
    public List<Usuario> obtenerUsuario() throws RemoteException {
       return l;
    }
    
    /*
     * Método que se encarga de eliminar un usuario de la lista pasandole la id por parametro 
     * 
     */
    public void eliminarUsuario(int id) throws RemoteException{
    	int indice=0;
    	boolean flag=true;
    	for (Usuario i: l) {
    		if(i.obtenerInformacion().getId()==id && flag) {
    			l.remove(indice);	
    			flag=false;
    		}
    		else {
    			indice++;
    		}
    	}	
    }
    
    /*
     * Método que se encarga de obtener la lista de peliculas disponibles por el Videoclub
     * 
     */
    public List<Pelicula> obtenerPeliculas() throws RemoteException{
    	return peldisponibles;
    }
    
    /*
     * Método que se encarga de realizar la reserva de una pelicula decrementando un valor a
     * la cantidad de peliculas con ese id
     * 
     */
    public void reservarPelicula(int id) throws RemoteException{
    	for (Pelicula i: peldisponibles) {
	    			if(i.getId()==id) {
	    				i.setNumero(i.getNumero()-1);		
	    			}
    	}
    }
    
    /*
     * Metodo que se encarga de realizar la devolución de una pelicula aumentando un valor a
     * la cantidad de peliculas con ese id
     * 
     */
    public void devolverPelicula(int id) throws RemoteException{
    	for (Pelicula i: peldisponibles) {
	    			if(i.getId()==id) {
	    				i.setNumero(i.getNumero()+1);		
	    			}
    	}
    }
    	
}
