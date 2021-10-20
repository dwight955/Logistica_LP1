package com.logistica.componentes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import com.logistica.utils.MySqlConexion;
import com.mxrck.autocompleter.TextAutoCompleter;

public class JTextFielBD extends JTextField {
		
	public JTextFielBD(String column, String table) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TextAutoCompleter ac = new TextAutoCompleter(this);
		String sql = "select "+column+" from "+ table;
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				ac.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">>Error en JTextFieldBD");
		} finally {
			try {
				if(cn !=null) cn.close();
				if(pstm != null)pstm.close();
				if(rs != null)rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
