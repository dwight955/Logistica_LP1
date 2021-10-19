package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.logistica.componentes.JComboBoxBD;
import com.logistica.controlador.MySqlTrabajadorDAO;
import com.logistica.entidad.Trabajador;

import lib.Mensajes;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.JMenuItem;

public class frmTrabajadores extends JFrame implements ActionListener, MouseListener {
	MySqlTrabajadorDAO trabajadorDAO = new MySqlTrabajadorDAO();
		
	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtApeNom;
	private JTextField txtSueldo;
	private JTextField txtFecNac;
	private JTextField txtBuscarTranajador;
	private JTable tblTrabajadores;
	private JButton btnAñdir;
	private JComboBox cboCargo;
	private JButton btnActualizar;
	private JMenuItem mntmEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTrabajadores frame = new frmTrabajadores();
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
	public frmTrabajadores() {
		setTitle("Trabajadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 499, 191);
		contentPane.add(scrollPane);
		
		tblTrabajadores = new JTable();
		tblTrabajadores.setFillsViewportHeight(true);
		tblTrabajadores.addMouseListener(this);
		tblTrabajadores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Dni", "Apellidos y Nombres", "Cargo", "Fecha de Nacimiento", "Sueldo"
			}
		));
		tblTrabajadores.getColumnModel().getColumn(0).setPreferredWidth(33);
		tblTrabajadores.getColumnModel().getColumn(0).setMinWidth(11);
		tblTrabajadores.getColumnModel().getColumn(1).setPreferredWidth(108);
		tblTrabajadores.getColumnModel().getColumn(4).setPreferredWidth(59);
		scrollPane.setViewportView(tblTrabajadores);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tblTrabajadores, popupMenu);
		popupMenu.setBounds(0, 0, 117, 48);
		
		mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(this);
		mntmEliminar.setIcon(new ImageIcon(frmTrabajadores.class.getResource("/iconos/trash.png")));
		popupMenu.add(mntmEliminar);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(10, 249, 86, 14);
		contentPane.add(lblDni);
		
		JLabel lblApellidosYNombres = new JLabel("Apellidos y Nombres");
		lblApellidosYNombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidosYNombres.setBounds(10, 299, 141, 14);
		contentPane.add(lblApellidosYNombres);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(215, 255, 86, 14);
		contentPane.add(lblCargo);
		
		txtDni = new JTextField();
		txtDni.setBounds(10, 268, 86, 25);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtApeNom = new JTextField();
		txtApeNom.setBounds(10, 323, 183, 25);
		contentPane.add(txtApeNom);
		txtApeNom.setColumns(10);
		
		cboCargo = new JComboBoxBD("cargoTrabajador","TB_Trabajadores");
		cboCargo.setBounds(215, 280, 141, 25);
		contentPane.add(cboCargo);
		
		JLabel lblFecha = new JLabel("Sueldo");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(10, 354, 86, 14);
		contentPane.add(lblFecha);
		
		txtSueldo = new JTextField();
		txtSueldo.setBounds(10, 379, 86, 25);
		contentPane.add(txtSueldo);
		txtSueldo.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaDeNacimiento.setBounds(215, 318, 159, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		txtFecNac = new JTextField();
		txtFecNac.setBounds(215, 343, 141, 25);
		contentPane.add(txtFecNac);
		txtFecNac.setColumns(10);
		
		btnAñdir = new JButton("A\u00F1adir");
		btnAñdir.addActionListener(this);
		btnAñdir.setBounds(390, 249, 106, 35);
		contentPane.add(btnAñdir);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(390, 291, 106, 35);
		contentPane.add(btnActualizar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmTrabajadores.class.getResource("/iconos/search.png")));
		lblNewLabel.setBounds(23, 11, 41, 26);
		contentPane.add(lblNewLabel);
		
		txtBuscarTranajador = new JTextField();
		txtBuscarTranajador.setBounds(61, 11, 120, 26);
		contentPane.add(txtBuscarTranajador);
		txtBuscarTranajador.setColumns(10);
		
		listar();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmEliminar) {
			actionPerformedMntmEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnAñdir) {
			actionPerformedBtnAñdir(e);
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblTrabajadores) {
			mouseClickedTblTrabajadores(e);
		}
	}
	protected void actionPerformedBtnAñdir(ActionEvent e) {
		String dni,nomape,cargo,fecnac,sueldo;
		dni = txtDni.getText();
		nomape = txtApeNom.getText();
		cargo = cboCargo.getSelectedItem().toString();
		fecnac = txtFecNac.getText();
		sueldo = txtSueldo.getText();
		//Validacion
		if(txtDni.equals(""))
			Mensajes.dialogo("Campo DNI es obligatorio");
			
		Trabajador tra = new Trabajador();
		tra.setDni(Integer.parseInt(dni));
		tra.setNomApe(nomape);
		tra.setCargo(cargo);
		tra.setFecNac(fecnac);
		tra.setSueldo(Double.parseDouble(sueldo));
		int salida = trabajadorDAO.Ingresar(tra);
		if(salida > 0) {
			Mensajes.dialogo("El registro fue un exito");
			listar();
		}else {
			Mensajes.error("No se hizo el registro correctamente");
		}
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		String dni,nomape,cargo,fecnac,sueldo;
		dni = txtDni.getText();
		nomape = txtApeNom.getText();
		cargo = cboCargo.getSelectedItem().toString();
		fecnac = txtFecNac.getText();
		sueldo = txtSueldo.getText();
		//Validacion
		if(txtDni.equals(""))
			Mensajes.dialogo("Campo DNI es obligatorio");
			
		Trabajador tra = new Trabajador();
		tra.setDni(Integer.parseInt(dni));
		tra.setNomApe(nomape);
		tra.setCargo(cargo);
		tra.setFecNac(fecnac);
		tra.setSueldo(Double.parseDouble(sueldo));
		int salida = trabajadorDAO.Actualizar(tra);
		if(salida > 0) {
			Mensajes.dialogo("La actualizacion fue un exito");
			listar();
		}else {
			Mensajes.error("No se pudo actualizar");
		}
	}
	void listar() {
		DefaultTableModel modelo = (DefaultTableModel) tblTrabajadores.getModel();
		modelo.setRowCount(0);
		ArrayList<Trabajador> data = trabajadorDAO.ListarTodo();
		for(Trabajador tra:data) {
			Object[] filas= {tra.getDni(),tra.getNomApe(),tra.getCargo(),tra.getFecNac(),tra.getSueldo()};
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
	protected void mouseClickedTblTrabajadores(MouseEvent e) {
		//variables
		int posFila;
		if((posFila = tblTrabajadores.getSelectedRow()) < 0) {
			posFila = 0;
		}else {
				String dni,nomape,cargo,fecnac,sueldo;
				//obtener posicion de la fila seleccionada en la tabla "tblLibros"
				posFila = tblTrabajadores.getSelectedRow();
				//obetener valores de la fila según la variable "posFila"
				//metodo getValueAt(fila,columna) retorna un valor (Object) según la interseccion fila y columna
				dni =  tblTrabajadores.getValueAt(posFila, 0).toString(); // 0 --- columna codigo
				nomape =  tblTrabajadores.getValueAt(posFila, 1).toString();
				cargo =  tblTrabajadores.getValueAt(posFila, 2).toString();
				fecnac =  tblTrabajadores.getValueAt(posFila, 3).toString();
				sueldo =  tblTrabajadores.getValueAt(posFila, 4).toString();
				//mostrar variables en las cajas
				txtDni.setText(dni);
				txtApeNom.setText(nomape);
				cboCargo.setSelectedItem(cargo);
				txtFecNac.setText(fecnac);
				txtSueldo.setText(sueldo);
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
	protected void actionPerformedMntmEliminar(ActionEvent e) {
		String dni = txtDni.getText();
		//validacion
		Trabajador tra = new Trabajador();
		tra.setDni(Integer.parseInt(dni));
		int salida = trabajadorDAO.Eliminar(tra);
		if(salida > 0) {
			Mensajes.dialogo("Se elimino el registro");
			listar();
		}else{
			Mensajes.error("No se pudo eliminar");
		}
	}
}
