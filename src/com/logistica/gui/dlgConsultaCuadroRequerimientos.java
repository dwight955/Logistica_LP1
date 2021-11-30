package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.logistica.controlador.MySqlCuadroRequerimientosDAO;
import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.entidad.Pecosa;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class dlgConsultaCuadroRequerimientos extends JDialog implements ActionListener, MouseListener {
	MySqlCuadroRequerimientosDAO cuadroReqDAO = new MySqlCuadroRequerimientosDAO();
	private final JPanel contentPanel = new JPanel();
	private String estado;
	private JTextField txtNroReq;
	private JTable tblCuadrosReq;
	private JComboBox cboEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgConsultaCuadroRequerimientos dialog = new dlgConsultaCuadroRequerimientos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Create the dialog.
	 */
	public dlgConsultaCuadroRequerimientos() {
		setTitle("Consultar Cuadros de Requerimientos");
		setBounds(100, 100, 660, 396);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNroCuadrosDe = new JLabel("Nro. Cuadros de Requerimientos");
		lblNroCuadrosDe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNroCuadrosDe.setBounds(10, 11, 202, 33);
		contentPanel.add(lblNroCuadrosDe);
		
		txtNroReq = new JTextField();
		txtNroReq.setBounds(205, 18, 65, 20);
		contentPanel.add(txtNroReq);
		txtNroReq.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 624, 267);
		contentPanel.add(scrollPane);
		
		tblCuadrosReq = new JTable();
		tblCuadrosReq.addMouseListener(this);
		tblCuadrosReq.setFillsViewportHeight(true);
		tblCuadrosReq.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro Cuadros Req.", "Solicitante", "Unidad Entregar", "Estado", "Fecha Formulacion"
			}
		));
		scrollPane.setViewportView(tblCuadrosReq);
		
		JLabel lblEstado = new JLabel("Estado                     :");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstado.setBounds(10, 44, 132, 20);
		contentPanel.add(lblEstado);
		
		cboEstado = new JComboBox();
		cboEstado.addActionListener(this);
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"EN REVISION", "APROBADO", "FORMULADO"}));
		cboEstado.setBounds(152, 46, 118, 22);
		contentPanel.add(cboEstado);
		listar(2,"","FORMULADO");
	}
	void listar(int op,String num,String estado) {
		DefaultTableModel modelo = (DefaultTableModel) tblCuadrosReq.getModel();
		modelo.setRowCount(0);
		ArrayList<CuadroRequerimientos> data = cuadroReqDAO.consultarCuadro(op,num,estado);
		for(CuadroRequerimientos p:data) {
			String[] nomUniEntr = p.getNomUniEntr().split("-");
			Object[] filas = {p.getNumreq(),p.getApenomSoli(),nomUniEntr[0],p.getEstado(),p.getFechaEmi()};
			modelo.addRow(filas);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboEstado) {
			actionPerformedCboEstado(e);
		}
	}
	protected void actionPerformedCboEstado(ActionEvent e) {
		estado = cboEstado.getSelectedItem().toString();
		listar(2,"",estado);
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblCuadrosReq) {
			mouseClickedTblCuadrosReq(e);
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
	protected void mouseClickedTblCuadrosReq(MouseEvent e) {
		int posFila = tblCuadrosReq.getSelectedRow();
		String numPec = tblCuadrosReq.getValueAt(posFila, 0).toString();
		txtNroReq.setText(numPec);
	}
}
