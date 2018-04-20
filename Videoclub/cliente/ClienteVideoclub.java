import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ClienteVideoclub {
    static public void main (String args[]) {
        if (args.length!=8) {
            System.err.println("Uso: ClienteVideoclub hostregistro numPuertoRegistro "
            		+ "IDusuario NombreUsuario Apellido1 Apellido2 email telefono");
            return;
        }

       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            Videoclub srv = (Videoclub) Naming.lookup("//" + args[0] + ":" + args[1] + "/Videoclub");
            Informacion info = new Informacion(Integer.parseInt(args[2]), args[3],args[4],args[5],
            		args[6],args[7]);
            Usuario c = srv.crearUsuario(info);
            if(c != null)
            {
           	System.out.print("\033[H\033[2J");
	        System.out.flush();
            System.out.println("Id: " + info.getId());
    	    System.out.println("Nombre: " + info.getNombre());
    	    System.out.println("Apellidos: " + info.getApellido1() + " " + info.getApellido2());
    	    System.out.println("email: " + info.getEmail());
    	    System.out.println("Teléfono: " + info.getTelefono() + "\n");
    	    
    	    Scanner sn = new Scanner(System.in);
    	    boolean salir = false;
    	    int opcion;
    	    int idpeli;
    	    Calendar fechaActual=Calendar.getInstance();
    	    Calendar fechaFinal=Calendar.getInstance();
        	fechaFinal.add(Calendar.MONTH, 1);
    	    List <PeliculaRes> listapelisres;
    	    
    	    while(!salir) {
    	    		System.out.println("1. Obtener lista de películas");
    	    		System.out.println("2. Sacar película");
    	    		System.out.println("3. Devolver película");
    	    		System.out.println("4. Mostrar películas sacadas");
    	    		System.out.println("5. Salir");
    	    		try { 
    	    			System.out.println("Escribe una de las opciones");
    	    			opcion = sn.nextInt();
    	           
    	    			if(opcion==1){
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				List <Pelicula> listapelis;
    	    				listapelis = srv.obtenerPeliculas();
    	       	    
    	    				for (Pelicula i: listapelis) {
    	    					System.out.println("-----------------------------");
    	    					System.out.println("ID de la película: " + i.getId());
    	    					System.out.println("Nombre de la película: " + i.getNombre());
    	    					System.out.println("Género: " + i.getTipo());
    	    					System.out.println("Cantidad disponible: " + i.getNumero());    	       	    	
    	    				}
    	    				System.out.println("-----------------------------\n");
    	    			}
    	    			else if(opcion==2){
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Introduce el ID de la película que quieres sacar");
    	    				idpeli = sn.nextInt();
    	    				boolean encontrada=false;
    	    				List <Pelicula> listapelis;
       	       	    		listapelis = srv.obtenerPeliculas();
       	       	    		for (Pelicula i: listapelis) {
       	       	    			if(i.getId()==idpeli && i.getNumero()>0) {
       	       	    				encontrada=true;
       	       	    				PeliculaRes pelireserva= new PeliculaRes(idpeli,i.getNombre(),i.getTipo(),
       	       	    						1.00,fechaActual,fechaFinal);
       	       	    				c.reservarPelicula(pelireserva);
       	       	    				srv.reservarPelicula(i.getId());
       	       	    				System.out.println("Has sacado la película con éxito\n");
       	       	    			}
       	       	    		}
       	       	    		if(encontrada==false){
       	       	    		System.out.println("No se ha encontrado la película\n");
       	       	    		}
    	    			}
    	    			else if(opcion==3) {
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Introduce el ID de la película que quieres devolver");
    	    				idpeli = sn.nextInt();
    	    				boolean encontrada=false;
       	       	    		listapelisres = c.obtenerPeliculas();
       	       	    		int indice = 0;
       	       	    		for (PeliculaRes i: listapelisres) {
       	       	    			if(i.getId()==idpeli) {
       	       	    				encontrada=true;
       	       	    				c.devolverPelicula(indice);
       	       	    				srv.devolverPelicula(idpeli);
       	       	    				System.out.println("Has devuelto la película con éxito\n");
       	       	    			}
       	       	    			indice++;
       	       	    		}
       	       	    		if(encontrada==false){
       	       	    			System.out.println("No se ha encontrado la película\n");
       	       	    		}
    	    			}
    	    			else if(opcion==4){
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				listapelisres = c.obtenerPeliculas();
    	    				if(!listapelisres.isEmpty()) {
    	    					for (PeliculaRes i: listapelisres) {
       	       	       				System.out.println("-----------------------------");
       	       	       				System.out.println("ID de la película: " + i.getId());
       	       	       				System.out.println("Nombre de la película: " + i.getNombre());
       	       	       				System.out.println("Género: " + i.getTipo());
    	    					}
    	    					System.out.println("-----------------------------\n");	
    	    				}
    	    				else {
    	    					System.out.println("No tienes ninguna película sacada\n");
    	    				}
    	    			}
    	    			else if(opcion==5) {
    	    				salir = true;
    	    			}
    	    			else {
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Introduce un número entre el 1 y el 5\n");
    	    			}
    	    		}
    	    		catch (InputMismatchException e) {
    	    			System.out.print("\033[H\033[2J");
    	    			System.out.flush();
    	    			System.out.println("Debes insertar un número\n");
    	    			sn.next();
    	            	}
    	    	}    
            }
            else {
            	System.out.println("Error al crear usuario");
            	}
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
