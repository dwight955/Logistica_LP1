package com.logistica.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.logistica.controlador.MySqlDirectorEjeLogisticaDAO;
import com.logistica.controlador.MySqlPecosaDAO;
import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.entidad.DetalleRequerimientos;

import lib.Mensajes;

public class dlgBandejaEntradaDirector extends JDialog implements ActionListener {
	MySqlDirectorEjeLogisticaDAO directorLog = new MySqlDirectorEjeLogisticaDAO();
	
	private static JTable tblCuadroReq;
	private JButton btnAporbar;
	private static String numReq;
	private static JTable tblDetalleReq;
	private static int cantReq;
	private static JLabel lblCuadrosDe;
	private JButton btnRechazar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dlgBandejaEntradaDirector frame = new dlgBandejaEntradaDirector();
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
	public dlgBandejaEntradaDirector() {
		setBounds(100, 100, 713, 571);
		getContentPane().setLayout(null);
		
		JLabel lblListadoDeCuadro = new JLabel("Listado de Cuadro de Requerimientos");
		lblListadoDeCuadro.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDeCuadro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListadoDeCuadro.setBounds(181, 17, 334, 27);
		getContentPane().add(lblListadoDeCuadro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 677, 185);
		getContentPane().add(scrollPane);
		
		tblCuadroReq = new JTable();
		tblCuadroReq.setFillsViewportHeight(true);
		tblCuadroReq.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NUMERO", "SOLICITANTE", "ENTREGA", "UNIDAD SOLICITANTE", "UNIDAD A ENTREGAR", "FechaEmision"
			}
		));
		tblCuadroReq.getColumnModel().getColumn(0).setPreferredWidth(52);
		tblCuadroReq.getColumnModel().getColumn(1).setPreferredWidth(55);
		tblCuadroReq.getColumnModel().getColumn(2).setPreferredWidth(59);
		tblCuadroReq.getColumnModel().getColumn(5).setPreferredWidth(45);
		scrollPane.setViewportView(tblCuadroReq);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tblCuadroReq, popupMenu);
		
		JMenuItem mntmVerDetalles = new JMenuItem("Aprobar");
		popupMenu.add(mntmVerDetalles);
		
		JMenuItem mntmRechazar = new JMenuItem("Rechazar");
		popupMenu.add(mntmRechazar);
		
		lblCuadrosDe = new JLabel("No hay Cuadros de Requerimientos que mostrar");
		lblCuadrosDe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCuadrosDe.setBounds(21, 55, 293, 14);
		getContentPane().add(lblCuadrosDe);
		
		btnAporbar = new JButton("Aprobar");
		btnAporbar.addActionListener(this);
		btnAporbar.setBounds(413, 488, 132, 33);
		getContentPane().add(btnAporbar);
		
		btnRechazar = new JButton("Rechazar");
		btnRechazar.addActionListener(this);
		btnRechazar.setIcon(new ImageIcon(dlgBandejaEntradaDirector.class.getResource("/iconos/cerrar.png")));
		btnRechazar.setBounds(555, 486, 132, 35);
		getContentPane().add(btnRechazar);
		
		JLabel label = new JLabel("Detalles");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(10, 270, 112, 19);
		getContentPane().add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 295, 677, 178);
		getContentPane().add(scrollPane_1);
		
		tblDetalleReq = new JTable();
		tblDetalleReq.setFillsViewportHeight(true);
		tblDetalleReq.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo Bien", "Descripcion", "Unidad Medida", "cantidad", "Precio Unitario"
			}
		));
		scrollPane_1.setViewportView(tblDetalleReq);
		
		listar("EN REVISION");
		
	}
	void listar(String estado) {
		DefaultTableModel modelo = (DefaultTableModel) tblCuadroReq.getModel();
		modelo.setRowCount(0);
		ArrayList<CuadroRequerimientos> data = directorLog.listarReqPorEstado(estado);
		for(CuadroRequerimientos cua:data) {
			Object[] filas = {cua.getNumreq(),cua.getApenomSoli(),cua.getApenomEntre(),cua.getNomUniSoli(), cua.getNomUniEntr(), cua.getFechaEmi()};
			modelo.addRow(filas);
		}
		cantReq = modelo.getRowCount();
		if(cantReq>0) {lblCuadrosDe.setText(cantReq + " Cuadros de Requerimientos");}
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
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == tblCuadroReq) {
					mouseClickedTblCuadroReq(e);
				}
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRechazar) {
			actionPerformedBtnRechazar(e);
		}
		if (e.getSource() == btnAporbar) {
			actionPerformedBtnAporbar(e);
		}
	}
	protected void actionPerformedBtnAporbar(ActionEvent e) {
		int salida = lib.Estado.ActualizarEstado("tb_cabecreq", "APROBADO", "numreq", numReq);
		if(salida > 0) {
			Mensajes.dialogo("Se acaba de aprobar el requerimiento numero "+numReq);
			listar("EN REVISION");
		}else {
			Mensajes.error("Seleccione un requerimiento");
		}
	}
	protected static void mouseClickedTblCuadroReq(MouseEvent e) {
		int posFila = tblCuadroReq.getSelectedRow();
		if(posFila < 0) {posFila = 0;}else {
			numReq = tblCuadroReq.getValueAt(posFila, 0).toString();
			
			DefaultTableModel modelo = (DefaultTableModel) tblDetalleReq.getModel();
			modelo.setRowCount(0);
			
			ArrayList<DetalleRequerimientos> data = new MySqlPecosaDAO().listarDetalleReqPorNum(numReq);
			
			for(DetalleRequerimientos dr:data) {
				Object[] filas = {dr.getCodBien(),dr.getDescripcion(),dr.getUniMed(),dr.getCant(),dr.getPreUni()};
				modelo.addRow(filas);
			}
		}
	}
	protected void actionPerformedBtnRechazar(ActionEvent e) {
		int delete = Mensajes.confirmarELiminar();
		if(delete == 0) {
			int salida = lib.Estado.ActualizarEstado("tb_cabecreq", "RECHAZADO", "numreq", numReq);
			if(salida < 0) {
				Mensajes.error("Ooops! hubo un problema al momento de actualizar el estado del documento");
			}else {
				Mensajes.dialogo("Acaba de rechazar el Cuadro de Requerimientos numero "+ numReq);
				listar("EN REVISION");
			}
		}
	}
}
