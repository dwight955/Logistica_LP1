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
			pstm.setInt(1,bean.getDniSoliPec());
			pstm.setInt(2, bean.getDniEntrPec());
			pstm.setString(3, bean.getEstadoPec());
			pstm.setString(4, bean.getFecPec());
			pstm.setString(5, bean.getReferencia());
			pstm.setInt(6, bean.getNumReq());
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

	@Override
	public String buscarMeta(String unidad) {
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String meta = null;
		String sql = "select u.meta from tb_unidadorganica as u where codUniOrg=?";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(unidad));
			rs = pstm.executeQuery();
			if(rs.next()) {
				meta = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.print("Error en BuscarMeta");
			e.printStackTrace();
		} finally {
			try {
				if(cn !=null) cn.close();
				if(pstm != null)pstm.close();
				if(rs != null)rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return meta;
	}

}
