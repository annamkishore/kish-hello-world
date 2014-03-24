package kish.spring.jpadata.services;

import java.util.List;

import kish.spring.jpadata.entities.Customer;

public interface CustomerService {
	public void addCustomer(Customer customer);
	public List<Customer> listCustomers();
}
