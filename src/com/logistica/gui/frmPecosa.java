package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Button;

public class frmPecosa extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumPecosa;
	private JTextField txtFecha;
	private JTextField txtSoliDni;
	private JTextField txtSoliNom;
	private JTextField txtSoliCargo;
	private JTextField txtEntrDni;
	private JTextField txtEntrApeNom;
	private JTextField txtEntrCargo;
	private JTextField txtUniOrg;
	private JTextField txtMeta;
	private JTextField txtReferencia;
	private JTextField txtPecEstado;
	private JTable table;
	private JTextField txtPrecioTotal;
	private JScrollPane tblDetallePecosa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPecosa frame = new frmPecosa();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmPecosa() {
		setTitle("Pedido Comprobante de Pecosa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(211, 211, 211));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(11, 11, 209, 118);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNPecosa = new JLabel("N\u00BA Pecosa:");
		lblNPecosa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNPecosa.setBounds(10, 29, 97, 23);
		panel.add(lblNPecosa);
		
		txtNumPecosa = new JTextField();
		txtNumPecosa.setBounds(102, 26, 97, 28);
		panel.add(txtNumPecosa);
		txtNumPecosa.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(102, 75, 97, 28);
		panel.add(txtFecha);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(10, 76, 97, 23);
		panel.add(lblFecha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(135, 206, 235));
		panel_1.setBounds(230, 41, 626, 163);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Solicitante DNI");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 15, 105, 14);
		panel_1.add(lblNewLabel);
		
		txtSoliDni = new JTextField();
		txtSoliDni.setBounds(106, 11, 94, 27);
		panel_1.add(txtSoliDni);
		txtSoliDni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ape. Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(206, 15, 94, 14);
		panel_1.add(lblNewLabel_1);
		
		txtSoliNom = new JTextField();
		txtSoliNom.setColumns(10);
		txtSoliNom.setBounds(292, 11, 166, 27);
		panel_1.add(txtSoliNom);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCargo.setBounds(463, 15, 63, 14);
		panel_1.add(lblCargo);
		
		txtSoliCargo = new JTextField();
		txtSoliCargo.setColumns(10);
		txtSoliCargo.setBounds(511, 11, 105, 27);
		panel_1.add(txtSoliCargo);
		
		JLabel lblEntregarDni = new JLabel("Entregar A DNI");
		lblEntregarDni.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEntregarDni.setBounds(8, 53, 105, 14);
		panel_1.add(lblEntregarDni);
		
		txtEntrDni = new JTextField();
		txtEntrDni.setColumns(10);
		txtEntrDni.setBounds(106, 48, 94, 27);
		panel_1.add(txtEntrDni);
		
		JLabel label_1 = new JLabel("Ape. Nombre");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(206, 52, 94, 14);
		panel_1.add(label_1);
		
		txtEntrApeNom = new JTextField();
		txtEntrApeNom.setColumns(10);
		txtEntrApeNom.setBounds(292, 48, 166, 27);
		panel_1.add(txtEntrApeNom);
		
		JLabel label_2 = new JLabel("Cargo");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(463, 52, 63, 14);
		panel_1.add(label_2);
		
		txtEntrCargo = new JTextField();
		txtEntrCargo.setColumns(10);
		txtEntrCargo.setBounds(511, 48, 105, 27);
		panel_1.add(txtEntrCargo);
		
		JLabel lblUnid = new JLabel("Unidad Org.  :");
		lblUnid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnid.setBounds(10, 86, 105, 23);
		panel_1.add(lblUnid);
		
		txtUniOrg = new JTextField();
		txtUniOrg.setColumns(10);
		txtUniOrg.setBounds(106, 86, 510, 27);
		panel_1.add(txtUniOrg);
		
		JLabel lblMeta = new JLabel("Meta  :");
		lblMeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMeta.setBounds(10, 127, 105, 14);
		panel_1.add(lblMeta);
		
		txtMeta = new JTextField();
		txtMeta.setColumns(10);
		txtMeta.setBounds(72, 123, 544, 27);
		panel_1.add(txtMeta);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(211, 211, 211));
		panel_2.setBounds(230, 215, 626, 41);
		contentPane.add(panel_2);
		
		JLabel lblReferencia = new JLabel("Referencia  :");
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReferencia.setBounds(10, 11, 105, 23);
		panel_2.add(lblReferencia);
		
		txtReferencia = new JTextField();
		txtReferencia.setColumns(10);
		txtReferencia.setBounds(100, 7, 516, 27);
		panel_2.add(txtReferencia);
		
		JLabel lblEstado = new JLabel("Estado  :");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(689, 9, 105, 23);
		contentPane.add(lblEstado);
		
		txtPecEstado = new JTextField();
		txtPecEstado.setColumns(10);
		txtPecEstado.setBounds(762, 8, 94, 27);
		contentPane.add(txtPecEstado);
		
		tblDetallePecosa = new JScrollPane();
		tblDetallePecosa.setBounds(11, 268, 845, 226);
		contentPane.add(tblDetallePecosa);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Uni. Med", "Bienes", "P. U. Salida", "Cant. Salida", "Precio Total Salida", "Doc. Ref", "Num. Refe"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(104);
		tblDetallePecosa.setViewportView(table);
		
		JLabel lblPrecioTotal = new JLabel("Precio Total  :");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioTotal.setBounds(661, 503, 105, 23);
		contentPane.add(lblPrecioTotal);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setColumns(10);
		txtPrecioTotal.setBounds(762, 501, 94, 27);
		contentPane.add(txtPrecioTotal);
		
		Button btnModificar = new Button("Modificar");
		btnModificar.setBounds(685, 540, 81, 38);
		contentPane.add(btnModificar);
		
		Button btnCancelar = new Button("Cancelar");
		btnCancelar.setBounds(594, 540, 81, 38);
		contentPane.add(btnCancelar);
		
		Button btnGuardar = new Button("Guardar");
		btnGuardar.setActionCommand("");
		btnGuardar.setBounds(499, 540, 81, 38);
		contentPane.add(btnGuardar);
		
		Button btnSalir = new Button("Salir");
		btnSalir.setBounds(775, 540, 81, 38);
		contentPane.add(btnSalir);
	}
}
