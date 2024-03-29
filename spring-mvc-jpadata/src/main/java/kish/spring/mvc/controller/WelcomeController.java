package kish.spring.mvc.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import kish.spring.jpadata.entities.Customer;
import kish.spring.jpadata.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class WelcomeController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/welcome")
	public String printWelcome(Model model, Map<String, Object> map) {
		model.addAttribute("customer2", new Customer());

		List<Customer> customerList = customerService.listCustomers();
		map.put("customerList", customerList);
		
		return "/pages/index";
	}

	@RequestMapping("/welcome1")
	public String printWelcome1(Model model, Map<String, Object> map, Locale locale) {
		
		map.put("message",  "map message");
		
		return "/pages/welcome";
	}
	
	@RequestMapping(value="/demoController", method = RequestMethod.GET)
	public String demoService(@ModelAttribute("customer2") Customer customer,  BindingResult result, Map<String, Object> map, Locale locale, Model model) {
		System.out.println( customer );
		String msg = "";
		if( result.hasErrors() ) {
			msg = "Error in Input";
		}else {
			customerService.addCustomer(customer);
			msg = "Success storing in DB";
		}

		map.put("message", msg);
		List<Customer> customerList = customerService.listCustomers();
		map.put("customerList", customerList);
		return "/pages/index";
	}
	
	
	@RequestMapping(value="customersPage", method = RequestMethod.GET)
	public String getCustomersPage(Model model, Pageable pageable) {
		
		Page<Customer> custPage = customerService.listCustomersPage(pageable);
		model.addAttribute("page", custPage);
		
		return "/pages/customersPageable";
	}
	
	@RequestMapping("/tabDemo")
	public String tabDemoService(Model model, Map<String, Object> map, Locale locale) {
		
		return "/tab/Home";
	}
	
	@RequestMapping(value="/customer/{custid}", method = RequestMethod.GET)
	public @ResponseBody Customer getCustomerById(@PathVariable Long custid) {
		
		Customer cust = customerService.findCustomerById(custid);
		return cust;
	}
}