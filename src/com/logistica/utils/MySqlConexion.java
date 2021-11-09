package com.logistica.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConexion {
	
	public static Connection getConexion() {
		Connection cn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url,user,pass;
			url="jdbc:mysql://localhost/qwerty?serverTimezone=UTC";
			user="root";
			pass="12345";
			cn=DriverManager.getConnection(url,user,pass);	
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cn;
	}
	
}
