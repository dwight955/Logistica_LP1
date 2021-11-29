package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.logistica.controlador.MySqlPecosaDAO;
import com.logistica.entidad.Pecosa;
import com.logistica.utils.Libreria;

import lib.Mensajes;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dlgBandejaEntradaJefe extends JDialog implements MouseListener, ActionListener {
	MySqlPecosaDAO pecosaDAO = new MySqlPecosaDAO();
	private final JPanel contentPanel = new JPanel();
	private JTable tblPecosas;
	private JTextField txtReferencia;
	private JTextField txtFecha;
	private JTextField txtNomJefe;
	private JTextField txtDniJefe;
	private JButton btnAutorizar;
	private String numPec;
	private JButton btnRevision;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgBandejaEntradaJefe dialog = new dlgBandejaEntradaJefe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dlgBandejaEntradaJefe() {
		setTitle("Bandeja de entrada PECOSAS");
		setBounds(100, 100, 616, 483);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setForeground(new Color(112, 128, 144));
		panel.setBounds(10, 49, 580, 35);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblReferencia = new JLabel("REFERENCIA:");
		lblReferencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblReferencia.setBounds(10, 6, 99, 24);
		panel.add(lblReferencia);
		
		txtReferencia = new JTextField();
		txtReferencia.setEditable(false);
		txtReferencia.setBounds(91, 9, 480, 20);
		panel.add(txtReferencia);
		txtReferencia.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 580, 292);
		contentPanel.add(scrollPane);
		
		tblPecosas = new JTable();
		tblPecosas.addMouseListener(this);
		tblPecosas.setFillsViewportHeight(true);
		tblPecosas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro. PECOSA", "Num. Req", "Formulado por", "Fecha Formulada", "Total"
			}
		));
		scrollPane.setViewportView(tblPecosas);
		
		btnAutorizar = new JButton("AUTORIZAR");
		btnAutorizar.addActionListener(this);
		btnAutorizar.setBounds(481, 398, 109, 35);
		contentPanel.add(btnAutorizar);
		
		btnRevision = new JButton("REVISION");
		btnRevision.addActionListener(this);
		btnRevision.setBounds(356, 398, 115, 35);
		contentPanel.add(btnRevision);
		
		JLabel lblFechaActual = new JLabel("Fecha Actual:");
		lblFechaActual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaActual.setBounds(10, 15, 93, 20);
		contentPanel.add(lblFechaActual);
		
		txtFecha = new JTextField();
		txtFecha.setText(lib.Fecha.fechaActual());
		txtFecha.setEditable(false);
		txtFecha.setBounds(94, 15, 69, 20);
		contentPanel.add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblJufa = new JLabel("JUFA   :");
		lblJufa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblJufa.setBounds(173, 18, 59, 14);
		contentPanel.add(lblJufa);
		
		txtNomJefe = new JTextField();
		txtNomJefe.setText(frmJefeDeUnidFuncAlmacen.lblNomJufa.getText());
		txtNomJefe.setEditable(false);
		txtNomJefe.setBounds(224, 16, 219, 20);
		contentPanel.add(txtNomJefe);
		txtNomJefe.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDni.setBounds(453, 18, 46, 14);
		contentPanel.add(lblDni);
		
		txtDniJefe = new JTextField();
		txtDniJefe.setText(String.valueOf(Libreria.codigoTrabajadorSesion));
		txtDniJefe.setEditable(false);
		txtDniJefe.setBounds(493, 16, 86, 20);
		contentPanel.add(txtDniJefe);
		txtDniJefe.setColumns(10);
		listar();
	}
	void listar() {
		DefaultTableModel modelo = (DefaultTableModel) tblPecosas.getModel();
		modelo.setRowCount(0);
		ArrayList<Pecosa> data = pecosaDAO.ListarTodo(2,"EN REVISION");
		for(Pecosa p:data) {
			Object[] filas = {p.getNumPec(),p.getNumReq(),p.getNombreFormulo(),p.getFecForm(),p.getTotal()};
			modelo.addRow(filas);
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblPecosas) {
			mouseClickedTblPecosas(e);
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
	protected void mouseClickedTblPecosas(MouseEvent e) {
		int posFila = tblPecosas.getSelectedRow();
		numPec = tblPecosas.getValueAt(posFila, 0).toString();
		ArrayList<Pecosa> data = pecosaDAO.ListarTodo(2,"EN REVISION");
		txtReferencia.setText(data.get(posFila).getReferencia()); 
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRevision) {
			actionPerformedBtnRevision(e);
		}
		if (e.getSource() == btnAutorizar) {
			actionPerformedBtnAutorizar(e);
		}
	}
	protected void actionPerformedBtnAutorizar(ActionEvent e) {
		//Variables
		String fechaApro, dniApro;
		fechaApro = txtFecha.getText();
		dniApro = txtDniJefe.getText();
		//Asignarlas al objeto
		Pecosa p = new Pecosa();
		p.setNumPec(Integer.parseInt(numPec));
		p.setFecApro(fechaApro);
		p.setDniJefeApro(Integer.parseInt(dniApro));
		int res = pecosaDAO.ActualizarFechaYDni(p);
		if(res > 0) {
			lib.Estado.ActualizarEstado("tb_pecosa", "AUTORIZADO", "nroPecosa", numPec);
			Mensajes.dialogo("Se acaba de autorizar la PECOSA numero "+numPec);
			listar();
		}else {
			Mensajes.error("No se pudo Autorizar");
		}
	}
	protected void actionPerformedBtnRevision(ActionEvent e) {
		int i = Mensajes.confirmarRechazo();
		if(i==0) {
			lib.Estado.ActualizarEstado("tb_pecosa", "RECHAZADO", "nroPecosa", numPec);
			Mensajes.dialogo("Se acaba de rechazar la PECOSA numero "+numPec);
			listar();
		}
	}
}
