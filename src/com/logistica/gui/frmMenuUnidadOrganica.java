package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Cursor;

import com.logistica.entidad.UnidadOrganica;
import com.logistica.gui.frmLogin;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.LineBorder;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmMenuUnidadOrganica extends JFrame implements ActionListener{
	private JPanel contentPane;
	public JFrame windows;
	public JLabel lblNomCargo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMenuUnidadOrganica frame = new frmMenuUnidadOrganica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	FondoPanel imagen = new FondoPanel();
	public JLabel lblBienvenido;
	private JMenuItem mntmGenerarCuadroDe;
	
	public frmMenuUnidadOrganica() {
		setTitle("Unidad Organica: (Nombre de la unidad org.)");
		setBackground(new Color(0, 120, 215));
		setForeground(SystemColor.textHighlight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 494);
		contentPane = new JPanel();
		this.setContentPane(imagen);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSitem = new JMenu("Sitema");
		menuBar.add(mnSitem);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmSalir(e);
			}
		});
		mnSitem.add(mntmSalir);
		
		JMenu mnAlmacen = new JMenu("Almacen");
		menuBar.add(mnAlmacen);
		
		mntmGenerarCuadroDe = new JMenuItem("Generar Cuadro de Requerimientos");
		mntmGenerarCuadroDe.addActionListener(this);
		mnAlmacen.add(mntmGenerarCuadroDe);
		
		JMenu mnConsultar = new JMenu("Consultar");
		menuBar.add(mnConsultar);
		
		JMenuItem mntmVerListadoDe = new JMenuItem("Ver listado de Requerimientos");
		mnConsultar.add(mntmVerListadoDe);
		getContentPane().setLayout(null);
		
		lblNomCargo = new JLabel("Cargo: " );
		lblNomCargo.setBackground(new Color(250, 240, 230));
		lblNomCargo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomCargo.setBounds(20, 62, 420, 20);
		getContentPane().add(lblNomCargo);
		
		lblBienvenido = new JLabel("Bienvenido:");
		lblBienvenido.setBackground(new Color(250, 240, 230));
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBienvenido.setBounds(20, 25, 404, 14);
		getContentPane().add(lblBienvenido);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(10, 11, 397, 83);
		getContentPane().add(panel);

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmGenerarCuadroDe) {
			actionPerformedMntmGenerarCuadroDe(e);
		}
	}
	
	class FondoPanel extends JPanel
	{
		private Image img;
		
		@Override
		public void paint(Graphics g) {
			img = new ImageIcon(getClass().getResource("/img/logistica.jpg")).getImage();
			
			g.drawImage(img, 0, 0, getWidth(), getHeight(),this);
			
			setOpaque(false);
			
			super.paint(g);
			
		}
	}

	protected void actionPerformedMntmGenerarCuadroDe(ActionEvent e) {
		frmCuadroRequerimientos frm =new frmCuadroRequerimientos();
		frm.setVisible(true);
		frm.setLocationRelativeTo(null);
	}
	protected void actionPerformedMntmSalir(ActionEvent e) {
		dispose();
	}
}
