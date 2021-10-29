package com.logistica.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import hora.MostraHora;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class frmPrincipal extends JFrame implements ActionListener {
	
	
	private JPanel contentPane;
	private JMenuItem mntmEmisionDePecosa;
	private JMenuItem mntmCerrar;
	private JMenuItem mntmInventarioInicial;
	public JLabel lblDatos;
	public static JLabel lblHora;
	public static JLabel lblFecha2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal frame = new frmPrincipal();
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
	public frmPrincipal() {
		setForeground(SystemColor.textInactiveText);
		setBackground(Color.WHITE);
		setExtendedState(Frame.MAXIMIZED_HORIZ);
		setTitle("LOGISTICA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		this.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		mntmCerrar = new JMenuItem("Cerrar");
		mntmCerrar.addActionListener(this);
		mnSistema.add(mntmCerrar);
		
		JMenu mnAlmacen = new JMenu("Almacen");
		menuBar.add(mnAlmacen);
		
		mntmInventarioInicial = new JMenuItem("Inventario Inicial");
		mntmInventarioInicial.addActionListener(this);
		mnAlmacen.add(mntmInventarioInicial);
		
		mntmEmisionDePecosa = new JMenuItem("Emision de PECOSA");
		mntmEmisionDePecosa.addActionListener(this);
		mnAlmacen.add(mntmEmisionDePecosa);
		
		JMenu mnReporte = new JMenu("Reporte");
		menuBar.add(mnReporte);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDatos = new JLabel("Bienvenido:");
		lblDatos.setForeground(Color.BLACK);
		lblDatos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblDatos.setBounds(319, 11, 545, 49);
		contentPane.add(lblDatos);
		
		
		lblHora = new JLabel("hora");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHora.setBounds(166, 9, 120, 29);
		contentPane.add(lblHora);
		
		lblFecha2 = new JLabel("fecha");
		lblFecha2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha2.setBounds(10, 5, 158, 36);
		contentPane.add(lblFecha2);
		
		MostraHora hora=new MostraHora();
		hora.start();
		
			
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmInventarioInicial) {
			actionPerformedMntmInventarioInicial(e);
		}
		if (e.getSource() == mntmCerrar) {
			actionPerformedMntmCerrar(e);
		}
		if (e.getSource() == mntmEmisionDePecosa) {
			actionPerformedMntmEmisionDePecosa(e);
		}
	}
	protected void actionPerformedMntmEmisionDePecosa(ActionEvent e) {
		frmPecosa frm= new frmPecosa();
		frm.setVisible(true);
	}
	protected void actionPerformedMntmCerrar(ActionEvent e) {
		System.exit(0);
	}
	
	protected void actionPerformedMntmInventarioInicial(ActionEvent e) {
		
	}
	
	
}
