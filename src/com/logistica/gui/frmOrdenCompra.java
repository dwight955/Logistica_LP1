package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import com.logistica.componentes.JTextFielBD;
import com.logistica.controlador.MySqlOrdenCompraDAO;
import com.logistica.entidad.OrdenCompra;
import com.logistica.entidad.Pecosa;
import com.mxrck.autocompleter.TextAutoCompleter;

import lib.Mensajes;

import java.awt.Button;

import javax.swing.JButton;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;

public class frmOrdenCompra extends JFrame implements ActionListener, KeyListener{
	MySqlOrdenCompraDAO ordenCompraDAO = new MySqlOrdenCompraDAO();
	
	private JPanel contentPane;
	private JTextField txtNumOc;
	private JTextField txtFecha;
	public static JTextField txtRuc;
	public static JTextField txtRazonSocial;
	public static JTextField txtDireccion;
	public static JTextField txtTelefono;
	private JTextField txtDocRefe;
	private JTextField txtEstado;
	private JTable tblOrdenCompra;
	private JTextField txtPrecioTotal;
	private JScrollPane tblDetallePecosa;
	private JButton btnGuardar;
	private TextAutoCompleter ac;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnSalir;
	public static JButton btnBuscarProveedor;
	private TextAutoCompleter cn;
	private JLabel lblOrdenDeCompra;
	private JTextField txtFuentesFto;
	private JPopupMenu popupMenu;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmOrdenCompra frame = new frmOrdenCompra();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmOrdenCompra() {
		setTitle("Orden de Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(211, 211, 211));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(10, 57, 209, 118);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNPecosa = new JLabel("N\u00BA O/C:");
		lblNPecosa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNPecosa.setBounds(10, 29, 97, 23);
		panel.add(lblNPecosa);
		
		txtNumOc = new JTextField(codigoCorrelativo());
		txtNumOc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNumOc.setEditable(false);
		txtNumOc.setBounds(80, 26, 119, 28);
		panel.add(txtNumOc);
		txtNumOc.setColumns(10);
		
		txtFecha = new JTextField(lib.Fecha.fechaActual());
		txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(80, 75, 119, 28);
		panel.add(txtFecha);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(10, 76, 97, 23);
		panel.add(lblFecha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(135, 206, 235));
		panel_1.setBounds(229, 88, 774, 88);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("RUC   :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(8, 13, 67, 19);
		panel_1.add(lblNewLabel);
		
		txtRuc = new JTextField();
		txtRuc.addKeyListener(this);
		txtRuc.setEditable(false);
		txtRuc.setBounds(99, 10, 167, 27);
		panel_1.add(txtRuc);
		txtRuc.setColumns(10);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setEditable(false);
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(321, 11, 443, 27);
		panel_1.add(txtRazonSocial);
		

		JLabel lblEntregarDni = new JLabel("Direcci\u00F3n  :");
		lblEntregarDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEntregarDni.setBounds(8, 51, 124, 22);
		panel_1.add(lblEntregarDni);
		
		txtDireccion = new JTextFielBD("dniTrabajador","TB_Trabajadores");
		txtDireccion.addKeyListener(this);
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(100, 48, 395, 27);
		panel_1.add(txtDireccion);
		
		JLabel lblApeNombre = new JLabel("Telefono :");
		lblApeNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApeNombre.setBounds(505, 51, 105, 20);
		panel_1.add(lblApeNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(597, 48, 167, 27);
		panel_1.add(txtTelefono);
		
		btnBuscarProveedor = new JButton("");
		btnBuscarProveedor.setEnabled(false);
		btnBuscarProveedor.addActionListener(this);
		btnBuscarProveedor.setIcon(new ImageIcon(frmOrdenCompra.class.getResource("/iconos/search.png")));
		btnBuscarProveedor.setBounds(276, 10, 35, 27);
		panel_1.add(btnBuscarProveedor);
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(211, 211, 211));
		panel_2.setBounds(229, 184, 774, 88);
		contentPane.add(panel_2);
		
		JLabel lblReferencia = new JLabel("Doc. Refe  :");
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReferencia.setBounds(10, 11, 105, 23);
		panel_2.add(lblReferencia);
		
		txtDocRefe = new JTextField();
		txtDocRefe.setEditable(false);
		txtDocRefe.setColumns(10);
		txtDocRefe.setBounds(110, 10, 652, 27);
		panel_2.add(txtDocRefe);
		
		JLabel lblFuentesFto = new JLabel("Fuentes Fto. :");
		lblFuentesFto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFuentesFto.setBounds(10, 51, 105, 23);
		panel_2.add(lblFuentesFto);
		
		txtFuentesFto = new JTextFielBD("concat_ws(' - ',codFin,nombre)","tb_ftfinanciamiento");
		txtFuentesFto.addKeyListener(this);
		txtFuentesFto.setEditable(false);
		txtFuentesFto.setColumns(10);
		txtFuentesFto.setBounds(110, 50, 652, 27);
		panel_2.add(txtFuentesFto);
		
		JLabel lblEstado = new JLabel("Estado  :");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(824, 51, 105, 23);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(897, 50, 94, 27);
		contentPane.add(txtEstado);
		
		tblDetallePecosa = new JScrollPane();
		tblDetallePecosa.setBounds(10, 295, 993, 245);
		contentPane.add(tblDetallePecosa);
		
		tblOrdenCompra = new JTable();
		tblOrdenCompra.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Uni. Med", "Bienes", "P. U. Salida", "Cant. Salida", "Precio Total Salida", "U. O.", "Meta"
			}
		));
		tblOrdenCompra.getColumnModel().getColumn(3).setPreferredWidth(104);
		
