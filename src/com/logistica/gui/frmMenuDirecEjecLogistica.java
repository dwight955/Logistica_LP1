package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

public class frmMenuDirecEjecLogistica extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMenuDirecEjecLogistica frame = new frmMenuDirecEjecLogistica();
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
	public frmMenuDirecEjecLogistica() {
		setTitle("Director Ejecutivo de Logistica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 496);
		contentPane = new JPanel();
		this.setContentPane(imagen);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(1, 1, 1, 1));
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnSistema.add(mntmSalir);
		
		JMenu mnNewMenu = new JMenu("Bandeja de Entrada");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmRequerimientos = new JMenuItem("Ver Cuadro de Requerimientos");
		mnNewMenu.add(mntmRequerimientos);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");
		mnMantenimiento.add(mntmProveedores);
		
		JMenuItem mntmTrabajadores = new JMenuItem("Trabajadores");
		mnMantenimiento.add(mntmTrabajadores);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BIENVENIDO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Swis721 Hv BT", Font.PLAIN, 17));
		lblNewLabel.setBounds(251, 11, 134, 41);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDirectorEjecutivoDe = new JLabel("Director Ejecutivo de Logistica");
		lblDirectorEjecutivoDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDirectorEjecutivoDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirectorEjecutivoDe.setBounds(195, 101, 249, 19);
		getContentPane().add(lblDirectorEjecutivoDe);
	}
	class FondoPanel extends JPanel
	{
		private Image img;
		
		@Override
		public void paint(Graphics g) {
			img = new ImageIcon(getClass().getResource("/img/Director.jpg")).getImage();
			
			g.drawImage(img, 0, 0, getWidth(), getHeight(),this);
			
			setOpaque(false);
			
			super.paint(g);
			
		}
	}
}
