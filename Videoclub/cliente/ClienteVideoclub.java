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
    	    int saldo=0;
    	    int dia;
    	    int mes;
    	    int año;
        	
    	    List <PeliculaRes> listapelisres;
    	    
    	    while(!salir) {
    	    		System.out.println("1. Obtener lista de películas");
    	    		System.out.println("2. Sacar película");
    	    		System.out.println("3. Devolver película");
    	    		System.out.println("4. Mostrar películas sacadas");
    	    		System.out.println("5. Añadir saldo");
    	    		System.out.println("6. Consultar saldo");
    	    		System.out.println("7. Salir");
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
    	    					System.out.println("Precio/dia: " + i.getPreciopordia());
    	    				}
    	    				System.out.println("-----------------------------\n");
    	    			}
    	    			else if(opcion==2){
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Introduce el ID de la película que quieres sacar");
    	    	    	    Calendar fechaActual=Calendar.getInstance();
    	    				Calendar fechaFinal=Calendar.getInstance();
    	    				Calendar fechaActual2=Calendar.getInstance();
    	    				Calendar fechaFinal2=Calendar.getInstance();
    	    				fechaActual.add(Calendar.MONTH,1);
    	    				fechaFinal.add(Calendar.MONTH,1);
    	    				fechaActual2.add(Calendar.MONTH,1);
    	    				fechaFinal2.add(Calendar.MONTH,1);
    	    				idpeli = sn.nextInt();
    	    				boolean encontrada=false;
    	    				List <Pelicula> listapelis;
       	       	    		listapelis = srv.obtenerPeliculas();
       	       	    		for (Pelicula i: listapelis) {
       	       	    			if(i.getId()==idpeli && i.getNumero()>0) {
       	       	    				encontrada=true;
       	       	    				System.out.println("Durante cuántos días quieres alquilar la película?");
       	       	    				dia = sn.nextInt();
       	       	    				fechaFinal.add(Calendar.DAY_OF_MONTH, dia);
       	       	    				fechaFinal2.add(Calendar.DAY_OF_MONTH, dia);
       	       	    				//PeliculaRes pelireserva= new PeliculaRes(idpeli,i.getNombre(),i.getTipo(),
       	       	    				//		3.00,fechaActual,fechaFinal);
       	       	    				PeliculaRes pelireserva= new PeliculaRes(idpeli,i.getNombre(),i.getTipo(),
           	       	    						i.getPreciopordia(),fechaActual,fechaFinal);
       	       	    				PeliculaRes pelireserva2= new PeliculaRes(idpeli,i.getNombre(),i.getTipo(),
   	       	    						i.getPreciopordia(),fechaActual2,fechaFinal2);
       	       	    				if(c.getSaldo()>c.PrecioTotal(pelireserva2)) {
       	       	    					c.reservarPelicula(pelireserva);
       	       	    					c.realizaPago();
       	       	    					srv.reservarPelicula(i.getId());
       	       	    					System.out.println("Has sacado la película con éxito\n");       	       	    				
       	       	    				}
       	       	    				else {
       	       	    					System.out.println("No tienes el suficiente saldo para sacar la película");
       	       	    				}
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
       	       	       				String cadenaFecha = String.format("%04d-%02d-%02d",
       	       	       						i.getFechaFin().get(Calendar.YEAR),
       	       	       						i.getFechaFin().get(Calendar.MONTH),
       	       	       						i.getFechaFin().get(Calendar.DAY_OF_MONTH));
       	       	       				String cadenaFechainicio = String.format("%04d-%02d-%02d",
	       	       						i.getFechaInicio().get(Calendar.YEAR),
	       	       						i.getFechaInicio().get(Calendar.MONTH),
	       	       						i.getFechaInicio().get(Calendar.DAY_OF_MONTH));
       	       	       				System.out.println("Fecha de inicio del alquiler: " + cadenaFechainicio);
       	       	       				System.out.println("Fecha de fin del alquiler: " + cadenaFecha);
    	    					}
    	    					System.out.println("-----------------------------\n");	
    	    				}
    	    				else {
    	    					System.out.println("No tienes ninguna película sacada\n");
    	    				}
    	    			}
    	    			else if(opcion==5) {
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Escribe la cantidad que quieres añadir");
        	    			saldo = sn.nextInt();
        	    			c.setSaldo(saldo);
    	    			}
    	    			else if(opcion==6) {
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println(c.getSaldo());
    	    			}
    	    			else if(opcion==7) {
    	    				salir = true;
    	    			}
    	    			else {
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Introduce un número entre el 1 y el 7\n");
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
