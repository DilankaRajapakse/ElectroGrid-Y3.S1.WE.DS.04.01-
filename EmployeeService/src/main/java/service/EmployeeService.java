package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Employee;
import service.InterServiceCom;

@Path("/Employee")
public class EmployeeService {
	
	Employee emp = new Employee();
	
	/****************************Insert**************************/
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(String EmpData)
	{
		//Convert the input string to a JSON object
		 JsonObject EmpObj = new JsonParser().parse(EmpData).getAsJsonObject();
		 
		//Read the values from the JSON object
		 String nic = EmpObj.get("nic").getAsString();
		 String name = EmpObj.get("name").getAsString();
		 String dob = EmpObj.get("dob").getAsString();
		 String address = EmpObj.get("address").getAsString();
		 int phone = EmpObj.get("phone").getAsInt();
		 float salary = EmpObj.get("salary").getAsFloat();
		 String type = EmpObj.get("type").getAsString();
		 String branch = EmpObj.get("branch").getAsString();
		 
		 //sending data to method
		 String output = emp.insert(nic,name,dob,address,phone,salary,type,branch);
		 return output;
	
	}
	
	/****************************Read**************************/
	@GET
	@Path("/{branch}")
	@Produces(MediaType.TEXT_HTML)
	public String readEmp(@PathParam("branch") String br)
	 {
		 
		 
		 return emp.rEmp(br);
	}
	
	/****************************Read One Data**************************/
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmp()
	 {
	 return emp.readEmp();
	}
	
	/****************************Update**************************/
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmp(String EmpData)
	{
		//Convert the input string to a JSON object
		 JsonObject EmpObj = new JsonParser().parse(EmpData).getAsJsonObject();
		 
		//Read the values from the JSON object
		 String id = EmpObj.get("empid").getAsString();
		 String nic = EmpObj.get("nic").getAsString();
		 String name = EmpObj.get("name").getAsString();
		 String dob = EmpObj.get("dob").getAsString();
		 String address = EmpObj.get("address").getAsString();
		 int phone = EmpObj.get("phone").getAsInt();
		 float salary = EmpObj.get("salary").getAsFloat();
		 String type = EmpObj.get("type").getAsString();
		 String branch = EmpObj.get("branch").getAsString();
		 
		 //sending data to method
		 String output = emp.updateEmp(id,nic,name,dob,address,phone,salary,type,branch);
		 return output;
	
	}
	
	
	/****************************Delete**************************/
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmp(String EmpData)
	{
		//Convert the input string to a JSON object
		 JsonObject EmpObj = new JsonParser().parse(EmpData).getAsJsonObject();
		
		//Read the values from the JSON object
		 String id = EmpObj.get("empid").getAsString();
		 
		 String output = emp.deleteEmployee(id);
			 
			 
			return output;
	}
	
	/*Note use for Inter Service Communication for Getting Employee Data by providing NIC*/
	@GET
	@Path("/Head/{nic}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readEmpSource(@PathParam("nic") String id) {
		
		JsonObject result = null;
		
		result=emp.readEmp(id);
		return result.toString();
		
	}
	
	
	//Do not use the Below fucntion
	/*************************Inter-Service Communication for Power Source********************/
	@GET
	@Path("/Source/{eng}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readSource(@PathParam("eng") String id)
	 {
		 
		 return (new InterServiceCom().PowerSource("/" + id)).toString();
		 //return emp.rEmp(br);
	}
	
	
}
