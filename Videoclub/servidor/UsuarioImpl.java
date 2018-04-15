import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class UsuarioImpl extends UnicastRemoteObject implements Usuario {
    private Informacion inf;
    private List<PeliculaRes> peliculasRes;
    private double pago=0.0;
    
    UsuarioImpl(Informacion t) throws RemoteException {
        inf = t;
    }
    public Informacion obtenerInformacion() throws RemoteException {
        return inf;
    }
    /*Metodo que reservara una pelicula,por tanto se le pondra la fecha inicial la actual
    y se añadira a la lista de peliculas reservadas*/
    public void reservarPelicula(PeliculaRes pelicula) throws RemoteException{
    	pelicula.setFechaInicio(Calendar.getInstance());
    	peliculasRes.add(pelicula);
    }
    /*Metodo que devolvera una pelicula,por tanto se le pondra la fecha final
     en principio 1 mes y se añadira a la lista de peliculas reservadas*/
    public void devolverPelicula(PeliculaRes peli) throws RemoteException{
    	Calendar fechaActual=Calendar.getInstance();
    	fechaActual.add(Calendar.MONTH, 1);
    	peli.setFechaFin(fechaActual);
    	peliculasRes.remove(peli);
    }
    public double PrecioTotal(PeliculaRes peli) throws RemoteException{
    	return peli.getPreciopordia()*PeliculaRes.getDiasRestantes(peli.getFechaInicio(), peli.getFechaFin());
    }
    
}
