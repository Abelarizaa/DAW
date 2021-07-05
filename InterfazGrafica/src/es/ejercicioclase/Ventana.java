package es.ejercicioclase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Ventana {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private String textofinal="", productofinal="";
	private int totalprod,uds,precio,total=0;
	private JTextField textField_6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
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
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 866, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String fechaComoCadena = sdf.format(new Date());
		JLabel label = new JLabel("New label");
		label.setText("Factura a fecha "+fechaComoCadena);
		label.setBounds(33, 12, 313, 15);
		frame.getContentPane().add(label);
		
		
		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(33, 48, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(33, 73, 64, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(33, 98, 89, 14);
		frame.getContentPane().add(lblApellidos);
		
		JLabel lblNif = new JLabel("NIF");
		lblNif.setBounds(33, 123, 46, 14);
		frame.getContentPane().add(lblNif);
		
		textField = new JTextField();
		textField.setBounds(118, 70, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(416, 48, 327, 384);
		frame.getContentPane().add(textArea);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(118, 95, 86, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(118, 120, 86, 20);
		frame.getContentPane().add(textField_2);
		
		JLabel lblNewLabel_1 = new JLabel("Producto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(234, 48, 64, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(234, 70, 80, 14);
		frame.getContentPane().add(lblCodigo);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(318, 67, 86, 20);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(318, 92, 86, 20);
		frame.getContentPane().add(textField_4);
		
		JLabel lblApellidos_1 = new JLabel("Descripcion");
		lblApellidos_1.setBounds(234, 95, 94, 14);
		frame.getContentPane().add(lblApellidos_1);
		
		JLabel lblNif_1 = new JLabel("Cantidad");
		lblNif_1.setBounds(234, 120, 64, 14);
		frame.getContentPane().add(lblNif_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(318, 117, 86, 20);
		frame.getContentPane().add(textField_5);
		
		textofinal=("Cliente: "+textField.getText()+" "+textField_1.getText());
		JButton btnNewButton = new JButton("A\u00F1adir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalprod=Integer.parseInt(textField_5.getText())*Integer.parseInt(textField_6.getText());
				textofinal=("Cliente: "+textField.getText()+" "+textField_1.getText()+" "+textField_2.getText()+"\n******************************\n");
				productofinal=productofinal+"Codigo: "+textField_3.getText()+"\n    Descripcion: "+textField_4.getText()+"\n    Precio unitario: "+textField_5.getText()+"\n    Unidades: "+textField_6.getText()+" Total producto "+totalprod+"\n";
				textArea.setText(textofinal+productofinal+"\n");
				total=total+totalprod;
			}
		});
		btnNewButton.setBounds(33, 194, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Generar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textofinal+productofinal+"**********************\nTotal factura: "+total);
				
				String nombrecliente;
				nombrecliente=textField.getText()+" "+textField_1.getText()+" "+fechaComoCadena;
				textofinal=textArea.getText();
				try {
					FileWriter escritura = new FileWriter(nombrecliente+".txt");
					for(int i=0; i<textofinal.length();i++) {
						escritura.write(textofinal.charAt(i));
					}
				
					escritura.close();
				} catch (IOException a) {
					a.printStackTrace();
				}
				}
					
		});
		btnNewButton_1.setBounds(169, 194, 101, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNif_1_1 = new JLabel("Precio U.");
		lblNif_1_1.setBounds(234, 149, 64, 14);
		frame.getContentPane().add(lblNif_1_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(318, 146, 86, 20);
		frame.getContentPane().add(textField_6);
		
		


	}
}
