package net.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {

	private Connection c;
	 
	public Connection getConnection() throws ClassNotFoundException, SQLException {
 
		String hostName = "localhost";
		String dbName = "vendas";
		String user = "sa";
		String senha = "k123456";
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		c = DriverManager.getConnection(String.format("jdbc:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;encrypt=false;", hostName, dbName, user, senha));
 
		return c;
	}
	
}
