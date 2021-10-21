package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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

	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JLabel lblNewLabel;
	private JProgressBar pbBarra;
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
		setBounds(100, 100, 378, 708);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmLogin.class.getResource("/iconos/login.png")));
		lblNewLabel.setBounds(38, 57, 256, 346);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("INTRANET");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 38));
		lblNewLabel_1.setBounds(0, 0, 368, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("USUARIO");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 414, 94, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtUsuario.setColumns(10);
		txtUsuario.setBackground(new Color(255, 255, 224));
		txtUsuario.setBounds(10, 433, 342, 26);
		contentPane.add(txtUsuario);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("CLAVE");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(10, 470, 94, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtClave = new JPasswordField();
		txtClave.setBackground(new Color(255, 255, 204));
		txtClave.setBounds(10, 492, 342, 26);
		contentPane.add(txtClave);
		
		pbBarra = new JProgressBar();
		pbBarra.setForeground(new Color(0, 204, 51));
		pbBarra.setBounds(10, 529, 342, 14);
		contentPane.add(pbBarra);
		
		btnIniciar = new JButton("INICIAR");
		btnIniciar.addActionListener(this);
		btnIniciar.setForeground(Color.WHITE);
		btnIniciar.setAutoscrolls(true);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnIniciar.setBackground(new Color(0, 153, 102));
		btnIniciar.setBounds(10, 561, 342, 38);
		contentPane.add(btnIniciar);
		
		btnCerrar = new JButton("CERRAR");
		btnCerrar.addActionListener(this);
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCerrar.setBackground(new Color(153, 0, 51));
		btnCerrar.setAutoscrolls(true);
		btnCerrar.setBounds(10, 610, 342, 38);
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
		
	}
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		
		
	}

	
}
