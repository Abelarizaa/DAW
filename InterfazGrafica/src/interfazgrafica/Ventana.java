package interfazgrafica;
/* Por consola cada vez que has hecho el boton aparece el numero de veces que has hecho click*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
/* Hacer calculadora suma resta multiplicacion y division*/
public class Ventana {

	private JFrame frmVentana;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int num=0;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frmVentana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * Va a pedir, boton, area de texto, etiqueta y campo de texto
	 */
	private void initialize() {
		frmVentana = new JFrame();
		frmVentana.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
		});
		frmVentana.setTitle("Ventana");
		frmVentana.setBounds(100, 100, 450, 300);
		frmVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Pulsar");
		btnNewButton.setBounds(49, 50, 123, 25);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("Has hecho clock!");
			
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			int num=0;
			public void actionPerformed(ActionEvent arg0) {
			
				System.out.println("Has hecho click "+num+" veces");
				num++;
				
			}
		});
		frmVentana.getContentPane().setLayout(null);
		frmVentana.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(259, 55, 70, 15);
		frmVentana.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(109, 111, 114, 19);
		frmVentana.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(317, 100, 76, 89);
		frmVentana.getContentPane().add(textArea);
	}
}
