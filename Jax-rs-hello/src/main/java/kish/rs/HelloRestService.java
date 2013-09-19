package kish.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/helloRestService/")
public class HelloRestService {
	
	@GET
	@Path("/greet/{user}")
	public String greet(@PathParam("user") String name) {
		String temp = "Rest Greetings, Hi " + name;
		System.out.println( temp );
		return temp;
	}

	@GET
	@Path("/wish/{user}")
	public String wish(@PathParam("user") String name) {
		String temp = "Rest Wishes, Hi " + name;
		System.out.println( temp );
		return temp;
	}
}
