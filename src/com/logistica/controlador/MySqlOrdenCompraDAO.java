package com.logistica.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.logistica.entidad.DetalleOrdenCompra;
import com.logistica.entidad.OrdenCompra;
import com.logistica.interfaces.OrdenCompraDAO;
import com.logistica.utils.MySqlConexion;

import lib.Mensajes;

public class MySqlOrdenCompraDAO implements OrdenCompraDAO{

	@Override
	public int Ingresar(OrdenCompra bean, ArrayList<DetalleOrdenCompra> data) {
		int salida = -1;
		Connection cn = null;
		CallableStatement cstmOc = null, cstmDet = null;
		
		try {
			cn = MySqlConexion.getConexion();
			cn.setAutoCommit(false);
			
			String sqlCabeOc = "call sp_registrar_cabeOrdenCompra(?,?,?,?,?)";
			cstmOc = cn.prepareCall(sqlCabeOc);
			
			cstmOc.setString(1, bean.getNroRUC());
			cstmOc.setString(2, bean.getCodFinan());
			cstmOc.setString(3, bean.getDocRefe());
			cstmOc.setString(4, bean.getFecha());
			cstmOc.setString(5, bean.getEstado());
			
			salida = cstmOc.executeUpdate();
			
			String sqlDet = "call sp_registrar_detalleOrdenCompra(?,?,?,?,?)";
			
			for(DetalleOrdenCompra dp:data) {
				cstmDet = cn.prepareCall(sqlDet);
				
				cstmDet.setString(1, dp.getCodBien());
				cstmDet.setInt(2, bean.getNroOC());
				cstmDet.setInt(3, dp.getCantidad());
				cstmDet.setDouble(4, dp.getPrecio());
				cstmDet.setDouble(5, dp.getTotal());
				
				salida = cstmDet.executeUpdate();
			}
				cn.commit();
				Mensajes.dialogo("El Orden de Compra se registro correctamente, espere su aprobacion");
				
		} catch (SQLException e) {
			try {
				cn.rollback();
				salida = -1;
				Mensajes.error("Error en Transaccion MySqlOrdenCompra");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(cstmOc !=null)cstmOc.close();
				if(cstmDet !=null)cstmDet.close();
				if(cn!=null)cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public String[] buscarProveedor(String str) {
		String cadena[] = new String[2];
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select p.nroRuc, p.rzonSoc, p.telf, d.nomDis " +
				"from tb_proveedores p "+
				"join tb_distrito d on d.codDis = p.codDis " +
				"where p.estado =" + "'str'";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				cadena[0] = rs.getString(1);
				cadena[1] = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Hay un problema en MySqlPecosaDAO");
		} finally {
			try {
				if(cn !=null) cn.close();
				if(pstm != null)pstm.close();
				if(rs != null)rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return cadena;
	}
	
	public ArrayList<OrdenCompra> ListarTodo() {
		ArrayList<OrdenCompra> lista = new ArrayList<OrdenCompra>();
		Connection cn = null;
		CallableStatement cstm = null;
		String sql = "call sp_listarOrdenCompra()";
		ResultSet rs = null;
		try {
			cn = MySqlConexion.getConexion();
			cstm = cn.prepareCall(sql);
			rs = cstm.executeQuery();
			while(rs.next()) {
				OrdenCompra oc = new OrdenCompra();
				oc.setNroOC(rs.getInt(1));
				oc.setFecha(rs.getString(2));
				oc.setDocRefe(rs.getString(3));
				oc.setNomFina(rs.getString(4));
				oc.setNroRUC(rs.getString(5));
				oc.setNomRazSoc(rs.getString(6));
				
				lista.add(oc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">> Error en Listar proveedores");
		} finally {
			try {
				if(cn != null)cn.close();
				if(cstm != null)cstm.close();
				if(rs != null)rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}
	
}
