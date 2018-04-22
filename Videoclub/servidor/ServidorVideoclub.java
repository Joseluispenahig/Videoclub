/******************	
 * 								ServidorVideoclub.java
 * 
 * Funcionalidad:Se encarga de iniciar el servicio remoto y hacerlo público usando el registro RMI 
 * (rmiregistry) que es el proceso que hace de enlazador pasandole como único argumento el numero de puerto
 * por el que está escuchando el proceso rmiregistry. 
 * 
 ****************/
/*Import para los metodos de RMI*/
import java.rmi.*;
import java.rmi.server.*;

/*Clase ServidorVideoclub*/

class ServidorVideoclub  {
	static public void main (String args[]) {
		/*Recibe un argumento que sera el numero de puerto por el que esta
		 *  escuchando rmiregistry*/
       if (args.length!=1) {
            System.err.println("Uso: ServidorVideoclub numPuertoRegistro");
            return;
        }
       
       /*Da soporte a la seguridad*/
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
        	
        	/*Creamos un objeto de la clase que implementa el servicio remoto*/
            VideoclubImpl srv = new VideoclubImpl();
            
            /*Damos de alta en rmiregistry*/
            Naming.rebind("rmi://localhost:" + args[0] + "/Videoclub", srv);
        }
        /*Gestiona las excepciones sobre errores de comunicación*/
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        /*Gestiona las excepciones sobre los errores del servidor*/
        catch (Exception e) {
            System.err.println("Excepcion en ServidorBanco:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
