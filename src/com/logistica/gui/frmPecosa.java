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
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import com.logistica.componentes.JComboBoxBD;
import com.logistica.componentes.JTextFielBD;
import com.logistica.controlador.MySqlPecosaDAO;
import com.mxrck.autocompleter.TextAutoCompleter;

import lib.Mensajes;

import java.awt.Button;
import javax.swing.JComboBox;
import java.awt.event.*;

public class frmPecosa extends JFrame implements ActionListener, KeyListener{
	MySqlPecosaDAO pecosaDao = new MySqlPecosaDAO();
	
	private JPanel contentPane;
	private JTextField txtNumPecosa;
	private JTextField txtFecha;
	private JTextField txtSoliDni;
	private JTextField txtSoliNom;

	private JTextField txtSoliCargo;
	private JTextField txtEntrDni;
	private JTextField txtEntrApeNom;
	private JTextField txtUniOrg;
	private JTextField txtMeta;
	private JTextField txtReferencia;
	private JTextField txtEstado;
	private JTable table;
	private JTextField txtPrecioTotal;
	private JScrollPane tblDetallePecosa;
	private JComboBox cboSoliCargo;
	private JComboBox cboEntrCargo;
	private Button btnGuardar;
	private TextAutoCompleter ac;


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
		
		txtFecha = new JTextField(lib.Fecha.fechaActual());
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
		
		txtSoliDni = new JTextFielBD("dniTrabajador","TB_Trabajadores");
		txtSoliDni.addKeyListener(this);
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
		

		JLabel lblEntregarDni = new JLabel("Entregar A DNI");
		lblEntregarDni.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEntregarDni.setBounds(8, 53, 105, 14);
		panel_1.add(lblEntregarDni);
		
		txtEntrDni = new JTextFielBD("dniTrabajador","TB_Trabajadores");
		txtEntrDni.addKeyListener(this);
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
		
		cboSoliCargo = new JComboBoxBD("cargoTrabajador","TB_Trabajadores");
		cboSoliCargo.setBounds(506, 12, 110, 22);
		panel_1.add(cboSoliCargo);
		
		cboEntrCargo = new JComboBoxBD("cargoTrabajador","TB_Trabajadores");
		cboEntrCargo.setBounds(506, 50, 110, 22);
		panel_1.add(cboEntrCargo);
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
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(762, 8, 94, 27);
		contentPane.add(txtEstado);
		
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
		
		btnGuardar = new Button("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setActionCommand("");
		btnGuardar.setBounds(499, 540, 81, 38);
		contentPane.add(btnGuardar);
		
		Button btnSalir = new Button("Salir");
		btnSalir.setBounds(775, 540, 81, 38);
		contentPane.add(btnSalir);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtEntrDni) {
			keyReleasedTxtEntrDni(e);
		}
		if (e.getSource() == txtSoliDni) {
			keyReleasedTxtSoliDni(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtSoliDni) {
			keyTypedTxtSoliDni(e);
		}
		if(e.getSource() == txtEntrDni) {
			keyTypedTxtEntrDni(e);
		}
	}
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		String numPec,fecPec,soliDni,entrDni,estad,refe,meta,uniOrg;
		fecPec = txtFecha.getText();
		soliDni = txtSoliDni.getText();
		entrDni = txtEntrDni.getText();
		estad = txtEstado.getText();
		refe = txtReferencia.getText();
		uniOrg = txtUniOrg.getText();
		meta = txtMeta.getText();
		if(soliDni.equals("")) {
			Mensajes.dialogo("Es obligatorio el campo DNI SOLICITANTE");
		}
		/*
		Pecosa pec = new Pecosa();
		pec.setFecPec(fecPec);
		pec.setDniSoliPec(Integer.parseInt(soliDni));
		pec.setDniEntrPec(Integer.parseInt(entrDni));
		pec.setEstadoPec(estad);
		pec.setReferencia(refe);
		pec.setUniOrgPec(uniOrg);
		pec.setMeta(meta);
		int salida = pecosaDao.Ingresar(pec);
		if(salida > 0) {
			mensaje("El registro de la PECOSA fue un exito");
		}else {
			mensaje("Fallo en el Ingreso");
		}*/
	}

	
	
	public void keyPressed(KeyEvent e) {
	}
	
	public void keyTypedTxtSoliDni(KeyEvent e) {
		char c = e.getKeyChar();
		String dni = txtSoliDni.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(dni.length()>7) {
			e.consume();
		}	
	}
	public void keyTypedTxtEntrDni(KeyEvent e) {
		char c = e.getKeyChar();
		String dni = txtEntrDni.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(dni.length()>7) {
			e.consume();
		}	
	}
	protected void keyReleasedTxtSoliDni(KeyEvent e) {
		String dni = txtSoliDni.getText();
		String[] data = pecosaDao.buscarTrabajador(dni); 
		txtSoliNom.setText(data[0]);
		cboSoliCargo.setSelectedItem(data[1]);
	}
	protected void keyReleasedTxtEntrDni(KeyEvent e) {
		String dni = txtEntrDni.getText();
		String[] data = pecosaDao.buscarTrabajador(dni); 
		txtEntrApeNom.setText(data[0]);
		cboEntrCargo.setSelectedItem(data[1]);
	}
}
