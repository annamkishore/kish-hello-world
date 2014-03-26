package kish.spring.jpadata.services;

import java.util.List;

import kish.spring.jpadata.entities.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
	public void addCustomer(Customer customer);
	public List<Customer> listCustomers();
	public Customer findCustomerById(Long id);
	public Page<Customer> listCustomersPage(Pageable pageable);
}
