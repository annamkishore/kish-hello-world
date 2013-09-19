package kish.ws;

import javax.jws.WebService;

@WebService
public interface HelloService {

	public String sayHello(String name);
	
	public String sayBye(String name);
}
