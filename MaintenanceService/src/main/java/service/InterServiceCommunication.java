package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class InterServiceCommunication {
	
			private static final String PROTOCOL = "http://";
			private static final String HOST = "127.0.0.1";
			private static final String PORT = "8080";
			private static final String CONSUMER_SERVICE_URI = PROTOCOL + HOST + ":" + PORT + "/ConsumerService/ElectroGrid/Consumers/id";
		
		public JsonObject CustomerDetails(String path) {
			
			

				Client client = Client.create();

				WebResource webResource = client.resource(CONSUMER_SERVICE_URI+path);

				ClientResponse response = webResource.accept("application/json")
		                   .get(ClientResponse.class);

				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
				}

				String output = response.getEntity(String.class);

				
				
				JsonObject JSONoutput = new JsonParser().parse(output).getAsJsonObject();
				return JSONoutput;

			  
		}
}
