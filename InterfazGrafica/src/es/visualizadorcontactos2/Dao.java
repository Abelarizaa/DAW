package es.visualizadorcontactos2;


public interface Dao {

	void añadirContacto(Contacto c);
	void borrarContacto(int id);
	void editarContacto(int id);
}