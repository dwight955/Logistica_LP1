package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;

public class frmCuadroRequerimientos extends JFrame {

	private JPanel contentPane;
	private JTextField txtDeUnidadOrg;
	private JTextField txtParaUnidadOrg;
	private JTextField FechaEmi;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblCodigo;
	private JTextField txtCodBien;
	private JLabel lblDescripcion;
	private JTextField txtDescPro;
	private JTextField txtCant;
	private JTextField txtUnidaMed;
	private JLabel lblCuadroDeRequerimientos;
	private JTable tblRequerimientos;
	private JTextField txtNombreApe;
	private JTextField txtdni;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCuadroRequerimientos frame = new frmCuadroRequerimientos();
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
	public frmCuadroRequerimientos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cabecera", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 77, 664, 118);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPara = new JLabel("Para: ");
		lblPara.setBounds(22, 81, 55, 17);
		panel.add(lblPara);
		lblPara.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtParaUnidadOrg = new JTextField();
		txtParaUnidadOrg.setBounds(71, 81, 224, 20);
		panel.add(txtParaUnidadOrg);
		txtParaUnidadOrg.setColumns(10);
		
		JLabel lblDe = new JLabel("De: ");
		lblDe.setBounds(22, 53, 55, 17);
		panel.add(lblDe);
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtDeUnidadOrg = new JTextField();
		txtDeUnidadOrg.setBounds(71, 53, 224, 20);
		panel.add(txtDeUnidadOrg);
		txtDeUnidadOrg.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha Emision: ");
		lblFecha.setBounds(465, 23, 108, 14);
		panel.add(lblFecha);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		FechaEmi = new JTextField();
		FechaEmi.setBounds(564, 23, 71, 19);
		panel.add(FechaEmi);
		FechaEmi.setColumns(10);
		
		JLabel lblDni = new JLabel("Solicitante: ");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(22, 24, 86, 17);
		panel.add(lblDni);
		
		txtNombreApe = new JTextField();
		txtNombreApe.setColumns(10);
		txtNombreApe.setBounds(175, 23, 120, 20);
		panel.add(txtNombreApe);
		
		txtdni = new JTextField();
		txtdni.setBackground(SystemColor.info);
		txtdni.setColumns(10);
		txtdni.setBounds(92, 23, 73, 20);
		panel.add(txtdni);
		
		JLabel lblEntregarA = new JLabel("Entregar A: ");
		lblEntregarA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEntregarA.setBounds(347, 53, 86, 17);
		panel.add(lblEntregarA);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(SystemColor.info);
		textField_1.setBounds(432, 53, 73, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(515, 53, 120, 20);
		panel.add(textField_2);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Datos del Bien o Servicio", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 206, 664, 124);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(10, 41, 55, 17);
		panel_1.add(lblCodigo);
		
		txtCodBien = new JTextField();
		txtCodBien.setColumns(10);
		txtCodBien.setBounds(70, 41, 152, 20);
		panel_1.add(txtCodBien);
		
		lblDescripcion = new JLabel("Descripcion: ");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(10, 88, 97, 17);
		panel_1.add(lblDescripcion);
		
		txtDescPro = new JTextField();
		txtDescPro.setColumns(10);
		txtDescPro.setBounds(93, 88, 181, 20);
		panel_1.add(txtDescPro);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidad.setBounds(284, 88, 77, 17);
		panel_1.add(lblCantidad);
		
		txtCant = new JTextField();
		txtCant.setColumns(10);
		txtCant.setBounds(344, 88, 55, 20);
		panel_1.add(txtCant);
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de Medida:");
		lblUnidadDeMedida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnidadDeMedida.setBounds(436, 88, 129, 17);
		panel_1.add(lblUnidadDeMedida);
		
		txtUnidaMed = new JTextField();
		txtUnidaMed.setColumns(10);
		txtUnidaMed.setBounds(553, 88, 101, 20);
		panel_1.add(txtUnidaMed);
		
		JButton button = new JButton("");
		button.setBounds(232, 36, 88, 41);
		panel_1.add(button);
		button.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/search.png")));
		
		JButton button_1 = new JButton("");
		button_1.setBounds(334, 36, 82, 41);
		panel_1.add(button_1);
		button_1.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/add.png")));
		
		JButton button_2 = new JButton("");
		button_2.setBounds(426, 36, 77, 41);
		panel_1.add(button_2);
		button_2.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/cerrar.png")));
		
		lblCuadroDeRequerimientos = new JLabel("Cuadro de Requerimientos");
		lblCuadroDeRequerimientos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCuadroDeRequerimientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuadroDeRequerimientos.setBounds(220, 11, 227, 27);
		contentPane.add(lblCuadroDeRequerimientos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 341, 664, 258);
		contentPane.add(scrollPane);
		
		tblRequerimientos = new JTable();
		tblRequerimientos.setFillsViewportHeight(true);
		tblRequerimientos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item", "Descripcion", "Unidad de Medida", "Cantidad"
			}
		));
		tblRequerimientos.getColumnModel().getColumn(0).setPreferredWidth(15);
		tblRequerimientos.getColumnModel().getColumn(0).setMinWidth(7);
		scrollPane.setViewportView(tblRequerimientos);
		
		JLabel lblNumRequerimiento = new JLabel("Num. Requerimiento:");
		lblNumRequerimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumRequerimiento.setBounds(10, 39, 138, 27);
		contentPane.add(lblNumRequerimiento);
		
		textField = new JTextField();
		textField.setBounds(147, 44, 73, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(581, 610, 93, 32);
		contentPane.add(btnGuardar);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(478, 610, 93, 32);
		contentPane.add(btnEnviar);
	}
}
