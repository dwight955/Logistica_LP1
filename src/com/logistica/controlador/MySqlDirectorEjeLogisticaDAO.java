package com.logistica.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.interfaces.CuadroRequerimientosDAO;
import com.logistica.interfaces.DirectorEjeLogisticaDAO;
import com.logistica.utils.MySqlConexion;


public class MySqlDirectorEjeLogisticaDAO implements DirectorEjeLogisticaDAO{

	@Override
	public ArrayList<CuadroRequerimientos> listarReqPorEstado(String estado) {
		ArrayList<CuadroRequerimientos> lista = new ArrayList<CuadroRequerimientos>();
		Connection cn = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		String sql = "call sp_consultarReqSegúnEstado(?)";
		try {
			cn = MySqlConexion.getConexion();
			cstm = cn.prepareCall(sql);
			cstm.setString(1, estado);
			rs = cstm.executeQuery();
			while(rs.next()) {
				CuadroRequerimientos cua = new CuadroRequerimientos();
				cua.setNumreq(rs.getString(1));
				cua.setApenomSoli(rs.getString(2));
				cua.setApenomEntre(rs.getString(3));
				cua.setNomUniSoli(rs.getString(4));
				cua.setNomUniEntr(rs.getString(5));
				cua.setFechaEmi(rs.getString(6));
				lista.add(cua);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">>Error en el listado");
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
	public int aprobarCuadroReq(String num) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "update tb_cabecreq "+ 
				"set estado='APROBADO' "+  
				"where numreq=?";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, num);
			salida = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">>Error al aprobar requerimiento");
		} finally {
			try {
				if(cn != null)cn.close();
				if(pstm != null)pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int desaprobarCuadroReq(String num) {
		// TODO Auto-generated method stub
		return 0;
	}
}
