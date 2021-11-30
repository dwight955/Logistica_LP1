package com.logistica.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.logistica.entidad.Proveedor;
import com.logistica.entidad.Trabajador;
import com.logistica.interfaces.ProveedorDAO;

import com.logistica.utils.MySqlConexion;

import lib.Mensajes;

import java.sql.CallableStatement;

public class MySqlProveedorDAO implements ProveedorDAO {


	@Override
	public int Actualizar(Proveedor bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "update tb_proveedores " + 
				"set rzonSoc=?, " + 
				"estado=?, " + 
				"condic=?, " + 
				"direc=?, " + 
				"telf=?, " + 
				"codDis=? " + 
				"where nroRuc=?";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getRzSoc());
			pstm.setString(2, bean.getEstado());
			pstm.setString(3, bean.getCondic());
			pstm.setString(4, bean.getDirec());
			pstm.setString(5, bean.getTelf());
			pstm.setString(6, bean.getCodDis());
			pstm.setString(7, bean.getNroRuc());
			salida = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">>Error al actualizar MySqlProveedorDAO");
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
	public String[] buscarProveedor(String ruc) {
		String cadena[] = new String[2];
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select t.apeNomTrabajador, uo.nomUniOrg from tb_trabajadores as t  join tb_unidadorganica as uo "+
				"on t.codUniOrg = uo.codUniOrg "+
				"where dnitrabajador like "+ '"'+ruc+'"';
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
	public ArrayList<Proveedor> ListarBusqueda(String razonSoc, String comodin, String distrito) {
		ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_buscar_proveedor(?,?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setString(1, razonSoc);
			cstm.setString(2, comodin);
			cstm.setString(3, distrito);
			rs=cstm.executeQuery();
			while(rs.next()){
				Proveedor pro = new Proveedor();
				pro.setNroRuc(rs.getString(1));
				pro.setRzSoc(rs.getString(2));
				pro.setTelf(rs.getString(3));
				pro.setNomDis(rs.getString(4));
				pro.setDirec(rs.getString(5));
				
				lista.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">>Error en MySqlTrabajadorDAO.buscarProveedor()");
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

	

}
