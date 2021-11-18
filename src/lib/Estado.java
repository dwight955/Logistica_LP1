package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.logistica.utils.MySqlConexion;

public class Estado {
	
	public static int ActualizarEstado(String tabla,String estado,String campo,String num) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql = "update "+tabla+ 
				" set estado = ?"+  
				" where "+campo+"= ? ";
		try {
			cn = MySqlConexion.getConexion();
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, estado);
			pstm.setString(2, num);
			salida = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(">>Error al aprobar requerimiento");
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
	
}
