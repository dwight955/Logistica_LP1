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


import com.logistica.componentes.JTextFielBD;
import com.logistica.controlador.MySqlPecosaDAO;
import com.logistica.entidad.Pecosa;
import com.mxrck.autocompleter.TextAutoCompleter;

import lib.Mensajes;

import java.awt.Button;

import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class frmPecosa extends JFrame implements ActionListener, KeyListener{
	MySqlPecosaDAO pecosaDao = new MySqlPecosaDAO();
	
	private JPanel contentPane;
	private JTextField txtNumPecosa;
	private JTextField txtFecha;
	private JTextField txtSoliDni;
	private JTextField txtSoliApeNom;

	private JTextField txtEntrDni;
	private JTextField txtEntrApeNom;
	private JTextField txtUniOrg;
	private JTextField txtMeta;
	private JTextField txtReferencia;
	private JTextField txtEstado;
	private JTable table;
	private JTextField txtPrecioTotal;
	private JScrollPane tblDetallePecosa;
	private JButton btnGuardar;
	private TextAutoCompleter ac;
	private JTextField txtCargoEntr;
	private JTextField txtCargoSoli;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnSalir;
	private JButton btnBuscarSoliTrabajador;
	private JButton btnBuscarEntrTrabajador;
	private TextAutoCompleter cn;


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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 987, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
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
		txtNumPecosa.setEditable(false);
		txtNumPecosa.setBounds(102, 26, 97, 28);
		panel.add(txtNumPecosa);
		txtNumPecosa.setColumns(10);
		
		txtFecha = new JTextField(lib.Fecha.fechaActual());
		txtFecha.setEditable(false);
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
		panel_1.setBounds(230, 41, 731, 163);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Solicitante DNI");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 15, 105, 14);
		panel_1.add(lblNewLabel);
		
		txtSoliDni = new JTextFielBD("dniTrabajador","TB_Trabajadores");
		txtSoliDni.addKeyListener(this);
		txtSoliDni.setEditable(false);
		txtSoliDni.setBounds(106, 11, 94, 27);
		panel_1.add(txtSoliDni);
		txtSoliDni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ape. Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(241, 18, 94, 14);
		panel_1.add(lblNewLabel_1);
		
		txtSoliApeNom = new JTextField();
		txtSoliApeNom.setEditable(false);
		txtSoliApeNom.setColumns(10);
		txtSoliApeNom.setBounds(327, 11, 166, 27);
		panel_1.add(txtSoliApeNom);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCargo.setBounds(498, 18, 63, 14);
		panel_1.add(lblCargo);
		

		JLabel lblEntregarDni = new JLabel("Entregar A DNI");
		lblEntregarDni.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEntregarDni.setBounds(8, 53, 105, 14);
		panel_1.add(lblEntregarDni);
		
		txtEntrDni = new JTextFielBD("dniTrabajador","TB_Trabajadores");
		txtEntrDni.addKeyListener(this);
		txtEntrDni.setEditable(false);
		txtEntrDni.setColumns(10);
		txtEntrDni.setBounds(106, 48, 94, 27);
		panel_1.add(txtEntrDni);
		
		JLabel label_1 = new JLabel("Ape. Nombre");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(241, 55, 94, 14);
		panel_1.add(label_1);
		
		txtEntrApeNom = new JTextField();
		txtEntrApeNom.setEditable(false);
		txtEntrApeNom.setColumns(10);
		txtEntrApeNom.setBounds(327, 48, 166, 27);
		panel_1.add(txtEntrApeNom);
		
		JLabel label_2 = new JLabel("Cargo");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(498, 55, 63, 14);
		panel_1.add(label_2);
		
		JLabel lblUnid = new JLabel("Unidad Org.  :");
		lblUnid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnid.setBounds(10, 86, 105, 23);
		panel_1.add(lblUnid);
		
		txtUniOrg = new JTextFielBD("concat_ws(' - ',codUniOrg, nomUnidadOrg)","TB_UnidadOrganica");
		txtUniOrg.setEditable(false);
		txtUniOrg.setColumns(10);
		txtUniOrg.setBounds(106, 86, 615, 27);
		panel_1.add(txtUniOrg);
		
		JLabel lblMeta = new JLabel("Meta  :");
		lblMeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMeta.setBounds(10, 127, 105, 14);
		panel_1.add(lblMeta);
		
		txtMeta = new JTextField();
		txtMeta.addKeyListener(this);
		txtMeta.setEditable(false);
		txtMeta.setColumns(10);
		txtMeta.setBounds(72, 123, 649, 27);
	    cn = new TextAutoCompleter(txtMeta);
		panel_1.add(txtMeta);
		
		txtCargoSoli = new JTextField();
		txtCargoSoli.setEditable(false);
		txtCargoSoli.setColumns(10);
		txtCargoSoli.setBounds(546, 11, 175, 27);
		panel_1.add(txtCargoSoli);
		
		txtCargoEntr = new JTextField();
		txtCargoEntr.setEditable(false);
		txtCargoEntr.setColumns(10);
		txtCargoEntr.setBounds(546, 48, 175, 27);
		panel_1.add(txtCargoEntr);
		
		btnBuscarSoliTrabajador = new JButton("");
		btnBuscarSoliTrabajador.setIcon(new ImageIcon(frmPecosa.class.getResource("/iconos/search.png")));
		btnBuscarSoliTrabajador.setBounds(201, 11, 35, 27);
		panel_1.add(btnBuscarSoliTrabajador);
		
		btnBuscarEntrTrabajador = new JButton("");
		btnBuscarEntrTrabajador.setIcon(new ImageIcon(frmPecosa.class.getResource("/iconos/search.png")));
		btnBuscarEntrTrabajador.setBounds(201, 49, 35, 26);
		panel_1.add(btnBuscarEntrTrabajador);
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(211, 211, 211));
		panel_2.setBounds(230, 215, 731, 41);
		contentPane.add(panel_2);
		
		JLabel lblReferencia = new JLabel("Referencia  :");
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReferencia.setBounds(10, 11, 105, 23);
		panel_2.add(lblReferencia);
		
		txtReferencia = new JTextField();
		txtReferencia.setEditable(false);
		txtReferencia.setColumns(10);
		txtReferencia.setBounds(100, 7, 621, 27);
		panel_2.add(txtReferencia);
		
		JLabel lblEstado = new JLabel("Estado  :");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(724, 12, 105, 23);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(797, 11, 94, 27);
		contentPane.add(txtEstado);
		
		tblDetallePecosa = new JScrollPane();
		tblDetallePecosa.setBounds(11, 268, 950, 226);
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
		lblPrecioTotal.setBounds(749, 503, 105, 23);
		contentPane.add(lblPrecioTotal);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setColumns(10);
		txtPrecioTotal.setBounds(850, 501, 94, 27);
		contentPane.add(txtPrecioTotal);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(773, 540, 97, 38);
		contentPane.add(btnModificar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(682, 540, 81, 38);
		contentPane.add(btnCancelar);
		
		btnGuardar = new JButton("Nuevo");
		btnGuardar.addActionListener(this);
		btnGuardar.setActionCommand("");
		btnGuardar.setBounds(587, 540, 81, 38);
		contentPane.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(880, 539, 81, 38);
		contentPane.add(btnSalir);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtMeta) {
			keyReleasedTxtMeta(e);
		}
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
		if(txtSoliDni.isEditable()==false) {
			txtSoliDni.setEditable(true);
			txtEntrDni.setEditable(true);
			txtEstado.setEditable(true);
			txtUniOrg.setEditable(true);
			txtMeta.setEditable(true);
			txtReferencia.setEditable(true);
			btnGuardar.setText("Guardar");
		}
		/*String numPec,fecPec,soliDni,entrDni,estad,refe,meta,uniOrg;
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
		String solidni = txtSoliDni.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(solidni.length()==8) {
			e.consume();
			
		}	
	}
	public void keyTypedTxtEntrDni(KeyEvent e) {
		char c = e.getKeyChar();
		String dni = txtEntrDni.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(dni.length()==8) {
			e.consume();
		}	
	}
	protected void keyReleasedTxtSoliDni(KeyEvent e) {
		String solidni = txtSoliDni.getText();
		String[] data = pecosaDao.buscarTrabajador(solidni); 
		txtSoliApeNom.setText(data[0]);
		txtCargoSoli.setText(data[1]);
	}
	protected void keyReleasedTxtEntrDni(KeyEvent e) {
		String dni = txtEntrDni.getText();
		String[] data = pecosaDao.buscarTrabajador(dni);
		txtEntrApeNom.setText(data[0]);
		txtCargoEntr.setText(data[1]);
	}
	protected void keyReleasedTxtMeta(KeyEvent e) {
		String part[] = txtUniOrg.getText().split(" - ");
		String m = pecosaDao.buscarMeta(part[0]);
		cn.removeAllItems();
		cn.addItem(m);
	}
}
