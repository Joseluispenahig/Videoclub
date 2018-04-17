import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class UsuarioImpl extends UnicastRemoteObject implements Usuario {
    private Informacion inf;
    private List<PeliculaRes> peliculasRes;
    private double pago=0.0;
    private double saldo;
    
    UsuarioImpl(Informacion t) throws RemoteException {
        inf = t;
        peliculasRes = new LinkedList<PeliculaRes>();
    }
    public Informacion obtenerInformacion() throws RemoteException {
        return inf;
    }
    /*Metodo que reservara una pelicula,por tanto se le pondra la fecha inicial la actual
    y se añadira a la lista de peliculas reservadas*/
    public void reservarPelicula(PeliculaRes pelicula) throws RemoteException{
    	/*
    	
    	Calendar fechaActual=Calendar.getInstance();
    	fechaActual.add(Calendar.MONTH, 1);
    	pelicula.setFechaFin(fechaActual);
    	*/
    	peliculasRes.add(pelicula);
    }
    /*Metodo que devolvera una pelicula,por tanto se le pondra la fecha final
     en principio 1 mes y se añadira a la lista de peliculas reservadas*/
    public void devolverPelicula(int id) throws RemoteException{
    		peliculasRes.remove(id); 	
    }
    public double PrecioTotal(PeliculaRes peli) throws RemoteException{
    	this.pago=peli.getPreciopordia()*PeliculaRes.getDiasRestantes(peli.getFechaInicio(), peli.getFechaFin());
    	return peli.getPreciopordia()*PeliculaRes.getDiasRestantes(peli.getFechaInicio(), peli.getFechaFin());
    }
    //Obtiene el saldo actual del cliente
    public double getSaldo() throws RemoteException{
    	return this.saldo;
    }
    
    //Recarga el saldo de un Usuario,para realizarle el pago
    public void setSaldo(double recarga) throws RemoteException{
    	this.saldo+=recarga;
    }
    
    //Realiza el calculo pago correspondiente
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
    
    public List<PeliculaRes> obtenerPeliculas() throws RemoteException{
    	return peliculasRes;
    }
}
