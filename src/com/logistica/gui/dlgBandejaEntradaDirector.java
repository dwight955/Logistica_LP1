package com.logistica.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import com.logistica.controlador.MySqlCuadroRequerimientosDAO;
import com.logistica.controlador.MySqlDirectorEjeLogisticaDAO;
import com.logistica.entidad.CuadroRequerimientos;

import lib.Mensajes;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dlgBandejaEntradaDirector extends JDialog implements ActionListener {
	MySqlDirectorEjeLogisticaDAO directorLog = new MySqlDirectorEjeLogisticaDAO();
	private static JTable tblCuadroReq;
	private JButton btnAporbar;
	private static String numReq;

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
		setBounds(100, 100, 713, 533);
		getContentPane().setLayout(null);
		
		JLabel lblListadoDeCuadro = new JLabel("Listado de Cuadro de Requerimientos");
		lblListadoDeCuadro.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDeCuadro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListadoDeCuadro.setBounds(198, 17, 334, 27);
		getContentPane().add(lblListadoDeCuadro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 677, 223);
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
		
		JMenuItem mntmVerDetalles = new JMenuItem("Ver Detalles");
		popupMenu.add(mntmVerDetalles);
		
		JLabel lblCuadrosDe = new JLabel("0 Cuadros de Requerimientos");
		lblCuadrosDe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCuadrosDe.setBounds(21, 55, 190, 14);
		getContentPane().add(lblCuadrosDe);
		
		btnAporbar = new JButton("Aprobar");
		btnAporbar.addActionListener(this);
		btnAporbar.setBounds(10, 316, 132, 33);
		getContentPane().add(btnAporbar);
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setIcon(new ImageIcon(dlgBandejaEntradaDirector.class.getResource("/iconos/cerrar.png")));
		btnRechazar.setBounds(152, 314, 132, 35);
		getContentPane().add(btnRechazar);
		
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
		numReq = tblCuadroReq.getValueAt(posFila, 0).toString();
	}
}
