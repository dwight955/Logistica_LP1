package com.logistica.controlador;

import java.sql.*;
import java.util.ArrayList;

import com.logistica.entidad.Trabajador;
import com.logistica.interfaces.TrabajadorDAO;
import com.logistica.utils.MySqlConexion;

import lib.Mensajes;

public class MySqlTrabajadorDAO implements TrabajadorDAO{

	@Override
	public int Ingresar(Trabajador bean) {
		int salida = -1;
		boolean repetido = false;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "insert into TB_Trabajadores values (?,?,?,?,?,?,?)";
			ArrayList<Trabajador> listaTra = this.ListarTodo();
			for(int i=0;i< listaTra.size();i++) {
				if(bean.getDni() == listaTra.get(i).getDni()) {
					repetido = true;
				}
			}
			if(repetido == false) {
				try {
					cn = MySqlConexion.getConexion();
					pstm = cn.prepareStatement(sql);
					pstm.setInt(1, bean.getDni());
					pstm.setString(2, bean.getNomApe());
					pstm.setString(3, bean.getCargo());
					pstm.setString(6, bean.getSexo());
					salida = pstm.executeUpdate();
				} catch (SQLException e) {
				  e.printStackTrace();
			    } finally {
					  try {
						  if(cn != null) cn.close();
						  if(pstm != null)pstm.close();
				}      catch (SQLException e2) {
					   e2.printStackTrace();
				}
			}
			}else {
				Mensajes.dialogo("No puede insertar un DNI ya existente");
			}
			return salida;
}
	@Override
	public int Actualizar(Trabajador bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "update TB_Trabajadores " + 
				"set apeNomTrabajador=?, " + 
				"cargoTrabajador=?, " + 
				"fecNac=?, " + 
				"sueldo=?, " + 
				"sexo=?, "+
				"CodDistrito=? "+
				"where dniTrabajador = ?";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNomApe());
			pstm.setString(2, bean.getCargo());
			pstm.setString(5, bean.getSexo());
			pstm.setInt(7, bean.getDni());
			salida = pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error en MySqlTrabajadorDAO");
			e.printStackTrace();
		} finally {
			try {
				if(cn != null) cn.close();
				if(pstm != null)pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int Eliminar(Trabajador bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "delete from TB_Trabajadores where dniTrabajador = ?";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getDni());
			salida = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salida;
	}

	@Override
	public ArrayList<Trabajador> ListarTodo() {
		ArrayList<Trabajador> lista = new ArrayList<Trabajador>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "call sp_ListarTrabajadores()";
			
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Trabajador tra = new Trabajador();
				tra.setDni(rs.getInt(1));
				tra.setNomApe(rs.getString(2));
				tra.setCargo(rs.getString(3));
				tra.setSexo(rs.getString(6));
				lista.add(tra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problemas en MySqlTrabajadorDao");
		} finally {
			try {
				if(cn!= null) cn.close();
				if(pstm != null)pstm.close();
				if(rs != null)rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}
	@Override
	public ArrayList<Trabajador> ListarBusqueda(String apenom,String comodin, String unidadOrg) {
		ArrayList<Trabajador> lista = new ArrayList<Trabajador>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_buscar_trabajador(?,?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setString(1, apenom);
			cstm.setString(2, comodin);
			cstm.setString(3, unidadOrg);
			rs=cstm.executeQuery();
			while(rs.next()){
				Trabajador tra = new Trabajador();
				tra.setDni(rs.getInt(1));
				tra.setNomApe(rs.getString(2));
				tra.setUnidadOrga(rs.getString(3));
				lista.add(tra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">>Error en MySqlTrabajadorDAO.buscarTrabajador()");
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(cstm!=null) cstm.close();
				if(cn!=null) cn.close();
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
