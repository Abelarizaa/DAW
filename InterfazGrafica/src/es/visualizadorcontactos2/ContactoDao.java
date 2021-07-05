package es.visualizadorcontactos2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class ContactoDao implements Dao {
	
	static File f;
	static RandomAccessFile fichero;
	private static final int TAMAÑO_NOMBRE=50;
	private static final int TAMAÑO_APELLIDOS=50;
	private static final int TAMAÑO_TLF=10;
	private static final int TAMAÑO_REG=220;
	
	public ContactoDao() {
		f=new File("contactos.dat");		
	}
	
	
	public static Contacto obtenerContacto(int id) {
		
		String nombre="",apellidos="",telefono="";
		f=new File("contactos.dat");
		try {
			fichero=new RandomAccessFile(f,"rw");
			fichero.seek((id-1)*TAMAÑO_REG);
			for(int i=0;i<TAMAÑO_NOMBRE;i++) {
				nombre+=fichero.readChar();
			}
			for(int i=0;i<TAMAÑO_APELLIDOS;i++) {
				apellidos+=fichero.readChar();
			}
			for(int i=0;i<TAMAÑO_TLF;i++) {
				telefono+=fichero.readChar();
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.err.println("Error al abrir para editar");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error al editar");
			e.printStackTrace();
		}	
		
		finally {
			try {
				fichero.close();
				
			} catch (IOException e) {
				System.err.println("Error al cerrar para añadir");
				e.printStackTrace();
			}
		}
		Contacto contacto= new Contacto(nombre, apellidos, telefono);
		return contacto;
	}
	
	
	
	@Override
	public void añadirContacto(Contacto c) {
		
		try {
			fichero=new RandomAccessFile(f,"rw");
			fichero.seek(fichero.length());
			fichero.writeChars(c.getNombre());
			fichero.writeChars(c.getApellidos());
			fichero.writeChars(c.getTelefono());
			
		} catch (FileNotFoundException e) {
			System.err.println("Error al abrir fichero para añadir Contacto");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error al escribir fichero para añadir Contacto");
			e.printStackTrace();
		}
		
		
		finally {
			try {
				fichero.close();
			} catch (IOException e) {
				System.err.println("Error al cerrar para añadir");
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void borrarContacto(int id) {
		
		RandomAccessFile copia=null;
		String nombre="",apellidos="",telefono="";
		
		try {
			fichero=new RandomAccessFile(f,"r");
			copia=new RandomAccessFile(new File("copia.dat"),"rw");
			
			while (fichero.getFilePointer()!=fichero.length()) {
				
				for(int i=0;i<TAMAÑO_NOMBRE;i++) {
					nombre+=fichero.readChar();
				}
				for(int i=0;i<TAMAÑO_APELLIDOS;i++) {
					apellidos+=fichero.readChar();
				}
				for(int i=0;i<TAMAÑO_TLF;i++) {
					telefono+=fichero.readChar();
				}
				
				if (fichero.getFilePointer()!=id*TAMAÑO_REG) {
					copia.writeChars(nombre);
					copia.writeChars(apellidos);
					copia.writeChars(telefono);
				}				
			}		
			
		} catch (FileNotFoundException e) {
			System.err.println("Error al abrir para borrar");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error al leer fichero para borrar Contacto");
			e.printStackTrace();
		}
		finally {
			try {
				fichero.close();
				copia.close();
			} catch (IOException e) {
				System.err.println("Error al cerrar para añadir");
				e.printStackTrace();
			}
		}
		
		//Renombrado de fichero con reemplazo del antiguo
		Path source = Paths.get("copia.dat");
        Path target = Paths.get("contactos.dat");    
        
        try{

          Files.move(source, target,StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
        	System.err.println("Error renombrado de fichero copia");
            e.printStackTrace();
        }
		
		
	}
	
	@Override
	public void editarContacto(int id) {
		
		Scanner teclado=new Scanner(System.in);
		String nombre="",apellidos="",telefono="";
		Contacto c;
		try {
			fichero=new RandomAccessFile(f,"rw");
			fichero.seek((id-1)*TAMAÑO_REG);
			for(int i=0;i<TAMAÑO_NOMBRE;i++) {
				nombre+=fichero.readChar();
			}
			for(int i=0;i<TAMAÑO_APELLIDOS;i++) {
				apellidos+=fichero.readChar();
			}
			for(int i=0;i<TAMAÑO_TLF;i++) {
				telefono+=fichero.readChar();
			}
			System.out.println("Nombre: "+nombre);
			System.out.println("Apellidos: "+apellidos);
			System.out.println("Teléfono: "+telefono);
			
			c=Utils.crearContacto(teclado);
			fichero.seek((id-1)*TAMAÑO_REG);
			fichero.writeChars(c.getNombre());
			fichero.writeChars(c.getApellidos());
			fichero.writeChars(c.getTelefono());
			
		} catch (FileNotFoundException e) {
			System.err.println("Error al abrir para editar");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error al editar");
			e.printStackTrace();
		}	
		
		finally {
			try {
				fichero.close();
				
			} catch (IOException e) {
				System.err.println("Error al cerrar para añadir");
				e.printStackTrace();
			}
		}
		
	}

}