		popupMenu = new JPopupMenu();
		addPopup(tblDetallePecosa, popupMenu);
		
		JMenuItem mntmA�adirBienes = new JMenuItem("A\u00F1adir Bien");
		popupMenu.add(mntmA�adirBienes);
		
		JMenuItem mntmEliminarBien = new JMenuItem("Eliminar Bien");
		popupMenu.add(mntmEliminarBien);
		tblDetallePecosa.setViewportView(tblOrdenCompra);
		
		JLabel lblPrecioTotal = new JLabel("Precio Total  S/.  :");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioTotal.setBounds(757, 552, 121, 23);
		contentPane.add(lblPrecioTotal);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setColumns(10);
		txtPrecioTotal.setBounds(885, 551, 94, 27);
		contentPane.add(txtPrecioTotal);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.setBounds(803, 593, 97, 38);
		contentPane.add(btnModificar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(699, 593, 94, 38);
		contentPane.add(btnCancelar);
		
		btnGuardar = new JButton("Nuevo");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.addActionListener(this);
		btnGuardar.setActionCommand("");
		btnGuardar.setBounds(595, 593, 94, 38);
		contentPane.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(910, 592, 81, 38);
		contentPane.add(btnSalir);
		
		lblOrdenDeCompra = new JLabel("Orden de Compra");
		lblOrdenDeCompra.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdenDeCompra.setFont(new Font("Square721 BT", Font.BOLD, 30));
		lblOrdenDeCompra.setBounds(360, 15, 382, 53);
		contentPane.add(lblOrdenDeCompra);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBackground(new Color(100, 149, 237));
		panel_3.setBounds(229, 57, 113, 32);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Proveedor");
		lblNewLabel_2.setFont(new Font("Swis721 Blk BT", Font.ITALIC, 15));
		lblNewLabel_2.setBounds(10, 0, 93, 32);
		panel_3.add(lblNewLabel_2);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
		if (e.getSource() == btnBuscarProveedor) {
			actionPerformedBtnBuscarProveedor(e);
		}
		if(e.getSource() == mntmA�adirBienes) {
			actionPerformedMntmAadirBienes(e);
		}
	}
	
	protected void actionPerformedBtnBuscarProveedor(ActionEvent e) {
		dlgBuscarEntrProveedores frm = new dlgBuscarEntrProveedores();
		frm.setVisible(true);
		frm.setLocationRelativeTo(null);
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		if(txtRuc.isEditable()==false) {
			componentes(true);
			btnGuardar.setText("Guardar");
			btnBuscarProveedor.setEnabled(true);
			txtEstado.setText("PROCESADO");
		}
		String numPec,fecPec,soliDni,entrDni,estad,refe,meta,uniOrg;
		fecPec = txtFecha.getText();
		soliDni = txtSoliDni.getText();
		entrDni = txtEntrDni.getText();
		estad = txtEstado.getText();
		refe = txtReferencia.getText();
		uniOrg = txtUniOrg.getText();
		meta = txtMeta.getText();
		if(soliDni.equals("")) {
			Mensajes.dialogo("Es obligatorio el campo DNI SOLICITANTE");
		}
		
		Pecosa pec = new Pecosa();
		pec.setFecPec(fecPec);
		pec.setDniSoliPec(Integer.parseInt(soliDni));
		pec.setDniEntrPec(Integer.parseInt(entrDni));
		pec.setEstadoPec(estad);
		pec.setReferencia(refe);
		pec.setUniOrgPec(uniOrg);
		pec.setMeta(meta);
		int salida = pecosaDao.Ingresar(pec);
		if(salida > 0) {
			mensaje("El registro de la PECOSA fue un exito");
		}else {
			mensaje("Fallo en el Ingreso");
		}
	}

	/*protected void keyReleasedTxtdniEntr(KeyEvent e) {
		String dni = txtdniEntr.getText();
		String[] data = pecosaDAO.buscarTrabajador(dni);
		txtNombreEntr.setText(data[0]);
		txtParaUnidadOrg.setText(data[1]);
	}*/
	
	protected void actionPerformedMntmAadirBienes(ActionEvent e) {
		dlgAgregarBienesPecosa dlg = new dlgAgregarBienesPecosa();
		dlg.setVisible(true);
		dlg.setLocationRelativeTo(null);
	}
	
	void componentes(boolean op) {
		txtRuc.setEditable(op);
		txtDocRefe.setEditable(op);
		txtFuentesFto.setEditable(op);
	}
	
	public String codigoCorrelativo() {
		ArrayList<OrdenCompra> data = ordenCompraDAO.ListarTodo();
		String codigoSerial;
		if(data.size()==0) {
			codigoSerial = "000001";
		}else {
			int num = data.get(data.size()-1).getNroOC() + 1;
			codigoSerial = String.format("%06d", num);
		}
		return codigoSerial;			
	}
	
	public void keyReleasedTxtRuc(KeyEvent e) {
		String ruc = txtRuc.getText();
		String[] data = ordenCompraDAO.buscarProveedor(ruc);
	}	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
