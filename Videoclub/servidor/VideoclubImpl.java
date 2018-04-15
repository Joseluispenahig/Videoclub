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
    	
    	for(int i=0;i<nombre.length;i++){
    		Pelicula peli=new Pelicula(i,nombre[i],tipo[i],cantidad[i]);
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
    
    public void eliminarUsuario(Informacion t) throws RemoteException{
    	Usuario d = new UsuarioImpl(t);
    	l.remove(d);
    }
    public List<Pelicula> obtenerPeliculas() throws RemoteException{
    	return peldisponibles;
    }
}
