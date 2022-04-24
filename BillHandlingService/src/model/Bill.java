package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill{

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/paf?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public String insertBilling(String Bill_date, String Consumed_Units, String Unit_Price, String Total_Amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into bills(`Bill_ID`,`Bill_date`,`Consumed_Units`,`Unit_Price`,`Total_Amount`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Bill_date);
			preparedStmt.setString(3, Consumed_Units);
			preparedStmt.setString(4, Unit_Price);
			preparedStmt.setString(5, Total_Amount);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the billing.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBilling() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Bill ID</th><th>Date</th><th>Consumed Unit</th><th>Unit Price</th><th>Total Amount</th></tr>";
			String query = "select * from bills";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Bill_ID = Integer.toString(rs.getInt("Bill_ID"));
				String Bill_date = rs.getString("Bill_date");
				String Consumed_Units = rs.getString("Consumed_Units");
				String Unit_Price = rs.getString("Unit_Price");
				String Total_Amount = rs.getString("Total_Amount");
				

				// Add into the html table
				output += "<tr><td>" + Bill_ID + "</td>";
				output += "<td>" + Bill_date + "</td>";
				output += "<td>" + Consumed_Units + "</td>";
				output += "<td>" + Unit_Price + "</td>";
				output += "<td>" + Total_Amount + "</td>";
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the billing.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBilling(String Bill_ID, String Bill_date, String Consumed_Units, String Unit_Price, String Total_Amount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE bills SET Bill_date=?,Consumed_Units=?,Unit_Price=?,Total_Amount=?" + "WHERE Bill_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Bill_date);
			preparedStmt.setString(2, Consumed_Units);
			preparedStmt.setString(3, Unit_Price);
			preparedStmt.setString(4, Total_Amount);
			preparedStmt.setInt(5, Integer.parseInt(Bill_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the billing.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteBilling(String Bill_ID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from bills where Bill_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Bill_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the billing.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
