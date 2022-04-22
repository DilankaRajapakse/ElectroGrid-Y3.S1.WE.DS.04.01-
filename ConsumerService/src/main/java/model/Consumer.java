package model;

import java.sql.*; 

public class Consumer {
	private Connection connect() { 
	 Connection con = null; 
	 try { 
	 Class.forName("com.mysql.jdbc.Driver"); 	 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ElectroGrid", "root", ""); 
	 } 
	 catch (Exception e) {
		 e.printStackTrace();
	 } 
	 return con; 
	 } 
	
	//insert
	public String insertConsumer(String nic, String name, String address, String phone, Date date, String power, String type) { 
	 String output = ""; 

	 try{ 
		 Connection con = connect(); 
		 if (con == null) {
			 return "Error while connecting to the database for inserting."; 
			 }
		 
	 // create a prepared statement
	 String query = " insert into consumers(`id`,`regNo`,`name`,`address`,`phone`,`regDate`,`power_usage`,`consumer_type`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, nic); 
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, address);
	 preparedStmt.setString(5, phone);
	 preparedStmt.setDate(6, date);
	 preparedStmt.setInt(7, Integer.parseInt(power)); 
	 preparedStmt.setString(8, type); 
	 
	 // execute the statement	 
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) { 
		 output = "Error while inserting the item."; 
		 System.err.println(e.getMessage()); 
	 	} 
	 return output; 
	 } 
	
	
	//retrieve
	public String readConsumers() { 
	 String output = "";
	 
	 try { 
	 Connection con = connect(); 

	 if (con == null) {
		 return "Error while connecting to the database for reading."; 
		 }
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'>" + 
			"<tr>" +
			"<th>NIC/Reg_No</th>" +
			"<th>Consumer Name</th>" +
	 		"<th>Consumer Address</th>" + 
	 		"<th>Contact</th>" +
	 		"<th>Registered Date</th>" +
	 		"<th>Power Consumnption</th>" +
	 		"<th>Consumer Type</th>" +
	 		"<th>Update</th>" +
	 		"<th>Remove</th>" +
	 		"</tr>"; 
	 
	 String query = "select * from consumers";
	 //String query = "SELECT `regNo`, `name`, `address`, `phone`, `regDate`, `power_usage`, `consumer_type` FROM `consumers`";
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 while (rs.next()) { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String nic = rs.getString("regNo"); 
	 
	 
	 String name = rs.getString("name"); 
	 String address = rs.getString("address"); 
	 String phone = rs.getString("phone");
	 String regDate = rs.getString("regDate"); 
	 //String power = rs.getString("power_usage");
	 String power = Integer.toString(rs.getInt("power_usage")); 
	 String type = rs.getString("consumer_type");
	 
	 // Add into the html table
	 output += "<tr><td>" + nic + "</td>"; 
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + address + "</td>"; 
	 output += "<td>" + phone + "</td>"; 
	 output += "<td>" + regDate + "</td>";
	 output += "<td>" + power + "</td>"; 
	 output += "<td>" + type + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'>"
	 + "<input name='itemID' type='hidden' value='" + id + "'>" 
	 + "</form></td>"
	 + "</tr>"; 
	 } 

	 con.close();
	 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 
	 catch (Exception e) { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

	//update
	public String updateConsumers(String id, String nic, String name, String address, String phone, String date, String power, String type) { 
	 String output = ""; 
	 
	 try { 
	 Connection con = connect(); 
	 
	 if (con == null) {
		 return "Error while connecting to the database for updating."; 
		 } 
	 
	 // create a prepared statement
	 String query = "UPDATE consumers SET regNo=?, name=?, address=?, phone=?, regDate=?, power_usage=?, consumer_type=? WHERE id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setString(1, nic); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, address); 
	 preparedStmt.setString(4, phone); 
	 preparedStmt.setString(5, date);
	 preparedStmt.setInt(6, Integer.parseInt(power));
	 preparedStmt.setString(7, type);
	 preparedStmt.setInt(8, Integer.parseInt(id));
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) { 
	 output = "Error while updating the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	//delete
	public String deleteConsumer(String id) { 
	 String output = "";
	 
	 try { 
	 Connection con = connect(); 
	 
	 if (con == null) {
		 return "Error while connecting to the database for deleting."; 
		 } 
	 
	 // create a prepared statement
	 String query = "delete from consumers where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 
	 return output; 
	 } 
}
