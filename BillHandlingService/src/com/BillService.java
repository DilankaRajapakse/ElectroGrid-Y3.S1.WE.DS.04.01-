package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import com.google.gson.*;


import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.Bill;

@Path("/Bill")
public class BillService {
	Bill BillingObj = new Bill();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBilling() {
		return BillingObj.readBilling();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBilling(@FormParam("Bill_date") String Bill_date,		
	 @FormParam("Consumed_Units") String Consumed_Units,
	 @FormParam("Unit_Price") String Unit_Price,
	 @FormParam("bAmount") String bAmount)
	{
	 String output = BillingObj.insertBilling(Bill_date, Consumed_Units, Unit_Price, bAmount);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBilling(String billingData)
	{
	//Convert the input string to a JSON object
	 JsonObject BillObject = new JsonParser().parse(billingData).getAsJsonObject();
	//Read the values from the JSON object
	 String Bill_ID = BillObject.get("Bill_ID").getAsString();
	 String Bill_date = BillObject.get("Bill_date").getAsString();
	 String Consumed_Units = BillObject.get("Consumed_Units").getAsString();
	 String Unit_Price = BillObject.get("Unit_Price").getAsString();
	 String Total_Amount = BillObject.get("Total_Amount").getAsString();
	 String output = BillingObj.updateBilling(Bill_ID, Bill_date, Consumed_Units, Unit_Price, Total_Amount);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBilling(String billingData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billingData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String Bill_ID = doc.select("Bill_ID").text();
	 String output = BillingObj.deleteBilling(Bill_ID);
	return output;
	}
}
