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

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.LineBorder;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;

public class frmMenuUnidadOrganica extends JFrame{

	private JPanel contentPane;

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
		mnSitem.add(mntmSalir);
		
		JMenu mnAlmacen = new JMenu("Almacen");
		menuBar.add(mnAlmacen);
		
		JMenuItem mntmGenerarCuadroDe = new JMenuItem("Generar Cuadro de Requerimientos");
		mnAlmacen.add(mntmGenerarCuadroDe);
		
		JMenu mnConsultar = new JMenu("Consultar");
		menuBar.add(mnConsultar);
		
		JMenuItem mntmVerListadoDe = new JMenuItem("Ver listado de Requerimientos");
		mnConsultar.add(mntmVerListadoDe);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(20, 27, 102, 26);
		getContentPane().add(lblNewLabel);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCargo.setBounds(20, 75, 102, 26);
		getContentPane().add(lblCargo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(10, 11, 259, 106);
		getContentPane().add(panel);
		
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
}
