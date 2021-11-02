package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import com.logistica.componentes.JComboBoxBD;
import com.logistica.controlador.MySqlProveedorDAO;
import com.logistica.entidad.Bienes;
import com.logistica.entidad.Proveedor;

import lib.Mensajes;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class frmProveedores extends JFrame implements ActionListener, MouseListener, KeyListener {
	MySqlProveedorDAO proveedorDAO = new MySqlProveedorDAO();
		
	private JPanel contentPane;
	private JTextField txtBusqueda;
	private JTable tblProveedores;
	private JLabel lblNroRuc;
	private JTextField txtRazonSoci;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtRuc;
	private JComboBox cboEstado;
	private JComboBox cboCondicion;
	private JComboBoxBD cboDistrito;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JButton btnCancelar;
	private JPopupMenu popupMenu;
	private JMenuItem mntmEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmProveedores frame = new frmProveedores();
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
	public frmProveedores() {
		setTitle("Mantenimiento - PROVEEDORES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 870, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 834, 191);
		contentPane.add(scrollPane);
		
		tblProveedores = new JTable();
		tblProveedores.addMouseListener(this);
		tblProveedores.setFillsViewportHeight(true);
		tblProveedores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro RUC", "Razon Social", "Estado", "Condicion", "Direccion", "telefono", "Distrito"
			}
		));
		tblProveedores.getColumnModel().getColumn(1).setPreferredWidth(171);
		tblProveedores.getColumnModel().getColumn(2).setPreferredWidth(43);
		tblProveedores.getColumnModel().getColumn(2).setMinWidth(11);
		tblProveedores.getColumnModel().getColumn(3).setPreferredWidth(30);
		tblProveedores.getColumnModel().getColumn(4).setPreferredWidth(116);
		tblProveedores.getColumnModel().getColumn(5).setPreferredWidth(49);
		tblProveedores.getColumnModel().getColumn(6).setPreferredWidth(98);
		scrollPane.setViewportView(tblProveedores);
		
		popupMenu = new JPopupMenu();
		addPopup(tblProveedores, popupMenu);
		
		mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(this);
		mntmEliminar.setIcon(new ImageIcon(frmProveedores.class.getResource("/iconos/trash.png")));
		popupMenu.add(mntmEliminar);
		
		txtBusqueda = new JTextField();
		txtBusqueda.setColumns(10);
		txtBusqueda.setBounds(60, 11, 159, 26);
		contentPane.add(txtBusqueda);
		
		lblNroRuc = new JLabel("Nro RUC:");
		lblNroRuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNroRuc.setBounds(10, 250, 73, 26);
		contentPane.add(lblNroRuc);
		
		txtRuc = new JTextField();
		txtRuc.addKeyListener(this);
		txtRuc.setEnabled(false);
		txtRuc.setBounds(10, 282, 132, 26);
		contentPane.add(txtRuc);
		txtRuc.setColumns(10);
		
		txtRazonSoci = new JTextField();
		txtRazonSoci.addKeyListener(this);
		txtRazonSoci.setEnabled(false);
		txtRazonSoci.setColumns(10);
		txtRazonSoci.setBounds(10, 350, 209, 26);
		contentPane.add(txtRazonSoci);
		
		JLabel lblRazonSocial = new JLabel("Razon Social");
		lblRazonSocial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRazonSocial.setBounds(10, 318, 120, 26);
		contentPane.add(lblRazonSocial);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstado.setBounds(254, 321, 120, 26);
		contentPane.add(lblEstado);
		
		cboEstado = new JComboBox();
		cboEstado.setEnabled(false);
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"ACTIVO", "NO ACTIVO"}));
		cboEstado.setBounds(254, 351, 159, 26);
		contentPane.add(cboEstado);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(10, 387, 120, 26);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(this);
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(10, 419, 132, 26);
		contentPane.add(txtTelefono);
		
		JLabel lblCondicion = new JLabel("Condicion");
		lblCondicion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCondicion.setBounds(254, 389, 120, 26);
		contentPane.add(lblCondicion);
		
		cboCondicion = new JComboBox();
		cboCondicion.setEnabled(false);
		cboCondicion.setModel(new DefaultComboBoxModel(new String[] {"HABIDO", "NO HABIDO"}));
		cboCondicion.setBounds(254, 419, 159, 26);
		contentPane.add(cboCondicion);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDireccion.setBounds(254, 252, 120, 26);
		contentPane.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setEnabled(false);
		txtDireccion.setBounds(254, 284, 242, 26);
		contentPane.add(txtDireccion);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 2, true));
		panel.setBounds(480, 350, 183, 85);
		contentPane.add(panel);
		
		cboDistrito = new JComboBoxBD("concat_ws('/',codDis,nomDis)","TB_Distrito");
		cboDistrito.setEnabled(false);
		cboDistrito.setBounds(10, 36, 163, 27);
		panel.add(cboDistrito);
		
		JLabel lblDistritos = new JLabel("Distritos");
		lblDistritos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDistritos.setBounds(10, 11, 86, 14);
		panel.add(lblDistritos);
		
		btnGuardar = new JButton("Nuevo");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(738, 276, 106, 35);
		contentPane.add(btnGuardar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(738, 325, 106, 35);
		contentPane.add(btnActualizar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(738, 378, 106, 35);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmProveedores.class.getResource("/iconos/search.png")));
		lblNewLabel.setBounds(20, 11, 41, 26);
		contentPane.add(lblNewLabel);
		
		listar();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmEliminar) {
			actionPerformedMntmEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnNuevo(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtRazonSoci) {
			keyTypedTxtRazonSoci(e);
		}
		if (e.getSource() == txtTelefono) {
			keyTypedTxtTelefono(e);
		}
		if (e.getSource() == txtRuc) {
			keyTypedTxtRuc(e);
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblProveedores) {
			mouseClickedTblProveedores(e);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		if(txtRuc.isEnabled() == false) {
			Elementos(true);
			btnGuardar.setText("Guardar");
		}else {
			String nroRuc,rzonSoc,estado,condi,dire,tele,codDis;
			nroRuc = txtRuc.getText();
			rzonSoc = txtRazonSoci.getText().toUpperCase();
			estado = cboEstado.getSelectedItem().toString();
			condi = cboCondicion.getSelectedItem().toString();
			tele = txtTelefono.getText();
			dire = txtDireccion.getText();
			codDis = cboDistrito.getSelectedItem().toString();
			//validacion
			if(nroRuc.equals("")) {
				Mensajes.dialogo("El campo NRO RUC es obligatorio");
			}else if(nroRuc.matches("[0-9]{11}") == false) {
				Mensajes.dialogo("Numero de Ruc: Min: 11 Max: 11");
			}else if(rzonSoc.equals("")) {
				Mensajes.dialogo("El campo RAZON SOCIAL es obligatorio");
			}else if(rzonSoc.length() < 10) {
				Mensajes.dialogo("Razon Social: Min: 10 Max: 40");
			}else if(tele.equals("")) {
				Mensajes.dialogo("El campo TELEFONO es obligatorio");
			}else if(tele.matches("^[9][0-9]{8}$") == false) {
				Mensajes.dialogo("Su número de telefono debe comenzar en 9 || Min: 9 Max: 9");
			}else if(dire.equals("")) {
				Mensajes.dialogo("El campo DIRECCION es obligatorio");
			}else if(dire.matches("^[A-Za-z0-9\\s//á//é//í//ó//ú//Á//É//Í//Ó//Ú//.//-]{10,40}$") == false) {
				Mensajes.dialogo("Direccion: Min:10 Max:40");
			}else {
				Proveedor pro = new Proveedor();
				pro.setNroRuc(nroRuc);
				pro.setRzSoc(rzonSoc);
				pro.setEstado(estado);
				pro.setCondic(condi);
				pro.setDirec(dire);
				pro.setTelf(Integer.parseInt(tele));
				String[] part = codDis.split("/");
				pro.setCodDis(part[0]);
				int salida = proveedorDAO.Ingresar(pro);
				if(salida > 0) {
					Mensajes.dialogo("El registro fue un exito");
					listar();
					limpiar();
					Elementos(false);
					btnGuardar.setText("Nuevo");
				}else {
					Mensajes.error("No se hizo el registro correctamente");
				}
			}
		}
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		String nroRuc,rzonSoc,estado,condi,dire,tele,codDis;
		nroRuc = txtRuc.getText();
		rzonSoc = txtRazonSoci.getText();
		estado = cboEstado.getSelectedItem().toString();
		condi = cboCondicion.getSelectedItem().toString();
		tele = txtTelefono.getText();
		dire = txtDireccion.getText();
		codDis = cboDistrito.getSelectedItem().toString();
		//Validacion
		
		Proveedor pro = new Proveedor();
		pro.setNroRuc(nroRuc);
		pro.setRzSoc(rzonSoc);
		pro.setEstado(estado);
		pro.setCondic(condi);
		pro.setDirec(dire);
		pro.setTelf(Integer.parseInt(tele));
		String[] part = codDis.split("/");
		pro.setCodDis(part[0]);
		int salida = proveedorDAO.Actualizar(pro);
		if(salida > 0) {
			Mensajes.dialogo("La actualizacion fue un exito");
			listar();
			limpiar();
			Elementos(false);
			btnActualizar.setEnabled(false);
			btnGuardar.setEnabled(true);
			btnGuardar.setText("Nuevo");
		}else {
			Mensajes.error("No se hizo la actualizacion correctamente");
		}
	}
	protected void actionPerformedMntmEliminar(ActionEvent e) {
		String nroRuc = txtRuc.getText();
		//validacion
		Proveedor pro = new Proveedor();
		pro.setNroRuc(nroRuc);
		int m = Mensajes.confirmarELiminar();
		if( m == 0) {
			Mensajes.dialogo("Se elimino el registro");
			proveedorDAO.Eliminar(pro);
			listar();
			limpiar();
		}
	}
	void Elementos(boolean op) {
		txtRuc.setEnabled(op);
		txtDireccion.setEnabled(op);
		txtRazonSoci.setEnabled(op);
		txtTelefono.setEnabled(op);
		cboCondicion.setEnabled(op);
		cboDistrito.setEnabled(op);
		cboEstado.setEnabled(op);
	}
	void limpiar() {
		txtBusqueda.setText("");
		txtDireccion.setText("");
		txtRazonSoci.setText("");
		txtRuc.setText("");
		txtTelefono.setText("");
		
	}
	void listar() {
		DefaultTableModel modelo = (DefaultTableModel) tblProveedores.getModel();
		modelo.setRowCount(0);
		
		ArrayList<Proveedor> data = new MySqlProveedorDAO().ListarTodo();
		for(Proveedor pro:data) {
			Object[] filas = {pro.getNroRuc(), pro.getRzSoc(),pro.getEstado(),pro.getCondic(),pro.getDirec(),pro.getTelf(),pro.getCodDis()};
			modelo.addRow(filas);
		}
	}

	
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTblProveedores(MouseEvent e) {
		String nroRuc,rzonSoc,estado,condi,dire,tele,codDis;
		int posFila;
		if((posFila = tblProveedores.getSelectedRow())<0) {
			posFila = 0;
		}else {
			Elementos(true);
			txtRuc.setEnabled(false);
			btnActualizar.setEnabled(true);
			btnGuardar.setEnabled(false);
			posFila = tblProveedores.getSelectedRow();
			
			nroRuc = tblProveedores.getValueAt(posFila, 0).toString();
			rzonSoc = tblProveedores.getValueAt(posFila, 1).toString();
			estado = tblProveedores.getValueAt(posFila, 2).toString();
			condi = tblProveedores.getValueAt(posFila, 3).toString();
			dire = tblProveedores.getValueAt(posFila, 4).toString();
			tele = tblProveedores.getValueAt(posFila, 5).toString();
			codDis = tblProveedores.getValueAt(posFila, 6).toString();
			
			txtRuc.setText(nroRuc);
			txtRazonSoci.setText(rzonSoc);
			cboEstado.setSelectedItem(estado);
			cboCondicion.setSelectedItem(condi);
			txtDireccion.setText(dire);
			txtTelefono.setText(tele);
			cboDistrito.setSelectedItem(codDis);
		}
		
		
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
	
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	
	protected void keyTypedTxtRuc(KeyEvent e) {
		char c = e.getKeyChar();
		String ruc = txtRuc.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(ruc.length() == 11) {
			e.consume();
		}
	}
	protected void keyTypedTxtTelefono(KeyEvent e) {
		char c = e.getKeyChar();
		String telf = txtTelefono.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(telf.length() == 9) {
			e.consume();
		}
	}
	protected void keyTypedTxtRazonSoci(KeyEvent e) {
		char c = e.getKeyChar();
		String rzSoc = txtRazonSoci.getText();
		if(!Character.isAlphabetic(c) && c != ' ') {
			e.consume();
		} else if(rzSoc.length() == 30) {
			e.consume();
		}
	}
}
