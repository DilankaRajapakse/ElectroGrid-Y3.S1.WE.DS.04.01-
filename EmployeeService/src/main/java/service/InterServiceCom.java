package service;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

public class InterServiceCom {
	//http://localhost:8082/PowerSource/ElectroGrid/PowerSource
		private static final String PROTOCOL = "http://";
		private static final String HOST = "127.0.0.1";
		private static final String PORT = "8082";
		private static final String POWER_SOURCE_URI = PROTOCOL + HOST + ":" + PORT + "/PowerSource/ElectroGrid/PowerSource";
	
	public JsonObject PowerSource(String path) {
		
		

			Client client = Client.create();

			WebResource webResource = client.resource(POWER_SOURCE_URI+path);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			//System.out.println("Output from Server .... \n");
			//System.out.println(output);
			
			JsonObject JSONoutput = new JsonParser().parse(output).getAsJsonObject();
			return JSONoutput;

		  
	}

}
