import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class VideoclubImpl extends UnicastRemoteObject implements Videoclub {
    List<Usuario> l;
    List <Pelicula> peldisponibles;
    VideoclubImpl() throws RemoteException {
        l = new LinkedList<Usuario>();
        peldisponibles = new LinkedList<Pelicula>();
        String [] nombre={"007","Batman Begins"};
    	String [] tipo={"Accion","Accion"};
    	int [] cantidad={1,2};
    	double [] precio={4.5,3};
    	
    	for(int i=0;i<nombre.length;i++){
    		Pelicula peli=new Pelicula(i,nombre[i],tipo[i],cantidad[i],precio[i]);
    		peldisponibles.add(peli);
    	}
    }
    public Usuario crearUsuario(Informacion t) throws RemoteException {
        Usuario c = new UsuarioImpl(t);
        l.add(c);
        return c;
    }
    public List<Usuario> obtenerUsuario() throws RemoteException {
       return l;
    }
    
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
    
    public List<Pelicula> obtenerPeliculas() throws RemoteException{
    	return peldisponibles;
    }
    
    public void reservarPelicula(int id) throws RemoteException{
    	for (Pelicula i: peldisponibles) {
	    			if(i.getId()==id) {
	    				i.setNumero(i.getNumero()-1);		
	    			}
    	}
    }
    
    public void devolverPelicula(int id) throws RemoteException{
    	for (Pelicula i: peldisponibles) {
	    			if(i.getId()==id) {
	    				i.setNumero(i.getNumero()+1);		
	    			}
    	}
    }
    	
}
