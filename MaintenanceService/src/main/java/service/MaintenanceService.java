package service;

import model.Maintenance;
import service.InterServiceCommunication;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Maintenance")
public class MaintenanceService {
	Maintenance ms = new Maintenance();
	
	/******************************Insert*************************/
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertMaintenance(@FormParam("id") String id,
								@FormParam("area") String area,
								@FormParam("gridName") String gridName,
								@FormParam("compType") String compType,
								@FormParam("complaint") String complaint)
								
								
	{
		String output = ms.insertMaintenance(id,area,gridName,compType,complaint);
		return output;
	}
	
	/************************Read everything*****************************/
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readMaintenance() {
		return ms.readMaintenance();
	}
	
	/**************************Update**********************************/
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMaintenance(String itemData) {
		//Covert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read the values from the JSON Object
		String compID = itemObject.get("compID").getAsString();
		String id = itemObject.get("id").getAsString();
		String area = itemObject.get("area").getAsString();
		String gridName = itemObject.get("gridName").getAsString();
		String compType = itemObject.get("compType").getAsString();
		String complaint = itemObject.get("complaint").getAsString();
		
		
		String output = ms.updateMaintenance(compID,id,area,gridName,compType,complaint);
		return output;
	}
	
	/**********************Delete*************************/
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteMaintenance(String maintenanceData) {
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(maintenanceData,"",Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String compID = doc.select("compID").text();
		String output = ms.deleteMaintenance(compID);
		return output;
	}
	
	/*************Inter-service communication between the consumer / retrieving single consumer *****************/
	@GET
	@Path("/{cus_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readMaintainCus(@PathParam("cus_id") String id) {
		
		JsonObject result = null;
		
		result=ms.readMaintainCus(id);
		return result.toString();
		
	}
	
	/*************Interservice communication with Consumer*******************************/
	/********http://localhost:8080/MaintenanceService/ElectroGrid/Maintenance/Maintain/3************/
	@GET
	@Path("/Maintain/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readSource(@PathParam("id") String id)
	 {
		 
		 return (new InterServiceCommunication().CustomerDetails("/" + id)).toString();
		 //return emp.rEmp(br);
	}
}
