package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.logistica.controlador.MySqlUsuarioDAO;
import com.logistica.entidad.Administradores;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmLogin extends JFrame implements ActionListener {
	MySqlUsuarioDAO usuarioDAO= new MySqlUsuarioDAO();
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JLabel lblNewLabel;
	private JButton btnIniciar;
	private JButton btnCerrar;

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
		setTitle("CONTROL DE ACCESO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmLogin.class.getResource("/iconos/Logo3.png")));
		lblNewLabel.setBounds(128, 57, 94, 141);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("INTRANET");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 38));
		lblNewLabel_1.setBounds(0, 0, 325, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("USUARIO");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(92, 219, 94, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtUsuario.setColumns(10);
		txtUsuario.setBackground(new Color(255, 255, 224));
		txtUsuario.setBounds(92, 245, 169, 26);
		contentPane.add(txtUsuario);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("CLAVE");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(92, 282, 94, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtClave = new JPasswordField();
		txtClave.setBackground(new Color(255, 255, 204));
		txtClave.setBounds(92, 307, 169, 26);
		contentPane.add(txtClave);
		
		btnIniciar = new JButton("INICIAR");
		btnIniciar.addActionListener(this);
		btnIniciar.setForeground(Color.WHITE);
		btnIniciar.setAutoscrolls(true);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnIniciar.setBackground(new Color(0, 153, 102));
		btnIniciar.setBounds(92, 360, 169, 38);
		contentPane.add(btnIniciar);
		
		btnCerrar = new JButton("CANCELAR");
		btnCerrar.addActionListener(this);
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCerrar.setBackground(new Color(153, 0, 51));
		btnCerrar.setAutoscrolls(true);
		btnCerrar.setBounds(92, 409, 169, 38);
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
				Administradores adm=usuarioDAO.iniciarSesion(login, clave);				
				if(adm!=null) {
					frmPrincipal frm=new frmPrincipal();				
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
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
		
	}

	
}
