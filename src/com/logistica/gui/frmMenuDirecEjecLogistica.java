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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class frmMenuDirecEjecLogistica extends JFrame implements ActionListener {

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
	public JLabel lblNomDirec;
	private JMenuItem mntmRequerimientos;
	public frmMenuDirecEjecLogistica() {
		setTitle("Director Ejecutivo de Logistica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 603);
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
		
		mntmRequerimientos = new JMenuItem("Ver Cuadro de Requerimientos");
		mntmRequerimientos.addActionListener(this);
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
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel.setBounds(275, 11, 134, 41);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDirectorEjecutivoDe = new JLabel("Director Ejecutivo de Logistica");
		lblDirectorEjecutivoDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDirectorEjecutivoDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirectorEjecutivoDe.setBounds(218, 101, 249, 19);
		getContentPane().add(lblDirectorEjecutivoDe);
		
		lblNomDirec = new JLabel("New label");
		lblNomDirec.setForeground(new Color(65, 105, 225));
		lblNomDirec.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomDirec.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNomDirec.setBounds(150, 63, 385, 19);
		getContentPane().add(lblNomDirec);
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmRequerimientos) {
			actionPerformedMntmRequerimientos(e);
		}
	}
	protected void actionPerformedMntmRequerimientos(ActionEvent e) {
		dlgBandejaEntradaDirector frm = new dlgBandejaEntradaDirector();
		frm.setVisible(true);
		frm.setLocationRelativeTo(null);
	}
}
