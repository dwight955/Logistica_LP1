package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.logistica.controlador.MySqlUsuarioDAO;
import com.logistica.entidad.Logistica;
import com.logistica.entidad.UnidadOrganica;
import com.logistica.utils.Libreria;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;


import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.Format;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class  frmLogin extends JFrame implements ActionListener {
	MySqlUsuarioDAO usuarioDAO= new MySqlUsuarioDAO();
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JLabel lblNewLabel;
	private JButton btnIniciar;
	private JButton btnCerrar;
	public UnidadOrganica uorg;
	public static String apenom,dni,unidad;
	private JComboBox cboTipoUsuario;
	private JLabel lblFondo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
					frame.setLocationRelativeTo(null);					 
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
	public frmLogin() {
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setTitle("CONTROL DE ACCESO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 538);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmLogin.class.getResource("/iconos/Logo3.png")));
		lblNewLabel.setBounds(172, 93, 94, 139);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("USUARIO");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(112, 243, 94, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtUsuario.setColumns(10);
		txtUsuario.setBackground(new Color(255, 255, 224));
		txtUsuario.setBounds(112, 261, 223, 26);
		contentPane.add(txtUsuario);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("CONTRASE\u00D1A:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(112, 298, 94, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtClave = new JPasswordField();
		txtClave.setBackground(new Color(255, 255, 204));
		txtClave.setBounds(112, 323, 223, 26);
		contentPane.add(txtClave);
		
		btnIniciar = new JButton("Ingresar");
		btnIniciar.addActionListener(this);
		btnIniciar.setForeground(Color.BLACK);
		btnIniciar.setAutoscrolls(true);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciar.setBackground(new Color(0, 153, 102));
		btnIniciar.setBounds(112, 397, 101, 38);
		contentPane.add(btnIniciar);
		
		btnCerrar = new JButton("Cancelar");
		btnCerrar.addActionListener(this);
		btnCerrar.setForeground(Color.BLACK);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerrar.setBackground(new Color(153, 0, 51));
		btnCerrar.setAutoscrolls(true);
		btnCerrar.setBounds(234, 397, 101, 38);
		contentPane.add(btnCerrar);
		
		cboTipoUsuario = new JComboBox();
		cboTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"LOGISTICA", "UNIDAD ORGANICA"}));
		cboTipoUsuario.setBounds(112, 360, 223, 26);
		contentPane.add(cboTipoUsuario);
		
		ImageIcon icon = new ImageIcon(new ImageIcon(frmPecosa.class.getResource("/img/fondoLogin.png")).getImage().getScaledInstance(468, 500, Image.SCALE_SMOOTH));
		
		lblFondo = new JLabel("");
		lblFondo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFondo.setIcon(icon);
		lblFondo.setBounds(0, 0, 439, 501);
		contentPane.add(lblFondo);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
		if (e.getSource() == btnIniciar) {
			actionPerformedBtnIniciar(e);
		}
	}
	protected void actionPerformedBtnIniciar(ActionEvent e) {
				//variables
				String login,clave;				
				
				login=txtUsuario.getText();
				clave=new String(txtClave.getPassword());
				
				//String[] log = login.split("/");
				String tipoUsu = cboTipoUsuario.getSelectedItem().toString();
				
				if(tipoUsu.equals("UNIDAD ORGANICA")) {
					UnidadOrganica uorg = new UnidadOrganica();
					uorg = usuarioDAO.iniciarSesionUnOrg(login, clave);
					if(uorg != null) {
						Libreria.codigoTrabajadorSesion = uorg.getDnilogin();
						frmMenuUnidadOrganica frm = new frmMenuUnidadOrganica();
						apenom = uorg.getApenom();
						dni = String.valueOf(uorg.getDnilogin());
						unidad = uorg.getNomUnidOrg();
						frm.lblBienvenido.setText("Bienvenido: " + apenom);
						frm.setTitle("Unidad Organica : " + unidad);
						frm.lblNomCargo.setText("Cargo : " + uorg.getCargo());
						
						frm.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Usuario y/o clave incorrectos..");
						txtUsuario.setText("");
						txtClave.setText("");
						txtUsuario.requestFocus();
					}
				}
				else if(tipoUsu.equals("LOGISTICA")) {
					
					Logistica lgt = usuarioDAO.iniciarSesionLog(login, clave);
					if(lgt != null) {
						Libreria.codigoTrabajadorSesion = lgt.getDni();
						if(lgt.getIdCargo() == 2) {
							frmMenuDirecEjecLogistica frm = new frmMenuDirecEjecLogistica();
							frm.lblNomDirec.setText(lgt.getNombre());
							frm.setVisible(true);
						} else if(lgt.getIdCargo() == 3) {
							frmMenuSubAlmacenero frm = new frmMenuSubAlmacenero();
							frm.lblnombreSesion.setText(lgt.getNombre());
							frm.lblCargo.setText("Cargo : " + lgt.getCargo());
							frm.setVisible(true);
						} else if(lgt.getIdCargo() == 4) {
							frmJefeDeUnidFuncAlmacen frm = new frmJefeDeUnidFuncAlmacen();
							frm.lblNomJufa.setText(lgt.getNombre());
							frm.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "Error en la conexion... Intentelo otra vez");
						}
						
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Usuario y/o clave incorrectos..");
						txtUsuario.setText("");
						txtClave.setText("");
						txtUsuario.requestFocus();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuario y/o clave incorrectos..");
					txtUsuario.setText("");
					txtClave.setText("");
					txtUsuario.requestFocus();
				}
	}
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
		
	}
}
