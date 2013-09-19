package kish.spring.jpadata.services.impl;

import kish.spring.jpadata.entities.Customer;
import kish.spring.jpadata.repo.CustomerRepository;
import kish.spring.jpadata.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepo;
	
	@Transactional
	public void addCustomer(Customer customer) {
		customerRepo.save(customer);
	}
}
