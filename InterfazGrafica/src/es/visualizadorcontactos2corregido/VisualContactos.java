package es.visualizadorcontactos2corregido;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VisualContactos {

	Contacto contacto;
	private int id=1;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private long registroActual=1;
	private long registrosTotales;
	private String contadorRegistros="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualContactos window = new VisualContactos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualContactos() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ContactoDao contactoDao = new ContactoDao();
		registrosTotales = contactoDao.contarRegistros();
		contadorRegistros=String.valueOf(registroActual)+" / " +String.valueOf(registrosTotales);
		
		
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				contactoDao.cerrar();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(27, 23, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(27, 65, 70, 15);
		frame.getContentPane().add(lblApellidos);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(27, 112, 70, 15);
		frame.getContentPane().add(lblTelefono);

		Contacto a;
		a=ContactoDao.obtenerContacto(id);
		String idTexto=String.valueOf(id);

		textField = new JTextField();
				textField.setText(contadorRegistros);
		textField.setBounds(157, 196, 141, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
				textField_1.setText(Utils.desrellenar(contacto.getNombre()));
		textField_1.setBounds(106, 21, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();

				textField_2.setText(Utils.desrellenar(contacto.getApellidos()));
		textField_2.setBounds(106, 63, 114, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();

				textField_3.setText(Utils.desrellenar(contacto.getTelefono()));

		textField_3.setBounds(106, 110, 114, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
			

			

		
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contacto = contactoDao.leerContactoSiguiente();
				
				//Comprobar si hay siguiente
				
				mostrarContacto(contacto);
				
			}
		});
		button.setBounds(310, 212, 87, 25);
		frame.getContentPane().add(button);
		
		

		
		
		
		JButton button_1 = new JButton("<");
		button_1.setBounds(50, 212, 95, 25);
		frame.getContentPane().add(button_1);
		

		
		
		
	}
	
	private void mostrarContacto(Contacto c) {
		textField_1.setText(c.getNombre());
		textField_1.setText(c.getApellidos());
		textField_1.setText(c.getTelefono());
	}
	
}
