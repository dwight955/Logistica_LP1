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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class frmPrincipal extends JFrame implements ActionListener {
	
	
	private JPanel contentPane;
	private JMenuItem mntmEmisionDePecosa;
	public JLabel lblDatos;
	private JMenuItem mntmCerrar;
	

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
		setTitle("LOGISTICA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 451);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		mntmCerrar = new JMenuItem("Cerrar");
		mntmCerrar.addActionListener(this);
		mnSistema.add(mntmCerrar);
		
		JMenu mnAlmacen = new JMenu("Almacen");
		menuBar.add(mnAlmacen);
		
		JMenuItem mntmInventarioInicial = new JMenuItem("Inventario Inicial");
		mnAlmacen.add(mntmInventarioInicial);
		
		mntmEmisionDePecosa = new JMenuItem("Emision de PECOSA");
		mntmEmisionDePecosa.addActionListener(this);
		mnAlmacen.add(mntmEmisionDePecosa);
		
		JMenu mnReporte = new JMenu("Reporte");
		menuBar.add(mnReporte);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDatos = new JLabel("Bienvenido:");
		lblDatos.setForeground(Color.BLACK);
		lblDatos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatos.setBounds(10, 11, 792, 23);
		contentPane.add(lblDatos);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmCerrar) {
			actionPerformedMntmCerrar(e);
		}
		if (e.getSource() == mntmEmisionDePecosa) {
			actionPerformedMntmEmisionDePecosa(e);
		}
	}
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	protected void actionPerformedMntmEmisionDePecosa(ActionEvent e) {
		frmPecosa abrir= new frmPecosa();
		abrir.setVisible(true);
		
		
	}
	protected void actionPerformedMntmCerrar(ActionEvent e) {
		dispose();
	}
	
}
