package com.logistica.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.logistica.entidad.Bienes;
import com.logistica.interfaces.BienesDAO;
import com.logistica.utils.MySqlConexion;

public class MySqlBienesDAO implements BienesDAO {

	@Override
	public int Ingresar(Bienes bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "insert into tb_bienes values (null,?,?,?,?,?,?)";
		
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getDescBien());
			pstm.setString(2, bean.getUniMed());
			pstm.setDouble(3, bean.getPrecUni());
			pstm.setInt(4, bean.getStockDisponible());
			pstm.setString(5, bean.getFecIngreso());
			salida = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (cn != null) cn.close();
				if (pstm != null) pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int Actualizar(Bienes bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "update tb_bienes set descripcion= ?, unidMed=?, precUni=?, stockDisponible=?, fecIngreso=? where codBien=?";
		
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getDescBien());
			pstm.setString(2, bean.getUniMed());
			pstm.setDouble(3, bean.getPrecUni());
			pstm.setInt(4, bean.getStockDisponible());
			pstm.setString(5, bean.getFecIngreso());
			pstm.setInt(6, bean.getCodBien());
			salida = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (cn != null) cn.close();
				if (pstm != null) pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int Eliminar(int cod) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "delete from tb_bienes where codBien = ?";
		
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (cn != null) cn.close();
				if (pstm != null) pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public ArrayList<Bienes> ListarTodo() {
		ArrayList<Bienes> lista = new ArrayList<Bienes>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from tb_bienes";
		
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Bienes bie = new Bienes();
				
				bie.setCodBien(rs.getInt(1));
				bie.setDescBien(rs.getString(2));
				bie.setUniMed(rs.getString(3));
				bie.setPrecUni(rs.getDouble(4));
				bie.setStockDisponible(rs.getInt(6));
				bie.setFecIngreso(rs.getString(7));
				
				lista.add(bie);	
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (cn != null) cn.close();
				if (pstm != null) pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}
	
}
