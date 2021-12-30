package com.logistica.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.logistica.componentes.JComboBoxBD;
import com.logistica.controlador.MySqlBienesDAO;
import com.logistica.controlador.MySqlTrabajadorDAO;
import com.logistica.entidad.Bienes;
import com.logistica.entidad.Trabajador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.io.Console;
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

public class dlgBuscarBienes extends JDialog implements KeyListener, ActionListener, MouseListener{
	MySqlBienesDAO bienDAO = new MySqlBienesDAO();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescripcion;
	private JTable tblBienes;
	private JComboBox cboUnidadMedida;
	private JButton btnA�adir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgBuscarBienes dialog = new dlgBuscarBienes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dlgBuscarBienes() {
		setTitle("Bienes");
		setBounds(100, 100, 667, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 424, 334);
		contentPanel.add(scrollPane);
		
		tblBienes = new JTable();
		tblBienes.addMouseListener(this);
		tblBienes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "DESCRIPCION", "STOCK AL.", "UNIDAD MEDIDA"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblBienes.getColumnModel().getColumn(0).setPreferredWidth(16);
		tblBienes.getColumnModel().getColumn(1).setPreferredWidth(104);
		tblBienes.getColumnModel().getColumn(3).setPreferredWidth(51);
		scrollPane.setViewportView(tblBienes);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setToolTipText("Apellidos o Nombres");
		txtDescripcion.addKeyListener(this);
		txtDescripcion.setBounds(444, 85, 197, 26);
		contentPanel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Descripcion:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(445, 60, 136, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BUSQUEDA DE BIENES");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Swis721 Blk BT", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(165, 11, 340, 36);
		contentPanel.add(lblNewLabel_1);
		
		cboUnidadMedida = new JComboBox();
		cboUnidadMedida.addActionListener(this);
		cboUnidadMedida.setModel(new DefaultComboBoxModel(new String[] {"[TODOS]", "MUEBLE", "VIRTUAL", "INMUEBLE", "CONSUMO", "INTERMEDIO"}));
		cboUnidadMedida.setBounds(444, 157, 197, 22);
		contentPanel.add(cboUnidadMedida);
		
		JLabel lblCargo = new JLabel("Categoria:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(444, 126, 136, 22);
		contentPanel.add(lblCargo);
		{
			btnA�adir = new JButton("");
			btnA�adir.addActionListener(this);
			btnA�adir.addMouseListener(this);
			btnA�adir.setIcon(new ImageIcon(dlgBuscarBienes.class.getResource("/iconos/add.png")));
			btnA�adir.setBounds(444, 212, 197, 36);
			contentPanel.add(btnA�adir);
			btnA�adir.setActionCommand("OK");
			getRootPane().setDefaultButton(btnA�adir);
		}
		listar("", "%", "");
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtDescripcion) {
			keyReleasedTxtBuscarTrabajadores(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		String apeNom = txtDescripcion.getText();
		if(!Character.isAlphabetic(c) && c != ' ') {
			e.consume();
		} else if(apeNom.length()==30) {
			e.consume();
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblBienes) {
			mouseClickedTblBienes(e);
		}
		if (e.getSource() == btnA�adir) {
			mouseClickedBtnA�adir(e);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboUnidadMedida) {
			actionPerformedCboCargo(e);
		}
	}
	protected void keyReleasedTxtBuscarTrabajadores(KeyEvent e) {
		String desc = txtDescripcion.getText();
		String comodin = "%";
		String categoria = cboUnidadMedida.getSelectedItem().toString();
		listar(desc, comodin, categoria);
	}
	
	protected void actionPerformedCboCargo(ActionEvent e) {
		String desc = "";
		String comodin = "";
		String categoria = cboUnidadMedida.getSelectedItem().toString();
		listar(desc, comodin, categoria);
	}
	
	void listar(String descr,String comodin, String categoria) {
		if(descr.equals("") && categoria.equals("[TODOS]")) comodin = "%";
		DefaultTableModel modelo = (DefaultTableModel) tblBienes.getModel();
		modelo.setRowCount(0);
		ArrayList<Bienes> lista = bienDAO.buscarBien(descr, comodin, categoria);
		for(Bienes bien:lista) {
			Object[] filas = {	bien.getCodBien(),
								bien.getDescBien(),
								bien.getStockAlmacen(),
								bien.getCategoria()};
			modelo.addRow(filas);
		}
	}
	protected void mouseClickedBtnA�adir(MouseEvent e) {
				try {
					//varaible
					
					String codigo,descr,unidadMed,stockAl;
					//Obtener posicion de la fila seleccionada
					int posFila;
					posFila = tblBienes.getSelectedRow();
					System.out.println(posFila);
					//Obtener datos de la fila seleccionada
					codigo = tblBienes.getValueAt(posFila, 0).toString();
					System.out.println(codigo);
					descr = tblBienes.getValueAt(posFila, 1).toString();
					System.out.println(descr);
					stockAl = tblBienes.getValueAt(posFila, 2).toString();
					System.out.println(stockAl);
					unidadMed = tblBienes.getValueAt(posFila, 3).toString();
					System.out.println(unidadMed);
						//Enviar valores a las cajas de formulario 
						frmCuadroRequerimientos.txtCodBien.setText(codigo);
						frmCuadroRequerimientos.txtDescPro.setText(descr);
						frmCuadroRequerimientos.txtStockTotSeparado_1.setText(stockAl);
						frmCuadroRequerimientos.txtUnidaMed.setText(unidadMed);
						//cerrar ventana actual
						dispose();
					
				} catch (Exception e2) {
					mensaje("Seleccione uno ");
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
	
	void mensaje(String m){
		JOptionPane.showMessageDialog(null, m);
	}
	protected void mouseClickedTblBienes(MouseEvent e) {
		String codigo,descr,unidadMed,stockAl;
		//Obtener posicion de la fila seleccionada
		int posFila;
		posFila = tblBienes.getSelectedRow();
		System.out.println(posFila);
		//Obtener datos de la fila seleccionada
		codigo = tblBienes.getValueAt(posFila, 0).toString();
		descr = tblBienes.getValueAt(posFila, 1).toString();
		stockAl = tblBienes.getValueAt(posFila, 2).toString();
		unidadMed = tblBienes.getValueAt(posFila, 3).toString();
	}
}
