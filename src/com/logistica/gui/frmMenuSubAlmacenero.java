package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmMenuSubAlmacenero extends JFrame implements ActionListener {

	private JPanel contentPane;
	public JLabel lblBienvenido;
	public JLabel lblCargo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMenuSubAlmacenero frame = new frmMenuSubAlmacenero();
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
	FondoPanel imagen = new FondoPanel();
	private JMenuItem mntmRequerimientos;
	public static JLabel lblnombreSesion;
	private JMenuItem mntmVerPECOSAs;
	
	public frmMenuSubAlmacenero() {
		setTitle("Sub Almacen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 596);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		this.setContentPane(imagen);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnSistema.add(mntmSalir);
		
		JMenu mnBandejaDeEntrada = new JMenu("Bandeja de Entrada");
		menuBar.add(mnBandejaDeEntrada);
		
		mntmRequerimientos = new JMenuItem("Ver Requerimientos aprobados");
		mntmRequerimientos.addActionListener(this);
		mnBandejaDeEntrada.add(mntmRequerimientos);
		
		JMenu mnAlmacen = new JMenu("Almacen");
		menuBar.add(mnAlmacen);
		
		mntmVerPECOSAs = new JMenuItem("Ver PECOSAs");
		mntmVerPECOSAs.addActionListener(this);
		mnAlmacen.add(mntmVerPECOSAs);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 11, 374, 79);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblBienvenido = new JLabel("Bienvenido:");
		lblBienvenido.setBounds(10, 11, 103, 21);
		panel.add(lblBienvenido);
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(10, 47, 354, 21);
		panel.add(lblCargo);
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		lblnombreSesion = new JLabel("");
		lblnombreSesion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblnombreSesion.setBounds(99, 11, 245, 21);
		panel.add(lblnombreSesion);
		
	}
	class FondoPanel extends JPanel
	{
		private Image img;
		
		@Override
		public void paint(Graphics g) {
			img = new ImageIcon(getClass().getResource("/img/SubAlmacenero.png")).getImage();
			
			g.drawImage(img, 0, 0, getWidth(), getHeight(),this);
			
			setOpaque(false);
			
			super.paint(g);
			
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmVerPECOSAs) {
			actionPerformedMntmFormulacionDeUna(e);
		}
		if (e.getSource() == mntmRequerimientos) {
			actionPerformedMntmRequerimientos(e);
		}
	}
	protected void actionPerformedMntmRequerimientos(ActionEvent e) {
		dlgBandejaEntradaSubAlmacen dlg = new dlgBandejaEntradaSubAlmacen();
		dlg.setVisible(true);
		dlg.setLocationRelativeTo(null);
	}
	protected void actionPerformedMntmFormulacionDeUna(ActionEvent e) {
		dlgConsultarPECOSA dlg = new dlgConsultarPECOSA();
		dlg.setVisible(true);
	}
}

