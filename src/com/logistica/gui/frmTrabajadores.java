package com.logistica.gui;


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

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.logistica.componentes.JComboBoxBD;
import com.logistica.controlador.MySqlTrabajadorDAO;
import com.logistica.entidad.Trabajador;

import lib.Fecha;
import lib.Mensajes;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.JMenuItem;


import java.awt.Color;

import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class frmTrabajadores extends JFrame implements ActionListener, MouseListener, KeyListener {
	MySqlTrabajadorDAO trabajadorDAO = new MySqlTrabajadorDAO();
	Fecha fec = new Fecha();
	
	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtApeNom;
	private JTextField txtBuscarTrabajador;
	private JTable tblTrabajadores;
	private JButton btnGuardar;
	public JButton btnActualizar;
	public JMenuItem mntmEliminar;
	private JComboBox cboSexo;
	private JComboBox cboUnidadOrganica;
	private JButton btnCancelar;
	private JTextField txtCargo;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 728, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 682, 191);
		contentPane.add(scrollPane);
		
		tblTrabajadores = new JTable();
		tblTrabajadores.setFillsViewportHeight(true);
		tblTrabajadores.addMouseListener(this);
		tblTrabajadores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Dni", "Apellidos y Nombres", "Cargo", "Sexo", "Unidad Organica"
			}
		));
		tblTrabajadores.getColumnModel().getColumn(0).setPreferredWidth(39);
		tblTrabajadores.getColumnModel().getColumn(0).setMinWidth(11);
		tblTrabajadores.getColumnModel().getColumn(1).setPreferredWidth(138);
		tblTrabajadores.getColumnModel().getColumn(2).setPreferredWidth(102);
		tblTrabajadores.getColumnModel().getColumn(3).setPreferredWidth(21);
		scrollPane.setViewportView(tblTrabajadores);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tblTrabajadores, popupMenu);
		popupMenu.setBounds(0, 0, 117, 48);
		
		mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(this);
		mntmEliminar.setIcon(new ImageIcon(frmTrabajadores.class.getResource("/iconos/trash.png")));
		popupMenu.add(mntmEliminar);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 249, 86, 14);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblDni);
		
		JLabel lblApellidosYNombres = new JLabel("Apellidos y Nombres");
		lblApellidosYNombres.setBounds(10, 299, 141, 14);
		lblApellidosYNombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblApellidosYNombres);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(281, 250, 86, 25);
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCargo);
		
		txtDni = new JTextField();
		txtDni.addKeyListener(this);
		txtDni.setBounds(10, 268, 86, 25);
		txtDni.setEditable(false);
		txtDni.setDisabledTextColor(Color.DARK_GRAY);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtApeNom = new JTextField();
		txtApeNom.addKeyListener(this);
		txtApeNom.setBounds(10, 323, 183, 25);
		txtApeNom.setEditable(false);
		txtApeNom.setDisabledTextColor(Color.DARK_GRAY);
		contentPane.add(txtApeNom);
		txtApeNom.setColumns(10);
		
		btnGuardar = new JButton("Nuevo");
		btnGuardar.setBounds(586, 260, 106, 35);
		btnGuardar.addActionListener(this);
		contentPane.add(btnGuardar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(586, 309, 106, 35);
		btnActualizar.setEnabled(false);
		btnActualizar.addActionListener(this);
		contentPane.add(btnActualizar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(23, 11, 41, 26);
		lblNewLabel.setIcon(new ImageIcon(frmTrabajadores.class.getResource("/iconos/search.png")));
		contentPane.add(lblNewLabel);
		
		txtBuscarTrabajador = new JTextField();
		txtBuscarTrabajador.setBounds(61, 11, 159, 26);
		contentPane.add(txtBuscarTrabajador);
		txtBuscarTrabajador.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(10, 359, 46, 14);
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblSexo);
		
		cboSexo = new JComboBox();
		cboSexo.setEnabled(false);
		cboSexo.setBounds(10, 383, 183, 25);
		cboSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		contentPane.add(cboSexo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 2, true));
		panel.setBounds(281, 323, 183, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		cboUnidadOrganica = new JComboBoxBD("concat_ws('-',codUniOrg,nomUniOrg)","tb_unidadorganica");
		cboUnidadOrganica.setEnabled(false);
		cboUnidadOrganica.setBounds(10, 36, 163, 27);
		panel.add(cboUnidadOrganica);
		
		JLabel lblDistrito = new JLabel("Unidad Organica");
		lblDistrito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDistrito.setBounds(10, 11, 122, 14);
		panel.add(lblDistrito);
		
		Date dt = new Date();
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(586, 362, 106, 35);
		contentPane.add(btnCancelar);
		
		txtCargo = new JTextField();
		txtCargo.setEditable(false);
		txtCargo.setBounds(281, 281, 183, 25);
		contentPane.add(txtCargo);
		txtCargo.setColumns(10);
		
		listar();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == mntmEliminar) {
			actionPerformedMntmEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnAñdir(e);
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblTrabajadores) {
			mouseClickedTblTrabajadores(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtApeNom) {
			keyTypedTxtApeNom(e);
		}
		if (e.getSource() == txtDni) {
			keyTypedTxtDni(e);
		}
	}
	protected void actionPerformedBtnAñdir(ActionEvent e) {
		
		if(txtDni.isEditable()==false) {
			Elementos(true);
			btnGuardar.setText("Guardar");
		}else {
			String dni,nomape,cargo,sexo,codUniOrg;
			dni = txtDni.getText();
			nomape = txtApeNom.getText().toUpperCase();
			cargo = txtCargo.getText();
			sexo = cboSexo.getSelectedItem().toString();
			
			ArrayList<Trabajador> listaTra = trabajadorDAO.ListarTodo();//luego lo eliminas
			//Validacion
			if(dni.equals("")) {
				Mensajes.dialogo("El campo DNI es obligatorio");
			}else if(nomape.equals("")) {
				Mensajes.dialogo("El campo APELLIDO Y NOMBRES es obligatorio");
			}else if(nomape.matches("^[A-Za-z\\s]{10,30}$")==false){
				Mensajes.dialogo("Nombre y Apellido: Minimo:3 Maximo: 30 LETRAS");
			}else {
				Trabajador tra = new Trabajador();
				tra.setDni(Integer.parseInt(dni));
				tra.setNomApe(nomape);
				tra.setCargo(cargo);
				tra.setSexo(sexo);
				codUniOrg = cboUnidadOrganica.getSelectedItem().toString();
				String[] part = codUniOrg.split("-");
				tra.setCodUnidadOrga(part[0]);
				int salida = trabajadorDAO.Ingresar(tra);
					if(salida > 0) {
						Mensajes.dialogo("El registro fue un exito");
						listar();
						Elementos(false);
						limpiar();
					}else {
						Mensajes.error("No se hizo el registro correctamente");
					}
				}
				
		    }
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		String dni,nomape,cargo,fecnac,sueldo,sexo,codDistrito;
		dni = txtDni.getText();
		nomape = txtApeNom.getText().toUpperCase();
		sexo = cboSexo.getSelectedItem().toString();
		codDistrito = cboUnidadOrganica.getSelectedItem().toString();
		//Validacion
		if(dni.equals("")) {
			Mensajes.dialogo("El campo DNI es obligatorio");
		}else if(nomape.equals("")) {
			Mensajes.dialogo("El campo APELLIDO Y NOMBRES es obligatorio");
		}else if(nomape.matches("^[A-Za-z\\s]{10,30}$")==false){
			Mensajes.dialogo("Nombre y Apellido: Minimo:3 Maximo: 30 LETRAS");
		}else {
			Trabajador tra = new Trabajador();
			tra.setDni(Integer.parseInt(dni));
			tra.setNomApe(nomape);
			tra.setSexo(sexo);
			
			String[] sep = codDistrito.split("/");
			tra.setCodUnidadOrga(sep[0]);
			
			int salida = trabajadorDAO.Actualizar(tra);
			if(salida > 0) {
				Mensajes.dialogo("La actualizacion fue un exito");
				listar();
				Elementos(false);
				btnActualizar.setEnabled(false);
				btnGuardar.setEnabled(true);
				btnGuardar.setText("Nuevo");
				limpiar();
			}else {
				Mensajes.error("No se pudo actualizar");
		}
	}
}
	protected void actionPerformedMntmEliminar(ActionEvent e) {
		String dni = txtDni.getText();
		//validacion
		Trabajador tra = new Trabajador();
		tra.setDni(Integer.parseInt(dni));
		int m = Mensajes.confirmarELiminar();
		if( m == 0) {
			Mensajes.dialogo("Se elimino el registro");
			trabajadorDAO.Eliminar(tra);
			listar();
			limpiar();
			Elementos(false);
			btnGuardar.setEnabled(true);
		}
	}
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		Elementos(false);
		limpiar();
		btnGuardar.setEnabled(true);
		btnGuardar.setText("Nuevo");
		btnActualizar.setEnabled(false);
	}
	public void listar() {
		DefaultTableModel modelo = (DefaultTableModel) tblTrabajadores.getModel();
		modelo.setRowCount(0);
		ArrayList<Trabajador> data = trabajadorDAO.ListarTodo();
		for(Trabajador tra:data) {
			Object[] filas= {tra.getDni(),tra.getNomApe(),tra.getCargo(),tra.getSexo(),tra.getCodUnidadOrga()};
			modelo.addRow(filas);
		}
	}
	void Elementos(boolean op) {
		txtDni.setEditable(op);
		txtDni.requestFocus();
		txtApeNom.setEditable(op);
		txtCargo.setEditable(op);
		cboSexo.setEnabled(op);
		cboUnidadOrganica.setEnabled(op);
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
		SimpleDateFormat df =new SimpleDateFormat("dd/MM/yyyy");
		if((posFila = tblTrabajadores.getSelectedRow()) < 0) {
			posFila = 0;
		}else {
				Elementos(true);
				txtDni.setEditable(false);
				if(mntmEliminar.isEnabled())btnActualizar.setEnabled(true);
				btnGuardar.setEnabled(false);
				String dni,nomape,cargo,sueldo;
				Date fecnac;
				//obtener posicion de la fila seleccionada en la tabla "tblLibros"
				posFila = tblTrabajadores.getSelectedRow();
				//obetener valores de la fila según la variable "posFila"
				//metodo getValueAt(fila,columna) retorna un valor (Object) según la interseccion fila y columna
				dni =  tblTrabajadores.getValueAt(posFila, 0).toString(); // 0 --- columna codigo
				nomape =  tblTrabajadores.getValueAt(posFila, 1).toString();
				cargo =  tblTrabajadores.getValueAt(posFila, 2).toString();
				sueldo =  tblTrabajadores.getValueAt(posFila, 4).toString();
				//mostrar variables en las cajas
				txtDni.setText(dni);
				txtApeNom.setText(nomape);
				//dtFecNac.setDate(fecnac);;
		}
	}
	void limpiar() {
		txtDni.setText("");
		txtDni.requestFocus();
		txtCargo.setText("");
		txtApeNom.setText("");
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
	protected void keyTypedTxtDni(KeyEvent e) {
		char c = e.getKeyChar();
		String dni = txtDni.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(dni.length()==8) {
			e.consume();
		}
	}
	protected void keyTypedTxtApeNom(KeyEvent e) {
		char c = e.getKeyChar();
		String apeNom = txtApeNom.getText();
		if(!Character.isAlphabetic(c) && c != ' ') {
			e.consume();
		} else if(apeNom.length()==30) {
			e.consume();
		}
	}
}
