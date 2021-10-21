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
			url="jdbc:mysql://logistica-lp1.mysql.database.azure.com/Logistica?serverTimezone=UTC";
			user="grupoLogistica@logistica-lp1";
			pass="QWEasdZXC123";
			cn=DriverManager.getConnection(url,user,pass);	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cn;
	}
	
}
