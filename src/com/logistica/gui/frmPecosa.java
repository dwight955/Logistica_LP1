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
import java.awt.Image;
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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class frmPecosa extends JFrame implements ActionListener, KeyListener{
	MySqlPecosaDAO pecosaDao = new MySqlPecosaDAO();
	
	private JPanel contentPane;
	private JTextField txtNumPecosa;
	private JTextField txtFecha;

	public static JTextField txtEntrDni;
	public static JTextField txtEntrApeNom;
	private JTextField txtMeta;
	private JTextField txtReferencia;
	private JTextField txtEstado;
	private JTable table;
	private JTextField txtPrecioTotal;
	private JScrollPane tblDetallePecosa;
	private JButton btnGuardar;
	private TextAutoCompleter ac;
	public static JTextField txtCargoEntr;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnSalir;
	private TextAutoCompleter auto;
	private JLabel lblPecosa;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblUnidadBeneficiaria;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblAprobadoPor;
	private JPanel panel_4;
	private JTextField textField_4;
	private JLabel lblFechaApro;
	private JPanel panel_5;
	private JTextField textField_5;
	private JLabel lblFechaEntr;
	private JLabel lblNewLabel;
	private JLabel lblFormuladoPor;
	private JTextField textField_6;


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
		setTitle("Generar Pedido Comprobante de Salida");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1069, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(211, 211, 211));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(10, 25, 246, 131);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNPecosa = new JLabel("N\u00BA Pecosa:");
		lblNPecosa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNPecosa.setBounds(10, 14, 97, 23);
		panel.add(lblNPecosa);
		
		txtNumPecosa = new JTextField();
		txtNumPecosa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNumPecosa.setEditable(false);
		txtNumPecosa.setBounds(110, 12, 126, 28);
		panel.add(txtNumPecosa);
		txtNumPecosa.setColumns(10);
		
		txtFecha = new JTextField(lib.Fecha.fechaActual());
		txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(149, 86, 87, 28);
		panel.add(txtFecha);
		
		JLabel lblFecha = new JLabel("Fecha Formulacion:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(10, 88, 145, 23);
		panel.add(lblFecha);
		
		JLabel lblNRequerimiento = new JLabel("N\u00BA Requerimiento:");
		lblNRequerimiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNRequerimiento.setBounds(10, 53, 126, 23);
		panel.add(lblNRequerimiento);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(139, 50, 97, 28);
		panel.add(textField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(135, 206, 235));
		panel_1.setBounds(266, 62, 777, 131);
		contentPane.add(panel_1);
		

		JLabel lblEntregarDni = new JLabel("Entregar A");
		lblEntregarDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEntregarDni.setBounds(10, 54, 105, 14);
		panel_1.add(lblEntregarDni);
		
		txtEntrDni = new JTextField();
		txtEntrDni.addKeyListener(this);
		txtEntrDni.setEditable(false);
		txtEntrDni.setColumns(10);
		txtEntrDni.setBounds(380, 49, 86, 27);
		panel_1.add(txtEntrDni);
		
		JLabel lblConNumeroDni = new JLabel("con numero DNI");
		lblConNumeroDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConNumeroDni.setBounds(263, 54, 126, 14);
		panel_1.add(lblConNumeroDni);
		
		txtEntrApeNom = new JTextField();
		txtEntrApeNom.setEditable(false);
		txtEntrApeNom.setColumns(10);
		txtEntrApeNom.setBounds(89, 49, 168, 27);
		panel_1.add(txtEntrApeNom);
		
		JLabel lblYConCargo = new JLabel("y con cargo de");
		lblYConCargo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYConCargo.setBounds(471, 50, 112, 22);
		panel_1.add(lblYConCargo);
		
		JLabel lblMeta = new JLabel("Meta  :");
		lblMeta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMeta.setBounds(327, 91, 72, 14);
		panel_1.add(lblMeta);
		
		txtMeta = new JTextField();
		txtMeta.setEditable(false);
		txtMeta.setColumns(10);
		txtMeta.setBounds(384, 87, 383, 27);
		panel_1.add(txtMeta);
		
		txtCargoEntr = new JTextField();
		txtCargoEntr.setEditable(false);
		txtCargoEntr.setColumns(10);
		txtCargoEntr.setBounds(579, 49, 188, 27);
		panel_1.add(txtCargoEntr);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(221, 10, 263, 27);
		panel_1.add(textField_1);
		
		JLabel lblUnidadOrganicaSolicitante = new JLabel("Unidad Organica Solicitante :");
		lblUnidadOrganicaSolicitante.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnidadOrganicaSolicitante.setBounds(12, 11, 233, 23);
		panel_1.add(lblUnidadOrganicaSolicitante);
		
		lblUnidadBeneficiaria = new JLabel("Unidad beneficiaria: ");
		lblUnidadBeneficiaria.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnidadBeneficiaria.setBounds(10, 93, 150, 14);
		panel_1.add(lblUnidadBeneficiaria);
		
		textField_3 = new JTextField();
		textField_3.setBounds(148, 87, 169, 27);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		lblFormuladoPor = new JLabel("Formu. por :");
		lblFormuladoPor.setBounds(494, 11, 105, 23);
		panel_1.add(lblFormuladoPor);
		lblFormuladoPor.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textField_6 = new JTextField();
		textField_6.setBackground(SystemColor.info);
		textField_6.setForeground(SystemColor.info);
		textField_6.setBounds(579, 12, 188, 27);
		panel_1.add(textField_6);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(211, 211, 211));
		panel_2.setBounds(266, 255, 777, 41);
		contentPane.add(panel_2);
		
		JLabel lblReferencia = new JLabel("Referencia  :");
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReferencia.setBounds(10, 9, 105, 23);
		panel_2.add(lblReferencia);
		
		txtReferencia = new JTextField();
		txtReferencia.setEnabled(false);
		txtReferencia.setEditable(false);
		txtReferencia.setColumns(10);
		txtReferencia.setBounds(106, 7, 661, 27);
		panel_2.add(txtReferencia);
		
		JLabel lblEstado = new JLabel("Estado  :");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(876, 25, 105, 23);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(949, 24, 94, 27);
		contentPane.add(txtEstado);
		
		tblDetallePecosa = new JScrollPane();
		tblDetallePecosa.setBounds(10, 310, 1033, 226);
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
		
		JLabel lblPrecioTotal = new JLabel("Precio Total  S/. :");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioTotal.setBounds(824, 549, 126, 23);
		contentPane.add(lblPrecioTotal);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setColumns(10);
		txtPrecioTotal.setBounds(949, 547, 94, 27);
		contentPane.add(txtPrecioTotal);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.setBounds(803, 590, 97, 38);
		contentPane.add(btnModificar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(699, 590, 94, 38);
		contentPane.add(btnCancelar);
		
		btnGuardar = new JButton("Nuevo");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.addActionListener(this);
		btnGuardar.setActionCommand("");
		btnGuardar.setBounds(608, 590, 81, 38);
		contentPane.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(910, 589, 81, 38);
		contentPane.add(btnSalir);
		
		lblPecosa = new JLabel("PECOSA");
		lblPecosa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPecosa.setFont(new Font("Square721 BT", Font.BOLD, 30));
		lblPecosa.setBounds(407, 4, 386, 53);
		contentPane.add(lblPecosa);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(UIManager.getColor("Button.shadow"));
		panel_3.setBounds(266, 204, 355, 41);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(116, 6, 229, 27);
		panel_3.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		lblAprobadoPor = new JLabel("Aprobado por:");
		lblAprobadoPor.setForeground(UIManager.getColor("CheckBox.foreground"));
		lblAprobadoPor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAprobadoPor.setBounds(10, 12, 130, 14);
		panel_3.add(lblAprobadoPor);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(SystemColor.controlShadow);
		panel_4.setBounds(631, 203, 201, 41);
		contentPane.add(panel_4);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(98, 6, 86, 27);
		panel_4.add(textField_4);
		
		lblFechaApro = new JLabel("Fecha Apro.");
		lblFechaApro.setForeground(Color.BLACK);
		lblFechaApro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaApro.setBounds(10, 12, 96, 14);
		panel_4.add(lblFechaApro);
		
		panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(SystemColor.controlShadow);
		panel_5.setBounds(842, 203, 201, 41);
		contentPane.add(panel_5);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(98, 6, 93, 27);
		panel_5.add(textField_5);
		
		lblFechaEntr = new JLabel("Fecha Entr.");
		lblFechaEntr.setForeground(Color.BLACK);
		lblFechaEntr.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaEntr.setBounds(10, 12, 96, 14);
		panel_5.add(lblFechaEntr);
		
		ImageIcon icon = new ImageIcon(new ImageIcon(frmPecosa.class.getResource("/iconos/Sub Almacenero.png")).getImage().getScaledInstance(135, 125, Image.SCALE_SMOOTH));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(10, 167, 246, 129);
		contentPane.add(lblNewLabel);
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
	}
	public void keyTyped(KeyEvent e) {
		if(e.getSource() == txtEntrDni) {
			keyTypedTxtEntrDni(e);
		}
	}
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		
		
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
	public void keyTypedTxtEntrDni(KeyEvent e) {
		char c = e.getKeyChar();
		String dni = txtEntrDni.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(dni.length()==8) {
			e.consume();
		}	
	}
	protected void keyReleasedTxtEntrDni(KeyEvent e) {
		String dni = txtEntrDni.getText();
		String[] data = pecosaDao.buscarTrabajador(dni);
		txtEntrApeNom.setText(data[0]);
		txtCargoEntr.setText(data[1]);
	}
}
