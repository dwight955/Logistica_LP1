package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.logistica.componentes.JTextFielBD;
import com.logistica.controlador.MySqlBienesDAO;
import com.logistica.controlador.MySqlCuadroRequerimientosDAO;
import com.logistica.controlador.MySqlPecosaDAO;
import com.logistica.controlador.MySqlTrabajadorDAO;
import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.entidad.DetalleRequerimientos;
import com.logistica.entidad.UnidadOrganica;
import com.logistica.interfaces.CuadroRequerimientosDAO;
import com.logistica.utils.Libreria;

import lib.Mensajes;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class frmCuadroRequerimientos extends JFrame implements ActionListener, KeyListener {
	private static final DefaultTableModel DefaultTableModel = null;
	MySqlCuadroRequerimientosDAO reqDAO = new MySqlCuadroRequerimientosDAO();
	MySqlPecosaDAO pecosaDAO = new MySqlPecosaDAO();
	MySqlBienesDAO bienDAO = new MySqlBienesDAO();
	
	
	private JPanel contentPane;
	public static JTextField txtDeUnidadOrg;
	public static JTextField txtParaUnidadOrg;
	private JTextField txtFechaEmi;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblCodigo;
	public static JTextField txtCodBien;
	private JLabel lblDescripcion;
	public static JTextField txtDescPro;
	public static JTextField txtCant;
	public static JTextField txtUnidaMed;
	private JLabel lblCuadroDeRequerimientos;
	private JTable tblRequerimientos;
	public static JTextField txtNombreApeSoli;
	public static JTextField txtdniSoli;
	private JTextField txtNumReq;
	public static JTextField txtdniEntr;
	public static JTextField txtNombreEntr;
	private JTextField txtEstado;
	private JButton btnNuevo;
	private JButton btnBuscarEntrDni;
	private JButton btnBuscarBien;
	private JButton btnAgregarBien;
	private JPopupMenu popupMenu;
	private JMenuItem mntmEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCuadroRequerimientos frame = new frmCuadroRequerimientos();
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
	public frmCuadroRequerimientos() {
		setTitle("Formulacion del Cuadro de Requerimientos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 807, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cabecera", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 95, 771, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPara = new JLabel("Para: ");
		lblPara.setBounds(390, 50, 55, 17);
		panel.add(lblPara);
		lblPara.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtParaUnidadOrg = new JTextField();
		txtParaUnidadOrg.setEditable(false);
		txtParaUnidadOrg.setBounds(439, 50, 322, 20);
		panel.add(txtParaUnidadOrg);
		txtParaUnidadOrg.setColumns(10);
		
		JLabel lblDe = new JLabel("De: ");
		lblDe.setBounds(22, 53, 55, 17);
		panel.add(lblDe);
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtDeUnidadOrg = new JTextField();
		txtDeUnidadOrg.setEditable(false);
		txtDeUnidadOrg.setBounds(71, 53, 298, 20);
		panel.add(txtDeUnidadOrg);
		txtDeUnidadOrg.setColumns(10);
		
		JLabel lblDni = new JLabel("Solicitante: ");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(22, 24, 86, 17);
		panel.add(lblDni);
		
		txtNombreApeSoli = new JTextField();
		txtNombreApeSoli.setEditable(false);
		txtNombreApeSoli.setColumns(10);
		txtNombreApeSoli.setBounds(204, 23, 165, 20);
		panel.add(txtNombreApeSoli);
		
		txtdniSoli = new JTextField();
		txtdniSoli.setEditable(false);
		txtdniSoli.setBackground(SystemColor.info);
		txtdniSoli.setColumns(10);
		txtdniSoli.setBounds(92, 23, 102, 20);
		panel.add(txtdniSoli);
		
		JLabel lblEntregarA = new JLabel("Entregar A: ");
		lblEntregarA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEntregarA.setBounds(390, 24, 86, 17);
		panel.add(lblEntregarA);
		
		txtdniEntr = new JTextFielBD("dniTrabajador","tb_trabajadores");
		txtdniEntr.setEnabled(false);
		txtdniEntr.addKeyListener(this);
		txtdniEntr.setColumns(10);
		txtdniEntr.setBackground(SystemColor.info);
		txtdniEntr.setBounds(475, 24, 73, 20);
		panel.add(txtdniEntr);
		
		txtNombreEntr = new JTextField();
		txtNombreEntr.setEditable(false);
		txtNombreEntr.setColumns(10);
		txtNombreEntr.setBounds(589, 24, 172, 20);
		panel.add(txtNombreEntr);
		
		btnBuscarEntrDni = new JButton("");
		btnBuscarEntrDni.addActionListener(this);
		btnBuscarEntrDni.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/search.png")));
		btnBuscarEntrDni.setBounds(553, 21, 33, 27);
		panel.add(btnBuscarEntrDni);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Datos del Bien o Servicio", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 206, 771, 124);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(10, 41, 55, 17);
		panel_1.add(lblCodigo);
		
		txtCodBien = new JTextFielBD("codBien","tb_bienes");
		txtCodBien.addKeyListener(this);
		txtCodBien.setEnabled(false);
		txtCodBien.setColumns(10);
		txtCodBien.setBounds(70, 41, 63, 20);
		panel_1.add(txtCodBien);
		
		lblDescripcion = new JLabel("Descripcion: ");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(-188, 40, 97, 17);
		panel_1.add(lblDescripcion);
		
		txtDescPro = new JTextField();
		txtDescPro.setEditable(false);
		txtDescPro.setEnabled(false);
		txtDescPro.setColumns(10);
		txtDescPro.setBounds(93, 80, 262, 20);
		panel_1.add(txtDescPro);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidad.setBounds(167, 40, 77, 17);
		panel_1.add(lblCantidad);
		
		txtCant = new JTextField();
		txtCant.setEnabled(false);
		txtCant.setColumns(10);
		txtCant.setBounds(227, 38, 55, 20);
		panel_1.add(txtCant);
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de Medida:");
		lblUnidadDeMedida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnidadDeMedida.setBounds(384, 82, 129, 17);
		panel_1.add(lblUnidadDeMedida);
		
		txtUnidaMed = new JTextField();
		txtUnidaMed.setEditable(false);
		txtUnidaMed.setEnabled(false);
		txtUnidaMed.setColumns(10);
		txtUnidaMed.setBounds(508, 80, 136, 20);
		panel_1.add(txtUnidaMed);
		
		btnBuscarBien = new JButton("");
		btnBuscarBien.addActionListener(this);
		btnBuscarBien.setEnabled(false);
		btnBuscarBien.setBounds(310, 28, 88, 41);
		panel_1.add(btnBuscarBien);
		btnBuscarBien.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/search.png")));
		
		btnAgregarBien = new JButton("");
		btnAgregarBien.addActionListener(this);
		btnAgregarBien.setEnabled(false);
		btnAgregarBien.setBounds(424, 28, 82, 41);
		panel_1.add(btnAgregarBien);
		btnAgregarBien.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/add.png")));
		
		JLabel lblDescripcion_1 = new JLabel("Descripcion:");
		lblDescripcion_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion_1.setBounds(10, 83, 97, 14);
		panel_1.add(lblDescripcion_1);
		
		lblCuadroDeRequerimientos = new JLabel("Cuadro de Requerimientos");
		lblCuadroDeRequerimientos.setFont(new Font("Swis721 LtEx BT", Font.BOLD, 18));
		lblCuadroDeRequerimientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuadroDeRequerimientos.setBounds(239, 11, 274, 38);
		contentPane.add(lblCuadroDeRequerimientos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9, 341, 772, 258);
		contentPane.add(scrollPane);
		
		tblRequerimientos = new JTable();
		tblRequerimientos.setFillsViewportHeight(true);
		tblRequerimientos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "DESCRIPCION", "UNIDAD DE MEDIDA", "CANTIDAD"
			}
		));
		tblRequerimientos.getColumnModel().getColumn(0).setPreferredWidth(15);
		tblRequerimientos.getColumnModel().getColumn(0).setMinWidth(7);
		scrollPane.setViewportView(tblRequerimientos);
		
		popupMenu = new JPopupMenu();
		addPopup(tblRequerimientos, popupMenu);
		
		mntmEliminar = new JMenuItem("ELIMINAR");
		mntmEliminar.addActionListener(this);
		mntmEliminar.setIcon(new ImageIcon(frmCuadroRequerimientos.class.getResource("/iconos/cerrar.png")));
		popupMenu.add(mntmEliminar);
		
		JLabel lblNumRequerimiento = new JLabel("N\u00BA Requerimiento:");
		lblNumRequerimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumRequerimiento.setBounds(13, 57, 124, 27);
		contentPane.add(lblNumRequerimiento);
		
		txtNumReq = new JTextField();
		txtNumReq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNumReq.setEditable(false);
		txtNumReq.setBounds(131, 62, 79, 22);
		contentPane.add(txtNumReq);
		txtNumReq.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGuardar(e);
			}
		});
		btnCancelar.setBounds(688, 610, 93, 32);
		contentPane.add(btnCancelar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(577, 610, 89, 32);
		contentPane.add(btnNuevo);
		
		JLabel lblFecha = new JLabel("Fecha Emision: ");
		lblFecha.setBounds(220, 62, 108, 22);
		contentPane.add(lblFecha);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtFechaEmi = new JTextField();
		txtFechaEmi.setEditable(false);
		txtFechaEmi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFechaEmi.setText(lib.Fecha.fechaActual());
		txtFechaEmi.setBounds(319, 62, 81, 22);
		contentPane.add(txtFechaEmi);
		txtFechaEmi.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstado.setBounds(410, 62, 59, 22);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(463, 62, 108, 22);
		contentPane.add(txtEstado);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmEliminar) {
			actionPerformedMntmEliminar(e);
		}
		if (e.getSource() == btnAgregarBien) {
			actionPerformedBtnAgregarBien(e);
		}
		if (e.getSource() == btnBuscarBien) {
			actionPerformedBtnBuscarBien(e);
		}
		if (e.getSource() == btnBuscarEntrDni) {
			actionPerformedBtnBuscarEntrDni(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtCodBien) {
			keyReleasedTxtCodBien(e);
		}
		if (e.getSource() == txtdniEntr) {
			keyReleasedTxtdniEntr(e);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		
		frmLogin frm= new frmLogin();
		
		if(txtdniEntr.isEnabled()==false) {
			btnNuevo.setText("Enviar");
			componentes(true);
			txtNombreApeSoli.setText(frm.apenom); 
			txtdniSoli.setText(frm.dni);
			txtDeUnidadOrg.setText(frm.unidad);
			txtNumReq.setText(codigoCorrelativo());
			txtEstado.setText("EN REVISION");
		}else {
			try {
				CuadroRequerimientos cure = new CuadroRequerimientos();
				
				cure.setNumreq(txtNumReq.getText());
				cure.setFechaEmi(txtFechaEmi.getText());
				cure.setEstado(txtEstado.getText());
				cure.setDniSoli(Libreria.codigoTrabajadorSesion);
				cure.setDniEntr(Integer.parseInt(txtdniEntr.getText()));
				
				ArrayList<DetalleRequerimientos> data = new ArrayList<DetalleRequerimientos>();
				
				for(int i = 0; i<tblRequerimientos.getRowCount(); i++) {
					DetalleRequerimientos dr = new DetalleRequerimientos();
					
					String codBien, cant;
					
					codBien = tblRequerimientos.getValueAt(i, 0).toString();
					cant = tblRequerimientos.getValueAt(i, 3).toString();
					
					dr.setCodBien(codBien);
					dr.setCant(Integer.parseInt(cant));			
														
				}
				
				
				reqDAO.registrar(cure, data);				
				Mensajes.dialogo("Su Cuadro de Requerimiento a sido enviado");				
				btnNuevo.setText("Nuevo");
				
				borrar();
				
			} catch (Exception e2) {
				Mensajes.error("No se logro registrar \n");
				
			}
			
		}
	}
	
	
	
	private String codigoCorrelativo() {
		ArrayList<CuadroRequerimientos> data = reqDAO.listarTodo();
		int num = Integer.parseInt(data.get(data.size()-1).getNumreq()) + 1;
		String codigoSerial;
		if(num==0) {
			codigoSerial = "000001";
		}else {
			codigoSerial = String.format("%06d", num);
		}
		return codigoSerial;			
	}
	
	
	protected void actionPerformedBtnBuscarEntrDni(ActionEvent e) {
		dlgBuscarEntrTrabajador frm = new dlgBuscarEntrTrabajador();
		frm.setVisible(true);
		frm.setLocationRelativeTo(null);
	}
	protected void keyReleasedTxtdniEntr(KeyEvent e) {
		String dni = txtdniEntr.getText();
		String[] data = pecosaDAO.buscarTrabajador(dni);
		txtNombreEntr.setText(data[0]);
		txtParaUnidadOrg.setText(data[1]);
	}
	void componentes(boolean op) {
		txtdniEntr.setEnabled(true);
		txtCodBien.setEnabled(op);
		txtDescPro.setEnabled(op);
		txtCant.setEnabled(op);
		txtUnidaMed.setEnabled(op);
		btnBuscarBien.setEnabled(op);
		btnAgregarBien.setEnabled(op);
	}
	public void keyPressed(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	protected void keyReleasedTxtCodBien(KeyEvent e) {
		String codigoBien = txtCodBien.getText();
		String[] data = bienDAO.buscarBienPorCodigo(codigoBien);
		txtDescPro.setText(data[1]);
		txtUnidaMed.setText(data[2]);
	}
	protected void actionPerformedBtnBuscarBien(ActionEvent e) {
		dlgBuscarBienes dlg = new dlgBuscarBienes();
		dlg.setVisible(true);
		dlg.setLocationRelativeTo(null);
	}
	protected void actionPerformedBtnAgregarBien(ActionEvent e) {
		try {
			//variables
			String codigo,descr, unidadMed;
			int cant;
			
			codigo = txtCodBien.getText();
			descr = txtDescPro.getText();
			unidadMed = txtUnidaMed.getText();
			cant = Integer.parseInt(txtCant.getText());
			txtCodBien.requestFocus();
			DefaultTableModel modelo = (DefaultTableModel) tblRequerimientos.getModel();
			
			Object[] filas = {codigo,descr,unidadMed,cant};
			modelo.addRow(filas);
			
			limpiarBien();
		} catch (Exception e2) {
			mensaje("Selecionar Bien");
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
		int posFila = tblRequerimientos.getSelectedRow();
		DefaultTableModel modelo = (DefaultTableModel) tblRequerimientos.getModel();
		modelo.removeRow(posFila);
	}
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		dispose();
	}
	
	void borrar(){
		txtdniEntr.setText("");
		txtNombreEntr.setText("");
		txtParaUnidadOrg.setText("");
		DefaultTableModel requerimiento=(DefaultTableModel) tblRequerimientos.getModel();
		requerimiento.setRowCount(0);	
	}
	
	void limpiarBien() {
		txtCodBien.setText("");
		txtDescPro.setText("");
		txtUnidaMed.setText("");
		txtCant.setText("");
		txtCodBien.requestFocus();
	}
	
	void mensaje(String m){
		JOptionPane.showMessageDialog(null, m);
	}
}
