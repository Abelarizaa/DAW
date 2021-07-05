package es.visualizadorcontactos;


import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Main_Agenda {

	public static void main(String[] args) {

		
		//VARIABLES
		String nombre = "";
		String apellido = "";
		String telefono = "";
		int decision = 0;//Se le da este valor inicial para que entre en el bucle 1 vez por lo menos
		int id = 0;
		File fichero = new File("Contactos.dat");
		File idFichero = new File("id.dat");
		Scanner entrada = new Scanner (System.in);
		
		
		
		//Si el fichero existe lee el id y le da ese dato al id y sino se queda inicializada a 0
		if(idFichero.exists()) {
		      try(DataInputStream dis=new DataInputStream(new FileInputStream(idFichero))){
		            //Leemos el id y se lo asignamos a nuestra variable id
		            id = dis.readInt();
		        }catch(EOFException e){
		            System.out.println("Fin del fichero");
		        }catch(IOException e){
		            System.out.println("Error E/S");
		        }
		}

	
//----------INICIO DEL BUCLE DEL MENU----------------		
		while(decision != 6) {
			
			System.out.println("MENU \n1.Añadir contacto \n2.Eliminar contacto \n3.Editar contacto \n4.Buscar contacto \n5.Mostrar todos los contactos \n6.Salir");
			decision = entrada.nextInt();
			String espacio = entrada.nextLine();
			
			switch (decision) {
			
			case 1://Añadir contacto
				
				//VARIABLES DE CASE
				boolean nombreValido = false;
				boolean apellidoValido = false;
				boolean telefonoValido = false;
			
				while(!nombreValido) {//Comprueba que no ocupe mas de 20 char y si lo hace te hace volver a poner el nombre
					System.out.println("Introduce el nombre");
					nombre = entrada.nextLine();
					while(nombre.length()<20) {//si ocupa menos de 20 char rellena con espacios hasta los 20 char
						nombre=nombre+" ";
					}
					if(nombre.length()>20) {//Si ocupa mas de 20 char te dice que te pasaste de longitud
						System.out.println("Nombre supera la longitud maxima(20)");
					}else {//Si ocupa 20 o menos char cambia el booleano que hace que te vuelva a pedir el nombre
						nombreValido = true;
					}
				}
				
				while(!apellidoValido) {//Comprueba que no ocupe mas de 20 char y si lo hace te hace volver a poner el apellidos
					System.out.println("Introduce el apellido");
					apellido = entrada.nextLine();
					while(apellido.length()<20) {//si ocupa menos de 20 char rellena con espacios hasta los 20 char
						apellido=apellido+" ";
					}
					if(apellido.length()>20) {//Si ocupa mas de 20 char te dice que te pasaste de longitud
						System.out.println("Apellido supera la longitud maxima(20)");
					}else {//Si ocupa 20 o menos char cambia el booleano que hace que te vuelva a pedir el apellido
						apellidoValido = true;
					}
				}
				
				while(!telefonoValido) {//Comprueba que no ocupe mas de 20 char y si lo hace te hace volver a poner el telefono
					System.out.println("Introduce el telefono");
					telefono = entrada.nextLine();
					while(telefono.length()<20) {//si ocupa menos de 20 char rellena con espacios hasta los 20 char
						telefono=telefono+" ";
					}
					if(telefono.length()>20) {//Si ocupa mas de 20 char te dice que te pasaste de longitud
						System.out.println("Telefono supera la longitud maxima(20)");
					}else {//Si ocupa 20 o menos char cambia el booleano que hace que te vuelva a pedir el nombre
						telefonoValido = true;
					}
				}
				
				//Mete los datos en el fichero
				 try(RandomAccessFile file = new RandomAccessFile(fichero, "rw")){		 
					 file.seek(file.length());//Se va a la posicion final del fichero			 
					 id++;//aumentamos el id para que cambie entre un contacto y otro
					 //metemos los datos
					 file.writeInt(id);//ocupa 4
					 file.writeChars(nombre);//ocupa 40
					 file.writeChars(apellido);// ocupa 40
					 file.writeChars(telefono);	 //ocupa 40
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				break;	
//		
			case 2://Eliminar contacto por id
				
				//VARIABLES CASE
				int idEliminar = 0;
				if(fichero.length()>1) {
				File ficheroEliminar = new File("ContactosEliminar.dat");		
				boolean idpasada= false;
				int contador = 0;
				boolean idValida = false;
				int idPedida = 0;
				
				try(RandomAccessFile file = new RandomAccessFile(fichero, "r")){
					while(!idValida) {//Repite el bucle si la id es 0 o negativa
						System.out.println("Introduce la id a editar");
						idPedida = entrada.nextInt();
						String space = entrada.nextLine();//Al leer un int se queda un espacio en blanco, esto lo quita(lo lee y ya esta)
						if (idPedida<=0) {//Comprueba que la id no sea 0 o negativa
							System.out.println("Rango de id no valido");
						}else {
							idValida = true;
						}
					}				
		
					try(RandomAccessFile nfile = new RandomAccessFile(ficheroEliminar, "rw")){		
							while(file.getFilePointer() != file.length()) {//Leemos y escribimos hasta fin de fichero
								String nomEliminar = "", apellEliminar = "", telEliminar = "";//Reinicia las variables
/*detector				System.out.println(file.getFilePointer());
						System.out.println(file.length());
*/	
								idEliminar = file.readInt();
					
								for (int j = 0; j < 20; j++) {
									nomEliminar = nomEliminar + file.readChar();
								}
								for (int j = 0; j < 20; j++) {
									apellEliminar = apellEliminar + file.readChar();
								}
								for (int j = 0; j < 20; j++) {
									telEliminar = telEliminar + file.readChar();
								}			
								
								if(idpasada) {//Hace que desde el momento que eliminamos la id las demas id disminuyan en 1 para que se mantenga el orden
									contador = -1;
								}
									if(idEliminar != idPedida) {
										
										int idResultado = idEliminar-contador;
										
										nfile.writeInt(idResultado);//ocupa 4
										nfile.writeChars(nomEliminar);//ocupa 40
										nfile.writeChars(apellEliminar);// ocupa 40
										nfile.writeChars(telEliminar);	 //ocupa 40
							
									}else {
										idpasada = true;
									}
								
							}
					}catch (IOException e) {						
						e.printStackTrace();
					}
					
				}catch (IOException e) {					
					e.printStackTrace();
				}		
					Path origenPath = FileSystems.getDefault().getPath("ContactosEliminar.dat");//Ruta origen
					Path destinoPath = FileSystems.getDefault().getPath("Contactos.dat");//Ruta destino
				
				try {
					Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);// Cambia el nombre  del fichero origen al del fichero destino y lo  reemplaza si ya existia un fichero con ese nombre
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				 idpasada= false;//Se devuelve la variable a falso para la siguienete vez que borres
				}else {
					System.out.println("------------------------------\n No hay contactos que eliminar \n------------------------------");
				}	
				
				id--;//Disminuye el id en uno porque has borrado 1(ejemplo tenias 5 contactos, el ultimo id es 5, borras 1, el ultimo id cambia a 4 para que la proxima vez que metas un dato sea 5)
				break;	
				
			case 3://editamos el contacto(buscado por id)
			
				//VARIABLES DE CASE
				boolean nombreValido2 = false;
				boolean apellidoValido2 = false;
				boolean telefonoValido2 = false;
				boolean idValida2 = false;
				int idEditar = 0;
				int idPedida = 0;
				if(fichero.length()>1) {
				File ficheroEditar = new File("ContactosEditar.dat");
				String nombreEditado = "", apellidoEditado="", telefonoEditado="";
				
				while(!idValida2) {//Repite el bucle si la id es 0 o negativa
					System.out.println("Introduce la id a editar");
					idPedida = entrada.nextInt();
					String space = entrada.nextLine();//Al leer un int se queda un espacio en blanco, esto lo quita(lo lee y ya esta)
					if (idPedida<=0) {//Comprueba que la id no sea 0 o negativa
						System.out.println("Rango de id no valido");
					}else {
						idValida2 = true;
					}
				}
				
				while(!nombreValido2) {//Comprueba que no ocupe mas de 20 char y si lo hace te hace volver a poner el nombre
					System.out.println("Introduce el nuevo nombre");
					nombreEditado = entrada.nextLine();
					while(nombreEditado.length()<20) {//si ocupa menos de 20 char rellena con espacios hasta los 20 char
						nombreEditado=nombreEditado+" ";
					}
					if(nombreEditado.length()>20) {//Si ocupa mas de 20 char te dice que te pasaste de longitud
						System.out.println("Nombre supera la longitud maxima(20)");
					}else {//Si ocupa 20 o menos char cambia el booleano que hace que te vuelva a pedir el nombre
						nombreValido2 = true;
					}
				}
				
				while(!apellidoValido2) {//Comprueba que no ocupe mas de 20 char y si lo hace te hace volver a poner el apellidos
					System.out.println("Introduce el nuevo apellido");
					apellidoEditado = entrada.nextLine();
					while(apellidoEditado.length()<20) {//si ocupa menos de 20 char rellena con espacios hasta los 20 char
						apellidoEditado=apellidoEditado+" ";
					}
					if(apellidoEditado.length()>20) {//Si ocupa mas de 20 char te dice que te pasaste de longitud
						System.out.println("Apellido supera la longitud maxima(20)");
					}else {//Si ocupa 20 o menos char cambia el booleano que hace que te vuelva a pedir el apellido
						apellidoValido2 = true;
					}
				}
				
				while(!telefonoValido2) {//Comprueba que no ocupe mas de 20 char y si lo hace te hace volver a poner el telefono
					System.out.println("Introduce el nuevo telefono");
					telefonoEditado = entrada.nextLine();
					while(telefonoEditado.length()<20) {//si ocupa menos de 20 char rellena con espacios hasta los 20 char
						telefonoEditado=telefonoEditado+" ";
					}
					if(telefono.length()>20) {//Si ocupa mas de 20 char te dice que te pasaste de longitud
						System.out.println("Telefono supera la longitud maxima(20)");
					}else {//Si ocupa 20 o menos char cambia el booleano que hace que te vuelva a pedir el nombre
						telefonoValido2 = true;
					}
				}
					try(RandomAccessFile file = new RandomAccessFile(fichero, "rw")){
						try(RandomAccessFile nfile = new RandomAccessFile(ficheroEditar, "rw")){	
							while(file.getFilePointer() != file.length()) {//Leemos y escribimos hasta fin de fichero
								String nomEd = "", apellEd = "", telEd = "";//Reinicia las variables
								idEditar = file.readInt();//Lee el id
							
								for (int j = 0; j < 20; j++) {//Bucle para leer el nombre char a char
									nomEd = nomEd + file.readChar();
								}
								for (int j = 0; j < 20; j++) {//Bucle para leer el apellido char a char
									apellEd = apellEd + file.readChar();
								}
								for (int j = 0; j < 20; j++) {//Bucle para leer el telefono char a char
									telEd = telEd + file.readChar();
								}
								if(idEditar != idPedida) {//Escribimos los datos que leemos									
									nfile.writeInt(idEditar);//ocupa 4
									nfile.writeChars(nomEd);//ocupa 40
									nfile.writeChars(apellEd);// ocupa 40
									nfile.writeChars(telEd);	 //ocupa 40							
								}else {//Escribimos los nuevos datos en la posicion que pidio									
									nfile.writeInt(idPedida);//ocupa 4
									nfile.writeChars(nombreEditado);//ocupa 40
									nfile.writeChars(apellidoEditado);// ocupa 40
									nfile.writeChars(telefonoEditado);//ocupa 40
								}												
							}
						}catch (IOException e) {						
							e.printStackTrace();
						}	
						
						
						/*FORMA 2 DE HACERLO, SOBBREESCRIBIENDO EL FICHERO
						 	file.seek(idPedida*124);
						 	file.writeInt(idPedida);//ocupa 4
							file.writeChars(nombreEditado);//ocupa 40
							file.writeChars(apellidoEditado);// ocupa 40
							file.writeChars(telefonoEditado);//ocupa 40
						 */
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					Path origenPath2 = FileSystems.getDefault().getPath("ContactosEditar.dat");//Ruta origen
					Path destinoPath2 = FileSystems.getDefault().getPath("Contactos.dat");//Ruta destino
					try {
						Files.move(origenPath2, destinoPath2, StandardCopyOption.REPLACE_EXISTING);// Cambia el nombre  del fichero origen al del fichero destino y lo  reemplaza si ya existia un fichero con ese nombre
					} catch (IOException e) {
						System.err.println(e);
					}	
				}else {
					System.out.println("------------------------------\n No hay contactos que editar \n------------------------------");
				}
				break;	
				
			case 4://Buscamos el contacto por telefono y mostramos los datos
				
				if(fichero.length()>1) {
				try(RandomAccessFile file = new RandomAccessFile(fichero, "rw")){
					
					System.out.println("Introduce el telefono a buscar");
					String telPedido = entrada.nextLine();
					
					while(telPedido.length()<20) {
						telPedido = telPedido+" ";
					}					
					while(file.getFilePointer() != file.length()) {
						//VARIABLES CASE
						String telReferencia = "", nomBuscar = "", apellBuscar = "", telBuscar = "";
						int idBuscar;	
/*detector				System.out.println(file.getFilePointer());
						System.out.println(file.length());
*/						
						file.seek(file.getFilePointer()+84);
						for (int j = 0; j < 20; j++) {
							telReferencia = telReferencia + file.readChar();
						}
						if(telReferencia.equalsIgnoreCase(telPedido)) {
							file.seek(file.getFilePointer()-124);
						
							idBuscar = file.readInt();//Lee el id
						
							for (int j = 0; j < 20; j++) {//Bucle para leer el nombre char a char
								nomBuscar = nomBuscar + file.readChar();
							}
							for (int j = 0; j < 20; j++) {//Bucle para leer el apellido char a char
								apellBuscar = apellBuscar + file.readChar();
							}
							for (int j = 0; j < 20; j++) {//Bucle para leer el telefono char a char
								telBuscar = telBuscar + file.readChar();
							}
							//Muestro los datos por pantalla
							System.out.println("---------------------------------\n id: "+idBuscar+" \n nombre: "+nomBuscar+" \n Apellido: "+apellBuscar+"\n Telefono: "+telBuscar+"\n---------------------------------");	
						}			
					}			
				}catch (IOException e) {						
					e.printStackTrace();
				}
				}else {
					System.out.println("------------------------------\n No hay contactos que buscar \n------------------------------");

				}
				break;
				
			case 5:		
				
				if(fichero.length()>1) {
				try(RandomAccessFile file = new RandomAccessFile(fichero, "rw")){
					while(file.getFilePointer() != file.length()) {
/*detector				System.out.println(file.getFilePointer());
						System.out.println(file.length());
*/
						
						//VARIABLES CASE
						String nomMostrar = "", apellMostrar = "", telMostrar = "";
						int idMostrar;
						
						idMostrar = file.readInt();//Leo el id
				
						for (int j = 0; j < 20; j++) {//Bucle para leer el nombre char a char
							nomMostrar = nomMostrar + file.readChar();
						}
						for (int j = 0; j < 20; j++) {//Bucle para leer el apellido char a char
							apellMostrar = apellMostrar + file.readChar();
						}
						for (int j = 0; j < 20; j++) {//Bucle para leer el telefono char a char
							telMostrar = telMostrar + file.readChar();
						}
						//Muestro los datos por pantalla
						System.out.println("---------------------------------\n id: "+idMostrar+" \n nombre: "+nomMostrar+" \n Apellido: "+apellMostrar+"\n Telefono: "+telMostrar+"\n---------------------------------");	
					}
				}catch (IOException e) {						
					e.printStackTrace();
				}	
				}else {
					System.out.println("------------------------------\n No hay contactos que mostrar \n------------------------------");

				}
				break;
			
			case 6://Salir
				
				System.out.println("Fin del programa");//Nos indica que finaliza el programa
				
				if(idFichero.exists()) {//Si el fichero existe lo borra
					idFichero.delete();
				}
				//Crea un fichero en el que guarda el numero de id por el que va 
				 try(DataOutputStream dos=new DataOutputStream(new FileOutputStream(idFichero));){	 
			            //Guardamos el id en el que nos hemos quedado
			            dos.writeInt(id);
			        }catch(IOException e){
			            System.out.println("Error E/S");
			        }				 
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}			
		}	
	}
}


/*
 * - Modificaciones pendientes:
 * 		- Modularizar
 * 		- Cambiar nombres algunas variables para que se vea mas claro lo que es
 * 		- Añadir comentarios mas precisos a algunas zonas
 * 		- Que se guarde el id cada vez que creamos o eliminamos un contacto
 * 
 * -NOTAS:
 * 		-Salir con el 6 de la aplicacion para que se guarde el id, sino no se guarda(pendiente de cambio)
 * 
 * 
 */




