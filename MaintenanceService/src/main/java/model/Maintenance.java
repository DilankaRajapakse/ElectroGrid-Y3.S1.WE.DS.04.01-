package model; 
import java.sql.*; 

public class Maintenance 
{ //connect to the DB
	DB_Connect DB = new DB_Connect();
	
	
	
	
	
	/********************Read Maintenance*****************************/
	public String readMaintenance() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = DB.connect();
	 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; }
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Complaint ID</th><th>Grid Name</th>" +
	 "<th>Type</th>" + 
	 "<th>Complaint</th>" ;
	 //"<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from Maintenance"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String compID = Integer.toString(rs.getInt("compID")); 
	 String compCode = rs.getString("compCode"); 
	 String gridName = rs.getString("gridName"); 
	 String compType = Double.toString(rs.getDouble("compType")); 
	 String compDesc = rs.getString("compDesc"); 
	 
	 
	 // Add into the html table
	 output += "<tr><td>" + compCode + "</td>"; 
	 output += "<td>" + gridName + "</td>"; 
	 output += "<td>" + compType + "</td>"; 
	 output += "<td>" + compDesc + "</td>"; 
	 // buttons
	 //output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 //+ "<input name='itemID' type='hidden' value='" + itemID 
	 //+ "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
/**************************************Insert Maintenance**********************************/
	
	
	
public String insertMaintenance(String code, String name, String type, String desc) 
 { 

 // create a prepared statement
 String query = " insert into items (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
 + " values (?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, code); 
 preparedStmt.setString(3, name); 
 preparedStmt.setDouble(4, Double.parseDouble(price)); 
 preparedStmt.setString(5, desc); 
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




/**********************update Maintenance********************/
public String updateMaintainance(String ID, String code, String name, String type, String desc) 

{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = DB.connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; }
	 
	 // create a prepared statement
	 String query = "UPDATE maintainance SET compCode=?,gridName=?,compType=?,compDesc=? WHERE compID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setString(1, code); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setDouble(3, Double.parseDouble(type)); 
	 preparedStmt.setString(4, desc); 
	 preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 



		/***************Delete Maintenance********************/

	public String deleteMaintenance(String compID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = DB.connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 
	 // create a prepared statement
	 String query = "delete from maintenance where compID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(compID));
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	/***********Getting one detail***************/
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
	
	
	
	}