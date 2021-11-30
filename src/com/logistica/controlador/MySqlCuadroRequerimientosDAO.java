package com.logistica.controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.entidad.DetalleRequerimientos;
import com.logistica.interfaces.CuadroRequerimientosDAO;
import com.logistica.utils.MySqlConexion;

import lib.Mensajes;

import java.sql.CallableStatement;

public class MySqlCuadroRequerimientosDAO implements CuadroRequerimientosDAO{

	@Override
	public int registrar(CuadroRequerimientos cuaReq, ArrayList<DetalleRequerimientos> lista) {
		int salida = -1;
		
		Connection cn = null;
		CallableStatement cstmRequ = null, cstmDet = null;
		
		try {
			cn = MySqlConexion.getConexion();
			cn.setAutoCommit(false);
			
			String sqlRequ = "call sp_registrar_cabecRequerimiento(?,?,?,?,?)";
			cstmRequ = cn.prepareCall(sqlRequ);
			
			cstmRequ.setString(1, cuaReq.getNumreq());
			cstmRequ.setString(2, cuaReq.getEstado());
			cstmRequ.setString(3, cuaReq.getFechaEmi());
			cstmRequ.setInt(4, cuaReq.getDniSoli());
			cstmRequ.setInt(5, cuaReq.getDniEntr());
			
			salida = cstmRequ.executeUpdate();
			
			String sqlDet = "call sp_registrar_detalleRequerimiento(?,?,?)";
			cstmDet = cn.prepareCall(sqlDet);
			
			for(DetalleRequerimientos d:lista) {
				cstmDet = cn.prepareCall(sqlDet);
				
				cstmDet.setString(1, cuaReq.getNumreq());
				cstmDet.setString(2, d.getCodBien());
				cstmDet.setInt(3, d.getCant());
				
				salida = cstmDet.executeUpdate();
				 
			}
			
			cn.commit();
			
		} catch (SQLException e) {
			try {
				salida = -1;
				cn.rollback();
				Mensajes.dialogo("Error en la conexión...");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(cstmRequ != null) cstmRequ.close();
				if(cstmDet != null) cstmDet.close();
				if(cn != null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public ArrayList<CuadroRequerimientos> listarPorNum(String num) {
		ArrayList<CuadroRequerimientos> lista = new ArrayList<CuadroRequerimientos>();
		Connection cn = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		String sql = "call sp_cabereq(?)";
		try {
			cn = MySqlConexion.getConexion();
			cstm = cn.prepareCall(sql);
			cstm.setString(1, num);
			rs = cstm.executeQuery();
			while(rs.next()) {
				CuadroRequerimientos cua = new CuadroRequerimientos();
				cua.setNumreq(rs.getString(1));
				cua.setNomUniSoli(rs.getString(2));
				cua.setApenomEntre(rs.getString(3));
				cua.setNomUniEntr(rs.getString(4));
				lista.add(cua);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">> error en ListarTodo() de MySqlCuadrReq");
		} finally {
			try {
				if(cn != null) cn.close();
				if(cstm != null)cstm.close();
				if(rs != null)rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public ArrayList<CuadroRequerimientos> consultarCuadro(int op, String num, String estado) {
		ArrayList<CuadroRequerimientos> lista = new ArrayList<CuadroRequerimientos>();
		Connection cn = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		String sql = "call sp_listarCuadroReqPorNum2(?,?,?)";
		try {
			cn = MySqlConexion.getConexion();
			cstm = cn.prepareCall(sql);
			cstm.setInt(1, op);
			cstm.setString(2, num);
			cstm.setString(3, estado);
			rs = cstm.executeQuery();
			while(rs.next()) {
				CuadroRequerimientos cua = new CuadroRequerimientos();
				cua.setNumreq(rs.getString(1));
				cua.setApenomSoli(rs.getString(2));
				cua.setNomUniEntr(rs.getString(3));
				cua.setEstado(rs.getString(4));
				cua.setFechaEmi(rs.getString(5));
				lista.add(cua);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">> error en ListarTodo() de MySqlCuadrReq");
		} finally {
			try {
				if(cn != null) cn.close();
				if(cstm != null)cstm.close();
				if(rs != null)rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}
}
