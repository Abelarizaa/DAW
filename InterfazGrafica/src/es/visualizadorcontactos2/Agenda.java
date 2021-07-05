package es.visualizadorcontactos2;

import java.util.Scanner;

public class Agenda {
	
	

	public static void main(String[] args) {
		
		
		int opcion;
		String nombre, apellidos, telefono;
		Contacto contacto;
		Scanner teclado;
		
		ContactoDao contactoDao=new ContactoDao();
		teclado=new Scanner(System.in);
		System.out.println("1. Añadir");
		System.out.println("2. Borrar por id");
		System.out.println("3. Editar por id");
		System.out.println("4. Buscar por teléfono");
		System.out.println("5. Salir");
		opcion=Integer.parseInt(teclado.nextLine());
		
		while (opcion!=5) {
			
			switch (opcion) {
				case 1:
					contacto=Utils.crearContacto(teclado);
					contactoDao.añadirContacto(contacto);
					break;
				case 2:
					//Pedir id por teclado 
					contactoDao.borrarContacto(3);
					break;
				case 3:
					//Pedir id por teclado 
					contactoDao.editarContacto(1);
					break;
				case 4:
					
					break;
				case 5:
					System.out.println("Adios");
					break;
				default:
					System.out.println("Opcion incorrecta");
					
			}
			System.out.println("1. Añadir");
			System.out.println("2. Borrar por id");
			System.out.println("3. Editar por id");
			System.out.println("4. Buscar por teléfono");
			System.out.println("5. Salir");
			opcion=Integer.parseInt(teclado.nextLine());
		}
		teclado.close();
	}//fin main
	
	

}