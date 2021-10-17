package com.logistica.componentes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import com.logistica.utils.MySqlConexion;


public class JTextFielBD extends JTextField {
		
	public JTextFielBD() {
		String cadena[] = new String[2];
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from TB_Trabajadores ";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				cadena[0] = rs.getString(0);
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
	}
}
