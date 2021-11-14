package com.logistica.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.logistica.entidad.Logistica;
import com.logistica.entidad.UnidadOrganica;
import com.logistica.interfaces.UsuarioDAO;
import com.logistica.utils.MySqlConexion;

public class MySqlUsuarioDAO implements UsuarioDAO {

	@Override
	public Logistica iniciarSesionLog(String login, String clave) {
		Logistica  bean=null;
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call Iniciar_sesion_Logistica(?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setString(1, login);
			cstm.setString(2, clave);
			rs=cstm.executeQuery();
			if(rs.next()) {
				bean= new Logistica();
				bean.setDni(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setFecNac(rs.getString(3));
				bean.setSueldo(rs.getDouble(4));
				bean.setSexo(rs.getString(5));
				bean.setCargo(rs.getString(6));
				bean.setIdCargo(rs.getInt(7));
			}
	
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally {
			try {
					if(rs != null) rs.close();
					if(cstm != null) cstm.close();
					if(cn !=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public UnidadOrganica iniciarSesionUnOrg(String login, String clave) {
		UnidadOrganica bean=null;
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call Iniciar_sesion_UnOrgSoli(?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setString(1, login);
			cstm.setString(2, clave);
			rs=cstm.executeQuery();
			if(rs.next()) {
				bean= new UnidadOrganica();
				bean.setApenom(rs.getString(1));
				bean.setCargo(rs.getString(2));
				bean.setNomUnidOrg(rs.getString(3));
				bean.setDnilogin(rs.getInt(5));
			}
	
		} catch (SQLException e) {
				System.out.println(">>Problemas en Unidad Organica Iniciar Sesion");
				e.printStackTrace();
		}
		finally {
			try {
					if(rs != null) rs.close();
					if(cstm != null) cstm.close();
					if(cn !=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return bean;
	}

}
