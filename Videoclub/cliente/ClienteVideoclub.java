/******************	
 * 								ClienteVideoclub.java
 * 
 *  Funcionalidad: Se encarga de conectar con el servidor remoto mediante el registro RMI,
 *  para ello, es necesario pasarle como argumento la dirección del servidor y el número de puerto.
 *  El cliente va a simular la creación de un usuario de un videoclub y le va a permitir que realice una serie
 *  de acciones: Mostrar la lista de películas disponibles en el videoclub, sacar una película, devolver
 *  una película, mostrar la lista de películas sacadas, añadir saldo, ver el saldo disponible, ver la lista
 *  de usuarios del videoclub y por último, salir. 
 *  Es necesario que se le pase mediante argumentos el id de usuario, nombre, dos apellidos, email y teléfono
 *  
 ****************/

/*Import para los metodos de RMI*/
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;


/* Clase ClienteVideoclub */
class ClienteVideoclub {
    static public void main (String args[]) {
    	
    	/* Comprueba que el número de argumentos es correcto */
        if (args.length!=8) {
            System.err.println("Uso: ClienteVideoclub hostregistro numPuertoRegistro "
            		+ "IDusuario NombreUsuario Apellido1 Apellido2 email telefono");
            return;
        }
        
        /* Da soporte a la seguridad */
       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
        	/* Creamos un objeto de la clase que implementa el servicio remoto y conectamos con el registro del servidor */
            Videoclub srv = (Videoclub) Naming.lookup("//" + args[0] + ":" + args[1] + "/Videoclub");
            /* Creamos un objeto Información que contiene la información referente al usuario */
            Informacion info = new Informacion(Integer.parseInt(args[2]), args[3],args[4],args[5],
            		args[6],args[7]);
            
            /* Creamos el usuario */
            Usuario c = srv.crearUsuario(info);
            
            /* Si el usuario se ha creado correctamente, seguimos con la ejecucuón del cliente */
            if(c != null)
            {
            	
            /* Las dos siguientes líneas son usadas para limpiar el terminal, se usan más veces a lo largo del programa */
           	System.out.print("\033[H\033[2J");
	        System.out.flush();
	        
	        /* Mostramos todos los datos del usuario */
            System.out.println("Id: " + info.getId());
    	    System.out.println("Nombre: " + info.getNombre());
    	    System.out.println("Apellidos: " + info.getApellido1() + " " + info.getApellido2());
    	    System.out.println("email: " + info.getEmail());
    	    System.out.println("Teléfono: " + info.getTelefono() + "\n");
    	    
    	    /* Creamos un objeto escanner para poder leer entradas del teclado del terminal */
    	    Scanner sn = new Scanner(System.in);
    	    
    	    /* Variable boolean que se hará true cuando elijamos la opción salir del programa */
    	    boolean salir = false;
    	    
    	    /* Variable utilizada para controlar qué opción queremos ejecutar del menú */
    	    int opcion;
    	    
    	    /* Variable que actualizará su contenido cuando queramos sacar o devolver una película, 
    	     donde el usuario es preguntado por el id de la película que quiere sacar o devolver  */
    	    int idpeli;
    	    
    	    /* Variable que almacenará el saldo del usuario */
    	    int saldo=0;
    	    
    	    /* Variable que actualizará su contenido cuando queramos sacar una película, donde el
    	     * usuario es preguntado por cúantos dias quiere alquilar la película */
    	    int dia;
        	
    	    /* Lista que almacenará la lista de las película reservadas por el usuario */
    	    List <PeliculaRes> listapelisres;
    	    
    	    /* Mientras salir sea false, permaneceremos en el menú */
    	    while(!salir) {
    	    		/* Mostramos todas las opciones disponibles */
    	    		System.out.println("1. Obtener lista de películas");
    	    		System.out.println("2. Sacar película");
    	    		System.out.println("3. Devolver película");
    	    		System.out.println("4. Mostrar películas sacadas");
    	    		System.out.println("5. Añadir saldo");
    	    		System.out.println("6. Consultar saldo");
    	    		System.out.println("7. Obtener lista de usuarios");
    	    		System.out.println("8. Salir");
    	    		try { 
    	    			System.out.println("Escribe una de las opciones");
    	    			/* Leemos la opción elegida por teclado */
    	    			opcion = sn.nextInt();
    	           
    	    			if(opcion==1){
    	    				/* Opción 1: Mostrar lista de películas */
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				/* Creamos la lista de películas y ejecutamos el método para recibir la lista */
    	    				List <Pelicula> listapelis;
    	    				listapelis = srv.obtenerPeliculas();
    	       	    
    	    				/* Recorremos la lista mostrando todos los atributos de las películas */
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
    	    				/* Opción 2: Sacar película */
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Introduce el ID de la película que quieres sacar");
    	    				/* Creamos variables calendar, dos para la fecha actual y dos para la final, son dobles porque 
    	    				 * se utlizará una para crear el objeto película que se sacará, y otro es auxiliar para ejecutar 
    	    				 * métodos de cálculo del pago  */
    	    	    	    Calendar fechaActual=Calendar.getInstance();
    	    				Calendar fechaFinal=Calendar.getInstance();
    	    				Calendar fechaActual2=Calendar.getInstance();
    	    				Calendar fechaFinal2=Calendar.getInstance();
    	    				/* Se le añade a todas las fechas un mes para que se puedan visualizar las fechas correctamente */
    	    				fechaActual.add(Calendar.MONTH,1);
    	    				fechaFinal.add(Calendar.MONTH,1);
    	    				fechaActual2.add(Calendar.MONTH,1);
    	    				fechaFinal2.add(Calendar.MONTH,1);
    	    				/* Leemos el id de la película que queremos sacar */
    	    				idpeli = sn.nextInt();
    	    				/* Variable que será true cuando la película se haya encontrado en la lista de películas del videoclub */
    	    				boolean encontrada=false;
    	    				/* Variable que será true cuando la película ya esté siendo alquilada por el usuario */
    	    				boolean repetida=false;
    	    				/* Creamos la lista de películas disponibles y la obtenemos del servidor */
    	    				List <Pelicula> listapelis;
       	       	    		listapelis = srv.obtenerPeliculas();
       	       	    		/* Obtenemos la lista de películas reservadas por el usuario */
       	       	    		listapelisres = c.obtenerPeliculas(); 
       	       	    		
       	       	    		//Primero comprobamos si la pelicula que queremos sacar ya está alquilada
       	       	    		for(PeliculaRes j: listapelisres) {
       	       	    			if(idpeli==j.getId()) {
       	       	    				repetida=true;
       	       	    			}       	       	    				
       	       	    		}       	
       	       	    		/* Recorremos la lista de películas disponibles */
       	       	    		for (Pelicula i: listapelis) {    
       	       	    			/* Si el id de la película es igual al id pedido al usuario, entramos */
       	       	    			if(i.getId()==idpeli && encontrada==false) {
       	       	    				/* Actualizamos el valor de la variable encontrada */
       	       	    				encontrada=true;
       	       	    				/* Si existen películas disponibles, entramos */
       	       	    				if(i.getNumero()>0) {
       	       	    					/* Si la película no ha sido alquilada, entramos */
       	       	    					if(repetida==false) {
       	       	    						/* Leemos el número de dias que queremos alquilar la película
       	       	    						 *  y se lo añadimos a las variables de fecha final*/
       	       	    						System.out.println("Durante cuántos días quieres alquilar la película?");
       	       	    						dia = sn.nextInt();
       	       	    						fechaFinal.add(Calendar.DAY_OF_MONTH, dia);
       	       	    						fechaFinal2.add(Calendar.DAY_OF_MONTH, dia);
       	       	    						/* Creamos los objetos película reservada con los datos correspondientes */
       	       	    						PeliculaRes pelireserva= new PeliculaRes(idpeli,i.getNombre(),i.getTipo(),
       	       	    								i.getPreciopordia(),fechaActual,fechaFinal);
       	       	    						PeliculaRes pelireserva2= new PeliculaRes(idpeli,i.getNombre(),i.getTipo(),
       	       	    								i.getPreciopordia(),fechaActual2,fechaFinal2);
       	       	    						/* Comprobamos que nuestro saldo es mayor que el precio que nos cuesta alquilar la película */
       	       	    						if(c.getSaldo()>c.PrecioTotal(pelireserva2)) {
       	       	    							/* Reservamos la película y hacemos el pago */
       	       	    							c.reservarPelicula(pelireserva);
       	       	    							c.realizaPago();
       	       	    							/* Actualizamos la cantidad de películas disponibles */
       	       	    							srv.reservarPelicula(i.getId());
       	       	    							System.out.println("Has sacado la película con éxito\n");       	       	    				
       	       	    						}
       	       	    						else {
       	       	    							/* Si el saldo es menor que el precio de sacar la película */
       	       	    							System.out.println("No tienes el suficiente saldo para sacar la película");
       	       	    						}
       	       	    					}
       	       	    					else {
       	       	    						/* Si ya tenemos alquilada esa película */
       	       	    						System.out.println("Película repetida\n");
       	       	    					}
       	       	    				}
       	       	    				else {
       	       	    					/* Si no hay películas disponibles */
       	       	    					System.out.println("Película no disponible\n");
       	       	    				}
       	       	    			}       	       	    		
       	       	    		}
       	       	    		if(encontrada==false) {
       	       	    			/* Si la película no existe */
       	       	    			System.out.println("No se ha encontrado la película\n");
       	       	    		}
    	    			}
    	    			else if(opcion==3) {
    	    				/* Opción 3: Devolver película */
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Introduce el ID de la película que quieres devolver");
    	    				/* Leemos el id de la película que queremos devolver */
    	    				idpeli = sn.nextInt();
    	    				/* Variable que nos dirá si la película ha sido encontrada o no */
    	    				boolean encontrada=false;
    	    				/* Actualizamos la lista de películas reservadas */
       	       	    		listapelisres = c.obtenerPeliculas();
       	       	    		/* Variable utilizada para llevar la cuenta de la posición donde se encuentra en la lista 
       	       	    		 * la película que queremos devolver */
       	       	    		int indice = 0;
       	       	    		/* Recorremos la lista de películas reservadas */
       	       	    		for (PeliculaRes i: listapelisres) {
       	       	    			/* Si encontramos la película, entramos */
       	       	    			if(i.getId()==idpeli) {
       	       	    				encontrada=true;
       	       	    				/* Eliminamos la película de la lista de películas reservadas */
       	       	    				c.devolverPelicula(indice);
       	       	    				/* Actualizamos la cantidad de películas disponibles */
       	       	    				srv.devolverPelicula(idpeli);
       	       	    				System.out.println("Has devuelto la película con éxito\n");
       	       	    			}
       	       	    			indice++;
       	       	    		}
       	       	    		if(encontrada==false){
       	       	    			/* Si no se ha encontrado la película */
       	       	    			System.out.println("No se ha encontrado la película\n");
       	       	    		}
    	    			}
    	    			else if(opcion==4){
    	    				/* Opción 4: Mostrar películas reservadas */
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				/* Actualizamos la lista de películas reservadas */
    	    				listapelisres = c.obtenerPeliculas();  
    	    				/* Recorremos la lista de películas reservadas */
    	    				if(!listapelisres.isEmpty()) {
    	    					for (PeliculaRes i: listapelisres) {
    	    						/* Mostramos los atributos de cada película de la lista */
    	    						Calendar fechainicioaux = i.getFechaInicio();
    	    						Calendar fechafinalaux = i.getFechaFin();
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
       	       	       				System.out.println("Dias restantes para la devolución: " + 
       	       	       				PeliculaRes.getDiasRestantes(fechainicioaux, fechafinalaux));
    	    					}
    	    					System.out.println("-----------------------------\n");	
    	    				}
    	    				else {
    	    					/* Si la lista está vacía */
    	    					System.out.println("No tienes ninguna película sacada\n");
    	    				}
    	    			}
    	    			else if(opcion==5) {
    	    				/* Opción 5: Añadir saldo */
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Escribe la cantidad que quieres añadir");
    	    				/* Leemos por teclado la cantidad que queremos añadir y lo añadimos */
        	    			saldo = sn.nextInt();
        	    			c.setSaldo(saldo);
    	    			}
    	    			else if(opcion==6) {
    	    				/* Opción 6: Mostrar saldo */
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Saldo disponible: " + c.getSaldo() + "€\n");
    	    			}
    	    			else if(opcion==7) {
    	    				/* Opción 7: Mostrar lista de usuarios */
    	    				/* Obtenemos la lista de usuarios y la recorremos mostrando sus atributos */
    	    				List <Usuario> listausuarios = srv.obtenerUsuario();
    	    				Informacion infoaux;
    	    				if(!listausuarios.isEmpty()) {
    	    					System.out.print("\033[H\033[2J");
        	    				System.out.flush();
    	    					for (Usuario i: listausuarios) {
    	    						infoaux = i.obtenerInformacion();
    	    						System.out.println("------------");
    	    						System.out.println("Id: " + infoaux.getId());
    	    			    	    System.out.println("Nombre: " + infoaux.getNombre());
    	    			    	    System.out.println("Apellidos: " + infoaux.getApellido1() + " " + info.getApellido2());
    	    			    	    System.out.println("email: " + infoaux.getEmail());
    	    			    	    System.out.println("Teléfono: " + infoaux.getTelefono());   	    			    	    
    	    					}
    	    					System.out.println("------------\n");
    	    				}
    	    				else {
    	    					/* Si la lista está vacía */
    	    					System.out.println("La lista de usuarios está vacía");
    	    				}
    	    			}
    	    			else if(opcion==8) {
    	    				/* Opción 8: Salir del programa */
    	    				/* Actualizamos el valor de la variable salir y eliminamos el usuario actual de la lista de usuarios */
    	    				salir = true;
    	    				srv.eliminarUsuario(info.getId());
    	    			}
    	    			else {
    	    				/* Si no se ha elegido un número entre el 1 y el 8 */
    	    				System.out.print("\033[H\033[2J");
    	    				System.out.flush();
    	    				System.out.println("Introduce un número entre el 1 y el 8\n");
    	    			}
    	    		}
    	    		catch (InputMismatchException e) {
    	    			/* Si no se ha introducido un número */
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
