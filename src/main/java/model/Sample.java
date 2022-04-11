package model;
import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DB_Connector;

public class Sample {
	
	DB_Connector DB= new DB_Connector();
	
	public String insert(String code,String name)
	 {
			String output = "";
				 try
				 {
					
					 //DB connection is established in below
					 Connection con = DB.connect();
					 
					
					 if (con == null)
					 {return "Error while connecting to the database for inserting."; }
					 
					 
					 // create a prepared statement
					 String query = " insert into sample (`code`,`name`)"
					 + " values (?, ?)";
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setString(1, code);
					 preparedStmt.setString(2, name);
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 output = "Inserted successfully";
				 }
				 catch (Exception e)
				 {
					 output = "Error while inserting the item.";
					 System.err.println(e.getMessage());
				 }
				 
				 	return output;
	 }

}
