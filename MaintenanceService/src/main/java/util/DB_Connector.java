package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connector {
	
	public static Connection connect()
	{
			Connection con = null;
				 try
				 {
					 Class.forName("com.mysql.jdbc.Driver");
				
					 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/electrogrid", "root", "");
				 }
				 catch (Exception e)
				 {e.printStackTrace();}
				 
				 	return con;
	}
}
