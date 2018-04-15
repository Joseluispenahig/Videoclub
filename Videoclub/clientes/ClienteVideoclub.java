import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ClienteVideoclub {
    static public void main (String args[]) {
        if (args.length!=4) {
            System.err.println("Uso: ClienteVideoclub hostregistro numPuertoRegistro IDusuario NombreUsuario");
            return;
        }

       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            Videoclub srv = (Videoclub) Naming.lookup("//" + args[0] + ":" + args[1] + "/Videoclub");
            Informacion info = new Informacion(Integer.parseInt(args[2]), args[3],"Apellido1","Apellido2","email","telefono");
            Usuario c = srv.crearUsuario(info);
            
            System.out.println(c.obtenerInformacion());
            System.out.println(info.getId());
    	    System.out.println(c.obtenerInformacion().getNombre());
    	    
    	    List <Pelicula> listapelis;
    	    listapelis = srv.obtenerPeliculas();
    	    
    	    for (Pelicula i: listapelis) {
    	    	System.out.println(i.getNombre());
    	    }
            /*
            c.operacion(30);

            List <Cuenta> l;
            l = srv.obtenerCuentas();
            for (Cuenta i: l) {
                Titular t = i.obtenerTitular();
                System.out.println(t + ": " +i.obtenerSaldo());
            }

            c.operacion(-5);

            l = srv.obtenerCuentas();
            for (Cuenta i: l)
                System.out.println(i.obtenerTitular() + ": " +i.obtenerSaldo());
                */
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Excepcion en ClienteVideoclub: " + e.toString());
            e.printStackTrace();
        }
    }
}
