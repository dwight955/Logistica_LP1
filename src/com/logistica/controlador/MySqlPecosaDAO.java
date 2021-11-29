package com.logistica.controlador;

import java.util.ArrayList;

import com.logistica.entidad.DetallePecosa;
import com.logistica.entidad.DetalleRequerimientos;
import com.logistica.entidad.Pecosa;
import com.logistica.entidad.Proveedor;
import com.logistica.gui.frmPecosa;
import com.logistica.interfaces.PecosaDAO;
import com.logistica.utils.MySqlConexion;

import lib.Mensajes;

import java.sql.*;

public class MySqlPecosaDAO implements PecosaDAO {

	@Override
	public int Ingresar(Pecosa bean, ArrayList<DetallePecosa> data) {
		int salida = -1;
		Connection cn = null;
		CallableStatement cstmPec = null,cstmDet = null;
		
		try {
			cn = MySqlConexion.getConexion();
			cn.setAutoCommit(false);
			
			String sqlCabePec = "call sp_registrar_cabePecosa(?,?,?,?,?,?)";
			cstmPec = cn.prepareCall(sqlCabePec);
			
			cstmPec.setString(1, bean.getEstadoPec());
			cstmPec.setString(2, bean.getFecForm());
			cstmPec.setString(3, bean.getReferencia());
			cstmPec.setString(4, bean.getNumReq());
			cstmPec.setInt(5, bean.getDniForm());
			cstmPec.setDouble(6, bean.getTotal());
			
			salida = cstmPec.executeUpdate();
			
			String sqlDet = "call sp_registrar_detallePecosa(?,?,?,?,?)";
			
			for(DetallePecosa dp:data) {
				cstmDet = cn.prepareCall(sqlDet);
				
				cstmDet.setInt(1, bean.getNumPec());
				cstmDet.setString(2, dp.getCodBien());
				cstmDet.setString(3, dp.getUniMed());
				cstmDet.setInt(4, dp.getCant());
				cstmDet.setDouble(5, dp.getPrecUnit());
				
				salida = cstmDet.executeUpdate();
			}
				cn.commit();
				Mensajes.dialogo("La PECOSA se registro correctamente, espere su aprobacion");
				
		} catch (SQLException e) {
			try {
				cn.rollback();
				salida = -1;
				Mensajes.error("Error en Transaccion MySqlPecosa");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(cstmPec !=null)cstmPec.close();
				if(cstmDet !=null)cstmDet.close();
				if(cn!=null)cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public ArrayList<Pecosa> ListarTodo(int op,String estado) {
		ArrayList<Pecosa> lista = new ArrayList<Pecosa>();
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "call sp_listarPecosa(?,?)";
		ResultSet rs = null;
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareCall(sql);
			pstm.setInt(1, op);
			pstm.setString(2, estado);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Pecosa pec = new Pecosa();
				pec.setNumPec(rs.getInt(1));
				pec.setNumReq(rs.getString(2));
				pec.setNombreFormulo(rs.getString(3));
				pec.setFecForm(rs.getString(4));
				pec.setTotal(rs.getDouble(5));
				pec.setReferencia(rs.getString(6));
				lista.add(pec);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">> Error en Listar proveedores");
		} finally {
			try {
				if(cn != null)cn.close();
				if(pstm != null)pstm.close();
				if(rs != null)rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public String[] buscarTrabajador(String str) {
		String cadena[] = new String[2];
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select t.apeNomTrabajador, uo.nomUniOrg from tb_trabajadores as t  join tb_unidadorganica as uo "+
				"on t.codUniOrg = uo.codUniOrg "+
				"where dnitrabajador like "+ '"'+str+'"';
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

	@Override
	public ArrayList<DetalleRequerimientos> listarDetalleReqPorNum(String numreq) {
		ArrayList<DetalleRequerimientos> lista = new ArrayList<DetalleRequerimientos>();
		Connection cn = null;
		CallableStatement cstm = null;
		String sql = "call sp_listarDetalleReqPorNum(?)";
		ResultSet rs = null;
		try {
			cn = MySqlConexion.getConexion();
			cstm = cn.prepareCall(sql);
			cstm.setString(1, numreq);
			rs = cstm.executeQuery();
			while(rs.next()) {
				DetalleRequerimientos dr = new DetalleRequerimientos();
				dr.setNumReq(rs.getString(1));
				dr.setCodBien(rs.getString(2));
				dr.setDescripcion(rs.getString(3));
				dr.setUniMed(rs.getString(4));
				dr.setCant(rs.getInt(5));
				dr.setPreUni(rs.getDouble(6));
				dr.setImporte(rs.getDouble(7));
				lista.add(dr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">> Error en Listar detalle requerimientos");
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

	@Override
	public int ActualizarFechaYDni(Pecosa bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstmPec = null;
		String sqlCabePec = "update tb_pecosa "
				+ " set FechApro = ?,"
				+ " 	dniJefeApro = ?"
				+ " where nroPecosa = ?";
		try {
			cn = MySqlConexion.getConexion();
			pstmPec = cn.prepareStatement(sqlCabePec);
			pstmPec.setString(1, bean.getFecApro());
			pstmPec.setInt(2, bean.getDniJefeApro());
			pstmPec.setInt(3, bean.getNumPec());
			salida = pstmPec.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">>Error al momento de actualizar la Fecha y Dni de la Pecosa");
		}finally {
			try {
				if(cn!=null) cn.close();
				if(pstmPec!=null)pstmPec.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}	
		}
		return salida;
	}
}
