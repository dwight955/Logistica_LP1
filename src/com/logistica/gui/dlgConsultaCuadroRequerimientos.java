package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

public class dlgConsultaCuadroRequerimientos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNroReq;
	private JTable table;

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
		scrollPane.setBounds(10, 79, 624, 264);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro Cuadros Req.", "Solicitante", "Unidad Entregar", "Estado", "Fecha Formulacion"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblEstado = new JLabel("Estado                     :");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstado.setBounds(10, 44, 132, 20);
		contentPanel.add(lblEstado);
		
		JComboBox cboEstado = new JComboBox();
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"EN REVISION", "APROBADO", "FORMULADO"}));
		cboEstado.setBounds(152, 46, 118, 22);
		contentPanel.add(cboEstado);
	}
}
