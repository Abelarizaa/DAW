package es.visualizadorcontactos2corregido;


import java.util.Scanner;

public class Utils {
	private static final int TAMAÑO_NOMBRE=50;
	private static final int TAMAÑO_APELLIDOS=50;
	private static final int TAMAÑO_TLF=10;
	
	public static Contacto crearContacto(Scanner teclado) {
		Contacto contacto=null;
		String nombre, apellidos, telefono;
		
		System.out.println("Nombre:");
		nombre=teclado.nextLine();
		System.out.println("Apellidos");
		apellidos=teclado.nextLine();
		System.out.println("Teléfono");
		telefono=teclado.nextLine();
		
		nombre=rellenar(nombre,TAMAÑO_NOMBRE);
		apellidos=rellenar(apellidos,TAMAÑO_APELLIDOS);
		telefono=rellenar(telefono,TAMAÑO_TLF);
		
		//crear contacto con datos de tamaño fijo
		contacto=new Contacto(nombre,apellidos,telefono);
		
		return contacto;
		
		
	}
	
	
	
	
	private static String rellenar(String texto,int tamaño) {
		
		while (texto.length()<tamaño) {
			texto+="*";
		}
		
		return texto;
	}
	
	static String desrellenar(String texto) {
		String texto2="";
		for (int i=0; i<texto.length();i++) {
			if(texto.charAt(i)=='*') {
				texto2=texto2+"";
			} else {
				texto2=texto2+texto.charAt(i);
			}
		}
		
		return texto2;
	}
}