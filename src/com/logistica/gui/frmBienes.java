package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import com.logistica.componentes.JComboBoxBD;
import com.logistica.controlador.MySqlBienesDAO;
import com.logistica.entidad.Bienes;
import com.logistica.entidad.Trabajador;
import com.toedter.calendar.JDateChooser;

import lib.Fecha;
import lib.Mensajes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JMenu;

public class frmBienes extends JFrame implements ActionListener, KeyListener, MouseListener {
	Fecha fec = new Fecha();
	MySqlBienesDAO bienesDAO = new MySqlBienesDAO();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBienes frame = new frmBienes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;
	private JTable tblBienes;
	private JComboBoxBD cboCategoria;
	private JTextField txtBuscarBien;
	private AbstractButton btnActualizar;
	private AbstractButton btnGuardar;
	private JTextComponent txtPrecioUnitario;
	private JComboBox cboUnidadMedida;
	private JTextComponent txtDescripcion;
	private JTextComponent txtCodigo;
	private AbstractButton mntmEliminar;
	private JTextField txtStock;
	private JButton btnCancelar;
	private JMenuItem mntmNewMenuItem;

	/**
	 * Create the frame.
	 */
	public frmBienes() {
		setTitle("Bienes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 792, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 755, 191);
		contentPane.add(scrollPane);
		
		tblBienes = new JTable();
		tblBienes.addMouseListener(this);
		tblBienes.setFillsViewportHeight(true);
		tblBienes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripcion", "Unidad de Medida", "Precio U.", "Categoria", "Stock ", "Fecha de Ingreso"
			}
		));
		tblBienes.getColumnModel().getColumn(0).setPreferredWidth(42);
		tblBienes.getColumnModel().getColumn(0).setMinWidth(11);
		tblBienes.getColumnModel().getColumn(1).setPreferredWidth(170);
		tblBienes.getColumnModel().getColumn(2).setPreferredWidth(88);
		tblBienes.getColumnModel().getColumn(3).setPreferredWidth(57);
		tblBienes.getColumnModel().getColumn(4).setPreferredWidth(111);
		tblBienes.getColumnModel().getColumn(5).setPreferredWidth(22);
		tblBienes.getColumnModel().getColumn(6).setPreferredWidth(93);
		scrollPane.setViewportView(tblBienes);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tblBienes, popupMenu);
		
		mntmNewMenuItem = new JMenuItem("Eliminar");
		mntmNewMenuItem.addActionListener(this);
		mntmNewMenuItem.setIcon(new ImageIcon(frmBienes.class.getResource("/iconos/trash.png")));
		popupMenu.add(mntmNewMenuItem);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 250, 86, 13);
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCodigo);
		
		JLabel lblApellidosYNombres = new JLabel("Descripcion:");
		lblApellidosYNombres.setBounds(10, 299, 141, 14);
		lblApellidosYNombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblApellidosYNombres);
		
		JLabel lblUnidadMed = new JLabel("Unidad de Medida");
		lblUnidadMed.setBounds(229, 250, 167, 14);
		lblUnidadMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblUnidadMed);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(10, 268, 86, 25);
		txtCodigo.setEnabled(false);
		txtCodigo.setDisabledTextColor(Color.DARK_GRAY);
		contentPane.add(txtCodigo);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(10, 323, 210, 25);
		txtDescripcion.setEnabled(false);
		txtDescripcion.setDisabledTextColor(Color.DARK_GRAY);
		contentPane.add(txtDescripcion);
		
		cboUnidadMedida = new JComboBox();
		cboUnidadMedida.setBounds(229, 268, 167, 25);
		cboUnidadMedida.setEnabled(false);
		cboUnidadMedida.setModel(new DefaultComboBoxModel(new String[] {"UNIDAD", "KILO", "LITRO", "METRO", "METRO CUADRADO"}));
		contentPane.add(cboUnidadMedida);
		
		JLabel lblFecha = new JLabel("Precio Unitario");
		lblFecha.setBounds(10, 354, 99, 14);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblFecha);
		
		txtPrecioUnitario = new JTextField();
		txtPrecioUnitario.addKeyListener(this);
		txtPrecioUnitario.setEnabled(false);
		txtPrecioUnitario.setBounds(10, 379, 54, 25);
		txtPrecioUnitario.setDisabledTextColor(Color.DARK_GRAY);
		contentPane.add(txtPrecioUnitario);
		
		btnGuardar = new JButton("Nuevo");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(633, 250, 106, 35);
		contentPane.add(btnGuardar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(633, 299, 106, 35);
		contentPane.add(btnActualizar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(23, 11, 41, 26);
		lblNewLabel.setIcon(new ImageIcon(frmTrabajadores.class.getResource("/iconos/search.png")));
		contentPane.add(lblNewLabel);
		
		txtBuscarBien = new JTextField();
		txtBuscarBien.setBounds(61, 11, 159, 26);
		txtBuscarBien.setEnabled(false);
		contentPane.add(txtBuscarBien);
		txtBuscarBien.setColumns(10);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(239, 299, 46, 14);
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblStock);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 2, true));
		panel.setBounds(419, 283, 183, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		cboCategoria = new JComboBoxBD("concat_ws(' - ',idCategoria,nomCategoria)","TB_Categorias");
		cboCategoria.setEnabled(false);
		cboCategoria.setBounds(10, 36, 163, 27);
		panel.add(cboCategoria);
		
		JLabel lblDistrito = new JLabel("Categoria");
		lblDistrito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDistrito.setBounds(10, 11, 86, 14);
		panel.add(lblDistrito);
		
		Date dt = new Date();
		
		txtStock = new JTextField();
		txtStock.addKeyListener(this);
		txtStock.setEnabled(false);
		txtStock.setBounds(239, 324, 63, 25);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(633, 352, 106, 35);
		contentPane.add(btnCancelar);
		
		listar();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmNewMenuItem) {
			actionPerformedMntmNewMenuItem(e);
		}
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtPrecioUnitario) {
			keyTypedTxtPrecioUnitario(e);
		}
		if (e.getSource() == txtStock) {
			keyTypedTxtStock(e);
		}
	}
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		if(txtCodigo.isEnabled() == false) {
			Elementos(true);
			btnGuardar.setText("Guardar");
		}else {
			String cod,desc,stock,prec,unidad,cate,fecIng;
			cod = txtCodigo.getText().toUpperCase();
			desc = txtDescripcion.getText().toUpperCase();
			stock = txtStock.getText();
			prec = txtPrecioUnitario.getText();
			unidad = cboUnidadMedida.getSelectedItem().toString();
			
			if(cod.equals("")) {
				Mensajes.dialogo("Es obligatorio el campo CODIGO");
			}else if(cod.matches("^[B][I][-][0-9][0-9]$") == false) {
				Mensajes.dialogo("Debe empezar con BI-[00 al 99]");
			}else if(desc.equals("")) {
				Mensajes.dialogo("Es obligatorio el campo DESCRIPCION");
			}else if(desc.matches("^[a-zA-Z0-9\\s//Ñ//ñ//á//é//í//ó//ú//Á//É//Í//Ó//Ú]{4,50}$") == false) {
				Mensajes.dialogo("Solo LETRAS - Minimo: 4 - Maximo: 50");
			}else if(stock.equals("")) {
				Mensajes.dialogo("El campo STOCK es obligatorio");
			}else if(prec.equals("")) {
				Mensajes.dialogo("Es obligatorio el campo PRECIO UNITARIO");
			}else if(prec.matches("([1-9]\\d||[1-9]\\d\\d||[1][0][0])||([1-9]\\d[.]\\d{1,2}||[1-9]\\d\\d[.]\\d{1,2}||[1][0][0][.]\\d\\d)")== false) {
				Mensajes.dialogo("El precio unitario es invalido || Min:10.00 Max: 999.99 ");
			}else {
				Bienes bien = new Bienes();
				bien.setCodBien(cod);
				bien.setDescBien(desc);
				bien.setStockDisponible(Integer.parseInt(stock));
				bien.setPrecUni(Double.parseDouble(prec));
				bien.setUniMed(unidad);
				cate =  cboCategoria.getSelectedItem().toString();
				String[] part = cate.split(" - ");
				bien.setCategoria(part[0]);
				int salida = bienesDAO.Ingresar(bien);
					if(salida > 0) {
						Mensajes.dialogo("El registro fue un exito");
						listar();
						Elementos(false);
						limpiar();
						btnGuardar.setText("Nuevo");
					}else {
						Mensajes.error("No se hizo el registro correctamente");
					}
			}
		}
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
			String cod,desc,stock,prec,unidad,cate,fecIng;
			cod = txtCodigo.getText().toUpperCase();
			desc = txtDescripcion.getText().toUpperCase();
			stock = txtStock.getText();
			prec = txtPrecioUnitario.getText();
			unidad = cboUnidadMedida.getSelectedItem().toString();
			
			if(cod.equals("")) {
				Mensajes.dialogo("Es obligatorio el campo CODIGO");
			}else if(cod.matches("^[B][I][-][0-9][0-9]$") == false) {
				Mensajes.dialogo("Debe empezar con BI-[00 al 99]");
			}else if(desc.equals("")) {
				Mensajes.dialogo("Es obligatorio el campo DESCRIPCION");
			}else if(desc.matches("^[a-zA-Z0-9\\s//Ñ//ñ//á//é//í//ó//ú//Á//É//Í//Ó//Ú]{4,50}$") == false) {
				Mensajes.dialogo("Solo LETRAS - Minimo: 4 - Maximo: 50");
			}else if(stock.equals("")) {
				Mensajes.dialogo("El campo STOCK es obligatorio");
			}else if(prec.equals("")) {
				Mensajes.dialogo("Es obligatorio el campo PRECIO UNITARIO");
			}else if(prec.matches("([1-9]\\d||[1-9]\\d\\d||[1][0][0])||([1-9]\\d[.]\\d{1,2}||[1-9]\\d\\d[.]\\d{1,2}||[1][0][0][.]\\d\\d)")== false) {
				Mensajes.dialogo("El precio unitario es invalido || Min:10.00 Max: 999.99 ");
			}else {
				Bienes bien = new Bienes();
				bien.setCodBien(cod);
				bien.setDescBien(desc);
				bien.setStockDisponible(Integer.parseInt(stock));
				bien.setPrecUni(Double.parseDouble(prec));
				bien.setUniMed(unidad);
				cate =  cboCategoria.getSelectedItem().toString();
				String[] part = cate.split(" - ");
				bien.setCategoria(part[0]);
				int salida = bienesDAO.Actualizar(bien);
					if(salida > 0) {
						Mensajes.dialogo("La actualizacion fue un exito");
						listar();
						Elementos(false);
						btnActualizar.setEnabled(false);
						btnGuardar.setEnabled(true);
						btnGuardar.setText("Nuevo");
						limpiar();
					}else {
						Mensajes.error("No se Actualizo correctamente");
					}
			}
	}
	void listar() {
		DefaultTableModel modelo = (DefaultTableModel) tblBienes.getModel();
		modelo.setRowCount(0);
		ArrayList<Bienes> data = bienesDAO.ListarTodo();
		for(Bienes bien:data) {
			Object[] filas = {bien.getCodBien(),bien.getDescBien(),bien.getUniMed(),bien.getPrecUni(),bien.getCategoria(),bien.getStockDisponible(),bien.getFecIngreso()};
			modelo.addRow(filas);
		}
	}
	void limpiar() {
		txtCodigo.setText("");
		txtDescripcion.setText("");
		txtPrecioUnitario.setText("");
		txtStock.setText("");
	}
	void Elementos(boolean op) {
		txtCodigo.setEnabled(op);
		txtCodigo.requestFocus();
		txtDescripcion.setEnabled(op);
		txtPrecioUnitario.setEnabled(op);
		txtStock.setEnabled(op);
		cboCategoria.setEnabled(op);
		cboUnidadMedida.setEnabled(op);
	}
	
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	
	protected void keyTypedTxtStock(KeyEvent e) {
		char c = e.getKeyChar();
		String stck = txtStock.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		}else if(stck.length()==3) {
			e.consume();
		}
	}
	protected void keyTypedTxtPrecioUnitario(KeyEvent e) {
		char c = e.getKeyChar();
		String preUni = txtPrecioUnitario.getText();
		if(!Character.isDigit(c) && c!='.') {
			e.consume();
		}else if(preUni.length()==6) {
			e.consume();
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblBienes) {
			mouseClickedTblBienes(e);
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
	protected void mouseClickedTblBienes(MouseEvent e) {
		int posFila;
		if((posFila = tblBienes.getSelectedRow()) < 0) {
			posFila = 0;	
		}else {
			Elementos(true);
			txtCodigo.setEnabled(false);
			btnActualizar.setEnabled(true);
			btnGuardar.setEnabled(false);
			String cod,desc,pre,stck,unidad,cate;
			posFila = tblBienes.getSelectedRow();
			cod = tblBienes.getValueAt(posFila, 0).toString();
			desc = tblBienes.getValueAt(posFila, 1).toString();
			unidad = tblBienes.getValueAt(posFila, 2).toString();
			pre = tblBienes.getValueAt(posFila, 3).toString();
			cate = tblBienes.getValueAt(posFila, 4).toString();
			stck = tblBienes.getValueAt(posFila, 5).toString();
			
			txtCodigo.setText(cod);
			txtDescripcion.setText(desc);
			txtPrecioUnitario.setText(pre);
			txtStock.setText(stck);
			cboCategoria.setSelectedItem(cate);
			cboUnidadMedida.setSelectedItem(unidad);
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
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		Elementos(false);
		limpiar();
		btnActualizar.setEnabled(false);
		btnGuardar.setEnabled(true);
		btnGuardar.setText("Nuevo");
	}
	protected void actionPerformedMntmNewMenuItem(ActionEvent e) {
		String cod = txtCodigo.getText();
		//validacion
		Bienes bien = new Bienes();
		bien.setCodBien(cod);
		int m = Mensajes.confirmarELiminar();
		if( m == 0) {
			Mensajes.dialogo("Se elimino el registro");
			bienesDAO.Eliminar(bien);
			listar();
			limpiar();
		}
	}
}
