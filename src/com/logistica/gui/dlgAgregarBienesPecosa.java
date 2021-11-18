package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.logistica.controlador.MySqlPecosaDAO;
import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.entidad.DetallePecosa;
import com.logistica.entidad.DetalleRequerimientos;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class dlgAgregarBienesPecosa extends JDialog implements ActionListener {
	MySqlPecosaDAO pecosaDAO = new MySqlPecosaDAO();
	private final JPanel contentPanel = new JPanel();
	private JTable tblBienes;
	public static JTextField txtNumReq;
	private JButton btnAadir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgAgregarBienesPecosa dialog = new dlgAgregarBienesPecosa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dlgAgregarBienesPecosa() {
		setTitle("Seleccion de Bienes");
		setBounds(100, 100, 541, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAadirBienes = new JLabel("A\u00D1ADIR BIENES - PECOSA");
			lblAadirBienes.setHorizontalAlignment(SwingConstants.CENTER);
			lblAadirBienes.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblAadirBienes.setBounds(136, 11, 203, 14);
			contentPanel.add(lblAadirBienes);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 505, 186);
		contentPanel.add(scrollPane);
		
		tblBienes = new JTable();
		tblBienes.setFillsViewportHeight(true);
		tblBienes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripcion", "Cantidad", "P. U.", "Importe"
			}
		));
		tblBienes.getColumnModel().getColumn(0).setPreferredWidth(15);
		tblBienes.getColumnModel().getColumn(1).setPreferredWidth(154);
		tblBienes.getColumnModel().getColumn(2).setPreferredWidth(46);
		tblBienes.getColumnModel().getColumn(3).setPreferredWidth(49);
		tblBienes.getColumnModel().getColumn(4).setPreferredWidth(43);
		scrollPane.setViewportView(tblBienes);
		
		btnAadir = new JButton("A\u00D1ADIR TODOS");
		btnAadir.addActionListener(this);
		btnAadir.setBounds(166, 260, 143, 37);
		contentPanel.add(btnAadir);
		
		JLabel lblNumeroDeRequerimiento = new JLabel("N\u00BA Requerimiento");
		lblNumeroDeRequerimiento.setBounds(10, 38, 112, 14);
		contentPanel.add(lblNumeroDeRequerimiento);
		
		txtNumReq = new JTextField();
		txtNumReq.setEditable(false);
		txtNumReq.setText(frmPecosa.txtNumReq.getText());
		txtNumReq.setBounds(120, 35, 68, 20);
		contentPanel.add(txtNumReq);
		txtNumReq.setColumns(10);
		
		listar(txtNumReq.getText());
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAadir) {
			actionPerformedBtnAadir(e);
		}
	}
	ArrayList<DetalleRequerimientos> data = new ArrayList<DetalleRequerimientos>();
	void listar(String numreq) {
		DefaultTableModel modelo = (DefaultTableModel) tblBienes.getModel();
		modelo.setRowCount(0);
		data = pecosaDAO.listarDetalleReqPorNum(numreq);
		for(DetalleRequerimientos dr:data) {
			Object[] filas = {dr.getCodBien(),dr.getDescripcion(),dr.getCant(),dr.getPreUni(),dr.getImporte()};
			modelo.addRow(filas);
		}
	}

	protected void actionPerformedBtnAadir(ActionEvent e) {
	DefaultTableModel modelo = (DefaultTableModel) frmPecosa.tblDetallePecosa.getModel();
	modelo.setRowCount(0);
	for(DetalleRequerimientos dr:data) {
		Object[] filas = {dr.getCodBien(),dr.getDescripcion(),dr.getUniMed()," ",dr.getCant(),dr.getPreUni(),dr.getImporte()};
		modelo.addRow(filas);
	}
	frmPecosa.txtPrecioTotal.setText(String.valueOf(sumaImporte()));
	this.dispose();
	}
	public double sumaImporte() {
		//acumulador
		double suma=0;
		//bucle para realizar recorrido sobre la tabla "tblDetalle"
		for(int i=0; i< frmPecosa.tblDetallePecosa.getRowCount();i++) 
			//obtener la columna 5 de la fila actual
			suma += Double.parseDouble(frmPecosa.tblDetallePecosa.getValueAt(i, 6).toString());
		return suma;
	}
}
