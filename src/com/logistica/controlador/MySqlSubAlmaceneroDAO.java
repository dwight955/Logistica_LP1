package com.logistica.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.entidad.Pecosa;
import com.logistica.interfaces.CuadroRequerimientosDAO;
import com.logistica.interfaces.SubAlmaceneroDAO;
import com.logistica.utils.MySqlConexion;

public class MySqlSubAlmaceneroDAO implements SubAlmaceneroDAO {

	@Override
	public ArrayList<CuadroRequerimientos> ListarReqSegunEstado(String estado) {
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

}
