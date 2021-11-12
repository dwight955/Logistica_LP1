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
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Canvas;
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
	private JTextField txtSueldo;
	private JTextField txtBuscarTrabajador;
	private JTable tblTrabajadores;
	private JButton btnGuardar;
	private JComboBox cboCargo;
	private JButton btnActualizar;
	private JMenuItem mntmEliminar;
	private JComboBox cboSexo;
	private JComboBox cboDistrito;
	private JDateChooser dtFecNac;
	private JButton btnCancelar;

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
		
		setBounds(100, 100, 858, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 822, 191);
		contentPane.add(scrollPane);
		
		tblTrabajadores = new JTable();
		tblTrabajadores.setFillsViewportHeight(true);
		tblTrabajadores.addMouseListener(this);
		tblTrabajadores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Dni", "Apellidos y Nombres", "Cargo", "Fecha de Nacimiento", "Sueldo", "Sexo", "Distrito"
			}
		));
		tblTrabajadores.getColumnModel().getColumn(0).setPreferredWidth(39);
		tblTrabajadores.getColumnModel().getColumn(0).setMinWidth(11);
		tblTrabajadores.getColumnModel().getColumn(1).setPreferredWidth(138);
		tblTrabajadores.getColumnModel().getColumn(2).setPreferredWidth(102);
		tblTrabajadores.getColumnModel().getColumn(3).setPreferredWidth(60);
		tblTrabajadores.getColumnModel().getColumn(4).setPreferredWidth(17);
		tblTrabajadores.getColumnModel().getColumn(5).setPreferredWidth(21);
		tblTrabajadores.getColumnModel().getColumn(6).setPreferredWidth(93);
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
		lblCargo.setBounds(247, 250, 86, 14);
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
		
		cboCargo = new JComboBox();
		cboCargo.setEnabled(false);
		cboCargo.setBounds(247, 268, 183, 25);
		cboCargo.setModel(new DefaultComboBoxModel(new String[] {"SUB-ALMACENERO", "SECRETARIA", "ASISTENTE DE ALMACEN", "EMPAQUETADOR", "COORDINADOR","JUFA"}));
		contentPane.add(cboCargo);
		
		JLabel lblFecha = new JLabel("Sueldo");
		lblFecha.setBounds(10, 354, 86, 14);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblFecha);
		
		txtSueldo = new JTextField();
		txtSueldo.addKeyListener(this);
		txtSueldo.setBounds(10, 379, 86, 25);
		txtSueldo.setEditable(false);
		txtSueldo.setDisabledTextColor(Color.DARK_GRAY);
		contentPane.add(txtSueldo);
		txtSueldo.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(247, 300, 159, 14);
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblFechaDeNacimiento);
		
		btnGuardar = new JButton("Nuevo");
		btnGuardar.setBounds(726, 249, 106, 35);
		btnGuardar.addActionListener(this);
		contentPane.add(btnGuardar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(726, 298, 106, 35);
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
		lblSexo.setBounds(247, 356, 46, 14);
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblSexo);
		
		cboSexo = new JComboBox();
		cboSexo.setEnabled(false);
		cboSexo.setBounds(247, 380, 183, 25);
		cboSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		contentPane.add(cboSexo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 2, true));
		panel.setBounds(493, 250, 183, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		cboDistrito = new JComboBoxBD("concat_ws('/',codDis,nomDis)","TB_Distrito");
		cboDistrito.setEnabled(false);
		cboDistrito.setBounds(10, 36, 163, 27);
		panel.add(cboDistrito);
		
		JLabel lblDistrito = new JLabel("Distrito");
		lblDistrito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDistrito.setBounds(10, 11, 86, 14);
		panel.add(lblDistrito);
		
		Date dt = new Date();
		dtFecNac = new JDateChooser();
		dtFecNac.setDateFormatString("dd/MM/yyyy");
		dtFecNac.setDate(dt);
		dtFecNac.setEnabled(false);
		dtFecNac.setBounds(247, 323, 183, 25);
		contentPane.add(dtFecNac);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(726, 351, 106, 35);
		contentPane.add(btnCancelar);
		
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
		if (e.getSource() == txtSueldo) {
			keyTypedTxtSueldo(e);
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
			String dni,nomape,cargo,fecnac,sueldo,sexo,codDis;
			dni = txtDni.getText();
			nomape = txtApeNom.getText().toUpperCase();
			cargo = cboCargo.getSelectedItem().toString();
			sueldo = txtSueldo.getText();
			sexo = cboSexo.getSelectedItem().toString();
			ArrayList<Trabajador> listaTra = trabajadorDAO.ListarTodo();//luego lo eliminas
			//Validacion
			if(dni.equals("")) {
				Mensajes.dialogo("El campo DNI es obligatorio");
			}else if(nomape.equals("")) {
				Mensajes.dialogo("El campo APELLIDO Y NOMBRES es obligatorio");
			}else if(nomape.matches("^[A-Za-z\\s]{10,30}$")==false){
				Mensajes.dialogo("Nombre y Apellido: Minimo:3 Maximo: 30 LETRAS");
			}else if(sueldo.equals("")) {
				Mensajes.dialogo("El campo sueldo es obligatorio");	
			}else if(sueldo.matches("([2-9]\\d||[1-9]\\d\\d||[1][0][0])||([2-9]\\d[.]\\d{1,2}||[1-9]\\d\\d[.]\\d{1,2}||[1][0][0][.]\\d\\d)")==false) {
				Mensajes.dialogo("Sueldo: Min: 29.99 Max: 1999.99 ");	
			}else if(dtFecNac.getDate()==null) {
				Mensajes.dialogo("Ingrese fecha de Nacimiento");	
			}else {
				fecnac = fec.leerFecha(dtFecNac);
				Trabajador tra = new Trabajador();
				tra.setDni(Integer.parseInt(dni));
				tra.setNomApe(nomape);
				tra.setCargo(cargo);
				tra.setFecNac(fecnac);
				tra.setSueldo(Double.parseDouble(sueldo));
				tra.setSexo(sexo);
				codDis = cboDistrito.getSelectedItem().toString();
				String[] part = codDis.split("/");
				tra.setCodDis(part[0]);
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
		cargo = cboCargo.getSelectedItem().toString();
		sueldo = txtSueldo.getText();
		sexo = cboSexo.getSelectedItem().toString();
		codDistrito = cboDistrito.getSelectedItem().toString();
		//Validacion
		if(dni.equals("")) {
			Mensajes.dialogo("El campo DNI es obligatorio");
		}else if(nomape.equals("")) {
			Mensajes.dialogo("El campo APELLIDO Y NOMBRES es obligatorio");
		}else if(nomape.matches("^[A-Za-z\\s]{10,30}$")==false){
			Mensajes.dialogo("Nombre y Apellido: Minimo:3 Maximo: 30 LETRAS");
		}else if(sueldo.equals("")) {
			Mensajes.dialogo("El campo sueldo es obligatorio");	
		}else if(sueldo.matches("([2-9]\\d||[1-9]\\d\\d||[1][0][0])||([2-9]\\d[.]\\d{1,2}||[1-9]\\d\\d[.]\\d{1,2}||[1][0][0][.]\\d\\d)")==false) {
			Mensajes.dialogo("Sueldo Invalido");	
		}else if(dtFecNac.getDate()==null) {
			Mensajes.dialogo("Ingrese fecha de Nacimiento");	
		}else {
			fecnac = fec.leerFecha(dtFecNac);
			Trabajador tra = new Trabajador();
			tra.setDni(Integer.parseInt(dni));
			tra.setNomApe(nomape);
			tra.setCargo(cargo);
			tra.setFecNac(fecnac);
			tra.setSueldo(Double.parseDouble(sueldo));
			tra.setSexo(sexo);
			String[] sep = codDistrito.split("/");
			tra.setCodDis(sep[0]);
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
			Object[] filas= {tra.getDni(),tra.getNomApe(),tra.getCargo(),tra.getFecNac(),tra.getSueldo(),tra.getSexo(),tra.getCodDis()};
			modelo.addRow(filas);
		}
	}
	void Elementos(boolean op) {
		txtDni.setEditable(op);
		txtDni.requestFocus();
		txtApeNom.setEditable(op);
		txtSueldo.setEditable(op);
		cboCargo.setEnabled(op);
		cboSexo.setEnabled(op);
		cboDistrito.setEnabled(op);
		dtFecNac.setEnabled(op);
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
				btnActualizar.setEnabled(true);
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
				cboCargo.setSelectedItem(cargo);
				//dtFecNac.setDate(fecnac);;
				txtSueldo.setText(sueldo);
		}
	}
	void limpiar() {
		txtDni.setText("");
		txtDni.requestFocus();
		txtApeNom.setText("");
		txtSueldo.setText("");
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
	protected void keyTypedTxtSueldo(KeyEvent e) {
		char c = e.getKeyChar();
		String suel = txtSueldo.getText();
		if(!Character.isDigit(c) && c!='.') {
			e.consume();
		}else if(suel.length()==6) {
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
