package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.logistica.controlador.MySqlUsuarioDAO;
import com.logistica.entidad.Logistica;
import com.logistica.entidad.UnidadOrganica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;


import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.Format;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class frmLogin extends JFrame implements ActionListener {
	MySqlUsuarioDAO usuarioDAO= new MySqlUsuarioDAO();
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JLabel lblNewLabel;
	private JButton btnIniciar;
	private JButton btnCerrar;
	public UnidadOrganica uorg;
	public static String apenom,dni,unidad;
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
		setBounds(100, 100, 645, 375);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmLogin.class.getResource("/iconos/Logo3.png")));
		lblNewLabel.setBounds(438, 98, 94, 139);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblNewLabel_1.setBounds(0, 0, 629, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("USUARIO");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(148, 82, 94, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtUsuario.setColumns(10);
		txtUsuario.setBackground(new Color(255, 255, 224));
		txtUsuario.setBounds(148, 100, 223, 26);
		contentPane.add(txtUsuario);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("CONTRASE\u00D1A:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(148, 137, 94, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtClave = new JPasswordField();
		txtClave.setBackground(new Color(255, 255, 204));
		txtClave.setBounds(148, 162, 223, 26);
		contentPane.add(txtClave);
		
		btnIniciar = new JButton("Ingresar");
		btnIniciar.addActionListener(this);
		btnIniciar.setForeground(Color.BLACK);
		btnIniciar.setAutoscrolls(true);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciar.setBackground(new Color(0, 153, 102));
		btnIniciar.setBounds(148, 247, 101, 38);
		contentPane.add(btnIniciar);
		
		btnCerrar = new JButton("Cancelar");
		btnCerrar.addActionListener(this);
		btnCerrar.setForeground(Color.BLACK);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerrar.setBackground(new Color(153, 0, 51));
		btnCerrar.setAutoscrolls(true);
		btnCerrar.setBounds(270, 247, 101, 38);
		contentPane.add(btnCerrar);
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
				
				String[] log = login.split("/");
				
				if(log[0].equals("UO")) {
					UnidadOrganica uorg = new UnidadOrganica();
					uorg = usuarioDAO.iniciarSesionUnOrg(login, clave);
					if(uorg != null) {
						frmMenuUnidadOrganica frm = new frmMenuUnidadOrganica();
						apenom = uorg.getApenom();
						dni = String.valueOf(uorg.getDnilogin());
						unidad = uorg.getNomUnidOrg();
						String[] part = unidad.split(" - ");
						frm.lblBienvenido.setText("Bienvenido: " + apenom);
						frm.setTitle("Unidad Organica : " + part[1]);
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
				else if(log[0].equals("LO")) {
					
					Logistica lgt = usuarioDAO.iniciarSesionLog(login, clave);
					if(lgt != null) {
						
						if(lgt.getIdCargo() == 2) {
							frmMenuDirecEjecLogistica frm = new frmMenuDirecEjecLogistica();
							frm.lblNomDirec.setText(lgt.getNombre());
							frm.setVisible(true);
						} else if(lgt.getIdCargo() == 3) {
							frmMenuSubAlmacenero frm = new frmMenuSubAlmacenero();
							frm.lblBienvenido.setText("Bienvenido : " + lgt.getNombre());
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
