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

import com.logistica.entidad.UnidadOrganica;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmCuadroRequerimientos extends JFrame implements ActionListener {
	
	
	private JPanel contentPane;
	public JTextField txtDeUnidadOrg;
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
	public JTextField txtNombreApeSoli;
	public JTextField txtdniSoli;
	private JTextField textField;
	private JTextField txtdniEntr;
	private JTextField txtNombreEntr;
	private JTextField textField_3;
	private JButton btnNuevo;

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
		setTitle("Formulacion del Cuadro de Requerimientos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cabecera", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 95, 735, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPara = new JLabel("Para: ");
		lblPara.setBounds(390, 50, 55, 17);
		panel.add(lblPara);
		lblPara.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtParaUnidadOrg = new JTextField();
		txtParaUnidadOrg.setBounds(439, 50, 286, 20);
		panel.add(txtParaUnidadOrg);
		txtParaUnidadOrg.setColumns(10);
		
		JLabel lblDe = new JLabel("De: ");
		lblDe.setBounds(22, 53, 55, 17);
		panel.add(lblDe);
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtDeUnidadOrg = new JTextField();
		txtDeUnidadOrg.setBounds(71, 53, 298, 20);
		panel.add(txtDeUnidadOrg);
		txtDeUnidadOrg.setColumns(10);
		
		JLabel lblDni = new JLabel("Solicitante: ");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(22, 24, 86, 17);
		panel.add(lblDni);
		
		txtNombreApeSoli = new JTextField();
		txtNombreApeSoli.setColumns(10);
		txtNombreApeSoli.setBounds(175, 23, 194, 20);
		panel.add(txtNombreApeSoli);
		
		txtdniSoli = new JTextField();
		txtdniSoli.setBackground(SystemColor.info);
		txtdniSoli.setColumns(10);
		txtdniSoli.setBounds(92, 23, 73, 20);
		panel.add(txtdniSoli);
		
		JLabel lblEntregarA = new JLabel("Entregar A: ");
		lblEntregarA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEntregarA.setBounds(390, 24, 86, 17);
		panel.add(lblEntregarA);
		
		txtdniEntr = new JTextField();
		txtdniEntr.setColumns(10);
		txtdniEntr.setBackground(SystemColor.info);
		txtdniEntr.setBounds(475, 24, 73, 20);
		panel.add(txtdniEntr);
		
		txtNombreEntr = new JTextField();
		txtNombreEntr.setColumns(10);
		txtNombreEntr.setBounds(558, 24, 167, 20);
		panel.add(txtNombreEntr);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Datos del Bien o Servicio", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 206, 735, 124);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(10, 41, 55, 17);
		panel_1.add(lblCodigo);
		
		txtCodBien = new JTextField();
		txtCodBien.setColumns(10);
		txtCodBien.setBounds(70, 41, 201, 20);
		panel_1.add(txtCodBien);
		
		lblDescripcion = new JLabel("Descripcion: ");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(10, 88, 97, 17);
		panel_1.add(lblDescripcion);
		
		txtDescPro = new JTextField();
		txtDescPro.setColumns(10);
		txtDescPro.setBounds(93, 88, 215, 20);
		panel_1.add(txtDescPro);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidad.setBounds(365, 85, 77, 17);
		panel_1.add(lblCantidad);
		
		txtCant = new JTextField();
		txtCant.setColumns(10);
		txtCant.setBounds(425, 85, 55, 20);
		panel_1.add(txtCant);
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de Medida:");
		lblUnidadDeMedida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnidadDeMedida.setBounds(507, 85, 129, 17);
		panel_1.add(lblUnidadDeMedida);
		
		txtUnidaMed = new JTextField();
		txtUnidaMed.setColumns(10);
		txtUnidaMed.setBounds(624, 85, 101, 20);
		panel_1.add(txtUnidaMed);
		
		JButton button = new JButton("");
		button.setBounds(313, 33, 88, 41);
		panel_1.add(button);
		button.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/search.png")));
		
		JButton button_1 = new JButton("");
		button_1.setBounds(415, 33, 82, 41);
		panel_1.add(button_1);
		button_1.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/add.png")));
		
		JButton button_2 = new JButton("");
		button_2.setBounds(507, 33, 77, 41);
		panel_1.add(button_2);
		button_2.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/cerrar.png")));
		
		lblCuadroDeRequerimientos = new JLabel("Cuadro de Requerimientos");
		lblCuadroDeRequerimientos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCuadroDeRequerimientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuadroDeRequerimientos.setBounds(253, 13, 248, 38);
		contentPane.add(lblCuadroDeRequerimientos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 341, 735, 258);
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
		
		JLabel lblNumRequerimiento = new JLabel("N\u00BA Requerimiento:");
		lblNumRequerimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumRequerimiento.setBounds(13, 57, 124, 27);
		contentPane.add(lblNumRequerimiento);
		
		textField = new JTextField();
		textField.setBounds(131, 62, 79, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(581, 610, 93, 32);
		contentPane.add(btnGuardar);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(478, 610, 93, 32);
		contentPane.add(btnEnviar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(379, 610, 89, 32);
		contentPane.add(btnNuevo);
		
		JLabel lblFecha = new JLabel("Fecha Emision: ");
		lblFecha.setBounds(220, 62, 108, 22);
		contentPane.add(lblFecha);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		FechaEmi = new JTextField();
		FechaEmi.setBounds(319, 62, 81, 22);
		contentPane.add(FechaEmi);
		FechaEmi.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstado.setBounds(410, 62, 59, 22);
		contentPane.add(lblEstado);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(463, 62, 108, 22);
		contentPane.add(textField_3);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		frmLogin frm = new frmLogin();
		txtNombreApeSoli.setText(frm.apenom); 
		txtdniSoli.setText(frm.dni);
		txtDeUnidadOrg.setText(frm.unidad);
	}
}
