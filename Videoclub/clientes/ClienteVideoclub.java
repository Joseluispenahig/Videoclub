import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ClienteVideoclub {
    static public void main (String args[]) {
        if (args.length!=4) {
            System.err.println("Uso: ClienteVideoclub hostregistro numPuertoRegistro nombreTitular IDTitular");
            return;
        }

       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            Videoclub srv = (Videoclub) Naming.lookup("//" + args[0] + ":" + args[1] + "/Videoclub");
            Informacion info = new Informacion(args[2], args[3]);
            Usuario c = srv.crearUsuario(info);
            
            
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
