package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.logistica.componentes.JComboBoxBD;
import com.logistica.controlador.MySqlTrabajadorDAO;
import com.logistica.entidad.Trabajador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class dlgBuscarEntrTrabajador extends JDialog implements KeyListener, ActionListener, MouseListener{
	MySqlTrabajadorDAO trabajadorDAO = new MySqlTrabajadorDAO();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscarTrabajadores;
	private JTable tblTrabajadores;
	private JComboBox cboCargo;
	private JButton btnAñadir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgBuscarEntrTrabajador dialog = new dlgBuscarEntrTrabajador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dlgBuscarEntrTrabajador() {
		setTitle("Trabajadores");
		setBounds(100, 100, 667, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 424, 334);
		contentPanel.add(scrollPane);
		
		tblTrabajadores = new JTable();
		tblTrabajadores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DNI", "Apellidos y Nombres", "Cargo"
			}
		));
		tblTrabajadores.getColumnModel().getColumn(0).setPreferredWidth(16);
		tblTrabajadores.getColumnModel().getColumn(2).setPreferredWidth(51);
		scrollPane.setViewportView(tblTrabajadores);
		
		txtBuscarTrabajadores = new JTextField();
		txtBuscarTrabajadores.setToolTipText("Apellidos o Nombres");
		txtBuscarTrabajadores.addKeyListener(this);
		txtBuscarTrabajadores.setBounds(444, 85, 197, 26);
		contentPanel.add(txtBuscarTrabajadores);
		txtBuscarTrabajadores.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Apellidos o Nombres:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(445, 60, 136, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BUSQUEDA DE TRABAJADORES");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Swis721 Blk BT", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(165, 11, 340, 36);
		contentPanel.add(lblNewLabel_1);
		
		cboCargo = new JComboBox();
		cboCargo.addActionListener(this);
		cboCargo.setModel(new DefaultComboBoxModel(new String[] {"", "ASISTENTE DE ALMACEN", "EMPAQUETADOR", "COORDINADOR", "SUB-ALMACENERO", "SECRETARIA"}));
		cboCargo.setBounds(444, 157, 197, 22);
		contentPanel.add(cboCargo);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(444, 126, 136, 22);
		contentPanel.add(lblCargo);
		{
			btnAñadir = new JButton("");
			btnAñadir.addMouseListener(this);
			btnAñadir.setIcon(new ImageIcon(dlgBuscarEntrTrabajador.class.getResource("/iconos/add.png")));
			btnAñadir.setBounds(444, 212, 197, 36);
			contentPanel.add(btnAñadir);
			btnAñadir.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAñadir);
		}
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtBuscarTrabajadores) {
			keyReleasedTxtBuscarTrabajadores(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		String apeNom = txtBuscarTrabajadores.getText();
		if(!Character.isAlphabetic(c) && c != ' ') {
			e.consume();
		} else if(apeNom.length()==30) {
			e.consume();
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnAñadir) {
			mouseClickedBtnAñadir(e);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboCargo) {
			actionPerformedCboCargo(e);
		}
	}
	protected void keyReleasedTxtBuscarTrabajadores(KeyEvent e) {
		String apenomTra = txtBuscarTrabajadores.getText();
		String comodin = "%";
		String cargo = cboCargo.getSelectedItem().toString();
		listar(apenomTra, comodin, cargo);
	}
	
	protected void actionPerformedCboCargo(ActionEvent e) {
		String apenomTra = "";
		String comodin = "";
		String cargo = cboCargo.getSelectedItem().toString();
		listar(apenomTra, comodin, cargo);
	}
	void listar(String apenomTra,String comodin, String cargo) {
		if(apenomTra.equals("")) comodin = "";
		DefaultTableModel modelo = (DefaultTableModel) tblTrabajadores.getModel();
		modelo.setRowCount(0);
		ArrayList<Trabajador> lista = trabajadorDAO.buscarTrabajador(apenomTra, comodin, cargo);
		for(Trabajador tra:lista) {
			Object[] filas = {tra.getDni(),tra.getNomApe(),tra.getCargo()};
			modelo.addRow(filas);
		}
	}
	protected void mouseClickedBtnAñadir(MouseEvent e) {
				//varaible
				String dni,apenom,cargo;
				//Obtener posicion de la fila seleccionada
				int posFila;
				posFila = tblTrabajadores.getSelectedRow();
				//Obtener datos de la fila seleccionada
				dni = tblTrabajadores.getValueAt(posFila, 0).toString();
				apenom = tblTrabajadores.getValueAt(posFila, 1).toString();
				cargo = tblTrabajadores.getValueAt(posFila, 2).toString();
				
					//Enviar valores a las cajas de formulario frmBoleta
					frmPecosa.txtEntrDni.setText(dni);
					frmPecosa.txtEntrApeNom.setText(apenom);
					frmPecosa.txtCargoEntr.setText(cargo);
					//cerrar ventana actual
					dispose();
				
				
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	
}
