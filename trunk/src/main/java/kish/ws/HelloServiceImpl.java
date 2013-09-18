package kish.ws;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService(endpointInterface = "kish.ws.HelloService")
public class HelloServiceImpl implements HelloService {

	@Resource
	WebServiceContext wsContext;
	
	public HelloServiceImpl() {
	}

	public String sayHello(String name) {
		Map map = (TreeMap)wsContext.getMessageContext().get(javax.xml.ws.handler.MessageContext.HTTP_REQUEST_HEADERS);
		System.out.println( "context-------" + wsContext );
		System.out.println( map );
		
		String temp = "Hello, " + name + "!";
		System.out.println( temp );
		return temp;
	}

	public String sayBye(String name) {
		String temp = "Bye, " + name + "!";
		System.out.println( temp );
		return temp;
	}

}
