package model;

import util.DB_Connector;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.sql.*;


public class Maintenance {
	
	private static Connection con = null;
	
	/******************************insert************************************/ 
	public String insertMaintenance(String id, String area, String gridName, String compType, String complaint) {
		String output = "";
		
		try {
				con = DB_Connector.connect();
			
			if(con == null)
				return "Database connection failed for inserting data";
			
			// create a prepared statement
			String query = " insert into maintenance (`compID`,`id`,`area`,`gridName`,`compType`,`complaint`)"
					 + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2,Integer.parseInt(id));
			preparedStmt.setString(3,area);
			preparedStmt.setString(4,gridName);
			preparedStmt.setString(5,compType);
			preparedStmt.setString(6,complaint);
			
			
			// execute the statement
			preparedStmt.executeUpdate();
			con.close();
			output = "Data was successfully inserted";
		}
		
		catch (Exception e)
		{
			output = "Error while inserting the maintenance reort details";
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		return output;
	}
	
	
	
	
	
	/*************************Read all the maintenance reports***********************/
	public String readMaintenance()
	{
		String output = "";
		try
		{
			con = DB_Connector.connect();
			if (con == null)
				return "Database connection failed for reading data.";
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Cutomer ID</th>" +
					"<th>Area</th><th>Grid Name</th>" +
					"<th>Complaint Type</th>"
					+ "<th>Complaint</th></tr>";
			String query = "select * from maintenance";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String compID = Integer.toString(rs.getInt("compID"));
				String id = Integer.toString(rs.getInt("id"));
				String area = rs.getString("area");
				String gridName = rs.getString("gridName");
				String compType = rs.getString("compType");
				String complaint = rs.getString("complaint");
				
				
				// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + area + "</td>";
				output += "<td>" + gridName + "</td>";
				output += "<td>" + compType + "</td>";
				output += "<td>" + complaint + "</td>";
				
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='itemID' type='hidden' value='" + compID 
				 + "'>" + "</form></td></tr>"; 
				 }
				
			con.close();
			// Complete the html table
			output += "</table>";
			
		}
		catch (Exception e)
		{
			output = "Error while reading the maintenance details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	
	
	
	/*************************************Update********************************/
	public String updateMaintenance(String compID, String id, String area, String gridName, String compType, String complaint)
	{
		String output = "";
		
		try
		{
			con = DB_Connector.connect();
			if (con == null)
				return "Database connection failed for updating data."; 
			
			// create a prepared statement
			String query = "UPDATE maintenance SET id=?,area=?,gridName=?,compType=?,complaint=? WHERE compID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
		
			preparedStmt.setInt(1, Integer.parseInt(id));
			preparedStmt.setString(2, area);
			preparedStmt.setString(3, gridName );
			preparedStmt.setString(4, compType);
			preparedStmt.setString(5, complaint);
			preparedStmt.setInt(6, Integer.parseInt(compID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "The maintenance data was updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating maintenance report.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	/***********************************Delete*********************************/
	public String deleteMaintenance(String ID)
	{
		String output = "";
		
		try
		{
			con = DB_Connector.connect();
			if (con == null)
				return "Database connection failed for deleting data."; 
			
			// create a prepared statement
			
		String query = "delete from maintenance where compID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(ID));
		
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "maintenance report was deleted successfully";
	}
		catch (Exception e)
		{
			output = "Error while deleting the power source.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	/**************Reading Source by the Consumer Id*******************/
	public JsonObject readMaintainCus(String id)
	{
		JsonObject output = null;
		
		try
		{
			con = DB_Connector.connect();
			if (con == null) {
				output=new JsonObject();
				output.addProperty("MESSAGE", "Database connection failed for reading data.");
				//return "Database connection failed for reading data.";
			}
			//
			String query = "select * from maintenance where id='"+id+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				JsonObject dbObject = new JsonObject();
				dbObject.addProperty("area", rs.getString("area"));
				dbObject.addProperty("gridName", rs.getString("gridName"));
				output=dbObject;
				
			}
			con.close();
			
		}
		catch (Exception e)
		{
			output=new JsonObject();
			output.addProperty("MESSAGE","Error while reading the power source details.");
			//output = "Error while reading the power source details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
