package com.logistica.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.logistica.entidad.Administradores;
import com.logistica.interfaces.UsuarioDAO;
import com.logistica.utils.MySqlConexion;

public class MySqlUsuarioDAO implements UsuarioDAO {

	@Override
	public Administradores iniciarSesion(String login, String clave) {
		Administradores  bean=null;
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call Iniciar_sesion (?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setString(1, login);
			cstm.setString(2, clave);
			rs=cstm.executeQuery();
			if(rs.next()) {
				bean= new Administradores();
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setApellido(rs.getString(3));
				
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

}
