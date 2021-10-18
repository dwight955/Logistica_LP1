package com.logistica.controlador;

import java.util.ArrayList;

import com.logistica.entidad.Pecosa;
import com.logistica.interfaces.PecosaDAO;
import com.logistica.utils.MySqlConexion;

import java.sql.*;

public class MySqlPecosaDAO implements PecosaDAO {

	@Override
	public int Ingresar(Pecosa bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "Insert into TB_Pecosa values (null,?,?,?,?,?,?,?);";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getUniOrgPec());
			pstm.setInt(2,bean.getDniSoliPec());
			pstm.setInt(3, bean.getDniEntrPec());
			pstm.setString(4, bean.getEstadoPec());
			pstm.setString(5, bean.getFecPec());
			pstm.setString(6, bean.getReferencia());
			pstm.setString(7, bean.getMeta());
			salida = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en MySqlPecosaDao");
		} finally {
			try {
				if(cn != null) cn.close();
				if(pstm !=null) pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			} 
		}
		return salida;
	}

	@Override
	public int Actualizar(Pecosa bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Eliminar(Pecosa bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Pecosa> ListarTodo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] buscarTrabajador(String str) {
		String cadena[] = new String[2];
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String filtro = str;
		String sql = "select * from TB_Trabajadores " + 
				"where dniTrabajador like  " +'"'+filtro+'"';
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				cadena[0] = rs.getString(2);
				cadena[1] = rs.getString(3);
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

}
