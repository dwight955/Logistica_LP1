package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.logistica.componentes.JComboBoxBD;
import com.logistica.controlador.MySqlProveedorDAO;
import com.logistica.entidad.Proveedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class dlgBuscarEntrProveedores extends JDialog implements KeyListener, ActionListener, MouseListener{
	MySqlProveedorDAO proveedorDAO = new MySqlProveedorDAO();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscarProveedor;
	private JTable tblProveedores;
	private JComboBox cboDistrito;
	private JButton btnAñadir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgBuscarEntrProveedores dialog = new dlgBuscarEntrProveedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dlgBuscarEntrProveedores() {
		setTitle("Proveedores");
		setBounds(100, 100, 765, 441);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 509, 334);
		contentPanel.add(scrollPane);
		
		tblProveedores = new JTable();
		tblProveedores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"RUC", "RAZON SOCIAL", "TELEFONO", "DISTRITO", "DIRECCION"
			}
		));
		tblProveedores.getColumnModel().getColumn(0).setPreferredWidth(16);
		tblProveedores.getColumnModel().getColumn(1).setPreferredWidth(98);
		tblProveedores.getColumnModel().getColumn(2).setPreferredWidth(49);
		tblProveedores.getColumnModel().getColumn(3).setPreferredWidth(53);
		scrollPane.setViewportView(tblProveedores);
		
		txtBuscarProveedor = new JTextField();
		txtBuscarProveedor.addKeyListener(this);
		txtBuscarProveedor.setBounds(542, 83, 197, 26);
		contentPanel.add(txtBuscarProveedor);
		txtBuscarProveedor.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Razon Social:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(543, 58, 136, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BUSQUEDA DE PROVEEDORES");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Swis721 Blk BT", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(196, 11, 340, 36);
		contentPanel.add(lblNewLabel_1);
		
		cboDistrito = new JComboBox();
		cboDistrito.addActionListener(this);
		cboDistrito.setModel(new DefaultComboBoxModel(new String[] {"[TODOS]", "ANCON", "COMAS", "SANTA ROSA", "VILLA ESTELA"}));
		cboDistrito.setBounds(542, 155, 197, 22);
		contentPanel.add(cboDistrito);
		
		JLabel lblCargo = new JLabel("Distrito :");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(542, 124, 136, 22);
		contentPanel.add(lblCargo);
		{
			btnAñadir = new JButton("");
			btnAñadir.addActionListener(this);
			btnAñadir.addMouseListener(this);
			btnAñadir.setIcon(new ImageIcon(dlgBuscarEntrProveedores.class.getResource("/iconos/add.png")));
			btnAñadir.setBounds(542, 212, 197, 36);
			contentPanel.add(btnAñadir);
			btnAñadir.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAñadir);
		}
		listar("", "%", "");
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtBuscarProveedor) {
			keyReleasedTxtBuscarProveedor(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		String razSoc = txtBuscarProveedor.getText();
		if(!Character.isAlphabetic(c) && c != ' ') {
			e.consume();
		} else if(razSoc.length()==30) {
			e.consume();
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnAñadir) {
			mouseClickedBtnAñadir(e);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboDistrito) {
			actionPerformedCboDistrito(e);
		}
	}
	protected void keyReleasedTxtBuscarProveedor(KeyEvent e) {
		String razonSoc = txtBuscarProveedor.getText();
		String comodin = "%";
		String distrito = cboDistrito.getSelectedItem().toString();
		listar(razonSoc, comodin, distrito);
	}
	
	protected void actionPerformedCboDistrito(ActionEvent e) {
		String razonSoc = "";
		String comodin = "";
		String distrito = cboDistrito.getSelectedItem().toString();
		listar(razonSoc, comodin, distrito);
	}
	void listar(String razonSoc,String comodin, String distrito) {
		if(razonSoc.equals("") && distrito.equals("[TODOS]")) comodin = "%";
		
		DefaultTableModel modelo = (DefaultTableModel) tblProveedores.getModel();
		modelo.setRowCount(0);
		
		ArrayList<Proveedor> lista = proveedorDAO.ListarBusqueda(razonSoc, comodin, distrito);
		
		for(Proveedor tra:lista) {
			Object[] filas = {	tra.getNroRuc(),
								tra.getRzSoc(),
								tra.getTelf(),
								tra.getNomDis(),
								tra.getDirec()};
			modelo.addRow(filas);
		}
	}
	protected void mouseClickedBtnAñadir(MouseEvent e) {
				try {
					//varaible
					String razSoc,ruc,distrito, telf, direc;
					//Obtener posicion de la fila seleccionada
					int posFila;
					posFila = tblProveedores.getSelectedRow();
					//Obtener datos de la fila seleccionada
					ruc = tblProveedores.getValueAt(posFila, 0).toString();
					razSoc = tblProveedores.getValueAt(posFila, 1).toString();
					telf = tblProveedores.getValueAt(posFila, 2).toString();
					distrito = tblProveedores.getValueAt(posFila, 3).toString();
					direc = tblProveedores.getValueAt(posFila, 4).toString();
					
						//Enviar valores a las cajas de formulario frmBoleta
						frmOrdenCompra.txtRuc.setText(ruc);
						frmOrdenCompra.txtRazonSocial.setText(razSoc);
						frmOrdenCompra.txtTelefono.setText(telf);
						frmOrdenCompra.txtDireccion.setText(direc);
						//cerrar ventana actual
						dispose();
					
					
					
				} catch (Exception e2) {
					mensaje("Seleccionar Trabajador");
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
	void mensaje(String m){
		JOptionPane.showMessageDialog(null, m);
	}
}
