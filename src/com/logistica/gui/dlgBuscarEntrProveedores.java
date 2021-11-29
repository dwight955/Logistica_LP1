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
import com.logistica.controlador.MySqlTrabajadorDAO;
import com.logistica.entidad.Trabajador;

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
	private JTable tblTrabajadores;
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
		setBounds(100, 100, 667, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 424, 334);
		contentPanel.add(scrollPane);
		
		tblTrabajadores = new JTable();
		tblTrabajadores.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"RUC", "RAZON SOCIAL", "TELEFONO", "DISTRITO"
			}
		));
		tblTrabajadores.getColumnModel().getColumn(0).setPreferredWidth(16);
		tblTrabajadores.getColumnModel().getColumn(2).setPreferredWidth(51);
		scrollPane.setViewportView(tblTrabajadores);
		
		txtBuscarProveedor = new JTextField();
		txtBuscarProveedor.setToolTipText("Apellidos o Nombres");
		txtBuscarProveedor.addKeyListener(this);
		txtBuscarProveedor.setBounds(444, 85, 197, 26);
		contentPanel.add(txtBuscarProveedor);
		txtBuscarProveedor.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Razon Social:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(445, 60, 136, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BUSQUEDA DE TRABAJADORES");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Swis721 Blk BT", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(165, 11, 340, 36);
		contentPanel.add(lblNewLabel_1);
		
		cboDistrito = new JComboBox();
		cboDistrito.addActionListener(this);
		cboDistrito.setModel(new DefaultComboBoxModel(new String[] {"[TODOS]"}));
		cboDistrito.setBounds(444, 157, 197, 22);
		contentPanel.add(cboDistrito);
		
		JLabel lblCargo = new JLabel("Distrito :");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(444, 126, 136, 22);
		contentPanel.add(lblCargo);
		{
			btnAñadir = new JButton("");
			btnAñadir.addActionListener(this);
			btnAñadir.addMouseListener(this);
			btnAñadir.setIcon(new ImageIcon(dlgBuscarEntrProveedores.class.getResource("/iconos/add.png")));
			btnAñadir.setBounds(444, 214, 197, 36);
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
			keyReleasedTxtBuscarTrabajadores(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		String apeNom = txtBuscarProveedor.getText();
		if(!Character.isAlphabetic(c) && c != ' ') {
			e.consume();
		} else if(apeNom.length()==30) {
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
			actionPerformedCboCargo(e);
		}
	}
	protected void keyReleasedTxtBuscarTrabajadores(KeyEvent e) {
		String apenomTra = txtBuscarProveedor.getText();
		String comodin = "%";
		String unidad = cboDistrito.getSelectedItem().toString();
		listar(apenomTra, comodin, unidad);
	}
	
	protected void actionPerformedCboCargo(ActionEvent e) {
		String apenomTra = "";
		String comodin = "";
		String unidad = cboDistrito.getSelectedItem().toString();
		listar(apenomTra, comodin, unidad);
	}
	void listar(String apenomTra,String comodin, String unidad) {
		if(apenomTra.equals("") && unidad.equals("[TODOS]")) comodin = "%";
		DefaultTableModel modelo = (DefaultTableModel) tblTrabajadores.getModel();
		modelo.setRowCount(0);
		ArrayList<Trabajador> lista = trabajadorDAO.ListarBusqueda(apenomTra, comodin, unidad);
		for(Trabajador tra:lista) {
			Object[] filas = {tra.getDni(),tra.getNomApe(),tra.getUnidadOrga()};
			modelo.addRow(filas);
		}
	}
	protected void mouseClickedBtnAñadir(MouseEvent e) {
				try {
					//varaible
					String dni,apenom,unidad;
					//Obtener posicion de la fila seleccionada
					int posFila;
					posFila = tblTrabajadores.getSelectedRow();
					//Obtener datos de la fila seleccionada
					dni = tblTrabajadores.getValueAt(posFila, 0).toString();
					apenom = tblTrabajadores.getValueAt(posFila, 1).toString();
					unidad = tblTrabajadores.getValueAt(posFila, 2).toString();
					
						//Enviar valores a las cajas de formulario frmBoleta
						frmCuadroRequerimientos.txtdniEntr.setText(dni);
						frmCuadroRequerimientos.txtNombreEntr.setText(apenom);
						frmCuadroRequerimientos.txtParaUnidadOrg.setText(unidad);
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
