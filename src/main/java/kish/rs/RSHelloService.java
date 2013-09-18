package kish.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/helloRestService/")
public class RSHelloService {
	
	@GET
	@Path("/greet/{name}")
	public String greet(@PathParam("name") String name) {
		String temp = "Rest Greetings, " + name;
		return temp;
	}
}
