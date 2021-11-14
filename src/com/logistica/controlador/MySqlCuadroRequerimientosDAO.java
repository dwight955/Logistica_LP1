package com.logistica.controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.interfaces.CuadroRequerimientosDAO;
import com.logistica.utils.MySqlConexion;
import java.sql.CallableStatement;

public class MySqlCuadroRequerimientosDAO implements CuadroRequerimientosDAO{

	@Override
	public int registrar(CuadroRequerimientos bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<CuadroRequerimientos> listarTodo() {
		ArrayList<CuadroRequerimientos> lista = new ArrayList<CuadroRequerimientos>();
		Connection cn = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		String sql = "call sp_listarRequerimientos";
		
		try {
			cn = MySqlConexion.getConexion();
			cstm = cn.prepareCall(sql);
			rs = cstm.executeQuery();
			while(rs.next()) {
				CuadroRequerimientos cr = new CuadroRequerimientos();
				cr.setNumreq(rs.getString(1));
				cr.setFechaEmi(rs.getString(2));
				cr.setDniSoli(rs.getInt(3));
				cr.setDniEntr(rs.getInt(4));
				cr.setEstado(rs.getString(5));
				
				lista.add(cr);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(cn != null) cn.close();
				if(cstm != null) cstm.close();
				if(rs != null) rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
	}

}
