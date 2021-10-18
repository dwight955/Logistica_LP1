package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class frmTrabajadores extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTrabajadores frame = new frmTrabajadores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmTrabajadores() {
		setTitle("Trabajadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 499, 191);
		contentPane.add(scrollPane);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(10, 249, 86, 14);
		contentPane.add(lblDni);
		
		JLabel lblApellidosYNombres = new JLabel("Apellidos y Nombres");
		lblApellidosYNombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidosYNombres.setBounds(10, 299, 141, 14);
		contentPane.add(lblApellidosYNombres);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(215, 255, 86, 14);
		contentPane.add(lblCargo);
		
		textField = new JTextField();
		textField.setBounds(10, 268, 86, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 323, 183, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(215, 280, 141, 25);
		contentPane.add(comboBox);
		
		JLabel lblFecha = new JLabel("Sueldo");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(10, 354, 86, 14);
		contentPane.add(lblFecha);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 379, 86, 25);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaDeNacimiento.setBounds(215, 318, 159, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		textField_3 = new JTextField();
		textField_3.setBounds(215, 343, 141, 25);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Nuevo");
		btnNewButton.setBounds(390, 249, 106, 35);
		contentPane.add(btnNewButton);
		
		JButton btnModificar = new JButton("Actualizar");
		btnModificar.setBounds(390, 291, 106, 35);
		contentPane.add(btnModificar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmTrabajadores.class.getResource("/iconos/search.png")));
		lblNewLabel.setBounds(23, 11, 41, 26);
		contentPane.add(lblNewLabel);
		
		textField_4 = new JTextField();
		textField_4.setBounds(61, 11, 120, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	}
}
