package net.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {

	private Connection c;
	 
	public Connection getConnection() throws ClassNotFoundException, SQLException {
 
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:sqlserver://localhost:80;databasename=clientetest;integratedsecurity=true");
 
		return c;
	}
	
}
