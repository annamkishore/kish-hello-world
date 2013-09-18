package kish.ws;
 
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
 
public class HelloWorldClient{
 
	public static void main(String[] args) throws Exception {
 
	URL url = new URL("http://localhost:8080/WSHelloWorld/helloService?wsdl");
 
        //1st argument service URI, refer to wsdl document above
	//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.kish/", "HelloServiceImplService");
 
        Service service = Service.create(url, qname);
 
        HelloService hello = service.getPort(HelloService.class);
 
        System.out.println(hello.sayHello("kish"));
 
    }
 
}