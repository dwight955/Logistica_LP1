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

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import com.logistica.controlador.MySqlPecosaDAO;
import com.logistica.entidad.Pecosa;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class dlgConsultarPECOSA extends JDialog implements ActionListener, KeyListener, MouseListener {
	MySqlPecosaDAO pecosaDAO = new MySqlPecosaDAO();
	private final JPanel contentPanel = new JPanel();
	private JTable tblPecosa;
	private JTextField txtNroPecosa;
	private JComboBox cboEstadoPecosa;
	private JTextField txtReferencia;
	private String numPec;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgConsultarPECOSA dialog = new dlgConsultarPECOSA();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dlgConsultarPECOSA() {
		setTitle("Consultar PECOSA");
		setBounds(100, 100, 550, 483);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 11, 514, 50);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblConsultarPecosa = new JLabel("Consultar PECOSA");
		lblConsultarPecosa.setForeground(Color.WHITE);
		lblConsultarPecosa.setBounds(116, 11, 294, 35);
		panel.add(lblConsultarPecosa);
		lblConsultarPecosa.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultarPecosa.setFont(new Font("Open Sans", Font.BOLD, 24));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 169, 514, 228);
		contentPanel.add(scrollPane);
		
		tblPecosa = new JTable();
		tblPecosa.addMouseListener(this);
		tblPecosa.setFillsViewportHeight(true);
		tblPecosa.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "NumReq", "Fecha Formulada", "Total"
			}
		));
		scrollPane.setViewportView(tblPecosa);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstado.setBounds(10, 85, 67, 27);
		contentPanel.add(lblEstado);
		
		cboEstadoPecosa = new JComboBox();
		cboEstadoPecosa.addActionListener(this);
		cboEstadoPecosa.setModel(new DefaultComboBoxModel(new String[] {"AUTORIZADO", "RECHAZADO"}));
		cboEstadoPecosa.setBounds(69, 89, 132, 22);
		contentPanel.add(cboEstadoPecosa);
		
		JLabel lblNroPecosa = new JLabel("Nro. PECOSA      :");
		lblNroPecosa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNroPecosa.setBounds(226, 88, 122, 22);
		contentPanel.add(lblNroPecosa);
		
		txtNroPecosa = new JTextField();
		txtNroPecosa.addKeyListener(this);
		txtNroPecosa.setBounds(337, 90, 42, 20);
		contentPanel.add(txtNroPecosa);
		txtNroPecosa.setColumns(10);
		
		JLabel lblReferemcia = new JLabel("Referencia:");
		lblReferemcia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReferemcia.setBounds(10, 123, 81, 22);
		contentPanel.add(lblReferemcia);
		
		txtReferencia = new JTextField();
		txtReferencia.setEditable(false);
		txtReferencia.setBounds(89, 126, 435, 20);
		contentPanel.add(txtReferencia);
		txtReferencia.setColumns(10);
	}
	void listar(int op, String estado) {
		DefaultTableModel modelo = (DefaultTableModel) tblPecosa.getModel();
		modelo.setRowCount(0);
		ArrayList<Pecosa> data = pecosaDAO.ListarTodo(op, estado);
		for(Pecosa p:data) {
			Object[] filas = {p.getNumPec(),p.getNumReq(),p.getFecForm(),p.getTotal()};
			modelo.addRow(filas);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboEstadoPecosa) {
			actionPerformedCboEstadoPecosa(e);
		}
	}
	protected void actionPerformedCboEstadoPecosa(ActionEvent e) {
		String item = cboEstadoPecosa.getSelectedItem().toString();
		listar(2,item);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtNroPecosa) {
			keyReleasedTxtNroPecosa(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		String nroPecosa = txtNroPecosa.getText();
		if(!Character.isDigit(c)) {
			e.consume();
		} else if(nroPecosa.length() == 9) {
			e.consume();
		}
	}
	protected void keyReleasedTxtNroPecosa(KeyEvent e) {
		String cad = txtNroPecosa.getText();
		ArrayList<Pecosa> data = pecosaDAO.ListarTodo(3,cad);
		for(Pecosa p:data) {
			numPec = p.getReferencia();
		}
		txtReferencia.setText(numPec);
		listar(3, cad);
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblPecosa) {
			mouseClickedTblPecosa(e);
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
	protected void mouseClickedTblPecosa(MouseEvent e) {
		int posFila = tblPecosa.getSelectedRow();
		String numPec = tblPecosa.getValueAt(posFila, 0).toString();
		ArrayList<Pecosa> data = pecosaDAO.ListarTodo(1,"");
		txtReferencia.setText(data.get(posFila).getReferencia()); 
	}
}
