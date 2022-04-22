package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.JsonObject;

import util.DB_Connector;


public class Employee {
	DB_Connector DB= new DB_Connector();
	/**********************************Read Employees*******************************************/
	
	public String readEmp()
	 {
			String output = "";
			 try
			 {
					 Connection con = DB.connect();
					 
					 if (con == null)
					 {return "Error while connecting to the database for reading."; }
					 
					 
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>NIC</th><th>Name</th>" +
					 "<th>DOB</th>" +
					 "<th>Address</th>" +
					 "<th>Phone </th>" +
					 "<th>Salary</th>" +
					 "<th>Type</th>" +
					 "<th>Branch</th>";
				
					 String query = "select * from Employee";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 
					 // iterate through the rows in the result set
					 while (rs.next())
					 {
					 String nic = rs.getString("nic");
					 String name = rs.getString("name");
					 String dob = rs.getString("dob");
					 String address = rs.getString("address");
					 String ph = Integer.toString(rs.getInt("phone"));
					 String sal =Float.toString(rs.getFloat("salary"));
					 String type = rs.getString("type");
					 String branch = rs.getString("branch");
					 
					 
					 
					 // Add into the html table
					 output += "<tr><td>" + nic + "</td>";
					 output += "<td>" + name + "</td>";
					 output += "<td>" + dob+ "</td>";
					 output += "<td>" + address+ "</td>";
					 output += "<td>" + ph + "</td>";
					 output += "<td>" + sal + "</td>";
					 output += "<td>" + type + "</td>";
					 output += "<td>" + branch + "</td></tr>";
					 
					 }
					 con.close();
					 
					 // Complete the html table
					 output += "</table>";
			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the Employee Data.";
				 System.err.println(e.getMessage());
			 }
			 	
			 
			 return output;
	 }
	/**********************************Insert Employees*******************************************/
	
	public String insert(String nic,String name,String dob,String address,int phone,float salary,String type,
						String branch)
	 {
			String output = "";
				 try
				 {
					
					 //DB connection
					 Connection con = DB.connect();
					 
					
					 if (con == null)
					 {return "Error while connecting to the database for inserting."; }
					 
					 
					 // create a prepared statement
					 String query = " insert into employee values (?,?,?,?,?,?,?,?,?)";
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1,0);
					 preparedStmt.setString(2, nic);
					 preparedStmt.setString(3, name);
					 preparedStmt.setString(4, dob);
					 preparedStmt.setString(5, address);
					 preparedStmt.setInt(6, phone);
					 preparedStmt.setFloat(7, salary);
					 preparedStmt.setString(8, type);
					 preparedStmt.setString(9, branch);
				
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 output = "Inserted Employee Data Successfully";
				 }
				 catch (Exception e)
				 {
					 output = "Error while inserting the Employee Data.";
					 System.err.println(e.getMessage());
				 }
				 
				 	return output;
	 }
	
	/*******************************Update Employees********************************/

	public String updateEmp(String ID,String nic,String name,String dob,String address,int phone,float salary,String type,
			String branch)
	{
		 String output = "";
		 
		 
		 try
		 {
				 Connection con = DB.connect();
				 if (con == null)
				 {return "Error while connecting to the database for updating."; }
				 
				 
				 // create a prepared statement
				 String query = "UPDATE employee SET nic=?,name=?,dob=?,address=?,phone=?,salary=?,type=?,branch=? WHERE empid=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 
				 // binding values
				 
			
				 
				 preparedStmt.setString(1, nic);
				 preparedStmt.setString(2, name);
				 preparedStmt.setString(3, dob);
				 preparedStmt.setString(4, address);
				 preparedStmt.setInt(5, phone);
				 preparedStmt.setFloat(6, salary);
				 preparedStmt.setString(7, type);
				 preparedStmt.setString(8, branch);
				 preparedStmt.setInt(9, Integer.parseInt(ID));
				 
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 
				 output = "Employee Data Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating Employee.";
			 System.err.println(e.getMessage());
		 }
		 
			return output;
		 }
	
	/*************************************Delete******************************************/
	public String deleteEmployee(String ID)
	 {
			String output = "";
			 try
			 {
				 	Connection con = DB.connect();
				 	
					 if (con == null)
					 {return "Error while connecting to the database for deleting employee."; }
					 
					 
			 // create a prepared statement
			 String query = "delete from employee where empid=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(ID));
			 
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Employee Deleted successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while deleting the Employee Object.";
				 System.err.println(e.getMessage());
			 }
			 
			 
			 	return output;
	 }
	/*******************Getting one detail*****************/
	public String rEmp(String branch)
	 {
			String output = "";
			 try
			 {
					 Connection con = DB.connect();
					 
					 if (con == null)
					 {return "Error while connecting to the database for reading."; }
					 
					 
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>NIC</th><th>Name</th>" +
					 "<th>DOB</th>" +
					 "<th>Address</th>" +
					 "<th>Phone </th>" +
					 "<th>Salary</th>" +
					 "<th>Type</th>" +
					 "<th>Branch</th>";
				
					 String query = "select * from Employee where branch='"+branch+"'";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 
					 // iterate through the rows in the result set
					 while (rs.next())
					 {
					 String nic = rs.getString("nic");
					 String name = rs.getString("name");
					 String dob = rs.getString("dob");
					 String address = rs.getString("address");
					 String ph = Integer.toString(rs.getInt("phone"));
					 String sal =Float.toString(rs.getFloat("salary"));
					 String type = rs.getString("type");
					 String br = rs.getString("branch");
					 
					 
					 
					 // Add into the html table
					 output += "<tr><td>" + nic + "</td>";
					 output += "<td>" + name + "</td>";
					 output += "<td>" + dob+ "</td>";
					 output += "<td>" + address+ "</td>";
					 output += "<td>" + ph + "</td>";
					 output += "<td>" + sal + "</td>";
					 output += "<td>" + type + "</td>";
					 output += "<td>" + br + "</td></tr>";
					 
					 }
					 con.close();
					 
					 // Complete the html table
					 output += "</table>";
			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the '"+branch+"'Employee Data.";
				 System.err.println(e.getMessage());
			 }
			 	
			 
			 return output;
	 }
	
	/*Reading Employees by ID*/
	
	public JsonObject readEmp(String id)
	{
		JsonObject output = null;
		
		try
		{
			Connection con = DB.connect();
			if (con == null) {
				output=new JsonObject();
				output.addProperty("MESSAGE", "Database connection failed for reading Employee data.");
				//return "Database connection failed for reading data.";
			}
			//
			String query = "select * from Employee where nic='"+id+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				JsonObject dbObject = new JsonObject();
				dbObject.addProperty("Employee Name", rs.getString("name"));
				dbObject.addProperty("Employee Address", rs.getString("address"));
				dbObject.addProperty("Employee Phone", rs.getInt("phone"));
				
				
				output=dbObject;
				
			}
			con.close();
			
		}
		catch (Exception e)
		{
			output=new JsonObject();
			output.addProperty("MESSAGE","Error while reading the employee details.");
			//output = "Error while reading the power source details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
