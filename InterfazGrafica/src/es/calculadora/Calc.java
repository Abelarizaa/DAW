package es.calculadora;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calc {

	/*
	 * 
	 */
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calc window = new Calc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * Hacer aplicacion que visualiza todos los datos que hemos guardado en la Agenda, se ven los datos de un contacto y podemos ir avanzando de contacto en contacto
	 * para ver el siguiente o el anterior.
	 * 
	 * Tres campos de texto Nombre apellidos y telefono
	 * y abajo el numero de registro visualizado una barra / y el numero de registros totales
	 * Dos botones abajo mayor y menor para avanzar en el fichero un registro hacia adelante y el menor para ir un
	 * contacto hacia atras.
	 * 
	 */
	public Calc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblOperando = new JLabel("Operando 1");
		lblOperando.setBounds(23, 23, 104, 15);
		frame.getContentPane().add(lblOperando);
		
		JLabel lblOperando_2 = new JLabel("Operando 2");
		lblOperando_2.setBounds(23, 62, 104, 15);
		frame.getContentPane().add(lblOperando_2);
		
		textField = new JTextField();
		textField.setBounds(121, 21, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(121, 60, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		final JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setBounds(73, 185, 272, 25);
		frame.getContentPane().add(lblResultado);
		
		JButton btnSumar = new JButton("Sumar");
		btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				double operando1=Double.parseDouble(textField.getText());
				double operando2=Double.parseDouble(textField_1.getText());
				String resultado=String.valueOf(operando1+operando2);
				
				lblResultado.setText("El resultado es "+resultado);
			}
		});
		btnSumar.setBounds(12, 121, 79, 25);
		frame.getContentPane().add(btnSumar);
		
		JButton btnRestar = new JButton("Restar");
		btnRestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				double operando1=Double.parseDouble(textField.getText());
				double operando2=Double.parseDouble(textField_1.getText());
				String resultado=String.valueOf(operando1-operando2);
				
				lblResultado.setText("El resultado es "+resultado);
			}
		});
		btnRestar.setBounds(103, 121, 89, 25);
		frame.getContentPane().add(btnRestar);
		
		JButton btnMultiplicar = new JButton("Multiplicar");
		btnMultiplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				double operando1=Double.parseDouble(textField.getText());
				double operando2=Double.parseDouble(textField_1.getText());
				String resultado=String.valueOf(operando1*operando2);
				
				lblResultado.setText("El resultado es "+resultado);
			}
		});
		btnMultiplicar.setBounds(204, 121, 114, 25);
		frame.getContentPane().add(btnMultiplicar);
		
		JButton btnDividir = new JButton("Dividir");
		btnDividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				double operando1=Double.parseDouble(textField.getText());
				double operando2=Double.parseDouble(textField_1.getText());
				String resultado=String.valueOf(operando1/operando2);
				
				lblResultado.setText("El resultado es "+resultado);
			}
		});
		btnDividir.setBounds(330, 121, 94, 25);
		frame.getContentPane().add(btnDividir);

	
	}

}
