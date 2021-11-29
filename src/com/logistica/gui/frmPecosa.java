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
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import com.logistica.componentes.JTextFielBD;
import com.logistica.controlador.MySqlPecosaDAO;
import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.entidad.DetallePecosa;
import com.logistica.entidad.Pecosa;
import com.mxrck.autocompleter.TextAutoCompleter;

import lib.Mensajes;

import java.awt.Button;

import javax.swing.JButton;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;

public class frmPecosa extends JFrame implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MySqlPecosaDAO pecosaDao = new MySqlPecosaDAO();
	
	private JPanel contentPane;
	private static JTextField txtNumPecosa;
	private JTextField txtFecha;
	
	public static JTextField txtapeNomFormPor;
	public static JTextField txtEntrDni;
	public static JTextField txtEntrApeNom;
	public static JTextField txtMeta;
	public static JTextField txtNumReq;
	public static JTextField txtUnidadOrgaSoli;
	public static JTextField txtAprobadoPor;
	public static JTextField txtUnidadBeneficiara;
	public static JTextField txtReferencia;
	private JTextField txtEstado;
	public static JTable tblDetallePecosa;
	public static JTextField txtPrecioTotal;
	private JScrollPane scrollPecosa;
	public static JButton btnGuardar;
	private TextAutoCompleter ac;
	public static JTextField txtCargoEntr;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnSalir;
	private TextAutoCompleter auto;
	private JLabel lblPecosa;
	private JLabel lblUnidadBeneficiaria;
	private JLabel lblAprobadoPor;
	private JPanel panel_4;
	private JTextField txtFechaApro;
	private JLabel lblFechaApro;
	private JPanel panel_5;
	private JTextField txtFechaEntr;
	private JLabel lblFechaEntr;
	private JLabel lblNewLabel;
	private JLabel lblFormuladoPor;
	private JPopupMenu popupMenu;
	private JMenuItem mntmAadirBienes;
	private JMenuItem mntmEliminar;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPecosa frame = new frmPecosa();
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
	public frmPecosa() {
		setTitle("Generar Pedido Comprobante de Salida");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1069, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(211, 211, 211));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(10, 25, 246, 131);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNPecosa = new JLabel("N\u00BA Pecosa:");
		lblNPecosa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNPecosa.setBounds(10, 14, 97, 23);
		panel.add(lblNPecosa);
		
		txtNumPecosa = new JTextField();
		txtNumPecosa.setText(codigoCorrelativo());
		txtNumPecosa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNumPecosa.setEditable(false);
		txtNumPecosa.setBounds(110, 12, 126, 28);
		panel.add(txtNumPecosa);
		txtNumPecosa.setColumns(10);
		
		txtFecha = new JTextField(lib.Fecha.fechaActual());
		txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(149, 86, 87, 28);
		panel.add(txtFecha);
		
		JLabel lblFecha = new JLabel("Fecha Formulacion:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(10, 88, 145, 23);
		panel.add(lblFecha);
		
		JLabel lblNRequerimiento = new JLabel("N\u00BA Requerimiento:");
		lblNRequerimiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNRequerimiento.setBounds(10, 53, 126, 23);
		panel.add(lblNRequerimiento);
		
		txtNumReq = new JTextField();
		txtNumReq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNumReq.setEditable(false);
		txtNumReq.setColumns(10);
		txtNumReq.setBounds(139, 50, 97, 28);
		panel.add(txtNumReq);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(135, 206, 235));
		panel_1.setBounds(266, 62, 777, 131);
		contentPane.add(panel_1);
		

		JLabel lblEntregarDni = new JLabel("Entregar A");
		lblEntregarDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEntregarDni.setBounds(10, 54, 105, 14);
		panel_1.add(lblEntregarDni);
		
		txtEntrDni = new JTextField();
		txtEntrDni.addKeyListener(this);
		txtEntrDni.setEditable(false);
		txtEntrDni.setColumns(10);
		txtEntrDni.setBounds(380, 49, 86, 27);
		panel_1.add(txtEntrDni);
		
		JLabel lblConNumeroDni = new JLabel("con numero DNI");
		lblConNumeroDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConNumeroDni.setBounds(263, 54, 126, 14);
		panel_1.add(lblConNumeroDni);
		
		txtEntrApeNom = new JTextField();
		txtEntrApeNom.setEditable(false);
		txtEntrApeNom.setColumns(10);
		txtEntrApeNom.setBounds(89, 49, 168, 27);
		panel_1.add(txtEntrApeNom);
		
		JLabel lblYConCargo = new JLabel("y con cargo de");
		lblYConCargo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYConCargo.setBounds(471, 50, 112, 22);
		panel_1.add(lblYConCargo);
		
		JLabel lblMeta = new JLabel("Meta  :");
		lblMeta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMeta.setBounds(327, 91, 72, 14);
		panel_1.add(lblMeta);
		
		txtMeta = new JTextField();
		txtMeta.setEditable(false);
		txtMeta.setColumns(10);
		txtMeta.setBounds(384, 87, 383, 27);
		panel_1.add(txtMeta);
		
		txtCargoEntr = new JTextField();
		txtCargoEntr.setEditable(false);
		txtCargoEntr.setColumns(10);
		txtCargoEntr.setBounds(579, 49, 188, 27);
		panel_1.add(txtCargoEntr);
		
		txtUnidadOrgaSoli = new JTextField();
		txtUnidadOrgaSoli.setEditable(false);
		txtUnidadOrgaSoli.setColumns(10);
		txtUnidadOrgaSoli.setBounds(221, 10, 263, 27);
		panel_1.add(txtUnidadOrgaSoli);
		
		JLabel lblUnidadOrganicaSolicitante = new JLabel("Unidad Organica Solicitante :");
		lblUnidadOrganicaSolicitante.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnidadOrganicaSolicitante.setBounds(12, 11, 233, 23);
		panel_1.add(lblUnidadOrganicaSolicitante);
		
		lblUnidadBeneficiaria = new JLabel("Unidad beneficiaria: ");
		lblUnidadBeneficiaria.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnidadBeneficiaria.setBounds(10, 93, 150, 14);
		panel_1.add(lblUnidadBeneficiaria);
		
		txtUnidadBeneficiara = new JTextField();
		txtUnidadBeneficiara.setBounds(148, 87, 169, 27);
		txtUnidadBeneficiara.setEditable(false);
		panel_1.add(txtUnidadBeneficiara);
		txtUnidadBeneficiara.setColumns(10);
		
		lblFormuladoPor = new JLabel("Formu. por :");
		lblFormuladoPor.setBounds(494, 11, 105, 23);
		panel_1.add(lblFormuladoPor);
		lblFormuladoPor.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtapeNomFormPor = new JTextField();
		txtapeNomFormPor.setBackground(SystemColor.info);
		txtapeNomFormPor.setBounds(579, 12, 188, 27);
		panel_1.add(txtapeNomFormPor);
		txtapeNomFormPor.setEditable(false);
		txtapeNomFormPor.setColumns(10);
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(211, 211, 211));
		panel_2.setBounds(266, 255, 777, 41);
		contentPane.add(panel_2);
		
		JLabel lblReferencia = new JLabel("Referencia  :");
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReferencia.setBounds(10, 9, 105, 23);
		panel_2.add(lblReferencia);
		
		txtReferencia = new JTextField();
		txtReferencia.setEditable(false);
		txtReferencia.setColumns(10);
		txtReferencia.setBounds(106, 7, 661, 27);
		panel_2.add(txtReferencia);
		
		JLabel lblEstado = new JLabel("Estado  :");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(876, 25, 105, 23);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(949, 24, 94, 27);
		contentPane.add(txtEstado);
		
		scrollPecosa = new JScrollPane();
		scrollPecosa.setBounds(10, 310, 1033, 226);
		contentPane.add(scrollPecosa);
		
		tblDetallePecosa = new JTable();
		tblDetallePecosa.setFillsViewportHeight(true);
		tblDetallePecosa.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripcion", "Unidad Medida", "Nro O/C", "Cant. Salida", "P. U. Salida", "Sub Total"
			}
		));
		tblDetallePecosa.getColumnModel().getColumn(5).setPreferredWidth(104);
		scrollPecosa.setViewportView(tblDetallePecosa);
		
		popupMenu = new JPopupMenu();
		addPopup(tblDetallePecosa, popupMenu);
		
		mntmAadirBienes = new JMenuItem("A\u00F1adir Bienes");
		mntmAadirBienes.addActionListener(this);
		popupMenu.add(mntmAadirBienes);
		
		mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(this);
		popupMenu.add(mntmEliminar);
		
		JLabel lblPrecioTotal = new JLabel("Precio Total  S/. :");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioTotal.setBounds(824, 549, 126, 23);
		contentPane.add(lblPrecioTotal);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setColumns(10);
		txtPrecioTotal.setBounds(949, 547, 94, 27);
		contentPane.add(txtPrecioTotal);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.setBounds(803, 590, 97, 38);
		contentPane.add(btnModificar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(699, 590, 94, 38);
		contentPane.add(btnCancelar);
		
		btnGuardar = new JButton("Nuevo");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.addActionListener(this);
		btnGuardar.setActionCommand("");
		btnGuardar.setBounds(592, 590, 97, 38);
		contentPane.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnSalir(e);
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(910, 589, 81, 38);
		contentPane.add(btnSalir);
		
		lblPecosa = new JLabel("PECOSA");
		lblPecosa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPecosa.setFont(new Font("Square721 BT", Font.BOLD, 30));
		lblPecosa.setBounds(407, 4, 386, 53);
		contentPane.add(lblPecosa);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(UIManager.getColor("Button.shadow"));
		panel_3.setBounds(266, 204, 355, 41);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		txtAprobadoPor = new JTextField();
		txtAprobadoPor.setBounds(116, 6, 229, 27);
		panel_3.add(txtAprobadoPor);
		txtAprobadoPor.setEditable(false);
		txtAprobadoPor.setColumns(10);
		
		lblAprobadoPor = new JLabel("Aprobado por:");
		lblAprobadoPor.setForeground(UIManager.getColor("CheckBox.foreground"));
		lblAprobadoPor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAprobadoPor.setBounds(10, 12, 130, 14);
		panel_3.add(lblAprobadoPor);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(SystemColor.controlShadow);
		panel_4.setBounds(631, 203, 201, 41);
		contentPane.add(panel_4);
		
		txtFechaApro = new JTextField();
		txtFechaApro.setEditable(false);
		txtFechaApro.setColumns(10);
		txtFechaApro.setBounds(98, 6, 86, 27);
		panel_4.add(txtFechaApro);
		
		lblFechaApro = new JLabel("Fecha Apro.");
		lblFechaApro.setForeground(Color.BLACK);
		lblFechaApro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaApro.setBounds(10, 12, 96, 14);
		panel_4.add(lblFechaApro);
		
		panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(SystemColor.controlShadow);
		panel_5.setBounds(842, 203, 201, 41);
		contentPane.add(panel_5);
		
		txtFechaEntr = new JTextField();
		txtFechaEntr.setEditable(false);
		txtFechaEntr.setColumns(10);
		txtFechaEntr.setBounds(98, 6, 93, 27);
		panel_5.add(txtFechaEntr);
		
		lblFechaEntr = new JLabel("Fecha Entr.");
		lblFechaEntr.setForeground(Color.BLACK);
		lblFechaEntr.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaEntr.setBounds(10, 12, 96, 14);
		panel_5.add(lblFechaEntr);
		
		ImageIcon icon = new ImageIcon(new ImageIcon(frmPecosa.class.getResource("/iconos/Sub Almacenero.png")).getImage().getScaledInstance(135, 125, Image.SCALE_SMOOTH));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(10, 167, 246, 129);
		contentPane.add(lblNewLabel);
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmEliminar) {
			actionPerformedMntmEliminar(e);
		}
		if (e.getSource() == mntmAadirBienes) {
			actionPerformedMntmAadirBienes(e);
		}
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtEntrDni) {
			keyReleasedTxtEntrDni(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		if(e.getSource() == txtEntrDni) {
			keyTypedTxtEntrDni(e);
		}
	}
	protected void actionPerformedBtnGuardar(ActionEvent e) {
					//Cabecera PECOSA
					Pecosa pec = new Pecosa();
				try {
					//El estado de la PECOSA pasa a EN REVISION
					txtEstado.setText("EN REVISION");
					
					String referencia;
					int filas;
					
					filas = tblDetallePecosa.getRowCount();
					referencia = txtReferencia.getText();
					
					if(referencia.equals("")) {
						Mensajes.dialogo("El campo REFERENCIA es obligatorio");
					}else if(filas == 0) {
						Mensajes.error("Inserte Bienes antes de Enviar");
					}else {
						pec.setNumPec(Integer.parseInt(txtNumPecosa.getText()));
						pec.setEstadoPec(txtEstado.getText());
						pec.setFecForm(txtFecha.getText());//fijarse el formato de la fecha
						pec.setReferencia(txtReferencia.getText());
						pec.setNumReq(txtNumReq.getText());
						String[] part = txtapeNomFormPor.getText().split("-");
						pec.setDniForm(Integer.parseInt(part[0]));
						pec.setTotal(Double.parseDouble(txtPrecioTotal.getText()));
						
						//Detalle Pecosa
						ArrayList<DetallePecosa> data = new ArrayList<DetallePecosa>();
						
						for(int i  = 0;i<tblDetallePecosa.getRowCount();i++) {
							DetallePecosa dp = new DetallePecosa();
							
							String codBien,desc,uniMed,cant,preUni;
							//Falta OC
							codBien = tblDetallePecosa.getValueAt(i, 0).toString();
							desc = tblDetallePecosa.getValueAt(i, 1).toString();
							uniMed = tblDetallePecosa.getValueAt(i, 2).toString();
							cant = tblDetallePecosa.getValueAt(i, 4).toString();
							preUni = tblDetallePecosa.getValueAt(i, 5).toString();
							
							dp.setCodBien(codBien);
							dp.setDesc(desc);
							dp.setUniMed(uniMed);
							dp.setCant(Integer.parseInt(cant));
							dp.setPrecUnit(Double.parseDouble(preUni));

							data.add(dp);
						}
							int salida = pecosaDao.Ingresar(pec, data);
							if(salida>=0) {
								lib.Estado.ActualizarEstado("tb_cabecreq", "FORMULADO", "numreq", txtNumReq.getText());
								btnGuardar.setText("Nuevo");
								frmPecosa.limpiar();
							}else {
								Mensajes.error("No se actualizo el Cuadro de Requerimientos");
							}
					}
				} catch (NumberFormatException e2) {
					Mensajes.error("Debe poner filas");
				}
			}

	public void keyPressed(KeyEvent e) {
	}
	public void keyTypedTxtEntrDni(KeyEvent e) {
		char c = e.getKeyChar();
		String dni = txtEntrDni.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(dni.length()==8) {
			e.consume();
		}	
	}
	protected void keyReleasedTxtEntrDni(KeyEvent e) {
		String dni = txtEntrDni.getText();
		String[] data = pecosaDao.buscarTrabajador(dni);
		txtEntrApeNom.setText(data[0]);
		txtCargoEntr.setText(data[1]);
	}
	public String codigoCorrelativo() {
		ArrayList<Pecosa> data = pecosaDao.ListarTodo(1,"");
		String codigoSerial;
		if(data.size()==0) {
			codigoSerial = "000001";
		}else {
			int num = data.get(data.size()-1).getNumPec() + 1;
			codigoSerial = String.format("%06d", num);
		}
		return codigoSerial;		
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
	protected void actionPerformedMntmAadirBienes(ActionEvent e) {
		dlgAgregarBienesPecosa dlg = new dlgAgregarBienesPecosa();
		dlg.setVisible(true);
		dlg.setLocationRelativeTo(null);
	}
	protected void actionPerformedMntmEliminar(ActionEvent e) {
		DefaultTableModel modelo = (DefaultTableModel) tblDetallePecosa.getModel();
		int fila = tblDetallePecosa.getSelectedRow();
		if(fila >= 0) {
			modelo.removeRow(fila);
			txtPrecioTotal.setText(String.valueOf(sumaImporte()));
		}else {
			Mensajes.error("Debe seleccionar una fila");
		}
	}
	public static void limpiar() {
		txtNumReq.setText("");
		txtNumPecosa.setText("");
		txtUnidadOrgaSoli.setText("");
		txtEntrApeNom.setText("");
		txtEntrDni.setText("");
		txtCargoEntr.setText("");
		txtUnidadBeneficiara.setText("");
		txtMeta.setText("");
		txtReferencia.setText("");
		txtPrecioTotal.setText("");
		DefaultTableModel modelo = (DefaultTableModel) tblDetallePecosa.getModel();
		modelo.setRowCount(0);
	}
	public double sumaImporte() {
		double suma=0;
		for(int i=0; i< tblDetallePecosa.getRowCount();i++) 
			suma += Double.parseDouble(frmPecosa.tblDetallePecosa.getValueAt(i, 6).toString());
		return suma;
	}
	protected void actionPerformedBtnSalir(ActionEvent e) {
		dispose();
	}
}
