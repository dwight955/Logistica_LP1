package com.logistica.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.logistica.entidad.Proveedor;

import com.logistica.interfaces.ProveedorDAO;

import com.logistica.utils.MySqlConexion;

import lib.Mensajes;

import java.sql.CallableStatement;

public class MySqlProveedorDAO implements ProveedorDAO {

	@Override
	public int Ingresar(Proveedor bean) {
		int salida = -1;
		boolean repetido = false;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "insert into tb_proveedores values (?,?,?,?,?,?,?)";
		ArrayList<Proveedor> listaPro = this.ListarTodo();
		for(int i=0; i < listaPro.size();i++) {
			if(bean.getNroRuc().equals(listaPro.get(i).getNroRuc())) {
				repetido = true;
			}
		}
		if(repetido == false) {
			try {
				cn = MySqlConexion.getConexion();
				pstm = cn.prepareStatement(sql);
				
				pstm.setString(1, bean.getNroRuc());
				pstm.setString(2, bean.getRzSoc());
				pstm.setString(3, bean.getEstado());
				pstm.setString(4, bean.getCondic());
				pstm.setString(5, bean.getDirec());
				pstm.setInt(6, bean.getTelf());
				pstm.setString(7, bean.getCodDis());
				salida = pstm.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(">>Error al Ingresar MySqlProveedor");
			} finally {
				try {
					if(cn !=null) cn.close();
					if(pstm != null ) pstm.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}else {
			Mensajes.dialogo("No puede insertar un Nro de RUC ya existente");
		}
		
		return salida;
	}

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
			pstm.setInt(5, bean.getTelf());
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
	public int Eliminar(Proveedor bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "delete from tb_proveedores where nroRuc = ?";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNroRuc());
			salida = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salida;
	}

	@Override
	public ArrayList<Proveedor> ListarTodo() {
		ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
		Connection cn = null;
		CallableStatement cstm = null;
		String sql = "call sp_listarProveedores()";
		ResultSet rs = null;
		try {
			cn = MySqlConexion.getConexion();
			cstm = cn.prepareCall(sql);
			rs = cstm.executeQuery();
			while(rs.next()) {
				Proveedor pro = new Proveedor();
				pro.setNroRuc(rs.getString(1));
				pro.setRzSoc(rs.getString(2));
				pro.setEstado(rs.getString(3));
				pro.setCondic(rs.getString(4));
				pro.setDirec(rs.getString(5));
				pro.setTelf(rs.getInt(6));
				pro.setCodDis(rs.getString(7));
				lista.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">> Error en Listar proveedores");
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
